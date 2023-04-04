package com.example.sample.employee.controller;

import com.example.sample.common.BaseTest;
import com.example.sample.employee.command.dto.SalaryUpdateDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class EmployeeUpdateControllerTest extends BaseTest {

    @Test
    @DisplayName("특정 부서 급여 인상 및 사원 급여 업데이트")
    void update() throws Exception {
        //given
        final String url = "/employee/department/30";

        //when-then
        mockMvc.perform(put(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new SalaryUpdateDto(1f)))
                )
                .andDo(print())
                .andExpect(status().isNoContent())
                .andDo(
                        document(
                                "update-employee-salary",
                                requestFields(
                                        fieldWithPath("percent").description("인상비율")
                                )
                        )
                )
        ;
    }

}