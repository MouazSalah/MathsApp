package com.example.simplemathsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ChooseOperation extends AppCompatActivity
{
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_operation);
    }

    public void chooseOperation (View v)
    {
        switch (v.getId())
        {
            case R.id.addition_textview:
                i = new Intent(this, GameLevel.class);
                i.putExtra("operation_type", 1);
                break;

            case R.id.subtraction_textview:
                i = new Intent(this, GameLevel.class);
                i.putExtra("operation_type", 2);
                break;

            case R.id.multiplication_textview:
                i = new Intent(this, GameLevel.class);
                i.putExtra("operation_type", 3);
                break;

        }

        startActivity(i);
    }

}
