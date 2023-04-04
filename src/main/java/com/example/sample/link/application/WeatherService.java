package com.example.sample.link.application;

import com.example.sample.common.exception.LinkException;
import com.example.sample.link.dto.WeatherBaseDto;
import com.example.sample.link.dto.WeatherQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class WeatherService {

    private final RestTemplate restTemplate;

    private static final String URL = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst";

    private static final String KEY = "7ybMtm52Wvh5bOH8PhzGTk%2Bramhq1PsDZgL5B0dstTb%2FcqiNGoCYylggaTrr1iWyDjCj5K5ux6Gr1eYIwmol9g%3D%3D";

    // 초단기실황 조회
    // 하루 전까지 데이터 조회 가능, 매시각 40분 후 호출 해야한다.
    // 15시데이터를 호출할라면 15시 40분에 호출해야한다.
    public Object find(WeatherQueryDto weatherQueryDto) {
        try {
            final String urlBuilder = URL + "?" + URLEncoder.encode("serviceKey", StandardCharsets.UTF_8) + "=" + KEY +
                    "&" + URLEncoder.encode("pageNo", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("1", StandardCharsets.UTF_8) +
                    "&" + URLEncoder.encode("numOfRows", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("1000", StandardCharsets.UTF_8) +
                    "&" + URLEncoder.encode("dataType", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("JSON", StandardCharsets.UTF_8) +
                    "&" + URLEncoder.encode("base_date", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(weatherQueryDto.getSearchDate(), StandardCharsets.UTF_8) +
                    "&" + URLEncoder.encode("base_time", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(weatherQueryDto.getSearchTime(), StandardCharsets.UTF_8) +
                    "&" + URLEncoder.encode("nx", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("55", StandardCharsets.UTF_8) +
                    "&" + URLEncoder.encode("ny", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("127", StandardCharsets.UTF_8);

            final ResponseEntity<WeatherBaseDto> forEntity = restTemplate.getForEntity(new URI(urlBuilder), WeatherBaseDto.class);

            final WeatherBaseDto weatherBaseDto = Objects.requireNonNull(forEntity.getBody());

            if (weatherBaseDto.isError()) {
                throw new LinkException("resultCode: " + weatherBaseDto.getResponse().getHeader().getResultCode() + ", resultMsg: " + weatherBaseDto.getResponse().getHeader().getResultMsg());
            }

            return weatherBaseDto.getResponse().getBody().getItems().getItem();
        } catch (Exception e) {
            throw new LinkException(e.getMessage());
        }
    }

}
