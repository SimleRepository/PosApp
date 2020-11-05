package uz.pdp.botsale.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uz.pdp.botsale.entity.Product;
import uz.pdp.botsale.exception.ResourceNotFoundException;
import uz.pdp.botsale.payload.ApiResponse;
import uz.pdp.botsale.payload.ReqProduct;
import uz.pdp.botsale.payload.ResPageable;
import uz.pdp.botsale.repository.BrandRepository;
import uz.pdp.botsale.repository.CategoryRepository;
import uz.pdp.botsale.repository.ProductRepository;
import uz.pdp.botsale.utils.CommonUtils;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    BrandRepository brandRepository;

    public ApiResponse saveOrEdit(ReqProduct reqProduct) {
        ApiResponse response = new ApiResponse();
        try {
            response.setSuccess(true);
            response.setMessage("Saved");
            Product product = new Product();
            if (reqProduct.getName() != null && reqProduct.getDescription() != null && reqProduct.getCategoryId() != null) {
                if (reqProduct.getId() != null) {
                    product = productRepository.findById(reqProduct.getId()).orElseThrow(() -> new ResourceNotFoundException("Product", "Id", reqProduct.getId()));
                    response.setMessage("Edit");
                }
                product.setCategory(categoryRepository.findById(reqProduct.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category", "id", reqProduct.getCategoryId())));
                product.setBrand(brandRepository.findById(reqProduct.getBrandId()).orElseThrow(() -> new ResourceNotFoundException("Brand", "id", reqProduct.getBrandId())));
                product.setName(reqProduct.getName());
                product.setBarCode(reqProduct.getBarCode());
                productRepository.save(product);
            }
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error");
        }
        return response;
    }

    public ResPageable pageable(Integer page, Integer size, String search) {
        Page<Product> productPage = productRepository.findAll(CommonUtils.getPageableById(page, size));
        if (!search.equals("all")) {
            productPage = productRepository.findAllByNameContainingIgnoreCase(search, CommonUtils.getPageableById(page, size));
        }
        return new ResPageable(productPage.getContent(), productPage.getTotalElements(), page);
    }

    public ApiResponse changeActive(Long id, boolean active) {
        try {
            Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
            product.setActive(active);
            productRepository.save(product);
            return new ApiResponse(active ? "Activated" : "Blocked", true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }

    public ApiResponse removeProduct(Long id) {
        try {
            productRepository.deleteById(id);
            return new ApiResponse("Deleted", true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }
}
