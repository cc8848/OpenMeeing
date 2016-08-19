/**
 * 
 */
package com.wyp.mybatis.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * @author ������
 * @date 2015-1-28
 */

public class Meets {

	private long meetId;//������
	private String meetTitle;//��������
	private String meetManager;//����ԤԼ�ˣ����֣�����
	private String meetPosition;//����ȼ�
	private String meetType;//��������
	private Date meetProTime;//����ʱ��yyyy-mm-dd hh:mm:ss
	private String meetTime;//�������� yyyy-mm-dd
	private String meetStartTime;//���鿪ʼʱ�� hh:mm:ss
	private String meetEndTime;//�������ʱ�� hh:mm:ss
	private int usersNum;//������Ա����
	private String meetInfo;//������Ϣ
    private long room_id; //��Ӧ�����ұ��
    private String applyUser;//����������
 
    private Set<Rooms> room = new HashSet<Rooms>();
    
    
	public Set<Rooms> getRoom() {
		return room;
	}

	public void setRoom(Set<Rooms> room) {
		this.room = room;
	}

	public String getApplyUser() {
		return applyUser;
	}

	public void setApplyUser(String applyUser) {
		this.applyUser = applyUser;
	}

	public long getRoom_id() {
		return room_id;
	}

	public void setRoom_id(long room_id) {
		this.room_id = room_id;
	}

	public long getMeetId() {
		return meetId;
	}

	public void setMeetId(long meetId) {
		this.meetId = meetId;
	}

	public String getMeetTitle() {
		return meetTitle;
	}

	public void setMeetTitle(String meetTitle) {
		this.meetTitle = meetTitle;
	}
	
	public Date getMeetProTime() {
		return meetProTime;
	}

	public void setMeetProTime(Date meetProTime) {
		this.meetProTime = meetProTime;
	}

	public String getMeetTime() {
		return meetTime;
	}

	public void setMeetTime(String meetTime) {
		this.meetTime = meetTime;
	}

	public String getMeetStartTime() {
		return meetStartTime;
	}

	public void setMeetStartTime(String meetStartTime) {
		this.meetStartTime = meetStartTime;
	}

	public String getMeetEndTime() {
		return meetEndTime;
	}

	public void setMeetEndTime(String meetEndTime) {
		this.meetEndTime = meetEndTime;
	}

	public String getMeetManager() {
		return meetManager;
	}

	public void setMeetManager(String meetManager) {
		this.meetManager = meetManager;
	}

	public String getMeetPosition() {
		return meetPosition;
	}

	public void setMeetPosition(String meetPosition) {
		this.meetPosition = meetPosition;
	}

	public String getMeetType() {
		return meetType;
	}

	public void setMeetType(String meetType) {
		this.meetType = meetType;
	}

	public int getUsersNum() {
		return usersNum;
	}

	public void setUsersNum(int usersNum) {
		this.usersNum = usersNum;
	}

	public String getMeetInfo() {
		return meetInfo;
	}

	public void setMeetInfo(String meetInfo) {
		this.meetInfo = meetInfo;
	}

	public Meets(){
		super();
	}

	@Override
	public String toString() {
		return "Meets [meetId=" + meetId + ", meetTitle=" + meetTitle
				+ ", meetManager=" + meetManager + ", meetPosition="
				+ meetPosition + ", meetType=" + meetType + ", meetProTime="
				+ meetProTime + ", meetTime=" + meetTime + ", meetStartTime="
				+ meetStartTime + ", meetEndTime=" + meetEndTime
				+ ", usersNum=" + usersNum + ", meetInfo=" + meetInfo
				+ ", room_id=" + room_id + ", applyUser=" + applyUser + "]";
	}

	
}
