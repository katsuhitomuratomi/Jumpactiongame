package jp.techacademy.katsuhito.muratomi.jumpactiongame;

/**
 * Created by katsu on 2017/04/23.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

import static jp.techacademy.katsuhito.muratomi.jumpactiongame.GameScreen.GAME_CLEAR;
import static jp.techacademy.katsuhito.muratomi.jumpactiongame.GameScreen.GAME_STATE_GAMEOVER;

public class ResultScreen extends ScreenAdapter {
    static final float GUI_WIDTH = 320;
    static final float GUI_HEIGHT = 480;

    private JumpActionGame mGame;
    Sprite mBg;
    OrthographicCamera mGuiCamera;
    FitViewport mGuiViewPort;
    BitmapFont mFont;
    Music music;
    int Gamestate;
    int mScore;


    public ResultScreen(JumpActionGame game, int score, int mGameState) {

        mGame = game;
        if (mGame.mRequestHandler != null) {
            mGame.mRequestHandler.showAds(true);
        }
        mScore = score;
        Gamestate = mGameState;

        // 背景の準備
        Texture bgTexture = new Texture("resultback.png");
        mBg = new Sprite(new TextureRegion(bgTexture, 0, 0, 540, 810));
        mBg.setSize(GUI_WIDTH, GUI_HEIGHT);
        mBg.setPosition(0, 0);

        // GUI用のカメラを設定する
        mGuiCamera = new OrthographicCamera();
        mGuiCamera.setToOrtho(false, GUI_WIDTH, GUI_HEIGHT);
        mGuiViewPort = new FitViewport(GUI_WIDTH, GUI_HEIGHT, mGuiCamera);

        // フォント
        mFont = new BitmapFont(Gdx.files.internal("font.fnt"), Gdx.files.internal("font.png"), false);
        if(mGameState==GAME_CLEAR){
            music = Gdx.audio.newMusic(Gdx.files.internal("gameclear.mp3"));
            music.setVolume(1.0f);
            music.setLooping(true);
        }else if(mGameState==GAME_STATE_GAMEOVER){
            music = Gdx.audio.newMusic(Gdx.files.internal("gameover1.mp3"));
            music.setVolume(1.0f);
            music.setLooping(true);
        }


        music.play();
    }

    @Override
    public void render(float delta) {
        // 描画する
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // カメラの座標をアップデート（計算）し、スプライトの表示に反映させる
        mGuiCamera.update();
        mGame.batch.setProjectionMatrix(mGuiCamera.combined);
        if (Gamestate== GAME_CLEAR){

            mGame.batch.begin();
            mBg.draw(mGame.batch);
            mFont.draw(mGame.batch, "Score: " + mScore, 0, GUI_HEIGHT / 2 + 40, GUI_WIDTH, Align.center, false);
            mFont.draw(mGame.batch, "Congratulations!!!!", 0, GUI_HEIGHT / 2 - 40, GUI_WIDTH, Align.center, false);
            mGame.batch.end();
        }else if(Gamestate== GAME_STATE_GAMEOVER) {
            mGame.batch.begin();
            mBg.draw(mGame.batch);
            mFont.draw(mGame.batch, "Score: " + mScore, 0, GUI_HEIGHT / 2 + 40, GUI_WIDTH, Align.center, false);
            mFont.draw(mGame.batch, "Retry??", 0, GUI_HEIGHT / 2 - 40, GUI_WIDTH, Align.center, false);
            mGame.batch.end();
        }



        if (Gdx.input.justTouched()) {
            if (mGame.mRequestHandler != null) {
                mGame.mRequestHandler.showAds(false);
            }

            mGame.setScreen(new GameScreen(mGame));
            music.stop();
        }
    }
}