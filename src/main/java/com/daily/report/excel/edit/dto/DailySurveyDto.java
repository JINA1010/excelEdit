package com.daily.report.excel.edit.dto;

import lombok.*;

import java.util.List;

public class DailySurveyDto {
    @Getter
    @Setter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class surveyRequest {
        private double cpuUseAmt;       //CPU 사용률

        private double memUseAmt;       //Memory 사용률

        private List<Double> logAmt;        //log DISK 잔여량

        private List<Double> dataAmt;       //data DISK 잔여량

        @Builder
        public surveyRequest(double cpuUseAmt, double memUseAmt, List<Double> logAmt, List<Double> dataAmt) {
            this.cpuUseAmt = cpuUseAmt;
            this.memUseAmt = memUseAmt;
            this.logAmt = logAmt;
            this.dataAmt = dataAmt;
        }

        public ExcelEditDto toVO() {
            return ExcelEditDto.builder()
                    .cpuUseAmt(cpuUseAmt)
                    .memUseAmt(memUseAmt)
                    .logAmt(logAmt)
                    .dataAmt(dataAmt)
                    .build();
        }
    }
    
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Response {
        private String updateMsg;
        private DailySurveyDto.surveyRequest dailySurveyDto;

        @Builder
        public Response(String updateMsg, DailySurveyDto.surveyRequest dailySurveyDto) {
            this.updateMsg = updateMsg;
            this.dailySurveyDto = dailySurveyDto;
        }
    }

}
