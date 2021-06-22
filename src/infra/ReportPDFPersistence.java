package infra;

import java.io.*;

import business.control.UserStatisticControl;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import exceptions.InfraException;


public class ReportPDFPersistence implements IReportPersistence{

    public void saveFile(String texto, String nomeArquivo) throws InfraException {

        FileOutputStream fileOutputStream = null;
        PdfWriter pdfWriter = null;
        Document document = null;

        try {
            document = new Document();
            fileOutputStream = new FileOutputStream(nomeArquivo + ".pdf");
            pdfWriter = PdfWriter.getInstance(document, fileOutputStream);
            document.open();

            Font font = new Font(Font.FontFamily.HELVETICA, 20);
            Paragraph paragraph = new Paragraph(texto);
            paragraph.setFont(font);

            document.add(paragraph);

            document.close();
            pdfWriter.close();
        }
        catch(Exception e) {
            throw new InfraException();
        }
    }
}