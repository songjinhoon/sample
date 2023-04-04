package com.example.sample.department.ui;

import com.example.sample.common.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DepartmentFindControllerTest extends BaseTest {

    @Test
    @DisplayName("부서 조회")
    void find() throws Exception {
        //given
        final String url = "/department/10";

        //when-then
        mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(
                        document("find-department")
                )
        ;
    }

    @Test
    @DisplayName("부서와 매니저 정보 조회")
    void findManager() throws Exception {
        //given
        final String url = "/department/10/manager";

        //when-then
        mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(
                        document("find-department-with-manager")
                )
        ;
    }

    @Test
    @DisplayName("부서목록 조회")
    void findAll() throws Exception {
        //given
        final String url = "/department";

        //when-then
        mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(
                        document("find-department-all")
                )
        ;
    }

    @Test
    @DisplayName("부서 조회 -> not found")
    void findNotFound() throws Exception {
        //given
        final String url = "/department/1";

        //when-then
        mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNotFound())
        ;
    }

    @Test
    @DisplayName("부서와 매니저 정보 조회 -> not found")
    void findManagerNotFound() throws Exception {
        //given
        final String url = "/department/120/manager";

        //when-then
        mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNotFound())
        ;
    }

}