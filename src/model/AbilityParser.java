package model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AbilityParser {

	private String abilityName, target, destination, drawCards, status, energyinfo, abilityparse, amount ;
	private String damage, condition, condAbility, trigger, triggerCond, source, filter, filterCat, count, choice;

	ability addAbility = null;
	ArrayList<Energy> EnergyInfo = new ArrayList<Energy>();;

	public int indexOf(String pattern, String s) {
		Pattern patternString = Pattern.compile(pattern);
	    Matcher matcher = patternString.matcher(s);
	    return matcher.find() ? matcher.end() : -1;
	}

	public ability parseAbilities(String ablty)
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
					return parseAbility(abilityElement);
		}
			
			//}
		public ability parseAbility(String abilityElement){
			abilityElement = abilityElement.replace("(", " (");
			ability ability = null;
			ArrayList<String> sub = new ArrayList<String>();
			//Debug.message(abilityElement);
			String [] abilitytest = abilityElement.split(",");
			if(abilitytest.length>1){
				for (String b: abilitytest)
				{
					sub.add(b);
				//composite ability do here
					//Debug.message(a);
				}
				ability compositeAbility = new CompositeAbility();
				for(int i=0; i<sub.size();i++)
				{
					String a = sub.get(i);
					if(a.contains("(") && !a.contains(")"))
					{
					
						a = sub.get(i) + "," + sub.get(i+1);
						sub.remove(i+1);
					}
					if(sub.size()>1){
						String array[] = a.replace("-", "").split(" ");
						((CompositeAbility) compositeAbility).add(getAbility(abilityName, array,EnergyInfo));
					// add composite ability here and then add composite ability to abilities arraylist.
					}
					else{
						String array[] = a.replace("-", "").split(" ");
						//Debug.message("Normal ability: " +abilityName);
						ability = (getAbility(abilityName, array, EnergyInfo));
					}
					
				}
				if(((CompositeAbility) compositeAbility).size()!=0){
					int i = 0;
					for(ability a: ((CompositeAbility) compositeAbility).get()){
						//Debug.message("Composite - "+i+" Ability "+ a.getName());
						i++;
					}
					ability = compositeAbility;
				}
				
			}
				
			else{
				//String[] a = abilitytest;
					String array[] = abilityElement.replace("-", "").split(" ");
					ability = getAbility(abilityName, array,EnergyInfo);
					//change type void to ability of get ability and return ability.
					//add the return object to the ability arraylist.
			}
			
			return ability;
		}

	
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
				ability[] abilities = conditionAbilityParser(condAbility,condition);
				
				abilityo = new condAbility(name, condition, abilities[2], abilities[0], abilities[1]);
				//abilityo = new Search("Search pokemon", "you", "deck","pokemon","basic",2);
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
				abilityo = new Redamage(name, source, destination, count);
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
				abilityo = new destatAbility(name, target);
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
				//Debug.message(a[3]);
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

	private ability[] conditionAbilityParser(String condAbility, String newCondition) {
		ability[] abilities = new ability[3];
		if(newCondition.contains("count")){
			newCondition = "count";
		}
		String abilityCondition = null;
		switch(newCondition){
			case "flip": case "choice":
				condAbility = condAbility.substring(condAbility.indexOf(" ")+1);
				break;
			case "healed":
				condAbility = condAbility.substring(nthIndexOf(condAbility, ' ', 3)+1);
				break;
			case "count":
				condAbility = condAbility.substring(nthIndexOf(condAbility, ' ', 4)+1);
				break;	
			case "ability":
				abilityCondition = condAbility.substring(0,condAbility.indexOf("(")-1);
				condAbility = condAbility.substring(condAbility.indexOf("("));				
				
				//condAbility = condAbility.substring(condAbility.indexOf("(")+1, condAbility.indexOf(")")-1);
				break;
		}
		if(condAbility.contains("else")){
			abilities[1] = this.parseAbility(condAbility.substring(condAbility.indexOf("else")+5));
			condAbility = condAbility.substring(0,condAbility.indexOf("else")-2);
		}
		if(abilityCondition!=null){
			abilities[2] = this.parseAbility(abilityCondition);
		}
		if(condAbility.contains(",") && condAbility.contains("(") && condAbility.contains(")")){
		    Matcher m = Pattern.compile("\\(([^,]+)\\,").matcher(condAbility);
			String temp = null;
		    while(m.find()) {
		       temp = m.group(1);    
		    }
		    String temp2 = null;
		    Debug.message(temp);
		    m = Pattern.compile("\\,([^)]+)\\)").matcher(condAbility);
		    while(m.find()){
		    	temp2= m.group(1);
		    }
		    condAbility = temp+","+temp2;
		    Debug.message(condAbility);
		}
		abilities[0] = this.parseAbility(condAbility);
		Debug.message(abilities[0].getName());
		
		return abilities;
	}

	public static void main(String[] arg){
		AbilityParser ap = new AbilityParser();
		ArrayList<Energy> energyinfo = null;
		/*
		cond:flip:dam:target:opponent-active:30
		cond:flip:applystat:status:paralyzed:opponent-active
		cond:flip:dam:target:opponent-active:10
		cond:flip:deenergize:target:opponent-active:1***
		cond:flip:dam:target:opponent-active:30
		cond:flip:deenergize:target:opponent-active:1
		cond:flip:dam:target:opponent-active:20
		cond:flip:heal:target:your-active:10
		cond:flip:dam:target:opponent-active:10
		cond:flip:dam:target:opponent-active:40:else:applystat:status:paralyzed:opponent-active
		cond:flip:dam:target:opponent-active:30:else:dam:target:your-active:30
		cond:flip:(applystat:status:asleep:opponent-active,applystat:status:poisoned:opponent-active)
		cond:healed:target:your-active:dam:target:opponent-active:80
		cond:count(target:your-active:energy:psychic)>0:dam:target:opponent-active:20***
		cond:ability:deck:destination:discard:target:choice:you:1:(search:target:you:source:deck:filter:top:8:1,shuffle:target:you)
		cond:choice:shuffle:target:opponent
	 */
		String a = "cond:choice:shuffle:target:opponent";
		a = a.replace(":", " ");
		String array[] = a.replace("-", "").split(" ");
		for(String s: array){
			Debug.message(s+"*");
		}
		ability abilt = ap.getAbility("ConditionAbilityTest", array, energyinfo);
		abilt.useAbility();
		Debug.message("End");
	}
	
	public static int nthIndexOf(String text, char needle, int n){
	    for (int i = 0; i < text.length(); i++){
	        if (text.charAt(i) == needle) {
	            n--;
	            if (n == 0){
	                return i;
	            }
	        }
	    }

	    return -1;
	}
}




