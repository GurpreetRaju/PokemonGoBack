package test.stubs;

import test.stubs.cardItem;

public class Debug {
	public static void message(String message){
		System.out.println(message);
	}
	public static void message(int message){
		System.out.println(Integer.toString(message));
	}
	
	public static void showCard(cardItem card){
		Debug.message(card.getID() + " " + card.getName());
	}
	
	public static void showCard(cardItem[] cards){
		for(cardItem mycard : cards){
			showCard(mycard);
		}
	}
}
