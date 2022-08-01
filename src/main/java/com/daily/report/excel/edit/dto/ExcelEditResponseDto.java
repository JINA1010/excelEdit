package com.daily.report.excel.edit.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExcelEditResponseDto {

    private String updateMsg;
    private ExcelEditDto excelEditDto;

    @Builder
    public ExcelEditResponseDto(String updateMsg, ExcelEditDto excelEditDto) {
        this.updateMsg = updateMsg;
        this.excelEditDto = excelEditDto;
    }

    public static ExcelEditResponseDto of (String updateMsg, ExcelEditDto excelEditDto) {
        return ExcelEditResponseDto.builder()
                .updateMsg(updateMsg)
                .excelEditDto(excelEditDto)
                .build();
    }
}
