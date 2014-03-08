package com.joinfun.wj.test;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.joinfun.wj.common.Constant;

public class TestAnalyseTable {
	
	AnalyseTableUtils util = new AnalyseTableUtils();

	public Document getDocument() throws ParserConfigurationException, SAXException, IOException{
    	DocumentBuilderFactory factory =  DocumentBuilderFactory.newInstance();
		//����ո� true
		factory.setIgnoringElementContentWhitespace(true);	
	    //��ȡ������
	    DocumentBuilder builder = factory.newDocumentBuilder();
	   
	    Document doc = builder.parse(new File( Constant.DIRECTORY + "\\temp.xml"));
	    
	    return doc;
    }
	
	public String analyseTabel(Document doc){
		Node root = util.getRootNode(doc);
		System.out.println(root.getNodeName());
		return null;
	}
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		TestAnalyseTable test = new TestAnalyseTable();
		Document doc = test.getDocument();
		test.analyseTabel(doc);
	}
	
	

}
