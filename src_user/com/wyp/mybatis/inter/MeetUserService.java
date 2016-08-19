package com.wyp.mybatis.inter;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wyp.mybatis.model.meetUser;

@Repository  
@Transactional  
public interface MeetUserService {
		//ɾ��ָ�������е�ָ���û�
		public void deleteUser(@Param("meet_id")long meet_id,@Param("user_id")long user_id);
		//����������Ϣ
		public List<meetUser> getUsersBymeetId(long meetId);
}
