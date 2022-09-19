package com.daily.report.excel.edit.util;

import com.daily.report.excel.edit.constans.ExcelConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.time.LocalDate;

@Slf4j
public class FileMakeUtils {

    public static String getFilePath(String fileNm, String fileType) {
        File currDir = new File(ExcelConstants.EXCEL_EDIT_DEPT);
        String path = currDir.getAbsolutePath();

        return path.substring(0, path.length() -1) + fileNm + fileType;
    }

    public static void excelHeaderCellMake(XSSFWorkbook workbook, XSSFSheet sheet) {
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        XSSFFont cellFont = workbook.createFont();
        cellFont.setBold(true);
        cellFont.setFontHeight(9);
        cellFont.setFontName(ExcelConstants.EXCEL_FONT_NAME);
        cellStyle.setFont(cellFont);

        FileMakeUtils.excelSheetMake(sheet,4,1,
                DateUtils.getFormatDate(String.valueOf(LocalDate.now()),ExcelConstants.DATE_FORMAT_YYYYMMDD2)
                        + ExcelConstants.EXCEL_EDIT_EMPTY + ExcelConstants.TIME_FORMAT,cellStyle);
    }

    public static XSSFCellStyle excelContentsCellMake(XSSFWorkbook workbook) {
        XSSFCellStyle cellContentsStyle = workbook.createCellStyle();
        cellContentsStyle.setAlignment(HorizontalAlignment.CENTER);
        cellContentsStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        XSSFFont cellContentsFont = workbook.createFont();
        cellContentsFont.setFontHeight(9);
        cellContentsFont.setFontName("돋움");

        cellContentsStyle.setBorderTop(BorderStyle.THIN); //테두리 위쪽
        cellContentsStyle.setBorderBottom(BorderStyle.THIN); //테두리 아래쪽
        cellContentsStyle.setBorderLeft(BorderStyle.THIN); //테두리 왼쪽
        cellContentsStyle.setBorderRight(BorderStyle.THIN); //테두리 오른쪽
        cellContentsStyle.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());  // 배경색
        cellContentsStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);	//채우기 적용

        cellContentsStyle.setFont(cellContentsFont);
        return cellContentsStyle;
    }

    public static void excelSheetMake(XSSFSheet sheet, Integer row, Integer cell, String val, XSSFCellStyle cellstyle) {
        sheet.getRow(row).createCell(cell).setCellValue(val);
        sheet.getRow(row).getCell(cell).setCellStyle(cellstyle);
    }
}
