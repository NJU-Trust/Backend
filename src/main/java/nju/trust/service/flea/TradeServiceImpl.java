package nju.trust.service.flea;

import nju.trust.dao.flea.TradeRepository;
import nju.trust.dao.flea.TradeSpecification;
import nju.trust.dao.user.UserRepository;
import nju.trust.entity.flea.Trade;
import nju.trust.exception.ResourceNotFoundException;
import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.flea.TradeFilterRequest;
import nju.trust.payloads.flea.TradeInfo;
import nju.trust.payloads.flea.TradeInfoPage;
import nju.trust.payloads.flea.UploadGoodsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/10/20
 * @Todo:
 */
@Service
public class TradeServiceImpl implements TradeService {
    @Autowired
    TradeRepository tradeRepository;

    @Autowired
    UserRepository userRepository;
    public TradeServiceImpl() {
    }

    @Override
    public ApiResponse uploadGoods(UploadGoodsRequest uploadGoodsRequest, String username) {
        Trade trade = new Trade();
        trade.setContact(uploadGoodsRequest.getContact());
        trade.setGoodsName(uploadGoodsRequest.getName());
        trade.setSelling(true);
        trade.setFromUsername(username);
        trade.setCreateDate(LocalDate.now());
        trade.setGoodsDesc(uploadGoodsRequest.getDesc());
        trade.setGoodsType(uploadGoodsRequest.getType());
        trade.setGoodsPrice(uploadGoodsRequest.getPrice());
        trade.setGoodsPic(uploadGoodsRequest.getPicPath());
        tradeRepository.save(trade);
        return new ApiResponse(true, "发布成功");
    }

    @Override
    public TradeInfoPage filterTrade(TradeFilterRequest tradeFilterRequest) {
        Specification<Trade> specification = new TradeSpecification(tradeFilterRequest);
        Pageable pageable = PageRequest.of(tradeFilterRequest.getPage(), tradeFilterRequest.getSize(),new Sort("ASC".equals(tradeFilterRequest.getSort()) ?Sort.Direction.ASC:Sort.Direction.DESC, tradeFilterRequest.getProperties()));
        Page<Trade> trades = tradeRepository.findAll(specification, pageable);
        return new TradeInfoPage(trades.getTotalPages(), trades.stream().map(TradeInfo::new).collect(Collectors.toList()));
    }

    @Override
    public ApiResponse rating(Long id, int rating) {
        Trade trade = tradeRepository.findById(id).get();
        trade.setRating(rating);
        trade.setRating(true);
        tradeRepository.save(trade);
        return new ApiResponse(true, "评价成功");
    }

    @Override
    public ApiResponse finishTrade(Long id, String toUsername) {
        Trade trade = tradeRepository.findById(id).get();
        userRepository.findByUsername(toUsername).orElseThrow(
                () -> new ResourceNotFoundException("username not found")
        );

        trade.setToUsername(toUsername);
        trade.setSelling(false);
        tradeRepository.save(trade);
        return new ApiResponse(true, "交易成功");
    }


}
