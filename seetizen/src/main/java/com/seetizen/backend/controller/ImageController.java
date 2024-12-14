package com.seetizen.backend.controller;

import com.drew.imaging.ImageProcessingException;
import com.seetizen.backend.dto.ApiResponse;
import com.seetizen.backend.dto.ImageUploadResponse;
import com.seetizen.backend.service.ImageService;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import javax.imageio.ImageIO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;

    @PostMapping
    public ResponseEntity<ApiResponse<ImageUploadResponse>> getAddress(@RequestParam("image") MultipartFile image)
            throws ImageProcessingException, IOException {
        return ResponseEntity.ok(imageService.getAddressByImage(image));
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImageById(@PathVariable Long id) throws NoSuchFieldException, IOException {
        // 이미지 데이터 가져오기(byte)
        byte[] imageData = imageService.getImageById(id);

        // 이미지 형식을 자동으로 감지하기 위해 ByteArrayInputStream을 사용
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageData);
        BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);

        // 이미지 형식을 자동으로 감지
        String mimeType = getMimeType(bufferedImage);

        // HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(mimeType));  // 자동 감지된 MIME 타입 설정

        // Content-Disposition을 'inline'으로 설정하여 브라우저에서 바로 표시
        headers.setContentDispositionFormData("inline", "image." + mimeType.split("/")[1]);

        // 이미지 데이터를 그대로 반환
        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
    }

    private String getMimeType(BufferedImage bufferedImage) {
        if (bufferedImage == null) {
            return "application/octet-stream";  // 이미지가 아니면 기본 MIME 타입 반환
        }

        // ImageIO를 사용하여 형식 감지
        if (ImageIO.getImageReadersByFormatName("jpg").hasNext()) {
            return "image/jpeg";
        } else if (ImageIO.getImageReadersByFormatName("png").hasNext()) {
            return "image/png";
        } else if (ImageIO.getImageReadersByFormatName("gif").hasNext()) {
            return "image/gif";
        } else if (ImageIO.getImageReadersByFormatName("bmp").hasNext()) {
            return "image/bmp";
        } else if (ImageIO.getImageReadersByFormatName("webp").hasNext()) {
            return "image/webp";
        } else if (ImageIO.getImageReadersByFormatName("tiff").hasNext()) {
            return "image/tiff";
        } else if (ImageIO.getImageReadersByFormatName("svg").hasNext()) {
            return "image/svg+xml";  // SVG 형식 처리 (벡터 형식)
        } else {
            return "application/octet-stream";  // 지원되지 않는 형식일 경우 기본 MIME 타입
        }
    }
}
// 알림, 동시성, 인기도, 스케줄링-> 배치, SSE 알림, 미나이오