package userInterfaces;

import java.util.Scanner;

public class consoleUI implements Messageable {
	Scanner scn = new Scanner(System.in);
	@Override
	public void showMessage(String msg) {
		System.out.println(msg);
	}

	@Override
	public String getMessage(String msg) {
		System.out.println(msg);
		return scn.nextLine();
	}

	@Override
	public void showErrMessage(String msg) {
		System.err.println(msg);
	}



}
