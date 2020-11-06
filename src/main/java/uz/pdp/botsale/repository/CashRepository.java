package uz.pdp.botsale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.botsale.entity.Cash;

public interface CashRepository extends JpaRepository<Cash, Integer> {
}
