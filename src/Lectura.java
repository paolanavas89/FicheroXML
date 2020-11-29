
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Lectura {

	public static void main(String[] args) {
		
		
		try {
			File fichero = new File ("FichEmpleados.xml");
			DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
			DocumentBuilder b = fac.newDocumentBuilder();
			
			Document doc = b.parse(fichero);
			
			//Quita los espacios en blanco redundantes, no significativos
			doc.getDocumentElement().normalize();
			
			//LECTURA CONOCIENDO LA ESTRUCTURA DEL DOCUMENTO
			
			//Se obtiene la etiqueta empleados(raiz)
			System.out.println("Raiz: "+doc.getDocumentElement().getNodeName());
			
			//Lista de nodos 
			NodeList emp = doc.getElementsByTagName("empleado");
			
			//Obtiene cuantos nodos tiene empleados
			int numNodos = emp.getLength();
			System.out.println("Número de empleados: "+numNodos);
			
			//Recorrer la lista
			/*for(int i=0;i<numNodos;i++) {
				Node empleado = emp.item(i);
				System.out.println("EMPLEADO "+(i+1));
				//Preguntamos si el nodo es de tipo Element
				if(empleado.getNodeType()==Node.ELEMENT_NODE) {
					//Convertimos en tipo Element el nodo
					Element elem = (Element)empleado;
					System.out.println("Id: "+elem.getElementsByTagName("id").item(0).getTextContent());

					System.out.println("Apellido 1: "+elem.getElementsByTagName("apellido1").item(0).getTextContent());
					System.out.println("Apellido 2: "+elem.getElementsByTagName("apellido2").item(0).getTextContent());

					System.out.println("Departamento: "+elem.getElementsByTagName("dep").item(0).getTextContent());
					System.out.println("Salario: "+elem.getElementsByTagName("salario").item(0).getTextContent());
				}
				
			}*/
			
			//LECTURA SI NO CONOCEMOS LA ESTRUCTURA
			//Recorrer lista
			for(int i=0;i<numNodos;i++) {
				Node empleado = emp.item(i);
				//Preguntamos si el nodo es de tipo Element
				if(empleado.getNodeType()==Node.ELEMENT_NODE) {
					System.out.println("EMPLEADO "+(i+1));
					//Convertimos en tipo Element el nodo
					Element elem = (Element)empleado;
					//Crear otra lista con las subetiquetas
					NodeList subEtiq = elem.getChildNodes();
					
					int numSub = subEtiq.getLength();
					for(int j=0;j<numSub;j++) {
						Node sub = subEtiq.item(j);
						if(sub.getNodeType()==Node.ELEMENT_NODE) {
							//Obtener el nombre de la etiqueta y el texto o valor que contiene una etiqueta
							
							System.out.print(sub.getNodeName()+": "+sub.getTextContent());
							
						}
					}
					System.out.print("\n");
				}
			}
			
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			
		} catch (SAXException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
