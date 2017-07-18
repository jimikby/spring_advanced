package com.epam.theatre.rest.parser;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.epam.theatre.domain.Ticket;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

public class TicketHttpMessageConverterImpl extends AbstractHttpMessageConverter<Ticket> {

	private static final Logger LOGGER = LogManager.getLogger(TicketHttpMessageConverterImpl.class);

    @Override
    protected boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    protected void writeInternal(Ticket t, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {

        outputMessage.getHeaders().set("Content-disposition", "inline; filename=ticket.pdf");
        outputMessage.getHeaders().setContentType(MediaType.valueOf("application/pdf"));

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, outputMessage.getBody());
            document.open();
            Table table = new Table(1);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell("Ticket:");
            table.addCell(t.toString());
            document.add(table);
            document.close();
        } catch (Exception e) {
        	LOGGER.info("PDF creating error: " + e.getMessage());
        }

    }

    @Override
    protected Ticket readInternal(Class<? extends Ticket> clazz, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        // TODO Auto-generated method stub
        return null;
    }

}
