package com.ohlavrac;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Scanner;

import com.google.zxing.WriterException;
import com.ohlavrac.models.QrCode;

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
                    System.out.print("\033\143");
                    boolean run2 = true;
                    String data = "UNDEFINED";
                    String fileName = "UNDEFINED";
                    String fileFormat = "UNDEFINED";
                    String charset = "UTF-8";

                    while (run2) {
                        System.out.println("1.  Data (information to be recorded in the qr code)  ["+ data +"]");
                        System.out.println("2.  File Name                                         ["+ fileName +"]");
                        System.out.println("3.  File Format                                       ["+ fileFormat +"]");
                        System.out.println("4.  Generate QR CODE");
                        System.out.println("0.  Exit");

                        System.out.print("/> ");
                        int choice2 = scan.nextInt();

                        switch (choice2) {
                            case 1:
                                System.out.print("\033\143");
                                System.out.print("Link: ");
                                data = scan.next();
                                break;
                            case 2:
                                System.out.print("\033\143");   
                                System.out.print("File Name: ");
                                fileName = scan.next();
                                break;
                            case 3:
                                System.out.print("\033\143");
                                System.out.println("Chose the file format");
                                System.out.println("1.  PNG");
                                System.out.println("2.  JPG");
                                System.out.print("/> ");
                                int fileFormatChoice = scan.nextInt();

                                if (fileFormatChoice == 1) {
                                    fileFormat = "png";
                                } else if (fileFormatChoice == 2) {
                                    fileFormat = "jpg";
                                } else {
                                    fileFormat = "png";
                                }

                                break;
                            case 4:

                                if (data == "UNDEFINED" || fileFormat == "UNDEFINED" || fileName == "UNDEFINED") {
                                    System.out.println("ERROR");
                                } else {
                                    System.out.print("\033\143");
                                    Path path = FileSystems.getDefault().getPath(fileName+"."+fileFormat);

                                    QrCode qrcode = new QrCode(data, fileName, fileFormat, charset, path, 200, 200);
                                    qrcode.generateQrCode();
                                    run2 = false;
                                }
                                
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
    }   
}