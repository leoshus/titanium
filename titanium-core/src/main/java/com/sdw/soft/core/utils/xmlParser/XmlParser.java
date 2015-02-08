/**
 * @Date 2014年9月11日
 * @version 1.0.0
 * Copyright (c) 2014
 */
package com.sdw.soft.core.utils.xmlParser;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 * @author syd
 */
public class XmlParser {

	public static void main(String[] args){
		new XmlParser().javaxpParser();
	}
	
	public void javaxpParser(){
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new FileInputStream(new File("D:/a.xml")));
			NodeList nl = document.getDocumentElement().getElementsByTagName("name");
			Node item = nl.item(0);
			String textContent = item.getTextContent();
			System.out.println("-----------"+textContent);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
