package nju.trust.dao.admin;

import nju.trust.entity.user.UnstructuredData;
import nju.trust.entity.user.UnstructuredDataType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2018/9/8
 */
public interface UnstructuredDataRepository extends CrudRepository<UnstructuredData, Long> {
    UnstructuredData findFirstByUserUsernameAndDataType(String username, UnstructuredDataType dataType);

    boolean existsUnstructuredDataByUserUsername(String username);

    List<UnstructuredData> findDistinctByDataType(UnstructuredDataType type);

    List<UnstructuredData> findDistinctByUserUsername(String username);
}
