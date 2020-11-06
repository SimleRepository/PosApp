package uz.pdp.botsale.payload;

import lombok.Data;
import uz.pdp.botsale.entity.Purchase;
import uz.pdp.botsale.entity.User;

import java.util.List;

@Data
public class ReqMarket {
    private Integer id;
    private String name;
    private String address;
    private Double lan;
    private Double lat;
    private List<Purchase> purchaseList;
    private List<User> users;
    private Integer cashId;
}
