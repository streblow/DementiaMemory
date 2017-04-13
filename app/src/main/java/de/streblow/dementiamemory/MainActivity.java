package de.streblow.dementiamemory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import de.streblow.seniormemory.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Easy(View v) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("difficulty", "easy");
        this.startActivity(intent);
    }

    public void Difficult(View v) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("difficulty", "difficult");
        this.startActivity(intent);
    }

    public void Impossible(View v) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("difficulty", "impossible");
        this.startActivity(intent);
    }

}
