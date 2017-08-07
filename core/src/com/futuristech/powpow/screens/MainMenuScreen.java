package com.futuristech.powpow.screens;

/**
 * Created by Eryll on 04/08/2017.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.futuristech.powpow.MyGame;
import com.futuristech.powpow.actors.Background;
import com.futuristech.powpow.utils.Assets;
import com.futuristech.powpow.utils.Constants;
import com.futuristech.powpow.utils.ScrollingBackground;


public class MainMenuScreen extends Actor implements Screen {
  
  private static final int EXIT_BUTTON_WIDTH = 150;
  private static final int EXIT_BUTTON_HEIGHT = 120;
  private static final int PLAY_BUTTON_WIDTH = 150;
  private static final int PLAY_BUTTON_HEIGHT = 120;
  private static final int EXIT_BUTTON_Y = 100;
  private static final int PLAY_BUTTON_Y = 230;
  private static final int LOGO_WIDTH = 400;
  private static final int LOGO_HEIGHT = 250;
  private static final int LOGO_Y = 450;
  private int speed = 100;
  
  private  TextureRegion textureRegion;
  private  Rectangle     textureRegionBounds1;
  private  Rectangle     textureRegionBounds2;
  
  final MyGame game;
  
  Texture       playButtonActive;
  Texture       playButtonInactive;
  Texture       exitButtonActive;
  Texture       exitButtonInactive;
  TextureRegion background;
  Texture       logo;
  
  public static Texture backgroundTexture;
  public static Sprite backgroundSprite;
  private SpriteBatch spriteBatch;
  
  public MainMenuScreen (final MyGame game) {
  
    
    this.game = game;
    playButtonActive = new Texture("btnstart.png");
    playButtonInactive = new Texture("btnstart.png");
    exitButtonActive = new Texture("exitbtnt.jpg");
    exitButtonInactive = new Texture("exitbtnt.jpg");
    background = new TextureRegion(new Texture("background.png"), 0, 0, 2048, 900);
  
    textureRegion = new TextureRegion(new Texture(Gdx.files.internal(Constants.BACKGROUND_IMAGE_PATH)), 1080, 800);
    textureRegionBounds1 = new Rectangle(0 - Constants.APP_WIDTH / 2, 0, Constants.APP_WIDTH, Constants.APP_HEIGHT);
    textureRegionBounds2 = new Rectangle(Constants.APP_WIDTH / 2, 0, Constants.APP_WIDTH, Constants.APP_HEIGHT);
    
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
  public void act(float delta) {
    if (leftBoundsReached(delta)) {
      resetBounds();
    } else {
      updateXBounds(-delta);
    }
  }
  
  @Override
  public void show()
  {
    
  }
  
  @Override
  public void draw(Batch batch, float parentAlpha)
  {
    super.draw(batch, parentAlpha);
    batch.draw(Background.textureRegion, Background.textureRegionBounds1.x, Background.textureRegionBounds1.y, Constants.APP_WIDTH,
        Constants.APP_HEIGHT);
    batch.draw(Background.textureRegion, Background.textureRegionBounds2.x, Background.textureRegionBounds2.y, Constants.APP_WIDTH,
        Constants.APP_HEIGHT);
  }
  
  @Override
  public void render (float delta) {
    Gdx.gl.glClearColor(0.15f, 0.15f, 0.3f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    game.batch.begin();
  
    game.batch.draw(Background.textureRegion, Background.textureRegionBounds1.x, Background.textureRegionBounds1.y, Constants.APP_WIDTH,
        Constants.APP_HEIGHT);
    game.batch.draw(Background.textureRegion, Background.textureRegionBounds2.x, Background.textureRegionBounds2.y, Constants.APP_WIDTH,
        Constants.APP_HEIGHT);
  
    game.batch.draw(textureRegion, 0, 0);
    
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
//    game.setScreen(new GameScreen());
    game.batch.draw(textureRegion, 0, Gdx.graphics.getHeight());
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
  
  private boolean leftBoundsReached(float delta) {
    return (textureRegionBounds2.x - (delta * speed)) <= 0;
  }
  
  private void updateXBounds(float delta) {
    textureRegionBounds1.x += delta * speed;
    textureRegionBounds2.x += delta * speed;
  }
  
  private void resetBounds() {
    textureRegionBounds1 = textureRegionBounds2;
    textureRegionBounds2 = new Rectangle(Constants.APP_WIDTH, 0, Constants.APP_WIDTH, Constants.APP_HEIGHT);
  }
  
}