package com.billbook.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.billbook.dto.request.ReceiptRequest;
import dto.response.ApiResponse;
import com.billbook.dto.response.ReceiptResponse;
import com.billbook.service.ReceiptService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/receipts")
public class ReceiptController {

    private final ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ReceiptResponse>> createReceipt(
            @Valid @RequestBody ReceiptRequest request) {

        ReceiptResponse receipt = receiptService.createReceipt(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "Receipt created successfully",
                        receipt));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ReceiptResponse>>> getAllReceipts() {

        List<ReceiptResponse> receipts = receiptService.getAllReceipts();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Receipts fetched successfully",
                        receipts));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ReceiptResponse>> getReceipt(
            @PathVariable Long id) {

        ReceiptResponse receipt = receiptService.getReceipt(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Receipt fetched successfully",
                        receipt));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteReceipt(
            @PathVariable Long id) {

        receiptService.deleteReceipt(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Receipt deleted successfully",
                        null));
    }
    
    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable Long id) {

        byte[] pdf = receiptService.generatePdf(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=receipt.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}