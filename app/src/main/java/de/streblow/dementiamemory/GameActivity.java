package de.streblow.dementiamemory;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import de.streblow.seniormemory.R;

/**
 * Created by streblow on 13.04.2017.
 */

public class GameActivity extends AppCompatActivity {

    private String difficulty;
    private boolean card1Open;
    private boolean card2Open;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        difficulty = intent.getStringExtra("difficulty");
        initGame();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater findMenuItems = getMenuInflater();
        findMenuItems.inflate(R.menu.menu_game, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.again:
                initGame();
                invalidateOptionsMenu();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu (Menu menu) {
        if (card1Open && card2Open)
            menu.findItem(R.id.again).setEnabled(true);
        else
            menu.findItem(R.id.again).setEnabled(false);
        return true;
    }

    public void initGame() {
        if (difficulty.equalsIgnoreCase("easy")) {
            if (Math.floor(Math.random() * 2.0) == 0) {
                card1Open = true;
                card2Open = false;
            } else {
                card1Open = false;
                card2Open = true;
            }
            ImageView view = (ImageView)findViewById(R.id.card1);
            if (card1Open)
                view.setImageResource(R.drawable.card_front);
            else
                view.setImageResource(R.drawable.card_back);
            view = (ImageView)findViewById(R.id.card2);
            if (card2Open)
                view.setImageResource(R.drawable.card_front);
            else
                view.setImageResource(R.drawable.card_back);
        }
        if (difficulty.equalsIgnoreCase("difficult")) {
            card1Open = false;
            card2Open = false;
            ImageView view = (ImageView)findViewById(R.id.card1);
            view.setImageResource(R.drawable.card_back);
            view = (ImageView)findViewById(R.id.card2);
            view.setImageResource(R.drawable.card_back);
        }
        if (difficulty.equalsIgnoreCase("impossible")) {
            card1Open = false;
            card2Open = false;
            ImageView view = (ImageView)findViewById(R.id.card1);
            view.setImageResource(R.drawable.card_back);
            view = (ImageView)findViewById(R.id.card2);
            view.setImageResource(R.drawable.card_back);
        }
    }

    public void touchCard1(View v) {
        if (!card1Open) {
            card1Open = true;
            ImageView view = (ImageView)findViewById(R.id.card1);
            if (!card2Open)
                view.setImageResource(R.drawable.card_front);
            if (card2Open && difficulty.equalsIgnoreCase("impossible"))
                view.setImageResource(R.drawable.card_front_impossible);
            if (card2Open && !difficulty.equalsIgnoreCase("impossible"))
                view.setImageResource(R.drawable.card_front);
            if (card1Open && card2Open && !difficulty.equalsIgnoreCase("impossible"))
                gameSuccess();
            if (card1Open && card2Open && difficulty.equalsIgnoreCase("impossible"))
                gameFailed();
        }
    }

    public void touchCard2(View v) {
        if (!card2Open) {
            card2Open = true;
            ImageView view = (ImageView)findViewById(R.id.card2);
            if (!card1Open)
                view.setImageResource(R.drawable.card_front);
            if (card1Open && difficulty.equalsIgnoreCase("impossible"))
                view.setImageResource(R.drawable.card_front_impossible);
            if (card1Open && !difficulty.equalsIgnoreCase("impossible"))
                view.setImageResource(R.drawable.card_front);
            if (card1Open && card2Open && !difficulty.equalsIgnoreCase("impossible"))
                gameSuccess();
            if (card1Open && card2Open && difficulty.equalsIgnoreCase("impossible"))
                gameFailed();
        }
    }

    public void returnToMainActivity(View v) {
        this.finish();
    }

    public void gameSuccess() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        // set title
        alertDialogBuilder.setTitle(getResources().getString(R.string.app_success_title));
        // set dialog message
        alertDialogBuilder
                .setMessage(getResources().getString(R.string.app_success))
                .setCancelable(true)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
        invalidateOptionsMenu();
    }

    public void gameFailed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        // set title
        alertDialogBuilder.setTitle(getResources().getString(R.string.app_failed_title));
        // set dialog message
        alertDialogBuilder
                .setMessage(getResources().getString(R.string.app_failed))
                .setCancelable(true)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
        invalidateOptionsMenu();
    }

}
