package uz.pdp.botsale.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.botsale.service.BrandService;

@RestController
@RequestMapping("/api/brand")
public class BrandController {
    @Autowired
    BrandService brandService;
}
