//package com.seetizen.backend.service;
//
//import static org.springframework.http.MediaType.APPLICATION_JSON;
//
//import com.drew.imaging.ImageMetadataReader;
//import com.drew.imaging.ImageProcessingException;
//import com.drew.metadata.Metadata;
//import com.drew.metadata.exif.GpsDirectory;
//import com.seetizen.backend.dto.ApiResponse;
//import com.seetizen.backend.dto.ImageUploadResponse;
//import java.io.File;
//import java.io.IOException;
//import java.math.BigDecimal;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestClient;
//import org.springframework.web.multipart.MultipartFile;
//
//@Service
//@Slf4j
//public class ImageService {
//
//    private static final String PATH = "/Users/leejuno/git/seetizen/backend/src/main/resources/images";
//
//    public ApiResponse<String> getAddress(MultipartFile image) {
//        ;
//        return ApiResponse.success(readMeta(image),"성공");
//    }
//
//    private ImageUploadResponse readMeta(MultipartFile image) throws ImageProcessingException, IOException {
//        // 이미지 위치 지정 및 File 로 변환(저장)
//        File destination = new File(PATH + image.getOriginalFilename());
//        image.transferTo(destination); // 파일을 local에 저장
//        String savedImage = destination.getAbsolutePath();
//        log.info("파일경로: " + savedImage);
//
//
//        Metadata metadata = ImageMetadataReader.readMetadata(new File(savedImage));
//        GpsDirectory gpsDirectory = metadata.getFirstDirectoryOfType(GpsDirectory.class);
//
//        if (!hasGpsInformation(gpsDirectory)) {
//
//        }
//        BigDecimal longitude = BigDecimal.valueOf(gpsDirectory.getGeoLocation().getLongitude()); // 경도, x
//        BigDecimal latitude = BigDecimal.valueOf(gpsDirectory.getGeoLocation().getLatitude());  // 위도, y
//
//        String address = getAddress(latitude, longitude);
//
//        return "위도 : " + latitude + ", 경도 : " + longitude + "\n"
//                + "주소: " + address;
//    }
//
//    private boolean hasGpsInformation(GpsDirectory gpsDirectory) {
//        return gpsDirectory.containsTag(GpsDirectory.TAG_LATITUDE) && gpsDirectory.containsTag(
//                GpsDirectory.TAG_LONGITUDE);
//    }
//
//    private String getAddress(double latitude, double longitude) {
//        RestClient restClient = RestClient.create();
//
//        KakaoGeoResponse apiResult = restClient.get()
//                .uri(KAKAO_BASE_URL + "?x=" + longitude + "&y=" + latitude + "&input_coord=WGS84")
//                .header("Authorization", "KakaoAK " + KAKAO_API_KEY)
//                .accept(APPLICATION_JSON)
//                .retrieve()
//                .body(KakaoGeoResponse.class);
//
//        return apiResult.getDocuments().get(0).getAddress().getAddress_name();
//    }
//}
