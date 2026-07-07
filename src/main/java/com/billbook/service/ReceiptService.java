package com.billbook.service;

import java.util.List;

import com.billbook.dto.request.ReceiptRequest;
import com.billbook.dto.response.ReceiptResponse;


public interface ReceiptService {

    ReceiptResponse createReceipt(ReceiptRequest request);

    List<ReceiptResponse> getAllReceipts();

    ReceiptResponse getReceipt(Long id);

    void deleteReceipt(Long id);
    
    byte[] generatePdf(Long id);
    
}