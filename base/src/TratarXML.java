import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/*Codigo creado por: Juan Miguel García González
 * UCLM
 * 4º Ingenieria Informatica
 * En este codigo se muestra como validar un archivo XML con un esquema XSD
 * Se puede validar desde un archivo o desde un Document
 * Se muestra como se puede convertir un archivo a Document
 * Se dan ejemplos de XML validos e invalidos
 * Se muestran los errores en los XML invalidos
 */

public class TratarXML { // Se permite la validación de un archivo XML con un esquema XSD
    // Se puede validar desde un archivo o desde un Document
    public static boolean validarXMLConEsquema(Object archivoXML, String archivoXSD) {
        try {
            Source source=null;
            //1. Se crea una instancia de SchemaFactory para poder instanciar posteriormente un esquema.
            //2. Se define el tipo de esquema que se va a usar (W3C_XML_SCHEMA_NS_URI).
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            //3. Se crea un objeto Schema a partir del archivo XSD.
            Schema schema = factory.newSchema(new StreamSource(new File(archivoXSD)));
            //4. Se crea un objeto Validator a partir del objeto Schema.
            Validator validator = schema.newValidator();
            //5. Crear un ErrorHandler personalizado para gestionar los errores que se puedan producir al validar el archivo XML.
            CustomErrorHandler errorHandler = new CustomErrorHandler();
            validator.setErrorHandler(errorHandler);
            //6. Se crea un Source a partir del archivo XML. Este es el que se va a validar.
            if (archivoXML instanceof Document) { // Si es un Document
                source = new DOMSource((Document) archivoXML);
            } else if (archivoXML instanceof String) { // Si es un String
                source = new StreamSource(new File((String)archivoXML));}
            //7. Se valida el archivo XML. Si no es válido, lanza una excepción.
            validator.validate(source);
            if (errorHandler.hasErrors()) {
                errorHandler.printErrors();
                return false;
            }
            return true;
        } catch (Exception e) {
            System.err.println("Error al validar el archivo XML: " + e.getMessage());
            return false;
        }}
    // Clase para manejar los errores de validación
    private static class CustomErrorHandler implements org.xml.sax.ErrorHandler {
        private boolean hasErrors = false;
        @Override
        public void warning(SAXParseException exception) throws SAXException {
            System.err.println("Advertencia: " + exception.getMessage());
        }
        @Override
        public void error(SAXParseException exception) throws SAXException {
            hasErrors = true;
            System.err.println("Error: " + exception.getMessage());
        }
        @Override
        public void fatalError(SAXParseException exception) throws SAXException {
            hasErrors = true;
            System.err.println("Error fatal: " + exception.getMessage());
        }
        public boolean hasErrors() {
            return hasErrors;
        }
        public void printErrors() {
            System.err.println("Errores de validación XML encontrados.");
        }
    }

    public static Document archivoToDocument(String archivo){
        try{
            File xmlFile = new File(archivo);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document xml = dBuilder.parse(xmlFile);
            return xml;
        }catch(Exception e){
            System.err.println("Error al convertir el archivo a Document: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args){
        String archivoXML = ".\\XML_PROTOCOLO_COMUNICACION_COMUN.xml";
        String archivoXSD = ".\\ESQUEMA_XML_PROTOCOLO_COMUNICACION.xsd";
        String archivoConError = ".\\XML_PROTOCOLO_COMUNICACION_COMUN_ERROR.xml";
        Document xml = archivoToDocument(archivoXML);
        //Prueba desde archivos
        System.out.println("Archivo XML válido: ");
        System.out.println(validarXMLConEsquema(archivoXML, archivoXSD));
        //Prueba desde Document
        System.out.println("Archivo XML válido desde Document: ");
        System.out.println(validarXMLConEsquema(xml, archivoXSD));
        //Prueba con archivo con error
        // Hay 3 errores en el archivo XML_PROTOCOLO_COMUNICACION_COMUN_ERROR.xml
        //1. se esperaba un integer y se encontró un string en type_protocol
        //2. se esperaba un integer y se encontró un string en owned_card_cost
        //3. el elementeo offered_cards no esta completo se esperaba un elmento offered_card
        System.out.println("Archivo XML con error: ");
        System.out.println(validarXMLConEsquema(archivoConError, archivoXSD));
        // De esta forma es facil validar un xml e indicar al usuario si es valido o no y porque
        }
}
