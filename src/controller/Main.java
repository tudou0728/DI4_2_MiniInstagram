package controller;

public class Main {

	public static void main(String[] args) {
		ConnBD connBD=new ConnBD();
		connBD.connect();
		
		connBD.close();

	}

}
