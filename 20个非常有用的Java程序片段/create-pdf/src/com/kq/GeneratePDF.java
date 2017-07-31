package com.kq;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class GeneratePDF {

	public static void main(String[] args) {

		try {
			OutputStream out = new FileOutputStream(new File("src/com/kq/test.pdf"));

			Document document = new Document();
			PdfWriter.getInstance(document, out);
			document.open();
			document.add(new Paragraph("Hello World!"));
			document.add(new Paragraph(new Date().toString()));

			document.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
