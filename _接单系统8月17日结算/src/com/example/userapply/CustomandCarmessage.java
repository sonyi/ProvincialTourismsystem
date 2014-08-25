package com.example.userapply;

import java.io.Serializable;

public class CustomandCarmessage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String carInfoID;
	String carType;//车型号
	String cardNum;//卡号
	String modifyMan;//修改者
	String customerName;//用户名称
	String driver;//驾驶人
	String driverPhone;//驾驶人电话
	String carid;//车牌号
	String carcolor;//车身颜色
	String carFramNum;//车架号
	String motorNum;//发动机号
	String carBrand;//车辆品牌
	String yearCheckDT;//年检到期日
	String curMileage;//当前行驶
	String serviceMileage;//保修公里
	String buyDT;//购买时间
	String carConfig;//车辆配置
	String yearCheckInte;//年检周期
	String serverDT;//保修到期日
	String safeLimitDT;//保险到期日
	String carOwnID;
	String position;//储值余额
	
	
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public String getModifyMan() {
		return modifyMan;
	}
	public void setModifyMan(String modifyMan) {
		this.modifyMan = modifyMan;
	}
	public String getCarOwnID() {
		return carOwnID;
	}
	public void setCarOwnID(String carOwnID) {
		this.carOwnID = carOwnID;
	}
	public String getSafeLimitDT() {
		return safeLimitDT;
	}
	public void setSafeLimitDT(String safeLimitDT) {
		this.safeLimitDT = safeLimitDT;
	}
	public String getCarInfoID() {
		return carInfoID;
	}
	public void setCarInfoID(String carInfoID) {
		this.carInfoID = carInfoID;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getDriverPhone() {
		return driverPhone;
	}
	public void setDriverPhone(String driverPhone) {
		this.driverPhone = driverPhone;
	}
	public String getCarid() {
		return carid;
	}
	public void setCarid(String carid) {
		this.carid = carid;
	}
	public String getCarcolor() {
		return carcolor;
	}
	public void setCarcolor(String carcolor) {
		this.carcolor = carcolor;
	}
	public String getCarFramNum() {
		return carFramNum;
	}
	public void setCarFramNum(String carFramNum) {
		this.carFramNum = carFramNum;
	}
	public String getMotorNum() {
		return motorNum;
	}
	public void setMotorNum(String motorNum) {
		this.motorNum = motorNum;
	}
	public String getCarBrand() {
		return carBrand;
	}
	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}
	public String getYearCheckDT() {
		return yearCheckDT;
	}
	public void setYearCheckDT(String yearCheckDT) {
		this.yearCheckDT = yearCheckDT;
	}
	public String getCurMileage() {
		return curMileage;
	}
	public void setCurMileage(String curMileage) {
		this.curMileage = curMileage;
	}
	public String getServiceMileage() {
		return serviceMileage;
	}
	public void setServiceMileage(String serviceMileage) {
		this.serviceMileage = serviceMileage;
	}
	public String getBuyDT() {
		return buyDT;
	}
	public void setBuyDT(String buyDT) {
		this.buyDT = buyDT;
	}
	public String getCarConfig() {
		return carConfig;
	}
	public void setCarConfig(String carConfig) {
		this.carConfig = carConfig;
	}
	public String getYearCheckInte() {
		return yearCheckInte;
	}
	public void setYearCheckInte(String yearCheckInte) {
		this.yearCheckInte = yearCheckInte;
	}
	public String getServerDT() {
		return serverDT;
	}
	public void setServerDT(String serverDT) {
		this.serverDT = serverDT;
	}
	
	
	
}
