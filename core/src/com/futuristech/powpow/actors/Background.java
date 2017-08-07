package com.futuristech.powpow.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Timer;
import com.futuristech.powpow.utils.Assets;
import com.futuristech.powpow.utils.Constants;

public class Background extends Actor {

    public static TextureRegion textureRegion;
    public static Rectangle textureRegionBounds1;
    public static Rectangle textureRegionBounds2;
    private int speed = 100;
    
    private int score = 100;
    private BitmapFont scoreFont = new BitmapFont();
    private BitmapFont gameOverFont = new BitmapFont();
    GlyphLayout glyphLayout = new GlyphLayout();

    private Music musicBackground;


    public Background()
    {
        textureRegion = new TextureRegion(new Texture(Gdx.files.internal(Constants.BACKGROUND_IMAGE_PATH)));
        textureRegionBounds1 = new Rectangle(0 - Constants.APP_WIDTH / 2, 0, Constants.APP_WIDTH, Constants.APP_HEIGHT);
        textureRegionBounds2 = new Rectangle(Constants.APP_WIDTH / 2, 0, Constants.APP_WIDTH, Constants.APP_HEIGHT);

        musicBackground = Gdx.audio.newMusic(Gdx.files.internal("music/backmusic.ogg"));
        musicBackground.setLooping(true);
        musicBackground.play();


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
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(textureRegion, textureRegionBounds1.x, textureRegionBounds1.y, Constants.APP_WIDTH,
                Constants.APP_HEIGHT);
        batch.draw(textureRegion, textureRegionBounds2.x, textureRegionBounds2.y, Constants.APP_WIDTH,
                Constants.APP_HEIGHT);
        if (Assets.runnerHit)
        {

            musicBackground.stop();
//            gameOverFont.draw(batch, "Game Over: " + (score / 100), 0, 0);
            String text = "Game Over: " + (score / 100);
            glyphLayout.setText(gameOverFont, text);
            float y = glyphLayout.height;
            float x = glyphLayout.width;
            gameOverFont.getData().setScale(3f);
            gameOverFont.draw(batch, "Game Over\n Final Score: " + (score / 100), x, 480 - 20);


        }
        else
        {
            score += 10;
            scoreFont.getData().setScale(2f);
            scoreFont.draw(batch, "Score: " + (score / 100), 16, 480 - 20);
        }
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
