//회원가입기능

package kr.co.bit_camp.usercontroller;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.session.SqlSession;

import kr.co.bit_camp.controller.Controller;
import kr.co.bit_camp.dao.UserMapper;
import kr.co.bit_camp.db.MyAppSqlConfig;
import kr.co.bit_camp.vo.User;

public class UserController  implements Controller{
	Scanner sc = new Scanner(System.in);
	public String input(String msg) {
		System.out.print(msg);
		return sc.nextLine();
	}
	private UserMapper mapper;
	public UserController() {
		SqlSession session = MyAppSqlConfig.getSqlSession();
		mapper = session.getMapper(UserMapper.class);
	}
	
	public boolean duplicateId(User user) {
		List<User> list = mapper.selectUserList();
			for(int i=0;i<list.size();i++) {
				User u=list.get(i);
				if(u.getId().equals(user.getId())) {
					return true;
				}
			}
	 	return false;
	}
	public boolean duplicateEmail(User user) {
		List<User> list = mapper.selectUserList();
		for(int i=0;i<list.size();i++) {
			User u=list.get(i);
			if(u.getEmail().equals(user.getEmail())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean emailForm(String email) {
		boolean err = false;
		  String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";   
		  Pattern p = Pattern.compile(regex);
		  Matcher m = p.matcher(email);
		  if(m.matches()) {
		   err = true; 
		  }
		  return err;
	}
	
	
	
	
	public void service() {
		System.out.println("회원가입");
		User user = new User();
		user.setId(input("아이디를 입력해주세요(12자 이하) : "));
		
		//아이디 중복검사
		if(duplicateId(user)) {
			System.out.println("중복된 아이디입니다. 다른 아이디를 입력해 주세요.");
			return;
		}
		
		user.setPassword(input("비밀번호를 입력해주세요(12자 이하): "));
		user.setName(input("이름을 입력해주세요 : "));
		user.setEmail(input("이메일를 입력해주세요 : "));
		
		
		
		
		if(emailForm(user.getEmail())==false) {											 		//이메일 형태검사
			System.out.println("올바른 이메일 형식이 입력되지 않았습니다. 이메일을 확인해주세요.");
			return;
		}
		if(duplicateEmail(user)) {											 		 //이메일 중복 검사
			System.out.println("중복된 이메일입니다. 다른 이메일을 입력해 주세요.");
			return;
		}
		if(user.getId().length()<4||user.getPassword().length()<4) {
			System.out.println("아이디와 비밀번호는 4자이상 입력해주세요.");
			return;
		}
		if(user.getId().length()>12||user.getPassword().length()>12||user.getEmail().length()>30||user.getName().length()>15) {	    //글자수 체크		
			System.out.println("글자수에 맞게 내용을 입력해주세요");
			return;
		}
		if(user.getId().equals("") || user.getPassword().equals("")||user.getName().equals("")) { 								//입력내용 공백체크
			System.out.println("---------------------------------");
			System.out.println("입력내용을 확인해주세요.");
			return;
		}
		
		
		mapper.insertUser(user);
		System.out.println("회원등록이 완료되었습니다.");
	}
}
