package com.wyp.mybatis.inter;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.wyp.mybatis.model.User;
import com.wyp.mybatis.util.PageInfo;
public interface UserService {
	 //@Select("select * from user where id = #{id}")
	 //��ʹ��ӳ���ļ���ʹ��ע�ⷽʽʱ�����дsql
	//ʹ��ӳ���ļ�ʱ��Ҫ��User.xml�������ļ��У�mapper namespace="com.yihaomen.mybatis.inter.IUserOperation" �������ռ�ǳ���Ҫ�������д�
//	  public User selectUserByID(@Param(value="id") int id);
	  public List<User> selectUserByName(@Param("userName") String userName);
	  //������ selectUserByID ������ User.xml �������õ� select ��id ��Ӧ��<select id="selectUserByID"��
      
//	  �ڲ���ʱ����There is no getter for property named 'id' in 'class java.lang.String'
//	  ���������MybatisĬ�ϲ���ONGL�������������Ի��Զ����ö���������ʽȡstring.idֵ�����𱨴�
//	  ���������  public Note findNoteByID(@Param(value="id") String id);˵������ֵ��
	  //�����û�Id��ȡ�û���Ϣ
	  public User selectUserById(@Param("userId") int userId);
	  
	  @Select("select seqNum from users order by userId desc limit 1;")
	  public String getLastNum();
	  
	  //��ѯ�б����ݣ�����������
	  public List<User> selectUsers(String userName); 
	  
	  //�������
	  public void addUser(User user);
	  
	  //�û�-��������
	  public void updateUser(User user);
	  
	  //��̨����-��������
	  public void updateUserht(User user);
	  
	  //��������
	  public void updatePass(@Param("userId") long userId,@Param("password") String password);
	  
	  //ɾ��������Ϣ
	  public void deleteUser(long id);
	  
	//��ҳ��Ϣ	  
	  public List<User> selectUserssListPage(@Param("page") PageInfo page,@Param("userId") long userId);
	  
}
