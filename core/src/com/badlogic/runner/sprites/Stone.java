package com.badlogic.runner.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Stone {
    public static final int TUBE_WIDTH = 50;
    public static final int TUBE_GAP = 70;
    public static final int FLUCTUATION = 130;
    private Texture stone;
    private Vector2 posTube;
    private Rectangle boundsTube;

    public Stone(float x) {
        stone = new Texture("stone2.png");

        posTube = new Vector2(x, TUBE_GAP);
        boundsTube = new Rectangle(posTube.x, posTube.y, 50, 50);

    }


    public Texture getStone() {
        return stone;
    }

    public Vector2 getPosStone() {
        return posTube;
    }

    public void reposition(float x) {
        posTube.set(x, TUBE_GAP);
        boundsTube.setPosition(posTube.x, posTube.y);
    }

    public boolean collides(Rectangle player) {
        return player.overlaps(boundsTube);
    }

    public void dispose() {
        stone.dispose();
    }
}
