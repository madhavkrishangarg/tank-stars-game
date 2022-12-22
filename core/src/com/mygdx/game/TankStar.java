package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.io.*;
import java.util.ArrayList;

public class TankStar implements Screen, Serializable {
    static Tanks tanks_static;
    static int saved;
    SpriteBatch batch;
    BitmapFont font;
    Texture image;
    float time = 0f;
    float time2 = 0f;
    int tank1_health = 100;
    int tank2_health = 100;
    private final Stage Menu;
    private final Game tank;
    private final Tanks tanks;
    private final ArrayList<Projectile> lefttank_projectile;
    private final ArrayList<SecondProjectile> righttank_projectile;
    private final ArrayList<Projectile> lefttank_discard;
    private final ArrayList<SecondProjectile> righttank_discard;
    private int count = 0;


    public TankStar(Game g, final int tank_choice,Tanks tks) {
        Skin default_skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        tank = g;
        Menu = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(Menu);
        tanks = tks;
        tanks_static = tanks;
        final TextButton resume = new TextButton("Resume Game", MyGdxGame.default_skin);
        resume.setSize(Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() / 13);
        resume.setPosition(10, Gdx.graphics.getHeight() - 120);
        resume.addListener(new InputListener() {
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                tank.setScreen(new TankStar(tank,tank_choice,TankStar.tanks_static));
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
                try {
                    TankStar.saved=1;
                    File f = new File("save.txt");
                    FileOutputStream file_output = new FileOutputStream(f);
                    ObjectOutputStream object_output = new ObjectOutputStream(file_output);
                    object_output.writeObject(tanks);

                    FileInputStream file_input = new FileInputStream(f);
                    ObjectInputStream object_input = new ObjectInputStream(file_input);
                    Tanks Tanks1 = (Tanks) object_input.readObject();
                    tank.setScreen(new TankStar(tank, tank_choice,Tanks1));


                } catch (Exception e) {
                    System.out.println("Saving ...");
                }
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
//

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
                Projectile.ReduceAngle(2 * Gdx.graphics.getDeltaTime());
                if (Projectile.getB() > 0 && tanks.anglebar_left.width > 1) {
                    tanks.anglebar_left.width -= 20 * Gdx.graphics.getDeltaTime();
                }
            } else {
                SecondProjectile.ReduceAngle(2 * Gdx.graphics.getDeltaTime());
                if (SecondProjectile.getB() > 0 && tanks.anglebar_right.width > 1) {
                    tanks.anglebar_right.width -= 20 * Gdx.graphics.getDeltaTime();
                }
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if (count % 2 == 0) {
                Projectile.IncreaseAngle(2 * Gdx.graphics.getDeltaTime());
                if (Projectile.getA() > 0 && tanks.anglebar_left.width < 100) {
                    tanks.anglebar_left.width += 20 * Gdx.graphics.getDeltaTime();
                }
            } else {
                SecondProjectile.IncreaseAngle(2 * Gdx.graphics.getDeltaTime());
                if (SecondProjectile.getA() > 0 && tanks.anglebar_right.width < 100) {
                    tanks.anglebar_right.width += 20 * Gdx.graphics.getDeltaTime();
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
                tanks.tank2_health = tanks.tank2_health - 2;
                tanks.healthbar_right.width = tanks.tank2_health;

            } else if (lefttank_projectile.get(0).getX() < tanks.tank2.getX() && (tanks.tank2.getX() - lefttank_projectile.get(0).getX()) < 5) {
                tanks.tank2_health = (int) (tanks.tank2_health - (2 - (tanks.tank2.getX() - lefttank_projectile.get(0).getX())));
                tanks.healthbar_right.width = tanks.tank2_health;

            } else if (lefttank_projectile.get(0).getX() < (tanks.tank2.getX() + tanks.tank2.getWidth()) && ((tanks.tank2.getX() + tanks.tank2.getWidth()) - lefttank_projectile.get(0).getX()) < 5) {
                tanks.tank2_health = (int) (tanks.tank2_health - (2 - ((tanks.tank2.getX() + tanks.tank2.getWidth()) - lefttank_projectile.get(0).getX())));
                tanks.healthbar_right.width = tanks.tank2_health;

            }
        }


        if (!righttank_projectile.isEmpty() && righttank_projectile.get(0).getY() < (tanks.tank1.getY() + tanks.tank1.height)) {
            if (righttank_projectile.get(0).getX() > tanks.tank1.getX() && righttank_projectile.get(0).getX() < (tanks.tank1.getX() + tanks.tank1.getWidth())) {
                tanks.tank1_health = tanks.tank1_health - 2;
                tanks.healthbar_left.width = tanks.tank1_health;

            } else if (righttank_projectile.get(0).getX() < tanks.tank1.getX() && (tanks.tank1.getX() - righttank_projectile.get(0).getX()) < 5) {
                tanks.tank1_health = (int) (tanks.tank1_health - (2 - (tanks.tank1.getX() - righttank_projectile.get(0).getX())));
                tanks.healthbar_left.width = tanks.tank1_health;

            } else if (righttank_projectile.get(0).getX() < (tanks.tank1.getX() + tanks.tank1.getWidth()) && ((tanks.tank1.getX() + tanks.tank1.getWidth()) - righttank_projectile.get(0).getX()) < 5) {
                tanks.tank1_health = (int) (tanks.tank1_health - (2 - ((tanks.tank1.getX() + tanks.tank1.getWidth()) - righttank_projectile.get(0).getX())));
                tanks.healthbar_left.width = tanks.tank1_health;

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
        batch.draw(tanks.health_left, tanks.healthbar_left.x, tanks.healthbar_left.y, tanks.healthbar_left.width, tanks.healthbar_left.height);
        batch.draw(tanks.health_right, tanks.healthbar_right.x, tanks.healthbar_right.y, tanks.healthbar_right.width, tanks.healthbar_right.height);
        batch.draw(tanks.angle_left, tanks.anglebar_left.x, tanks.anglebar_left.y, tanks.anglebar_left.width, tanks.anglebar_left.height);
        batch.draw(tanks.angle_right, tanks.anglebar_right.x, tanks.anglebar_right.y, tanks.anglebar_right.width, tanks.anglebar_right.height);

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
