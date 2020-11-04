package uz.pdp.botsale.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.botsale.entity.template.AbsNameEntity;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Market extends AbsNameEntity {
    @Column(unique = true)
    private String name;
    private String address;
    private Double lan;
    private Double lat;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Purchase> purchaseList;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<User> users;
    @OneToOne(fetch = FetchType.LAZY)
    private Cash cashId;
    private boolean active = true;
}
