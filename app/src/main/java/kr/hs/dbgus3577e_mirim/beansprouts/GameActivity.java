package kr.hs.dbgus3577e_mirim.beansprouts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class GameActivity extends Activity {
    // ImageView 배열 생성
    int max = 5;
    ImageView step[] = new ImageView[max];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        step[1] = (ImageView) findViewById(R.id.step1);
        step[2] = (ImageView) findViewById(R.id.step2);
        step[3] = (ImageView) findViewById(R.id.step3);

        step[1].setImageResource(R.drawable.step1);
        step[2].setImageResource(R.drawable.step2);
        step[3].setImageResource(R.drawable.step3);

        /*        for (int i = 1; i < max; i++) {
            step[i] = (ImageView) findViewById(R.id.step + i);
        }
        for (int i = 1; i < max; i++) {
            step[i].setBackgroundResource(R.drawable.step + i);
        }*/
        step[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PlayActivity.class);
                startActivity(intent);
            }
        });
        step[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PlayActivity.class);
                startActivity(intent);
            }
        });
        step[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PlayActivity.class);
                startActivity(intent);
            }
        });
    }
}
