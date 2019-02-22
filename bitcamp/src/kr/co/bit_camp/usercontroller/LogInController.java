//일반로그인 및 관리자로그인, 로그아웃
package kr.co.bit_camp.usercontroller;

import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.ibatis.session.SqlSession;

import kr.co.bit_camp.Bookcontroller.BookController;
import kr.co.bit_camp.attcontroller.AttController;
import kr.co.bit_camp.attcontroller.ManagerAttController;
import kr.co.bit_camp.boardcontroller.BoardController;
import kr.co.bit_camp.controller.Controller;
import kr.co.bit_camp.dao.UserMapper;
import kr.co.bit_camp.db.MyAppSqlConfig;
import kr.co.bit_camp.vo.User;

public class LogInController implements Controller{
	public static User logInUser;
	Scanner sc = new Scanner(System.in);
	private void exit() {
		System.out.println("비트캠프를 종료합니다.");
		System.exit(0);
	}
	private UserMapper mapper;
	public String input(String msg) {
		System.out.print(msg);
		return sc.nextLine();
	}
	public LogInController() {
		SqlSession session = MyAppSqlConfig.getSqlSession();
		mapper = session.getMapper(UserMapper.class);
	}
	
	public boolean loginConfirm(User user) {
		List<User> list = mapper.selectUserList();
			for(int i=0;i<list.size();i++) {
				User u=list.get(i);
				if(u.getId().equals(user.getId())) {
					if(u.getPassword().equals(user.getPassword())) {
						logInUser=u;
						return true;
					};
				}
			}
	 	return false;
	}
	
	public void email(String email) {
		  String host     = "smtp.naver.com";
		  final String user   = "h_4261";
		  final String password  = "gn13811381";

		  String to     = email;

		  
		  // Get the session object
		  Properties props = new Properties();
		  props.put("mail.smtp.host", host);
		  props.put("mail.smtp.auth", "true");

		  Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
		   protected PasswordAuthentication getPasswordAuthentication() {
		    return new PasswordAuthentication(user, password);
		   }
		  });

		  // Compose the message
		  try {
		   MimeMessage message = new MimeMessage(session);
		   message.setFrom(new InternetAddress(user));
		   message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

		   // Subject
		   message.setSubject("아이디 및 비밀번호를 알려드립니다.");
		   
		   // Text
		   List<User> list = mapper.selectUserList();
		   User u = null;
		   
		   for(int i = 0; i < list.size() ;i++) {
			    u = list.get(i);
			   if(u.getEmail().equals(email)) {
				break;   
			   }
		   }
		   
		   message.setText(
				   u.getName()+"님의 아이디는"+
				   "\n아이디 : "+u.getId()+
				   "\n비밀번호 : "+u.getPassword()
				   +"\n입니다" );

		   // send the message
		   Transport.send(message);
		   System.out.println("아이디와 비밀번호 발송이 완료되었습니다.");

		  } catch (MessagingException e) {
		   e.printStackTrace();
		  }
	}
	
	
	
	
	public void service() {
		while(true) {
			System.out.println("로그인페이지 입니다.");
			System.out.println("1.로그인");
			System.out.println("2.아이디 및 비밀번호 찾기");
			System.out.println("3.뒤로");
			System.out.print("메뉴 중 처리할 항목을 선택하세요 : ");
			int no = Integer.parseInt(sc.nextLine());
			System.out.println("---------------------------------");
			switch(no){
			case 1 :
				User user = new User();
				user.setId(input("아이디를 입력하세요 : "));
				user.setPassword(input("비밀번호를 입력하세요 : "));
				
				if(loginConfirm(user)) {
					System.out.println("로그인이 완료되었습니다.");
					if(user.getId().equals("admin")) {
						adminLogIn();
						return;
					}
					UserLogIn();
					return;
				}
				System.out.println("아이디와 비밀번호를 확인해주세요.");
				System.out.println("---------------------------------");
				break;
			case 2 :
				System.out.println("가입시 등록했던 이메일을 입력해주세요");
				System.out.print("이메일 : ");
				String em = sc.nextLine();
				List<User> list = mapper.selectUserList();
				boolean con = false;
				for(int i = 0 ; i < list.size() ; i++) {
					User userMail = list.get(i);
					if(userMail.getEmail().equals(em)) {
						con=true;
					}
				}
				if(con==false) {
					System.out.println("해당 이메일은 존재하지 않습니다.");
					System.out.println("이메일을 확인해 주세요.");
					return;
				}
				email(em);
				break;
			case 3 :
				System.out.println("메인메뉴로 이동합니다.");
				return;
				default :
					System.out.println("없는번호입니다.");
					System.out.println("메뉴를 확인해 주세요.");
			}
		}
	}
	
	
	public void UserLogIn() {
	try {
			while(true) {
				Controller ctrl = null; 
				System.out.println("메인메뉴");
				System.out.println("---------------------------------");
				System.out.println("---------------------------------");
				System.out.println("1.로그아웃");
				System.out.println("2.회원정보");
				System.out.println("3.자유게시판");
				System.out.println("4.출결관리");
				System.out.println("5.학점관리");
				System.out.println("6.도서관");
				System.out.println("7.종료");
				System.out.println("---------------------------------");
				System.out.print("메뉴중 처리할 항목을 선택하세요 : ");
				int no = Integer.parseInt(sc.nextLine());
				
				switch(no) {
				case 1 :
					logInUser = null;
					System.out.println("로그아웃 되었습니다.");
					return;
				case 2 :
					ctrl = new UserInformationController();
					break;
				case 3 : 
					ctrl = new BoardController(logInUser);
					break;
				case 4 :
					ctrl = new AttController();
					break;
				case 5 :
					ctrl = new UserSelectListController();
					break;
				case 6 :
					ctrl = new BookController(logInUser);
					break;
				case 7 :
					exit();
					break;
				default :
					System.out.println("없는번호입니다.");
					System.out.println("메뉴를 확인해 주세요.");
				}
				if (ctrl != null) {
					ctrl.service();
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void adminLogIn(){
		try {
			while(true) {
				Controller ctrl = null; 
				System.out.println("메인메뉴");
				System.out.println("---------------------------------");
				System.out.println("---------------------------------");
				System.out.println("1.로그아웃");
				System.out.println("2.관리자정보");
				System.out.println("3.회원관리");
				System.out.println("4.자유게시판");
				System.out.println("5.출결관리");
				System.out.println("6.학점관리");
				System.out.println("7.도서관");
				System.out.println("8.종료");
				System.out.println("---------------------------------");
				System.out.print("메뉴중 처리할 항목을 선택하세요 : ");
				int no = Integer.parseInt(sc.nextLine());
				
				switch(no) {
				case 1 :
					logInUser = null;
					System.out.println("로그아웃 되었습니다.");
					return;
				case 2 :
					ctrl = new UserInformationController();
					break;
				case 3 : 
					ctrl = new ManageUserController();
					break;
				case 4 :
					ctrl = new BoardController(logInUser);
					break;
				case 5 :
					ctrl = new ManagerAttController();
					break;
				case 6 :
					ctrl = new RecordController();					
					break;
				case 7 :
					ctrl = new BookController(logInUser);
					break;
				case 8 :
					exit();
				default :
					System.out.println("없는번호입니다.");
					System.out.println("메뉴를 확인해 주세요.");
				}
				if (ctrl != null) {
					ctrl.service();
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	};               //관리자 페이지
}
