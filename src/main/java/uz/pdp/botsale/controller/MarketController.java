package uz.pdp.botsale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.botsale.payload.ApiResponse;
import uz.pdp.botsale.payload.ReqMarket;
import uz.pdp.botsale.service.MarketService;

@RestController
@RequestMapping("/api/market")
public class MarketController {

    @Autowired
    MarketService marketService;

    @PostMapping
    public HttpEntity<?> saveOrEdit(@RequestBody ReqMarket reqMarket) {
        ApiResponse response = marketService.saveOrEdit(reqMarket);
        return ResponseEntity.status(response.isSuccess() ? response.getMessage().equals("Saved") ? HttpStatus.CREATED : HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(response);
    }
}
