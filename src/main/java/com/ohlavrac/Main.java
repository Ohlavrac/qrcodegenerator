package com.ohlavrac;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class Main {
    public static void main(String[] args) throws WriterException, IOException {
        String data = "google.com";
        String fileName = "seila.png";
        String fileFormat = "png";
        String charset = "UTF-8";
        Path path = FileSystems.getDefault().getPath(fileName);
        Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();

        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        generateQrCode(data, fileName, fileFormat, charset, hashMap, 300, 300, path);
        System.out.println("QR-Code Gerado");
    }

    public static void generateQrCode(
        String data,
        String fileName,
        String fileFormat,
        String charset,
        Map hashMap,
        int height,
        int width,
        Path path
    ) throws WriterException, IOException{
        BitMatrix matrix = new MultiFormatWriter().encode(
            new String(data.getBytes(charset), charset),
            BarcodeFormat.QR_CODE, width, height
        );

        MatrixToImageWriter.writeToPath(matrix, fileFormat, path);
    }
}