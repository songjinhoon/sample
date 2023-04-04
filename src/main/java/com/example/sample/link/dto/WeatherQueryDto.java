package com.example.sample.link.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@Setter
@Getter
public class WeatherQueryDto {

    private LocalDate baseDate;

    private Integer baseTime; // 0 ~ 23

    @JsonIgnore
    private String searchDate;

    @JsonIgnore
    private String searchTime;

    public WeatherQueryDto(LocalDate baseDate, Integer baseTime) {
        this.baseDate = baseDate;
        this.baseTime = baseTime;
    }

    public LocalDateTime getLocalDateTime() {
        return LocalDateTime.of(this.baseDate, LocalTime.of(this.baseTime, 0));
    }

    public void updateSearchTime() {
        this.searchDate = DateTimeFormatter.ofPattern("yyyyMMdd").format(this.baseDate);
        this.searchTime = DateTimeFormatter.ofPattern("HHmm").format(LocalTime.of(this.baseTime, 0));
    }

}
