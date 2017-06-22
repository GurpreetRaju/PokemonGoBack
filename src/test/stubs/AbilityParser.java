package test.stubs;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import test.stubs.*;

public class AbilityParser {

	private String abilityName, target, destination, drawCards, status, energyinfo, abilityparse, amount ;
	private String damage, condition, condAbility, trigger, triggerCond, source, filter, filterCat, count, choice;

	ability addAbility = null;
	ArrayList<Energy> EnergyInfo = new ArrayList<Energy>();
	ArrayList<ability> abilities = new ArrayList<ability>();

	public int indexOf(String pattern, String s) {
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
						abilities.add(getAbility(abilityName, array, EnergyInfo));
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
				damage = a_join.substring(indexOf("\\d", a_join)-1,indexOf("\\d+", a_join));
				target = a_join.contains("choice") ? "opponentbench" : a_join.substring(indexOf("target ", a_join), a_join.indexOf(" ", indexOf("target ", a_join)));
				count = null;
				if(a_join.contains("count"))
				{
					count = a_join.substring(indexOf("\\(target ", a_join), a_join.indexOf(")", indexOf("\\(target ", a_join)));
				}
				abilityo = new damageAbility(name, Integer.valueOf(damage), energyinfo, target, count);
				break;
			case "cond":
				condition = a[1];
				condAbility = a_join.substring(a_join.indexOf(a[1]));
				//Debug.message(condAbility);
				break;
			case "swap":
				abilityo =new swapAbility(name, a[2], a[5]);
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
				abilityo = new drawAbility(name,Integer.valueOf(drawCards),target);
				break;
			case "deck":
				//deck:target:opponent:destination:deck:count(opponent:hand)
				//deck:target:your:destination:deck:count(your-hand)
				//deck:target:them:destination:deck:bottom:choice:target:1
				target = a_join.substring(indexOf("target ", a_join), a_join.indexOf(" ", indexOf("target ", a_join)));	
				destination = a_join.substring(a_join.indexOf("destination "), a_join.indexOf(" ", a_join.indexOf("destination ")));
				drawCards = String.valueOf(indexOf("\\d", a_join));
				// drawCards empty in 1st 2 cases
				choice = a_join.contains("count") ? a_join.substring(a_join.indexOf("count ("), a_join.indexOf(" ", a_join.indexOf("count ("))).replace(" ", "").replace("-",  "") : "null";
				abilityo = new DeckAbility(name, target, destination,Integer.valueOf(drawCards), choice);
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
						filter = null;
						filterCat="evolvesfrom";
					}
				}
				abilityo = new Search(name, target, source, filter, filterCat, Integer.valueOf(drawCards));
				break;
			case "redamage":
				source = "choiceopponent";
				destination = a_join.substring(a_join.indexOf("destination "), a_join.indexOf(" ", a_join.indexOf("destination ")));
				count = a_join.contains("count") ? "opponentdamage": a_join.substring(indexOf("\\d", a_join)-1);
				abilityo = new Redamage(name, source, destination, count, a_join, 0);
				break;
			case "reenergize":
				source = "choiceyour";
				destination = "choiceyour";
				count = "1";
				abilityo = new Reenergize(name, source, destination, Integer.valueOf(count));
				break;
			case "deenergize":
				//deenergize:target:your-active:count(target:your-active:energy)
				target = a_join.substring(indexOf("target ", a_join), a_join.indexOf(" ", indexOf("target ", a_join)));
				count = a_join.contains("count") ? "youractive energy" : String.valueOf(a_join.charAt(indexOf("\\d", a_join)));
				abilityo = new Deenergize(name, target, count);
				break;
			case "applystat":
				status = a[2];
				target = a[3];
				abilityo = new applystatAbility(name, target, status);
				break;
			case "destat":
				target = "choiceyour";
				abilityo = new destatAbility(name);
				break;
			case "heal":
				if(a[2].equalsIgnoreCase("choice"))
				{
					amount = a[4];
					target = a[2]+a[3];
				}
				else{
					amount = a[3];
					target = a[2];
				}
				Debug.message(a[3]);
				abilityo = new healingAbility(name, Integer.valueOf(amount), target);
				break;
			case "add":
				target = a[2];
				trigger = a[4];
				triggerCond = a[5];
				//add:target:your:trigger:opponent:turn-end:(heal:target:self:20)
				addAbility = getAbility(name, a[6].replace("(", "").concat(" " + a[7]).concat(" " + a[8]).concat(" "+ a[9]).split(" "), EnergyInfo);
				abilityo = new Add(name, target, trigger, triggerCond, addAbility);
				break;
			case "shuffle":
				abilityo = new Shuffle(name,a[2]);
				break;
		}
		return abilityo;
	}
}




