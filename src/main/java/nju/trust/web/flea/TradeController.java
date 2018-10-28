package nju.trust.web.flea;

import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.flea.TradeFilterRequest;
import nju.trust.payloads.flea.TradeInfo;
import nju.trust.payloads.flea.TradeInfoPage;
import nju.trust.payloads.flea.UploadGoodsRequest;
import nju.trust.payloads.target.SmallTargetFilterRequest;
import nju.trust.service.flea.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/10/20
 * @Todo:
 */
@RestController
@RequestMapping("/flea")
public class TradeController {

    private TradeService tradeService;

    @Autowired
    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @PostMapping(value = "/newTrade")
    public ApiResponse createTrade(@RequestBody UploadGoodsRequest uploadGoodsRequest, Principal principal) {
        return tradeService.uploadGoods(uploadGoodsRequest, principal.getName());
    }

    @RequestMapping(value = "/getNew")
    public TradeInfoPage getNovelTrade(@Valid @RequestBody TradeFilterRequest filter) {
        return tradeService.filterTrade(filter);
    }


    @GetMapping(value = "/rating")
    public ApiResponse rating(Long id, int rating) {
        return tradeService.rating(id, rating);
    }

    @GetMapping(value = "/finish")
    public ApiResponse finish(Long id, String toUsername) {
        System.out.println(id);
        return tradeService.finishTrade(id, toUsername);
    }



}
