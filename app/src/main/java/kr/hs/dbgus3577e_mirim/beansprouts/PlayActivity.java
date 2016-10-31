package kr.hs.dbgus3577e_mirim.beansprouts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Toast;

public class PlayActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        int level=intent.getIntExtra("level",1);

        Toast.makeText(this, "Lv."+level, Toast.LENGTH_SHORT).show();

        setContentView(R.layout.activity_play);
    }
}
