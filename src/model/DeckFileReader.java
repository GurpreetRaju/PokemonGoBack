package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.*;

public class DeckFileReader {
	private String deck1file = "resources/deck1.txt";
	private String deck2file = "resources/deck2.txt";
	private String cardsfile = "resources/cards.txt";
	private String abilityfile = "resources/abilities.txt";
	private String abilityName, target, destination, drawCards, status, energyinfo, abilityparse ;
	private String damage, condition, condAbility, trigger, triggerCond, addAbility, source, filter, filterCat, count;
	ArrayList<Energy> EnergyInfo = new ArrayList<Energy>();
	String abilityR[] = new String[74];

	ArrayList<String[]> deck = new ArrayList<String[]>();
	ArrayList<ability> abilities = new ArrayList<ability>();
	
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
			String cardLine, abilityLine;
			br = new BufferedReader(new FileReader(filename));
			cr = new BufferedReader(new FileReader(cardsfile));
			ar = new BufferedReader(new FileReader(abilityfile));
			
			String cards[] = new String[100];   //change size
//			br.readLine();
			String Deck1[] = new String[60];
			//String type = new String();
			int i=0,j = 0, k=0;
			
			while ((cardLine = cr.readLine()) != null) {
				
				cards[i] = cardLine;
				i++;
			}
			
			while ((abilityLine = ar.readLine()) != null) {
				
				abilityR[k] = abilityLine;
				k++;
			}

			while ((sCurrentLine = br.readLine()) != null) {
				
				Deck1[j] = cards[Integer.parseInt(sCurrentLine)-1];
			
				String[] deckcard = Deck1[j].split(":");
				
				deck.add(deckcard);
						
				j++;
			}

			
			for(String [] card:deck)
			{
				//String PokemonName = card[0];
				//parse cards.txt
				switch(card[1])
				{	
					case "pokemon":
						EnergyInfo.clear();
						String carditem = String.join(" ", card);
						//String retreat = carditem.substring(carditem.indexOf("retreat cat"), carditem.indexOf("attack"));
						String ability = carditem.substring(carditem.indexOf("attack"));
						
						
						String ability1, ability2; 
						int index = indexOf("\\d\\s+\\d+", ability);
						//Debug.message(index);
						ability1 = ability.substring(8, index);
						//Debug.message(ability1);
						
						String[] abilityone = ability1.split(",");
						String[] substring11 = abilityone[0].split("\\s+");
						switch(abilityone.length)
						{
						//create objects of separate abilities and pass to a new class composite ability
							case 1:
								//parseAbilities((substring11[1]+" "+substring11[2]+" "+ abilityR[Integer.parseInt(substring11[3])-1]));
								parseAbilities(abilityR[Integer.parseInt(substring11[3])-1]);
								getEnergy(substring11[1], substring11[2]);
								
								break;
							case 2:
								String[] substring12 = abilityone[1].split("\\s+");
								//parseAbilities((substring11[1]+" "+substring11[2]+" "+substring12[1]+" "+substring12[2]+" "+abilityR[Integer.parseInt(substring12[3])-1]));
								parseAbilities(abilityR[Integer.parseInt(substring12[3])-1]);
								getEnergy(substring11[1], substring11[2]);
								getEnergy(substring12[1],substring12[2]);
								break;
						}
						
						if(ability.length() >= index+2)
						{
							ability2 = ability.substring(index+2);
							//Debug.message(ability2);
						
							String[] abilitytwo = ability2.split(",");
							String[] substring21 = abilitytwo[0].split("\\s+");
							switch(abilitytwo.length)
							{
								case 1:
//									Debug.message(substring21[3]);
//									Debug.message(abilityR[Integer.parseInt(substring21[3])-1]);
									//parseAbilities((substring21[1]+" "+substring21[2]+" "+ abilityR[Integer.parseInt(substring21[3])-1]));
									parseAbilities(abilityR[Integer.parseInt(substring21[3])-1]);
									getEnergy(substring21[1], substring21[2]);
									break;
								case 2:
									String[] substring22 = abilitytwo[1].split("\\s+");
									//parseAbilities((substring21[1]+" "+substring21[2]+" "+substring22[1]+" "+substring22[2]+" "+ abilityR[Integer.parseInt(substring22[3])-1]));
									parseAbilities(abilityR[Integer.parseInt(substring22[3])-1]);
									getEnergy(substring21[1], substring21[2]);
									getEnergy(substring22[1],substring22[2]);
									break;
							}
						}

//						for(String ab: abilityInfo)
//						{
//							Debug.message(ab);
//						}
						//Pokemon pk = new Pokemon();

						break;
					
					case "trainer":
						//Debug.message(card[4]);
						parseAbilities(abilityR[Integer.parseInt(card[4])-1]);
						break;
						
					case "energy":
						break;      
				}
				//switch()
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
	
	public static int indexOf(String pattern, String s) {
		Pattern patternString = Pattern.compile(pattern);
	    Matcher matcher = patternString.matcher(s);
	    return matcher.find() ? matcher.end() : -1;
	}
	
	public void parseAbilities(String ablty)
	{
		//parse abilities.txt			

//		for(String ablty: abilityR)
//		{
//		String[] abilityvar = ablty.split("\\s+");
//		switch(abilityvar.length){
//		case 1:
//			energyinfo = null;
//			abilityparse = abilityvar[0];
//		case 3:
//			 energyinfo = abilityvar[0] + " " + abilityvar[1];
//			 abilityparse = abilityvar[2];
//			 break;
//		case 5:
//			energyinfo = abilityvar[0] + " " + abilityvar[1] + " " + abilityvar[2] + " " + abilityvar[3];
//			 abilityparse = abilityvar[4];
//			 break;
//		}
//			abilityName = abilityparse.substring(0, abilityparse.indexOf(":"));
//			String abilityElement = abilityparse.replace(":", " ").substring(abilityparse.indexOf(":")+1);

			abilityName = ablty.substring(0, ablty.indexOf(":"));
			String abilityElement = ablty.replace(":", " ").substring(ablty.indexOf(":")+1);
			abilityElement = abilityElement.replace("(", " (");
	
			ArrayList<String> sub = new ArrayList<String>();
			String [] abilitytest = abilityElement.split(",");
			if(abilitytest.length>1){
				for (String a: abilitytest)
				{
					sub.add(a);
				//composite ability do here
					//Debug.message(a);
				}
			
				for(String a: sub)
				{
					if(a.contains("(") && !a.contains(")"))
					{
					
						a = sub.get(0) + "," + sub.get(1);
						sub.remove(1);
					}
					if(sub.size()>1){
					String array[] = a.replace("-", "").split(" ");
					getAbility(abilityName, array,EnergyInfo);
					// add composite ability here and then add composite ability to abilities arraylist.
					}
					else{
						String array[] = a.replace("-", "").split(" ");
						abilities.add(getAbility(abilityName, array,EnergyInfo));
						
					}
					
				}
			}
				
			else{
				//String[] a = abilitytest;
					String array[] = abilityElement.replace("-", "").split(" ");
					abilities.add(getAbility(abilityName, array,EnergyInfo));
					//change type void to ability of get ability and return ability.
					//add the return object to the ability arraylist.
				}
			}
			
			//}


	
	public void getEnergy(String energytype, String energynumber){
		for(int e =0; e < Integer.valueOf(energynumber);e++){
			//Debug.message(energytype);
			EnergyInfo.add(new Energy(energytype));
		}
	}
	
	public ability getAbility(String name,String[] a, ArrayList<Energy> energyinfo)
	{
		String a_join = String.join(" ", a);

		ability abilityo = null;

		switch(a[0])
		{
			case "dam":
				damage = a_join.substring(indexOf("\\d", a_join)-1);
				target = a_join.contains("choice") ? "opponentbench" : a_join.substring(indexOf("target ", a_join), a_join.indexOf(" ", indexOf("target ", a_join)));
				count = null;
				if(a_join.contains("count"))
				{
					count = a_join.substring(indexOf("\\(target ", a_join), a_join.indexOf(" ", indexOf("\\(target ", a_join)));
				}
					//dam:target:opponent-active:count(target:opponent-active:energy)*10 
					//dam:target:choice:opponent-bench:30
				
				abilityo = (new damageAbility(name, Integer.valueOf(damage), energyinfo, target, count));
				break;
			case "cond":
				condition = a[1];
				condAbility = a_join.substring(a_join.indexOf(a[1]));
//				Debug.message(condAbility);
				break;
			case "swap":
				abilityo = (new swapAbility(name, a[2], a[5]));
				break;
			case "draw":
				if(a.length == 3)
				{
					target=a[1];
					drawCards = a[2];
				}
				else
				{
					drawCards = a[1];
					target = null;
				}
				//Debug.message(drawCards);
				abilityo = (new drawAbility(name,Integer.valueOf(drawCards),target));
				break;
			case "deck":
				//deck:destination:discard:target:choice:you:1:(search:target:you:source:deck:filter:top:8:1,shuffle:target:you)
				//deck:target:opponent:destination:deck:count(opponent:hand)
				//deck:target:your:destination:deck:count(your-hand),shuffle:target:you,draw:5
				//deck:target:them:destination:deck:bottom:choice:target:1
				int i = 0;
				for(String ab: a)
				{
					//Debug.message(a_join);
					if(ab.contains("target") && a[i+1].matches("[a-z]+"))
					{
						target = a[i+1];  //remove hyphen in opponent-active
					}
					if(ab.contains("destination"))
					{
						destination = a[i+1];
						//Debug.message(target);
					}
					drawCards= String.valueOf(indexOf("\\d", ab));
					//Debug.message("deck"+drawCards);
					i++;
				}
				abilityo = new DeckAbility(name, target, destination, true, Integer.valueOf(drawCards));
				break;
			case "search":
				target = a_join.substring(indexOf("target ", a_join), a_join.indexOf(" ", indexOf("target ", a_join)));	
				source = a_join.substring(indexOf("source ", a_join), a_join.indexOf(" ", indexOf("source ", a_join)));	
				drawCards = a[a.length -1];
				filter = null;
				filterCat = null;
				if(a_join.contains("filter"))
				{
					filter = a_join.substring(indexOf("filter ", a_join), a_join.indexOf(" ", indexOf("filter ", a_join)));
					if(a_join.contains("type"))
					{
						filterCat = a_join.substring(indexOf("type ", a_join), a_join.indexOf(" ", indexOf("type ", a_join)));
					}
					else if(a_join.contains("top"))
					{
						filterCat = a_join.substring(indexOf("top ", a_join), a_join.indexOf(" ", indexOf("top ", a_join)));
					}
					else if(a_join.contains("cat"))
					{
						filter = null;
						filterCat = a_join.substring(indexOf("cat ", a_join), a_join.indexOf(" ", indexOf("cat ", a_join)));
					}
					if(a_join.contains("evolvesfrom"))
					{
						target = "yourbasic";
						filter = "null";
						filterCat="evolvesfrom";
					}
				}
				abilityo = (new Search(name, target, source, filter, filterCat, Integer.valueOf(drawCards)));
				break;
			case "redamage":
				//Debug.message(a_join);
				//redamage:source:choice:opponent:destination:opponent:count(target:last:source:damage)
				source = "choiceopponent";
				destination = a_join.substring(a_join.indexOf("destination "), a_join.indexOf(" ", a_join.indexOf("destination ")));
				count = a_join.contains("count") ? "sourcedamage": a_join.substring(indexOf("\\d", a_join)-1);
				//amount left
				abilityo = (new Redamage(name, source, destination, count));
				break;
			case "reenergize":
				//reenergize:target:choice:your:1:target:choice:your:1
				source = "choiceyour";
				destination = "choiceyour";
				count = "0";
				//amount left
				abilityo = (new Reenergize(name, source, destination, Integer.valueOf(count)));
				break;
			case "deenergize":
				//deenergize:target:your-active:count(target:your-active:energy)
				//deenergize:target:opponent-active:1
				//deenergize:target:opponent-active:1
				//deenergize:target:your-active:1:(search:target:your:source:discard:filter:cat:item:1)
				target = a_join.substring(indexOf("target ", a_join), a_join.indexOf(" ", indexOf("target ", a_join)));
				if(a_join.contains("count"))
				{
					count = "youractiveenergy"; //edit this
				}
				else
				{
					count= String.valueOf(a_join.charAt(indexOf("\\d", a_join)));
				}
				if(a_join.contains("search"))
				{
					//implement search option
				}
				abilityo = (new Deenergize(name, target, count));
				break;
			case "applystat":
				status = a[2];
				target = a[3];
				abilityo = (new applystatAbility(name, target, status));
				break;
			case "destat":
				target = "choiceyour";
				abilityo = new destatAbility(name, target);
				break;
			case "heal":
				abilityo = (new healingAbility(name, Integer.valueOf(a[3]), a[2]));
				break;
			case "add":
				target = a[2];
				trigger = a[4];
				triggerCond = a[5];
				addAbility = a_join.substring(a_join.indexOf("(")+1, a_join.indexOf(")"));
				abilityo = (new Add(name, target, trigger, triggerCond, addAbility));
				break;
			case "shuffle":
				abilityo = (new Shuffle(name,a[2]));
				break;
		}
		return abilityo;
	}
}
