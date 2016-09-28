package com.example.arlin.cardgames;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.Rect;

/**
 * Created by Arlind on 13-May-16.
 */
public class PlayingTable {
    //attribute that contains how is the screen divided in x axis
    private final int DIVIDER_X=8;
    //attribute that contains how is the screen divided in y axis
    private Bitmap bitmap;
    private Rect playerOne;
    private Rect throwCards;
    private PointF startDrawingForOne;
    private PointF startDrawingForTwo;
    private PointF startDrawingForThree;
    private PointF startDrawingForFour;


    public PlayingTable(int widthOfScreen,int heightOfScreen){
        int middleX=widthOfScreen/2;
        int middleY=heightOfScreen/2;
        int playingSpace=heightOfScreen-2*GameView.MARGIN;

        throwCards=new Rect(middleX-200,middleY-200,middleX+200,middleY+200);
        playerOne=new Rect(widthOfScreen/DIVIDER_X,heightOfScreen-heightOfScreen/DIVIDER_X,widthOfScreen-widthOfScreen/DIVIDER_X,heightOfScreen);
        //TODO change coordinates for drawing
        startDrawingForFour=new PointF(widthOfScreen-GameView.MARGIN,GameView.MARGIN);
        startDrawingForThree=new PointF((widthOfScreen/2)-playingSpace/2,0);
        startDrawingForTwo=new PointF(0,GameView.MARGIN);
        startDrawingForOne=new PointF((widthOfScreen/2)-playingSpace/2,heightOfScreen-Card.getHeight());
        //TODO add photo
        // bitmap= Bitmap.createScaledBitmap(,widthOfScreen,heightOfScreen,true)

    }

    public Rect getPlayerOne(){
        return playerOne;
    }
    public Rect getThrowCards(){
        return throwCards;
    }
    public PointF getStartDrawingForTwo(){
        return startDrawingForTwo;
    }
    public PointF getStartDrawingForThree(){
        return startDrawingForThree;
    }
    public PointF getStartDrawingForFour(){
        return startDrawingForFour;
    }
    public PointF getStartDrawingForOne(){
        return startDrawingForOne;
    }



}