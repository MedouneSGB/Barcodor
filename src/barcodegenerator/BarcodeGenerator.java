/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barcodegenerator;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import javax.swing.JOptionPane;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

/**
 *
 * @author MSGB
 */
public class BarcodeGenerator {


    public static void createImage(String image_name, String myString) {
        try {

            Code128Bean code128 = new Code128Bean();
            code128.setHeight(15f);
            code128.setModuleWidth(0.3);
            code128.setQuietZone(10);
            code128.doQuietZone(true);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(baos, "image/x-png", 300, BufferedImage.TYPE_BYTE_BINARY, false, 0);
            code128.generateBarcode(canvas, myString);
            canvas.finish();

            //write to png file
            FileOutputStream fos = new FileOutputStream(image_name + ".png");
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();

            System.out.println("Généré avec succes");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erreur " + e);
        }
    }

}
