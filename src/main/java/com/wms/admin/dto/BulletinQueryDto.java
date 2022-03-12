package com.wms.admin.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BulletinQueryDto {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private Integer page = 1;

    private Integer limit = 10;
}
