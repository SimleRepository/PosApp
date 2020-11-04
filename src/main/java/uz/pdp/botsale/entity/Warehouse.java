package uz.pdp.botsale.entity;

import uz.pdp.botsale.entity.template.AbsNameEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Warehouse extends AbsNameEntity {
    private String name;
    private String address;
    private Double lan;
    private Double lat;
    private User users;
    @OneToMany(fetch = FetchType.LAZY)
    private List<PurchaseElements> purchaseElementsList;
    private UUID brandID;
    private boolean active=true;
}
