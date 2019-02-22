//회원 강퇴 기능

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

import kr.co.bit_camp.controller.Controller;
import kr.co.bit_camp.dao.UserMapper;
import kr.co.bit_camp.db.MyAppSqlConfig;
import kr.co.bit_camp.vo.User;

public class ManageUserController implements Controller{
	Scanner sc = new Scanner(System.in);
	public String input(String msg) {
		System.out.print(msg);
		return sc.nextLine();
	}
	private UserMapper mapper;
	public ManageUserController() {
		SqlSession session = MyAppSqlConfig.getSqlSession();
		mapper = session.getMapper(UserMapper.class);
	}
	
	

	public void email(String email,String reason) {
		
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
		   message.setSubject("비트캠프에서  강퇴당하셨습니다.");
		   
		   // Text
		   List<User> list = mapper.selectUserList();
		   User u = null;
		   
		   for(int i = 0; i < list.size() ;i++) {
			    u = list.get(i);
			   if(u.getEmail().equals(email)) {
				break;   
			   }
		   }
		   
		   message.setText("비트캠프에서 강퇴당하셨습니다.\n강퇴사유 : "+reason);

		   // send the message
		   Transport.send(message);
		   System.out.println("강퇴사유를 이메일로 발송했습니다.");

		  } catch (MessagingException e) {
		   e.printStackTrace();
		  }
	}
	
	
	
	
	
	
	
	
	
	
	public void service() {
		List<User> list = mapper.selectUserList();
		
		System.out.println("회원관리");
		System.out.println("---------------------------------");
		System.out.println("번호\t아이디\t\t이름");
		for(int i = 1; i < list.size() ; i++) {
			User user = list.get(i);
			System.out.printf("%d\t%-8s\t%s%n",i,user.getId(),user.getName());
		}
		System.out.println("---------------------------------");
		int no = Integer.parseInt(input("강퇴할 회원의 번호를 입력해주세요 : "));
		if(no>=list.size()) {
			System.out.println("입력 번호를 확인해주세요.");
			return;
		}
		
		User user = list.get(no);
		String rs = input("강퇴 사유를 적어주세요 : ");
		System.out.print(user.getName()+"회원을 강퇴시키겠습니까?(Y/N)");
		String answer = sc.nextLine().toUpperCase();
		switch(answer) {
		case "Y" :
			email(user.getEmail(),rs);
			
			mapper.deleteUser(user.getName());
			System.out.println(user.getName()+"회원을 강퇴시켰습니다.");
			return;
		case "N" : 
			return;
		}
	}
}