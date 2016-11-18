package com.doumob.bean;

import java.io.Serializable;

import com.doumob.util.BeanUtil;
import com.doumob.util.PinYinUtil;
import com.doumob.util.Property;

public class Salary implements  Serializable {
	private static final long serialVersionUID = 1L;
	private String name;//姓名
	private String pretax;//税前
	private String leave;//请假
	private String performance;//绩效
	private String fund;//公积金
	private String yanglao;//养老
	private String shiye;//失业
	private String yiliao;//医疗
	private String others;//其它
	private String tax;//个税（其它费用）
	private String fact;//实际工资
	private String remark;//备注
	private String email;//邮箱 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPretax() {
		return pretax;
	}
	public void setPretax(String pretax) {
		this.pretax = pretax;
	}
	public String getLeave() {
		return leave;
	}
	public void setLeave(String leave) {
		this.leave = leave;
	}
	public String getPerformance() {
		return performance;
	}
	public void setPerformance(String performance) {
		this.performance = performance;
	}
	public String getFund() {
		return fund;
	}
	public void setFund(String fund) {
		this.fund = fund;
	}
	public String getYanglao() {
		return yanglao;
	}
	public void setYanglao(String yanglao) {
		this.yanglao = yanglao;
	}
	public String getShiye() {
		return shiye;
	}
	public void setShiye(String shiye) {
		this.shiye = shiye;
	}
	public String getYiliao() {
		return yiliao;
	}
	public void setYiliao(String yiliao) {
		this.yiliao = yiliao;
	}
	public String getOthers() {
		return others;
	}
	public void setOthers(String others) {
		this.others = others;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	public String getFact() {
		return fact;
	}
	public void setFact(String fact) {
		this.fact = fact;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getEmail() {
		if(BeanUtil.isEmpty(email)){
			email=PinYinUtil.getPinyin(getName())+Property.getValue("mail_suffix");
		}
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
