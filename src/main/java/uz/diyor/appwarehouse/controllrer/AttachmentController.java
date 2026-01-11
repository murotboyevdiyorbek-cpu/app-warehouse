package uz.diyor.appwarehouse.controllrer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.diyor.appwarehouse.dto.Result;
import uz.diyor.appwarehouse.service.AttachmentService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attachment")
public class AttachmentController {

    final AttachmentService attachmentService;

    @PostMapping(
            value = "/upload",
            consumes = "multipart/form-data"
    )
    public Result upload(@RequestParam("file") MultipartFile file) throws IOException {
        return attachmentService.uploadFile((MultipartFile) file);
    }


    @GetMapping("/download/{id}")
    public HttpEntity<byte[]> download(@PathVariable Integer id) {
        return attachmentService.download(id);
    }




}
