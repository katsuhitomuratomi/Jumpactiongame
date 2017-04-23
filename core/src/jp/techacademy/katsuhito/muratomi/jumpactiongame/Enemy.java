package jp.techacademy.katsuhito.muratomi.jumpactiongame;

/**
 * Created by katsu on 2017/04/23.
 */
import com.badlogic.gdx.graphics.Texture;

public class Enemy extends GameObject {
    // 横幅、高さ
    public static final float ENEMY_WIDTH = 0.9f;
    public static final float ENEMY_HEIGHT = 0.9f;

    // タイプ（通常と動くタイプ）
    public static final int ENEMY_TYPE_STATIC = 0;
    public static final int ENEMY_TYPE_MOVING = 1;

    // 状態（通常と消えた状態）
    public static final int ENEMY_STATE_NORMAL = 0;
    public static final int ENEMY_STATE_VANISH = 1;

    // 速度
    public static final float ENEMY_VELOCITY = 1.0f;

    int mType;
    int mState;

    public Enemy(int type, Texture texture, int srcX, int srcY, int srcWidth, int srcHeight) {
        super(texture, srcX, srcY, srcWidth, srcHeight);
        setSize(ENEMY_WIDTH, ENEMY_HEIGHT);
        mType = type;
        if (mType == ENEMY_TYPE_MOVING) {
            velocity.x = ENEMY_VELOCITY;
        }
    }

    // 座標を更新する
    public void update(float deltaTime) {
        if (mType == ENEMY_TYPE_MOVING) {
            setX(getX() + velocity.x * deltaTime);

            if (getX() < ENEMY_WIDTH / 2) {
                velocity.x = -velocity.x;
                setX(ENEMY_WIDTH / 2);
            }
            if (getX() > GameScreen.WORLD_WIDTH - ENEMY_WIDTH / 2) {
                velocity.x = -velocity.x;
                setX(GameScreen.WORLD_WIDTH - ENEMY_WIDTH / 2);
            }
        }
    }

    // 消える
    public void vanish() {
        mState = ENEMY_STATE_VANISH;
        setAlpha(0);
        velocity.x = 0;
    }
}