package uz.pdp.botsale.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.botsale.entity.template.AbsNameEntity;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PayType extends AbsNameEntity {
    private String cashMoney;//naxt pull
    private String name;
    private String cardNumber;
    private String cardCompany;

    public PayType(String cashMoney) {
        this.cashMoney = cashMoney;
    }

    public PayType(String name, String cardNumber, String cardCompany) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.cardCompany = cardCompany;
    }
}
