package com.joinfun.wj.generatorXML;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.joinfun.wj.common.Constant;
import com.joinfun.wj.common.Utils;
import com.joinfun.wj.entity.XmlEnd;
import com.joinfun.wj.entity.XmlIntermediateEvent;
import com.joinfun.wj.entity.XmlManualTask;
import com.joinfun.wj.entity.XmlStart;
import com.joinfun.wj.entity.XmlUserTask;

public class Analyser {
	Utils util = new Utils();
	
	//获取要解析的BPMN文档对象
	public Document getDocument() throws ParserConfigurationException, SAXException, IOException{
    	DocumentBuilderFactory factory =  DocumentBuilderFactory.newInstance();
		//清除空格 true
		factory.setIgnoringElementContentWhitespace(true);	
	    //获取解析器
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    //把bpmn文档输入到解析器中,并获取bpmn文档对应的document对象
	    Document doc = builder.parse(new File( Constant.DIRECTORY + Constant.BPMN_SOURCE_PATH));
	    
	    return doc;
    }
	
	public XmlStart getStartEvent(Document doc){
		NodeList nodeList = doc.getElementsByTagName("startEvent");		
		Element startElement = (Element)nodeList.item(0);
		String name = startElement.getAttribute("name");
		String id = startElement.getAttribute("id");
		System.out.println("name"+name + "id" + id);
		Integer posX = Integer.parseInt(util.getPostitionById(startElement, id)[0]);
		Integer posY = Integer.parseInt(util.getPostitionById(startElement, id)[1]);
		//Node startNode = util.getNodeByName(nodeList, Constant.START_EVENT);
		String targetId = util.getTargetGuidById(doc, id);
		XmlStart start = new XmlStart();
		start.setStartEventId(id);
		start.setStartEventName(name);
		start.setAttrValue(name);
		start.setPositionX(posX);
		start.setPositionY(posY);
		start.setPointToGuid(targetId);
		System.out.println(start.toString());
		return start;
	}
	
	public XmlEnd getEndEvent(Document doc){
		NodeList nodeList = doc.getElementsByTagName("endEvent");
		Element endElement = (Element)nodeList.item(0);
		String name = endElement.getAttribute("name");
		String id = endElement.getAttribute("id");
		Integer posX = Integer.parseInt(util.getPostitionById(endElement, id)[0]);
		Integer posY = Integer.parseInt(util.getPostitionById(endElement, id)[1]);
		
		XmlEnd end = new XmlEnd();
		end.setAttrValue(name);
		end.setEndEventName(name);
		end.setEndEventId(id);
		end.setPositionX(posX);
		end.setPositionY(posY);
		System.out.println(end.toString());
		return end;
	}
	
	public List<XmlUserTask> getUserTasks(Document doc){
		NodeList nodeList = doc.getElementsByTagName("userTask");
		List<XmlUserTask> xmlUserTasks = new ArrayList<XmlUserTask>();
		for(int i = 0; i < nodeList.getLength(); i++){
			Element temp = (Element)nodeList.item(i);
			String name = temp.getAttribute("name");
			String id = temp.getAttribute("id");
			Integer posX = Integer.parseInt(util.getPostitionById(temp, id)[0]);
			Integer posY = Integer.parseInt(util.getPostitionById(temp, id)[1]);
			String targetId = util.getTargetGuidById(doc, id);
			
			XmlUserTask userTask = new XmlUserTask();
			userTask.setUserTaskId(id);
			userTask.setUserTaskName(name);
			userTask.setPositionX(posX);
			userTask.setPositionY(posY);
			userTask.setPointToGuid(targetId);
			System.out.println(userTask.toString());
			xmlUserTasks.add(userTask);
		}
		return xmlUserTasks;
	}
	
	public List<XmlManualTask> getManualTasks(Document doc){
		NodeList nodeList = doc.getElementsByTagName("manualTask");
		List<XmlManualTask> xmlManualTasks = new ArrayList<XmlManualTask>();
		for(int i = 0; i < nodeList.getLength(); i++){
			Element temp = (Element)nodeList.item(i);
			String name = temp.getAttribute("name");
			String id = temp.getAttribute("id");
			Integer posX = Integer.parseInt(util.getPostitionById(temp, id)[0]);
			Integer posY = Integer.parseInt(util.getPostitionById(temp, id)[1]);
			String targetId = util.getTargetGuidById(doc, id);
			
			XmlManualTask manualTask = new XmlManualTask();
			manualTask.setManualTaskId(id);
			manualTask.setManualTaskName(name);
			manualTask.setPositionX(posX);
			manualTask.setPositionY(posY);
			manualTask.setPointToGuid(targetId);
			System.out.println(manualTask.toString());
			xmlManualTasks.add(manualTask);
		}
		return xmlManualTasks;
	}
	
	public List<XmlIntermediateEvent> getMiddleEvents(Document doc){
		NodeList nodeList = doc.getElementsByTagName("intermediateCatchEvent");
		List<XmlIntermediateEvent> xmlMiddleEvents = new ArrayList<XmlIntermediateEvent>();
		for(int i = 0; i < nodeList.getLength(); i++){
			Element temp = (Element)nodeList.item(i);
			String name = temp.getAttribute("name");
			String id = temp.getAttribute("id");
			Integer posX = Integer.parseInt(util.getPostitionById(temp, id)[0]);
			Integer posY = Integer.parseInt(util.getPostitionById(temp, id)[1]);
			String targetId = util.getTargetGuidById(doc, id);
			
			XmlIntermediateEvent middleEvent = new XmlIntermediateEvent();
			middleEvent.setIntermediateEventId(id);
			middleEvent.setIntermediateEventName(name);
			middleEvent.setPositionX(posX);
			middleEvent.setPositionY(posY);
			middleEvent.setPointToGuid(targetId);
			System.out.println(middleEvent.toString());
			xmlMiddleEvents.add(middleEvent);
		}
		return xmlMiddleEvents;
	}
	
	public String getModelName(Document doc){
    	String modelName = "";
    	try{
    		NodeList modelNode = doc.getElementsByTagName("process");
    		modelName = ((Element)modelNode.item(0)).getAttribute("name");
    	}catch(Exception e){
    		System.err.println("process name not found");
    		e.printStackTrace();
    	}
    	return modelName;
    }
	
	/*public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException{
		Document doc = new Analyser().getDocument();
		new Analyser().getStartEvent(doc);
		new Analyser().getEndEvent(doc);
		new Analyser().getUserTasks(doc);
		new Analyser().getManualTasks(doc);
		new Analyser().getMiddleEvents(doc);
	}*/
	
}
