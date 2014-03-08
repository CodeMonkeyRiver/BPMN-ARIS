package com.joinfun.wj.entity;

import com.joinfun.wj.common.Utils;

public class XmlEnd {
	
	private String endEventName;
	private String endEventId;
	private String symbolNum;			//SymbolNum
	private String attrValue;			//AttrValue
	private Integer positionX;
	private Integer positionY;
	
	public String getEndEventName() {
		return endEventName;
	}
	public void setEndEventName(String endEventName) {
		this.endEventName = endEventName;
	}
	public String getEndEventId() {
		Utils util = new Utils();
		endEventId = util.transer(endEventId);
		return endEventId;
	}
	public void setEndEventId(String endEventId) {
		this.endEventId = endEventId;
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
	@Override
	public String toString() {
		return "XmlEnd [endEventName=" + endEventName + ", endEventId="
				+ endEventId + ", symbolNum=" + symbolNum + ", attrValue="
				+ attrValue + ", positionX=" + positionX + ", positionY="
				+ positionY + "]";
	}
}
