package kr.co.bit_camp.attcontroller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import kr.co.bit_camp.controller.Controller;
import kr.co.bit_camp.dao.AttendMapper;
import kr.co.bit_camp.db.MyAppSqlConfig;
import kr.co.bit_camp.usercontroller.LogInController;
import kr.co.bit_camp.vo.Attendence;

public class AttController implements Controller {
	//데스크탑에서푸쉬
	Scanner sc = new Scanner(System.in);
	Calendar c = Calendar.getInstance();
	private AttendMapper mapper;
	private Attendence att = new Attendence();
	Date d = null;
	public AttController() {
		SqlSession session = MyAppSqlConfig.getSqlSession();
		mapper = session.getMapper(AttendMapper.class);
	}
	public int choiceMenu() {
		System.out.println("異쒓껐愿�由�");
		System.out.println("---------------------------------");		
		System.out.println("1. �엯�떎");
		System.out.println("2. �눜�떎");
		System.out.println("3. �굹�쓽 異쒓껐 �쁽�솴");
		System.out.println("4. �뮘濡�");
		System.out.println("---------------------------------");
		System.out.print("硫붾돱 以� 泥섎━�븷 �빆紐⑹쓣 �꽑�깮�븯�꽭�슂 : ");		
		return Integer.parseInt(sc.nextLine());		
	}
	public void service() throws Exception {		
		while(true) {			
			switch(choiceMenu()) {		
			case 1:	
				enter();
				break;				
			case 2:
				leave();
				break;				
			case 3:
				status();
			case 4:
				return;
			default:
				System.out.println("�뾾�뒗踰덊샇�엯�땲�떎.");
				System.out.println("硫붾돱瑜� �솗�씤�빐 二쇱꽭�슂.");
			}
		}
	}
	public int enterMenu() {	
		System.out.println("�엯�떎�븯�떆寃좎뒿�땲源�?");
		System.out.println("---------------------------------");
		System.out.println("1. �삁");
		System.out.println("2. �븘�땲�슂");		
		System.out.print("硫붾돱 以� 泥섎━�븷 �빆紐⑹쓣 �꽑�깮�븯�꽭�슂 : ");
		return Integer.parseInt(sc.nextLine());		
	}
	public void enteryes() {
		try {
			String start = "0940";
			SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
			Date start_time;
			start_time = sdf.parse(start);
			att.setAttEnterTime(new Date());
			if(att.getAttEnterTime() == null) {
				att.setAttStatus(4);
			}
			else if(att.getAttEnterTime().getTime() <= start_time.getTime()) {
				att.setAttStatus(1);						
			}else {
				att.setAttStatus(2);
				System.out.println("吏�媛곸쿂由� �릺�뿀�뒿�땲�떎.");
				System.out.println("議고눜 諛� 吏�媛� 5踰� �씠�긽 �떆 寃곗꽍 1�쉶濡� 泥섎━�맗�땲�떎.");
				System.out.println("---------------------------------");
			}
				
		} catch (ParseException e) {					
			e.printStackTrace();
		}								
		System.out.println("�굹�쓽 異쒓껐 �쁽�솴�뿉 �벑濡앸릺�뿀�뒿�땲�떎.");
		System.out.println("09�떆 40遺� �씠�썑遺��꽣 吏�媛곸쿂由щ맗�땲�떎.");
		System.out.println("---------------------------------");
		att.setNo(LogInController.logInUser.getNo());
		mapper.insertAttendence(att);
		return;		
	}
	public void enter() {
		while(true) {
			switch(enterMenu()) {
			case 1:				
				enteryes();
				return;				
			case 2:
				return;				
			default:
				System.out.println("�뾾�뒗踰덊샇�엯�땲�떎.");
				System.out.println("硫붾돱瑜� �솗�씤�빐 二쇱꽭�슂.");				
			}	
		}			
	}
	public int leaveMenu() {	
		System.out.println("�눜�떎�븯�떆寃좎뒿�땲源�?");
		System.out.println("---------------------------------");
		System.out.println("1. �삁");
		System.out.println("2. �븘�땲�슂");		
		System.out.print("硫붾돱 以� 泥섎━�븷 �빆紐⑹쓣 �꽑�깮�븯�꽭�슂 : ");
		return Integer.parseInt(sc.nextLine());		
	}	
	public void leaveyes() {
		try {
			String end = "1820";
			SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
			Date end_time;
			end_time = sdf.parse(end);
			att.setAttEnterTime(new Date());
			att.setAttLeaveTime(new Date());
			att.setNo(LogInController.logInUser.getNo());
			
			Attendence att2=null;
			List<Attendence> list =  mapper.selectAttendenceList2(att);
			for(int i = 0 ;i < list.size() ; i++) {
				att2 = list.get(i);
				Date d = att.getAttLeaveTime();
				int k = d.getDate();
				Date d2 = att2.getAttEnterTime();
				int j = d2.getDate();
				if(k==j) {
					att.setAttNo(att2.getAttNo());
					break;
				}
			}
			
			
			
			if(att2.getAttStatus()==2&&att.getAttLeaveTime().getTime()>=end_time.getTime()) {
				att.setAttStatus(2);
			}else if(att.getAttLeaveTime().getTime() >= end_time.getTime()) {
				att.setAttStatus(1);
			}
			else {
				att.setAttStatus(3);
				System.out.println("議고눜泥섎━ �릺�뿀�뒿�땲�떎.");
				System.out.println("議고눜 諛� 吏�媛� 5踰� �씠�긽 �떆 寃곗꽍 1�쉶濡� 泥섎━�맗�땲�떎.");
			}
			
		} catch (ParseException e) {					
			e.printStackTrace();
		}								
		System.out.println("�굹�쓽 異쒓껐 �쁽�솴�뿉 �벑濡앸릺�뿀�뒿�땲�떎.");
		System.out.println("18�떆 20遺� �씠�쟾�� 議고눜泥섎━�맗�땲�떎.");
		att.setNo(LogInController.logInUser.getNo());
		mapper.updateAttendence(att);		
	}
	public void leave() {
		while(true) {
			switch(leaveMenu()) {
			case 1: 
				leaveyes();
				return;			
			case 2:
				return;
			default:
				System.out.println("�뾾�뒗踰덊샇�엯�땲�떎.");
				System.out.println("硫붾돱瑜� �솗�씤�빐 二쇱꽭�슂.");
			}
		}
	}
	public int statusMenu() {		
		System.out.println("1. �썡蹂� 異쒖꽍 �쁽�솴");
		System.out.println("2. �쟾泥� 異쒖꽍 �쁽�솴");
		System.out.println("3. �뮘濡�");
		System.out.print("硫붾돱 以� 泥섎━�븷 �빆紐⑹쓣 �꽑�깮�븯�꽭�슂 : ");
		return Integer.parseInt(sc.nextLine());
	}
	public int status(int k,List<Attendence> list) {
		int status = 0;
		for(int i =0 ; i<list.size();i++) {
			Attendence a = list.get(i);
			Date d = a.getAttLeaveTime();
			int day1 = d.getDate();
			if(k==day1) {
				status = a.getAttStatus();
				return status;
			}
		}
		return status;
	}
	
	public void agoMonth(Date date) {
		att.setAttLeaveTime(date);
		att.setNo(LogInController.logInUser.getNo());		
		List<Attendence> list = mapper.selectAttendenceList(att);
		
		System.out.printf("%d�뀈 %2d�썡\n", c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1);
		System.out.println("----------------------------------------------");
		System.out.println("�씪        �썡         �솕         �닔         紐�         湲�         �넗");
		int lastDate = c.getActualMaximum(Calendar.DAY_OF_MONTH); 
		c.set(Calendar.DAY_OF_MONTH, 1); 
		int dayWeek = c.get(Calendar.DAY_OF_WEEK); 					
		int nlCnt = 0;  
		for (int j = 1; j < dayWeek; j++) {
			System.out.print("     ");
			nlCnt++;
		}	   
		 
		for (int k = 1; k <= lastDate; k++) {			
				System.out.printf("%-3d",k );
				Attendence a = list.get(list.size()-1);
				Date d = a.getAttLeaveTime();
				int day = d.getDate();
				if(k<= day) {
					
				switch(status(k,list)) {
					case 1 :
						System.out.print((++nlCnt % 7 == 0) ? "\n"  : 
							             (nlCnt % 7 == 1 ?"  " : "O "));
						break;
					case 2 :
						System.out.print((++nlCnt % 7 == 0) ? "\n"  :  
							(nlCnt % 7 == 1 ?"  " : "�뼰 "));
						break;
					case 3 :
						System.out.print((++nlCnt % 7 == 0) ? "\n"  :  
							(nlCnt % 7 == 1 ?"  " : "�뼯 "));
						break;
					case 4 :
						System.out.print((++nlCnt % 7 == 0) ? "\n"  : 
							(nlCnt % 7 == 1 ?"  " : "X "));
						break;
					case 0 : 
						System.out.print((++nlCnt % 7 == 0) ? "\n"  : "  ");
						break;
					
					}
				}else{
					System.out.print((++nlCnt % 7 == 0) ? "\n"  : "  ");
					}
			}
			
			System.out.println();
	}
	public void listMonth() {
		d = new Date();
		att.setAttLeaveTime(d);
		att.setNo(LogInController.logInUser.getNo());		
		List<Attendence> list = mapper.selectAttendenceList(att);
		
		System.out.printf("%d�뀈 %2d�썡\n", c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1);
		System.out.println("----------------------------------------------");
		System.out.println("�씪        �썡         �솕         �닔         紐�         湲�         �넗");
		int lastDate = c.getActualMaximum(Calendar.DAY_OF_MONTH); 
		c.set(Calendar.DAY_OF_MONTH, 1); 
		int dayWeek = c.get(Calendar.DAY_OF_WEEK); 					
		int nlCnt = 0;  
		for (int j = 1; j < dayWeek; j++) {
			System.out.print("     ");
			nlCnt++;
		}	   
		 
		for (int k = 1; k <= lastDate; k++) {			
				System.out.printf("%-3d",k );
				Attendence a = list.get(list.size()-1);
				Date d = a.getAttLeaveTime();
				int day = d.getDate();
				if(k<= day) {
					
				switch(status(k,list)) {
					case 1 :
						System.out.print((++nlCnt % 7 == 0) ? "\n"  : 
							             (nlCnt % 7 == 1 ?"  " : "O "));
						break;
					case 2 :
						System.out.print((++nlCnt % 7 == 0) ? "\n"  :  
							(nlCnt % 7 == 1 ?"  " : "�뼰 "));
						break;
					case 3 :
						System.out.print((++nlCnt % 7 == 0) ? "\n"  :  
							(nlCnt % 7 == 1 ?"  " : "�뼯 "));
						break;
					case 4 :
						System.out.print((++nlCnt % 7 == 0) ? "\n"  : 
							(nlCnt % 7 == 1 ?"  " : "X "));
						break;
					case 0 : 
						System.out.print((++nlCnt % 7 == 0) ? "\n"  : "  ");
						break;
					
					}
				}else{
					System.out.print((++nlCnt % 7 == 0) ? "\n"  : "  ");
					}
			}
			
			System.out.println();
		}
	public void month() {
		System.out.printf("%d�뀈 %2d�썡\n", c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1);
		System.out.println("----------------------------------------------");
		System.out.println("�씪\t�썡\t�솕\t�닔\t紐�\t湲�\t�넗");
		int lastDate = c.getActualMaximum(Calendar.DAY_OF_MONTH);  
		
		c.set(Calendar.DAY_OF_MONTH, 1);  
		int dayWeek = c.get(Calendar.DAY_OF_WEEK);  
		
		c.add(Calendar.MONTH,-1); 
		
		int nlCnt = 0;  
		for (int j = 1; j < dayWeek; j++) {
			System.out.print("\t");
			nlCnt++;
		}
		for (int k = 1; k <= lastDate; k++) {
			System.out.print(k + ((++nlCnt % 7 == 0) ? "\n" : "\t"));
		}
		System.out.println();
		System.out.println("---------------------------------------------");		
		c.add(Calendar.MONTH,+1);
		
	}
	
	
	public void calendar() {
		while(true) {			
			System.out.print("�빆紐⑹쓣 �꽑�깮�븯�꽭�슂(1. �쁽�옱�떖 異쒖꽍�쁽�솴  2. �씠�쟾�떖 異쒖꽍�쁽�솴  3. �떎�쓬�떖 異쒖꽍�쁽�솴  4. �뮘濡�) :");
			int num =  Integer.parseInt(sc.nextLine());
			switch(num) {
			case 1:			
				att.setNo(LogInController.logInUser.getNo());
				d = new Date();
				c = Calendar.getInstance();
				d=c.getTime();	
				att.setAttEnterTime(d);
				
				listMonth();
				
				System.out.println("----------------------------------------------");
			
				c.set(c.get(Calendar.YEAR),	c.get(Calendar.MONTH), 1);
				System.out.println();
				System.out.println("異쒖꽍 : �뿃");
				System.out.println("吏�媛� : �뼰");
				System.out.println("議고눜 : �뼯");
				System.out.println("寃곗꽍 : X");
				
				System.out.println("異쒖꽍�씪 �닔 : " + mapper.selectAttendenceMonthCount1(att));
				System.out.println("吏�媛곸씪 �닔 : " + mapper.selectAttendenceMonthCount2(att));
				System.out.println("議고눜�씪 �닔 : " + mapper.selectAttendenceMonthCount3(att));
				System.out.println("寃곗꽍�씪 �닔 : " + mapper.selectAttendenceMonthCount4(att));
				break;						
			case 2:	
				att.setNo(LogInController.logInUser.getNo());
				d = new Date();
				c.add(Calendar.MONTH, -1);
				
				d=c.getTime();	
				att.setAttEnterTime(d);
				int agoDay = d.getMonth();
				if(agoDay == 0)	agoMonth(d);
				else month();
				
				System.out.println();
				System.out.println("異쒖꽍 : �뿃");
				System.out.println("吏�媛� : �뼰");
				System.out.println("議고눜 : �뼯");
				System.out.println("寃곗꽍 : X");
				
				System.out.println("異쒖꽍�씪 �닔 : " + mapper.selectAttendenceMonthCount1(att));
				System.out.println("吏�媛곸씪 �닔 : " + mapper.selectAttendenceMonthCount2(att));
				System.out.println("議고눜�씪 �닔 : " + mapper.selectAttendenceMonthCount3(att));
				System.out.println("寃곗꽍�씪 �닔 : " + mapper.selectAttendenceMonthCount4(att));				
				
				break;
			case 3:
				att.setNo(LogInController.logInUser.getNo());
				d = new Date();
				c.add(Calendar.MONTH, +1);
				d=c.getTime();
				att.setAttEnterTime(d);
				month();
				System.out.println();
				System.out.println("異쒖꽍 : �뿃");
				System.out.println("吏�媛� : �뼰");
				System.out.println("議고눜 : �뼯");
				System.out.println("寃곗꽍 : X");				

				System.out.println("異쒖꽍�씪 �닔 : " + mapper.selectAttendenceMonthCount1(att));
				System.out.println("吏�媛곸씪 �닔 : " + mapper.selectAttendenceMonthCount2(att));
				System.out.println("議고눜�씪 �닔 : " + mapper.selectAttendenceMonthCount3(att));
				System.out.println("寃곗꽍�씪 �닔 : " + mapper.selectAttendenceMonthCount4(att));				
				break;
			case 4:
				return;
			default:
				System.out.println("�뾾�뒗踰덊샇�엯�땲�떎.");
				System.out.println("硫붾돱瑜� �솗�씤�빐 二쇱꽭�슂.");
			}
		}
	}	

	public void status() {	
		while(true) {			
			switch(statusMenu()) {
			case 1:				
				calendar();
				break;																
			case 2:				
				att.setNo(LogInController.logInUser.getNo());
				System.out.println("�쟾泥� 異쒖꽍 �쁽�솴");
				System.out.println("---------------------------------");
				att.setAttStatus(1);
				System.out.printf("珥� 異쒖꽍�씪 �닔 : %d%n" ,mapper.selectAttendenceCount(att) );
				att.setAttStatus(2);
				System.out.printf("珥� 吏�媛곸씪 �닔 : %d%n", mapper.selectAttendenceCount(att));
				att.setAttStatus(3);
				System.out.printf("珥� 議고눜 �닔 : %d%n", mapper.selectAttendenceCount(att));
				att.setAttStatus(4);
				System.out.printf("珥� 寃곗꽍 �닔 : %d%n", mapper.selectAttendenceCount(att));
				System.out.println("寃곗꽍 10踰� �씠�긽�떆 媛뺤젣 �닔媛뺤쿋�쉶 �맗�땲�떎.");
				System.out.println("議고눜 諛� 吏�媛� 5踰� �씠�긽 �떆 寃곗꽍 1�쉶濡� 泥섎━�맗�땲�떎.");
				System.out.printf("珥� 寃곗꽍 : %d/10%n", mapper.selectAttendenceCount(att));
				System.out.println("---------------------------------");
				break;
			case 3:
				return;
			default:
				System.out.println("�뾾�뒗踰덊샇�엯�땲�떎.");
				System.out.println("硫붾돱瑜� �솗�씤�빐 二쇱꽭�슂.");
			}
		}
	}
}
