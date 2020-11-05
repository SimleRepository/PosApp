package uz.pdp.botsale.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uz.pdp.botsale.entity.Brand;
import uz.pdp.botsale.exception.ResourceNotFoundException;
import uz.pdp.botsale.payload.ApiResponse;
import uz.pdp.botsale.payload.ReqBrand;
import uz.pdp.botsale.payload.ResPageable;
import uz.pdp.botsale.repository.AttachmentRepository;
import uz.pdp.botsale.repository.BrandRepository;
import uz.pdp.botsale.utils.CommonUtils;

@Service
public class BrandService {

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

    public ApiResponse saveOrEdit(ReqBrand reqBrand) {
        ApiResponse response = new ApiResponse();
        try {
            if (!reqBrand.getName().equals(brandRepository.findByName(reqBrand.getName())) && reqBrand.getBrandIcon() != null) {
                response.setMessage("Saved");
                response.setSuccess(true);
                Brand brand = new Brand();
                if (reqBrand.getId() != null) {
                    brand = brandRepository.findById(reqBrand.getId()).orElseThrow(() -> new ResourceNotFoundException("Brand", "id", reqBrand.getId()));
                    response.setMessage("Edited");
                }
                brand.setBrandIcon(attachmentRepository.findById(reqBrand.getBrandIcon()).orElseThrow(() -> new ResourceNotFoundException("BranIcon", "id", reqBrand.getBrandIcon())));
                brand.setName(reqBrand.getName());
//               ////////******************************************************************
                System.err.println("You must sent sms my Number !!");
//               ////////******************************************************************
                brandRepository.save(brand);
            } else if (reqBrand.getName().equals(brandRepository.findByName(reqBrand.getName()))) {
                response.setMessage("This massage already exist");
                response.setSuccess(false);
            } else if (reqBrand.getBrandIcon() == null) {
                response.setMessage("siz rasm tanlang");
                response.setSuccess(false);
            } else if (reqBrand.getName() == null) {
                response.setMessage("Brand nomini yozing!");
                response.setSuccess(false);
            }
        } catch (Exception e) {
            response.setMessage("Error");
            response.setSuccess(false);
        }
        return response;
    }

    public ResPageable pageable(Integer page, Integer size, String search) {
        Page<Brand> brandPage = brandRepository.findAll(CommonUtils.getPageableById(page, size));
        if (!search.equals("all")) {
            brandPage = brandRepository.findAllByNameContainingIgnoreCase(search, CommonUtils.getPageableById(page, size));
        }
        return new ResPageable(brandPage.getContent(), brandPage.getTotalElements(), page);
    }

    public ApiResponse changeActive(Long id, boolean active) {
        try {
            Brand brand = brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("brand", "id", id));
            brand.setActive(active);
            brandRepository.save(brand);
            return new ApiResponse(active ? "Activated" : "Blocked", true);
        } catch (Exception e) {
            return new ApiResponse("Error", false);
        }
    }

    public ApiResponse removeBrand(Long id) {
        try {
            brandRepository.deleteById(id);
            return new ApiResponse("Deleted",true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }
}
