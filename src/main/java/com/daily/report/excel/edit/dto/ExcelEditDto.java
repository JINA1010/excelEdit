package com.daily.report.excel.edit.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExcelEditDto {
    @NotNull
    private double cpuUseAmt;       //CPU 사용률
    @NotNull
    private double memUseAmt;       //Memory 사용률
    @NotNull
    private double cdrvUseAmt;      //Disk(C) 사용률
    @NotNull
    private double cdrvDiskAmt;     //Disk(C) 잔여량
    @NotNull
    private double ddrvDiskAmt;     //Disk(D) 잔여량
    @NotNull
    private List<Double> logAmt;        //log DISK 잔여량
    @NotNull
    private List<Double> dataAmt;       //data DISK 잔여량

    @Builder
    public ExcelEditDto(double cpuUseAmt, double memUseAmt, double cdrvUseAmt, double cdrvDiskAmt, double ddrvDiskAmt, List<Double> logAmt, List<Double> dataAmt) {
        this.cpuUseAmt = cpuUseAmt;
        this.memUseAmt = memUseAmt;
        this.cdrvUseAmt = cdrvUseAmt;
        this.cdrvDiskAmt = cdrvDiskAmt;
        this.ddrvDiskAmt = ddrvDiskAmt;
        this.logAmt = logAmt;
        this.dataAmt = dataAmt;
    }
}
