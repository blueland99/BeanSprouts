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
import android.widget.ViewFlipper;

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

    TextView text1, text2,text3, text4;
    String dirPath;
    AlertDialog.Builder builder;

    ImageView clearImg;
    TextView clearText, clearStory;
    LayoutInflater inflater;
    View layout;
    Context mContext = null;
    Intent intent;

    ViewFlipper flipper;

    String name[] = {"콩남울", "콩짜이오", "콩 사와코", "콩순이", "콩알로아", "콩성훈", "파티콩", "콩미림", "쵸파콩", "요리콩", "하이콩", "연아콩"};
//    String story[] = { "고시공부를 하면서 처음으로 노량진을 벗어나 일탈이란 것을 처음 해본 콩남울씨, \"정말 짜릿한 기분인걸요~?\"",
//            "먹을것을 너무 좋아하여 세계룰 돌던 콩짜이오, 이번에는 한국에 와서 맛있는 음식들을 찾아 헤맨다. \"김 최고예요!\"",
//            "케이팝 가수를 좋아하여 콘서트를 보러온 콩 사와코, 오늘도 어김없이 사와코상은 케이팝 가수들 콘서트를 보러 어김없이 콘서트장을 찾아왔다. \"케이팝 사랑해요! I Love K-Pop!\"",
//            "한복을 너무 좋아하는 콩순이, 한복을 너무 좋아한 나머지 파티장에 한복을 전파하기위해 파티에 왔다. \"안녕하세요, 우리 선조들이 만든 이 아름다운 한복에 빠져보세요\"",
//            "알로아오예 노래를 항상 부르고 다니며 하와이에 대해 홍보로를 하고 있는 콩알로아. \"알로아오예~ 알로아오예~ 하와이 가보셨나요? \n저와 함께 하와이로 가시죠!\"",
//            "파티장 옆 레스토랑에서 사랑이와 밥먹다가 음악소리에 신난 사랑이가 사라져서 찾기위해 파티에 온 콩성훈, \"우리 사랑쨩 보셔써요? 사랑이찾다 여기까지 왔슴미다.\"",
//            "콩남울의 친구, 평소에도 김치국을 잘 마셔 이번에도 파티의 주인공인줄 알고 혼자 파티복을 입고온 파티콩",
//            "관악구에서 학교를 다니고있는 콩미림, \"152버스에서 광고를 보고 파티에 오게 되었다. 안녕하십니까!\"",
//            "신세계에서 밀짚모자 해적단에서 의사로 있던 쵸파, 사실 짱구 덕후였다. 액션가면을 만나기위해 파티장에 온 쵸파콩",
//            "콩나물계의 최고 요리사인 요리콩, \"오늘 밤 당신을 최고의 디너쇼에 초대합니다.\"",
//            "한국 고교 대표 미림 배구팀과 결승을 하러 온 하이콩, 세트 스코어 1:1인 상황. \"우린 한세트 더 게임 할 수 있어\"",
//            "어렸을 때 김연아 선수를 보고 피겨를 시작한 연아콩, 그리하여 기록적인 점수를 받고 수상소감을 하는데.. \"이 영광을 제 사랑하는 부모님과 존경하는 연아선배님께 바칩니다.\"",
//    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();
        level = intent.getIntExtra("level", 0);
        setContentView(R.layout.activity_play);

        text1 = (TextView)findViewById(R.id.text1);
        text2 = (TextView)findViewById(R.id.text2);
        text3 = (TextView)findViewById(R.id.text3);
        text4 = (TextView)findViewById(R.id.text4);
        face = Typeface.createFromAsset(getAssets(), "BMHANNA.ttf");
        text1.setTypeface(face);
        text2.setTypeface(face);
        text3.setTypeface(face);
        text4.setTypeface(face);


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

        flipper = (ViewFlipper)findViewById(R.id.view_flipper);
        flipper.setFlipInterval(1500);
        flipper.startFlipping();


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


            tempLevel = level - 1;
            if (score == beanHead*100) {
                arrY.clear();
                arrX.clear();
                img.clear();
                if (level != (max + 2)) {
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
                    }
                }
                flipper.stopFlipping();

                if (level == max+1) {
                    // txt 파일 생성
                    String text = "1";
                    File savefile = new File(dirPath + "/BeanSproutsClear.txt");
                    try {
                        FileWriter fos = new FileWriter(savefile);
                        fos.write(text);
                        fos.close();
                    } catch (IOException e) {
                    }
                }






                setContentView(R.layout.activity_clear);
                clearImg = (ImageView)findViewById(R.id.clear_img);
                clearImg.setImageResource(R.drawable.step01+tempLevel);
                clearText = (TextView)findViewById(R.id.clear_text);
                clearText.setText( name[tempLevel] + "\n캐릭터 획득!");
                clearText.setTypeface(face);
//                clearStory = (TextView)findViewById(R.id.clear_story);
//                clearStory.setText(story[tempLevel]);
//                clearStory.setTypeface(face);




                mContext = getApplicationContext();
                inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                layout = inflater.inflate(R.layout.activity_clear, (ViewGroup) findViewById(R.id.clear_activity));

                setContentView(R.layout.activity_play);
                text1 = (TextView)findViewById(R.id.text1);
                text2 = (TextView)findViewById(R.id.text2);
                text3 = (TextView)findViewById(R.id.text3);;
                text4 = (TextView)findViewById(R.id.text4);
                face = Typeface.createFromAsset(getAssets(), "BMHANNA.ttf");
                text1.setTypeface(face);
                text2.setTypeface(face);
                text3.setTypeface(face);
                text4.setTypeface(face);

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
    public void onBackPressed() {
        intent = new Intent(getApplicationContext(), GameActivity.class);
        startActivity(intent);
        finish();
    }
}