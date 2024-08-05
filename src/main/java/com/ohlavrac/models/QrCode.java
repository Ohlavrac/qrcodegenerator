package com.ohlavrac.models;

import java.io.IOException;
import java.nio.file.Path;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class QrCode {
    private String data;
    private String fileName;
    private String fileFormat;
    private String charset;
    private Path path;
    private int height;
    private int width;

    public QrCode(String data, String fileName, String fileFormat, String charset, Path path, int height, int width) {
        this.data = data;
        this.fileName = fileName;
        this.fileFormat = fileFormat;
        this.charset = charset;
        this.path = path;
        this.height = height;
        this.width = width;
    }

    public String getData() {
        return data;
    }
    public String getFileName() {
        return fileName;
    }
    public String getFileFormat() {
        return fileFormat;
    }
    public String getCharset() {
        return charset;
    }
    public Path getPath() {
        return path;
    }
    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }

    public void generateQrCode() throws WriterException, IOException {
        BitMatrix matrix =  new MultiFormatWriter().encode(
            new String(data.getBytes(charset), charset),
            BarcodeFormat.QR_CODE,
            width, height
        );

        MatrixToImageWriter.writeToPath(matrix, fileFormat, path);
    }
}
