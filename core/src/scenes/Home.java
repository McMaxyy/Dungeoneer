package scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import config.GameScreen;
import storage.Storage;

public class Home implements Screen{
	Skin skin;
	Viewport vp;
	public Stage stage;
	private Game game;
	private GameScreen gameScreen; 	
	private Storage storage;
	private TextButton play, zerker, sorc, thief;
	public static String selectedClass1, selectedClass2;

	public Home(Viewport viewport, Game game, GameScreen gameScreen) {
		this.gameScreen = gameScreen;
		this.game = game;
		stage = new Stage(viewport);
		vp = viewport;
		Gdx.input.setInputProcessor(stage);
		storage = Storage.getInstance();
		storage.createFont();
		skin = storage.skin;
	
		createComponents();
	}
	
	private void createComponents() {
		play = new TextButton("New Game", storage.buttonStyle);
		play.setColor(Color.LIGHT_GRAY);
		play.addListener(new ClickListener() {
    		@Override
    	    public void clicked(InputEvent event, float x, float y) {
    			if(selectedClass1 == null)
    				selectedClass1 = "Barbarian";
    			
    			if(selectedClass2 == null) {
    				if(selectedClass1.equals("Barbarian") || selectedClass1.equals("Thief"))
    					selectedClass2 = "Sorcerer";
    				else
    					selectedClass2 = "Barbarian";
    			}
    			gameScreen.setCurrentState(GameScreen.EXPEDITION);
    	    }});
		play.setSize(350, 100);
		play.setPosition(vp.getWorldWidth() / 1.5f, vp.getWorldHeight() / 3f);
		stage.addActor(play);	
		
		zerker = new TextButton("Barbarian", storage.buttonStyle);
		zerker.setColor(Color.LIGHT_GRAY);
		zerker.addListener(new ClickListener() {
    		@Override
    	    public void clicked(InputEvent event, float x, float y) {   			
    			if(selectedClass1 != null) {
    				if(selectedClass1.equals("Sorcerer") || selectedClass1.equals("Thief"))
        				selectedClass2 = "Barbarian";
        			else
        				selectedClass1 = "Barbarian";
    			}
    			else
    				selectedClass1 = "Barbarian";
    	    }});
		zerker.setSize(150, 100);
		zerker.setPosition(vp.getWorldWidth() / 2f, vp.getWorldHeight() / 2f);
		stage.addActor(zerker);	
		
		sorc = new TextButton("Sorcerer", storage.buttonStyle);
		sorc.setColor(Color.LIGHT_GRAY);
		sorc.addListener(new ClickListener() {
    		@Override
    	    public void clicked(InputEvent event, float x, float y) {
    			if(selectedClass1 != null) {
    				if(selectedClass1.equals("Barbarian") || selectedClass1.equals("Thief"))
        				selectedClass2 = "Sorcerer";
        			else
        				selectedClass1 = "Sorcerer";
    			}
    			else
    				selectedClass1 = "Sorcerer";
    	    }});
		sorc.setSize(150, 100);
		sorc.setPosition(vp.getWorldWidth() / 2f, vp.getWorldHeight() / 2.5f);
		stage.addActor(sorc);
		
		thief = new TextButton("Thief", storage.buttonStyle);
		thief.setColor(Color.LIGHT_GRAY);
		thief.addListener(new ClickListener() {
    		@Override
    	    public void clicked(InputEvent event, float x, float y) {
    			if(selectedClass1 != null) {
    				if(selectedClass1.equals("Barbarian") || selectedClass1.equals("Sorcerer"))
        				selectedClass2 = "Thief";
        			else
        				selectedClass1 = "Thief";
    			}
    			else
    				selectedClass1 = "Thief";
    	    }});
		thief.setSize(150, 100);
		thief.setPosition(vp.getWorldWidth() / 2f, vp.getWorldHeight() / 3.35f);
		stage.addActor(thief);	
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
