package com.devsuperior.dsmeta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaleReportDTO {

    private Long saleId;
    private LocalDate date;
    private Double amount;
    private String sellerName;

}
