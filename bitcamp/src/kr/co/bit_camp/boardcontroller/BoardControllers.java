package kr.co.bit_camp.boardcontroller;

import java.util.Scanner;

public abstract class BoardControllers{
	public Scanner sc = new Scanner(System.in);
	protected String input(String msg) {
		System.out.print(msg);
		return sc.nextLine();
	}
	public abstract void service() throws Exception;
}
