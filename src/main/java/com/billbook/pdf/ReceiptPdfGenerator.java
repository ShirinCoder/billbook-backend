package com.billbook.pdf;

import java.io.ByteArrayOutputStream;

import org.springframework.stereotype.Component;

import com.billbook.entity.receipt.Receipt;
import com.billbook.entity.receiptitem.ReceiptItem;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

@Component
public class ReceiptPdfGenerator {

    public byte[] generate(Receipt receipt) {

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Heading
        document.add(
                new Paragraph(receipt.getHeading())
                        .setBold()
                        .setFontSize(22)
                        .setTextAlignment(TextAlignment.CENTER));

        document.add(new Paragraph(" "));

        // Table
        Table table = new Table(UnitValue.createPercentArray(new float[]{4, 2, 2, 2}));
        table.setWidth(UnitValue.createPercentValue(100));

        // Header
        table.addHeaderCell(createHeaderCell("Item"));
        table.addHeaderCell(createHeaderCell("Qty"));
        table.addHeaderCell(createHeaderCell("Price"));
        table.addHeaderCell(createHeaderCell("Amount"));

        boolean alternate = false;

        for (ReceiptItem item : receipt.getItems()) {

            Cell itemCell = createBodyCell(item.getItemName());
            Cell qtyCell = createBodyCell(String.valueOf(item.getQuantity()));
            Cell priceCell = createBodyCell("₹ " + item.getPrice());
            Cell amountCell = createBodyCell("₹ " + item.getAmount());

            if (alternate) {
                itemCell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
                qtyCell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
                priceCell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
                amountCell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
            }

            table.addCell(itemCell);
            table.addCell(qtyCell);
            table.addCell(priceCell);
            table.addCell(amountCell);

            alternate = !alternate;
        }

        // Grand Total Row
        table.addCell(new Cell(1, 3)
                .add(new Paragraph("Grand Total").setBold())
                .setTextAlignment(TextAlignment.RIGHT)
                .setBackgroundColor(ColorConstants.LIGHT_GRAY));

        table.addCell(new Cell()
                .add(new Paragraph("₹ " + receipt.getGrandTotal()).setBold())
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(ColorConstants.YELLOW));

        document.add(table);

        // Notes
        if (receipt.getNotes() != null && !receipt.getNotes().isBlank()) {

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Notes")
                    .setBold()
                    .setFontSize(12));

            document.add(new Paragraph(receipt.getNotes()));
        }

        document.close();

        return out.toByteArray();
    }

    // Header Cell
    private Cell createHeaderCell(String text) {

        return new Cell()
                .add(new Paragraph(text).setBold())
                .setBackgroundColor(ColorConstants.BLUE)
                .setFontColor(ColorConstants.WHITE)
                .setTextAlignment(TextAlignment.CENTER);
    }

    // Body Cell
    private Cell createBodyCell(String text) {

        return new Cell()
                .add(new Paragraph(text))
                .setTextAlignment(TextAlignment.CENTER);
    }
}