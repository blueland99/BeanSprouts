package kr.hs.dbgus3577e_mirim.beansprouts;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ImageActivity extends Activity {

    Typeface face;

    int max = 12;
    int img;
    ImageView imageView;
    TextView textView;

    String name[] = {"콩남울1", "콩남울2", "콩남울3", "콩남울4", "콩남울5", "콩남울6", "콩남울7", "콩남울8", "콩남울9", "콩남울10", "콩남울11", "콩남울12"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        img = intent.getIntExtra("img", 0);
        setContentView(R.layout.activity_image);

        imageView = (ImageView) findViewById(R.id.img);
        textView = (TextView) findViewById(R.id.img_text);

        face = Typeface.createFromAsset(getAssets(), "computersetak.ttf");
        textView.setTypeface(face);

        imageView.setImageResource(R.drawable.step01 + (img-1));
        textView.setText(name[img - 1]);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(getApplicationContext(), CollectActivity.class);
        startActivity(intent);
        finish();
    }
}
