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
public class Cash {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Double startBalance;
    private boolean status;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Sell> sellList;
}
