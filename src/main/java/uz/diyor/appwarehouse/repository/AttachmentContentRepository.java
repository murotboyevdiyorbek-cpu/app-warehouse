package uz.diyor.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.diyor.appwarehouse.entity.AttachmentContent;

import java.util.Optional;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent,Integer> {

    Optional<AttachmentContent> findByAttachmentId(Integer attachmentId);


}
