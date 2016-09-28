package com.example.arlin.cardgames;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.PointF;
import android.view.MotionEvent;

import java.util.ArrayList;

/**
 * Created by Arlind on 16-May-16.
 */
public abstract class GameController {
    private PlayingTable table;
    private Deck deck;
    private GameView gameView;
    private ArrayList<Player> players;
    private ArrayList<Card> cardsOnTable;
    private Player currentPlayer;
    private Context context;
    private boolean inizialized;
    private ArrayList<Card> selectedCards;
    private boolean playing;


    public abstract void initialize();
    public abstract void initializeGame();
    public abstract void initializeCards(Context context);

    public GameController(GameView gameView,Context context){
        this.inizialized=false;
        this.context=context;
        this.gameView=gameView;
        players=new ArrayList<Player>();
        cardsOnTable=new ArrayList<Card>();
        selectedCards=new ArrayList<Card>();
    }

    public abstract void divideCards();
    public ArrayList<Player> getPlayers(){
        return this.players;
    }
    public Deck getDeck(){
        return this.deck;
    }

    public void setDeck(Deck deck){
        this.deck=deck;
    }

    public ArrayList<Card> getCardsOnTable(){
        return this.cardsOnTable;
    }
    public abstract boolean checkPlayerChoise(Card card);

    public Player getCurrentPlayer(){
        return this.currentPlayer;
    }
    public void setCurrentPlayer(Player currentPlayer){
        this.currentPlayer=currentPlayer;
    }

    public void setNextPlayer(){
        int number=0;
        for(Player player:players){
            if(player.equals(currentPlayer)){
                break;
            }
            number++;
        }
        if(number+1==players.size()){
            number=0;
        }
        currentPlayer=players.get(number);

    }

    public PlayingTable getTable(){
        return this.table;
    }
    public void setTable(PlayingTable table){
        this.table=table;
    }
    public Context getContext(){
        return this.context;
    }
    public boolean getInitialized(){
        return this.inizialized;
    }
    public void setInizialized(boolean inizialized){
        this.inizialized=inizialized;
    }

    public Card selectCard(Player player, MotionEvent motionEvent){
        for(Card cards:player.getCards()){
            if(cards.handleInput(motionEvent)){
                this.selectedCards.add(cards);
            }else{
                this.selectedCards.remove(cards);
            }
        }
        return null;
    }

    public ArrayList<Card> getSelectedCards(){
        return this.selectedCards;
    }
    public void handleInput(MotionEvent motionEvent){
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if(this.table.getPlayerOne().contains(x,y)){
            selectCard(this.getPlayers().get(0),motionEvent);
        }else{
            if(this.table.getThrowCards().contains(x,y)){
                if(this.selectedCards.size()!=0) {
                    if(this.getCurrentPlayer().equals(this.getPlayers().get(0))) {
                        handleThrow();
                    }else{
                        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this.context);

                        dlgAlert.setMessage("Please wait for your turn");
                        dlgAlert.setTitle("Not your turn");
                        dlgAlert.setPositiveButton("OK", null);
                        dlgAlert.setCancelable(true);
                        dlgAlert.create().show();

                        dlgAlert.setPositiveButton("Ok",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                    }
                }
            }
        }

    }
    public abstract void handleThrow();
    public boolean isPlaying(){
        return this.playing;
    }
    public void setPlaying(boolean playing){
        this.playing=playing;
    }
    public abstract void playGame();

}
