package com.controller;

import com.daily.report.excel.edit.controller.ExcelEditController;
import com.daily.report.excel.edit.dto.ExcelEditDto;
import com.daily.report.excel.edit.dto.ExcelEditResponseDto;
import com.daily.report.excel.edit.service.report.ExcelReportBulkMail;
import com.daily.report.excel.edit.service.report.ExcelReportKixx;
import com.daily.report.excel.edit.service.report.ExcelReportSurvey;
import com.daily.report.excel.edit.service.report.ExcelReportWebShell;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;

@DisplayName("Excel Edit Rest Api 호출")
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@WebMvcTest(ExcelEditController.class)
@Slf4j
class ExcelEditApplicationTests {

    MockMvc mvc;

    @MockBean
    ExcelReportBulkMail excelReportBulkMail;
    ExcelReportKixx excelReportKixx;
    ExcelReportSurvey excelReportSurvey;
    ExcelReportWebShell excelReportWebShell;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setup(WebApplicationContext webApplicationContext,
               RestDocumentationContextProvider restDocumentationContextProvider) {
        this.mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentationContextProvider))
                .build();
    }

    private String getJsonStringByDto(Object dto) {
        try {
            return objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            log.error("Json String Error : {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Report Excel 대량메일 테스트 - PUT /report/excel/edit/bulk-mail")
    void reportBulkMailTest() throws Exception {
        ExcelEditDto excelEditDto = ExcelEditDto.builder()
                .cpuUseAmt(10.1)
                .memUseAmt(77.2)
                .cdrvUseAmt(33)
                .cdrvDiskAmt(22)
                .ddrvDiskAmt(22.5)
                .logAmt(new double[222])
                .dataAmt(new double[123])
                .build();

        String updateMsg = "Success";

        given(excelReportBulkMail.dailyReportBulkMailExelEdit(any(ExcelEditDto.class)))
                .willReturn(ExcelEditResponseDto.of(updateMsg,excelEditDto));

        final ResultActions actions = mvc.perform(put("/report/excel/edit/bulk-mail")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(getJsonStringByDto(excelEditDto))
                );

    }

    @Test
    @DisplayName("Report Excel KIXX 테스트 - PUT /report/excel/edit/kiix")
    void reportKixxTest() {
        ExcelEditDto excelEditDto = ExcelEditDto.builder()
                .cpuUseAmt(10.1)
                .memUseAmt(77.2)
                .cdrvUseAmt(33)
                .cdrvDiskAmt(22)
                .ddrvDiskAmt(22.5)
                .logAmt(new double[222])
                .dataAmt(new double[123])
                .build();
    }

    @Test
    @DisplayName("Report Excel 설문조사 테스트 - PUT /report/excel/edit/survey")
    void reportSurveyTest() {
        ExcelEditDto excelEditDto = ExcelEditDto.builder()
                .cpuUseAmt(10.1)
                .memUseAmt(77.2)
                .cdrvUseAmt(33)
                .cdrvDiskAmt(22)
                .ddrvDiskAmt(22.5)
                .logAmt(new double[222])
                .dataAmt(new double[123])
                .build();
    }

    @Test
    @DisplayName("Report Excel Web Shell 테스트 - PUT /report/excel/edit/web-shell")
    void reportWebShellTest() {
        ExcelEditDto excelEditDto = ExcelEditDto.builder()
                .cpuUseAmt(10.1)
                .memUseAmt(77.2)
                .cdrvUseAmt(33)
                .cdrvDiskAmt(22)
                .ddrvDiskAmt(22.5)
                .logAmt(new double[222])
                .dataAmt(new double[123])
                .build();
    }

    @Test
    void contextLoads() {
    }

}
