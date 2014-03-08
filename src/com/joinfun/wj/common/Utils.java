package com.joinfun.wj.common;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Utils {
	
	
	//在形如<ObjOcc SymbolNum="ST_BPMN_开始事件">这样的标签中取"ST_BPMN_开始事件"的值
	public Node getNodeByName(NodeList nodeList,String nodeName){
		Node node = null;
		for(int i = 0; i < nodeList.getLength(); i++){
			Node temp = nodeList.item(i);
			if(temp.getAttributes().item(0).getNodeValue().equals(nodeName)){
				node = temp;
			}
		}
		return node;
	}
	
	/**
	 * 方法基本作用类似getNodeByName，但取出的为集合，为usertask与mannualtask使用
	 * @param nodeList
	 * @param nodeName
	 * @return
	 */
	public List<Node> getNodesByName(NodeList nodeList,String nodeName){
		List<Node> nodes = new ArrayList<Node>();
		for(int i = 0; i < nodeList.getLength(); i++){
			Node temp = nodeList.item(i);
			if(temp.getAttributes().item(0).getNodeValue().equals(nodeName)){
				nodes.add(temp);
			}
		}
		return nodes;
	}
	
	/**
	 * 在nodelist中取某个tag name为指定值的node
	 * @param temp
	 * @param tagName
	 * @return Node
	 */
	public Node getNodeByTagName(Node temp,String tagName){
		Element element = (Element)temp;
		Node node = element.getElementsByTagName(tagName).item(0);
		return node;
	}
	
	/**
	 * 根据guid找出doc中objdef中为该guid的symbolNum
	 * @param doc
	 * @param guid
	 * @return String
	 */
	public String getSymbolNumByGuid(Document doc,String guid){
		NodeList nodeList = doc.getElementsByTagName("ObjDef");
		Element element = null;
		String symbolNum = "";
		for(int i = 0; i < nodeList.getLength(); i++){
			element = (Element)nodeList.item(i);
			Node guidNode = element.getElementsByTagName("GUID").item(0);
			if(guidNode.getTextContent().equals(guid)){
				System.err.println("length:"+(String)element.getAttribute("SymbolNum"));
				symbolNum = element.getAttribute("SymbolNum");
			}
		}
		return symbolNum;
	}
	/**
	 * 根据guid查找该节点的name
	 * @param doc
	 * @param guid
	 * @return String
	 */
	public String getNameByGuid(Document doc,String guid){
		NodeList nodeList = doc.getElementsByTagName("ObjDef");
		Element element = null;
		String name = "";
		for(int i = 0; i < nodeList.getLength(); i++){
			element = (Element)nodeList.item(i);
			Node guidNode = element.getElementsByTagName("GUID").item(0);
			if(guidNode.getTextContent().equals(guid)){
				name = getNodeByTagName(nodeList.item(i), "AttrValue").getTextContent();
			}
		}
		return name;
	}
	
	/**
	 * 以下方法为BPMN转换为XML文件时使用
	 */
	public String[] getPostitionById(Element element,String id){
		String[] positions = new String[2];
		Element posElement =  (Element)element.getElementsByTagName("ns5:Position").item(0);
		String posX = posElement.getAttribute("x");
		String posY = posElement.getAttribute("y");
		positions[0] = posX;
		positions[1] = posY;
		return positions;
	}
	
	public String getTargetGuidById(Document doc,String id){
		NodeList sequenceFlows = doc.getElementsByTagName("sequenceFlow");
		String targetId = "";
		for(int i = 0; i < sequenceFlows.getLength(); i++){
			Element temp = (Element)sequenceFlows.item(i);
			if(temp.getAttribute("sourceRef").equals(id)){
				targetId = temp.getAttribute("targetRef");
			}
		}
		return targetId;
	}
	
	//转换GUID中"_"与"-"使用
	public String transer(String guid){
		StackTraceElement[] stacks = new Throwable().getStackTrace();
		String classFullName = stacks[2].getClassName();
		String[] temp = classFullName.split("\\.");
		String className = temp[temp.length-1];
		if("Replacer".equals(className)){
			guid = guid.replace("-", "_");
		}else if("Genenator4XML".equals("className")){
			guid = guid.replace("_", "-");
		}
		return guid;
	}
	
	
}
