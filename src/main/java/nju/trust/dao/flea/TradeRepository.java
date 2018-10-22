package nju.trust.dao.flea;

import nju.trust.entity.flea.Trade;
import nju.trust.entity.user.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/10/20
 * @Todo:
 */
public interface TradeRepository extends CrudRepository<Trade, String>, JpaSpecificationExecutor<Trade> {
    Optional<Trade> findById(Long id);
}
