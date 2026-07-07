package com.billbook.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.billbook.dto.request.ReceiptRequest;
import com.billbook.dto.response.ReceiptResponse;
import com.billbook.entity.receipt.Receipt;
import com.billbook.entity.receiptitem.ReceiptItem;
import com.billbook.exception.ResourceNotFoundException;
import com.billbook.mapper.ReceiptMapper;
import com.billbook.repository.ReceiptRepository;
import com.billbook.service.ReceiptService;
import com.billbook.pdf.ReceiptPdfGenerator;

@Service
@Transactional
public class ReceiptServiceImpl implements ReceiptService {

	private final ReceiptRepository receiptRepository;
	private final ReceiptMapper receiptMapper;
	private final ReceiptPdfGenerator receiptPdfGenerator;

	public ReceiptServiceImpl(
	        ReceiptRepository receiptRepository,
	        ReceiptMapper receiptMapper,
	        ReceiptPdfGenerator receiptPdfGenerator) {

	    this.receiptRepository = receiptRepository;
	    this.receiptMapper = receiptMapper;
	    this.receiptPdfGenerator = receiptPdfGenerator;
	}

	@Override
	public ReceiptResponse createReceipt(ReceiptRequest request) {

		Receipt receipt = receiptMapper.toEntity(request);

		receipt.setReceiptDate(LocalDate.now());
		receipt.setReceiptNumber("INV-" + System.currentTimeMillis());

		BigDecimal grandTotal = BigDecimal.ZERO;

		receipt.setItems(new ArrayList<>());

		for (var itemRequest : request.getItems()) {

			ReceiptItem item = receiptMapper.toItemEntity(itemRequest);

			BigDecimal amount = item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));

			item.setAmount(amount);

			item.setReceipt(receipt);

			receipt.getItems().add(item);

			grandTotal = grandTotal.add(amount);
		}

		receipt.setGrandTotal(grandTotal);

		Receipt savedReceipt = receiptRepository.save(receipt);

		return receiptMapper.toResponse(savedReceipt);
	}

	@Override
	public List<ReceiptResponse> getAllReceipts() {

		return receiptRepository.findAll().stream().map(receiptMapper::toResponse).toList();
	}

	@Override
	public ReceiptResponse getReceipt(Long id) {

		Receipt receipt = receiptRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Receipt not found"));

		return receiptMapper.toResponse(receipt);
	}

	@Override
	public void deleteReceipt(Long id) {

		Receipt receipt = receiptRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Receipt not found"));

		receiptRepository.delete(receipt);
	}
	
	@Override
	public byte[] generatePdf(Long id) {

	    Receipt receipt = receiptRepository.findById(id)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException("Receipt not found"));

	    return receiptPdfGenerator.generate(receipt);
	}

}