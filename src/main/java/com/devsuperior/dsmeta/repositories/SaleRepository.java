package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(value = """
            SELECT
               new com.devsuperior.dsmeta.dto.SaleReportDTO(sa.id, sa.date, sa.amount, sa.seller.name)
            FROM Sale sa
            WHERE UPPER(sa.seller.name) LIKE UPPER(CONCAT('%', :name, '%'))
            AND sa.date BETWEEN :minDate AND :maxDate
            """)
    Page<SaleReportDTO> getReport(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);

    @Query(value = """
            SELECT
               new com.devsuperior.dsmeta.dto.SaleSummaryDTO(sa.seller.name, SUM(sa.amount))
            FROM Sale sa
            WHERE sa.date BETWEEN :minDate AND :maxDate
            GROUP BY sa.seller.name
            """)
    List<SaleSummaryDTO> getSummary(LocalDate minDate, LocalDate maxDate);
}
