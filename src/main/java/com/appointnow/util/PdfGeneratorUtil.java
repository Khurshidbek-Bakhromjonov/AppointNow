package com.appointnow.util;

import com.appointnow.entity.Invoice;
import com.itextpdf.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PdfGeneratorUtil {

    private final SpringTemplateEngine springTemplateEngine;

    @Value("${base.url}")
    private final String baseUrl;

    public File generatePdfFromInvoice(Invoice invoice) {
        Context context = new Context();
        context.setVariable("invoice", invoice);
        String processHtml = springTemplateEngine.process("email/pdf/invoice", context);

        ITextRenderer iTextRenderer = new ITextRenderer();
        iTextRenderer.setDocumentFromString(processHtml, baseUrl);
        iTextRenderer.layout();

        String fileName = UUID.randomUUID().toString();
        FileOutputStream os = null;
        try {
            final File outputFile = File.createTempFile(fileName, ".pdf");
            os = new FileOutputStream(outputFile);
            iTextRenderer.createPDF(os, false);
            iTextRenderer.finishPDF();
            return outputFile;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


}
