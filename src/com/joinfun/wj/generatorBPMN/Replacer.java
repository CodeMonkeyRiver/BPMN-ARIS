package com.joinfun.wj.generatorBPMN;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.joinfun.wj.common.Constant;
import com.joinfun.wj.common.IOUtils;
import com.joinfun.wj.entity.XmlEnd;
import com.joinfun.wj.entity.XmlIntermediateEvent;
import com.joinfun.wj.entity.XmlManualTask;
import com.joinfun.wj.entity.XmlStart;
import com.joinfun.wj.entity.XmlUserTask;

public class Replacer{
	
	private Converter converter = new Converter();
	private Document doc;
	
	public Replacer() throws ParserConfigurationException, SAXException, IOException {
		super();
		doc = converter.getDocument();
	}
	
	//用于替换SequenceFlow
	InputStream instream = this.getClass().getClassLoader().getResourceAsStream(Constant.ARIS_TEMPLATE_PATH + Constant.BPM_SEQUENCEFLOW_TEMPLATE_NAME);
	String sequenceFlowXML = new String(IOUtils.read(instream), "UTF-8");
	String sequenceFlowResult = "";
	Integer sequence = 1000;
	private static OutputStreamWriter osw;
	
	//返回替换后的字符串
	public String replaceForUserTask() throws IOException{
		InputStream instream = this.getClass().getClassLoader().getResourceAsStream(Constant.ARIS_TEMPLATE_PATH + Constant.BPM_USERTASK_TEMPLATE_NAME);
	    String oldXML = new String(IOUtils.read(instream), "UTF-8");  
	    String newXML = "";
	    for(XmlUserTask userTask : converter.getUserTasks(doc)){
	    	newXML += oldXML.replaceAll("\\$USER_TASK_ID", userTask.getUserTaskId())
	    					.replaceAll("\\$USER_TASK_NAME", userTask.getUserTaskName())
	    					.replaceAll("\\$POSITION_X", "" + userTask.getPositionX())
	    					.replaceAll("\\$POSITION_Y", "" + userTask.getPositionY()) + "\r\n";
	    	
	    	sequenceFlowResult += sequenceFlowXML.replaceAll("\\$SOURCE_GUID", "" + userTask.getUserTaskId())
					.replaceAll("\\$TARGET_GUID", "" + userTask.getPointToGuid())
					.replaceAll("\\$SEQUENCE", "sf" + (sequence++))
					.replaceAll("\\$CONSTANT", Constant.SEQUENCE_FLOW_CONSTANT) + "\r\n";
	    }
	    return newXML;
	}
	
	public String replaceForManualTask() throws IOException{
		InputStream instream = this.getClass().getClassLoader().getResourceAsStream(Constant.ARIS_TEMPLATE_PATH + Constant.BPM_MANUALTASK_TEMPLATE_NAME);
	    String oldXML = new String(IOUtils.read(instream), "UTF-8");  
	    String newXML = "";
	    for(XmlManualTask manualTask : converter.getManualTasks(doc)){
	    	newXML += oldXML.replaceAll("\\$MANUAL_TASK_ID", manualTask.getManualTaskId())
	    					.replaceAll("\\$MANUAL_TASK_NAME", manualTask.getManualTaskName())
	    					.replaceAll("\\$POSITION_X", "" + manualTask.getPositionX())
	    					.replaceAll("\\$POSITION_Y", "" + manualTask.getPositionY()) + "\r\n";
	    	
	    	sequenceFlowResult += sequenceFlowXML.replaceAll("\\$SOURCE_GUID", "" + manualTask.getManualTaskId())
					.replaceAll("\\$TARGET_GUID", "" + manualTask.getPointToGuid())
					.replaceAll("\\$SEQUENCE", "sf" + (sequence++))
					.replaceAll("\\$CONSTANT", Constant.SEQUENCE_FLOW_CONSTANT) + "\r\n";
	    }
	    return newXML;
	}
	
	public String replaceForIntermediateEvent() throws IOException{
		InputStream instream = this.getClass().getClassLoader().getResourceAsStream(Constant.ARIS_TEMPLATE_PATH + Constant.BPM_MIDDLE_TEMPLATE_NAME);
	    String oldXML = new String(IOUtils.read(instream), "UTF-8");  
	    String newXML = "";
	    for(XmlIntermediateEvent middleEvent : converter.getIntermediateEvents(doc)){
	    	newXML += oldXML.replaceAll("\\$MIDDLE_EVENT_ID", middleEvent.getIntermediateEventId())
	    					.replaceAll("\\$MIDDLE_EVENT_NAME", middleEvent.getIntermediateEventName())
	    					.replaceAll("\\$POSITION_X", "" + middleEvent.getPositionX())
	    					.replaceAll("\\$POSITION_Y", "" + middleEvent.getPositionY()) + "\r\n";
	    	
	    	sequenceFlowResult += sequenceFlowXML.replaceAll("\\$SOURCE_GUID", "" + middleEvent.getIntermediateEventId())
					.replaceAll("\\$TARGET_GUID", "" + middleEvent.getPointToGuid())
					.replaceAll("\\$SEQUENCE", "sf" + (sequence++))
					.replaceAll("\\$CONSTANT", Constant.SEQUENCE_FLOW_CONSTANT) + "\r\n";
	    }
	    return newXML;
	}
	
	public String replaceForStartEvent() throws IOException{
		InputStream instream = this.getClass().getClassLoader().getResourceAsStream(Constant.ARIS_TEMPLATE_PATH + Constant.BPM_START_TEMPLATE_NAME);
	    String oldXML = new String(IOUtils.read(instream), "UTF-8");  
	    String newXML = "";
	    XmlStart startEvent = converter.getStartEvent(doc);
	    newXML += oldXML.replaceAll("\\$START_EVENT_ID", startEvent.getStartEventId())
						.replaceAll("\\$START_EVENT_NAME", startEvent.getStartEventName())
						.replaceAll("\\$POSITION_X", "" + startEvent.getPositionX())
						.replaceAll("\\$POSITION_Y", "" + startEvent.getPositionY()) + "\r\n";
	    
	    sequenceFlowResult += sequenceFlowXML.replaceAll("\\$SOURCE_GUID", "" + startEvent.getStartEventId())
						.replaceAll("\\$TARGET_GUID", "" + startEvent.getPointToGuid())
						.replaceAll("\\$SEQUENCE", "sf" + (sequence++))
						.replaceAll("\\$CONSTANT", Constant.SEQUENCE_FLOW_CONSTANT)+"\r\n";
	    return newXML;
	}
	
	public String replaceForEndEvent() throws IOException{
		InputStream instream = this.getClass().getClassLoader().getResourceAsStream(Constant.ARIS_TEMPLATE_PATH + Constant.BPM_END_TEMPLATE_NAME);
	    String oldXML = new String(IOUtils.read(instream), "UTF-8");  
	    String newXML = "";
	    XmlEnd endEvent = converter.getEndEvent(doc);
	    newXML += oldXML.replaceAll("\\$END_EVENT_ID", endEvent.getEndEventId())
						.replaceAll("\\$END_EVENT_NAME", endEvent.getEndEventName())
						.replaceAll("\\$POSITION_X", "" + endEvent.getPositionX())
						.replaceAll("\\$POSITION_Y", "" + endEvent.getPositionY()) + "\r\n";
	    return newXML;
	}
	
	/*public String replaceForSequenceFlow() throws IOException{
		InputStream instream = this.getClass().getClassLoader().getResourceAsStream(Constant.ARIS_TEMPLATE_PATH + Constant.BPM_SEQUENCEFLOW_TEMPLATE_NAME);
		String oldXML = new String(IOUtils.read(instream), "UTF-8");  
	    String newXML = "";
	    Integer sequence = 1000;
	    XmlStart start = converter.getStartEvent(doc);
	    List<XmlUserTask> userTasks = converter.getUserTasks(doc);
	    List<XmlManualTask> manualTasks = converter.getManualTasks(doc);
	    
	    String startFlow = oldXML.replaceAll("\\$SOURCE_GUID", "" + start.getStartEventId())
	    						.replaceAll("\\$TARGET_GUID", "" + start.getPointToGuid())
	    						.replaceAll("\\$SEQUENCE", "sf" + (sequence++))
	    						.replaceAll("\\$CONSTANT", Constant.SEQUENCE_FLOW_CONSTANT);
	    
	    return null;
	}*/
	
	public String getModelName(){
		return converter.getModelName(doc);
	}
	
	//生成
	public static void main(String[] args)  throws ParserConfigurationException, SAXException, IOException{
		Replacer replacer = new Replacer();
		String outputString  = replacer.replaceForStartEvent() + replacer.replaceForUserTask() + replacer.replaceForIntermediateEvent() +
								replacer.replaceForManualTask() + replacer.replaceForEndEvent() + replacer.sequenceFlowResult;
		//System.out.println(outputString);	
		InputStream instream = replacer.getClass().getClassLoader().getResourceAsStream(Constant.ARIS_TEMPLATE_PATH + Constant.BPM_BPMN_TEMPLATE_NAME);
	    String oldXML = new String(IOUtils.read(instream), "UTF-8");
	    String out = oldXML.replaceAll("\\$MODEL_NAME", replacer.getModelName())
	    					.replaceAll("\\$CONTENT", outputString);
	    
	    //写入BPMN文件
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HHmmss");
		String date = sdf.format(new Date());
	   /* File bpmnFile = new File(Constant.DIRECTORY + "\\BPMN" + date + ".bpmn");
	    FileWriter fw;*/
	    try{
	    	FileOutputStream fos = new FileOutputStream(Constant.DIRECTORY + "\\BPMN" + date + ".bpmn"); 
	    	osw = new OutputStreamWriter(fos,"UTF-8");
	    	System.out.println(out);
	    	osw.write(out);
	    	osw.flush();			//如果不flush,则最大文件不能超过8kb
	    	System.err.println("生成BPMN文件成功！");
	    }catch (IOException e) {
	    	   e.printStackTrace();
	    }
	}
}
