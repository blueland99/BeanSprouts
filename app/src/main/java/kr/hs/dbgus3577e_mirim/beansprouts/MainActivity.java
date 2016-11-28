package kr.hs.dbgus3577e_mirim.beansprouts;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;

    ImageButton imageButton;

    //MediaPlayer bgm;

    String dirPath;


    ViewFlipper flipper, pa, ti;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dirPath = getFilesDir().getAbsolutePath() + "/beanSprouts";

        flipper = (ViewFlipper)findViewById(R.id.main_flipper);
        flipper.setFlipInterval(500);
        flipper.startFlipping();

        pa = (ViewFlipper)findViewById(R.id.pa_flipper);
        pa.setFlipInterval(500);
        pa.startFlipping();

        ti = (ViewFlipper)findViewById(R.id.ti_flipper);
        ti.setFlipInterval(500);
        ti.startFlipping();
        // -----
        File file = new File(dirPath);
        if (!file.exists()) { // 디렉토리가 없으면
            file.mkdirs();
            //Toast.makeText(this, "새로운 디렉토리", Toast.LENGTH_SHORT).show();
        }
        if (file.listFiles().length <= 0) {
            // txt 파일 생성
            String text = "0";
            File savefile = new File(dirPath + "/BeanSproutsVolume.txt");
            try {
                FileWriter fos = new FileWriter(savefile);
                fos.write(text);
                fos.close();
                //Toast.makeText(this, "txt 파일 생성", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
            }
        }


        // ------------------------------------------------------
        // 파일 읽어오기
        String volume = "";
        String name = "BeanSproutsVolume.txt";
        String loadPath = dirPath + "/" + name;
        Log.v(null, "fileName : " + name);
        try {
            FileReader fr = new FileReader(loadPath);
            BufferedReader br = new BufferedReader(fr);
            String temp = "";
            while ((temp = br.readLine()) != null) {
                volume = volume + temp;
            }
            Log.v(null, "" + volume);
        } catch (Exception e) {
            Toast.makeText(this, "안들어옴", Toast.LENGTH_SHORT).show();
        }

        // -----

//        bgm = MediaPlayer.create(this, R.raw.bgm);
//        bgm.setLooping(true);
        if (volume.equals("0")){
            startService(new Intent(this, ServiceClass.class));
        }

        imageButton = (ImageButton)findViewById(R.id.game_start);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(intent);
            }
        });
        imageButton = (ImageButton)findViewById(R.id.game_collect);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CollectActivity.class);
                startActivity(intent);
            }
        });
        imageButton = (ImageButton)findViewById(R.id.game_reset);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), SetActivity.class);
                startActivity(intent);
            }
        });
    }



    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime)
        {
            stopService(new Intent(this, ServiceClass.class));
            finish();
        }
        else
        {
            backPressedTime = tempTime;
            Toast.makeText(this, "한번 더 뒤로가기를 누르면 종료", Toast.LENGTH_SHORT).show();
        }
    }
}
