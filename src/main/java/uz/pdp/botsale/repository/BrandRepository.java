package uz.pdp.botsale.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.botsale.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    String findBuName(String name);

    Page<Brand> findAllByNameContainingIgnoreCase(String search, Pageable pageable);
}
