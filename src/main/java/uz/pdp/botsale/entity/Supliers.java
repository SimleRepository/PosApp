package uz.pdp.botsale.entity;

import uz.pdp.botsale.entity.template.AbsNameEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Supliers extends AbsNameEntity {
    private String name;
    private String number;
    private String companyName;
    private String brand;
}
