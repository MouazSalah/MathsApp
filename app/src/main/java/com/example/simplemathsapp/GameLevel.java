package com.example.simplemathsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GameLevel extends AppCompatActivity
{
    int level = 1;
    int operation = 1;

    Intent i ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_level);

        Intent mIntent = getIntent();
        operation = mIntent.getIntExtra("operation_type", 0);

    }

    public void ChooseLevel (View v)
    {
        switch (v.getId())
        {
            case R.id.level1_textview:
                level = 1;
                break;

            case R.id.level2_textview:
                level = 2;
                break;

            case R.id.level3_textview:
                level = 3;
                break;

            case R.id.level4_textview:
                level = 4;
                break;

            default:
                level = 1;
                break;
        }

        StartIntent();
    }

    private void StartIntent()
    {
        if (operation == 2)
        {
             i = new Intent(this, Subtraction.class);
        }
        else if (operation == 3)
        {
             i = new Intent(this, Multiplication.class);
        }
        else
        {
             i = new Intent(this, Addition.class);
        }

        i.putExtra("game_level", level);
        startActivity(i);
    }
}
