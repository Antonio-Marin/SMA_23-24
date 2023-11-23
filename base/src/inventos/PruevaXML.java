import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;

public class PruevaXML {
    static final private Logger LOGGER = Logger.getLogger("mx.com.hash.pruebaxml.PruebaXML");
    
    public static void main(String[] args) {
        try {
            CrearXML crearXML = new CrearXML();
            Document documento = crearXML.crearDocumento();
            
            System.out.println(crearXML.convertirString(documento));
            
            crearXML.escribirArchivo(documento, "ejemplo.xml");            
            
        } catch (ParserConfigurationException ex) {
            LOGGER.log(Level.SEVERE, "Error de configuracion");
            LOGGER.log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            LOGGER.log(Level.SEVERE, "Error de transformacion XML a String");
            LOGGER.log(Level.SEVERE, null, ex);
        }
        
    }
}
