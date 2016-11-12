package kr.hs.dbgus3577e_mirim.beansprouts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.*;

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
        if (file.listFiles().length <= 0) {
            // txt 파일 생성
            String text = "1";
            File savefile = new File(dirPath + "/BeanSproutsLevel.txt");
            try {
                FileWriter fos = new FileWriter(savefile);
                fos.write(text);
                fos.close();
                Toast.makeText(this, "txt 파일 생성", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(this, "안들어옴", Toast.LENGTH_SHORT).show();
        }

        //super.onResume();
        int level = Integer.parseInt(levelStr);
        if (level == (max+1)){
            level--;
        }

        ImageView step[] = new ImageView[level];
        for (int i = 0; i < level; i++) {
            if (level != (max+1)) {
                step[i] = (ImageView) findViewById(R.id.step01 + i);
                step[i].setOnClickListener(imgVHandler);
            }
        }
        for (int i = 0; i < level; i++) {
            if (level != (max+1)) {
                step[i].setImageResource(R.drawable.heart);
            }
        }
    }

    View.OnClickListener imgVHandler=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), PlayActivity.class);
            int level=0;
            switch (v.getId()){
                case R.id.step01:
                    level = 1;
                    break;
                case R.id.step02:
                    level = 2;
                    break;
                case R.id.step03:
                    level = 3;
                    break;
                case R.id.step04:
                    level = 4;
                    break;
                case R.id.step05:
                    level = 5;
                    break;
                case R.id.step06:
                    level = 6;
                    break;
                case R.id.step07:
                    level = 7;
                    break;
                case R.id.step08:
                    level = 8;
                    break;
                case R.id.step09:
                    level = 9;
                    break;
                case R.id.step10:
                    level = 10;
                    break;
                case R.id.step11:
                    level = 11;
                    break;
                case R.id.step12:
                    level = 12;
                    break;
            }
            intent.putExtra("level",level);
            startActivity(intent);
            finish();
        }
    };
}
