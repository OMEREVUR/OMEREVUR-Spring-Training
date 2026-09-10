package com.servicedesk.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoHardwarePart {

    private Long id;

    @NotBlank(message = "Parça adı boş olamaz")
    private String partName;

    @NotBlank(message = "Parça kodu boş olamaz")
    private String partCode;

    @NotNull(message = "Fiyat boş olamaz")
    @Positive(message = "Fiyat sıfırdan büyük olmalı")
    private Double price;

    @PositiveOrZero(message = "Stok adedi negatif olamaz")
    private Integer stockQuantity;
}
