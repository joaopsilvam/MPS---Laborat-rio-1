package infra;

import java.io.*;

import business.control.UserStatisticControl;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfObject;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.kernel.pdf.*;
import exceptions.DocumentException;
import exceptions.InfraException;


public class ReportPDFPersistence implements IReportPersistence{
    public ReportPDFPersistence(UserStatisticControl userStatisticControl) {
    }

    public void saveFile(String texto, String nomeArquivo) throws InfraException {

        FileOutputStream fileOutputStream = null;
        PdfWriter pdfWriter = null;
        File file;

        try {
            file = new File(nomeArquivo);
            fileOutputStream = new FileOutputStream(file);
            pdfWriter = new PdfWriter(fileOutputStream);
            pdfWriter.writeString(texto);

            pdfWriter.close();
            fileOutputStream.close();
        }
        catch(IOException e) {
            throw new InfraException();
        }
    }
}