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
public class Product extends AbsNameEntity {
    @Column(unique = true)
    private String name;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category   ;

    @ManyToOne(fetch = FetchType.LAZY)
    private Brand brand;

    private String barCode;
    private boolean active = true;
}
