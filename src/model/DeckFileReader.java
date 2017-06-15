package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
				//Debug.message(deckcard.length + " " + deckcard[1]);
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
			
			for(String [] card:deck)
			{
				int count =0;
				switch(card[1])
				{	
					case "pokemon":
						String carditem = String.join(" ", card);
						String retreat = carditem.substring(carditem.indexOf("retreat cat"), carditem.indexOf("attack"));
						String ability = carditem.substring(carditem.indexOf("attack"));
						String [] abilities = ability.split(",");
						String[] abilityone = abilities[0].split("\\s+"), abilitytwo, abilitythree;
						String [] energyType = new String[3], energyCount = new String[3], line = new String[3];
						if(abilityone.length == 4)
						{
							abilityone = (abilities[0] + " " + abilities[1]).split("\\s+");
							count=1;
							energyType[0] = abilityone[2];
							energyType[1] = abilityone[5];
							energyCount[0] = abilityone[3];
							energyCount[0] = abilityone[6];
							line[0] = abilityone[7];
						}
						else //if (abilityone.length == 5)
						{
							switch (abilities.length) 
							{
								case 1:
									count=1;
									energyType[0] = abilityone[2];
									energyCount[0] = abilityone[3];
									line[0] = abilityone[4];
									break;
								case 2:
									abilitytwo = abilities[1].split("\\s+");
									count=2;
									energyType[0] = abilityone[2];
									energyType[1] = abilitytwo[1];
									energyCount[0] = abilityone[3];
									energyCount[0] = abilitytwo[2];
									line[0] = abilityone[4];
									line[1] = abilitytwo[3];
									break;
								case 3:
									abilitytwo = abilities[1].split("\\s+");
									if(abilitytwo.length == 3)
									{
										abilitytwo = (abilitytwo.toString() + " " + abilities[2]).split("//s+");
										count=2;
										energyType[0] = abilityone[2];
										//energyType[1] = abilitytwo[1];
										//energyType[2] = abilitytwo[4];
										energyCount[0] = abilityone[3];
										//energyCount[1] = abilitytwo[2];
										//energyCount[2] = abilitytwo[5];
										line[0] = abilityone[4];
										//line[1] = abilitytwo[6];
									}
									else
									{
										//not needed yet (case not found)
										abilitythree = abilities[2].split("\\s+");
										count=3;
										energyType[0] = abilityone[2];
										energyType[1] = abilitytwo[1];
										energyType[2] = abilitythree[1];
										energyCount[0] = abilityone[3];
										energyCount[1] = abilitytwo[2];
										energyCount[2] = abilitythree[2];
										line[0] = abilityone[4];
										line[1] = abilitytwo[3];
										line[2] = abilitythree[3];
									}
									break;
							}
						}
						Debug.message(count);
						Debug.message(ability);
						break;
					
					case "trainer":
						break;
						
					case "energy":
						break;      
				}
				
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
