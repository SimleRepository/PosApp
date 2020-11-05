package uz.pdp.botsale.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.botsale.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByNameContainingIgnoreCase(String search, Pageable pageableById);
}
