package model;

import controller.GameController;

public enum target {
	
	youractive{
			public Object getTarget(){
				return Turn.getInstance().getCurrentPlayer().getActivePokemon();
			}
		},
	opponentactive{
			public Object getTarget(){
				return Turn.getInstance().getOpponent().getActivePokemon();
				}
		},
	choiceopponent{
			public Object getTarget(){
				return GameController.getInstance().getHandandBenchPokemonsDialog(Turn.getInstance().getOpponent());
		}
	},
	choiceyour{
		public Object getTarget(){
			return GameController.getInstance().getHandandBenchPokemonsDialog(Turn.getInstance().getCurrentPlayer());
		}
	},
	opponentbench{
		public Object getTarget(){
			return GameController.getInstance().getBenchPokemonDialog(Turn.getInstance().getOpponent());
		}
	},
	yourbench{
		public Object getTarget(){
			return GameController.getInstance().getBenchPokemonDialog(Turn.getInstance().getCurrentPlayer());
		}
	},
	you{
		public Object getTarget() {
			return Turn.getInstance().getCurrentPlayer();
		}
	},
	opponent{
		public Object getTarget() {
			return Turn.getInstance().getOpponent();
		}
	};
	
	target(){}
	
	public abstract Object getTarget();
		
	public static target getTargetObject(String name){
		for(target t : target.values()){
			if(t.name().equals(name)){
				return t;
			}
		}
		return null;
	}
	
//	public static void main(String[] arg){
//		AIplayer ai = new AIplayer("F");
//		UserPlayer user= new UserPlayer("");
//		ai.setTurn(false);
//		user.setTurn(true);
//		Turn.getInstance().setPlayer(ai,user);
//		Debug.message("1");
//		System.out.print(target.getTargetObject("your").getTarget().getName());
//	}
}
