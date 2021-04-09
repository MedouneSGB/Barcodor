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


    public static void createImage(String imageName, String barcode, String location) {
        try {
            
            //Filtre du slash 
            if(!"".equals(location)){location = location+"/";}
            System.out.println("location = "+location);
            
            //Generation Barcode
            Code128Bean code128 = new Code128Bean();
            code128.setHeight(15f);
            code128.setModuleWidth(0.3);
            code128.setQuietZone(10);
            code128.doQuietZone(true);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(baos, "image/x-png", 300, BufferedImage.TYPE_BYTE_BINARY, false, 0);
            code128.generateBarcode(canvas, barcode);
            canvas.finish();

            //Generation et ecriture sur image
            FileOutputStream fos = new FileOutputStream(location+""+ imageName + ".png");
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();

            System.out.println("Généré avec succés");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erreur " + e);
        }
    }

}
