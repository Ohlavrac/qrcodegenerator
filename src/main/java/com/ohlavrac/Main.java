package com.ohlavrac;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.Scanner;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class Main {
    public static void main(String[] args) throws WriterException, IOException {
        Scanner scan = new Scanner(System.in);
        boolean run = true;

        while (run) {
            System.out.println("=====[QR CODE GENERATOR]=====");
            System.out.println("1.  Generate QR CODE");
            System.out.println("0.  Quit");
            System.out.print("/> ");

            int choice = scan.nextInt();

            switch (choice) {
                case 1:
                    boolean run2 = true;
                    String data = "";
                    String fileName = "";
                    String fileFormat = "";
                    String charset = "UTF-8";

                    while (run2) {
                        System.out.println("1.  Data (information to be recorded in the qr code)  ");
                        System.out.println("2.  File Name                                         ");
                        System.out.println("3.  File Format                                       ");
                        System.out.println("4.  Generate QR CODE");
                        System.out.println("0.  Exit");

                        System.out.print("/> ");
                        int choice2 = scan.nextInt();

                        switch (choice2) {
                            case 1:
                                System.out.print("Link: ");
                                data = scan.next();
                                break;
                            case 2:
                                System.out.print("File Name");
                                fileName = scan.next();
                                break;
                            case 3:
                                System.out.println("Chose the file format");
                                System.out.println("1.  PNG");
                                System.out.println("2.  JPG");
                                System.out.print("/> ");
                                int fileFormatChoice = scan.nextInt();

                                if (fileFormatChoice == 1) {
                                    fileFormat = "png";
                                } else if (fileFormatChoice == 2) {
                                    fileFormat = "jpg";
                                }

                                break;
                            case 4:
                                System.out.println(data);
                                System.out.println(fileName);
                                System.out.println(fileFormat);
                                System.out.println(charset);

                                run2 = false;
                                break;
                            case 0:
                                run2 = false;
                                break;
                            default:
                                System.out.println("COMMAND ERRO");
                                break;
                        }
                    }
                    break;
                case 0:
                    System.out.println("GOOD BYE :D");
                    run = false;
                    break;
                default:
                    System.out.println("COMMAND ERROR");
                    break;
            }
        }

        /*String data = "google.com";
        String fileName = "seila.png";
        String fileFormat = "png";
        String charset = "UTF-8";
        Path path = FileSystems.getDefault().getPath(fileName);
        Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();

        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        generateQrCode(data, fileName, fileFormat, charset, hashMap, 300, 300, path);
        System.out.println("QR-Code Gerado");*/
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