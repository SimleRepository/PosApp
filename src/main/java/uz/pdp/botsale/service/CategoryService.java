package uz.pdp.botsale.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uz.pdp.botsale.entity.Category;
import uz.pdp.botsale.exception.ResourceNotFoundException;
import uz.pdp.botsale.payload.ApiResponse;
import uz.pdp.botsale.payload.ApiResponseModel;
import uz.pdp.botsale.payload.ReqCategory;
import uz.pdp.botsale.payload.ResPageable;
import uz.pdp.botsale.repository.CategoryRepository;
import uz.pdp.botsale.utils.CommonUtils;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public ApiResponse saveOrEdit(ReqCategory reqCategory) {
        ApiResponse response = new ApiResponse();
        try {
            response.setMessage("Saved");
            response.setSuccess(true);
            Category category = new Category();
            if (!reqCategory.getName().equals(categoryRepository.findByName(reqCategory.getName())) && reqCategory.getName() != null || reqCategory.getId() != null) {
                if (reqCategory.getId() != null) {
                    categoryRepository.findById(reqCategory.getId()).orElseThrow(() -> new ResourceNotFoundException("Category", "id", reqCategory.getId()));
                    response.setMessage("Edited");
                }
                if (reqCategory.getParentId() != null) {
                    category.setParent(categoryRepository.findById(reqCategory.getParentId()).orElseThrow(() -> new ResourceNotFoundException("parentCategory", "id", reqCategory.getParentId())));
                }
                category.setName(reqCategory.getName());
                categoryRepository.save(category);
            } else if (reqCategory.getName().equals(categoryRepository.findByName(reqCategory.getName()))) {
                response.setMessage("This massage already exist");
                response.setSuccess(false);
            } else if (reqCategory.getName() == null) {
                response.setMessage("must be name");
                response.setSuccess(false);
            }
        } catch (Exception e) {
            response.setMessage("Error");
            response.setSuccess(false);
        }
        return response;
    }

    public ResPageable pageable(Integer page, Integer size, String search) {
        Page<Category> categoryPage = categoryRepository.findAll(CommonUtils.getPageableById(page, size));
        if (!search.equals("all")){
            categoryPage = categoryRepository.findAllByNameContainingIgnoreCase(search, CommonUtils.getPageableById(page, size));
        }
        return new ResPageable(categoryPage.getContent(),categoryPage.getTotalElements(),page);
    }

    public ApiResponse changeActive(Integer id, boolean active) {
        try {
            Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("category", "id", id));
            category.setActive(active);
            categoryRepository.save(category);
            return new ApiResponse(active?"Activated":"Blocked",true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }

    public ApiResponseModel getAll() {
        return new ApiResponseModel(true,"Ok",categoryRepository.findAll());
    }

    public ApiResponse removeCategory(Integer id) {
        try {
            categoryRepository.deleteById(id);
            return new ApiResponse("Deleted", true);
        }catch (Exception e){
            return new ApiResponse("Error", false);
        }
    }
}
