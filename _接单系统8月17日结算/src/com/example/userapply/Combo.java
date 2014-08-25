package com.example.userapply;

import java.io.Serializable;

public class Combo implements Serializable{
 String comboName;
 String comboNum;
 String modifyDt1;
 String unitID;
 String itemNum;
 String itemName;
 String comboMID;
 String comboMoney;
 
 
 
 public String getComboMoney() {
	return comboMoney;
}
public void setComboMoney(String comboMoney) {
	this.comboMoney = comboMoney;
}
public String getComboMID() {
	return comboMID;
}
public void setComboMID(String comboMID) {
	this.comboMID = comboMID;
}
public String getComboName() {
	return comboName;
}
public void setComboName(String comboName) {
	this.comboName = comboName;
}
public String getComboNum() {
	return comboNum;
}
public void setComboNum(String comboNum) {
	this.comboNum = comboNum;
}
public String getModifyDt1() {
	return modifyDt1;
}
public void setModifyDt1(String modifyDt1) {
	this.modifyDt1 = modifyDt1;
}
public String getUnitID() {
	return unitID;
}
public void setUnitID(String unitID) {
	this.unitID = unitID;
}
public String getItemNum() {
	return itemNum;
}
public void setItemNum(String itemNum) {
	this.itemNum = itemNum;
}
public String getItemName() {
	return itemName;
}
public void setItemName(String itemName) {
	this.itemName = itemName;
}

}
