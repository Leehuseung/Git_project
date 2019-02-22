package kr.co.bit_camp.dao;

import java.util.List;

import kr.co.bit_camp.vo.Message;
import kr.co.bit_camp.vo.User;

public interface MessageMapper {
	void writeMessage(Message message);
    List<Message> selectReceiveMessageList(String id);
    List<Message> selectSendMessageList(String id);
	void updateStatusMessage(Message message);
	void deleteReceiveMessage(Message message);
	void deleteSendMessage(Message message);
	void deleteMessage(Message message);
}
