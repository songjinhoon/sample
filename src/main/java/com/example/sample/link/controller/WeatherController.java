package com.example.sample.link.controller;

import com.example.sample.link.application.WeatherService;
import com.example.sample.link.dto.WeatherQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/weather")
@RestController
public class WeatherController {

    private final WeatherService weatherService;

    private final WeatherValidator weatherValidator;


    @GetMapping("/short-term-forecast")
    public ResponseEntity<?> find(@RequestBody @Valid WeatherQueryDto weatherQueryDto, Errors errors) {
        if (weatherValidator.validate(weatherQueryDto, errors)) {
            return ResponseEntity.badRequest().body(errors);
        }
        return ResponseEntity.ok().body(weatherService.find(weatherQueryDto));
    }

}
