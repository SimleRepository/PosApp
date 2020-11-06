package uz.pdp.botsale.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.botsale.entity.template.AbsNameEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Stock extends AbsNameEntity {
    private String name;
    private Double percent;
    private Timestamp startData;
    private Timestamp endData;
    @OneToMany(fetch = FetchType.LAZY)
    private List<StockDetails> stockDetailsList;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Market> marketList;
    private boolean active = true;
}
