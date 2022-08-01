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
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
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
public class ExcelReportKixx {
    private final ExcelPerformDataService excelPerformDataService;

    @Transactional
    public ExcelEditResponseDto dailyReportKixxExelEdit(ExcelEditDto dto) {
        String editMsg;
        String percent = ExcelConstants.EXCEL_EDIT_PERCENT;
        ExcelEditInfo excelEditInfo = ExcelEditInfo.findByExcelEditPath(ExcelConstants.EXCEL_EDIT_KIXX);
        String fileLocation = FileMakeUtils.getFilePath(excelEditInfo.getDailyReportNm(),
                ExcelConstants.EXCEL_EDIT_DEPT + ExcelConstants.EXCEL_XLSX_TYPE);

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(fileLocation));
            XSSFSheet sheet = workbook.getSheetAt(0);
            FileMakeUtils.excelHeaderCellMake(workbook,sheet);
            XSSFCellStyle cellContentsStyle = FileMakeUtils.excelContentsCellMake(workbook);

            // S1CIWEB1
            FileMakeUtils.excelSheetMake(sheet,9,12,excelPerformDataService.getExcelData(72,3) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,9,13,excelPerformDataService.getExcelData(72,5) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,9,14,excelPerformDataService.getExcelData(72,4) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,10,12,excelPerformDataService.getExcelData(72,6) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,10,13,excelPerformDataService.getExcelData(72,8) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,10,14,excelPerformDataService.getExcelData(72,7) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,11,15, dto.getLogAmt()[0] + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,12,15,dto.getDataAmt()[0] + percent, cellContentsStyle);

            // S1CIWEB2
            FileMakeUtils.excelSheetMake(sheet,13,12,excelPerformDataService.getExcelData(73,3) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,13,13,excelPerformDataService.getExcelData(73,5) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,13,14,excelPerformDataService.getExcelData(73,4) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,14,12,excelPerformDataService.getExcelData(73,6) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,14,13,excelPerformDataService.getExcelData(73,8) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,14,14,excelPerformDataService.getExcelData(73,7) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,15,15,dto.getLogAmt()[1] + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,16,15,dto.getDataAmt()[1] + percent, cellContentsStyle);

            // S1CIWEB3
            FileMakeUtils.excelSheetMake(sheet,17,12,excelPerformDataService.getExcelData(74,3) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,17,13,excelPerformDataService.getExcelData(74,5) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,17,14,excelPerformDataService.getExcelData(74,4) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,18,12,excelPerformDataService.getExcelData(74,6) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,18,13,excelPerformDataService.getExcelData(74,8) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,18,14,excelPerformDataService.getExcelData(74,7) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,19,15,dto.getLogAmt()[2] + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,20,15,dto.getDataAmt()[2] + percent, cellContentsStyle);

            // S1CIWEB4
            FileMakeUtils.excelSheetMake(sheet,21,12,excelPerformDataService.getExcelData(75,3) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,21,13,excelPerformDataService.getExcelData(75,5) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,21,14,excelPerformDataService.getExcelData(75,4) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,22,12,excelPerformDataService.getExcelData(75,6) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,22,13,excelPerformDataService.getExcelData(75,8) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,22,14,excelPerformDataService.getExcelData(75,7) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,23,15,dto.getLogAmt()[3] + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,24,15,dto.getDataAmt()[3] + percent, cellContentsStyle);

            // S1CIAPP1
            FileMakeUtils.excelSheetMake(sheet,25,12,excelPerformDataService.getExcelData(76,3) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,25,13,excelPerformDataService.getExcelData(76,5) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,25,14,excelPerformDataService.getExcelData(76,4) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,26,12,excelPerformDataService.getExcelData(76,6) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,26,13,excelPerformDataService.getExcelData(76,8) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,26,14,excelPerformDataService.getExcelData(76,7) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,27,15,dto.getLogAmt()[4] + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,28,15,dto.getDataAmt()[4] + percent, cellContentsStyle);

            // S1CIAPP2
            FileMakeUtils.excelSheetMake(sheet,29,12,excelPerformDataService.getExcelData(77,3) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,29,13,excelPerformDataService.getExcelData(77,5) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,29,14,excelPerformDataService.getExcelData(77,4) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,30,12,excelPerformDataService.getExcelData(77,6) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,30,13,excelPerformDataService.getExcelData(77,8) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,30,14,excelPerformDataService.getExcelData(77,7) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,31,15,dto.getLogAmt()[5] + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,32,15,dto.getDataAmt()[5] + percent, cellContentsStyle);

            // S1CIAPP3
            FileMakeUtils.excelSheetMake(sheet,33,12,excelPerformDataService.getExcelData(78,3) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,33,13,excelPerformDataService.getExcelData(78,5) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,33,14,excelPerformDataService.getExcelData(78,4) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,34,12,excelPerformDataService.getExcelData(78,6) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,34,13,excelPerformDataService.getExcelData(78,8) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,34,14,excelPerformDataService.getExcelData(78,7) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,35,15,dto.getLogAmt()[6] + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,36,15,dto.getDataAmt()[6] + percent, cellContentsStyle);

            // S1CIAPP4
            FileMakeUtils.excelSheetMake(sheet,37,12,excelPerformDataService.getExcelData(79,3) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,37,13,excelPerformDataService.getExcelData(79,5) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,37,14,excelPerformDataService.getExcelData(79,4) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,38,12,excelPerformDataService.getExcelData(79,6) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,38,13,excelPerformDataService.getExcelData(79,8) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,38,14,excelPerformDataService.getExcelData(79,7) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,39,15,dto.getLogAmt()[7] + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,40,15,dto.getDataAmt()[7] + percent, cellContentsStyle);

            // S1NPSLDB
            FileMakeUtils.excelSheetMake(sheet,49,12,excelPerformDataService.getExcelData(81,3) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,49,13,excelPerformDataService.getExcelData(81,5) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,49,14,excelPerformDataService.getExcelData(81,4) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,50,12,excelPerformDataService.getExcelData(81,6) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,50,13,excelPerformDataService.getExcelData(81,8) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,50,14,excelPerformDataService.getExcelData(81,7) + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,51,15,dto.getLogAmt()[8] + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,52,15,dto.getDataAmt()[8] + percent, cellContentsStyle);

            FileOutputStream outStream = new FileOutputStream(DateUtils.getFormatDate(String.valueOf(LocalDate.now()),
                    ExcelConstants.DATE_FORMAT_YYYYMMDD) + ExcelConstants.EXCEL_EDIT_UNDER_BAR + excelEditInfo.getDailyReportNm()
                    + ExcelConstants.EXCEL_EDIT_DEPT + ExcelConstants.EXCEL_XLSX_TYPE);

            workbook.write(outStream);
            outStream.close();

            editMsg = "Success";
        } catch (IOException e) {
            log.error("Daily Report KIXX Exception : {}", e.getMessage());
            editMsg = "Fail";
        }

        return ExcelEditResponseDto.of(editMsg,dto);
    }
}
