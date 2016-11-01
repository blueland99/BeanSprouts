package kr.hs.dbgus3577e_mirim.beansprouts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class PlayActivity extends Activity {
    int level;
    int beanHead;

    int move_X, move_Y;
    int width, height;
    final int beanX = 160;
    final int beanY = (int)(beanX * 2.5);
    int b = 10;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        level = intent.getIntExtra("level", 0);
        setContentView(R.layout.activity_play);

        beanHead = level * 5;

        DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics();

        width = dm.widthPixels - beanX;
        height = dm.heightPixels - beanY;

        Random Ran_move = new Random();
        move_X = Ran_move.nextInt(width) + 1;
        move_Y = Ran_move.nextInt(height) + 1;

        Random ranX1 = new Random();
        Random ranY1 = new Random();
        Random ranX2 = new Random();
        Random ranY2 = new Random();
        setContentView(R.layout.activity_play);


        FrameLayout F = (FrameLayout)findViewById(R.id.frameLayout);
        AbsoluteLayout A1 = new AbsoluteLayout(this);

        ArrayList<ImageView> img = new ArrayList<ImageView>();
        // --------------------- FrameLayout, AbsoluteLayout ---------------------------------

        for (int i=0; i<beanHead; i++) {
            ImageView num = new ImageView(this);
            num.setBackgroundResource(R.drawable.bean1);
            A1.addView(num, new AbsoluteLayout.LayoutParams(beanX, beanY, ranX1.nextInt(move_X), ranY1.nextInt(move_Y)));  //(가로,세로, x좌표, y좌표)
            if (ranX1.nextInt(move_X) < (width-beanX) && ranX1.nextInt(move_X) > (beanX/2)) {
                A1.removeView(num);
                A1.addView(num, new AbsoluteLayout.LayoutParams(beanX, beanY, ranX1.nextInt(move_X), ranY1.nextInt(move_Y)));  //(가로,세로, x좌표, y좌표)
                if (ranY1.nextInt(move_Y) < (height-beanY) && ranY1.nextInt(move_Y) > (beanY/2)) {
                    A1.removeView(num);
                    A1.addView(num, new AbsoluteLayout.LayoutParams(beanX, beanY, ranX1.nextInt(move_X), ranY1.nextInt(move_Y)));  //(가로,세로, x좌표, y좌표)
                }
            }
            img.add(num);
        }
        F.addView(A1);

        for (int i=0; i<beanHead; i++){
            img.get(i).setOnClickListener(imgVHandler);
        }
    }

    View.OnClickListener imgVHandler=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(PlayActivity.this, "클릭됨", Toast.LENGTH_SHORT).show();
        }
    };
}