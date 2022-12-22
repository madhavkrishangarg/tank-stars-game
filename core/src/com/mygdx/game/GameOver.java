package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.io.Serializable;

public class GameOver implements Screen, Serializable {
    SpriteBatch batch;
    Texture image;
    private Stage Menu;
    private Game tank;

    public GameOver(Game g){
        Skin default_skin=new Skin(Gdx.files.internal("skin/uiskin.json"));
        tank=g;
        Menu=new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(Menu);

        TextButton restart = new TextButton("Restart",MyGdxGame.default_skin);
        restart.setSize(Gdx.graphics.getWidth()/5,Gdx.graphics.getHeight()/14);
        restart.setPosition(Gdx.graphics.getWidth()/2-restart.getWidth()/2, 235);
        restart.addListener(new InputListener(){
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                tank.setScreen(new TankSelect(tank));
            }

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
        });

        TextButton quit = new TextButton("Quit",MyGdxGame.default_skin);
        quit.setSize(Gdx.graphics.getWidth()/5,Gdx.graphics.getHeight()/14);
        quit.setPosition(Gdx.graphics.getWidth()/2-quit.getWidth()/2, 150);
        quit.addListener(new InputListener(){
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                tank.setScreen(new MainMenu(tank));
            }

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
        });

        batch=new SpriteBatch();
        image=new Texture("game_over.jpg");
        Menu.addActor(restart);
        Menu.addActor(quit);

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(image,0,20,640,430);
        batch.end();
        Menu.act();
        Menu.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
        Menu.dispose();
    }

}
