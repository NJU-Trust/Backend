package nju.trust.service.flea;

import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.flea.TradeFilterRequest;
import nju.trust.payloads.flea.TradeInfo;
import nju.trust.payloads.flea.TradeInfoPage;
import nju.trust.payloads.flea.UploadGoodsRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/10/20
 * @Todo:
 */

@Service
public interface TradeService {
    ApiResponse uploadGoods(UploadGoodsRequest uploadGoodsRequest, String username);
    TradeInfoPage filterTrade(TradeFilterRequest tradeFilterRequest);
    ApiResponse rating(Long id, int rating);
    ApiResponse finishTrade(Long id, String toUsername);

}
