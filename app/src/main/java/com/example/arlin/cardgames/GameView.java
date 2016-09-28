package com.example.arlin.cardgames;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Looper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * Created by Arlind on 11-May-16.
 */
public class GameView extends SurfaceView implements Runnable{
    private Context context;
    private Thread gameThread=null;
    public final static int MARGIN=100;
    //TODO delete after testing
    private Card card;
    // For drawing
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder ourHolder;
    private PlayingTable table;
    private GameController gameController;
    public GameView(Context context,int screenWidth,int screenHeight) {
        super(context);
        this.context=context;
        ourHolder = getHolder();
        Card.setCardSize((screenHeight-2*GameView.MARGIN)/13,2*(screenHeight-2*GameView.MARGIN)/13);
        paint = new Paint();
        this.table=new PlayingTable(screenWidth,screenHeight);
        //TODO delete
        /*card=new Card(3,Type.CLUBS,true,true);
        card.setPosition(new PointF(300,400));
        */



    }

    public void draw(){

        if (ourHolder.getSurface().isValid()) {

            canvas = ourHolder.lockCanvas();
            // Rub out the last frame
            paint.setColor(Color.argb(255, 0, 0, 0));
            canvas.drawColor(Color.argb(255, 0, 0, 0));
            //TODO test
            /*card.setResourceName();
            card.setBitmap(context);
            canvas.drawBitmap(card.getBitmap(), card.getPosition().x, card.getPosition().y, paint);
            */


            //TODO delete coment
            this.drawCards(canvas, paint);

            // Unlock and draw the scene
            ourHolder.unlockCanvasAndPost(canvas);
        }


    }




    @Override
    public void run() {

        while(true) {
            draw();
            update();
        }
    }

    public  void resume(){
        gameThread=new Thread(this);
        gameThread.start();

    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent){
        int x=(int)motionEvent.getX();
        int y=(int)motionEvent.getY();
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {

// Has the player lifted their finger up?
            case MotionEvent.ACTION_UP:

                break;
// Has the player touched the screen?
            case MotionEvent.ACTION_DOWN:
                //TODO delete after test
                //card.handleInput(motionEvent);
                //TODO delete coment
                this.gameController.handleInput(motionEvent);
// Do something here
                break;
        }
        return true;

    }
    public void update(){
        GameController controller=this.gameController;
        if(controller!=null){
            Player player=controller.getCurrentPlayer();
            if(player!=null){
                for(Card card:this.gameController.getCurrentPlayer().getCards()) {
                    card.move();
                }
            }


        }


    }

    private void drawCards(Canvas canvas,Paint paint){



        for(Player player:gameController.getPlayers()) {
            for (Card card : player.getCards()) {
                if(card.isHorizontal()){
                    Bitmap rotated;
                    Bitmap original=card.getBitmap();
                    Matrix matrix = new Matrix();
                    matrix.postRotate(90);
                    rotated = Bitmap.createBitmap(original, 0, 0,
                            original.getWidth(), original.getHeight(),
                            matrix, true);
                    canvas.drawBitmap(rotated,card.getPosition().x,card.getPosition().y,paint);

                }else{
                    canvas.drawBitmap(card.getBitmap(), card.getPosition().x, card.getPosition().y, paint);
                }

            }

        }
        for(Card card:gameController.getCardsOnTable()){
            canvas.drawBitmap(card.getBitmap(),card.getPosition().x,card.getPosition().y,paint);

        }
    }
    public void setGameController(GameController gameController){
        this.gameController=gameController;
        gameController.setTable(table);
    }
    public void pause(){
        try {
            gameThread.join();
        } catch (InterruptedException e) {
        }
    }


}
