package com.petshop.petshop_inventory.dto.purchase.add_ons;

import com.petshop.petshop_inventory.dto.product.add_ons.BatchResponseDTO;
import com.petshop.petshop_inventory.model.purchase.add_ons.PurchaseDetails;

public record PurchaseDetailsResponseDTO(

        String productBarCode,
        String productName,
        String productBatch,
        Double unitPrice,
        Integer quantity,
        Double total,
        BatchResponseDTO bach


) {
    public PurchaseDetailsResponseDTO (PurchaseDetails purchaseDetails) {
        this(purchaseDetails.getBatch().getProduct().getBarCode(),
                purchaseDetails.getBatch().getProduct().getName(),
                purchaseDetails.getBatch().getBatchNumber(),
                purchaseDetails.getUnitPrice(),
                purchaseDetails.getQuantity(),
                purchaseDetails.getTotal(),
                new BatchResponseDTO(purchaseDetails.getBatch())

                );



    }
}
