package ems.bll.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.EAN13Writer;

import java.awt.image.BufferedImage;
import java.nio.file.Paths;

public class EAN13Generator {

    //generateEAN13BarcodeImage("0123456789012", "ean13.png");
    public static void generateEAN13BarcodeImage(String barcodeText, String path) throws Exception {
        EAN13Writer barcodeWriter = new EAN13Writer();
        BitMatrix bitMatrix =
                barcodeWriter.encode(barcodeText, BarcodeFormat.EAN_13, 300, 150);

        MatrixToImageWriter.writeToPath(bitMatrix, "png", Paths.get(path));
    }

    public static BufferedImage generateEAN13BarcodeImage(String barcodeText) throws Exception {
        EAN13Writer barcodeWriter = new EAN13Writer();
        BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.EAN_13, 300, 150);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
}
