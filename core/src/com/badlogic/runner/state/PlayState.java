package com.badlogic.runner.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.runner.Main;
import com.badlogic.runner.sprites.Player;
import com.badlogic.runner.sprites.Stone;

public class PlayState extends State {
    private static final int TUBE_SPACING = 500;
    private static final int TUBE_COUNT = 200;
    private float counter;

    private Player player;
    private Texture bg;


    private Array<Stone> stones;
    BitmapFont font = new BitmapFont();

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        player = new Player(50, 200);
        cam.setToOrtho(false, Main.WIDTH / 2,
                Main.HEIGHT / 2);
        bg = new Texture("bg.png");
        stones = new Array<Stone>();
        for (int i = 1; i <= TUBE_COUNT; i++) {
            stones.add(new Stone(i * (TUBE_SPACING + Stone.TUBE_WIDTH)));
        }
        counter = 0;
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            player.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        player.update(dt);
        counter+= dt;
        cam.position.x = player.getPosition().x + 100;


        for (int i=0;i<stones.size;i ++) {
            Stone stone = stones.get(i);
            if (cam.position.x - (cam.viewportWidth / 2)
                    > stone.getPosStone().x + stone.getPosStone().y + 0) {
                stone.reposition(stone.getPosStone().x + (Stone.TUBE_WIDTH + TUBE_SPACING * TUBE_COUNT));
            }

            if(stone.collides(player.getBounds()))
            {
                Preferences prefs = Gdx.app.getPreferences("My Preferences");

                Integer topScore = prefs.getInteger("score");
                if (topScore < counter)
                 prefs.putInteger("score", (int)counter);

                prefs.flush();

                gsm.set(new MenuState(gsm));
            }
        }

        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glClearColor(0, 0, 0, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.setProjectionMatrix(cam.combined);
        sb.begin();

        sb.draw(bg, cam.position.x - cam.viewportWidth / 2, 0);
        sb.draw(player.getTexture(), player.getPosition().x, player.getPosition().y);
        for (Stone stone : stones) {
            sb.draw(stone.getStone(), stone.getPosStone().x, stone.getPosStone().y, 50, 50);
        }
        font.draw(sb, "Score: " + ((int)counter), cam.position.x - cam.viewportWidth / 2 + 30, 350);
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        player.dispose();

        for (Stone stone: stones)
            stone.dispose();
        System.out.println("Play State disposed;");
    }
}
