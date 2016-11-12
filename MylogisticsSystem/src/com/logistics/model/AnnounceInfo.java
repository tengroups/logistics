package com.logistics.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AnnounceInfo {
	private int announce_Id;
	private String announce_Title;
	private String announce_Content;
	private Date writeTime;
	private int manager_id;
	private String manager_name;
	public int getAnnounce_Id() {
		return announce_Id;
	}
	public void setAnnounce_Id(int announce_Id) {
		this.announce_Id = announce_Id;
	}
	public String getAnnounce_Title() {
		return announce_Title;
	}
	public void setAnnounce_Title(String announce_Title) {
		this.announce_Title = announce_Title;
	}
	public String getAnnounce_Content() {
		return announce_Content;
	}
	public void setAnnounce_Content(String announce_Content) {
		this.announce_Content = announce_Content;
	}

	public int getManager_id() {
		return manager_id;
	}
	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}
	public String getManager_name() {
		return manager_name;
	}
	public void setManager_name(String manager_name) {
		this.manager_name = manager_name;
	}
	//修改获取方法，改变时间显示格式
		public String getWriteTime() {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm");
			return sdf.format(writeTime);
			//return writeTime;
		}

		public void setWriteTime(Date writeTime) {
			this.writeTime = writeTime;
		}
}
