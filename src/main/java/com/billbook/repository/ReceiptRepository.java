package com.billbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.billbook.entity.receipt.Receipt;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

}