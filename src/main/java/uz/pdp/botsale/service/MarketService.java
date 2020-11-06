package uz.pdp.botsale.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uz.pdp.botsale.entity.Market;
import uz.pdp.botsale.entity.Purchase;
import uz.pdp.botsale.exception.ResourceNotFoundException;
import uz.pdp.botsale.payload.ApiResponse;
import uz.pdp.botsale.payload.ReqMarket;
import uz.pdp.botsale.payload.ResPageable;
import uz.pdp.botsale.repository.CashRepository;
import uz.pdp.botsale.repository.MarketRepository;
import uz.pdp.botsale.utils.CommonUtils;

@Service
public class MarketService {

    @Autowired
    MarketRepository marketRepository;

    @Autowired
    CashRepository cashRepository;

    public ApiResponse saveOrEdit(ReqMarket reqMarket) {
        ApiResponse response = new ApiResponse();
        try {
            response.setSuccess(true);
            response.setMessage("Saved");
            Market market = new Market();
            if (reqMarket.getId() != null) {
                market = marketRepository.findById(reqMarket.getId()).orElseThrow(() -> new ResourceNotFoundException("Market", "Id", reqMarket.getId()));
                response.setMessage("Edited");
            }
            market.setLan(reqMarket.getLan());
            market.setLat(reqMarket.getLat());
            market.setAddress(reqMarket.getAddress());
            market.setName(reqMarket.getName());
            market.setCashId(cashRepository.findById(reqMarket.getCashId()).orElseThrow(() -> new ResourceNotFoundException("Cash", "id", reqMarket.getCashId())));
            for (Purchase purchase : reqMarket.getPurchaseList()) {

            }
            marketRepository.save(market);
        } catch (Exception e) {
            response.setMessage("Error");
            response.setSuccess(false);
        }
        return response;
    }

    public ResPageable pageable(Integer page, Integer size, String search) {
        Page<Market> marketPage = marketRepository.findAll(CommonUtils.getPageableById(page, size));
        if (!search.equals("all")) {
            marketPage = marketRepository.findAllByNameContainingIgnoreCase(search, CommonUtils.getPageableById(page, size));
        }
        return new ResPageable(marketPage.getContent(), marketPage.getTotalElements(), page);
    }

    public ApiResponse changeActive(Integer id, boolean active) {
        try {
            Market market = marketRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Market", "Id", id));
            market.setActive(active);
            return new ApiResponse(active?"Activated":"Blocked",true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }

    public ApiResponse removeMarket(Integer id) {
        try {
            marketRepository.deleteById(id);
            return new ApiResponse("Deleted",true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }
}
