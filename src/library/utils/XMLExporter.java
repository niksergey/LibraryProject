package library.utils;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * Created by sergey on 11.04.17.
 */
public class XMLExporter {
    public static void classMetaInfoToXML(String rootName, OutputStream outputStream, Object obj) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element rootElement = doc.createElement(rootName);
            doc.appendChild(rootElement);

            appendToNode(doc, rootElement, obj);

            writeXmlToOutputStream(doc, outputStream);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    private static void appendToNode(Document doc, Element nodeElement, Object obj) {
        Element fieldsElement = doc.createElement("Fields");
        nodeElement.appendChild(fieldsElement);

        try {
            for (Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Element fElement = doc.createElement("Field");

                Attr nameAttr = doc.createAttribute("name");
                nameAttr.setValue(field.getName());
                fElement.setAttributeNode(nameAttr);

                Attr typeAttr = doc.createAttribute("type");
                typeAttr.setValue(field.getType().getName());
                fElement.setAttributeNode(typeAttr);

                Object objField = field.get(obj);
                if (objField != null) {
                    fElement.appendChild(doc.createTextNode(objField.toString()));
                    fieldsElement.appendChild(fElement);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Element methodElement = doc.createElement("Methods");
        nodeElement.appendChild(methodElement);

        for (Method method : obj.getClass().getMethods()) {
            method.setAccessible(true);
            Element metElem = doc.createElement("Method");
            methodElement.appendChild(metElem);

            for(Parameter param: method.getParameters()) {
                Element paramElem = doc.createElement("parameter");
                metElem.appendChild(paramElem);

                Attr metAttr = doc.createAttribute("type");
                metAttr.setValue(param.getType().getName());
                paramElem.setAttributeNode(metAttr);
            }

            Attr nameAttr = doc.createAttribute("name");
            nameAttr.setValue(method.getName());
            metElem.setAttributeNode(nameAttr);

            Attr modifAttr = doc.createAttribute("isAccessible");
            modifAttr.setValue(Boolean.toString(method.isAccessible()));
            metElem.setAttributeNode(modifAttr);

            Attr returnTypeAttr = doc.createAttribute("returnType");
            returnTypeAttr.setValue(method.getReturnType().getName());
            metElem.setAttributeNode(returnTypeAttr);
        }
    }

    private static void writeXmlToOutputStream(Document doc, OutputStream outputStream) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            StreamResult result = new StreamResult(outputStream);

            transformer.transform(source, result);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
