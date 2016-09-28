package com.example.arlin.cardgames;

/**
 * Created by arlin on 29-Aug-16.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.view.MotionEvent;

/**
 * Created by Arlind on 11-May-16.
 */
public class Card extends MovingObject{
    private final int DISTORTION_WHEN_SELECTED=15;
    private final int MOVED_WHEN_SELECTED=20;
    private Bitmap bitmap;
    private Type type;
    private int number;
    private String resourceName;
    private static int width;
    private static int height;
    private boolean selected;
    private boolean visible;
    private boolean horizontal;

    public Card( int number, Type type,boolean visible,boolean horizontal) {
        super();
        this.number=number;
        this.type=type;
        this.visible=visible;
        this.selected=false;
        this.horizontal=horizontal;
    }

    public Card(int number,boolean visible,boolean horizontal) {
        super();
        this.visible=visible;
        this.number=number;
        this.horizontal=horizontal;

    }


    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public Type getType() {
        return this.type;
    }

    public int getNumber() {
        return this.number;
    }



    public boolean handleInput(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        PointF pos=getPosition();
        if(x>=pos.x&&x<=(pos.x+width)&&y>=pos.y&&y<(pos.y+height)){

            switch (motionEvent.getAction() &
                    MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    if(!selected) {
                        select();

                        return true;
                    }else{
                        deselect();
                        return false;
                    }

            }


        }
        return false;


    }

    private void select(){
        addToPosition(-MOVED_WHEN_SELECTED,-MOVED_WHEN_SELECTED);
        height= height+DISTORTION_WHEN_SELECTED;
        width=width+DISTORTION_WHEN_SELECTED;
        bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
        selected=true;
    }

    private void deselect(){
        addToPosition(MOVED_WHEN_SELECTED,MOVED_WHEN_SELECTED);
        height= height-DISTORTION_WHEN_SELECTED;
        width=width-DISTORTION_WHEN_SELECTED;
        bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
        selected=false;
    }

    public boolean isVisible(){
        return visible;
    }

    public void setVisible(boolean visible){
        this.visible=visible;
    }

    public void setBitmap(Context context){

        int id = context.getResources().getIdentifier(this.resourceName, "drawable", context.getPackageName());
        bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource
                (context.getResources(), id), Card.width, Card.height, true);
    }

    public void setResourceName(){
        String cardNumber;
        if(isVisible()) {
            if (number > 1 && number <= 10) {

                cardNumber = "a" + number;


            } else {
                switch (number) {
                    case 1:
                        cardNumber = "ace";
                        break;
                    case 11:
                        cardNumber = "jack";
                        break;
                    case 12:
                        cardNumber = "queen";
                        break;
                    case 13:
                        cardNumber = "king";
                        break;
                    default:
                        cardNumber = "";
                }
            }
            String picture = cardNumber + "_of_" + type.toString().toLowerCase();

            if (number == 14) {
                picture = "black_jocker";
            } else {
                if (number == 15) {
                    picture = "red_jocker";
                }
            }

            this.resourceName = picture;
        }else{

            this.resourceName="back_card";

            }
    }

    public static void setCardSize(int width,int height){
        Card.height=height;
        Card.width=width;

    }
    public static int getWidth(){
        return Card.width;
    }
    public static int getHeight(){
        return Card.height;
    }
    public boolean isHorizontal(){
        return this.horizontal;
    }
    public void setHorizontal(boolean horizontal){
        this.horizontal=horizontal;
    }


}
