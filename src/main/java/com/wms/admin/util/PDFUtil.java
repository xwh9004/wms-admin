package com.wms.admin.util;

import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;

import java.io.*;

public class PDFUtil {

    public static String convertToPdf(String path, String destPath) {

        File inputWord = new File(path);
        File outputFile = new File(destPath);
        try {
            InputStream docxInputStream = new FileInputStream(inputWord);
            OutputStream outputStream = new FileOutputStream(outputFile);
            IConverter converter = LocalConverter.make();
            String fileType = path.substring(path.lastIndexOf("."), path.length());//获取文件类型
            if (".docx".equals(fileType)) {
                converter.convert(docxInputStream).as(DocumentType.DOCX).to(outputStream).as(DocumentType.PDF).execute();
            } else if (".doc".equals(fileType)) {
                converter.convert(docxInputStream).as(DocumentType.DOC).to(outputStream).as(DocumentType.PDF).execute();
            } else if (".xls".equals(fileType)) {
                converter.convert(docxInputStream).as(DocumentType.XLS).to(outputStream).as(DocumentType.PDF).execute();
            } else if (".xlsx".equals(fileType)) {
                converter.convert(docxInputStream).as(DocumentType.XLSX).to(outputStream).as(DocumentType.PDF).execute();
            }
            outputStream.close();
            System.out.println("pdf转换成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outputFile.getAbsolutePath();
    }
}
