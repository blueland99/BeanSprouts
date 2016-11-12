package kr.hs.dbgus3577e_mirim.beansprouts;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class PlayActivity extends Activity {

    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;

    Typeface face;

    int max = 12;
    int levelMax;
    int level;
    int beanHead;
    int score = 0;
    int tempLevel;
    int width, height;
    final int beanX = 160;
    final int beanY = (int)(beanX * 2.5);

    ArrayList<ImageView> img;
    ArrayList<Integer> arrX, arrY;

    TextView Tscore, textScore;
    String dirPath;
    AlertDialog.Builder builder;

    ImageView clear;
    LayoutInflater inflater;
    View layout;
    Context mContext = null;
    Intent intent;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();
        level = intent.getIntExtra("level", 0);
        setContentView(R.layout.activity_play);

        textScore = (TextView)findViewById(R.id.score);
        Tscore = (TextView)findViewById(R.id.tscore);
        face = Typeface.createFromAsset(getAssets(), "ENGDOSBI.ttf");
        textScore.setTypeface(face);
        Tscore.setTypeface(face);


        dirPath = getFilesDir().getAbsolutePath() + "/beanSprouts";

        // ------  파일 읽어오기
        String levelStr = "";
        String name = "BeanSproutsLevel.txt";
        String loadPath = dirPath + "/" + name;
        Log.v(null, "fileName : " + name);
        try {
            FileReader fr = new FileReader(loadPath);
            BufferedReader br = new BufferedReader(fr);
            String temp = "";
            while ((temp = br.readLine()) != null) {
                levelStr = levelStr + temp;
            }
            Log.v(null, "" + levelStr);
        } catch (Exception e) {
            Toast.makeText(PlayActivity.this, "Exception error : PlayActivity.java", Toast.LENGTH_SHORT).show();
        }
        levelMax = Integer.parseInt(levelStr);
        // ------


        beanHead = level * 20;

        DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics();

        width = dm.widthPixels - beanX;
        height = dm.heightPixels - (beanY + (beanY/2));

        Random Ran_move = new Random();

        FrameLayout F = (FrameLayout)findViewById(R.id.frameLayout);
        AbsoluteLayout A1 = new AbsoluteLayout(this);

        img = new ArrayList<ImageView>();
        // --------------------- FrameLayout, AbsoluteLayout ---------------------------------

        arrX = new ArrayList<Integer>();
        arrY = new ArrayList<Integer>();

        for (int i=0; i<beanHead; i++) {
            arrX.add(Ran_move.nextInt(width) + 1);
            arrY.add(Ran_move.nextInt(height) + 1);
        }
        for (int i=0; i<beanHead; i++) {
            ImageView num = new ImageView(this);
            num.setBackgroundResource(R.drawable.bean1);
            A1.addView(num, new AbsoluteLayout.LayoutParams(beanX, beanY, arrX.get(i), arrY.get(i)));  //(가로,세로, x좌표, y좌표)
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
            Tscore.setText(score+"");


            tempLevel = level - 1;
            if (score == beanHead*100) {
                if (level != (max + 1)) {
                    level++;
                }
                if (level > levelMax) {
                    String text = String.valueOf(level);
                    File savefile = new File(dirPath + "/BeanSproutsLevel.txt");
                    try {
                        FileWriter fos = new FileWriter(savefile);
                        fos.write(text);
                        fos.close();
                    } catch (IOException e) {
                        Toast.makeText(PlayActivity.this, "안들어옴", Toast.LENGTH_SHORT).show();
                    }
                }
                setContentView(R.layout.activity_clear);
                clear = (ImageView)findViewById(R.id.clear_img);
                clear.setImageResource(R.drawable.step01+tempLevel);


                mContext = getApplicationContext();
                inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                layout = inflater.inflate(R.layout.activity_clear, (ViewGroup) findViewById(R.id.clear_activity));

                setContentView(R.layout.activity_play);
                textScore = (TextView)findViewById(R.id.score);
                Tscore = (TextView)findViewById(R.id.tscore);
                face = Typeface.createFromAsset(getAssets(), "ENGDOSBI.ttf");
                textScore.setTypeface(face);
                Tscore.setTypeface(face);
                Tscore.setText(score+"");

                builder = new AlertDialog.Builder(PlayActivity.this);
                builder.setCancelable(false);
                builder.setView(layout);
                builder.setTitle("Game Clear");
                builder.setPositiveButton("MAIN", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        intent = new Intent(getApplicationContext(), GameActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.show();
            }
        }
    };
}