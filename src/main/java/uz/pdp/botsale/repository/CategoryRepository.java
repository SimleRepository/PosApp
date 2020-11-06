package uz.pdp.botsale.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.botsale.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    String findByName(String name);

    Page<Category> findAllByNameContainingIgnoreCase(String search, Pageable pageableById);
}
