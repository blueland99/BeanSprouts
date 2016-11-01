package kr.hs.dbgus3577e_mirim.beansprouts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class GameActivity extends Activity {
    // ImageView 배열 생성
    int level = 5;
    ImageView step[] = new ImageView[level];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        for (int i = 0; i < level; i++) {
            step[i] = (ImageView) findViewById(R.id.step1 + i);
            step[i].setOnClickListener(imgVHandler);
        }
        for (int i = 0; i < level; i++) {
            step[i].setImageResource(R.drawable.step1+i);
        }

    }

    View.OnClickListener imgVHandler=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), PlayActivity.class);
            int level=0;
            switch (v.getId()){
                case R.id.step1:
                    level = 1;
                    break;
                case R.id.step2:
                    level = 2;
                    break;
                case R.id.step3:
                    level = 3;
                    break;
                case R.id.step4:
                    level = 4;
                    break;
                case R.id.step5:
                    level = 5;
                    break;
                case R.id.step6:
                    level = 6;
                    break;
                case R.id.step7:
                    level = 7;
                    break;
                case R.id.step8:
                    level = 8;
                    break;
                case R.id.step9:
                    level = 9;
                    break;
            }
            intent.putExtra("level",level);
            startActivity(intent);
        }
    };
}
