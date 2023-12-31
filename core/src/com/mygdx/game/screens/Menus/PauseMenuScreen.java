package com.mygdx.game.screens.Menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.ourgdx.game.GameMain;
import com.ourgdx.game.MainAssets;

public class PauseMenuScreen implements Screen {
    // The pause screen is a near carbon copy of the main menu screen,
    // see the comments there.

    //instance variables
    private GameMain parent;
    private Stage stage;
    private Skin menuSkin;

    public PauseMenuScreen(GameMain gameMain) {
        // Set parent to the orchestrator
        parent = gameMain;

        // initialize our stage
        stage = new Stage(new ScreenViewport());

        parent.assets.loadAsset(MainAssets.skin);
        parent.assets.manager.finishLoading();
        menuSkin = parent.assets.manager.get(MainAssets.skin.location());
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table(menuSkin);
        table.setFillParent(true);
        //table.setDebug(true);
        table.background("window");
        stage.addActor(table);


        TextButton resume = new TextButton("Resume", menuSkin);
        TextButton preferences = new TextButton("Preferences", menuSkin);
        TextButton quit = new TextButton("Quit", menuSkin);

        table.add(resume).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(preferences).fillX().uniformX();
        table.row();
        table.add(quit).fillX().uniformX();


        // create button listeners

        resume.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.updateActivePreferences();
                parent.changeScreen(parent.getPreviousScreen(), GameMain.PAUSE);
            }
        });

        preferences.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(GameMain.PREFERENCES, GameMain.PAUSE);
            }
        });

        quit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.clearGame();
                parent.changeScreen(GameMain.MAIN_MENU, GameMain.PAUSE);
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // tell our stage to do actions and draw itself
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}

