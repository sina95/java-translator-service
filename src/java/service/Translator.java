/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.FileNotFoundException;
import java.io.InputStream;
import javax.ws.rs.*;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 *
 * @author HP
 */
@Path("/Translator/{word}/{language1}/{language2}")
public class Translator {

    @GET
    public String translate(@PathParam("word") String word, @PathParam("language1") String language1, @PathParam("language2") String language2) throws XPathExpressionException, FileNotFoundException {
        String output = null;
        XPathFactory factory = XPathFactory.newInstance();
        XPath path = factory.newXPath();
        XPathExpression xPE;

        InputSource source = new InputSource(getFileFromResourceAsStream("dictionary.xml"));

        if ("en".equals(language1)) {
            xPE = path.compile("//word[@en='" + word.trim().toLowerCase() + "']");
        } else {

            xPE = path.compile("//word[text()='" + word.trim().toLowerCase() + "']");

        }
        Object result = xPE.evaluate(source, XPathConstants.NODESET);
        NodeList nList = (NodeList) result;
        System.out.println(nList);

        if (nList.getLength() < 1) {

            output = "Rijec " + word + " ne postoji u nasem rjecniku";
        } else {
            for (int i = 0; i < nList.getLength(); i++) {
                if ("en".equals(language1)) {
                    output = "Rijec " + word + " se prevodi kao: " + nList.item(i).getTextContent();
                } else {
                    Node currentItem = nList.item(i);
                    String key = currentItem.getAttributes().getNamedItem("en").getNodeValue();

                    output = "Rijec " + word + " se prevodi kao: " + key;
                }
            }
        }
        return output;
    }

    private InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }

}
