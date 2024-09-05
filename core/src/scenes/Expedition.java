package scenes;

import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import config.GameScreen;
import storage.Storage;

public class Expedition implements Screen{
	Skin skin;
	Viewport vp;
	public Stage stage;
	private Game game;
	private GameScreen gameScreen;
	private Storage storage;
	private TextButton throwDice, skill1, skill2, skill3, trait, reset, sorc, barb, thief;
	private Label dice1, dice2, dice3, dice4, dice5, d20, total, playerHP, enemyNameLbl, objLbl, 
			skillDesc, objNumLbl;
	private Random rand = new Random();
	private int[] dices = {0, 0, 0, 0, 0};
	private boolean warcry, blink, newGame = true, shriek, bite, stealth, steal;
	private String objective, enemyName, eTrait;
	private int objNum, str, vig, wis, dex, traitCount = 1, hp, maxHP, eDmg, blinkCount = 1;
	private int char1HP, char2HP, char1MaxHP, char2MaxHP, activeChar, encCleared = 0,
			char3HP, char3MaxHP, normalEnemy, eliteEnemy, bossEnemy, traps;
	
	public Expedition(Viewport viewport, Game game, GameScreen gameScreen) {
		this.gameScreen = gameScreen;
		this.game = game;
		stage = new Stage(viewport);
		vp = viewport;
		Gdx.input.setInputProcessor(stage);
		storage = Storage.getInstance();
		storage.createFont();
		skin = storage.skin;
		
		normalEnemy = 4;
		traps = 2;
		
		setEncounter();
				
		createComponents();
		setSkills();
		
		addEnterListener(skill1, skill1.getText().toString());
	    addEnterListener(skill2, skill2.getText().toString());
	    addEnterListener(skill3, skill3.getText().toString());
	    addEnterListener(trait, trait.getText().toString());
	    addEnterListener(throwDice, Home.selectedClass1);
	}
	
	private void addEnterListener(final TextButton skill, final String description) {
	    skill.addListener(new InputListener() {
	        @Override
	        public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
	            showDescription(description);
	        }

	        @Override
	        public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
	            skillDesc.setText("");
	        }
	    });
	}
	
	private void showDescription(String button) {
		switch(button) {
		case "Double Swing":
			skillDesc.setText(storage.doubleSwing.getDescription());
			break;
		case "Skullbash":
			skillDesc.setText(storage.skullbash.getDescription());
			break;
		case "Stand Your Ground":
			skillDesc.setText(storage.standYourGround.getDescription());
			break;
		case "Warcry":
			skillDesc.setText(storage.warcry.getDescription());
			break;
		case "Fireball":
			skillDesc.setText(storage.fireball.getDescription());
			break;
		case "Hailstorm":
			skillDesc.setText(storage.hailstorm.getDescription());
			break;
		case "Blink":
			skillDesc.setText(storage.blink.getDescription());
			break;
		case "Tweak":
			skillDesc.setText(storage.tweak.getDescription());
			break;
		case "Throw Knife":
			skillDesc.setText(storage.throwKnife.getDescription());
			break;
		case "Shadowstep":
			skillDesc.setText(storage.shadowstep.getDescription());
			break;
		case "Stealth":
			skillDesc.setText(storage.stealth.getDescription());
			break;
		case "Steal":
			skillDesc.setText(storage.steal.getDescription());
			break;
		case "Sorcerer":
		case "Barbarian":
		case "Thief":
			showAvailableDice();
			break;
		}
	}
	
	private void showAvailableDice() {
		switch(objective) {
		case "Strength":
			skillDesc.setText("Roll a d" + str);
			break;
		case "Vigor":
			skillDesc.setText("Roll a d" + vig);
			break;
		case "Wisdom":
			skillDesc.setText("Roll a d" + wis);
			break;
		case "Dexterity":
			skillDesc.setText("Roll a d" + dex);
			break;
		}
	}

	private void setEncounter() {
		if(normalEnemy > 0 && traps > 0)
			chooseEnemy(rand.nextInt(1, 3));
		else if(normalEnemy > 0 && traps <= 0)
			chooseEnemy(1);
		else if(normalEnemy <= 0 && traps > 0)
			chooseEnemy(2);
	}
	
	/* x = 1 - Normal enemy
	 * x = 2 - Trap
	 * x = 3 - Elite enemy */
	private void chooseEnemy(int x) {
		if(x == 1) {
			switch(rand.nextInt(1, 4)) {
			case 1:
				enemyName = storage.goblin.getName();
				objective = storage.goblin.getObjective();
				objNum = storage.goblin.getValue();
				eDmg = storage.goblin.getAttackPower();
				eTrait = storage.goblin.getTrait();
				break;
			case 2:
				enemyName = storage.rat.getName();
				objective = storage.rat.getObjective();
				objNum = storage.rat.getValue();
				eDmg = storage.rat.getAttackPower();
				eTrait = storage.rat.getTrait();
				break;
			case 3:
				enemyName = storage.skeleton.getName();
				objective = storage.skeleton.getObjective();
				objNum = storage.skeleton.getValue();
				eDmg = storage.skeleton.getAttackPower();
				eTrait = storage.skeleton.getTrait();
				break;
			}
			
			normalEnemy--;
		}
		else if(x == 2) {
			switch(rand.nextInt(1, 2)) {
			case 1:
				enemyName = storage.spikeTrap.getName();
				objective = storage.spikeTrap.getObjective();
				objNum = storage.spikeTrap.getValue();
				eDmg = storage.spikeTrap.getAttackPower();
				eTrait = storage.spikeTrap.getTrait();
				break;
			}
			
			traps--;
		}
	}
	
	private void setSkills() {
		if(Home.selectedClass1.equals("Barbarian")) {
			skill1.setText(storage.doubleSwing.getName());
			skill2.setText(storage.skullbash.getName());
			skill3.setText(storage.standYourGround.getName());
			trait.setText(storage.warcry.getName());
			str = 12;
			vig = 8;
			wis = 4;
			dex = 6;
			if(newGame) {
				char1MaxHP = storage.barbarian.getMaxHP();
				char1HP = storage.barbarian.getMaxHP();
			}	
			activeChar = 1;
		}	
		else if(Home.selectedClass1.equals("Sorcerer")) {
			skill1.setText(storage.fireball.getName());
			skill2.setText(storage.hailstorm.getName());
			skill3.setText(storage.blink.getName());
			trait.setText(storage.tweak.getName());
			str = 4;
			vig = 6;
			wis = 12;
			dex = 8;
			if(newGame) {
				char2MaxHP = storage.sorcerer.getMaxHP();
				char2HP = storage.sorcerer.getMaxHP();
			}	
			activeChar = 2;
		}
		else if(Home.selectedClass1.equals("Thief")) {
			skill1.setText(storage.throwKnife.getName());
			skill2.setText(storage.shadowstep.getName());
			skill3.setText(storage.stealth.getName());
			trait.setText(storage.steal.getName());
			str = 8;
			vig = 4;
			wis = 6;
			dex = 12;
			if(newGame) {
				char3MaxHP = storage.thief.getMaxHP();
				char3HP = storage.thief.getMaxHP();
			}	
			activeChar = 3;
		}
		
		switch(Home.selectedClass2.toString()) {
		case "Barbarian":
			char1MaxHP = char1HP = storage.barbarian.getMaxHP();
			break;
		case "Sorcerer":
			char2MaxHP = char2HP = storage.sorcerer.getMaxHP();
			break;
		case "Thief":
			char3MaxHP = char3HP = storage.thief.getMaxHP();
			break;
		}
		
		if(activeChar == 1)
			playerHP.setText("HP: " + char1HP + "/" + char1MaxHP);
		else if(activeChar == 2)
			playerHP.setText("HP: " + char2HP + "/" + char2MaxHP);
		else if(activeChar == 3)
			playerHP.setText("HP: " + char3HP + "/" + char3MaxHP);
		
		newGame = false;
	}
	
	private void setCharacter(int x) {
		activeChar = x;
		
		switch(x) {
		case 1:
			skill1.setText(storage.doubleSwing.getName());
			skill2.setText(storage.skullbash.getName());
			skill3.setText(storage.standYourGround.getName());
			trait.setText(storage.warcry.getName());
			str = 12;
			vig = 8;
			wis = 4;
			dex = 6;
			playerHP.setText("HP: " + char1HP + "/" + char1MaxHP);
			skill3.setDisabled(false);
			skill3.setTouchable(Touchable.enabled);
			break;
		case 2:
			skill1.setText(storage.fireball.getName());
			skill2.setText(storage.hailstorm.getName());
			skill3.setText(storage.blink.getName());
			trait.setText(storage.tweak.getName());
			str = 4;
			vig = 6;
			wis = 12;
			dex = 8;
			playerHP.setText("HP: " + char2HP + "/" + char2MaxHP);
			if(blinkCount != 1) {
				skill3.setDisabled(true);
				skill3.setTouchable(Touchable.disabled);
			}
			break;
		case 3:
			skill1.setText(storage.throwKnife.getName());
			skill2.setText(storage.shadowstep.getName());
			skill3.setText(storage.stealth.getName());
			trait.setText(storage.steal.getName());
			str = 8;
			vig = 4;
			wis = 6;
			dex = 12;
			playerHP.setText("HP: " + char3HP + "/" + char3MaxHP);
			skill3.setDisabled(false);
			skill3.setTouchable(Touchable.enabled);
		}
	}
	
	private void addDice(String skill) {
		int dice = 0;
		String talent = null;
		
		switch(skill) {
		case "Double Swing":
			dice = 8;
			talent = "Dexterity";
			break;
		case "Skullbash":
			dice = 4;
			talent = "Strength";
			break;
		case "Stand Your Ground":
			dice = 6;
			talent = "Vigor";
			break;
		case "Fireball":
			dice = 4;
			talent = "Wisdom";
			break;
		case "Hailstorm":
			dice = 6;
			talent = "Dexterity";
			break;
		case "Blink":
			blinkCount = 4;
			blink = true;
			talent = "";
			break;
		case "Throw Knife":
			dice = 4;
			talent = objective;
			break;
		case "Shadowstep":
			dice = 8;
			talent = "Dexterity";
			break;
		case "Stealth":
			dice = 4;
			talent = "Strength";
			stealth = true;
			break;
		}
		
		for(int i = 0; i < dices.length; i++) {
			if(dices[i] == 0 && talent.equals(objective)) {
				dices[i] = dice;
				if(skill.equals("Fireball"))
					dices[i + 1] = dice;
				break;
			}
		}
	}

	private void createComponents() {
		skill1 = new TextButton("", storage.buttonStyle);
		skill1.setColor(Color.LIGHT_GRAY);
		skill1.addListener(new ClickListener() {
    		@Override
    	    public void clicked(InputEvent event, float x, float y) {
    			addDice(skill1.getText().toString());
    			skill1.setTouchable(Touchable.disabled);
    			skill1.setDisabled(true);
    	    }});
		skill1.setSize(250, 50);
		skill1.setPosition(vp.getWorldWidth() / 10f, vp.getWorldHeight() / 1.1f);
		stage.addActor(skill1);	
		
		skill2 = new TextButton("", storage.buttonStyle);
		skill2.setColor(Color.LIGHT_GRAY);
		skill2.addListener(new ClickListener() {
    		@Override
    	    public void clicked(InputEvent event, float x, float y) {
    			addDice(skill2.getText().toString());
    			skill2.setTouchable(Touchable.disabled);
    			skill2.setDisabled(true);
    	    }});
		skill2.setSize(250, 50);
		skill2.setPosition(vp.getWorldWidth() / 10f, skill1.getY() - 100f);
		stage.addActor(skill2);	
		
		skill3 = new TextButton("", storage.buttonStyle);
		skill3.setColor(Color.LIGHT_GRAY);
		skill3.addListener(new ClickListener() {
    		@Override
    	    public void clicked(InputEvent event, float x, float y) {
    			addDice(skill3.getText().toString());
    			skill3.setTouchable(Touchable.disabled);
    			skill3.setDisabled(true);
    	    }});
		skill3.setSize(250, 50);
		skill3.setPosition(vp.getWorldWidth() / 10f, skill2.getY() - 100f);
		stage.addActor(skill3);	
		
		trait = new TextButton("", storage.buttonStyle);
		trait.setColor(Color.LIGHT_GRAY);
		trait.addListener(new ClickListener() {
    		@Override
    	    public void clicked(InputEvent event, float x, float y) {
    			if(trait.getText().toString().equals("Warcry"))
    				warcry = true;
    			else if(trait.getText().toString().equals("Tweak")) {
    				for(int i = 0; i < dices.length; i++)
    					if(dices[i] == 0) {
    						dices[i] = 12;
    						break;
    					}
    				}
				else if(trait.getText().toString().equals("Steal"))
					steal = true;
    			trait.setTouchable(Touchable.disabled);
    			trait.setDisabled(true);
    			traitCount = 3;
    	    }});
		trait.setSize(250, 50);
		trait.setPosition(vp.getWorldWidth() / 10f, skill3.getY() - 150f);
		if(traitCount != 1) {
			trait.setTouchable(Touchable.disabled);
			trait.setDisabled(true);
		}
		stage.addActor(trait);
		
		throwDice = new TextButton("Throw", storage.buttonStyle);
		throwDice.setColor(Color.LIGHT_GRAY);
		throwDice.addListener(new ClickListener() {
    		@Override
    	    public void clicked(InputEvent event, float x, float y) {    			
    		if (throwDice.getText().toString().equals("Throw")) 
                diceThrow();
            else if (throwDice.getText().toString().equals("Return"))
                gameScreen.setCurrentState(GameScreen.HOME);
    	    }});
		throwDice.setSize(100, 100);
		throwDice.setPosition(vp.getWorldWidth() / 2f, vp.getWorldHeight() / 5f);
		stage.addActor(throwDice);	
		
		reset = new TextButton("Reset", storage.buttonStyle);
		reset.setColor(Color.LIGHT_GRAY);
		reset.addListener(new ClickListener() {
    		@Override
    	    public void clicked(InputEvent event, float x, float y) {
    			stage.clear();
    			gameScreen.setCurrentState(GameScreen.EXPEDITION);
    	    }});
		reset.setSize(100, 100);
		reset.setPosition(vp.getWorldWidth() / 1.5f, vp.getWorldHeight() / 5f);
		stage.addActor(reset);
		
		barb = new TextButton("Barb", storage.buttonStyle);
		barb.setColor(Color.LIGHT_GRAY);
		barb.addListener(new ClickListener() {
    		@Override
    	    public void clicked(InputEvent event, float x, float y) {
    			if(Home.selectedClass1.equals("Barbarian")) {
    				setCharacter(1);   			       			
        		    addEnterListener(throwDice, "Barbarian");
    			}
    			else if(Home.selectedClass2.equals("Barbarian")) {
    				setCharacter(1);   			       			
        		    addEnterListener(throwDice, "Barbarian");
    			}
    			else if(Home.selectedClass1.equals("Sorcerer")) {
    				setCharacter(2);   			
        		    addEnterListener(throwDice, "Sorcerer");
    			}
    			else if(Home.selectedClass2.equals("Sorcerer")) {
    				setCharacter(2);   			
        		    addEnterListener(throwDice, "Sorcerer");
    			}
    			else if(Home.selectedClass1.equals("Thief")) {
    				setCharacter(3);   			       			
        		    addEnterListener(throwDice, "Thief");
    			}
    			else if(Home.selectedClass2.equals("Thief")) {
    				setCharacter(3);   			       			
        		    addEnterListener(throwDice, "Thief");
    			}
    			
    			addEnterListener(skill1, skill1.getText().toString());
    		    addEnterListener(skill2, skill2.getText().toString());
    		    addEnterListener(skill3, skill3.getText().toString());
    		    addEnterListener(trait, trait.getText().toString());
    			
    	    }});
		barb.setSize(100, 100);
		barb.setPosition(vp.getWorldWidth() / 10f, vp.getWorldHeight() / 5f);
		stage.addActor(barb);
		
		sorc = new TextButton("Sorc", storage.buttonStyle);
		sorc.setColor(Color.LIGHT_GRAY);
		sorc.addListener(new ClickListener() {
    		@Override
    	    public void clicked(InputEvent event, float x, float y) {
    			if(Home.selectedClass1.equals("Sorcerer")) {
    				setCharacter(2);   			       			
        		    addEnterListener(throwDice, "Sorcerer");
    			}
    			else if(Home.selectedClass2.equals("Sorcerer")) {
    				setCharacter(2);   			       			
        		    addEnterListener(throwDice, "Sorcerer");
    			}
    			else if(Home.selectedClass1.equals("Barbarian")) {
    				setCharacter(1);   			
        		    addEnterListener(throwDice, "Barbarian");
    			}
    			else if(Home.selectedClass2.equals("Barbarian")) {
    				setCharacter(1);   			
        		    addEnterListener(throwDice, "Barbarian");
    			}
    			else if(Home.selectedClass1.equals("Thief")) {
    				setCharacter(3);   			       			
        		    addEnterListener(throwDice, "Thief");
    			}
    			else if(Home.selectedClass2.equals("Thief")) {
    				setCharacter(3);   			       			
        		    addEnterListener(throwDice, "Thief");
    			}
    			
    			addEnterListener(skill1, skill1.getText().toString());
    		    addEnterListener(skill2, skill2.getText().toString());
    		    addEnterListener(skill3, skill3.getText().toString());
    		    addEnterListener(trait, trait.getText().toString());
    	    }});
		sorc.setSize(100, 100);
		sorc.setPosition(vp.getWorldWidth() / 5f, vp.getWorldHeight() / 5f);
		stage.addActor(sorc);
		
		thief = new TextButton("Thief", storage.buttonStyle);
		thief.setColor(Color.LIGHT_GRAY);
		thief.addListener(new ClickListener() {
    		@Override
    	    public void clicked(InputEvent event, float x, float y) {
    			if(Home.selectedClass1.equals("Thief")) {
    				setCharacter(3);   			       			
        		    addEnterListener(throwDice, "Thief");
    			}
    			else if(Home.selectedClass2.equals("Thief")) {
    				setCharacter(3);   			       			
        		    addEnterListener(throwDice, "Thief");
    			}
    			else if(Home.selectedClass1.equals("Sorcerer")) {
    				setCharacter(2);   			
        		    addEnterListener(throwDice, "Sorcerer");
    			}
    			else if(Home.selectedClass2.equals("Sorcerer")) {
    				setCharacter(2);   			
        		    addEnterListener(throwDice, "Sorcerer");
    			}
    			else if(Home.selectedClass1.equals("Barbarian")) {
    				setCharacter(1);   			
        		    addEnterListener(throwDice, "Barbarian");
    			}
    			else if(Home.selectedClass2.equals("Barbarian")) {
    				setCharacter(1);   			
        		    addEnterListener(throwDice, "Barbarian");
    			}
    			
    			addEnterListener(skill1, skill1.getText().toString());
    		    addEnterListener(skill2, skill2.getText().toString());
    		    addEnterListener(skill3, skill3.getText().toString());
    		    addEnterListener(trait, trait.getText().toString());
    			
    	    }});
		thief.setSize(100, 100);
		thief.setPosition(vp.getWorldWidth() / 10f, vp.getWorldHeight() / 10f);
		stage.addActor(thief);
		
		dice1 = new Label("", storage.labelStyle);
		dice1.setPosition(vp.getWorldWidth() / 5f, vp.getWorldHeight() / 2f);
		stage.addActor(dice1);
		
		dice2 = new Label("", storage.labelStyle);
		dice2.setPosition(dice1.getX() + 100f, vp.getWorldHeight() / 2f);
		stage.addActor(dice2);
		
		dice3 = new Label("", storage.labelStyle);
		dice3.setPosition(dice2.getX() + 100f, vp.getWorldHeight() / 2f);
		stage.addActor(dice3);
		
		dice4 = new Label("", storage.labelStyle);
		dice4.setPosition(dice3.getX() + 100f, vp.getWorldHeight() / 2f);
		stage.addActor(dice4);
		
		dice5 = new Label("", storage.labelStyle);
		dice5.setPosition(dice4.getX() + 100f, vp.getWorldHeight() / 2f);
		stage.addActor(dice5);
		
		d20 = new Label("", storage.labelStyle);
		d20.setPosition(dice5.getX() + 100f, vp.getWorldHeight() / 2f);
		stage.addActor(d20);
		
		total = new Label("", storage.labelStyle);
		total.setPosition(d20.getX() + 400f, vp.getWorldHeight() / 2f);
		stage.addActor(total);
		
		playerHP = new Label("HP: " + hp + "/" + maxHP, storage.labelStyle);
		playerHP.setPosition(dice1.getX() + 150f, vp.getWorldHeight() / 5f);
		stage.addActor(playerHP);
		
		enemyNameLbl = new Label(enemyName + " (" + eTrait + ")", storage.labelStyle);
		enemyNameLbl.setPosition(vp.getWorldWidth() / 1.5f, vp.getWorldHeight() / 1.2f);
		stage.addActor(enemyNameLbl);
		
		objLbl = new Label(objective, storage.labelStyle);
		objLbl.setPosition(enemyNameLbl.getX(), enemyNameLbl.getY() + 50f);
		stage.addActor(objLbl);
		
		objNumLbl = new Label("" + objNum, storage.labelStyle);
		objNumLbl.setPosition(objLbl.getX() - objLbl.getWidth(), objLbl.getY());
		stage.addActor(objNumLbl);
		
		skillDesc = new Label("", storage.labelStyle);
		skillDesc.setPosition(vp.getWorldWidth() / 2f, vp.getWorldHeight() / 2f);
		stage.addActor(skillDesc);
		
		if(!Home.selectedClass1.equals("Barbarian") && !Home.selectedClass2.equals("Barbarian")) {
			barb.setVisible(false);
			thief.setPosition(barb.getX(), barb.getY());
		}
		if(!Home.selectedClass1.equals("Sorcerer") && !Home.selectedClass2.equals("Sorcerer")) {
			sorc.setVisible(false);
			thief.setPosition(sorc.getX(), sorc.getY());
		}
		if(!Home.selectedClass1.equals("Thief") && !Home.selectedClass2.equals("Thief"))
			thief.setVisible(false);
	}
	
	private void diceThrow() {
		int x = 0, y = 0;
		
		for(int i = 0; i < dices.length; i++) {
			if(dices[i] == 0) {
				switch(objective) {
				case "Strength":
					dices[i] = str;
					break;
				case "Vigor":
					dices[i] = vig;
					break;
				case "Wisdom":
					dices[i] = wis;
					break;
				case "Dexterity":
					dices[i] = dex;
					break;
				}
			break;
			}
		}
		
		if(warcry) {
			for(int i = 0; i < dices.length; i++) {
				if(dices[i] != 0 && dices[i] > y) {
					y = dices[i];				
				}
			}
			
			for(int j = 0; j < dices.length; j++) {
				if(dices[j] == 0) {
					dices[j] = y;
					break;
				}
			}
			
			warcry = false;
		}
		
		if(dices[0] != 0) {
			x = rand.nextInt(1, dices[0] + 1);
			dice1.setText(x);	
			dices[0] = x;
		}
		if(dices[1] != 0) {
			x = rand.nextInt(1, dices[1] + 1);
			dice2.setText(x);		
			dices[1] = x;
		}
		if(dices[2] != 0) {
			x = rand.nextInt(1, dices[2] + 1);
			dice3.setText(x);
			dices[2] = x;
		}
		if(dices[3] != 0) {
			x = rand.nextInt(1, dices[3] + 1);
			dice4.setText(x);
			dices[3] = x;
		}
		if(dices[4] != 0) {
			x = rand.nextInt(1, dices[4] + 1);
			dice5.setText(x);
			dices[4] = x;			
		}
		
		x = 0;
				
		for(int i = 0; i < dices.length; i++)
			x += dices[i];
		
		if(shriek) {
			x--;
			shriek = false;
		}
		
		total.setText("Total: " + x);
		
		d20.setText(rand.nextInt(1, 21));
		
		if(Integer.parseInt(d20.getText().toString()) > 17)
			x += x/2;
		
		if(steal)
			objNum -= 2;
		
		if(x >= objNum && Integer.parseInt(d20.getText().toString()) > 10) {			
			encCleared++;
			total.setText("You win. Total: " + x);	
			throwDice.remove();
			if(encCleared < 6) {
				throwDice = new TextButton("Next encounter", storage.buttonStyle);
			}			
			if(encCleared >= 6) {
				throwDice.setText("Return");
			} 
			throwDice.setColor(Color.LIGHT_GRAY);
			throwDice.addListener(new ClickListener() {
	    		@Override
	    	    public void clicked(InputEvent event, float x, float y) {
	    			if(throwDice.getText().toString().equals("Next encounter")) {
	    				resetActors(1);
	    			}   	
	    			else
	    				gameScreen.setCurrentState(GameScreen.HOME);
	    	    }});
			throwDice.setSize(200, 100);
			throwDice.setPosition(vp.getWorldWidth() / 2f, vp.getWorldHeight() / 5f);
			stage.addActor(throwDice);
			
			switch(activeChar) {
			case 1:
				char1HP += 5;
				if(char1HP > char1MaxHP)
					char1HP = char1MaxHP;
				break;
			case 2:
				char2HP += 5;
				if(char2HP > char2MaxHP)
					char2HP = char2MaxHP;
				break;
			}
		}
		else {
			total.setText("You miss. Total: " + x);
			enemyAttack();
		}
		
		if(traitCount > 1)
			traitCount--;
		
		if(blinkCount > 1)
			blinkCount--;
		
		if(steal) {
			objNum += 2;
			steal = false;
		}
	}
	
	private void enemyAttack() {
		if(activeChar == 1) {
			char1HP -= eDmg;
			playerHP.setText("HP: " + char1HP + "/" + char1MaxHP);
		}				
		else if(activeChar == 2 && blink)
			blink = false;
		else if(activeChar == 2) {
			char2HP -= eDmg;
			playerHP.setText("HP: " + char2HP + "/" + char2MaxHP);
		}
		else if(activeChar == 3 && stealth && objective.equals("Strength"))
			stealth = false;
		else if(activeChar == 3) {
			char3HP -= eDmg;
			playerHP.setText("HP: " + char3HP + "/" + char3MaxHP);
		}
		
		enemyTrait();
				
		throwDice.remove();
		throwDice = new TextButton("New roll", storage.buttonStyle);
		throwDice.setColor(Color.LIGHT_GRAY);
		throwDice.addListener(new ClickListener() {
    		@Override
    	    public void clicked(InputEvent event, float x, float y) { 
    			if(activeChar == 1 && bite) {
    				char1HP--;
    				playerHP.setText("HP: " + char1HP + "/" + char1MaxHP);
    				bite = false;
    			}
    			else if(activeChar == 2 && bite) {
    				char2HP--;
    				playerHP.setText("HP: " + char2HP + "/" + char2MaxHP);
    				bite = false;
    			}
    			else if(activeChar == 3 && bite) {
    				char3HP--;
    				playerHP.setText("HP: " + char3HP + "/" + char3MaxHP);
    				bite = false;
    			}
    			
    			resetActors(0);
    	    }});
		throwDice.setSize(200, 100);
		throwDice.setPosition(vp.getWorldWidth() / 2f, vp.getWorldHeight() / 5f);
		stage.addActor(throwDice);			
	}
	
	private void enemyTrait() {
		switch(eTrait) {
		case "Shriek":
			shriek = true;
			break;
		case "Bite":
			bite = true;
			break;
		case "Growth":
			eDmg++;
			break;
		case "Damage":
			break;
		}
	}
	
	private void resetActors(int x) {
		d20.remove();
		dice1.remove();
		dice2.remove();
		dice3.remove();
		dice4.remove();
		dice5.remove();
		skill1.remove();
		skill2.remove();
		skill3.remove();
		trait.remove();
		throwDice.remove();
		reset.remove();
		total.remove();
		playerHP.remove();
		enemyNameLbl.remove();
		objLbl.remove();
		objNumLbl.remove();
		
		if(x == 1)
			setEncounter();	
		
		for(int i = 0; i < dices.length; i++)
			dices[i] = 0;
				
		createComponents();
		
		if(activeChar == 1) {
			setCharacter(1);
		    addEnterListener(throwDice, "Barbarian");
		}
		else if(activeChar == 2) {
			setCharacter(2);
		    addEnterListener(throwDice, "Sorcerer");
		}
		else if(activeChar == 3) {
			setCharacter(3);
		    addEnterListener(throwDice, "Thief");
		}
		
		addEnterListener(skill1, skill1.getText().toString());
	    addEnterListener(skill2, skill2.getText().toString());
	    addEnterListener(skill3, skill3.getText().toString());
	    addEnterListener(trait, trait.getText().toString());
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		stage.act();
		stage.draw();		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		
	}

}
