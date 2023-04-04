package com.example.sample.employee.controller;

import com.example.sample.common.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class EmployeeFindControllerTest extends BaseTest {

    @Test
    @DisplayName("직원 조회")
    void find() throws Exception {
        //given
        final String url = "/employee/101";

        //when-then
        mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(
                        document(
                                "find-employee"
                        )
                )
        ;
    }

    @Test
    @DisplayName("직원 쿼리")
    void query() throws Exception {
        //given
        final String url = "/employee";
        final MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("page", "1");
        multiValueMap.add("size", "5");
        multiValueMap.add("sort", "employeeId");
        multiValueMap.add("sort", "hireDate");
        multiValueMap.add("firstName", null);
        multiValueMap.add("lastName", null);
        multiValueMap.add("email", "A");
        multiValueMap.add("phoneNumber", "1");
        multiValueMap.add("hireDate", null);

        //when-then
        mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .params(multiValueMap)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(
                        document(
                                "query-employee",
                                requestParameters(
                                        parameterWithName("page").description("페이지번호"),
                                        parameterWithName("size").description("페이지크기"),
                                        parameterWithName("sort").description("정렬"),
                                        parameterWithName("firstName").description("성"),
                                        parameterWithName("lastName").description("이름"),
                                        parameterWithName("email").description("이메일"),
                                        parameterWithName("phoneNumber").description("핸드폰번호"),
                                        parameterWithName("hireDate").description("고용일")
                                )
                        )
                )
        ;
    }

    //
    @Test
    @DisplayName("직원/부서 조회")
    void findDepartment() throws Exception {
        //given
        final String url = "/employee/101/department";

        //when-then
        mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(
                        document("find-employee-with-department")
                )
        ;
    }

    @Test
    @DisplayName("직원 조회 -> notfound ")
    void findNotFound() throws Exception {
        //given
        final String url = "/employee/1";

        //when-then
        mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNotFound())
        ;
    }

    @Test
    @DisplayName("직원/부서 조회 -> notfound")
    void findDepartmentNotFound() throws Exception {
        //given
        final String url = "/employee/178/department";

        //when-then
        mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNotFound())
        ;
    }

}