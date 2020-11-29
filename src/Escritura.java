import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;


public class Escritura {

	public static void main(String[] args) throws IOException {
		
		try {
			/*File fichero = new File("FicheroEmpleados.dat");
			
			RandomAccessFile fich = new RandomAccessFile(fichero,"r");*/
			
			//Crear un fichero XML
			DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
			
			DocumentBuilder builder = fact.newDocumentBuilder();
			
			DOMImplementation impl = builder.getDOMImplementation();
			
			//Establecer raiz
			Document doc = impl.createDocument(null,"Empleados",null);
			
			doc.setXmlVersion("1.0");
			
			
			//Llamar al metodo para crear elementos
			añadirElemento(doc, "empleado", "1","1", "Sanz","Lopez", "15", "1000");
			añadirElemento(doc, "empleado", "2", "2","Perez","Rodriguez", "20", "1500");
						
			
			//Cerrar ficheroXML
			Source source = new DOMSource(doc);
			Result result = new StreamResult (new File ("FichEmpleados.xml")); //Si no quieres crear un fichero, poner System.out
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(source,result);
			
			
			
			System.out.println("Fin de programa");
			
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
			
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
			
		} catch (TransformerException e) {
			e.printStackTrace();
			
		} 
	}
	
	public static void añadirElemento(Document doc, String empleado, String id, String atrib, String apellido, String apellido2, String dep, String salario) {
		//Crear los nodos 
		Element elemento=null;
		Element hijo=null;
		Text text = null;
		Element raiz=null;
		
		raiz = doc.createElement(empleado);
		doc.getDocumentElement().appendChild(raiz);
		
		elemento = doc.createElement("id");
		text = doc.createTextNode(id);
		raiz.appendChild(elemento);
		elemento.appendChild(text);
		
		//Crear un atributo en id
		Attr atr = doc.createAttribute("numero");
		atr.setValue(atrib);
		elemento.setAttributeNode(atr);
		
		elemento = doc.createElement("apellido");
		raiz.appendChild(elemento);
		
		//Hijo de apellido
		hijo = doc.createElement("apellido1");
		text = doc.createTextNode(apellido);
		elemento.appendChild(hijo);
		hijo.appendChild(text);
		
		hijo = doc.createElement("apellido2");
		text = doc.createTextNode(apellido2);
		elemento.appendChild(hijo);
		hijo.appendChild(text);
		
		elemento = doc.createElement("dep");
		text = doc.createTextNode(dep);
		raiz.appendChild(elemento);
		elemento.appendChild(text);
		
		elemento = doc.createElement("salario");
		text = doc.createTextNode(salario);
		raiz.appendChild(elemento);
		elemento.appendChild(text);
		
	}

}
