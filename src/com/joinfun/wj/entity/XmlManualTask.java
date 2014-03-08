package com.joinfun.wj.entity;

import com.joinfun.wj.common.Utils;

public class XmlManualTask {
	
	private String manualTaskName;
	private String manualTaskId;
	private Integer positionX;
	private Integer positionY;
	private String pointToGuid;
	
	public String getManualTaskName() {
		return manualTaskName;
	}
	
	public void setManualTaskName(String manualTaskName) {
		this.manualTaskName = manualTaskName;
	}
	
	public String getManualTaskId() {
		Utils util = new Utils();
		manualTaskId = util.transer(manualTaskId);
		return manualTaskId;
	}
	
	public void setManualTaskId(String manualTaskId) {
		this.manualTaskId = manualTaskId;
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
		return "XmlManualTask [manualTaskName=" + manualTaskName
				+ ", manualTaskId=" + manualTaskId + ", positionX=" + positionX
				+ ", positionY=" + positionY + ", pointToGuid=" + pointToGuid
				+ "]";
	}
}
