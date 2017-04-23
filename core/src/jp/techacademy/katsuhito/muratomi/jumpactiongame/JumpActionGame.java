package jp.techacademy.katsuhito.muratomi.jumpactiongame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class JumpActionGame extends Game {
    //publicにして外からアクセルできるようにする
    public SpriteBatch batch;


    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new GameScreen(this));
    }


}

