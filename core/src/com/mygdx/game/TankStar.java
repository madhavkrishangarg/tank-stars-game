package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.io.Serializable;

public class TankStar implements Screen, Serializable {
    SpriteBatch batch;
    Texture image;
    private Stage Menu;
    private Game tank;

    public TankStar(Game g){
        Skin default_skin=new Skin(Gdx.files.internal("skin/uiskin.json"));
        tank=g;
        Menu=new Stage(new ScreenViewport());

        final TextButton resume = new TextButton("Resume Game",MyGdxGame.default_skin);
        resume.setSize(Gdx.graphics.getWidth()/5,Gdx.graphics.getHeight()/13);
        resume.setPosition(10,Gdx.graphics.getHeight()-120);
        resume.addListener(new InputListener(){
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                tank.setScreen(new TankStar(tank));
            }

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
        });

        final TextButton save_game = new TextButton("Save Game",MyGdxGame.default_skin);
        save_game.setSize(Gdx.graphics.getWidth()/5,Gdx.graphics.getHeight()/13);
        save_game.setPosition(10, resume.getY()- save_game.getHeight()-5);
        save_game.addListener(new InputListener(){
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                tank.setScreen(new TankStar(tank));
            }

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
        });

        final TextButton exit = new TextButton("Exit",MyGdxGame.default_skin);
        exit.setSize(Gdx.graphics.getWidth()/5,Gdx.graphics.getHeight()/13);
        exit.setPosition(10, save_game.getY()-exit.getHeight()-5);
        exit.addListener(new InputListener(){
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                tank.setScreen(new MainMenu(tank));
            }

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
        });

        TextButton pause = new TextButton("| |",MyGdxGame.default_skin);
        pause.setSize(Gdx.graphics.getWidth()/20,Gdx.graphics.getHeight()/15);
        pause.setPosition(10,Gdx.graphics.getHeight()-80);
        pause.addListener(new InputListener(){
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Menu.addActor(resume);
                Menu.addActor(save_game);
                Menu.addActor(exit);
            }

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
        });

        batch=new SpriteBatch();
        image=new Texture("1.jpg");
        Menu.addActor(pause);

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
