package com.joinfun.wj.entity;

import com.joinfun.wj.common.Utils;

public class XmlIntermediateEvent {
	
	private String IntermediateEventName;
	private String IntermediateEventId;
	private Integer positionX;
	private Integer positionY;
	private String pointToGuid;
	
	public String getIntermediateEventName() {
		return IntermediateEventName;
	}
	public void setIntermediateEventName(String intermediateEventName) {
		IntermediateEventName = intermediateEventName;
	}
	public String getIntermediateEventId() {
		Utils util = new Utils();
		IntermediateEventId = util.transer(IntermediateEventId);
		return IntermediateEventId;
	}
	public void setIntermediateEventId(String intermediateEventId) {
		IntermediateEventId = intermediateEventId;
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
		return "XmlIntermediateEvent [IntermediateEventName="
				+ IntermediateEventName + ", IntermediateEventId="
				+ IntermediateEventId + ", positionX=" + positionX
				+ ", positionY=" + positionY + ", pointToGuid=" + pointToGuid
				+ "]";
	}
	
}
