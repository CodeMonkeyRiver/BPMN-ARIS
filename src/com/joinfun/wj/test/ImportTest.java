/*package com.joinfun.wj.test;

import jxl.*;
import jxl.write.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import jxl.read.biff.BiffException;

public class ImportTest {
	public void NotesMain() {

		try {
			Session session = getSession();
			AgentContext agentContext = session.getAgentContext();
			Agent agent = agentContext.getCurrentAgent();
			Document doc = agentContext.getDocumentContext();
			String sType = session.getPlatform();
			EmbeddedObject excelEmbeddedObject = null;

			String ServerPath = session.getEnvironmentString("Directory", true);
			PrintWriter pw = getAgentOutput();
			if (!ServerPath.equals("")) {
				ServerPath = getServerPath(ServerPath, sType);
				// boolean flag=false;
				// File excelFile=null;
				// excelFile=getFileByDoc(doc,ServerPath,CurrUser,pw);
				excelEmbeddedObject = this.getEmbeddedObjectByDoc(doc, pw);
				InputStream excelInputStream = null;
				if (excelEmbeddedObject != null) {
					excelInputStream = excelEmbeddedObject.getInputStream();
				}
				// if (excelFile!=null)
				if (excelInputStream != null) {
					try {
						// 导入数据,在此方法中加入导入数据的处理
						// ImportDataFromExcel(excelFile,pw,doc);
						this.importDataFromExcel(excelInputStream, pw, doc,
								session);
						excelInputStream.close();
					} catch (Exception e) {
						if (excelEmbeddedObject != null) {
							excelEmbeddedObject.recycle();
						}
					}
					if (excelEmbeddedObject != null) {
						excelEmbeddedObject.recycle();
					} else {
						pw.println("导出失败，没找到服务器路径");
					}
				}
				e.printStackTrace();
			}
			// Workbook book = Workbook.getWorkbook("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ImportDataFromExcel(InputStream excelInputStream,
			PrintWriter pw, Document doc, Session s) {
		int excelRows;// 行数
		// int excelColumns;//列数
		Cell cell1;// 单元格对象
		Workbook workbook;// Excel对象
		DateTime dt = null;
		try {

			workbook = Workbook.getWorkbook(excelInputStream);
			Database db = s.getAgentContext().getCurrentDatabase();
			// Database
			// flowdb=s.getDatabase(db.getServer(),doc.getItemValueString("appName")
			// + "/mbgl.nsf");
			// Notes
			Document newdoc = null;
			Sheet sheet = workbook.getSheet(0);
			excelRows = sheet.getRows();
			// excelColumns=sheet.getColumns();
			System.out.println("excelRows=" + excelRows);
			String CurrUser = doc.getItemValueString("UserName");
			Name notesName = s.createName(CurrUser);
			// 增加你的代码
			// pw.println("<META HTTP-EQUIV='Pragma' CONTENT='no-cache'>");
			// pw.println("<META HTTP-EQUIV=/"Content-Type/" content=/"text/html;
			// charset=gb2312/">");
			// pw.println("<LINK REL=stylesheet HREF=/"" + doc.getItemValueString("ResourcePath") + "css/global.css/" TYPE=/"text/css/">");
			// pw.println("<link href=/"" + doc.getItemValueString("ResourcePath") + "css/viewStyle.css/" rel=/"stylesheet/" type=/"text/css/">");
			String ErrorMsg = "";
			// pw.println("<table width=95% align=center class=tableClass cellspan='5' cellpadding='2' border='1' id=outtable>");
			for (int i = 1; i < excelRows; i++) {
				// pw.println("<tr>");
				cell1 = sheet.getCell(0, i);// 单元各对象
				if (!cell1.getContents().equals("")) {
					newdoc = db.createDocument();
					newdoc.replaceItemValue("form", "frmWebFlow");
					// 项目名称
					newdoc.replaceItemValue("docreater",
							notesName.getCanonical());
					// System.out.println(doc.getItemValueString("currTime")+"11111111111");
					dt = s.createDateTime(doc.getItemValueString("currTime"));
					newdoc.replaceItemValue("docreatime", dt);
					// newdoc.getFirstItem("docreatime").setDateTimeValue(dt);

					newdoc.replaceItemValue("fldXmmc", cell1.getContents());
					cell1 = sheet.getCell(1, i);// 单元各对象 项目编码
					newdoc.replaceItemValue("fldXmbm", cell1.getContents());
					cell1 = sheet.getCell(2, i);// 单元各对象 审计机构名称
					newdoc.replaceItemValue("fldSjdw", cell1.getContents());
					cell1 = sheet.getCell(3, i);// 单元各对象 审计项目经理
					newdoc.replaceItemValue("fldProjectManager",
							cell1.getContents());
					cell1 = sheet.getCell(4, i);// 单元各对象 联系人
					newdoc.replaceItemValue("fldContact", cell1.getContents());
					cell1 = sheet.getCell(5, i);// 单元各对象 联系电话
					newdoc.replaceItemValue("fldContactTel",
							cell1.getContents());
					cell1 = sheet.getCell(6, i);// 单元各对象 已完成工作小结
					newdoc.replaceItemValue("fldGznr", cell1.getContents());
					cell1 = sheet.getCell(7, i);// 单元各对象 将要进行的工作
					newdoc.replaceItemValue("fldWtlsqk", cell1.getContents());
					cell1 = sheet.getCell(8, i);// 单元各对象 审计发现问题
					newdoc.replaceItemValue("fldGzwczy", cell1.getContents());

					cell1 = sheet.getCell(9, i);// 单元各对象 工作中遇到的主要问题及建议解决方法
					newdoc.replaceItemValue("fldSjfx", cell1.getContents());
					cell1 = sheet.getCell(10, i);// 单元各对象 其它
					newdoc.replaceItemValue("fldBz", cell1.getContents());
					newdoc.replaceItemValue("subform",
							doc.getItemValueString("subform"));
					newdoc.replaceItemValue("fldParentCode",
							doc.getItemValueString("fldParentCode"));
					newdoc.replaceItemValue("viewname",
							doc.getItemValueString("viewname"));
					newdoc.replaceItemValue("flowtype",
							doc.getItemValueString("flowtype"));
					newdoc.replaceItemValue("flowid",
							doc.getItemValueString("flowid"));
					newdoc.computeWithForm(true, false);
					// newdoc.replaceItemValue("hfldFlowDefPath","hq/dep11/flowdef_11.nsf");
					newdoc.replaceItemValue("flownum", new Integer(0));
					newdoc.replaceItemValue("DocumentAuthors",
							notesName.getCanonical());
					newdoc.replaceItemValue("fldAuthor",
							notesName.getCanonical());
					newdoc.replaceItemValue("fldLastStepReader",
							notesName.getCanonical());
					newdoc.replaceItemValue("fldLastStepAuthor",
							notesName.getCanonical());
					newdoc.replaceItemValue("alldealer",
							notesName.getCanonical());
					newdoc.replaceItemValue("stat", "正在起草");

					newdoc.save(true);
				} else {
					System.out.println("第" + i + "第一列为空，没有导入");
					ErrorMsg = ErrorMsg + "<tr><td class='tdContent'>第" + i
							+ "行第一列为空，没有导入</td></tr>";
				}
				// for(int j=0;j<excelColumns;j++)
				// {
				// cell1=sheet.getCell(j,i);//单元各对象
				// pw.println("<td class='tdContent'>"+cell1.getContents()+"</td>");//单元格内容
				// }
				// pw.println("</tr>");
			}
			// pw.println("</table>");

			workbook.close();
			pw.println("<META HTTP-EQUIV='Pragma' CONTENT='no-cache'>");
			pw.println("<META HTTP-EQUIV='Content-Type' content='text/html; charset=gb2312'>");

			pw.println("<table width=95% align=center class=tableClass cellspan='5' cellpadding='2' border='1' id=outtable>");

			pw.println("<tr><td class='tdContent'>导入完成</td></tr>");
			pw.println(ErrorMsg);
			pw.println("<tr><td align='center'><input type=button name='return' value='返  回' onclick='javascript:history.back();'></td></tr>");
			pw.println("</table>");
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}*/