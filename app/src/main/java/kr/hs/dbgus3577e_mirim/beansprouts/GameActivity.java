package kr.hs.dbgus3577e_mirim.beansprouts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GameActivity extends Activity {
    // ImageView 배열 생성
    //  level은 올렸다 내려오기

    String dirPath;
    int max = 12;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        // ------------------------------------------------------
        // Create new Directory
        dirPath = getFilesDir().getAbsolutePath() + "/beanSprouts";

        File file = new File(dirPath);
        if (!file.exists()) { // 디렉토리가 없으면
            file.mkdirs();
            Toast.makeText(this, "새로운 디렉토리", Toast.LENGTH_SHORT).show();
        }
        if (file.listFiles().length <= 1) {
            // txt 파일 생성
            String text = "1";
            File savefile = new File(dirPath + "/BeanSproutsLevel.txt");
            try {
                FileWriter fos = new FileWriter(savefile);
                fos.write(text);
                fos.close();
            } catch (IOException e) {
            }
        }


        // ------------------------------------------------------
        // 파일 읽어오기
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

        //super.onResume();
        int level = Integer.parseInt(levelStr);
        if (level == (max + 1)) {
            level--;
        }

        ImageView step[] = new ImageView[level];
        for (int i = 0; i < level; i++) {
            if (level != (max + 1)) {
                step[i] = (ImageView) findViewById(R.id.step01 + i);
                step[i].setTag(i);
                step[i].setOnClickListener(imgVHandler);
                step[i].setImageResource(R.drawable.lock01 + i);
            }
        }


        String clearTrue = "";
        name = "BeanSproutsClear.txt";
        loadPath = dirPath + "/" + name;
        Log.v(null, "fileName : " + name);
        try {
            FileReader fr = new FileReader(loadPath);
            BufferedReader br = new BufferedReader(fr);
            String temp = "";
            while ((temp = br.readLine()) != null) {
                clearTrue = clearTrue + temp;
            }
            Log.v(null, "" + clearTrue);
        } catch (Exception e) {
        }

        step[level - 1].setImageResource(R.drawable.lock2);
        if (clearTrue.equals("1")) {
            step[max - 1] = (ImageView) findViewById(R.id.step12);
            step[max - 1].setImageResource(R.drawable.lock12);
        }


        // step[level-1].setImageResource(R.drawable.lock2);


    }

    View.OnClickListener imgVHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), PlayActivity.class);
            int level = Integer.parseInt(v.getTag().toString()) + 1;
            intent.putExtra("level", level);
            startActivity(intent);
            finish();
        }
    };
}
