package model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AbilityParser {


	private String abilityName, target, destination, drawCards, status, amount ;

	private String damage, condition, condAbility, trigger, triggerCond, source, filter, filterCat, count, choice;


	ability addAbility = null;

	public int indexOf(String pattern, String s) {
		Pattern patternString = Pattern.compile(pattern);
	    Matcher matcher = patternString.matcher(s);
	    return matcher.find() ? matcher.end() : -1;
	}

	public ability parseAbilities(String ablty, ArrayList<EnergyNode> energyInfo){
		//parse abilities.txt			
		abilityName = ablty.substring(0, ablty.indexOf(":"));
		String abilityElement = ablty.replace(":", " ").substring(ablty.indexOf(":")+1);
		return parseAbility(abilityElement, energyInfo);
	}
			
			//}
		public ability parseAbility(String abilityElement, ArrayList<EnergyNode> energyInfo){
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
				ability compositeAbility = new CompositeAbility(abilityName, energyInfo);
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
						((CompositeAbility) compositeAbility).add(getAbility(abilityName, array,energyInfo));
					// add composite ability here and then add composite ability to abilities arraylist.
					}
					else{
						String array[] = a.replace("-", "").split(" ");
						//Debug.message("Normal ability: " +abilityName);
						ability = (getAbility(abilityName, array, energyInfo));
					}
					
				}
				if(((CompositeAbility) compositeAbility).size()!=0){
					ability = compositeAbility;
				}
				
			}
				
			else{
				//String[] a = abilitytest;
					String array[] = abilityElement.replace("-", "").split(" ");
					ability = getAbility(abilityName, array,energyInfo);
					//change type void to ability of get ability and return ability.
					//add the return object to the ability arraylist.
			}
			
			return ability;
		}

	
	public ability getAbility(String name,String[] a, ArrayList<EnergyNode> energyInfo)
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
				abilityo = new damageAbility(name, Integer.valueOf(damage), energyInfo, target, count);
				break;
			case "cond":
				condition = a[1];
				condAbility = a_join.substring(a_join.indexOf(a[1]));
				//Debug.message(condAbility);
				ability[] abilities = conditionAbilityParser(condAbility,condition, energyInfo);
				if(condition.contains("count")){
					//Debug.message(a_join);
					Matcher m = Pattern.compile("count\\s+\\(target\\s([^)]+)\\)([^\\d]+)([^\\s]+)\\s").matcher(a_join);
					String temp = null;
					String temp2 = null;
				    String temp3 = null;
					while(m.find()) {
				       temp = m.group(1);
				       temp2= m.group(2);
				       temp3 = m.group(3);
				    }
				    condition = condition + " " + temp + " " + temp2 + " " +temp3;	
				    //Debug.message(condition);
				}
				else if(condition.contains("healed")){
					//Debug.message(a_join);
					Matcher m = Pattern.compile("healed\\starget\\s([^\\s]+)\\s").matcher(a_join);
					String temp = null;
					while(m.find()) {
				       temp = m.group(1);
				    }
				    condition = condition + " " + temp;
				   //Debug.message(condition);
				}

				abilityo = new condAbility(name, condition, abilities[2], abilities[0], abilities[1],energyInfo);

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
				
				//destination = a_join.substring(a_join.indexOf("destination "), a_join.indexOf(" ", a_join.indexOf("destination ")));
				
				Matcher m = Pattern.compile("destination\\s+([^\\s]+)\\s").matcher(a_join);
				while(m.find()) {
			       destination = m.group(1);
			    }
				int draw = 0;
				if(abilityName.equals("Act Cute")){
					drawCards = a_join.substring(indexOf("\\d",a_join)-1);
					//Debug.message(drawCards);
					drawCards = String.valueOf(drawCards.charAt(0));
					draw = Integer.parseInt(drawCards);
				}
				// drawCards empty in 1st 2 cases
				//choice = a_join.contains("count") ? a_join.substring(a_join.indexOf("count ("), a_join.indexOf(" ", a_join.indexOf("count ("))).replace(" ", "").replace("-",  "") : "null";
				if(a_join.contains("count")){
					m = Pattern.compile("count\\s+\\(([^)]+)\\)").matcher(a_join);
					while(m.find()) {
						choice = m.group(1);
					}
				}
			    			
				abilityo = new DeckAbility(name, target, destination,draw, choice, energyInfo);
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
				count = a_join.contains("count") ? "youractive energy" : a_join.substring(indexOf("\\d", a_join)-1);
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
				addAbility = getAbility(name, a[6].replace("(", "").concat(" " + a[7]).concat(" " + a[8]).concat(" "+ a[9]).split(" "), energyInfo);
				abilityo = new Add(name, target, trigger, triggerCond, addAbility);
				break;
			case "shuffle":
				abilityo = new Shuffle(name,a[2]);
				break;
		}
		return abilityo;
	}

	private ability[] conditionAbilityParser(String condAbility, String newCondition, ArrayList<EnergyNode> energyInfo) {
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
				condAbility = condAbility.substring(nthIndexOf(condAbility, ' ', 5)+1);
				break;	
			case "ability":
				abilityCondition = condAbility.substring(0,condAbility.indexOf("(")-1);
				condAbility = condAbility.substring(condAbility.indexOf("("));				
				
				//condAbility = condAbility.substring(condAbility.indexOf("(")+1, condAbility.indexOf(")")-1);
				break;
		}
		//Debug.message(condAbility);
		if(condAbility.contains("else")){
			abilities[1] = this.parseAbility(condAbility.substring(condAbility.indexOf("else")+5),energyInfo);
			condAbility = condAbility.substring(0,condAbility.indexOf("else")-2);
		}
		if(abilityCondition!=null){
			abilities[2] = this.parseAbility(abilityCondition, energyInfo);
		}
		if(condAbility.contains("(")){
			//condAbility = condAbility.substring(condAbility.indexOf("(")+1,condAbility.indexOf(")")-1);
			condAbility = condAbility.replace("(", "");
			condAbility = condAbility.replace(")", "");
		}		
		abilities[0] = this.parseAbility(condAbility, energyInfo);
		//Debug.message(abilities[0].getName());
		
		return abilities;
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




