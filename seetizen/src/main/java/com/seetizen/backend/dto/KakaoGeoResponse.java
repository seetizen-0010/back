package com.seetizen.backend.dto;


import java.util.List;
import lombok.Getter;

@Getter
public class KakaoGeoResponse {
    private Meta meta;
    private List<Document> documents;

    public static class Meta {
        private int total_count;
    }

    @Getter
    public static class Document {
        private Address road_address;
        private Address address;

        @Getter
        public static class Address {
            private String address_name;
            private String region_1depth_name;
            private String region_2depth_name;
            private String region_3depth_name;
            private String mountain_yn;
            private String main_address_no;
            private String sub_address_no;
            private String zip_code;
        }
    }
}

/**
 * { "meta": { "total_count": 1 }, "documents": [ { "road_address": null, "address": { "address_name": "경기 용인시 처인구 모현읍
 * 왕산리 476-5", "region_1depth_name": "경기", "region_2depth_name": "용인시 처인구", "region_3depth_name": "모현읍 왕산리",
 * "mountain_yn": "N", "main_address_no": "476", "sub_address_no": "5", "zip_code": "" } } ] }
 */