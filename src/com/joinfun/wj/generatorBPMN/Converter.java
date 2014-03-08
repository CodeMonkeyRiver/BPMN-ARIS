package com.joinfun.wj.generatorBPMN;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.joinfun.wj.common.Constant;
import com.joinfun.wj.common.Utils;
import com.joinfun.wj.entity.XmlEnd;
import com.joinfun.wj.entity.XmlIntermediateEvent;
import com.joinfun.wj.entity.XmlManualTask;
import com.joinfun.wj.entity.XmlStart;
import com.joinfun.wj.entity.XmlUserTask;

public class Converter {
	
	Document doc = null;
	
	Utils util = new Utils();

	public XmlStart getStartEvent(Document doc){
		//TODO 验证根据节点名的取法是否正确
		NodeList nodeList = doc.getElementsByTagName("ObjOcc");						//nodeList为在ObjOcc标签下所有节点
		Node startNode = util.getNodeByName(nodeList, Constant.START_EVENT);
		//String guid = util.getNodeByName(startNode.getChildNodes()).getTextContent();
		String guid = util.getNodeByTagName(startNode, "GUID").getTextContent();
	//	String symbolNum = util.getSymbolNumByGuid(doc, guid);
		String eventName = util.getNameByGuid(doc, guid).trim();
		Node cxnOccNode = util.getNodeByTagName(startNode, "CxnOcc");
		Node endNode = util.getNodeByTagName(cxnOccNode, "CxnEnd");
		String pointToGuid = endNode.getTextContent().trim();
		Node posNode = util.getNodeByTagName(startNode, "Position");
		Element posElement = (Element)posNode;
		Integer positionX = Integer.parseInt(posElement.getAttribute("Pos.X"));
		Integer positionY = Integer.parseInt(posElement.getAttribute("Pos.Y"));
		
		XmlStart start = new XmlStart();
		start.setStartEventId(guid);
		//start.setSymbolNum(symbolNum);
		start.setPointToGuid(pointToGuid);
		start.setPositionX(positionX);
		start.setPositionY(positionY);
		start.setStartEventName(eventName);
		start.setAttrValue(eventName);
		System.out.println(start.toString());
		//System.out.println("symbolNum"+symbolNum);
		System.out.println("point to:" + pointToGuid);
		return start;
	}
	
	public List<XmlIntermediateEvent> getIntermediateEvents(Document doc){
		NodeList nodeList = doc.getElementsByTagName("ObjOcc");
		List<Node> middleNodes = util.getNodesByName(nodeList, Constant.MIDDEN_EVENT);
		List<XmlIntermediateEvent> middleEvents = new ArrayList<XmlIntermediateEvent>();
		for(Node middleNode : middleNodes){
			String guid = util.getNodeByTagName(middleNode, "GUID").getTextContent();
			//String symbolNum = util.getSymbolNumByGuid(doc, guid);
			String eventName = util.getNameByGuid(doc, guid).trim();
			Node cxnOccNode = util.getNodeByTagName(middleNode, "CxnOcc");
			Node endNode = util.getNodeByTagName(cxnOccNode, "CxnEnd");
			String pointToGuid = endNode.getTextContent().trim();
			Node posNode = util.getNodeByTagName(middleNode, "Position");
			Element posElement = (Element)posNode;
			Integer positionX = Integer.parseInt(posElement.getAttribute("Pos.X"));
			Integer positionY = Integer.parseInt(posElement.getAttribute("Pos.Y"));
			
			XmlIntermediateEvent  middleEvent = new XmlIntermediateEvent();
			middleEvent.setIntermediateEventId(guid);
			middleEvent.setIntermediateEventName(eventName);
			//manualTask.setManualTaskName(symbolNum);
			middleEvent.setPointToGuid(pointToGuid);
			middleEvent.setPositionX(positionX);
			middleEvent.setPositionY(positionY);
			middleEvents.add(middleEvent);
			System.out.println(middleEvent.toString());
		}
		System.out.println("manual size = " + middleEvents.size());
		return middleEvents;
	}
	
	public XmlEnd getEndEvent(Document doc){
		NodeList nodeList = doc.getElementsByTagName("ObjOcc");						//nodeList为在ObjOcc标签下所有节点
		Node endNode = util.getNodeByName(nodeList, Constant.END_EVENT);
		
		String guid = util.getNodeByTagName(endNode, "GUID").getTextContent();
	//	String symbolNum = util.getSymbolNumByGuid(doc, guid);
		String eventName = util.getNameByGuid(doc, guid).trim();
		Node posNode = util.getNodeByTagName(endNode, "Position");
		Element posElement = (Element)posNode;
		Integer positionX = Integer.parseInt(posElement.getAttribute("Pos.X"));
		Integer positionY = Integer.parseInt(posElement.getAttribute("Pos.Y"));
		
		XmlEnd end = new XmlEnd();
		end.setEndEventId(guid);
		end.setAttrValue(eventName);
		end.setEndEventName(eventName);
		//end.setSymbolNum(symbolNum);
		end.setPositionX(positionX);
		end.setPositionY(positionY);
		System.out.println(end.toString());
		return end;
	}
	
	//获取UserTask的集合
	public List<XmlUserTask> getUserTasks(Document doc){
		NodeList nodeList = doc.getElementsByTagName("ObjOcc");
		List<Node> userTasks = util.getNodesByName(nodeList, Constant.USER_TASK);
		List<XmlUserTask> xmlUserTasks = new ArrayList<XmlUserTask>();
		for(Node userTaskNode : userTasks){
			String guid = util.getNodeByTagName(userTaskNode, "GUID").getTextContent();
			//String symbolNum = util.getSymbolNumByGuid(doc, guid);
			String eventName = util.getNameByGuid(doc, guid).trim();
			Node cxnOccNode = util.getNodeByTagName(userTaskNode, "CxnOcc");
			Node endNode = util.getNodeByTagName(cxnOccNode, "CxnEnd");
			String pointToGuid = endNode.getTextContent().trim();
			Node posNode = util.getNodeByTagName(userTaskNode, "Position");
			Element posElement = (Element)posNode;
			Integer positionX = Integer.parseInt(posElement.getAttribute("Pos.X"));
			Integer positionY = Integer.parseInt(posElement.getAttribute("Pos.Y"));
			
			XmlUserTask userTask = new XmlUserTask();
			userTask.setUserTaskName(eventName);
			userTask.setUserTaskId(guid);
		//	userTask.setUserTaskName(symbolNum);
			userTask.setPointToGuid(pointToGuid);
			userTask.setPositionX(positionX);
			userTask.setPositionY(positionY);
			System.out.println(userTask.toString());
			xmlUserTasks.add(userTask);
		}
		System.out.println("user size = " + xmlUserTasks.size());
		return xmlUserTasks;
	}
	
	public List<XmlManualTask> getManualTasks(Document doc){
		NodeList nodeList = doc.getElementsByTagName("ObjOcc");
		List<Node> manualTasks = util.getNodesByName(nodeList, Constant.MANUAL_TASK);
		List<XmlManualTask> xmlManualTasks = new ArrayList<XmlManualTask>();
		for(Node manualTaskNode : manualTasks){
			String guid = util.getNodeByTagName(manualTaskNode, "GUID").getTextContent();
			//String symbolNum = util.getSymbolNumByGuid(doc, guid);
			String eventName = util.getNameByGuid(doc, guid).trim();
			Node cxnOccNode = util.getNodeByTagName(manualTaskNode, "CxnOcc");
			Node endNode = util.getNodeByTagName(cxnOccNode, "CxnEnd");
			String pointToGuid = endNode.getTextContent().trim();
			Node posNode = util.getNodeByTagName(manualTaskNode, "Position");
			Element posElement = (Element)posNode;
			Integer positionX = Integer.parseInt(posElement.getAttribute("Pos.X"));
			Integer positionY = Integer.parseInt(posElement.getAttribute("Pos.Y"));
			
			XmlManualTask manualTask = new XmlManualTask();
			manualTask.setManualTaskId(guid);
			manualTask.setManualTaskName(eventName);
			//manualTask.setManualTaskName(symbolNum);
			manualTask.setPointToGuid(pointToGuid);
			manualTask.setPositionX(positionX);
			manualTask.setPositionY(positionY);
			xmlManualTasks.add(manualTask);
			System.out.println(manualTask.toString());
		}
		System.out.println("manual size = " + xmlManualTasks.size());
		return xmlManualTasks;
	}
	
	
	//测试方法
    private static void findNode(Document document) {
        NodeList nodeList = document.getElementsByTagName("ObjOcc");
        System.err.println(nodeList.getLength());
        Node node = nodeList.item(0); 
        Element element = (Element)node;
        System.err.println(element.getElementsByTagName("GUID").item(0).getTextContent());
        
        //NodeList childList = nodeList.item(0).getChildNodes();	//开始事件
        System.out.println(node.getAttributes().item(0).getNodeName());
        System.out.println(node.getAttributes().item(0).getNodeValue());
    }
    
    //测试
    public static void main(String args[]) throws ParserConfigurationException, SAXException, IOException  {
		DocumentBuilderFactory factory =  DocumentBuilderFactory.newInstance();
		//清除空格 true
		factory.setIgnoringElementContentWhitespace(true);	
	    
	    //获取解析器
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    //把xml文档输入到解析器中,并获取xml文档对应的document对象
	    Document document = builder.parse(new File( Constant.DIRECTORY + Constant.XML_SOURCE_PATH));
	    System.out.println(document.getDocumentURI());
	    findNode(document);
	    //new Converter().getStartEvent(document);
	    //new Converter().getModelName(document);
	    //new Converter().getManualTasks(document);
	  	//new Converter().getUserTasks(document);
	    //new Converter().getManualTasks(document);
	    new Converter().getIntermediateEvents(document);
	}
    
    public Document getDocument() throws ParserConfigurationException, SAXException, IOException{
    	DocumentBuilderFactory factory =  DocumentBuilderFactory.newInstance();
		//清除空格 true
		factory.setIgnoringElementContentWhitespace(true);	
	    //获取解析器
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    //把xml文档输入到解析器中,并获取xml文档对应的document对象
	    Document doc = builder.parse(new File( Constant.DIRECTORY + Constant.XML_SOURCE_PATH));
	    
	    return doc;
    }

    public String getModelName(Document doc){
    	String modelName = "";
    	try{
    		NodeList modelNode = doc.getElementsByTagName("Model");
    		modelName = ((Element)modelNode.item(0)).getAttribute("Model.Type");
    	}catch(Exception e){
    		System.err.println("Model name not found");
    		e.printStackTrace();
    	}
    	return modelName;
    }
}
