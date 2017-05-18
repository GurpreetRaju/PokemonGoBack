package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DeckFileReader {
	private String deck1file = "resources/deck1.ptcgo.txt";
	private String deck2file = "resources/deck2.ptcgo.txt";
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
		FileReader fr = null;

		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader(filename));
			br.readLine();
			String type = new String();
			
			while ((sCurrentLine = br.readLine()) != null) {
				
				if(sCurrentLine.startsWith("##")){
					type = sCurrentLine.substring(2, 2);
				}
				if(!sCurrentLine.startsWith("* ")){
					continue;
				}
				sCurrentLine = sCurrentLine.replace("* ","");
				String[] cline = sCurrentLine.split(" ", 2);
				String[] card = new String[cline.length+1];
				for(int x=0;x<cline.length;x++){
					card[x] = cline[x];
				}
				card[card.length-1] = type;
				deck.add(cline);
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
