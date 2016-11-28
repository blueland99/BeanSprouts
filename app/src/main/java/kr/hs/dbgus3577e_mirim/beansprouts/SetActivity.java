package kr.hs.dbgus3577e_mirim.beansprouts;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SetActivity extends Activity implements View.OnClickListener {

    ImageButton volumeUp, volumeDown, reset;
    AlertDialog.Builder builder;
    String dirPath;

    //MediaPlayer bgm;
    Intent sintent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        dirPath = getFilesDir().getAbsolutePath() + "/beanSprouts";

        volumeUp = (ImageButton)findViewById(R.id.volume_up);
        volumeDown = (ImageButton)findViewById(R.id.volume_down);
        reset = (ImageButton)findViewById(R.id.reset);

        volumeUp.setOnClickListener(this);
        volumeDown.setOnClickListener(this);
        reset.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.volume_up){
            stopService(new Intent(this, ServiceClass.class));
            startService(new Intent(this, ServiceClass.class));
            String text = "0";
            File savefile = new File(dirPath + "/BeanSproutsVolume.txt");
            try {
                FileWriter fos = new FileWriter(savefile);
                fos.write(text);
                fos.close();
            } catch (IOException e) {
                Toast.makeText(SetActivity.this, "안들어옴", Toast.LENGTH_SHORT).show();
            }
        }
        else if (v.getId() == R.id.volume_down){
            stopService(new Intent(this, ServiceClass.class));
            String text = "1";
            File savefile = new File(dirPath + "/BeanSproutsVolume.txt");
            try {
                FileWriter fos = new FileWriter(savefile);
                fos.write(text);
                fos.close();
            } catch (IOException e) {
                Toast.makeText(SetActivity.this, "안들어옴", Toast.LENGTH_SHORT).show();
            }
        }
        else if (v.getId() == R.id.reset){
            builder = new AlertDialog.Builder(SetActivity.this);
            //builder.setCancelable(false);
            builder.setMessage("정말 게임을 초기화 하시겠습니까?");
            builder.setTitle("초기화");
            builder.setPositiveButton("아니오", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.setNeutralButton("예", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String text = "1";
                    File savefile = new File(dirPath + "/BeanSproutsLevel.txt");
                    try {
                        FileWriter fos = new FileWriter(savefile);
                        fos.write(text);
                        fos.close();
                    } catch (IOException e) {}

                    savefile = new File(dirPath + "/BeanSproutsClear.txt");
                    text = "0";
                    try {
                        FileWriter fos = new FileWriter(savefile);
                        fos.write(text);
                        fos.close();
                    } catch (IOException e) {
                        Toast.makeText(SetActivity.this, "안들어옴", Toast.LENGTH_SHORT).show();
                    }


                    Toast.makeText(SetActivity.this, "게임 단계와 콜렉션이 초기화 되었습니다.", Toast.LENGTH_SHORT).show();
                }
            });
            builder.show();
        }
    }
}
