package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DeckFileReader {
	private String deck1file = "resources/deck1.txt";
	private String deck2file = "resources/deck2.txt";
	private String cardsfile = "resources/cards.txt";
	private String abilityfile = "resources/abilities.txt";
	
	ArrayList<String[]> deck = new ArrayList<String[]>();
	
	public DeckFileReader (int i){
		switch(i){
			case 1:	
				readDeck(deck1file);
				break;
			case 2:
				readDeck(deck2file);
				break;
		}
	}
	
	public void readDeck(String filename){
		
		BufferedReader br = null;
		BufferedReader cr = null;
		BufferedReader ar = null;
		FileReader fr = null;

		try {

			String sCurrentLine;
			String cardLine;
			br = new BufferedReader(new FileReader(filename));
			cr = new BufferedReader(new FileReader(cardsfile));
			ar = new BufferedReader(new FileReader(abilityfile));
			
			String cards[] = new String[100];   //change size
//			br.readLine();
			String Deck1[] = new String[60];
			//String type = new String();
			int i=0,j = 0;
			
			while ((cardLine = cr.readLine()) != null) {
				
				cards[i] = cardLine;
				i++;
			}
			
			
			while ((sCurrentLine = br.readLine()) != null) {
				
				
				Deck1[j] = cards[Integer.parseInt(sCurrentLine)-1];
				//Debug.message(Deck1[j]);
				String[] deckcard = Deck1[j].split(":");
				Debug.message(deckcard.length + " " + deckcard[1]);
				//String[] card = deckcard[0];
				deck.add(deckcard);
						
//				switch(deckcard[1]){
//				case "trainer":
//					break;
//				
//				case "energy":
//					break;
//					
//				case "pokemon":
//					break;
//				}
				
				
				//Debug.message(sCurrentLine);
				j++;
				
				
//				if(sCurrentLine.startsWith("##")){
//					type = sCurrentLine.substring(2, 3);
//					//Debug.message(type);
//				}
//				if(!sCurrentLine.startsWith("* ")){
//					continue;
//				}
//				sCurrentLine = sCurrentLine.replace("* ","");
//				String[] cline = sCurrentLine.split(" ", 2);
//				String[] card = new String[cline.length+1];
//				for(int x=0;x<cline.length;x++){
//					card[x] = cline[x];
//				}
//				card[card.length-1] = type;
//				deck.add(card);
//				//Debug.message(card[0]+" "+card[1]+" "+card[2]);
				
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
	}
	
	public ArrayList<String[]> getDeck(){
		return this.deck;
	}
	
	public static void main(String[] arg){
		DeckFileReader deck = new DeckFileReader(1);
		deck.getDeck();
	}
}
