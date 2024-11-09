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
                image[i].setTag(i);
                image[i].setOnClickListener(imgHandler);
                image[i].setImageResource(R.drawable.lock01 + i);
            }
        }
    }

    View.OnClickListener imgHandler = v -> {
        Intent intent = new Intent(getApplicationContext(), ImageActivity.class);
        int img = Integer.parseInt(v.getTag().toString()) + 1;
        intent.putExtra("img", img);
        startActivity(intent);
        finish();
    };
}