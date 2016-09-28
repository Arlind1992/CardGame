package com.example.arlin.cardgames;


import java.util.ArrayList;

/**
 * Created by Arlind on 16-May-16.
 */
public class GameState {
    private ArrayList<Card> playedCards;
    public GameState(){
        playedCards=new ArrayList<Card>();
    }
    public ArrayList<Card> getPlayedCards(){
        return playedCards;
    }

}