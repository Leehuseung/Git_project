package kr.co.bit_camp.dao;

import java.util.List;

import kr.co.bit_camp.vo.User;

public interface UserMapper {
	void insertUser(User user); 
	List<User> selectUserList();
	void updatePassword(User user);
	void deleteUser(String name);
	List<User> selectUserNoName();
}
