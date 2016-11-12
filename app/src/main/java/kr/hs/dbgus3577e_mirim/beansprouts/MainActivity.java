package kr.hs.dbgus3577e_mirim.beansprouts;

import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;

    ImageButton imageButton;

    MediaPlayer bgm;

    String dirPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dirPath = getFilesDir().getAbsolutePath() + "/beanSprouts";
        bgm = MediaPlayer.create(this, R.raw.bgm);
        bgm.setLooping(true);
        bgm.start();

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
            public void onClick(View v) {String text = "1";
                File savefile = new File(dirPath + "/BeanSproutsLevel.txt");
                try {
                    FileWriter fos = new FileWriter(savefile);
                    fos.write(text);
                    fos.close();
                } catch (IOException e) {
                }
                Toast.makeText(MainActivity.this, "게임 단계와 콜렉션이 초기화 되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime)
        {
            bgm.pause();
            finish();
        }
        else
        {
            backPressedTime = tempTime;
            Toast.makeText(this, "한번 더 뒤로가기를 누르면 종료", Toast.LENGTH_SHORT).show();
        }
    }
}
