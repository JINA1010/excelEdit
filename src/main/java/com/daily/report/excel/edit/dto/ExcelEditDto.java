package com.daily.report.excel.edit.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExcelEditDto {
    @NotNull
    private double cpuUseAmt;
    @NotNull
    private double memUseAmt;
    @NotNull
    private double cdrvUseAmt;
    @NotNull
    private double cdrvDiskAmt;
    @NotNull
    private double ddrvDiskAmt;
    @NotNull
    private double[] logAmt;
    @NotNull
    private double[] dataAmt;

    @Builder
    public ExcelEditDto(double cpuUseAmt, double memUseAmt, double cdrvUseAmt, double cdrvDiskAmt, double ddrvDiskAmt, double[] logAmt, double[] dataAmt) {
        this.cpuUseAmt = cpuUseAmt;
        this.memUseAmt = memUseAmt;
        this.cdrvUseAmt = cdrvUseAmt;
        this.cdrvDiskAmt = cdrvDiskAmt;
        this.ddrvDiskAmt = ddrvDiskAmt;
        this.logAmt = logAmt;
        this.dataAmt = dataAmt;
    }
}
