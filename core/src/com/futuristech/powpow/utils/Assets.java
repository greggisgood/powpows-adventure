package com.futuristech.powpow.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Created by Jessie-Futuristech on 8/4/2017.
 */

public class Assets
{
  public static boolean runnerHit = false;
  public static Music musicBackground = Gdx.audio.newMusic(Gdx.files.internal("music/backmusic.ogg"));
  public static Sound jump = Gdx.audio.newSound(Gdx.files.internal("music/jump.wav"));
  public static  Sound gameover = Gdx.audio.newSound(Gdx.files.internal("music/gameover.wav"));

  
}
