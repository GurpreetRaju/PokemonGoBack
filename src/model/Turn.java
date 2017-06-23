package model;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

import controller.GameController;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Turn {
	
	private static Turn turn;
	private AIplayer ai;
	private UserPlayer user;
	
	private Turn(){
		
	}
	
	public static Turn getInstance(){
        if(turn == null){
            turn = new Turn();
        }
        return turn;
    }
	
	public void setPlayer(AIplayer newAi, UserPlayer newuser){
		ai = newAi;
		user = newuser;
	}
	
	public Player getCurrentPlayer(){
		if(ai.getTurn()){
			return ai;
		}
		else{
			return user;
		}
	}
	
	public void changeTurn(){
		
		if(this.getCurrentPlayer().getActivePokemon()!=null){
			if(this.getCurrentPlayer().getActivePokemon().isHealed()){
				this.getCurrentPlayer().getActivePokemon().resetHealStatus();
			}
			if(!this.getCurrentPlayer().getBench().getAllPokemonCard().isEmpty()){
				for(Pokemon p:this.getCurrentPlayer().getBench().getAllPokemonCard()){
					p.resetHealStatus();
				}
			}
			ArrayList<Pokemon> pokemons = this.getCurrentPlayer().getPokemonFromBenchAndActive();
			if(pokemons!=null && !pokemons.isEmpty()){
				if(!pokemons.isEmpty()){
					for(Pokemon p:pokemons){
						switch(p.getStatus()){
							case "asleep":
								Random random = new Random();
								int number = random.nextInt(2);
								if(number == 0){
									p.setState("normal");
								}
								break;
							case "paralyzed": case "stuck":
								p.setStatus("normal");
								break;
							case "poisoned":
								p.addDamage(10);
								break;
						}
					}
				}
			}
		}
		
		if(user.getTurn()){
			user.setTurn(false);
			GameController.getInstance().dealCard("ai");
			ai.setTurn(true);
			if(ai.getActivePokemon() !=null && user.getActivePokemon() !=null)
				Pokemon.getTurnEndAbilities(ai);
		}
		else{
			user.setTurn(true);
			GameController.getInstance().dealCard("user");
			//GameController.getInstance().ulabelUpdate();
			ai.setTurn(false);		
			if(ai.getActivePokemon() !=null && user.getActivePokemon() !=null)
				Pokemon.getTurnEndAbilities(user);
		}
		GameController.getInstance().ulabelUpdate();
		
	}
	
	public Player getOpponent(){
		if(user!=null){
			if(user.getTurn()){
				return ai;
			}
			else{
				return user;
			}
		}
		return null;
	}
	
	public boolean[] toss(){
		boolean[] turn = {true,false};
        String dec;
        ButtonType toss = new ButtonType("Toss", ButtonBar.ButtonData.OK_DONE);
        Alert alert = new Alert(Alert.AlertType.INFORMATION,"It's Time for Toss ",toss);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setHeaderText(null);
        alert.setX(475);
        alert.setY(270);
        alert.setHeight(50);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == toss)
        {
            // ... user chose OK
            //head or tail
            Circle circle = new Circle();
            circle.setCenterX(200.0f);
            circle.setCenterY(135.0f);
            //Setting the radius of the circle
            circle.setRadius(25.0f);
            //Setting the color of the circle
            circle.setFill(javafx.scene.paint.Color.BROWN);
            //Setting the stroke width of the circle
            circle.setStrokeWidth(20);
            //Instantiating the path class
            javafx.scene.shape.Path path = new javafx.scene.shape.Path();
            //Creating the MoveTo path element
            MoveTo moveTo = new MoveTo(200, 250);
            //Creating the Cubic curve path element
            CubicCurveTo cubicCurveTo1 = new CubicCurveTo(200, 250, 200, 250, 200, 250);
            CubicCurveTo cubicCurveTo = new CubicCurveTo(200, 40, 200, 40,200, 40);
            //Adding the path elements to Observable list of the Path class
            path.getElements().add(moveTo);
            path.getElements().add(cubicCurveTo);
            path.getElements().add(cubicCurveTo1);
            //Creating a path transition
            PathTransition pathTransition = new PathTransition();
            //Setting the duration of the path transition
            pathTransition.setDuration(Duration.millis(4000));
            //Setting the node for the transition
            pathTransition.setNode(circle);
            //Setting the path
            pathTransition.setPath(path);
            //Setting the cycle count for the transition
            pathTransition.setCycleCount(1);
            //Setting auto reverse value to false
            //pathTransition.setAutoReverse(true);
            //Playing the animation
            pathTransition.play();
            //Creating a Group object
          // Thread.sleep(1000);
            Random random = new Random();
            int number = random.nextInt(2)+1;
            //public String dec;
            ButtonType Head = new ButtonType("Head", ButtonBar.ButtonData.YES);
            ButtonType Tail = new ButtonType("Tail", ButtonBar.ButtonData.NO);
            Alert ts = new Alert(Alert.AlertType.INFORMATION,"Select Your Coin_S",Head,Tail);
            ts.initStyle(StageStyle.UNDECORATED);
            ts.setHeaderText(null);
            ts.setX(475);
            ts.setY(270);
            System.out.print(number+"\n");
            Optional<ButtonType> result1 = ts.showAndWait();
            if ((result1.get().getText().toString() == "Head") && number==1)
            {
                //num2=1;
                dec="Congratulations you won the toss";
                turn[0] = true;
                turn[1] = false;
                System.out.print(dec);
            }
            else if ((result1.get().getText().toString()== "Tail") && number==2)
            {
                // num2=2;
                dec="Congratulations you won the toss";
                turn[0] = true;
                turn[1] = false;
                System.out.print(dec);
            }
            else
            {
                dec="Ooops!! You can't make it";
                turn[1] = true;
                turn[0] = false;
                System.out.print(dec);
            }
            
        }
        else
        {
            // ... user chose CANCEL or closed the dialog
        }		
        return turn;
	}

}