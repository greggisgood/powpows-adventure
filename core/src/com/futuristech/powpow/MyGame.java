package com.futuristech.powpow;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.futuristech.powpow.screens.GameScreen;
import com.futuristech.powpow.screens.MainMenuScreen;
import com.futuristech.powpow.utils.GameCamera;
import com.futuristech.powpow.utils.ScrollingBackground;

public class MyGame extends Game {
	
	//	public GameCamera  cam;
	public SpriteBatch batch;
	public static final int WIDTH = 480;
	public static final int HEIGHT = 720;
	public static boolean IS_MOBILE = false;
	public ScrollingBackground scrollingBackground;
	
	public GameCamera cam;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		cam = new GameCamera(WIDTH, HEIGHT);
		
		if (Gdx.app.getType() == Application.ApplicationType.Android)
			IS_MOBILE = true;
		IS_MOBILE = true;
		
		this.scrollingBackground = new ScrollingBackground();
		this.setScreen(new MainMenuScreen(this));
	}
	
	@Override
	public void render () {
		batch.setProjectionMatrix(cam.combined());
		super.render();
	}
	
	@Override
	public void resize(int width, int height) {
		cam.update(width, height);
		super.resize(width, height);
	}
	
	
}
