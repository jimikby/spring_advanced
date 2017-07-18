package com.epam.theatre.rest.parser;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.epam.theatre.domain.Ticket;
import com.google.common.collect.Lists;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class ListTicketHttpMessageConverterImpl implements HttpMessageConverter<List<Ticket>> {

	private static final Logger LOGGER = LogManager.getLogger(ListTicketHttpMessageConverterImpl.class);

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        if (getSupportedMediaTypes().contains(mediaType)) {
            return true;
        }
        return false;
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return Lists.newArrayList(new MediaType("application","pdf"));
    }

    @Override
    public void write(List<Ticket> t, MediaType contentType, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {

        outputMessage.getHeaders().set("Content-disposition", "inline; filename=tickets.pdf");
        outputMessage.getHeaders().setContentType(MediaType.valueOf("application/pdf"));

        try {
            Document document = new Document();

            PdfWriter.getInstance(document, outputMessage.getBody());
            document.open();

            PdfPTable table = new PdfPTable(1);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            PdfPCell cell1;
            if (t.size() == 1) {
                cell1 = new PdfPCell(new Paragraph("Ticket:"));
            } else {
                cell1 = new PdfPCell(new Paragraph("Tickets:"));
            }
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

            table.addCell(cell1);

            for (Ticket ticket: t) {
                PdfPCell cell = new PdfPCell(new Paragraph(ticket.toString().trim()));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);
            }

            document.add(table);
            document.close();
        } catch (Exception e) {
        	LOGGER.info("PDF creating error: " + e.getMessage());
        }
    }

    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Ticket> read(Class<? extends List<Ticket>> clazz, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        // TODO Auto-generated method stub
        return null;
    }

}
