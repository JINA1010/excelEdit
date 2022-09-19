package com.daily.report.excel.edit.service.report;

import com.daily.report.excel.edit.constans.ExcelConstants;
import com.daily.report.excel.edit.constans.ExcelEditInfo;
import com.daily.report.excel.edit.dto.ExcelEditDto;
import com.daily.report.excel.edit.dto.ExcelEditResponseDto;
import com.daily.report.excel.edit.service.ExcelPerformDataService;
import com.daily.report.excel.edit.util.DateUtils;
import com.daily.report.excel.edit.util.FileMakeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExcelReportBulkMail {
    private final ExcelPerformDataService excelPerformDataService;

    @Transactional
    public ExcelEditResponseDto dailyReportBulkMailExelEdit(ExcelEditDto dto) {
        String editMsg;
        String percent = ExcelConstants.EXCEL_EDIT_PERCENT;
        ExcelEditInfo excelEditInfo = ExcelEditInfo.findByExcelEditPath(ExcelConstants.EXCEL_EDIT_BULK_MAIL);
        String fileLocation = FileMakeUtils.getFilePath(excelEditInfo.getDailyReportPath(),
                                                        excelEditInfo.getDailyReportType());

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(fileLocation));
            XSSFSheet sheet = workbook.getSheetAt(0);
            FileMakeUtils.excelHeaderCellMake(workbook,sheet);
            XSSFCellStyle cellContentsStyle = FileMakeUtils.excelContentsCellMake(workbook);

            // S1WMAILS
            FileMakeUtils.excelSheetMake(sheet,9,11,excelPerformDataService.getExcelData(56,3) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,9,12,excelPerformDataService.getExcelData(56,5) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,9,13,excelPerformDataService.getExcelData(56,4) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,10,11,excelPerformDataService.getExcelData(56,6) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,10,12,excelPerformDataService.getExcelData(56,8) + percent,cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,10,13,excelPerformDataService.getExcelData(56,7) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,11,14, String.valueOf(dto.getCdrvDiskAmt()), cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,12,14, String.valueOf(dto.getDdrvDiskAmt()), cellContentsStyle);

            FileOutputStream outStream = new FileOutputStream(
                DateUtils.getFormatDate(String.valueOf(LocalDate.now()), ExcelConstants.DATE_FORMAT_YYYYMMDD) +
                      ExcelConstants.EXCEL_EDIT_UNDER_BAR + ExcelConstants.EXCEL_EDIT_NM +
                      ExcelConstants.EXCEL_EDIT_UNDER_BAR + excelEditInfo.getDailyReportNm() +
                      excelEditInfo.getDailyReportType());

            workbook.write(outStream);
            outStream.close();

            editMsg = "Success";
        } catch (IOException e) {
            log.error("Daily Report Bulk Mail Exception : {}", e.getMessage());
            editMsg = "Fail";
        }

        return ExcelEditResponseDto.of(editMsg,dto);
    }

}
