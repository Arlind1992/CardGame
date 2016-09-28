package com.example.arlin.cardgames;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;

import java.util.concurrent.locks.Lock;
import java.util.logging.Logger;

public class GameActivity extends Activity {

    private GameView game;
    private GameController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  Get a Display object to access screen details
        Display display = getWindowManager().getDefaultDisplay();
// Load the resolution into a Point object
        Point resolution = new Point();
        display.getSize(resolution);
        game=new GameView(this,resolution.x,resolution.y);
        controller=new PeseKateshGameController(game,this);
        game.setGameController(controller);
        //    controller.initialize();
        //this.setContentView(game);
        (new LoadTask(new ProgressDialog(this),controller,game,this)).execute();

    }
    @Override
    protected void onResume(){
        super.onResume();

       /* controller.initializeGame();
        controller.initializeCards(this);
        */
       game.resume();
    }
    // If the Activity is paused make sure to pause our thread
    @Override
    protected void onPause() {
        super.onPause();
        game.pause();
    }

}
