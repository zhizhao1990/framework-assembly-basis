package com.cmc.common.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.cmc.model.animal.Animal;

public class XMLParser {

	private static final String XML_FILE_ADDR = "./src/main/resources/xml/demo.xml";

	/**
	 * 解析方法
	 * @param xmlFileAddr XML文件地址
	 * @param nodeName 节点名称
	 * @return
	 */
	public static List<Animal> parse(String xmlFileAddr, String nodeName) {
		List<Animal> animals = new ArrayList<Animal>();
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		NodeList nodes = null;
		try {
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(xmlFileAddr);
			nodes = document.getElementsByTagName(nodeName);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		if (null != nodes) {
			for (int i = 0; i < nodes.getLength(); i++) {
				Node item = nodes.item(i);
				Animal animal = new Animal();
				animal.setId(((Element) item).getAttribute("id"));
				for (Node child = item.getFirstChild(); child != null; child = child.getNextSibling()) {
					if (Node.ELEMENT_NODE == child.getNodeType()) {
						String name = child.getNodeName();
						String value = child.getFirstChild().getNodeValue();
						if ("name".equals(name)) {
							animal.setName(value);
						} else if ("love".equals(name)) {
							animal.setLove(value);
						} else if ("strain".equals(name)) {
							animal.setStrain(value);
						} else if ("sex".equals(name)) {
							animal.setSex(value);
						} else if ("health".equals(name)) {
							animal.setHealth(value);
						}
					}
				}
				animals.add(animal);
			}
		}
		return animals;
	}

	public static void main(String[] args) {
		List<Animal> dogs = XMLParser.parse(XML_FILE_ADDR, "dog");
		Iterator<Animal> iDogs = dogs.iterator();
		while (iDogs.hasNext()) {
			Animal animal = iDogs.next();
			System.out.println(animal.toString());
		}
	}

}