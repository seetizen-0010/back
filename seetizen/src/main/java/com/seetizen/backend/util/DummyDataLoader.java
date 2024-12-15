package com.seetizen.backend.util;

import com.seetizen.backend.entity.Post;
import com.seetizen.backend.entity.Image;
import com.seetizen.backend.service.PostRepository;
import com.seetizen.backend.repository.ImageRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DummyDataLoader implements CommandLineRunner {

    private final PostRepository postRepository;
    private final ImageRepository imageRepository;

    @Override
    public void run(String... args) throws Exception {

        if (postRepository.count() == 0) {
            // Create dummy images
            Image image1 = imageRepository.save(Image.builder().path("/Users/kimtaekcheon/IdeaProjects/seetizen-back/seetizen/src/main/resources/images/leaf.webp").build());
            Image image2 = imageRepository.save(Image.builder().path("/Users/kimtaekcheon/IdeaProjects/seetizen-back/seetizen/src/main/resources/images/helmet.webp").build());
            Image image3 = imageRepository.save(Image.builder().path("/Users/kimtaekcheon/IdeaProjects/seetizen-back/seetizen/src/main/resources/images/speed.jpg").build());
            Image image4 = imageRepository.save(Image.builder().path("/Users/kimtaekcheon/IdeaProjects/seetizen-back/seetizen/src/main/resources/images/traffic_light.webp").build());
            Image image5 = imageRepository.save(Image.builder().path("/Users/kimtaekcheon/IdeaProjects/seetizen-back/seetizen/src/main/resources/images/tire.jpg").build());
            Image image6 = imageRepository.save(Image.builder().path("/Users/kimtaekcheon/IdeaProjects/seetizen-back/seetizen/src/main/resources/images/manhole.jpg").build());
            Image image7 = imageRepository.save(Image.builder().path("/Users/kimtaekcheon/IdeaProjects/seetizen-back/seetizen/src/main/resources/images/bigsago.jpg").build());
            Image image8 = imageRepository.save(Image.builder().path("/Users/kimtaekcheon/IdeaProjects/seetizen-back/seetizen/src/main/resources/images/fire_apt.jpg").build());
            Image image9 = imageRepository.save(Image.builder().path("/Users/kimtaekcheon/IdeaProjects/seetizen-back/seetizen/src/main/resources/images/pannel.jpg").build());
            Image image10 = imageRepository.save(Image.builder().path("/Users/kimtaekcheon/IdeaProjects/seetizen-back/seetizen/src/main/resources/images/snowdown.jpg").build());

            List<Post> dummyPosts = List.of(
                    Post.builder()
                            .content("여기 대형사고 났어요 ㅠ")
                            .latitude(37.35599)
                            .longitude(127.07144)
                            .likes(50)
                            .dislikes(7)
                            .tag("교통 안전")
                            .address("성남시 분당구 야탑동")
                            .image(image7)
                            .build(),
                    Post.builder()
                            .content("어? 우리집?")
                            .latitude(37.35206)
                            .longitude(127.07166)
                            .likes(45)
                            .dislikes(7)
                            .tag("화재 안전,재난 안전")
                            .address("성남시 분당구 야탑동")
                            .image(image8)
                            .build(),

                    Post.builder()
                            .content("여기 간판떨어졌어요 ㅠㅠ")
                            .latitude(37.35590)
                            .longitude(127.06916)
                            .likes(21)
                            .dislikes(9)
                            .tag("생활 안전,교통 안전")
                            .address("성남시 분당구 야탑동")
                            .image(image9)
                            .build(),
                    Post.builder()
                            .content("눈와서 지하주차장 입구가 무너짐")
                            .latitude(37.34821)
                            .longitude(127.07439)
                            .likes(23)
                            .dislikes(1)
                            .tag("생활 안전,재난 안전")
                            .address("성남시 분당구 야탑동")
                            .image(image10)
                            .build(),
                    Post.builder()
                            .content("산책로에 낙엽이 많이 쌓여 미끄럼 사고 위험이 있습니다.")
                            .latitude(37.352)
                            .longitude(127.072)
                            .likes(30)
                            .dislikes(0)
                            .tag("생활 안전")
                            .address("성남시 분당구 정자동")
                            .image(image1)
                            .build(),
                    Post.builder()
                            .content("공사장 근처에서 헬멧을 착용하지 않은 근로자가 많습니다.")
                            .latitude(37.3515)
                            .longitude(127.0718)
                            .likes(22)
                            .dislikes(1)
                            .tag("공사중")
                            .address("성남시 분당구 정자동")
                            .image(image2)
                            .build(),
                    Post.builder()
                            .content("어린이집 앞에 과속 차량이 많아 매우 위험합니다.")
                            .latitude(37.3512)
                            .longitude(127.071)
                            .likes(60)
                            .dislikes(3)
                            .tag("생활 안전,교통 안전")
                            .address("성남시 분당구 정자동")
                            .image(image3)
                            .build(),
                    Post.builder()
                            .content("학교 앞 횡단보도에 신호등이 고장났습니다. 모두 조심하세요.")
                            .latitude(37.351)
                            .longitude(127.0712)
                            .likes(45)
                            .dislikes(2)
                            .tag("생활 안전,교통 안전")
                            .address("성남시 분당구 정자동")
                            .image(image4)
                            .build(),
                    Post.builder()
                            .content("고속도로 위에 타이어 잔해물이 있어 위험합니다.")
                            .latitude(37.3508)
                            .longitude(127.0715)
                            .likes(50)
                            .dislikes(5)
                            .tag("교통 안전,기타")
                            .address("성남시 분당구 정자동")
                            .image(image5)
                            .build(),
                    Post.builder()
                            .content("맨홀에서 물이 흘러넘쳐요 ㅠ")
                            .latitude(37.3519)
                            .longitude(127.0723)
                            .likes(30)
                            .dislikes(5)
                            .tag("생활 안전,교통 안전")
                            .address("성남시 분당구 야탑동")
                            .image(image6)
                            .build()

            );

            postRepository.saveAll(dummyPosts);
            System.out.println("Dummy data with images loaded successfully.");
        }
    }
}

