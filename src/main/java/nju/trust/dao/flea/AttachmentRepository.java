package nju.trust.dao.flea;

import nju.trust.entity.flea.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/9/1
 * @Todo:
 */
public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
    @Modifying
    @Query(value="insert into attachment(attachment_name, attachment_url,goods_id) values (?1,?2,?3) ", nativeQuery = true)
    void saveAttachmentByParams (String attachmentName, String attachmentUrl, Integer goodsId);
}