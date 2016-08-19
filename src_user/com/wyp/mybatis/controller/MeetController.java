package com.wyp.mybatis.controller;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import com.wyp.mybatis.inter.MeetUserService;
import com.wyp.mybatis.inter.MeetingService;
import com.wyp.mybatis.model.Meets;
import com.wyp.mybatis.model.User;
import com.wyp.mybatis.model.meetUser;
import com.wyp.mybatis.util.PageInfo;

@SuppressWarnings("deprecation")
@Controller
@RequestMapping("/meet")
public class MeetController extends SimpleFormController {
	@Autowired
	MeetingService MeetingService;
	@Autowired
	MeetUserService meetUserService;
	
	//	�������
	@RequestMapping("/insert")
	public ModelAndView insertMeets(Meets meets,HttpServletRequest request,HttpServletResponse response){
//        Timestamp meetProTime = new Timestamp(new Date().getTime());
		Date meetProTime = new Date();
		meets.setMeetProTime(meetProTime);
		String applyUser = (String) request.getSession().getAttribute("username");
		meets.setApplyUser(applyUser);
//		System.out.println(meets.getMeetStartTime()+"555555555555555555555"+meets.getMeetEndTime());
		MeetingService.addMeets(meets); 
//		System.out.println(meets);
		//�ض��򵽷�ҳ��ʾ�б�
		ModelAndView mav=new ModelAndView("redirect:/meet/ownlist");
		return mav;
	}
	
	
	//	�鿴��Ϣ�������û���ӻ���
	@RequestMapping("/checkInfo")
	public ModelAndView checkInfo(HttpServletRequest request,HttpServletResponse response){
		String mid = request.getParameter("meetId");	
        int meetId = 1;
        if(mid!=null &&!"".equals(mid)){
        	meetId = Integer.parseInt(mid);
        }
		//Meets meets = MeetingService.getMeetInfoById(meetId); 
		List<User> allUsers = MeetingService.getUsers();
		//�ض��򵽷�ҳ��ʾ�б�
		ModelAndView mav=new ModelAndView(new RedirectView("/OpenMeeting/htjsp/checkMeets.jsp"));
		//mav.addObject("meets",meets);
		request.getSession().setAttribute("allUsers", allUsers);
		request.getSession().setAttribute("meetId", meetId);
		return mav;
	}
	
	
	//	�鿴������Ϣ������ѡ��Ļ����û�����Ϣ�ķ���
	@RequestMapping("/meetInfo")
	public ModelAndView meetInfo(HttpServletRequest request,HttpServletResponse response){
		long meetId = 31;//Ĭ�ϻ�����
		String meetii = request.getParameter("meetId");//get���տ��ܴӲ鿴ֱ�Ӵ�������ֵ
		if(meetii!=null && !"".equals(meetii)){
			meetId = Long.parseLong(meetii);
		}else{
			long meetI = (Long) request.getSession().getAttribute("meetId");//������ת�����Ĳ���	
			meetId = meetI;
		}
		//���Ȼ�ȡ����εĻ�����Ϣ
		Meets meets = MeetingService.getMeetInfoById(meetId); 
		//��Σ���ȡ����������û���Ϣ
		List<meetUser> allUsers = meetUserService.getUsersBymeetId(meetId);
		//�ض��򵽷�ҳ��ʾ�б�
		ModelAndView mav=new ModelAndView("meetInfo");
		//mav.addObject("meets",meets);
		request.getSession().setAttribute("allUsers", allUsers);
		request.getSession().setAttribute("meets", meets);
		return mav;
	}

	
	//	��Ϣ����
	@RequestMapping("/goupdate")
	public ModelAndView goUpdateMeets(HttpServletRequest request,HttpServletResponse response){
        String mid = request.getParameter("meetid");	
        int meetId = 1;
        if(mid!=null &&!"".equals(mid)){
        	meetId = Integer.parseInt(mid);
        }
		Meets meets = MeetingService.getMeetInfoById(meetId); 
//		System.out.println(meets+"5555555555555555555555555");
		//�ض��򵽷�ҳ��ʾ�б�
		ModelAndView mav=new ModelAndView(new RedirectView("/OpenMeeting/htjsp/updatemeet.jsp"));
		mav.addObject("meets",meets);
		return mav;
	}
	
	
	//	��Ϣ����
	@RequestMapping("/update")
	public ModelAndView updateMeets(Meets meets,HttpServletRequest request,HttpServletResponse response){
		Date meetProTime = new Date();
		meets.setMeetProTime(meetProTime);//
		
//		SimpleDateFormat sdf=new SimpleDateFormat("hh:mm:ss");
//		String s=sdf.format(meets.getMeetTime());
//		try {
//			Time time =  (Time) sdf.parse(s) ;
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
		
		meets.setMeetTime(this.getDate());//��������
		
		MeetingService.updateMeets(meets); 
		System.out.println(meets);
		//�ض��򵽷�ҳ��ʾ�б�
		ModelAndView mav=new ModelAndView("redirect:/meet/ownlist");
		return mav;
	}
	
	//��Ϣɾ��
	@RequestMapping("/delete")
	public ModelAndView deleteMeets(HttpServletRequest request,HttpServletResponse response){
		String linklist = request.getParameter("linklist");
		System.out.println("ɾ����"+linklist);
		long []arrayId = new long[100];
		if(linklist!=null && linklist!="")
		{
			String []arrayStr = linklist.split(";");
			for(int i=0;i<arrayStr.length;i++)
			{
				arrayId[i] = Long.parseLong(arrayStr[i]);	
				this.MeetingService.deleteMeets(arrayId[i]);
			}
		}
		
		//�ض��򵽷�ҳ��ʾ�б�
		ModelAndView mav=new ModelAndView("redirect:/meet/ownlist");
		return mav;
	}
	
	//	ɾ��ָ�������ָ���û�
	@RequestMapping("/deleteUser")
	public ModelAndView deleteMeetUser(HttpServletRequest request,HttpServletResponse response){
		String userId = request.getParameter("userId");
		String meetId = request.getParameter("meetId");
		
		//ɾ��ָ���û���¼
		this.meetUserService.deleteUser(Long.parseLong(meetId), Long.parseLong(userId));
		//�ض��򵽷�ҳ��ʾ�б�
		ModelAndView mav=new ModelAndView("redirect:/meet/meetInfo");
		request.getSession().setAttribute("meetId", Long.parseLong(meetId));
		return mav;
	}
	
//�����������������ͻ��������
	@RequestMapping("/findMeetsNumByType")
	public ModelAndView findMeetsNumByType(HttpServletRequest request,HttpServletResponse response){
		String applyUser = (String) request.getSession().getAttribute("username");
		List<Meets> listxsdy= this.MeetingService.findMeetsByType(applyUser,"������Ա");
		List<Meets> listjstl= this.MeetingService.findMeetsByType(applyUser,"��������");
		List<Meets> listbmlh= this.MeetingService.findMeetsByType(applyUser,"��������");
		//�ض��򵽷�ҳ��ʾ�б�
		System.out.println(applyUser);
		System.out.println(listxsdy);
		ModelAndView mav=new ModelAndView("meetlistByType");
		mav.addObject("listxsdy",listxsdy);
		mav.addObject("listjstl",listjstl);
		mav.addObject("listbmlh",listbmlh);
		return mav;
	}
	
	
//�����������������ͻ��������
	@RequestMapping("/findMeetsNumByTime")
	public ModelAndView findMeetsNumByTime(HttpServletRequest request,HttpServletResponse response){
	//1���Ȼ�õ�ǰϵͳʱ��
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
		System.out.println("��ǰϵͳʱ�䣺"+df.format(new Date()));// new Date()Ϊ��ȡ��ǰϵͳʱ��
		String date = df.format(new Date());//��ǰϵͳʱ��,2015-05-30 23:44:10
	//2�����session�е��û�	
		String applyUser = (String) request.getSession().getAttribute("username");
	//3�������ݿ�ȡ������	
		List<Meets> listmeet= this.MeetingService.selectlistMeetsForCompare(applyUser);
    //4����ֵ�ǰϵͳʱ�䣬�ֱ������ں�ʱ��,arrayStr[0]�����ڣ�arrayStr[1]��ʱ��
		String []arrayStr = date.split(" ");
		String []arrayday = arrayStr[0].split("-");//������ڣ�2015-05-30
	//5��������Ӧlist	
		List<Meets> listhaveBe= new ArrayList<Meets>();//�Ѿ�������
		List<Meets> listisBe= new ArrayList<Meets>();//���ڿ���
		List<Meets> listnotBe= new ArrayList<Meets>();//��û�п���
	//6��ѭ�������ݱȶ�ʱ��Ľ������Ϊ������
		for(Meets meet:listmeet){
			String day = meet.getMeetTime();//��������,2015-05-13
			String startTime = meet.getMeetStartTime();//���鿪ʼʱ��,10:00:00
			String endTime = meet.getMeetEndTime();//�������ʱ��
			String []arrayStr1 = day.split("-");
			//1.�Ƚ����
			if(compareDay(arrayday[0],arrayStr1[0])>0){
				//��ǰ��ݱȻ�����ݴ�,�û����ѿ���
				listhaveBe.add(meet);
				continue;
			}else if(compareDay(arrayday[0],arrayStr1[0])==0){
				//��ǰ�����������һ��
				
				//2.�Ƚ��·�
				if(compareDay(arrayday[1],arrayStr1[1])>0){
					//��ǰ�·ݱȻ����·ݴ�,�û����ѿ���
					listhaveBe.add(meet);
					continue;
				}else if(compareDay(arrayday[1],arrayStr1[1])==0){
					//��ǰ�·�������·�һ��
					
					//3.�Ƚ���
					if(compareDay(arrayday[2],arrayStr1[2])>0){
						//��ǰ���ӱȻ������Ӵ�,�û����ѿ���
						listhaveBe.add(meet);
						continue;
					}else if(compareDay(arrayday[2],arrayStr1[2])==0){
						//��ǰ�������������һ��
						
						if(compareTime(arrayStr[1], startTime, endTime)==0){
							listisBe.add(meet);
							continue;
						}else if(compareTime(arrayStr[1], startTime, endTime)>0){
							listhaveBe.add(meet);
							continue;
						}else{
							listnotBe.add(meet);
							continue;
						}
						
					}else{
						//��ǰ�·ݱȻ����·�С
						listnotBe.add(meet);
						continue;
					}
					
				}else{
					//��ǰ�·ݱȻ����·�С
					listnotBe.add(meet);
					continue;
				}
				
			}else{
				//��ǰ��ݱȻ������С
				listnotBe.add(meet);
				continue;
			}
			
		}
		//�ض��򵽷�ҳ��ʾ�б�
		System.out.println(applyUser);
		ModelAndView mav=new ModelAndView("meetlistByTime");
		mav.addObject("listhaveBe",listhaveBe);
		mav.addObject("listisBe",listisBe);
		mav.addObject("listnotBe",listnotBe);
		return mav;
	}
	
	
//	�λ���Աѡ�񣬲���ӵ�meetuser����
	@RequestMapping("/addmeetuser")
	public ModelAndView addMeetUsers(HttpServletRequest request,HttpServletResponse response){
		String linklist = request.getParameter("userlink");
		System.out.println("�û��б�"+linklist);
		String meetId1 = request.getParameter("meetId");
		long meetId = 1;
        if(meetId1!=null &&!"".equals(meetId1)){
        	meetId = Long.parseLong(meetId1);
        }
		long []arrayId = new long[100];
		if(linklist!=null && linklist!="")
		{
			String []arrayStr = linklist.split(";");
			for(int i=0;i<arrayStr.length;i++)
			{
				arrayId[i] = Long.parseLong(arrayStr[i]);	
				System.out.println("�û�id:"+arrayId[i]);
				this.MeetingService.addmeetUsers(arrayId[i],meetId);
			}
		}
		
		//�ض��򵽷�ҳ��ʾ�б�
		ModelAndView mav=new ModelAndView("redirect:/meet/meetInfo");
		request.getSession().setAttribute("meetId", meetId);
		return mav;
	}
	
	
	@RequestMapping("/jumpOther")
	public ModelAndView jumpOther(HttpServletRequest request,HttpServletResponse response){
        String meetTime = request.getParameter("meetTime");
        String meetStartTime = request.getParameter("meetStartTime");
        String meetEndTime = request.getParameter("meetEndTime");
        System.out.println(meetStartTime+"  "+meetEndTime);
		ModelAndView mav=new ModelAndView(new RedirectView("/OpenMeeting/htjsp/insertmeet.jsp"));
//		mav.addObject("meetStartTime",meetStartTime);
//		mav.addObject("meetEndTime",meetEndTime);
		mav.addObject("meetTime",meetTime);
		request.getSession().setAttribute("meetStartTime", meetStartTime);
		request.getSession().setAttribute("meetEndTime", meetEndTime);
		return mav;
	}
	

	@RequestMapping("/timeline")
	public ModelAndView search(HttpServletRequest request,HttpServletResponse response){
		String roomId = request.getParameter("roomId");
		long mId =1;
		if(roomId!=null&&!"".equals(roomId)){
			mId = Long.parseLong(roomId);
		}
		String ttt = request.getParameter("meetTime");
		System.out.println("���յ���ʱ�䣺"+ttt);
        System.out.println("����ID��"+mId+" ʱ�䣺"+getDate());
        //1.�����ݿ��ȡ�������ʹ�÷�������л�����Ϣ
		List<Meets> listmeets= new ArrayList<Meets>();
		listmeets =	MeetingService.selectMeetInfos(mId,ttt); 
		//2.����Ϣ��ʱ��˳������
		Collections.sort(listmeets,comparator);
		//3.����Ϣ���ͳ�ȥ
		ModelAndView mav=new ModelAndView(new RedirectView("/OpenMeeting/htjsp/timeline.jsp"));
		
		//��Ϊ�������תʹ�õ����ض���������Ҫʹ��session����������
		request.getSession().setAttribute("listmeets", listmeets);
		request.getSession().setAttribute("meetTime", ttt);
        return mav;
		
		
//		for (Meets meets : listmeets) {
//			System.out.println("�����б�"+meets.getMeetStartTime());
//		}
	}
	
//	��ҳ��ʾ
	@RequestMapping("/pagelist")
	public ModelAndView pageList(HttpServletRequest request,HttpServletResponse response){
		int currentPage = request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page"));
		int pageSize = 6;
		if (currentPage<=0){
			currentPage =1;
		}
		int currentResult = (currentPage-1) * pageSize;
		
//		System.out.println(request.getRequestURI());
//		System.out.println(request.getQueryString());
		String applyUser = (String) request.getSession().getAttribute("username");
		
		PageInfo page = new PageInfo();
		page.setShowCount(pageSize);
		page.setCurrentResult(currentResult);
		List<Meets> listmeet=MeetingService.selectListPageMeets(page,applyUser);
		System.out.println(page);
		
		int totalCount = page.getTotalResult();
		System.out.println("������Ϊ��"+totalCount);
		int lastPage=0;
		if (totalCount % pageSize==0){
			lastPage = totalCount / pageSize;
		}
		else{
			lastPage = totalCount / pageSize+1;
		}
		
		if (currentPage>=lastPage){
			currentPage =lastPage;
		}
		
		String pageStr = "";

		pageStr=String.format("<a href=\"%s\">��һҳ</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"%s\">��һҳ</a>",
						request.getRequestURI()+"?page="+(currentPage-1),request.getRequestURI()+"?page="+(currentPage==lastPage?lastPage:currentPage+1) );


		//�ƶ���ͼ��Ҳ����list.jsp
		ModelAndView mav=new ModelAndView("meetlist");
		mav.addObject("listmeet",listmeet);
		mav.addObject("pageStr",pageStr);
		return mav;
	}
	
	
	//����������ɹ�������û�����ȡ�����б��ҳ��ʾ
	@RequestMapping("/ownlist")
	public ModelAndView ownRoomList(HttpServletRequest request,HttpServletResponse response){
		int currentPage = request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page"));
		int pageSize = 5;
		if (currentPage<=0){
			currentPage =1;
		}
		int currentResult = (currentPage-1) * pageSize;
		String applyUser = "wang";
		
		PageInfo page = new PageInfo();
		page.setShowCount(pageSize);
		page.setCurrentResult(currentResult);
		List<Meets> listmeet=MeetingService.selectListPageMeetsByApplyUser(page,applyUser);
		
		System.out.println(page);
		int totalCount = page.getTotalResult();

		int lastPage=0;
		if (totalCount % pageSize==0){
			lastPage = totalCount / pageSize;
		}
		else{
			lastPage =totalCount / pageSize+1;
		}
		
		if (currentPage>=lastPage){
			currentPage =lastPage;
		}
		
		String pageStr = "";

		pageStr=String.format("<a href=\"%s\">��һҳ</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"%s\">��һҳ</a>",
				request.getRequestURI()+"?page="+(currentPage-1),request.getRequestURI()+"?page="+(currentPage==lastPage?lastPage:currentPage+1) );


		//�ƶ���ͼ��Ҳ����list.jsp
		ModelAndView mav=new ModelAndView("ownMeetlist");
		mav.addObject("listmeet",listmeet);
		mav.addObject("pageStr",pageStr);
		return mav;
	}
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	//��ȡ���ڵ�����
	public String getDate(){
		   Date currentTime = new Date();
		   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		   String dateString = formatter.format(currentTime);
		   return dateString;
		   
	}
	
	//������ĸ������Զ�list��������
	Comparator<Meets> comparator = new Comparator<Meets>(){
		   public int compare(Meets s1, Meets s2) {
		    //��������
		    if(!s1.getMeetStartTime().equals(s2.getMeetStartTime())){
		     return s1.getMeetStartTime().compareTo(s2.getMeetStartTime());
		    }else{
		    	return (int) (s1.getMeetId() - s2.getMeetId());
		    }
		    
		   }
	};

	public int compareDay(String str1,String str2){
	   int num1 = Integer.parseInt(str1);
	   int num2 = Integer.parseInt(str2);
		if(num1==num2){
	    	//���
	    	return 0;
	    }else{
	    	if(num1<num2){
	    		return -1;
	    	}else{
	    		return 1;
	    	}
	    }
	}
	
	public int compareTime(String now,String start,String end){
		String []arrayStr1 = now.split(":");
		String []arrayStr2 = start.split(":");
		String []arrayStr3 = end.split(":");
		long num1 = Integer.parseInt(arrayStr1[0])*3600+Integer.parseInt(arrayStr1[1])*60+Integer.parseInt(arrayStr1[2]);
		long num2 = Integer.parseInt(arrayStr2[0])*3600+Integer.parseInt(arrayStr2[1])*60+Integer.parseInt(arrayStr2[2]);
		long num3 = Integer.parseInt(arrayStr3[0])*3600+Integer.parseInt(arrayStr3[1])*60+Integer.parseInt(arrayStr3[2]);
		if(num1>=num2&&num1<=num3){
			return 0;
		}else if(num1<num2){
			return -1;
		}else {
			return 1;
		}
	}
	
}
