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
    TextView textView, storyView;

    String name[] = {"콩남울", "콩짜이오", "콩 사와코", "콩순이", "콩알로아", "콩성훈", "파티콩", "콩미림", "쵸파콩", "요리콩", "하이콩", "연아콩"};
    String story[] = { "고시공부를 하면서 처음으로 노량진을 벗어나 일탈이란 것을 처음 해본 콩남울씨, \"정말 짜릿한 기분인걸요~?\"",
            "먹을것을 너무 좋아하여 세계룰 돌던 콩짜이오, 이번에는 한국에 와서 맛있는 음식들을 찾아 헤맨다. \"김 최고예요!\"",
            "케이팝 가수를 좋아하여 콘서트를 보러온 콩 사와코, 오늘도 어김없이 사와코상은 케이팝 가수들 콘서트를 보러 어김없이 콘서트장을 찾아왔다. \"케이팝 사랑해요! I Love K-Pop!\"",
            "한복을 너무 좋아하는 콩순이, 한복을 너무 좋아한 나머지 파티장에 한복을 전파하기위해 파티에 왔다. \"안녕하세요, 우리 선조들이 만든 이 아름다운 한복에 빠져보세요\"",
            "알로아오예 노래를 항상 부르고 다니며 하와이에 대해 홍보로를 하고 있는 콩알로아. \"알로아오예~ 알로아오예~ 하와이 가보셨나요? \n저와 함께 하와이로 가시죠!\"",
            "파티장 옆 레스토랑에서 사랑이와 밥먹다가 음악소리에 신난 사랑이가 사라져서 찾기위해 파티에 온 콩성훈, \"우리 사랑쨩 보셔써요? 사랑이찾다 여기까지 왔슴미다.\"",
            "콩남울의 친구, 평소에도 김치국을 잘 마셔 이번에도 파티의 주인공인줄 알고 혼자 파티복을 입고온 파티콩",
            "관악구에서 학교를 다니고있는 콩미림, \"152버스에서 광고를 보고 파티에 오게 되었다. 안녕하십니까!\"",
            "신세계에서 밀짚모자 해적단에서 의사로 있던 쵸파, 사실 짱구 덕후였다. 액션가면을 만나기위해 파티장에 온 쵸파콩",
            "콩나물계의 최고 요리사인 요리콩, \"오늘 밤 당신을 최고의 디너쇼에 초대합니다.\"",
            "한국 고교 대표 미림 배구팀과 결승을 하러 온 하이콩, 세트 스코어 1:1인 상황. \"우린 한세트 더 게임 할 수 있어\"",
            "어렸을 때 김연아 선수를 보고 피겨를 시작한 연아콩, 그리하여 기록적인 점수를 받고 수상소감을 하는데.. \"이 영광을 제 사랑하는 부모님과 존경하는 연아선배님께 바칩니다.\"",
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        img = intent.getIntExtra("img", 0);
        setContentView(R.layout.activity_image);

        imageView = (ImageView) findViewById(R.id.img);
        textView = (TextView) findViewById(R.id.img_text);
        storyView = (TextView) findViewById(R.id.img_story);

        face = Typeface.createFromAsset(getAssets(), "BMHANNA.ttf");
        textView.setTypeface(face);
        storyView.setTypeface(face);

        imageView.setImageResource(R.drawable.step01 + (img-1));
        textView.setText(name[img - 1]);
        storyView.setText(story[img - 1]);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(getApplicationContext(), CollectActivity.class);
        startActivity(intent);
        finish();
    }
}
