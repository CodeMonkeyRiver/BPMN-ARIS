package com.joinfun.wj.test;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class AnalyseTableUtils {

	public Node getRootNode(Document doc){
		NodeList nodeList = doc.getElementsByTagName("root");
		Node node = nodeList.item(0);
		return node;
	}

}
