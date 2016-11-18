package com.doumob.execute;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.doumob.bean.Salary;
import com.doumob.email.EmailService;
import com.doumob.reader.ExcelReader;
import com.doumob.util.BeanUtil;

public class Executor {
	private static Logger logger = Logger.getLogger(Executor.class);

	/**
	 * @2015-6-29<br>
	 * @autor:zhangH<br>
	 * @desc:执行解析，并入库<br>
	 * @param path
	 *            文件路径
	 * @throws Exception
	 */
	public static void execute(String path) throws Exception {
		logger.info("邮件系统启动……");
		Long smillis = System.currentTimeMillis();
		ExcelReader reader=new ExcelReader();
		Workbook wb=reader.readWorkBook(path);
		Sheet sheet = reader.readSheet(wb, 0);
		List<Salary> errorList=new ArrayList<Salary>();//错误列表
		EmailService service=EmailService.getService();
		logger.info("开始解析并发送邮件……");
		for (int i = 2; i <=sheet.getLastRowNum(); i++) {
			//读取一行，发送一行，避免文件过大,内存不够
			Salary s=BeanUtil.readFromRow(reader.readRow(sheet, i));
			if(s!=null){
				boolean r=service.send(s);
				if(r){
					logger.debug("邮件发送成功，接收人:"+s.getName());
				}else{
					errorList.add(s);
					logger.debug("邮件发送失败,具体原因，请参照错误日志。");
				}
			}
		}
		service.close();
		if(errorList.size()>0){
			logger.info("邮件发送失败"+errorList.size()+"个。具体原因请查看错误日志。");
			File dir = new File("");
			File log = new File(dir.getAbsolutePath() +"/错误日志.txt");
			FileOutputStream fos=new FileOutputStream(log);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			for (Salary s: errorList) {
				bw.write(s.getName()+s.getEmail());
				bw.newLine();
			}
			bw.flush();
			bw.close();
		}
		Thread.sleep(2000);
		Long emillis = System.currentTimeMillis();
		logger.info("系统运行时间：" + (emillis - smillis) / 1000 + "秒。");

	}

	public static void main(String[] args) throws Exception {
		File file = new File("");
		File dir = new File(file.getAbsolutePath() + "\\");
		String path = file.getAbsolutePath() + "\\";
		boolean find=false;
		if (dir.isDirectory()) {
			File[] fs = dir.listFiles();
			for (int i = 0; i < fs.length; i++) {
				if (fs[i].isFile() &&( fs[i].getName().endsWith(".xls")||fs[i].getName().endsWith(".xlsx"))) {
					path = path + fs[i].getName();
					find=true;
					break;
				}
			}
		}
		if(find){
			logger.info("找到文件:"+path);
			execute(path);
		}else{
			logger.info("未找到可用文件，请检查当前文件夹:"+path);
		}
		// String path="C:/Users/killer/Desktop/kaoqin/8月.xls";
		Thread.sleep(3000);
		System.exit(0);
	}

}
