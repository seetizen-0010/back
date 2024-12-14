package com.seetizen.backend.exception;

import com.drew.imaging.ImageProcessingException;
import com.seetizen.backend.controller.ImageController;
import com.seetizen.backend.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = ImageController.class)
public class ImageExceptionHandler {
    // 이미지 처리도중 에러
    @ExceptionHandler(ImageProcessingException.class)
    public ResponseEntity<ApiResponse<String>> handleImageProcessingException(ImageProcessingException ex) {
        return new ResponseEntity<>(ApiResponse.fail(ex.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
