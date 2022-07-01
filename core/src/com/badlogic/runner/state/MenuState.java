package com.badlogic.runner.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.runner.Main;

public class MenuState extends State {

    private Texture background;
    private Texture playBtn;
    BitmapFont font = new BitmapFont();



    public MenuState(GameStateManager gsm) {
        super(gsm);
//        cam.update();
        background = new Texture("background.jpg");
        playBtn = new Texture("playbtn.png");

    }


    @Override
    public void handleInput() {

        if (Gdx.input.justTouched() ) {
            if (Gdx.input.getX() > (Main.WIDTH / 2) - (playBtn.getWidth() / 2)
            && Gdx.input.getX() < (Main.WIDTH / 2) - (playBtn.getWidth() / 2) + playBtn.getWidth()
            && Gdx.input.getY() > (Main.HEIGHT / 3)
                    && Gdx.input.getY() < (Main.HEIGHT / 3) + playBtn.getHeight()) {
                gsm.set(new PlayState(gsm));
            }

        }

    }

    @Override
    public void update(float dt) {

        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glClearColor(0, 0, 0, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.begin();
        sb.draw(background, 0, 0, Main.WIDTH, Main.HEIGHT);
        sb.draw(playBtn, (Main.WIDTH / 2) - (playBtn.getWidth() / 2),
                (Main.HEIGHT / 3));

        Preferences prefs = Gdx.app.getPreferences("My Preferences");

        Integer topScore = prefs.getInteger("score");


        font.draw(sb, "Top score: " + (topScore), cam.position.x - cam.viewportWidth / 2 + 30, 600);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        System.out.println("Menu State disposed");
    }
}
