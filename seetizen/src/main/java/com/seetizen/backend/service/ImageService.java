package com.seetizen.backend.service;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.GpsDirectory;
import com.seetizen.backend.dto.ApiResponse;
import com.seetizen.backend.dto.ImageUploadResponse;
import com.seetizen.backend.dto.KakaoGeoResponse;
import com.seetizen.backend.entity.Image;
import com.seetizen.backend.repository.ImageRepository;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    private static final String PATH = "/Users/leejuno/git/seetizen2/back/seetizen/src/main/resources/images/";
    private static final String KAKAO_BASE_URL = "https://dapi.kakao.com/v2/local/geo/coord2address.json";
    private static final String KAKAO_API_KEY = "527c24de529e525e614182be5eba7c32";

    public ApiResponse<ImageUploadResponse> getAddressByImage(MultipartFile image)
            throws ImageProcessingException, IOException {
        // 위도 경도 주소 이미지경로
        ImageUploadResponse imageUploadResponse = getAddressByMetadata(image);

        String savedImagePath = imageUploadResponse.getImagePath();
        Image savedLocalImage = new Image(savedImagePath);
        // 이미지 경로 DB 저장 후 id 값 추출
        Image savedDBImage = imageRepository.save(savedLocalImage);
        imageUploadResponse.setId(savedDBImage.getId());

        return ApiResponse.success(imageUploadResponse, "주소를 성공적으로 가져왔습니다.");
    }

    private ImageUploadResponse getAddressByMetadata(MultipartFile image) throws ImageProcessingException, IOException {
        // 이미지 위치 지정 및 File 로 변환(저장)
        File destination = new File(PATH + image.getOriginalFilename());
        image.transferTo(destination); // 파일을 local에 저장
        String imagePath = destination.getAbsolutePath();
        log.info("파일경로: " + imagePath);
        // 메타데이터 추출, 없으면 예외
        Metadata metadata = getMetadataByImage(imagePath);
        // 메타에서 위치 추출
        GpsDirectory gpsDirectory = metadata.getFirstDirectoryOfType(GpsDirectory.class);
        // 위치 없으면 예외
        if (!hasGpsInformation(gpsDirectory)) {
            throw new ImageProcessingException("GPS 정보가 존재하지 않습니다.");
        }

        BigDecimal longitude = BigDecimal.valueOf(gpsDirectory.getGeoLocation().getLongitude()); // 경도, x
        BigDecimal latitude = BigDecimal.valueOf(gpsDirectory.getGeoLocation().getLatitude());  // 위도, y

        String addressByKakao = getAddressByKakao(latitude, longitude);

        return new ImageUploadResponse(latitude, longitude, addressByKakao, imagePath);
    }

    private String getAddressByKakao(BigDecimal latitude, BigDecimal longitude) {
        RestClient restClient = RestClient.create();

        KakaoGeoResponse apiResult = restClient.get()
                .uri(KAKAO_BASE_URL + "?x=" + longitude + "&y=" + latitude + "&input_coord=WGS84")
                .header("Authorization", "KakaoAK " + KAKAO_API_KEY)
                .accept(APPLICATION_JSON)
                .retrieve()
                .body(KakaoGeoResponse.class);

        return apiResult.getDocuments().get(0).getAddress().getAddress_name();
    }

    private Metadata getMetadataByImage(String imagePath) throws ImageProcessingException {
        Metadata metadata;
        try {
            metadata = ImageMetadataReader.readMetadata(new File(imagePath));
        } catch (Exception e) {
            throw new ImageProcessingException("메타데이터가 존재하지 않습니다.");
        }

        return metadata;
    }

    private boolean hasGpsInformation(GpsDirectory gpsDirectory) {
        return gpsDirectory.containsTag(GpsDirectory.TAG_LATITUDE) && gpsDirectory.containsTag(
                GpsDirectory.TAG_LONGITUDE);
    }
}
