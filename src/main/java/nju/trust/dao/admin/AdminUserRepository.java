package nju.trust.dao.admin;

import nju.trust.entity.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author: 许杨
 * @Description: 管理员查询用户信息时需要的接口
 * @Date: 2018/8/29
 */
public interface AdminUserRepository extends CrudRepository<User, String> {
    /**
     * 得到所有用户名
     * @return
     */
    @Query(value = "select username from User u")
    List<String> getAllName();

    /**
     * 对用户名进行模糊查询
     * @param keyword 关键字
     * @return 符合的用户名
     */
    @Query(value = "select username from User u where u.username like %?1%")
    List<String> getNamesByKeyword(String keyword);
}
