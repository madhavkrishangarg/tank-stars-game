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

public class LoginMenu implements Screen, Serializable {
    SpriteBatch batch;
    Texture image;
    private Stage Menu;
    private Game tank;

    public LoginMenu(Game g){
        Skin default_skin=new Skin(Gdx.files.internal("skin/uiskin.json"));
        tank=g;
        Menu=new Stage(new ScreenViewport());

        TextButton enter = new TextButton("Enter",MyGdxGame.default_skin);
        enter.setSize(Gdx.graphics.getWidth()/5,Gdx.graphics.getHeight()/14);
        enter.setPosition(Gdx.graphics.getWidth()/2-enter.getWidth()/2, 100);
        enter.addListener(new InputListener(){
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                tank.setScreen(new MainMenu(tank));
            }

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
        });
        batch=new SpriteBatch();
        image=new Texture("Loginscreen.jpg");
        Menu.addActor(enter);

    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(Menu);
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
