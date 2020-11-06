package uz.pdp.botsale.payload;

import lombok.Data;

import java.util.UUID;

@Data
public class ReqBrand {
    private UUID id;
    private String name;
    private UUID BrandIcon;
}
