package com.badlogic.runner.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Player {

    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 150;
    private Vector3 position;
    private Vector3 velocity;
    private Texture player;
    private Rectangle bounds;
    private Sound jumpSound;


    public Player(int x, int y) {
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        player = new Texture("jog.png");

        bounds = new Rectangle(x,y, player.getWidth(), player.getHeight());
        jumpSound = Gdx.audio.newSound(Gdx.files.internal("jump_sound.wav"));
    }

    public void update(float dt) {
        if (position.y > 70)
            velocity.add(0, GRAVITY, 0);
        velocity.scl(dt);

        position.add(MOVEMENT * dt, velocity.y, 0);
        if (position.y < 70) position.y = 70;

        velocity.scl(1/dt);
        bounds.setPosition(position.x, position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return player;
    }

    public void jump() {
        if (position.y == 70)
        {
            velocity.y = 500;
            jumpSound.play(0.5f);
        }
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose() {
        player.dispose();
        jumpSound.dispose();
    }
}
