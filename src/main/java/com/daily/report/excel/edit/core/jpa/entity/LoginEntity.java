package com.daily.report.excel.edit.core.jpa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "TB_LOGIN_INFO")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginEntity implements Serializable {

    @Id
    @Column(name = "LOGIN_SEQ", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long loginSeq;

    @Column(name = "CUST_NO")
    private String custNo;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH")
    @Column(name = "LOGIN_DT")
    private LocalDateTime loginDt;

    @Column(name = "LOGIN_IP")
    private String loginIp;

    @Column(name = "SNS_TYPE")
    private String snsType;

    @Column(name = "DEVICE_TYPE")
    private String deviceType;
}
