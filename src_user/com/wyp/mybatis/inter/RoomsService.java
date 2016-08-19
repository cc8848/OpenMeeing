package com.wyp.mybatis.inter;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.wyp.mybatis.model.Rooms;
import com.wyp.mybatis.util.PageInfo;

public interface RoomsService {

	  //�������
	  public void addRooms(Rooms rooms);
	  
	  //��������
	  public void updateRooms(Rooms room);
	  
	  //ɾ��������Ϣ
	  public void deleteRooms(long roomId);
	  
	  //�鿴ĳһ����Ϣ
	  public Rooms getRoomsInfo(long roomId);
	  
      //��ҳ��Ϣ	  
	  public List<Rooms> selectRoomsListPage(@Param("page") PageInfo page,@Param("roomId") long roomId);
}
