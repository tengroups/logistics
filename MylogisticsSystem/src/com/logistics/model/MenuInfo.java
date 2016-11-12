package com.logistics.model;

public class MenuInfo {
private int menu_id;
private int upMenuId;
private String menuContent;
private String menuPath;
private int menuOrder;
private int isBlank;
public int getMenu_id() {
	return menu_id;
}
public void setMenu_id(int menu_id) {
	this.menu_id = menu_id;
}
public int getUpMenuId() {
	return upMenuId;
}
public void setUpMenuId(int upMenuId) {
	this.upMenuId = upMenuId;
}
public String getMenuContent() {
	return menuContent;
}
public void setMenuContent(String menuContent) {
	this.menuContent = menuContent;
}
public String getMenuPath() {
	return menuPath;
}
public void setMenuPath(String menuPath) {
	this.menuPath = menuPath;
}
public int getMenuOrder() {
	return menuOrder;
}
public void setMenuOrder(int menuOrder) {
	this.menuOrder = menuOrder;
}
public int getIsBlank() {
	return isBlank;
}
public void setIsBlank(int isBlank) {
	this.isBlank = isBlank;
}

}
