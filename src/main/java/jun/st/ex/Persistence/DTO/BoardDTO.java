package jun.st.ex.Persistence.DTO;

import java.util.Arrays;
import java.util.Date;

public class BoardDTO {
	private int bno;
	private String title;
	private String content;
	private String writer; 
	private Date regdate; 
	private int viewcnt;
	private String name; 
	private int cnt;//댓글 갯수
	private String show; 
	private String[] files; 
	private int ref;//groups
	private int ref_group;
	private String level;
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getViewcnt() {
		return viewcnt;
	}
	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	public String[] getFiles() {
		return files;
	}
	public void setFiles(String[] files) {
		this.files = files;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getRef_group() {
		return ref_group;
	}
	public void setRef_group(int ref_group) {
		this.ref_group = ref_group;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	@Override
	public String toString() {
		return "BoardDTO [bno=" + bno + ", title=" + title + ", content=" + content + ", writer=" + writer
				+ ", regdate=" + regdate + ", viewcnt=" + viewcnt + ", name=" + name + ", cnt=" + cnt + ", show=" + show
				+ ", files=" + Arrays.toString(files) + ", ref=" + ref + ", ref_group=" + ref_group + ", level=" + level
				+ "]";
	}
		
}
