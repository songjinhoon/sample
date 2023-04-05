package com.example.sample.link.controller;

import com.example.sample.link.dto.WeatherQueryDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class WeatherValidator {

    public boolean validate(WeatherQueryDto weatherQueryDto, Errors errors) {
        LocalDateTime now = LocalDateTime.now();

        if (weatherQueryDto.getBaseDate() != null) {
            if (weatherQueryDto.getBaseTime() == null) {
                errors.rejectValue("baseTime", "wrongValues", "baseDate 값이 존재하면 baseTime 값이 필요합니다.");
                return errors.hasErrors();
            }

            LocalDateTime check = LocalDateTime.of(weatherQueryDto.getBaseDate(), LocalTime.of(weatherQueryDto.getBaseTime(), 0));
            if (check.isBefore(now.minusDays(1))) {
                errors.rejectValue("baseTime", "wrongValues", "하루 전 시간 까지만 조회가 가능합니다.");
                return errors.hasErrors();
            }

            if (now.getHour() == weatherQueryDto.getBaseTime() && now.getMinute() < 40) {
                errors.rejectValue("baseTime", "wrongValues", "데이터는 매 시각 40분에 생성됩니다. 이전 시간을 조회해주세요.");
                return errors.hasErrors();
            }

            weatherQueryDto.updateSearchTime();

            return false;
        } else {
            if (weatherQueryDto.getBaseTime() != null) {
                errors.rejectValue("baseTime", "wrongValues", "baseDate 값이 없으면 baseTime 값이 없어야 합니다.");
            } else {
                if (now.getMinute() < 40) {
                    LocalDateTime searchDate = now.minusHours(1);
                    weatherQueryDto.setSearchDate(DateTimeFormatter.ofPattern("yyyyMMdd").format(searchDate));
                    weatherQueryDto.setSearchTime(DateTimeFormatter.ofPattern("HHmm").format(LocalTime.of(searchDate.getHour(), 0)));
                } else {
                    weatherQueryDto.setSearchDate(DateTimeFormatter.ofPattern("yyyyMMdd").format(now));
                    weatherQueryDto.setSearchTime(DateTimeFormatter.ofPattern("HHmm").format(LocalTime.of(now.getHour(), 0)));
                }
            }

            return errors.hasErrors();
        }
    }

}
