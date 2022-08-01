package com.daily.report.excel.edit.service;

import com.daily.report.excel.edit.constans.ExcelConstants;
import com.daily.report.excel.edit.constans.ExcelEditInfo;
import com.daily.report.excel.edit.util.FileMakeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
@Service
public class ExcelPerformDataService {

    public String getExcelData(Integer row, Integer cell) {
        String value;
        ExcelEditInfo excelEditInfo = ExcelEditInfo.findByExcelEditPath(ExcelConstants.EXCEL_PERFORM_DATA);
        String fileLocation = FileMakeUtils.getFilePath(excelEditInfo.getDailyReportNm(),
                                               ExcelConstants.EXCEL_EDIT_DEPT + ExcelConstants.EXCEL_XLSX_TYPE);
        try {
            FileInputStream file = new FileInputStream(fileLocation);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            XSSFRow excelRow = sheet.getRow(row);
            XSSFCell excelCell = excelRow.getCell(cell);

            value = excelCell.getStringCellValue();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return value;
    }
}
