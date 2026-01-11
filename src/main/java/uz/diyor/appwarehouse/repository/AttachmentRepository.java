package uz.diyor.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.diyor.appwarehouse.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment,Integer> {


}
