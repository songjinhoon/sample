package com.example.sample.link.controller;

import com.example.sample.common.BaseTest;
import com.example.sample.link.dto.WeatherQueryDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class WeatherControllerTest extends BaseTest {

    @Test
    @DisplayName("초단기실황조회")
    void find() throws Exception {
        //given
        final String url = "/weather/short-term-forecast";
        final LocalDateTime now = LocalDateTime.now().minusHours(1);
        final LocalDate localDate = LocalDate.of(now.getYear(), now.getMonth(), now.getDayOfMonth());
        final int baseTime = Integer.parseInt(DateTimeFormatter.ofPattern("HH").format(now));

        //when-then
        mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new WeatherQueryDto(localDate, baseTime)))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(
                        document(
                                "find-weather",
                                requestFields(
                                        fieldWithPath("baseDate").description("조회일").optional(),
                                        fieldWithPath("baseTime").description("조회시간").optional(),
                                        fieldWithPath("localDateTime").ignored()
                                )
                        )
                )
        ;
    }

}