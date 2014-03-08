package com.joinfun.wj.generatorXML;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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


public class Gerenator4XML {

	private Analyser analyser = new Analyser();
	private Document doc;
	private XmlStart start;
	private XmlEnd end;
	private List<XmlUserTask> userTasks;
	private List<XmlIntermediateEvent> middleEvents;
	private List<XmlManualTask> manualTasks;
	String modelName = "";
	
	public Gerenator4XML() throws ParserConfigurationException, SAXException, IOException {
		super();
		doc = analyser.getDocument();
		start = analyser.getStartEvent(doc);
		end = analyser.getEndEvent(doc);
		userTasks = analyser.getUserTasks(doc);
		middleEvents = analyser.getMiddleEvents(doc);
		manualTasks = analyser.getManualTasks(doc);
		modelName = analyser.getModelName(doc);
	}
	
	

	InputStream defInstream = this.getClass().getClassLoader().getResourceAsStream(Constant.ARIS_TEMPLATE_PATH + Constant.XML_DEF_NAME);
	InputStream occInstream = this.getClass().getClassLoader().getResourceAsStream(Constant.ARIS_TEMPLATE_PATH + Constant.XML_OCC_NAME);
	InputStream occEndInstream = this.getClass().getClassLoader().getResourceAsStream(Constant.ARIS_TEMPLATE_PATH + Constant.XML_OCC_END_NAME);
	String define = new String(IOUtils.read(defInstream), "UTF-8");
	String occurrence = new String(IOUtils.read(occInstream),"UTF-8");
	private static OutputStreamWriter osw;
	
	public String getDefineStr(){
		String startStr = "";
		String middleStr = "";
		String endStr = "";
		String userTaskStr = "";
		String manualTaskStr = "";
		
		startStr = define.replaceAll("\\$TYPE", Constant.START_EVENT).
							replaceAll("\\$ID", start.getStartEventId()).
							replaceAll("\\$NAME", start.getAttrValue()) + "\r\n";
		endStr = define.replaceAll("\\$TYPE", Constant.END_EVENT).
							replaceAll("\\$ID", end.getEndEventId()).
							replaceAll("\\$NAME", end.getAttrValue()) + "\r\n";
		for(XmlIntermediateEvent middleEvent : middleEvents){
			middleStr += define.replaceAll("\\$TYPE",Constant.MIDDEN_EVENT).
								replaceAll("\\$ID", middleEvent.getIntermediateEventId()).
								replaceAll("\\$NAME", middleEvent.getIntermediateEventName()) + "\r\n";
		}
		for(XmlManualTask manualTask : manualTasks){
			manualTaskStr += define.replaceAll("\\$TYPE", Constant.MANUAL_TASK).
									replaceAll("\\$ID", manualTask.getManualTaskId()).
									replaceAll("\\$NAME", manualTask.getManualTaskName()) + "\r\n";
		}
		for(XmlUserTask userTask : userTasks){
			userTaskStr += define.replaceAll("\\$TYPE", Constant.USER_TASK).
					replaceAll("\\$ID", userTask.getUserTaskId()).
					replaceAll("\\$NAME", userTask.getUserTaskName()) + "\r\n";
		}
		return startStr + middleStr + userTaskStr + manualTaskStr  + endStr;
	}
	
	public String getOccStr() throws IOException{
		String startStr = "";
		String middleStr = "";
		String userTaskStr = "";
		String manualTaskStr = "";
		
		startStr = occurrence.replaceAll("\\$NAME", Constant.START_EVENT).
								replaceAll("\\$POSITION_X", "" + start.getPositionX()).
								replaceAll("\\$POSITION_Y", "" + start.getPositionY()).
								replaceAll("\\$SOURCE_GUID", start.getStartEventId()).
								replaceAll("\\$TARGET_GUID", start.getPointToGuid()) + "\r\n";
		/*endStr = define.replaceAll("\\$NAME", Constant.END_EVENT).
								replaceAll("\\$POSITION_X", "" + end.getPositionX()).
								replaceAll("\\$POSITION_Y", "" + end.getPositionY()).
								replaceAll("\\$ID", end.getEndEventId()).
								replaceAll("\\$TARGET_GUID", end.get) + "\r\n";*/
		for(XmlIntermediateEvent middleEvent : middleEvents){
			middleStr += occurrence.replaceAll("\\$NAME",Constant.MIDDEN_EVENT).
								replaceAll("\\$POSITION_X", "" + middleEvent.getPositionX()).
								replaceAll("\\$POSITION_Y", "" + middleEvent.getPositionY()).
								replaceAll("\\$SOURCE_GUID", middleEvent.getIntermediateEventId()).
								replaceAll("\\$TARGET_GUID", middleEvent.getPointToGuid()) + "\r\n";
		}
		for(XmlManualTask manualTask : manualTasks){
			manualTaskStr += occurrence.replaceAll("\\$NAME", Constant.MANUAL_TASK).
									replaceAll("\\$POSITION_X", "" + manualTask.getPositionX()).
									replaceAll("\\$POSITION_Y", "" + manualTask.getPositionY()).
									replaceAll("\\$SOURCE_GUID", manualTask.getManualTaskId()).
									replaceAll("\\$TARGET_GUID", manualTask.getPointToGuid()) + "\r\n";
		}
		for(XmlUserTask userTask : userTasks){
			userTaskStr += occurrence.replaceAll("\\$NAME", Constant.USER_TASK).
									replaceAll("\\$POSITION_X", "" + userTask.getPositionX()).
									replaceAll("\\$POSITION_Y", "" + userTask.getPositionY()).
									replaceAll("\\$SOURCE_GUID", userTask.getUserTaskId()).
									replaceAll("\\$TARGET_GUID", userTask.getPointToGuid()) + "\r\n";
		}
		String endStr = new String(IOUtils.read(occEndInstream),"UTF-8").
									replaceAll("\\$NAME", Constant.END_EVENT).
									replaceAll("\\$SOURCE_GUID", end.getEndEventId()) + "\r\n";
		return startStr + middleStr + userTaskStr + manualTaskStr + endStr;
		
	}
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException{
		Gerenator4XML generator = new Gerenator4XML();
		InputStream instream = generator.getClass().getClassLoader().getResourceAsStream(Constant.ARIS_TEMPLATE_PATH + Constant.XML_ARIS_TEMPLATE_NAME);
	    String oldXML = new String(IOUtils.read(instream), "UTF-8");
	    String out = oldXML.replaceAll("\\$MODEL_NAME", generator.modelName)
							.replaceAll("\\$DEFINE", generator.getDefineStr())
							.replaceAll("\\$OCC", generator.getOccStr());
		//System.out.println(generator.getDefineStr());
		//System.out.println(generator.getOccStr());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HHmmss");
		String date = sdf.format(new Date());
	    try{
	    	FileOutputStream fos = new FileOutputStream(Constant.DIRECTORY + "\\ARIS" + date + ".xml"); 
	    	osw = new OutputStreamWriter(fos,"UTF-8");
	    	osw.write(out);
	    	osw.flush();			//如果不flush,则最大文件不能超过8kb
	    	System.err.println("生成ARIS文件成功！");
	    }catch (IOException e) {
	    	   e.printStackTrace();
	    }
	}
	
}
