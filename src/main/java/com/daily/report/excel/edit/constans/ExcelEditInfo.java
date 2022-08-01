package com.daily.report.excel.edit.constans;

import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;


@Getter
@ToString
public enum ExcelEditInfo {
    EXCEL_PERFORM_DATA(ExcelConstants.EXCEL_PERFORM_DATA,"data.xlxs\\"),
    EXCEL_BULK_MAIL(ExcelConstants.EXCEL_EDIT_BULI_MAIL,"bulkMail.xlxs\\"),
    EXCEL_KIXX(ExcelConstants.EXCEL_EDIT_KIXX,"kixx.xlxs\\"),
    EXCEL_SURVEY(ExcelConstants.EXCEL_EDIT_SURVEY,"survey.xlxs\\"),
    EXCEL_WEBSHELL(ExcelConstants.EXCEL_EDIT_WEB_SHELL,"webShell.xlxs\\");

    private final String dailyReportNm;
    private final String dailyReportPath;

    ExcelEditInfo(String dailyReportNm, String dailyReportPath) {
        this.dailyReportNm = dailyReportNm;
        this.dailyReportPath = dailyReportPath;
    }

    public static ExcelEditInfo findByExcelEditPath(String dailyReportNm) {
        return Arrays.stream(ExcelEditInfo.values())
                .filter(info -> info.getDailyReportNm().equals(dailyReportNm))
                .findAny()
                .orElse(null);
    }
}
