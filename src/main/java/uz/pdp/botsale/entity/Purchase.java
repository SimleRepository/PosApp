package uz.pdp.botsale.entity;

import uz.pdp.botsale.entity.template.AbsNameEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Purchase extends AbsNameEntity {
    @OneToMany(fetch = FetchType.LAZY)
    private List<PurchaseElements> elementsList;
    @OneToOne(fetch = FetchType.LAZY)
    private Company companyId;
    private Double Total;
}
