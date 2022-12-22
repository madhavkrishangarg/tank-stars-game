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

public class TankSelect implements Screen, Serializable {

    SpriteBatch batch;
    Texture image;
    private Stage Menu;
    private Game tank;
    private int choice;

    public TankSelect(Game g){
        Skin default_skin=new Skin(Gdx.files.internal("skin/uiskin.json"));
        tank=g;
        Menu=new Stage(new ScreenViewport());

        TextButton select1 = new TextButton("Select",MyGdxGame.default_skin);
        select1.setSize(Gdx.graphics.getWidth()/5,Gdx.graphics.getHeight()/14);
        select1.setPosition(Gdx.graphics.getWidth()/2-260, 100);
        select1.addListener(new InputListener(){
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                tank.setScreen(new TankStar(tank,1,new Tanks(1)));
            }

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
        });

        TextButton select2 = new TextButton("Select",MyGdxGame.default_skin);
        select2.setSize(Gdx.graphics.getWidth()/5,Gdx.graphics.getHeight()/14);
        select2.setPosition(Gdx.graphics.getWidth()/2-select2.getWidth()/2, 100);
        select2.addListener(new InputListener(){
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                tank.setScreen(new TankStar(tank,2,new Tanks(2)));
            }

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
        });

        TextButton select3 = new TextButton("Select",MyGdxGame.default_skin);
        select3.setSize(Gdx.graphics.getWidth()/5,Gdx.graphics.getHeight()/14);
        select3.setPosition(Gdx.graphics.getWidth()/2+130, 100);
        select3.addListener(new InputListener(){
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                tank.setScreen(new TankStar(tank,3,new Tanks(3)));
            }

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
        });
        batch=new SpriteBatch();
        image=new Texture("tank_select.jpg");
        Menu.addActor(select1);
        Menu.addActor(select2);
        Menu.addActor(select3);

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
