package kr.hs.dbgus3577e_mirim.beansprouts;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileReader;

public class CollectActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);

        String imgStr = "";
        String dirPath = getFilesDir().getAbsolutePath() + "/beanSprouts";
        String name = "BeanSproutsLevel.txt";
        String loadPath = dirPath + "/" + name;
        Log.v(null, "fileName : " + name);
        try {
            FileReader fr = new FileReader(loadPath);
            BufferedReader br = new BufferedReader(fr);
            String temp = "";
            while ((temp = br.readLine()) != null) {
                imgStr = imgStr + temp;
            }
            Log.v(null, "" + imgStr);
        } catch (Exception e) {
            Toast.makeText(this, "안들어옴", Toast.LENGTH_SHORT).show();
        }


        int img = Integer.parseInt(imgStr);

        ImageView image[] = new ImageView[img];
        if (img > 1) {
            for (int i = 0; i < img - 1; i++) {
                image[i] = (ImageView) findViewById(R.id.image01 + i);
                image[i].setOnClickListener(imgHandler);
                image[i].setImageResource(R.drawable.lock01 + i);
            }
        }
    }

    View.OnClickListener imgHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), ImageActivity.class);
            int img = 0;
            switch (v.getId()) {
                case R.id.image01:
                    img = 1;
                    break;
                case R.id.image02:
                    img = 2;
                    break;
                case R.id.image03:
                    img = 3;
                    break;
                case R.id.image04:
                    img = 4;
                    break;
                case R.id.image05:
                    img = 5;
                    break;
                case R.id.image06:
                    img = 6;
                    break;
                case R.id.image07:
                    img = 7;
                    break;
                case R.id.image08:
                    img = 8;
                    break;
                case R.id.image09:
                    img = 9;
                    break;
                case R.id.image10:
                    img = 10;
                    break;
                case R.id.image11:
                    img = 11;
                    break;
                case R.id.image12:
                    img = 12;
                    break;
            }
            intent.putExtra("img", img);
            startActivity(intent);
            finish();
        }
    };
}