package kr.co.bit_camp.boardcontroller;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public abstract class SearchBoardControllers {
	Scanner sc = new Scanner(System.in);
	protected String input(String msg) {
		System.out.print(msg);
		return sc.nextLine();
	}
	SimpleDateFormat sdf = new SimpleDateFormat(
			"MM/dd HH:mm");
	public abstract void service() throws Exception;

}
