package com.example.simplemathsapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class Subtraction extends AppCompatActivity
{

    LinearLayout linearLayout;

    TextView timeTextView,
            scoreTextView,
            operationTextView,
            resultTextView;

    Button goButton,
            button0,
            button1,
            button2,
            button3;

    int levelOfGame = 2,
            time = 100000,
            score = 0,
            numberOfQuestion = 0,
            maxOfQuestions = 10,
            firstNumber,
            secondNumber,
            sum,
            coreectAnswerLocation;


    ArrayList<Integer> answers = new ArrayList<Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent mIntent = getIntent();
        levelOfGame = mIntent.getIntExtra("game_level", 0);


        linearLayout = (LinearLayout) findViewById(R.id.design_layout);
        linearLayout.setVisibility(View.INVISIBLE);

        timeTextView = (TextView) findViewById(R.id.time_textview);
        timeTextView.setText("20");

        scoreTextView = (TextView) findViewById(R.id.score_textview);
        scoreTextView.setText("0/0");


        resultTextView = (TextView) findViewById(R.id.result_textview);
        resultTextView.setText("");

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        operationTextView = (TextView) findViewById(R.id.operation_textview);



        goButton = (Button) findViewById(R.id.go_button);
        goButton.setVisibility(View.VISIBLE);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                goButton.setVisibility(View.INVISIBLE);
                linearLayout.setVisibility(View.VISIBLE);
                clear();
                GenerateNumbers();
                CountTime();
            }
        });

    }


    public void checkLevelGame()
    {
        Random random = new Random();

        if (levelOfGame == 1 )
        {
            firstNumber = random.nextInt(6);
            secondNumber = random.nextInt(6);
        }
        else if (levelOfGame == 2 )
        {
            firstNumber = random.nextInt(11);
            secondNumber = random.nextInt(11);
        }
        else if (levelOfGame == 3 )
        {
            firstNumber = random.nextInt(21);
            secondNumber = random.nextInt(21);
        }
        else if (levelOfGame == 4 )
        {
            firstNumber = random.nextInt(101);
            secondNumber = random.nextInt(101);
        }
        else
        {
            firstNumber = random.nextInt(1001);
            secondNumber = random.nextInt(1001);
        }

    }




    public void GenerateNumbers()
    {
        if (numberOfQuestion >= maxOfQuestions)
        {
            showAlert();
            return;
        }

        Random random = new Random();

        if (levelOfGame == 1 )
        {
            firstNumber = random.nextInt(6);
            secondNumber = random.nextInt(6);
        }
        else if (levelOfGame == 2 )
        {
            firstNumber = random.nextInt(11);
            secondNumber = random.nextInt(11);
        }
        else if (levelOfGame == 3 )
        {
            firstNumber = random.nextInt(21);
            secondNumber = random.nextInt(21);
        }
        else if (levelOfGame == 4 )
        {
            firstNumber = random.nextInt(101);
            secondNumber = random.nextInt(101);
        }
        else
        {
            firstNumber = random.nextInt(1001);
            secondNumber = random.nextInt(1001);
        }

        sum = firstNumber +secondNumber;


        coreectAnswerLocation = random.nextInt(4);

        for (int i = 0; i < 4; i++)
        {
            if (coreectAnswerLocation == i)
            {
                answers.add(sum);
            }
            else
            {
                int inCorrectAnswer = random.nextInt(50);
                if (sum == inCorrectAnswer)
                {
                    answers.add(random.nextInt(50));
                }
                else
                {
                    answers.add(inCorrectAnswer);
                }
            }
        }


        if ( firstNumber > secondNumber)
        {
            operationTextView.setText(firstNumber + " - " + secondNumber);
        }
        else
        {
            operationTextView.setText(secondNumber + " - " + firstNumber);
        }



        button0.setText(String.valueOf(answers.get(0)));
        button1.setText(String.valueOf(answers.get(1)));
        button2.setText(String.valueOf(answers.get(2)));
        button3.setText(String.valueOf(answers.get(3)));


    }

    public void clear()
    {
        answers.clear();

        numberOfQuestion = 0;

        timeTextView.setText("30");
        scoreTextView.setText("0/0");
        button0.setText("");
        button1.setText("");
        button2.setText("");
        button3.setText("");

        resultTextView.setText("");
        score = 0;
        numberOfQuestion = 0;
        firstNumber = secondNumber = sum = 0;
        coreectAnswerLocation = 0;

    }


    public void CheckAnswer(View v)
    {
        Button button = (Button) v ;
        int answer  = Integer.parseInt(button.getText().toString());

        if (answer == sum)
        {
            score++;
            resultTextView.setText("الاجابة صحيحة");
        }
        else
        {
            resultTextView.setText( "الاجابة خاطئة" + "\n" +
                    firstNumber + " - " + secondNumber + " = " + sum);
            // resultTextView.setText( "الاجابة خاطئة الناتج الصحيح = "  + sum);
        }

        numberOfQuestion++ ;
        answers.clear();
        GenerateNumbers();

        scoreTextView.setText(score + "/" + numberOfQuestion);

    }


    public void CountTime()
    {
        new CountDownTimer(time, 1000)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {
                timeTextView.setText("" +  String.valueOf(millisUntilFinished /1000));
            }

            @Override
            public void onFinish()
            {

                showAlert();
            }

        }.start();
    }

    private void showAlert()
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("لقد احرزت " + score + " نقاط من " + maxOfQuestions)
                .setCancelable(false);
        alert.setNegativeButton("اختار لعبة اخري", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Intent intent = new Intent(getApplicationContext(), ChooseOperation.class);
                startActivity(intent);
            }
        });

        alert.setPositiveButton("العب مرة اخري", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                clear();
                GenerateNumbers();
                CountTime();
            }
        });

        alert.show();
    }



}



