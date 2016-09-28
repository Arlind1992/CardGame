package com.example.arlin.cardgames;

import java.util.ArrayList;

/**
 * Created by Arlind on 16-May-16.
 */
public abstract class OponentAI  extends Player{

    private ArrayList<Card> cards;
    public OponentAI(){

    }

    public abstract Card chooseCard(GameState gameState);
}
