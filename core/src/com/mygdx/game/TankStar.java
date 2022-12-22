package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class TankStar implements Screen, Serializable {
    SpriteBatch batch;
    BitmapFont font;
    Texture image;
    float time = 0f;
    float time2 = 0f;
    int tank1_health = 100;
    int tank2_health = 100;
    private Stage Menu;
    private Game tank;
    private Tanks tanks;
    private ArrayList<Projectile> lefttank_projectile;
    private ArrayList<SecondProjectile> righttank_projectile;
    private ArrayList<Projectile> lefttank_discard;
    private ArrayList<SecondProjectile> righttank_discard;
    private int count = 0;
    private Texture health_left;
    private Texture health_right;
    private Texture angle_left;
    private Texture angle_right;
    private Rectangle anglebar_left;
    private Rectangle anglebar_right;
    private Rectangle healthbar_left;
    private Rectangle healthbar_right;

    public TankStar(Game g, int tank_choice) {
        Skin default_skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        tank = g;
        Menu = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(Menu);
        tanks = new Tanks(tank_choice);
        final TextButton resume = new TextButton("Resume Game", MyGdxGame.default_skin);
        resume.setSize(Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() / 13);
        resume.setPosition(10, Gdx.graphics.getHeight() - 120);
        resume.addListener(new InputListener() {
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                //tank.setScreen(new TankStar(tank));
            }

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        final TextButton save_game = new TextButton("Save Game", MyGdxGame.default_skin);
        save_game.setSize(Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() / 13);
        save_game.setPosition(10, resume.getY() - save_game.getHeight() - 5);
        save_game.addListener(new InputListener() {
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                //tank.setScreen(new TankStar(tank));
            }

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        final TextButton exit = new TextButton("Exit", MyGdxGame.default_skin);
        exit.setSize(Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() / 13);
        exit.setPosition(10, save_game.getY() - exit.getHeight() - 5);
        exit.addListener(new InputListener() {
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                tank.setScreen(new MainMenu(tank));
            }

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        TextButton pause = new TextButton("| |", MyGdxGame.default_skin);
        pause.setSize(Gdx.graphics.getWidth() / 20, Gdx.graphics.getHeight() / 15);
        pause.setPosition(10, Gdx.graphics.getHeight() - 80);
        pause.addListener(new InputListener() {
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Menu.addActor(resume);
                Menu.addActor(save_game);
                Menu.addActor(exit);
            }

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        batch = new SpriteBatch();
        image = new Texture("1.jpg");
        Menu.addActor(pause);

        lefttank_projectile = new ArrayList<>();
        righttank_projectile = new ArrayList<>();
        lefttank_discard = new ArrayList<>();
        righttank_discard = new ArrayList<>();

//        anglebar_left=new Rectangle();
//        anglebar_right=new Rectangle();
//
//        anglebar_left.x = 40;
//        anglebar_left.y = 20;
//        anglebar_left.width = 10;
//        anglebar_left.height = 1;
//        anglebar_right.x = 540;
//        anglebar_right.y = 20;
//        anglebar_right.width = 10;
//        anglebar_right.height = 1;
//        angle_left = new Texture("green_rect.png");
//        angle_right = new Texture("green_rect.png");
//        health_left = new Texture("yellow_rect.png");
//        health_right = new Texture("yellow_rect.png");
    }

    public int getTank1_health() {
        return tank1_health;
    }

    public int getTank2_health() {
        return tank2_health;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(Menu);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
//             left will start first
            if (count % 2 == 0) {
                lefttank_projectile.add(new Projectile(tanks.tank1.getX() + 55, tanks.tank1.getY() + 32));
                count++;
                righttank_projectile.clear();
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.M)) {
            if (count % 2 != 0) {
                righttank_projectile.add(new SecondProjectile(tanks.tank2.getX(), tanks.tank2.getY() + 32));
                count++;
                lefttank_projectile.clear();
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            if (count % 2 == 0) {
                Projectile.ReduceAngle(5 * Gdx.graphics.getDeltaTime());
//                if (Projectile.getB() > 0 && anglebar_left.height > 1) {
//                    anglebar_left.height -= 80 * Gdx.graphics.getDeltaTime();
//                }
            } else {
                SecondProjectile.ReduceAngle(5 * Gdx.graphics.getDeltaTime());
//                if (SecondProjectile.getB() > 0 && anglebar_right.height > 1) {
//                    anglebar_right.height -= 80 * Gdx.graphics.getDeltaTime();
//                }
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if (count % 2 == 0) {
                Projectile.IncreaseAngle(5 * Gdx.graphics.getDeltaTime());
                if (Projectile.getA() > 0 && anglebar_left.height < 50) {
                    anglebar_left.height += 80 * Gdx.graphics.getDeltaTime();
                }
            } else {
                SecondProjectile.IncreaseAngle(5 * Gdx.graphics.getDeltaTime());
                if (SecondProjectile.getA() > 0 && anglebar_right.height < 50) {
                    anglebar_right.height += 80 * Gdx.graphics.getDeltaTime();
                }
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (count % 2 == 0) {
                if (0 <= tanks.tank1.x)
                    tanks.tank1.x -= 120 * Gdx.graphics.getDeltaTime();
            } else {
                if (Gdx.graphics.getWidth() / 2 <= tanks.tank2.x)
                    tanks.tank2.x -= 120 * Gdx.graphics.getDeltaTime();
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (count % 2 == 0) {
                if (tanks.tank1.x <= Gdx.graphics.getWidth() / 2.5) {
                    tanks.tank1.x += 120 * Gdx.graphics.getDeltaTime();
                }
            } else {
                if (tanks.tank2.x <= Gdx.graphics.getWidth()) {
                    tanks.tank2.x += 120 * Gdx.graphics.getDeltaTime();
                }
            }
        }

//        if (tanks.tank1.x > Gdx.graphics.getWidth()/2.5)
//            tanks.tank1.x = (float) (Gdx.graphics.getWidth()/2.5);
//        if (tanks.tank1.x < 0)
//            tanks.tank1.x = 0;
//
//        if (tanks.tank2.x > Gdx.graphics.getWidth())
//            tanks.tank2.x = Gdx.graphics.getWidth();
//        if (tanks.tank2.x < Gdx.graphics.getWidth()/2.5)
//            tanks.tank2.x = (float) (Gdx.graphics.getWidth()/2.5);

        for (Projectile projectile : lefttank_projectile) {
            time += (Gdx.graphics.getDeltaTime());
            projectile.update(time);
            if (projectile.remove) {
                lefttank_discard.add(projectile);
                time = 0f;
            }
        }
        lefttank_projectile.removeAll(lefttank_discard);


        for (SecondProjectile Secondprojectile : righttank_projectile) {
            time2 += (Gdx.graphics.getDeltaTime());
            Secondprojectile.update(time2);
            if (Secondprojectile.remove) {
                righttank_discard.add(Secondprojectile);
                time2 = 0f;
            }
        }
        righttank_projectile.removeAll(righttank_discard);

            if (!lefttank_projectile.isEmpty() && lefttank_projectile.get(0).getY() < (tanks.tank2.getY() + tanks.tank2.height)) {
                if (lefttank_projectile.get(0).getX() > tanks.tank2.getX() && lefttank_projectile.get(0).getX() < (tanks.tank2.getX() + tanks.tank2.getWidth())) {
                    tanks.tank2_health -= 20;
//                    System.out.println("tank2 : " + tank2_health);
                }
                if (lefttank_projectile.get(0).getX() < tanks.tank2.getX() && (tanks.tank2.getX() - lefttank_projectile.get(0).getX()) < 20) {
                    tanks.tank2_health -= 20 - (tanks.tank2.getX() - lefttank_projectile.get(0).getX());
//                    System.out.println("tank2 : " + tank2_health);
                }
                if (lefttank_projectile.get(0).getX() < (tanks.tank2.getX() + tanks.tank2.getWidth()) && ((tanks.tank2.getX() + tanks.tank2.getWidth()) - lefttank_projectile.get(0).getX()) < 20) {
                    tanks.tank2_health -= 20 - ((tanks.tank2.getX() + tanks.tank2.getWidth()) - lefttank_projectile.get(0).getX());
//                    System.out.println("tank2 : " + tank2_health);
                }
            }


            if (!righttank_projectile.isEmpty() && righttank_projectile.get(0).getY() < (tanks.tank1.getY() + tanks.tank1.height)) {
                if (righttank_projectile.get(0).getX() > tanks.tank1.getX() && righttank_projectile.get(0).getX() < (tanks.tank1.getX() + tanks.tank1.getWidth())) {
                    tanks.tank1_health -= 20;
//                    System.out.println("tank1 : " + tank1_health);
                }
                if (righttank_projectile.get(0).getX() < tanks.tank1.getX() && (tanks.tank1.getX() - righttank_projectile.get(0).getX()) < 20) {
                    tanks.tank1_health -= 20 - (tanks.tank1.getX() - righttank_projectile.get(0).getX());
//                    System.out.println("tank1 : " + tank1_health);
                }
                if (righttank_projectile.get(0).getX() < (tanks.tank1.getX() + tanks.tank1.getWidth()) && ((tanks.tank1.getX() + tanks.tank1.getWidth()) - righttank_projectile.get(0).getX()) < 20) {
                    tanks.tank1_health -= 20 - ((tanks.tank1.getX() + tanks.tank1.getWidth()) - righttank_projectile.get(0).getX());
//                    System.out.println("tank1 : " + tank1_health);
                }
            }

        if (tanks.tank1_health < 1 || tanks.tank2_health < 1)
            tank.setScreen(new GameOver(tank));
//        Double angle_left=Math.atan2(Projectile.getB(),Projectile.getA());
//        font.draw(batch,angle_left.toString(),20,30);
//        Double angle_right=Math.atan2(SecondProjectile.getB(),SecondProjectile.getA());
//        font.draw(batch,angle_right.toString(),420,30);
        batch.begin();
        batch.draw(image, 0, 20, 640, 430);
        batch.draw(tanks.tankimage1, tanks.tank1.x, tanks.tank1.y, tanks.tank1.width, tanks.tank1.height);
        batch.draw(tanks.tankimage2, tanks.tank2.x, tanks.tank2.y, tanks.tank2.width, tanks.tank2.height);
//        batch.draw(health_left, rectangle1.x, rectangle1.y, rectangle1.width, rectangle1.height);
//        batch.draw(health_right, rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height);

        //batch.draw(whitedot, dot.x, dot.y, dot.width, dot.height);
        for (Projectile projectile : lefttank_projectile) {
            projectile.render(batch);
        }
        for (SecondProjectile projectile : righttank_projectile) {
            projectile.render(batch);
        }
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
