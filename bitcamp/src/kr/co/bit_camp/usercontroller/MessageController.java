package kr.co.bit_camp.usercontroller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import kr.co.bit_camp.controller.Controller;
import kr.co.bit_camp.dao.MessageMapper;
import kr.co.bit_camp.dao.UserMapper;
import kr.co.bit_camp.db.MyAppSqlConfig;
import kr.co.bit_camp.vo.Message;
import kr.co.bit_camp.vo.User;

public class MessageController implements Controller {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Scanner sc = new Scanner(System.in);
	public String input(String msg) {
		System.out.print(msg);
		return sc.nextLine();
	}
	private MessageMapper mapper;
	private UserMapper umapper;
	public MessageController() {
		SqlSession session = MyAppSqlConfig.getSqlSession();
		mapper = session.getMapper(MessageMapper.class);
		umapper = session.getMapper(UserMapper.class);
	}
	public boolean searchId(String id) {
		List<User> list = umapper.selectUserList();
		for(int i = 0; i < list.size() ;i++) {
			User user = list.get(i);
			if(user.getId().equals(id)) {
				System.out.println("'"+id+"'"+"회원에게 쪽지를 보냅니다.");
			return true;
			}
		}
		System.out.println("존재하지 않는 회원 아이디입니다.");
		System.out.println("아이디를 확인해주세요.");
		return false;
	}
	public String searchName(String id) { 
		List<User> list = umapper.selectUserList();
		String name="";
		for(int i = 0; i < list.size() ;i++) {
			User user = list.get(i);
			if(user.getId().equals(id)) {
				name = user.getName();
			}
		}
		return name;
	}
	public void writeMessage() {
			String id = input("받는사람의 아이디를 검색합니다 : ");
			
			if(id.equals(LogInController.logInUser.getId())) {
				System.out.println("자기자신에게 쪽지를 보낼수 없습니다.");
				return;
			};
			if(searchId(id)) {
				Message message = new Message();
				message.setStatus(0);                       
				message.setSendName(LogInController.logInUser.getName());
				message.setReceiveId(id);
				message.setReceiveName(searchName(id));
				message.setSendId(LogInController.logInUser.getId());
				message.setReceiveDel("n");
				message.setSendDel("n");
				message.setTitle(input("제목을 입력해 주세요 : "));
				if(message.getTitle().equals("")) {
					System.out.println("제목을 입력해주세요.");
					return;
				}
				message.setContent(input("내용을 입력하세요 : "));
				if(message.getContent().equals("")) {
					System.out.println("내용을 입력해주세요.");
					return;
				}
				message.setTime(new Date());
				mapper.writeMessage(message);
				System.out.println("쪽지를 성공적으로 보냈습니다.");
				return;
			}
	}
	
	public void readMessage() {
		System.out.println("받은 쪽지함");
		System.out.println("---------------------------------");
		List<Message> list = mapper.selectReceiveMessageList(LogInController.logInUser.getId());
		
		if(list.size()==0) {
			System.out.println("받은 쪽지가 없습니다.");
			return;	
		}
		
		for(int i = 0; i<list.size() ; i++) {
			Message message = list.get(i);
			if(message.getReceiveDel().equals("y")) {
				list.remove(i);
			}
		}
		System.out.println("번호\t  보낸사람\t\t제목\t\t\t시간\t\t\t");
		for(int i = 0; i<list.size() ; i++) {
			Message message = list.get(i);
			System.out.printf((i+1)+"\t%5s \t\t%s\t\t%s%n",message.getSendName(),message.getTitle(),sdf.format(message.getTime()));
		}
		
		
		System.out.println("---------------------------------");
		System.out.print("조회할 쪽지번호를 입력해주세요 : ");
		int no = Integer.parseInt(sc.nextLine());
		if(no>list.size()||no<1) {
			System.out.println("쪽지 번호를 확인해 주세요.");
			return;
		}
		Message message =list.get(no-1);
		message.setStatus(1);
		mapper.updateStatusMessage(message);
		
		System.out.println("---------------------------------");
		System.out.println("보낸이 : "+message.getSendName());
		System.out.println("제목 : "+message.getTitle());
		System.out.println("내용 : "+message.getContent());
		System.out.println("받은날짜 : "+sdf.format(message.getTime()));
		System.out.println("---------------------------------");
		System.out.println("1.삭제");
		System.out.println("0.뒤로");
		System.out.println("---------------------------------");
		System.out.print("메뉴중 처리할 항목을 선택하세요 : ");
		int no1 = Integer.parseInt(sc.nextLine());
		
		switch(no1) {
		case 1 : 
			message.setReceiveDel("y");
			mapper.deleteReceiveMessage(message);
			if(message.getSendDel().equals("y")&&message.getReceiveDel().equals("y")) {
				mapper.deleteMessage(message);
			}
			System.out.println("쪽지 삭제를 완료했습니다.");
			return;
		case 0 :
			break;
			default :
				System.out.println("없는번호입니다.");
				System.out.println("메뉴를 확인해 주세요.");
		}
	}
	
	
	
	public void sendMessage() {
		System.out.println("보낸 쪽지함");
		System.out.println("---------------------------------");
		List<Message> list = mapper.selectSendMessageList(LogInController.logInUser.getId());
		if(list.size()==0) {
			System.out.println("보낸 쪽지가 없습니다.");
			return;	
		}
		
		System.out.println("번호\t  받은사람\t\t제목\t\t\t시간\t\t\t읽은상태");
		
		for(int i = 0; i<list.size() ; i++) {
			Message message = list.get(i);
			if(message.getSendDel().equals("y")) {
				list.remove(i);
			}
		}
		
		for(int i = 0; i<list.size() ; i++) {
			Message message = list.get(i);
			System.out.printf((i+1)+"\t%5s \t\t%-40s%s\t%s%n",message.getReceiveName(),message.getTitle(),sdf.format(message.getTime()),
					(message.getStatus()==1 ? "읽음" : "안읽음"));
		}
		
		System.out.println("---------------------------------");
		System.out.print("조회할 쪽지번호를 입력해주세요 : ");
		int no = Integer.parseInt(sc.nextLine());
		if(no>list.size()||no<0) {
			System.out.println("쪽지 번호를 확인해 주세요.");
			return;
		}
		Message message =list.get(no-1);
		System.out.println("---------------------------------");
		System.out.println("받는이 : "+message.getReceiveName());
		System.out.println("제목 : "+message.getTitle());
		System.out.println("내용 : "+message.getContent());
		System.out.println("받은날짜 : "+sdf.format(message.getTime()));
		System.out.println("---------------------------------");
		System.out.println("1.삭제");
		System.out.println("0.뒤로");
		System.out.print("메뉴중 처리할 항목을 선택하세요 : ");
		int no1 = Integer.parseInt(sc.nextLine());
		switch(no1) {
		case 1 : 
			message.setSendDel("y");
			mapper.deleteSendMessage(message);
			if(message.getSendDel().equals("y")&&message.getReceiveDel().equals("y")) {
				mapper.deleteMessage(message);
			}
			System.out.println("쪽지 삭제를 완료했습니다.");
			
			return;
		case 0 :
			break;
			default :
				System.out.println("없는번호입니다.");
				System.out.println("메뉴를 확인해 주세요.");
		}
	}
	
	
	public void service() {
		System.out.println("쪽지");
		System.out.println("---------------------------------");
		System.out.println("1.쪽지쓰기");
		System.out.println("2.받은 쪽지함");
		System.out.println("3.보낸 쪽지함");
		System.out.println("4.뒤로");
		System.out.println("---------------------------------");
		System.out.print("메뉴중 처리할 항목을 선택하세요 : ");
		int no = Integer.parseInt(sc.nextLine());
		switch(no) {
		case 1 :
			writeMessage();
			break;
		case 2 :
			readMessage();
			break;
		case 3 :
			sendMessage();
			break;
		case 4 : 
			return;
			default : 
				System.out.println("없는번호입니다.");
				System.out.println("메뉴를 확인해 주세요.");
		}
	}
}
