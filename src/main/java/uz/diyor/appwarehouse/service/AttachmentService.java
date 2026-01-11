package uz.diyor.appwarehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.diyor.appwarehouse.dto.Result;
import uz.diyor.appwarehouse.entity.Attachment;
import uz.diyor.appwarehouse.entity.AttachmentContent;
import uz.diyor.appwarehouse.repository.AttachmentContentRepository;
import uz.diyor.appwarehouse.repository.AttachmentRepository;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AttachmentService {
    
    final AttachmentRepository attachmentRepository;
    final AttachmentContentRepository attachmentContentRepository;

    public Result uploadFile(MultipartFile file) throws IOException {

        if (file == null || file.isEmpty()) {
            return new Result("File yo‘q", false);
        }

        Attachment attachment = new Attachment();
        attachment.setName(file.getOriginalFilename());
        attachment.setSize(file.getSize());
        attachment.setContentType(file.getContentType());
        Attachment saved = attachmentRepository.save(attachment);

        AttachmentContent content = new AttachmentContent();
        content.setAttachment(saved);
        content.setBytes(file.getBytes());
        attachmentContentRepository.save(content);

        return new Result("Uploaded", true, saved.getId());
    }

    public HttpEntity<byte[]> download(Integer id) {

        Attachment attachment = attachmentRepository.findById(id).orElse(null);
        if (attachment == null) return null;

        AttachmentContent content =
                attachmentContentRepository.findByAttachmentId(id).orElse(null);
        if (content == null) return null;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(attachment.getContentType()));
        headers.setContentDispositionFormData("attachment", attachment.getName());

        return new HttpEntity<>(content.getBytes(), headers);
    }

}

