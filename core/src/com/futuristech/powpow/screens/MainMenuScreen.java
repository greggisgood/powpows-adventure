package com.futuristech.powpow.screens;

/**
 * Created by Eryll on 04/08/2017.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.futuristech.powpow.MyGame;


public class MainMenuScreen implements Screen {
  
  private static final int EXIT_BUTTON_WIDTH = 150;
  private static final int EXIT_BUTTON_HEIGHT = 120;
  private static final int PLAY_BUTTON_WIDTH = 150;
  private static final int PLAY_BUTTON_HEIGHT = 120;
  private static final int EXIT_BUTTON_Y = 100;
  private static final int PLAY_BUTTON_Y = 230;
  private static final int LOGO_WIDTH = 400;
  private static final int LOGO_HEIGHT = 250;
  private static final int LOGO_Y = 450;
  
  final MyGame game;
  
  Texture playButtonActive;
  Texture playButtonInactive;
  Texture exitButtonActive;
  Texture exitButtonInactive;
  Texture logo;
  
  public MainMenuScreen (final MyGame game) {
    this.game = game;
    playButtonActive = new Texture("btnstart.png");
    playButtonInactive = new Texture("btnstart.png");
    exitButtonActive = new Texture("exitbtnt.jpg");
    exitButtonInactive = new Texture("exitbtnt.jpg");
    logo = new Texture("ground.png");
    
    
    final MainMenuScreen mainMenuScreen = this;
    
    Gdx.input.setInputProcessor(new InputAdapter()
    {
      
      @Override
      public boolean touchDown(int screenX, int screenY, int pointer, int button)
      {
        //Exit button
        int x = MyGame.WIDTH / 2 - EXIT_BUTTON_WIDTH / 2;
        if (game.cam.getInputInGameWorld().x < x + EXIT_BUTTON_WIDTH && game.cam.getInputInGameWorld().x > x && MyGame.HEIGHT - game.cam.getInputInGameWorld().y < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT && MyGame.HEIGHT - game.cam.getInputInGameWorld().y > EXIT_BUTTON_Y)
        {
          mainMenuScreen.dispose();
          Gdx.app.exit();
        }
        
        //Play game button
        x = MyGame.WIDTH / 2 - PLAY_BUTTON_WIDTH / 2;
        if (game.cam.getInputInGameWorld().x < x + PLAY_BUTTON_WIDTH && game.cam.getInputInGameWorld().x > x && MyGame.HEIGHT - game.cam.getInputInGameWorld().y < PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT && MyGame.HEIGHT - game.cam.getInputInGameWorld().y > PLAY_BUTTON_Y)
        {
          mainMenuScreen.dispose();
          game.setScreen(new GameScreen());
        }
        
        return super.touchUp(screenX, screenY, pointer, button);
      }
      
    });
  }
  
  @Override
  public void show () {
    
  }
  
  @Override
  public void render (float delta) {
    Gdx.gl.glClearColor(0.15f, 0.15f, 0.3f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    game.batch.begin();
    
    
    int x = MyGame.WIDTH / 2 - EXIT_BUTTON_WIDTH / 2;
    if (game.cam.getInputInGameWorld().x < x + EXIT_BUTTON_WIDTH && game.cam.getInputInGameWorld().x > x && MyGame.HEIGHT - game.cam.getInputInGameWorld().y < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT && MyGame.HEIGHT - game.cam.getInputInGameWorld().y > EXIT_BUTTON_Y)
    {
      game.batch.draw(exitButtonActive, x, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
    }
    else
    {
      game.batch.draw(exitButtonInactive, x, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
    }
    
    x = MyGame.WIDTH / 2 - PLAY_BUTTON_WIDTH / 2;
    if (game.cam.getInputInGameWorld().x < x + PLAY_BUTTON_WIDTH && game.cam.getInputInGameWorld().x > x &&
        MyGame.HEIGHT - game.cam.getInputInGameWorld().y < PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT && MyGame.HEIGHT - game.cam.getInputInGameWorld().y > PLAY_BUTTON_Y)
    {
      game.batch.draw(playButtonActive, x, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
    }
    else
    {
      game.batch.draw(playButtonInactive, x, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
    }
    
    game.batch.draw(logo, MyGame.WIDTH / 2 - LOGO_WIDTH / 2, LOGO_Y, LOGO_WIDTH, LOGO_HEIGHT);
    
    game.batch.end();
  }
  
  @Override
  public void resize (int width, int height) {
    
  }
  
  @Override
  public void pause() {}
  
  @Override
  public void resume() {}
  
  @Override
  public void hide() {}
  
  @Override
  public void dispose() {
    Gdx.input.setInputProcessor(null);
  }
  
}