package com.epam.theatre.config;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

public class PdfViewResolver extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter arg2,
			HttpServletRequest arg3, HttpServletResponse arg4) throws Exception {

		List<?> items = (List<?>) model.get("items");

		Table table = new Table(1);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell("Items list:");

		if (items != null) {
			for (Object item : items) {
				table.addCell(item.toString());
			}
		}

		document.add(table);
	}

}
