package com.billbook.dto.request;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceiptRequest {

    private String heading;

    private String customerName;

    private String mobile;

    private String address;

    private String notes;

    @Valid
    @NotEmpty
    private List<ReceiptItemRequest> items;

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public List<ReceiptItemRequest> getItems() {
		return items;
	}

	public void setItems(List<ReceiptItemRequest> items) {
		this.items = items;
	}
    
    

}