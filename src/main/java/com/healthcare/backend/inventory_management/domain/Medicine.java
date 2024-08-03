package com.healthcare.backend.inventory_management.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MEDICINE")
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(max = 255, message = "Name can have at most 255 characters")
    private String name;

    @Size(max = 255, message = "Generic name can have at most 255 characters")
    private String genericName;

    @Lob  // Changed to TEXT
    private String description;

    @NotBlank(message = "Manufacturer is mandatory")
    @Size(max = 255, message = "Manufacturer can have at most 255 characters")
    private String manufacturer;

    @NotBlank(message = "Category is mandatory")
    @Size(max = 255, message = "Category can have at most 255 characters")
    private String category;

    @NotBlank(message = "Form is mandatory")
    @Size(max = 100, message = "Form can have at most 100 characters")
    private String form;

    @NotBlank(message = "Dosage is mandatory")
    @Size(max = 100, message = "Dosage can have at most 100 characters")
    private String dosage;

    @NotBlank(message = "Batch number is mandatory")
    @Size(max = 100, message = "Batch number can have at most 100 characters")
    private String batchNumber;

    @Future(message = "Expiry date must be in the future")
    @Temporal(TemporalType.DATE)
    private Date expiryDate;

    @NotNull(message = "Stock quantity is mandatory")
    @Min(value = 0, message = "Stock quantity must be at least 0")
    private Integer stockQuantity;

    @NotNull(message = "Reorder level is mandatory")
    @Min(value = 0, message = "Reorder level must be at least 0")
    private Integer reorderLevel;

    @NotNull(message = "Price is mandatory")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Price must be a valid amount")
    private BigDecimal price;

    @NotNull(message = "Purchase price is mandatory")
    @DecimalMin(value = "0.0", inclusive = false, message = "Purchase price must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Purchase price must be a valid amount")
    private BigDecimal purchasePrice;

    @NotBlank(message = "Storage conditions are mandatory")
    @Lob  // Changed to TEXT
    private String storageConditions;

    @NotNull(message = "Prescriptions required flag is mandatory")
    private Boolean prescriptionsRequired;

    @NotNull(message = "Controlled substance flag is mandatory")
    private Boolean controlledSubstance;

    @Lob  // Changed to TEXT
    private String sideEffects;

    @Lob  // Changed to TEXT
    private String instructions;

    @Size(max = 100, message = "Barcode can have at most 100 characters")
    private String barcode;

    @NotBlank(message = "Country of origin is mandatory")
    @Size(max = 255, message = "Country of origin can have at most 255 characters")
    private String countryOfOrigin;

    @NotBlank(message = "Dosage unit is mandatory")
    @Size(max = 50, message = "Dosage unit can have at most 50 characters")
    private String dosageUnit;

    @Lob  // Changed to TEXT
    private String contraindications;

    @Lob  // Changed to TEXT
    private String interactions;

    @Lob  // Changed to TEXT
    private String warnings;
}
