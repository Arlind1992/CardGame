package com.example.arlin.cardgames;

import android.graphics.PointF;

/**
 * Created by Arlind on 12-May-16.
 */
public class MovingObject {
    private PointF position;
    private int xSpeed;
    private int ySpeed;
    private boolean moving;
    private PointF destination;

    public MovingObject(){
        xSpeed=10;
        ySpeed=10;
        moving=false;
    }
    public void setDestination(PointF destination){
        this.destination=destination;
        moving=true;
    }
    public void move(){
        if(isMoving()) {
            if (destination.equals(position)) {
                moving = false;
            } else {
                position.x = position.x + (destination.x - position.x) / xSpeed;
                position.y = position.y + (destination.y - position.y) / ySpeed;

            }
        }

    }
    public PointF getPosition(){
        return this.position;
    }
    public void setPosition(PointF position){
        this.position=position;
    }
    //method to be used when selecting and deselecting an object
    public void addToPosition(int x,int y){
        this.position.x=this.position.x+x;
        this.position.y=this.position.y+y;
    }
    public boolean isMoving(){
        return this.moving;
    }



}
