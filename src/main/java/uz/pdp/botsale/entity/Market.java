package uz.pdp.botsale.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Market {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
