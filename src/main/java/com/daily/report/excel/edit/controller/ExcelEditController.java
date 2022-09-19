package com.daily.report.excel.edit.controller;

import com.daily.report.excel.edit.dto.ExcelEditDto;
import com.daily.report.excel.edit.dto.ExcelEditResponseDto;
import com.daily.report.excel.edit.service.report.ExcelReportBulkMail;
import com.daily.report.excel.edit.service.report.ExcelReportKixx;
import com.daily.report.excel.edit.service.report.ExcelReportSurvey;
import com.daily.report.excel.edit.service.report.ExcelReportWebShell;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ExcelEditController {
    private final ExcelReportBulkMail excelReportBulkMail;
    private final ExcelReportKixx excelReportKixx;
    private final ExcelReportSurvey excelReportSurvey;
    private final ExcelReportWebShell excelReportWebShell;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> error(Exception e) {
        log.error("Excel Edit Exception {}",e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error : " + e.getClass().getSimpleName());
    }

    @PutMapping(value = "/report/excel/edit/bulk-mail")
    public ResponseEntity<ExcelEditResponseDto> dailyReportBulkMailExelEdit(@Valid @RequestBody ExcelEditDto dto) {
        return ResponseEntity.ok(excelReportBulkMail.dailyReportBulkMailExelEdit(dto));
    }

    @PutMapping(value = "/report/excel/edit/kixx")
    public ResponseEntity<ExcelEditResponseDto> dailyReportKixxExelEdit(@Valid @RequestBody ExcelEditDto dto) {
        return ResponseEntity.ok(excelReportKixx.dailyReportKixxExelEdit(dto));
    }

    @PutMapping(value = "/report/excel/edit/survey")
    public ResponseEntity<ExcelEditResponseDto> dailyReportSurveyExelEdit(@Valid @RequestBody ExcelEditDto dto) {
        return ResponseEntity.ok(excelReportSurvey.dailyReportSurveyExelEdit(dto));
    }

    @PutMapping(value = "/report/excel/edit/web-shell")
    public ResponseEntity<ExcelEditResponseDto> dailyReportWebShellExelEdit(@Valid @RequestBody ExcelEditDto dto) {
        return ResponseEntity.ok(excelReportWebShell.dailyReportWebShellExelEdit(dto));
    }
}
