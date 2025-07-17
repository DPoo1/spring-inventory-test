package com.practice.test.DTOs;

import lombok.Data;

@Data
public class RecordSaleRequest {
    private Integer productId;
    private Integer quantitySold;
}
