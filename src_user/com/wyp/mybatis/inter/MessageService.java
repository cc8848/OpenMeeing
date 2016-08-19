package com.wyp.mybatis.inter;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wyp.mybatis.model.Message;

@Repository  
@Transactional  
public interface MessageService {
		//��Ϣ����
		public void sendMessages(Message message);
		//����isScanF���ֶΣ�ȷ���û������
		public void updateOne(long id);
		//���»ظ���Ϣ
		public void updateTwo(Message message);
		//����isScan���ֶ�
		public void updateThird(long id);
		//��ȡ���û���ص�������Ϣ
		public List<Message> getMessageWithMe(long userId);
		//����������Ϣ
		public List<Message> getAllMessage(long meetId);
}
