package com.daily.report.excel.edit.controller;

import com.daily.report.excel.edit.dto.ExcelEditDto;
import com.daily.report.excel.edit.dto.ExcelEditResponseDto;
import com.daily.report.excel.edit.service.report.ExcelReportBulkMail;
import com.daily.report.excel.edit.service.report.ExcelReportKixx;
import com.daily.report.excel.edit.service.report.ExcelReportSurvey;
import com.daily.report.excel.edit.service.report.ExcelReportWebShell;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(RestDocumentationExtension.class)
@WebMvcTest(ExcelEditController.class)
class ExcelEditControllerTests {

    MockMvc mvc;

    @MockBean
    ExcelReportBulkMail excelReportBulkMail;
    @MockBean
    ExcelReportKixx excelReportKixx;
    @MockBean
    ExcelReportSurvey excelReportSurvey;
    @MockBean
    ExcelReportWebShell excelReportWebShell;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setup(WebApplicationContext webApplicationContext,
               RestDocumentationContextProvider restDocumentation) {
        this.mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    private String getJsonStringByDto(Object dto) {
        try {
            return objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Report Excel ???????????? ????????? - PUT /report/excel/edit/bulk-mail")
    void reportBulkMailTest() throws Exception {
        List<Double> dataList = List.of(11.0);
        List<Double> logList = List.of(10.0);

        //given
        ExcelEditDto excelEditDto = ExcelEditDto.builder()
                .cpuUseAmt(10.1)
                .memUseAmt(77.2)
                .cdrvUseAmt(12.3)
                .cdrvDiskAmt(23.4)
                .ddrvDiskAmt(111)
                .dataAmt(dataList)
                .logAmt(logList)
                .build();

        String msg = "Success";

        given(excelReportBulkMail.dailyReportBulkMailExelEdit(any(ExcelEditDto.class)))
                .willReturn(ExcelEditResponseDto.of(msg,excelEditDto));

        //when
        final ResultActions actions = mvc.perform(put("/report/excel/edit/bulk-mail")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(getJsonStringByDto(excelEditDto)))
                .andDo(document("put-dailyReportBulkMail",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("cpuUseAmt").type(JsonFieldType.NUMBER).description("CPU ?????????"),
                                fieldWithPath("memUseAmt").type(JsonFieldType.NUMBER).description("Memory ?????????"),
                                fieldWithPath("cdrvUseAmt").type(JsonFieldType.NUMBER).description("Disk(C) ?????????"),
                                fieldWithPath("cdrvDiskAmt").type(JsonFieldType.NUMBER).description("DisK(C) ?????????"),
                                fieldWithPath("ddrvDiskAmt").type(JsonFieldType.NUMBER).description("Disk(D) ?????????"),
                                fieldWithPath("logAmt").type(JsonFieldType.ARRAY).description("log DISK ?????????"),
                                fieldWithPath("dataAmt").type(JsonFieldType.ARRAY).description("data DISK ?????????")

                        ),
                        responseFields(
                                fieldWithPath("updateMsg").type(JsonFieldType.STRING).description("?????? ?????????"),
                                fieldWithPath("excelEditDto.cpuUseAmt").type(JsonFieldType.NUMBER).description("CPU ?????????"),
                                fieldWithPath("excelEditDto.memUseAmt").type(JsonFieldType.NUMBER).description("Memory ?????????"),
                                fieldWithPath("excelEditDto.cdrvUseAmt").type(JsonFieldType.NUMBER).description("Disk(C) ?????????"),
                                fieldWithPath("excelEditDto.cdrvDiskAmt").type(JsonFieldType.NUMBER).description("Disk(C) ?????????"),
                                fieldWithPath("excelEditDto.ddrvDiskAmt").type(JsonFieldType.NUMBER).description("Disk(D) ?????????"),
                                fieldWithPath("excelEditDto.logAmt[]").type(JsonFieldType.ARRAY).description("log DISK ?????????"),
                                fieldWithPath("excelEditDto.dataAmt[]").type(JsonFieldType.ARRAY).description("data DISK ?????????")
                        )
                ));

        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.updateMsg").value(msg))
                .andExpect(jsonPath("$.excelEditDto.cpuUseAmt").value(excelEditDto.getCpuUseAmt()))
                .andExpect(jsonPath("$.excelEditDto.memUseAmt").value(excelEditDto.getMemUseAmt()))
                .andExpect(jsonPath("$.excelEditDto.cdrvUseAmt").value(excelEditDto.getCdrvUseAmt()))
                .andExpect(jsonPath("$.excelEditDto.cdrvDiskAmt").value(excelEditDto.getCdrvDiskAmt()))
                .andExpect(jsonPath("$.excelEditDto.ddrvDiskAmt").value(excelEditDto.getDdrvDiskAmt()))
                .andExpect(jsonPath("$.excelEditDto.logAmt[0]").value(excelEditDto.getLogAmt().get(0)))
                .andExpect(jsonPath("$.excelEditDto.dataAmt[0]").value(excelEditDto.getDataAmt().get(0)));
    }

    @Test
    @DisplayName("Report Excel KIXX ????????? - PUT /report/excel/edit/kiix")
    void reportKixxTest() throws Exception {
        List<Double> dataList = new ArrayList<>();
        for (int i = 10, n = 100; i < n; ) {
                dataList.add((double) i);
                i+=10;
        }
        //given
        ExcelEditDto excelEditDto = ExcelEditDto.builder()
                .cpuUseAmt(10.1)
                .memUseAmt(77.2)
                .cdrvUseAmt(12.3)
                .cdrvDiskAmt(23.4)
                .ddrvDiskAmt(111)
                .logAmt(dataList)
                .dataAmt(dataList)
                .build();

        String msg = "Success";

        given(excelReportKixx.dailyReportKixxExelEdit(any(ExcelEditDto.class)))
                .willReturn(ExcelEditResponseDto.of(msg,excelEditDto));

        //when
        final ResultActions actions = mvc.perform(put("/report/excel/edit/kiix")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(getJsonStringByDto(excelEditDto)))
                .andDo(document("put-dailyReportKixx",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("updateMsg").type(JsonFieldType.STRING).description("?????? ?????????"),
                                fieldWithPath("excelEditDto.cpuUseAmt").type(JsonFieldType.NUMBER).description("CPU ?????????"),
                                fieldWithPath("excelEditDto.memUseAmt").type(JsonFieldType.NUMBER).description("Memory ?????????"),
                                fieldWithPath("excelEditDto.cdrvUseAmt").type(JsonFieldType.NUMBER).description("Disk(C) ?????????"),
                                fieldWithPath("excelEditDto.cdrvDiskAmt").type(JsonFieldType.NUMBER).description("Disk(C) ?????????"),
                                fieldWithPath("excelEditDto.ddrvDiskAmt").type(JsonFieldType.NUMBER).description("Disk(D) ?????????"),
                                fieldWithPath("excelEditDto.logAmt").type(JsonFieldType.ARRAY).description("log DISK ?????????"),
                                fieldWithPath("excelEditDto.dataAmt").type(JsonFieldType.ARRAY).description("data DISK ?????????")
                        )
                ));

        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.updateMsg").value(msg))
                .andExpect(jsonPath("$.excelEditDto.cpuUseAmt").value(excelEditDto.getCpuUseAmt()))
                .andExpect(jsonPath("$.excelEditDto.memUseAmt").value(excelEditDto.getMemUseAmt()))
                .andExpect(jsonPath("$.excelEditDto.cdrvUseAmt").value(excelEditDto.getCdrvUseAmt()))
                .andExpect(jsonPath("$.excelEditDto.cdrvDiskAmt").value(excelEditDto.getCdrvDiskAmt()))
                .andExpect(jsonPath("$.excelEditDto.ddrvDiskAmt").value(excelEditDto.getDdrvDiskAmt()))
                .andExpect(jsonPath("$.excelEditDto.logAmt").value(excelEditDto.getLogAmt()))
                .andExpect(jsonPath("$.excelEditDto.dataAmt").value(excelEditDto.getDataAmt()));
    }

    @Test
    @DisplayName("Report Excel ???????????? ????????? - PUT /report/excel/edit/survey")
    public void reportSurveyTest() throws Exception {
        //given
        ExcelEditDto excelEditDto = ExcelEditDto.builder()
                .cpuUseAmt(10.1)
                .memUseAmt(77.2)
                .cdrvUseAmt(12.3)
                .cdrvDiskAmt(23.4)
                .ddrvDiskAmt(111)
                .logAmt(Collections.singletonList(11.3))
                .dataAmt(Collections.singletonList(14.3))
                .build();

        String msg = "Success";

        given(excelReportSurvey.dailyReportSurveyExelEdit(any(ExcelEditDto.class)))
                .willReturn(ExcelEditResponseDto.of(msg,excelEditDto));

        //when
        final ResultActions actions = mvc.perform(put("/report/excel/edit/survey")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(getJsonStringByDto(excelEditDto)))
                .andDo(document("put-dailyReportSurvey",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                       /* requestFields(
                                fieldWithPath("cpuUseAmt").type(JsonFieldType.NUMBER).description("CPU ?????????"),
                                fieldWithPath("memUserAmt").type(JsonFieldType.NUMBER).description("Memory ?????????"),
                                fieldWithPath("logAmt").type(JsonFieldType.NUMBER).description("log DISK ?????????"),
                                fieldWithPath("dataAmt").type(JsonFieldType.NUMBER).description("data DISK ?????????")
                        ),*/
                        responseFields(
                                fieldWithPath("updateMsg").type(JsonFieldType.STRING).description("?????? ?????????"),
                                fieldWithPath("excelEditDto.cpuUseAmt").type(JsonFieldType.NUMBER).description("CPU ?????????"),
                                fieldWithPath("excelEditDto.memUseAmt").type(JsonFieldType.NUMBER).description("Memory ?????????"),
                                fieldWithPath("excelEditDto.cdrvUseAmt").type(JsonFieldType.NUMBER).description("Disk(C) ?????????"),
                                fieldWithPath("excelEditDto.cdrvDiskAmt").type(JsonFieldType.NUMBER).description("Disk(C) ?????????"),
                                fieldWithPath("excelEditDto.ddrvDiskAmt").type(JsonFieldType.NUMBER).description("Disk(D) ?????????"),
                                fieldWithPath("excelEditDto.logAmt[]").type(JsonFieldType.ARRAY).description("log DISK ?????????"),
                                fieldWithPath("excelEditDto.dataAmt[]").type(JsonFieldType.ARRAY).description("data DISK ?????????")
                        )
                ));

        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.updateMsg").value(msg))
                .andExpect(jsonPath("$.excelEditDto.cpuUseAmt").value(excelEditDto.getCpuUseAmt()))
                .andExpect(jsonPath("$.excelEditDto.memUseAmt").value(excelEditDto.getMemUseAmt()))
                .andExpect(jsonPath("$.excelEditDto.cdrvUseAmt").value(excelEditDto.getCdrvUseAmt()))
                .andExpect(jsonPath("$.excelEditDto.cdrvDiskAmt").value(excelEditDto.getCdrvDiskAmt()))
                .andExpect(jsonPath("$.excelEditDto.ddrvDiskAmt").value(excelEditDto.getDdrvDiskAmt()))
                .andExpect(jsonPath("$.excelEditDto.logAmt[0]").value(excelEditDto.getLogAmt().get(0)))
                .andExpect(jsonPath("$.excelEditDto.dataAmt[0]").value(excelEditDto.getDataAmt().get(0)));
    }

    @Test
    @DisplayName("Report Excel Web Shell ????????? - PUT /report/excel/edit/web-shell")
    void reportWebShellTest() throws Exception {
        List<Double> logList = List.of(13.3);
        List<Double> dataList = List.of(12.3);

        //given
        ExcelEditDto excelEditDto = ExcelEditDto.builder()
                .cpuUseAmt(10.1)
                .memUseAmt(77.2)
                .cdrvUseAmt(12.3)
                .cdrvDiskAmt(23.4)
                .ddrvDiskAmt(111)
                .logAmt(logList)
                .dataAmt(dataList)
                .build();

        String msg = "Success";

        given(excelReportWebShell.dailyReportWebShellExelEdit(any(ExcelEditDto.class)))
                .willReturn(ExcelEditResponseDto.of(msg,excelEditDto));

        //when
        final ResultActions actions = mvc.perform(put("/report/excel/edit/web-shell")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(getJsonStringByDto(excelEditDto)))
                .andDo(document("put-dailyReportWebShell",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("cpuUseAmt").type(JsonFieldType.NUMBER).description("CPU ?????????"),
                                fieldWithPath("memUseAmt").type(JsonFieldType.NUMBER).description("Memory ?????????"),
                                fieldWithPath("cdrvUseAmt").type(JsonFieldType.NUMBER).description("Disk(C) ?????????"),
                                fieldWithPath("ddrvDiskAmt").type(JsonFieldType.NUMBER).description("Disk(D) ?????????"),
                                fieldWithPath("logAmt").type(JsonFieldType.ARRAY).description("log DISK ?????????"),
                                fieldWithPath("dataAmt").type(JsonFieldType.ARRAY).description("data DISK ?????????"),
                                fieldWithPath("cdrvDiskAmt").type(JsonFieldType.NUMBER).description("DisK(C) ?????????")
                        ),
                        responseFields(
                                fieldWithPath("updateMsg").type(JsonFieldType.STRING).description("?????? ?????????"),
                                fieldWithPath("excelEditDto.cpuUseAmt").type(JsonFieldType.NUMBER).description("CPU ?????????"),
                                fieldWithPath("excelEditDto.memUseAmt").type(JsonFieldType.NUMBER).description("Memory ?????????"),
                                fieldWithPath("excelEditDto.cdrvUseAmt").type(JsonFieldType.NUMBER).description("Disk(C) ?????????"),
                                fieldWithPath("excelEditDto.cdrvDiskAmt").type(JsonFieldType.NUMBER).description("Disk(C) ?????????"),
                                fieldWithPath("excelEditDto.ddrvDiskAmt").type(JsonFieldType.NUMBER).description("Disk(D) ?????????"),
                                fieldWithPath("excelEditDto.logAmt[]").type(JsonFieldType.ARRAY).description("log DISK ?????????"),
                                fieldWithPath("excelEditDto.dataAmt[]").type(JsonFieldType.ARRAY).description("data DISK ?????????")
                        )
                ));

        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.updateMsg").value(msg))
                .andExpect(jsonPath("$.excelEditDto.cpuUseAmt").value(excelEditDto.getCpuUseAmt()))
                .andExpect(jsonPath("$.excelEditDto.memUseAmt").value(excelEditDto.getMemUseAmt()))
                .andExpect(jsonPath("$.excelEditDto.cdrvUseAmt").value(excelEditDto.getCdrvUseAmt()))
                .andExpect(jsonPath("$.excelEditDto.cdrvDiskAmt").value(excelEditDto.getCdrvDiskAmt()))
                .andExpect(jsonPath("$.excelEditDto.ddrvDiskAmt").value(excelEditDto.getDdrvDiskAmt()))
                .andExpect(jsonPath("$.excelEditDto.logAmt[0]").value(excelEditDto.getLogAmt().get(0)))
                .andExpect(jsonPath("$.excelEditDto.dataAmt[0]").value(excelEditDto.getDataAmt().get(0)));
    }
}
