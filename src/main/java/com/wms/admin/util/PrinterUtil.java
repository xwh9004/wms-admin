package com.wms.admin.util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.apache.poi.ss.usermodel.Workbook;


import javax.print.PrintService;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;

public class PrinterUtil {

    public static PrinterJob getPrintServiceByName(String printerName) throws Exception {
        PrinterJob job = PrinterJob.getPrinterJob();
        // 遍历查询打印机名称
        boolean flag = false;
        for (PrintService ps : PrinterJob.lookupPrintServices()) {
            String psName = ps.toString();
            // 选用指定打印机，需要精确查询打印机就用equals，模糊查询用contains
            if (psName.contains(printerName)) {
                flag = true;
                job.setPrintService(ps);
                break;
            }
        }
        if (!flag) {
            throw new RuntimeException("打印失败，未找到名称为" + printerName + "的打印机，请检查。");
        }
        return job;
    }

    public static void setPageStyle(PDDocument document, PrinterJob job) {
        job.setPageable(new PDFPageable(document));
    }

    public static PDDocument printPDF(String pdfPath, String printerName) throws Exception {
        File file = new File(pdfPath);
        PDDocument document = PDDocument.load(file);
        PrinterJob job = getPrintServiceByName(printerName);
        setPageStyle(document, job);
        // 开始打印
        job.print();
        return document;
    }
}
