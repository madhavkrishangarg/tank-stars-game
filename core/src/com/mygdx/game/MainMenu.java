package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.io.Serializable;

public class MainMenu implements Screen, Serializable {

    SpriteBatch batch;
    Texture image;
    private Stage Menu;
    private Game tank;

    public MainMenu(Game g){
        Skin default_skin=new Skin(Gdx.files.internal("skin/uiskin.json"));
        tank=g;
        Menu=new Stage(new ScreenViewport());

        TextButton login = new TextButton("Login",MyGdxGame.default_skin);
        login.setSize(Gdx.graphics.getWidth()/5,Gdx.graphics.getHeight()/10);
        login.setPosition(Gdx.graphics.getWidth()-210,Gdx.graphics.getHeight()-220);
        login.addListener(new InputListener(){
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                tank.setScreen(new LoginMenu(tank));
            }

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
        });

        TextButton new_game = new TextButton("New Game",MyGdxGame.default_skin);
        new_game.setSize(Gdx.graphics.getWidth()/5,Gdx.graphics.getHeight()/10);
        new_game.setPosition(Gdx.graphics.getWidth()-210,login.getY()- new_game.getHeight()-20);
        new_game.addListener(new InputListener(){
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                tank.setScreen(new TankSelect(tank));
            }

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
        });

        TextButton saved_games = new TextButton("Saved Games",MyGdxGame.default_skin);
        saved_games.setSize(Gdx.graphics.getWidth()/5,Gdx.graphics.getHeight()/10);
        saved_games.setPosition(Gdx.graphics.getWidth()-210, new_game.getY()- saved_games.getHeight()-20);
        saved_games.addListener(new InputListener(){
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                tank.setScreen(new TankStar(tank));
            }

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
        });

        TextButton exit = new TextButton("Exit",MyGdxGame.default_skin);
        exit.setSize(Gdx.graphics.getWidth()/5,Gdx.graphics.getHeight()/10);
        exit.setPosition(Gdx.graphics.getWidth()-210, saved_games.getY()-exit.getHeight()-20);
        exit.addListener(new InputListener(){
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                System.exit(0);
            }

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
        });
        batch=new SpriteBatch();
        image=new Texture("login_screen.jpg");
        Menu.addActor(login);
        Menu.addActor(new_game);
        Menu.addActor(saved_games);
        Menu.addActor(exit);
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
