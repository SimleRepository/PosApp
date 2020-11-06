package uz.pdp.botsale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.botsale.payload.ApiResponse;
import uz.pdp.botsale.payload.ReqProduct;
import uz.pdp.botsale.service.ProductService;
import uz.pdp.botsale.utils.AppConstants;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public HttpEntity<?> saveOrEdit(@RequestBody ReqProduct reqProduct) {
        ApiResponse response = productService.saveOrEdit(reqProduct);
        return ResponseEntity.status(response.isSuccess() ? response.getMessage().equals("Saved") ? HttpStatus.CREATED : HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(response);
    }

    @GetMapping
    public HttpEntity<?> pageable(@RequestParam(name = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
                                  @RequestParam(name = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size,
                                  @RequestParam(name = "search", defaultValue = "all") String search) {
        return ResponseEntity.ok(productService.pageable(page, size, search));
    }

    @GetMapping("/{id}")
    public ApiResponse changeActive(@PathVariable Long id, @PathVariable boolean active) {
        return productService.changeActive(id, active);
    }

    @DeleteMapping("/{id}")
    public ApiResponse removeController(@PathVariable Long id) {
        return productService.removeProduct(id);
    }
}
