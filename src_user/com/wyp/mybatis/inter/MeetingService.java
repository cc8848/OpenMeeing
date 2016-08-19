package com.wyp.mybatis.inter;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wyp.mybatis.model.Meets;
import com.wyp.mybatis.model.User;
import com.wyp.mybatis.util.PageInfo;

public interface MeetingService {
	  
	  //�������ֲ�ѯ�б����ݣ����ض�������
	  public List<Meets> selectMeetsByName(String meetTitle); 
	  
	  //�������ֲ�ѯ�б����ݣ����ض�������
	  public List<Meets> selectMeetInfos(@Param("room_id") long roomId,@Param("meetTime") String meetTime); 
	  
	  //�������
	  public void addMeets(Meets meets);
	  
	  //��ӻ���λ��û�
	  public void addmeetUsers(@Param("user_id")long user_id,@Param("meet_id")long meet_id);
	  
	  //����id��ȡ������Ϣ
	  public Meets getMeetInfoById(long meetId);
	  
	  //����id��ȡ������Ϣ
	  public List<User> getUsers();
	  
	  //��������
	  public void updateMeets(Meets meets);
	  
	  //ɾ��������Ϣ
	  public void deleteMeets(long id);
	  
	  //��ȡ��ҳ��Ϣ
	  public List<Meets> selectListPageMeets(@Param("page") PageInfo page,@Param("applyUser") String applyUser);

	  //���ݷ�ҳ��Ϣ�����������˻�ȡ��ҳ��Ϣ
	  public List<Meets> selectlistMeetsForCompare(@Param("applyUser") String applyUser);
	
	  //���ݷ�ҳ��Ϣ�����������˻�ȡ��ҳ��Ϣ
	  public List<Meets> selectListPageMeetsByApplyUser(@Param("page") PageInfo page,@Param("applyUser") String applyUser);
       
	  //���ݷ�ҳ��Ϣ�����������˻�ȡ��ҳ��Ϣ
	  public List<Meets> findMeetsByType(@Param("applyUser") String applyUser,@Param("meetType") String meetType);
	  
	  //���ݵ�¼���û�id��meetuser���в���������صĻ��飬����ʾ������Ϣ
	  public List<Meets> getAllMeetsById(@Param("userId")int userId);
}
