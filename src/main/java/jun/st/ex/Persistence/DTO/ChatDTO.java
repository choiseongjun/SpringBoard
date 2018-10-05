package jun.st.ex.Persistence.DTO;

import java.util.Date;

public class ChatDTO {

	int chatno;
	String fromid;
	String toid;//
	Date chattime;
	String stringTime;
	String chatcontent;
	int chatread;
	String profileimage;
	
	
	public String getProfileimage() {
		return profileimage;
	}
	public void setProfileimage(String profileimage) {
		this.profileimage = profileimage;
	}
	public int getChatread() {
		return chatread;
	}
	public void setChatread(int chatread) {
		this.chatread = chatread;
	}
	public String getStringTime() {
		return stringTime;
	}
	public void setStringTime(String stringTime) {
		this.stringTime = stringTime;
	}
	public int getChatno() {
		return chatno;
	}
	public void setChatno(int chatno) {
		this.chatno = chatno;
	}
	public String getFromid() {
		return fromid;
	}
	public void setFromid(String fromid) {
		this.fromid = fromid;
	}
	public String getToid() {
		return toid;
	}
	public void setToid(String toid) {
		this.toid = toid;
	}
	public Date getChattime() {
		return chattime;
	}
	public void setChattime(Date chattime) {
		this.chattime = chattime;
	}
	public String getChatcontent() {
		return chatcontent;
	}
	public void setChatcontent(String chatcontent) {
		this.chatcontent = chatcontent;
	}
	@Override
	public String toString() {
		return "ChatDTO [chatno=" + chatno + ", fromid=" + fromid + ", toid=" + toid + ", chattime=" + chattime
				+ ", chatcontent=" + chatcontent + "]";
	}
}
