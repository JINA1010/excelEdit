package com.daily.report.excel.edit.constans;

import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;

@Getter
@ToString
public enum ExcelEditInfo {
    EXCEL_PERFORM_DATA(
            ExcelConstants.EXCEL_PERFORM_DATA,
            ExcelConstants.EXCEL_PERFORM_DATA,
            ExcelConstants.EXCEL_EDIT_DEPT + ExcelConstants.EXCEL_XLSX_TYPE
    ),
    EXCEL_BULK_MAIL(
            ExcelConstants.EXCEL_EDIT_BULK_MAIL,
            ExcelConstants.EXCEL_BULK_MAIL,
            ExcelConstants.EXCEL_EDIT_DEPT + ExcelConstants.EXCEL_XLSX_TYPE
    ),
    EXCEL_KIXX(
            ExcelConstants.EXCEL_EDIT_KIXX,
            ExcelConstants.EXCEL_KIXX,
            ExcelConstants.EXCEL_EDIT_DEPT + ExcelConstants.EXCEL_XLSX_TYPE
    ),
    EXCEL_SURVEY(
            ExcelConstants.EXCEL_EDIT_SURVEY,
            ExcelConstants.EXCEL_SURVEY,
            ExcelConstants.EXCEL_EDIT_DEPT + ExcelConstants.EXCEL_XLSX_TYPE
    ),
    EXCEL_WEBSHELL(
            ExcelConstants.EXCEL_EDIT_WEB_SHELL,
            ExcelConstants.EXCEL_WEB_SHELL,
            ExcelConstants.EXCEL_EDIT_DEPT + ExcelConstants.EXCEL_XLSX_TYPE
    );

    private final String dailyReportNm;
    private final String dailyReportPath;
    private final String dailyReportType;

    ExcelEditInfo(String dailyReportNm, String dailyReportPath, String dailyReportType) {
        this.dailyReportNm = dailyReportNm;
        this.dailyReportPath = dailyReportPath;
        this.dailyReportType = dailyReportType;
    }

    public static ExcelEditInfo findByExcelEditPath(String dailyReportNm) {
        return Arrays.stream(ExcelEditInfo.values())
                .filter(info -> info.getDailyReportNm().equals(dailyReportNm))
                .findAny()
                .orElse(null);
    }
}
