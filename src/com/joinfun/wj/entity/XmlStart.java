package com.joinfun.wj.entity;

import com.joinfun.wj.common.Utils;

public class XmlStart {
	
	private String startEventName ;
	private String startEventId;		//GUID
	private String symbolNum;			//SymbolNum
	private String attrValue;			//AttrValue对应到event的name
	private Integer positionX;			//起始点x位置
	private Integer positionY;			//起始点y位置
	private String pointToGuid;		//指向的节点GUID
	
	public String getStartEventName() {
		return startEventName;
	}
	public void setStartEventName(String startEventName) {
		this.startEventName = startEventName;
	}
	public String getStartEventId() {
		/*StackTraceElement[] stacks = new Throwable().getStackTrace();
		String classFullName = stacks[1].getClassName();
		String[] temp = classFullName.split("\\.");
		String className = temp[temp.length-1];
		System.out.println("className = " + className);
		if("Replacer".equals(className)){
			startEventId = startEventId.replace("-", "_");
		}else if("Genenator4XML".equals("className")){
			startEventId = startEventId.replace("_", "-");
		}*/
			Utils util = new Utils();
			startEventId = util.transer(startEventId);
		return startEventId;
	}
	public void setStartEventId(String startEventId) {
		this.startEventId = startEventId;
	}
	public String getSymbolNum() {
		return symbolNum;
	}
	public void setSymbolNum(String symbolNum) {
		this.symbolNum = symbolNum;
	}
	public String getAttrValue() {
		return attrValue;
	}
	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}
	public Integer getPositionX() {
		return positionX;
	}
	public void setPositionX(Integer positionX) {
		this.positionX = positionX;
	}
	public Integer getPositionY() {
		return positionY;
	}
	public void setPositionY(Integer positionY) {
		this.positionY = positionY;
	}
	public String getPointToGuid() {
		Utils util = new Utils();
		pointToGuid = util.transer(pointToGuid);
		return pointToGuid;
	}
	public void setPointToGuid(String pointToGuid) {
		this.pointToGuid = pointToGuid;
	}
	
	@Override
	public String toString() {
		return "XmlStart [startEventName=" + startEventName + ", startEventId="
				+ startEventId + ", symbolNum=" + symbolNum + ", attrValue="
				+ attrValue + ", positionX=" + positionX + ", positionY="
				+ positionY + ", pointToGuid=" + pointToGuid + "]";
	}
}
