package uz.pdp.botsale.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.botsale.entity.enums.StatusSell;
import uz.pdp.botsale.entity.template.AbsNameEntity;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Sell extends AbsNameEntity {
    @OneToMany(fetch = FetchType.LAZY)
    private List<SellDetails> sellDetails;
    @OneToOne(fetch = FetchType.LAZY)
    private Client clientId;
    @OneToOne(fetch = FetchType.LAZY)
    private Market marketId;
    @OneToOne(fetch = FetchType.LAZY)
    private Cash cashId;//Kassa
    @OneToOne(fetch = FetchType.LAZY)
    private PayType payType;
    @Enumerated(value = EnumType.STRING)
    private StatusSell statusSell;
    private Double total;
}
