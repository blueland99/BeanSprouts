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
    int levelMax = 9;

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
        Toast.makeText(this, loadPath, Toast.LENGTH_SHORT).show();
        try {
            FileReader fr = new FileReader(loadPath);
            BufferedReader br = new BufferedReader(fr);
            String temp = "";
            while ((temp = br.readLine()) != null) {
                levelStr = levelStr + temp;
            }
            Toast.makeText(this, levelStr, Toast.LENGTH_SHORT).show();
            Log.v(null, "" + levelStr);
        } catch (Exception e) {
            Toast.makeText(this, "안들어옴", Toast.LENGTH_SHORT).show();
        }

        int level = Integer.parseInt(levelStr);

        ImageView step[] = new ImageView[level];
        for (int i = 0; i < level; i++) {
            step[i] = (ImageView) findViewById(R.id.step1 + i);
            step[i].setOnClickListener(imgVHandler);
        }
        for (int i = 0; i < level; i++) {
            step[i].setImageResource(R.drawable.step1 + i);
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
