package com.doumob.util;

import org.apache.poi.ss.usermodel.Row;

import com.doumob.bean.Salary;

public class BeanUtil {
	
	public final static  String[] header={"姓名","税前工资","请假","绩效","公积金","养老扣除","失业扣除","医疗扣除","其他扣款","费用","实发工资","备注"};
	
	public static Salary readFromRow(Row row){
		Salary sl=null;
		if(!isTitleRow(row)){
			sl=new Salary();
			for (int i = 0; i < header.length+1; i++) {
				if(i==0){sl.setName(row.getCell(i).toString());}
				if(i==1){sl.setPretax(row.getCell(i).toString());}
				if(i==2){sl.setLeave(row.getCell(i).toString());}
				if(i==3){sl.setPerformance(row.getCell(i).toString());}
				if(i==4){sl.setFund(row.getCell(i).toString());}
				if(i==5){sl.setYanglao(row.getCell(i).toString());}
				if(i==6){sl.setShiye(row.getCell(i).toString());}
				if(i==7){sl.setYiliao(row.getCell(i).toString());}
				if(i==8){sl.setOthers(row.getCell(i).toString());}
				if(i==9){sl.setTax(row.getCell(i).toString());}
				if(i==10){sl.setFact(row.getCell(i).toString());}
				if(i==11){sl.setRemark(row.getCell(i).toString());}
				if(i==12){sl.setEmail(row.getCell(i)==null?null:row.getCell(i).toString());}
			}
		}
		return sl;
	};
	
	public static String[] sortWithHeader(Salary sl){
		String[] data=new String[header.length];
		for (int i = 0; i < header.length; i++) {
			if(i==0){data[i]= sl.getName();}
			if(i==1){data[i]= sl.getPretax();}
			if(i==2){data[i]= sl.getLeave();}
			if(i==3){data[i]= sl.getPerformance();}
			if(i==4){data[i]= sl.getFund();}
			if(i==5){data[i]= sl.getYanglao();}
			if(i==6){data[i]= sl.getShiye();}
			if(i==7){data[i]= sl.getYiliao();}
			if(i==8){data[i]= sl.getOthers();}
			if(i==9){data[i]= sl.getTax();}
			if(i==10){data[i]= sl.getFact();}
			if(i==11){data[i]= sl.getRemark();}
		}
		return data;
	}
	
	public static String buildContent(Salary sl){
		StringBuffer sb=new StringBuffer();
		sb.append("<table>");
		sb.append("<tr class='bg'>");
		for (String s:header) {
			sb.append("<td>").append(s).append("</td>");
		}
		sb.append("</tr>");
		sb.append("<tr>");
		for (String s:sortWithHeader(sl)) {
			sb.append("<td>").append(s).append("</td>");
		}
		sb.append("</tr>");
		sb.append("</table>");
		return sb.toString();
	}
	
	public static boolean isEmpty(String str){
		return str==null||str.trim().equals("");
	}
	/**
	 * 如果两个单元格有一个为空，则认为此行为标题行
	 * @param row
	 * @return 
	 */
	public static boolean isTitleRow(Row row){
		return row==null||(row.getCell(0)==null||row.getCell(1)==null)||isEmpty(row.getCell(0).toString())||isEmpty(row.getCell(1).toString());
	}
}
