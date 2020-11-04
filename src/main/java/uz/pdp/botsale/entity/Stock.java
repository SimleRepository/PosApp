package uz.pdp.botsale.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.glassfish.grizzly.http.util.TimeStamp;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import uz.pdp.botsale.entity.template.AbsNameEntity;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Stock extends AbsNameEntity {
    private String name;
    private Double percent;
    private TimeStamp startData;
    private TimeStamp endData;
    @OneToMany(fetch = FetchType.LAZY)
    private List<StockDetails> stockDetailsList;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Market> marketList;
    private boolean active = true;
}
