package com.example.tsechacksapp.game;


import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.tsechacksapp.R;

public class GameMainActivity extends AppCompatActivity {

    String options[] = {"this is your mother","this is your grandmother","this is your father","this is your daughter","this is your grandfather","this is your son","this is your uncle","this is your friend"};
    ImageView imageView;
    Button btn1,btn2,btn3,btn4;
    int count =1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_main);
        btn1 = findViewById(R.id.oa);
        btn2 = findViewById(R.id.ob);
        btn3 = findViewById(R.id.oc);
        btn4 = findViewById(R.id.od);

        imageView = findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.woman1);

    }

    public void click(View view){
        if(count<=5){
            int rand1 = (int) (Math.random()*(7)+1);
            int rand2 = (int) (Math.random()*(7)+1);
            int rand3 = (int) (Math.random()*(7)+1);
            int rand4 = (int) (Math.random()*(7)+1);
            Log.e("tag","d"+rand1);
            switch (count)
            {
                case 1 : imageView.setImageResource(R.drawable.woman2);break;
                case 2 : imageView.setImageResource(R.drawable.woman3);break;
                case 3 : imageView.setImageResource(R.drawable.woman4);break;
                case 4 : imageView.setImageResource(R.drawable.woman5);break;
                case 5 : imageView.setImageResource(R.drawable.woman6);break;

            }
            if(view.getTag().toString().contentEquals("1"))
            {
                btn1.setText(options[rand1]);
                btn2.setText(options[rand2]);
                btn3.setText(options[rand3]);
                btn4.setText(options[rand4]);
            }
            else if(view.getTag().toString().contentEquals("2"))
            {
                btn1.setText(options[rand1]);
                btn2.setText(options[rand2]);
                btn3.setText(options[rand3]);
                btn4.setText(options[rand4]);

            }
            else if(view.getTag().toString().contentEquals("3"))
            {
                btn1.setText(options[rand1]);
                btn2.setText(options[rand2]);
                btn3.setText(options[rand3]);
                btn4.setText(options[rand4]);

            }
            else if(view.getTag().toString().contentEquals("4"))
            {
                btn1.setText(options[rand1]);
                btn2.setText(options[rand2]);
                btn3.setText(options[rand3]);
                btn4.setText(options[rand4]);

            }
            count++;
        }
        else {
            Intent intent = new Intent(this,SuccessActivity.class);
            startActivity(intent);
        }
    }
}