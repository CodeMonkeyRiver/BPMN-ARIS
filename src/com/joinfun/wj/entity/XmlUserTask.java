package com.joinfun.wj.entity;

import com.joinfun.wj.common.Utils;

public class XmlUserTask {
	
	private String userTaskName;
	private String userTaskId;
	private Integer positionX;
	private Integer positionY;
	private String pointToGuid;
	
	public String getUserTaskName() {
		return userTaskName;
	}
	public void setUserTaskName(String userTaskName) {
		this.userTaskName = userTaskName;
	}
	public String getUserTaskId() {
		Utils util = new Utils();
		userTaskId = util.transer(userTaskId);
		return userTaskId;
	}
	public void setUserTaskId(String userTaskId) {
		this.userTaskId = userTaskId;
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
		return "XmlUserTask [userTaskName=" + userTaskName + ", userTaskId="
				+ userTaskId + ", positionX=" + positionX + ", positionY="
				+ positionY + ", pointToGuid=" + pointToGuid + "]";
	}
}
