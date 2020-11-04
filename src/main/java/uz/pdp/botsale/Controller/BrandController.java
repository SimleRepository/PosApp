package uz.pdp.botsale.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.botsale.payload.ApiResponse;
import uz.pdp.botsale.payload.ReqBrand;
import uz.pdp.botsale.service.BrandService;
import uz.pdp.botsale.service.ExcelService;
import uz.pdp.botsale.utils.AppConstants;

@RestController
@RequestMapping("/api/brand")
public class BrandController {

    @Autowired
    BrandService brandService;

    @Autowired
    ExcelService excelService;

    @PostMapping
    public HttpEntity<?> saveOrEdit(@RequestBody ReqBrand reqBrand) {
        ApiResponse response = brandService.saveOrEdit(reqBrand);
        return ResponseEntity.status(response.isSuccess() ? response.getMessage().equals("Saved") ? HttpStatus.CREATED : HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(response);
    }

    @GetMapping
    public HttpEntity<?> pageable(@RequestParam(name = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
                                  @RequestParam(name = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size,
                                  @RequestParam(name = "search", defaultValue = "all") String search) {
        return ResponseEntity.ok(brandService.pageable(page, size, search));
    }

    @GetMapping("/{id}")
    public ApiResponse changeActive(@PathVariable Long id, @RequestParam boolean active) {
        return brandService.changeActive(id, active);
    }

    @GetMapping("/excel")
    public HttpEntity<?> getExcel() {
        return excelService.getBrand();
    }

    @DeleteMapping("/{id}")
    public ApiResponse removeBrand(@PathVariable Long id) {
        return brandService.removeBrand(id);
    }
}
