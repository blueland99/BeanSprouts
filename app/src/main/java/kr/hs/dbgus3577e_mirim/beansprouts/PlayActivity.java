package kr.hs.dbgus3577e_mirim.beansprouts;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class PlayActivity extends Activity {
    int levelMax = 9;
    int level;
    int beanHead;
    int score = 0;

    int move_X, move_Y;
    int width, height;
    final int beanX = 160;
    final int beanY = (int)(beanX * 2.5);
    int b = 10;
    ArrayList<ImageView> img;
    ArrayList<Integer> arrX, arrY;

    TextView Tscore;
    String dirPath;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        level = intent.getIntExtra("level", 0);
        setContentView(R.layout.activity_play);
        dirPath = getFilesDir().getAbsolutePath() + "/beanSprouts";

        Tscore = (TextView)findViewById(R.id.tscore);
        Tscore.setText("계산 결과 : "+score);
        beanHead = level * 1;

        DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics();

        width = dm.widthPixels - beanX;
        height = dm.heightPixels - beanY;

        Random Ran_move = new Random();

        FrameLayout F = (FrameLayout)findViewById(R.id.frameLayout);
        AbsoluteLayout A1 = new AbsoluteLayout(this);

        img = new ArrayList<ImageView>();
        // --------------------- FrameLayout, AbsoluteLayout ---------------------------------

        arrX = new ArrayList<Integer>();
        arrY = new ArrayList<Integer>();

        for (int i=0; i<beanHead; i++) {
            arrX.add(Ran_move.nextInt(width) + 1);
            arrY.add(Ran_move.nextInt(width) + 1);
        }
        for (int i=0; i<beanHead; i++) {
            ImageView num = new ImageView(this);
            num.setBackgroundResource(R.drawable.bean1);
            A1.addView(num, new AbsoluteLayout.LayoutParams(beanX, beanY, arrX.get(i), arrY.get(i)));  //(가로,세로, x좌표, y좌표)
            if (arrX.get(i) < (width-(beanX/2)) && arrX.get(i) > (beanX/2)) {
                A1.removeView(num);
                A1.addView(num, new AbsoluteLayout.LayoutParams(beanX, beanY, arrX.get(i), arrY.get(i)));  //(가로,세로, x좌표, y좌표)
                if (arrY.get(i) < (height-beanY) && arrY.get(i) > (beanY/2)) {
                    A1.removeView(num);
                    A1.addView(num, new AbsoluteLayout.LayoutParams(beanX, beanY, arrX.get(i), arrY.get(i)));  //(가로,세로, x좌표, y좌표)
                }
            }
            num.setId(i);
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
            int id = v.getId();
            img.get(id).setVisibility(View.GONE);
            score += 100;
           // Toast.makeText(PlayActivity.this, v.getId()+", "+(score/10), Toast.LENGTH_SHORT).show();
            if (score == beanHead*100) {
                Toast.makeText(PlayActivity.this, "CLEAR", Toast.LENGTH_SHORT).show();

                // img.size();
                Tscore.setText("계산 결과 : "+score);
/*                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                if (level != levelMax) {
                    level++;
                }

                GameActivity ga = new GameActivity();
                String text = String.valueOf(level);
                File savefile = new File(dirPath + "/BeanSproutsLevel.txt");
                try {
                    FileWriter fos = new FileWriter(savefile);
                    fos.write(text);
                    fos.close();
                } catch (IOException e) {
                    Toast.makeText(PlayActivity.this, "안들어옴", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(intent);
            }
            //Toast.makeText(PlayActivity.this, "클릭됨, score : "+score, Toast.LENGTH_SHORT).show();

        }
    };
}