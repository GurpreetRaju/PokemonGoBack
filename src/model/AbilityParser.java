//package model;
//
//import java.util.ArrayList;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class AbilityParser {
//	private String abilityName, target, destination, drawCards, status, energyinfo, abilityparse ;
//	private String damage,condition,condAbility, trigger, triggerCond,addAbility;
//	ArrayList<Energy> EnergyInfo = new ArrayList<Energy>();
//
//	public static void parseAbility(String ablty)
//	{
//		//parse abilities.txt			
//
////		for(String ablty: abilityR)
////		{
////		String[] abilityvar = ablty.split("\\s+");
////		switch(abilityvar.length){
////		case 1:
////			energyinfo = null;
////			abilityparse = abilityvar[0];
////		case 3:
////			 energyinfo = abilityvar[0] + " " + abilityvar[1];
////			 abilityparse = abilityvar[2];
////			 break;
////		case 5:
////			energyinfo = abilityvar[0] + " " + abilityvar[1] + " " + abilityvar[2] + " " + abilityvar[3];
////			 abilityparse = abilityvar[4];
////			 break;
////		}
////			abilityName = abilityparse.substring(0, abilityparse.indexOf(":"));
////			String abilityElement = abilityparse.replace(":", " ").substring(abilityparse.indexOf(":")+1);
//
//			abilityName = ablty.substring(0, ablty.indexOf(":"));
//			String abilityElement = ablty.replace(":", " ").substring(ablty.indexOf(":")+1);
//			abilityElement = abilityElement.replace("(", " (");
//	
//			ArrayList<String> sub = new ArrayList<String>();
//			for (String a: abilityElement.split(","))
//			{
//				sub.add(a);
//					//Debug.message(a);
//			}
//			
//			for(String a: sub)
//			{
//				if(a.contains("(") && !a.contains(")"))
//				{
//					
//					a = sub.get(0) + "," + sub.get(1);
//					sub.remove(1);
//				}
//				
//
//				String array[] = a.replace("-", "").split(" ");
//				getAbility(abilityName, array,EnergyInfo);
//			//}
//
//			
//		}
//	}
//	
//	public void getEnergy(String energytype, String energynumber){
//		for(int e =0; e < Integer.valueOf(energynumber);e++){
//			Debug.message(energytype);
//			EnergyInfo.add(new Energy(energytype));
//		}
//	}
//	
//	public void getAbility(String name,String[] a, ArrayList<Energy> energyinfo)
//	{
//		String a_join = String.join(" ", a);
////		for(String ab: a)
////			Debug.message(ab);
//		//String[] energyvalues = energyinfo.split("//s+");
//		switch(a[0])
//		{
//			case "dam":
//				//for(String ab: a)
////					Debug.message(String.join(" ", a));
//				if(! a_join.contains("choice"))
//				{
//					if(! a_join.contains("else"))
//					{
//						target = a_join.substring(indexOf("target ", a_join), a_join.indexOf(" ", indexOf("target ", a_join)));
//						target = target.replace("-", "");
//						damage = a_join.substring(indexOf("\\d+", a_join)-2);
////						Debug.message(target);
//					}
//					else
//					{
//						//implement contains else condition (choice)
//						target = a_join.substring(indexOf("\\d+", a_join)-2, a_join.indexOf("else"));
//					}
//
//					
//				}
//				else
//				{
//					//contains choice
//				}
//				break;
//			case "cond":
//				//for(String ab: a)
////					Debug.message(String.join(" ", a));
////				Debug.message(" ");
//				condition = a[1];
//				condAbility = a_join.substring(a_join.indexOf(a[1]));
////				Debug.message(condAbility);
//				break;
//			case "swap":
////				for(String ab: a)
////					Debug.message(ab);
//				break;
//			case "draw":
////				for(String ab: a)
////					Debug.message(ab);
//				if(a.length == 3)
//				{
//					target=a[1];
//					drawCards = a[2];
//				}
//				else
//				{
//					drawCards = a[1];
//				}
//				//Debug.message(drawCards);
//				break;
//			case "deck":
//				int i = 0;
//				for(String ab: a)
//				{
//					//Debug.message(ab);
//					if(ab.contains("target") && a[i+1].matches("[a-z]+"))
//					{
//						target = a[i+1];  //remove hyphen in opponent-active
//					}
//					if(ab.contains("destination"))
//					{
//						String destination = a[i+1];
//						//Debug.message(target);
//					}
//					drawCards= String.valueOf(indexOf("\\d", ab));
//					//Debug.message("deck"+drawCards);
//					i++;
//				}
//				break;
//			case "search":
//				break;
//			case "redamage":
//				break;
//			case "reenergize":
//				break;
//			case "applystat":
////				for(String ab: a)
////					Debug.message(ab);
//				status = a[2];
//				target = a[3];
//
//				//Debug.message(target);
//				break;
//			case "heal":
//				break;
//			case "add":
//				target = a[2];
//				trigger = a[4];
//				triggerCond = a[5];
//				addAbility = a_join.substring(a_join.indexOf("(")+1, a_join.indexOf(")"));
//				break;
//			case "shuffle":
//				target = a[2];
//				break;
//				
//		}
//	}
//
//	public static int indexOf(String pattern, String s) {
//		Pattern patternString = Pattern.compile(pattern);
//	    Matcher matcher = patternString.matcher(s);
//	    return matcher.find() ? matcher.end() : -1;
//	}
//}
