package swd;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CreatePDF {
    
    File file;
    BaseFont bf;
    Font f_title;
    Font f_text;
    Font f_s_text;
    Font fontNormal;
    private String encoding;
    
    public void setFont() throws DocumentException, IOException{
        try{
            //подключаем файл шрифта, который поддерживает кириллицу
            ///usr/share/fonts/truetype/msttcorefonts/
            bf = BaseFont.createFont("/fonts/Times_New_Roman.ttf", BaseFont.IDENTITY_H , BaseFont.EMBEDDED); 
            fontNormal = FontFactory.getFont(("/fonts/arialuni.ttf"), encoding,BaseFont.EMBEDDED);
            f_title = new Font(bf, 14 );
            f_text = new Font(bf);
            f_s_text = new Font(bf, 8 , Font.ITALIC );
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void make_invoice() throws DocumentException, IOException{
        setFont();
        Document doc = new Document(PageSize.A4);
        Desktop d = Desktop.getDesktop();
        try{
            file = new File("invoice.pdf");
            PdfWriter.getInstance(doc, new FileOutputStream(file));
            doc.open();
            Paragraph title = new Paragraph();
            title.setAlignment(Element.ALIGN_CENTER);
            title.setFont(f_title);
            title.add("Счет фактура");
            
            Paragraph p_bna = new Paragraph();
            p_bna.setAlignment(Element.ALIGN_CENTER);
            p_bna.setFont(f_s_text);
            p_bna.add("(БИН, наименование и адрес)");
            
            doc.add(title);
            
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("Поставщик: ",f_text));
            doc.add(new Paragraph("БИН/ИИН и адрес места нахождения поставщика: ",f_text));
            doc.add(new Paragraph("ИИК поставщика: ",f_text));
            doc.add(new Paragraph("Договор (контракт) на поставку товаров (работ,услуг): ",f_text));
            doc.add(new Paragraph("Условия оплаты по договору (контракту): ",f_text));
            doc.add(new Paragraph("Пункт назначения поставляемых товаров (работ, услуг) ",f_text));
            doc.add(new Paragraph(" "));
            
            doc.add(new Paragraph("Поставка товаров (работ, услуг) осуществлена по доверенности: ",f_text));
            doc.add(new Paragraph("Способ отправления: ",f_text));
            doc.add(new Paragraph("Товарно-транспортная накладная: ",f_text));
            doc.add(new Paragraph(" "));//dasdasdsadasdasdddddddd
            
            doc.add(new Paragraph("Грузоотправитель: ",f_text));
            doc.add(p_bna);
            doc.add(new Paragraph(" "));
            
            doc.add(new Paragraph("Грузополучатель: ",f_text));
            doc.add(p_bna);
            doc.add(new Paragraph(" "));
            
            doc.add(new Paragraph("Получатель: ",f_text));
            doc.add(new Paragraph("БИН/ИИН и адрес места нахождения получателя: ",f_text));
            doc.add(new Paragraph("ИИК получателя: ",f_text));
            
            
            doc.add(create_table());
        
            doc.add(new Phrase("ИИК получателя: ",f_text));
            
            doc.close();
            
            DesktopApi.open(file);
            
            }catch(Exception ex){
                ex.printStackTrace();
            }
    }
    
    public PdfPTable create_table() throws DocumentException{
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingBefore(5f);
        
        PdfPCell cell;
        
        Phrase ph = new Phrase("Номер",f_text);
        
        cell = new PdfPCell(ph);
        table.addCell(cell);
        table.addCell("Nuber");

        return table;
    }
    
    public void make_report() throws DocumentException, IOException{
        Document doc = new Document(PageSize.A4.rotate());
        try{
            PdfWriter.getInstance(doc, new FileOutputStream("report.pdf"));
            doc.open();
            doc.add(new Paragraph("Create PDF report"));
            doc.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}