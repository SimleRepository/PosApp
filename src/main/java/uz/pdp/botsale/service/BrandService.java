package uz.pdp.botsale.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.botsale.repository.BrandRepository;

@Service
public class BrandService {

    @Autowired
    BrandRepository brandRepository;

}
