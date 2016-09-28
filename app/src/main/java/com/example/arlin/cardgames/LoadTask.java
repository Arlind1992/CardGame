package com.example.arlin.cardgames;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

/**
 * Created by Arlind on 05-Jun-16.
 */
public class LoadTask extends AsyncTask<Void, Integer, Void> {
    //A ProgressDialog object
    private ProgressDialog progressDialog;
    private GameController controller;
    private GameView view;
    private Activity activity;

    public LoadTask(ProgressDialog progressDialog,GameController controller,GameView view,Activity activity){
        this.activity=activity;
        this.controller=controller;
        this.progressDialog=progressDialog;
        this.view=view;
    }



    @Override
    protected Void doInBackground(Void... params) {
        controller.initialize();
        return null;
    }

    //Before running code in separate thread
    @Override
    protected void onPreExecute(){
        //Set the progress dialog to display a horizontal progress bar
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        //Set the dialog title to 'Loading...'
        progressDialog.setTitle("Loading...");
        //Set the dialog message to 'Loading application View, please wait...'
        progressDialog.setMessage("Loading application View, please wait...");
        //This dialog can't be canceled by pressing the back key
        progressDialog.setCancelable(false);
        //This dialog isn't indeterminate
        progressDialog.setIndeterminate(false);
        //The maximum number of items is 100
        progressDialog.setMax(100);
        //Set the current progress to zero
        progressDialog.setProgress(0);
        //Display the progress dialog
        progressDialog.show();

    }
    //Update the progress
    @Override
    protected void onProgressUpdate(Integer... values)
    {
        //set the current progress of the progress dialog
        progressDialog.setProgress(values[0]);
    }

    //after executing the code in the thread
    @Override
    protected void onPostExecute(Void result)
    {
        //close the progress dialog
        progressDialog.dismiss();
        //initialize the View
        activity.setContentView(view);

    }
}
