package com.datn.be.infrastructure.utils.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;

@Service
public class QR {

    public static void genQR(String data) {
        String path = "C:\\Users\\hungv\\OneDrive\\Máy tính\\abc.jsp";
        try {
            BitMatrix matrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, 500, 500);
            MatrixToImageWriter.writeToPath(matrix, "jpg", Paths.get(path));
        } catch (WriterException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        QR.genQR("Vux Trongj Hungf");
    }

}
