package uz.pdp.botsale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.botsale.entity.Market;

public interface MarketRepository extends JpaRepository<Market, Integer> {

}
