package com.billbook.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.billbook.dto.request.ReceiptItemRequest;
import com.billbook.dto.request.ReceiptRequest;
import com.billbook.dto.response.ReceiptItemResponse;
import com.billbook.dto.response.ReceiptResponse;
import com.billbook.entity.receipt.Receipt;
import com.billbook.entity.receiptitem.ReceiptItem;

@Mapper(componentModel = "spring")
public interface ReceiptMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "receiptNumber", ignore = true)
    @Mapping(target = "receiptDate", ignore = true)
    @Mapping(target = "grandTotal", ignore = true)
    Receipt toEntity(ReceiptRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "receipt", ignore = true)
    @Mapping(target = "amount", ignore = true)
    ReceiptItem toItemEntity(ReceiptItemRequest request);

    ReceiptResponse toResponse(Receipt receipt);

    ReceiptItemResponse toItemResponse(ReceiptItem item);

}