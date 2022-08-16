package com.daily.report.excel.edit.service.report;

import com.daily.report.excel.edit.constans.ExcelConstants;
import com.daily.report.excel.edit.constans.ExcelEditInfo;
import com.daily.report.excel.edit.dto.ExcelEditDto;
import com.daily.report.excel.edit.dto.ExcelEditResponseDto;
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
public class ExcelReportWebShell {
    @Transactional
    public ExcelEditResponseDto dailyReportWebShellExelEdit(ExcelEditDto dto) {
        String editMsg;
        String percent = ExcelConstants.EXCEL_EDIT_PERCENT;
        ExcelEditInfo excelEditInfo = ExcelEditInfo.findByExcelEditPath(ExcelConstants.EXCEL_EDIT_WEB_SHELL);
        String fileLocation = FileMakeUtils.getFilePath(excelEditInfo.getDailyReportNm(),
                ExcelConstants.EXCEL_EDIT_DEPT + ExcelConstants.EXCEL_XLSX_TYPE);

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(fileLocation));
            XSSFSheet sheet = workbook.getSheetAt(0);
            FileMakeUtils.excelHeaderCellMake(workbook,sheet);
            XSSFCellStyle cellContentsStyle = FileMakeUtils.excelContentsCellMake(workbook);

            // S1WEBSH
            FileMakeUtils.excelSheetMake(sheet,10,11,dto.getMemUseAmt() + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,11,12,dto.getCdrvDiskAmt() + percent, cellContentsStyle);
            FileMakeUtils.excelSheetMake(sheet,12,12,dto.getCdrvDiskAmt() + percent, cellContentsStyle);


            FileOutputStream outStream = new FileOutputStream(DateUtils.getFormatDate(String.valueOf(LocalDate.now()),
                    ExcelConstants.DATE_FORMAT_YYYYMMDD) + ExcelConstants.EXCEL_EDIT_UNDER_BAR + excelEditInfo.getDailyReportNm()
                    + ExcelConstants.EXCEL_EDIT_DEPT + ExcelConstants.EXCEL_XLSX_TYPE);

            workbook.write(outStream);
            outStream.close();

            editMsg = "Success";
        } catch (IOException e) {
            log.error("Daily Report Web Shell Exception : {}", e.getMessage());
            editMsg = "Fail";
        }

        return ExcelEditResponseDto.of(editMsg,dto);
    }
}
