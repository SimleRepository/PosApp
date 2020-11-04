package uz.pdp.botsale.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.botsale.entity.template.AbsNameEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MainCash extends AbsNameEntity {
    @OneToMany(fetch = FetchType.LAZY)
    private List<Purchase> purchaseId;
    @OneToOne(fetch = FetchType.LAZY)
    private Market marketId;
    private Double count;
    private Double mainCount;//opshi astatka soni
}
