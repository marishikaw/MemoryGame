package com.example.memorygame;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageView curView = null;
    private int countPair = 0;
    private final int[] DRAWABLE = new int[]{
            R.drawable.apple,
            R.drawable.banana,
            R.drawable.grape,
            R.drawable.orange,
            R.drawable.peach,
            R.drawable.strawberry
    };

    private int[] positions = {0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 4, 5};
    private int currentPos = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // インスタンス生成
        ImageAdapter imageAdapter = new ImageAdapter(this);
        GridView gridView = (GridView) findViewById(R.id.gridView);
        // GridviewにImageAdapterをセット
        gridView.setAdapter(imageAdapter);
        // Gridviewがクリックされた時
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //1枚目
                if (currentPos < 0 ) {
                    currentPos = position;
                    curView = (ImageView) view;
                    ((ImageView) view).setImageResource(DRAWABLE[positions[position]]);
                //2枚目
                } else {
                    //1枚目と同じ場所をクリックした場合
                    if (currentPos == position) {
                        ((ImageView) view).setImageResource(R.drawable.card);
                    //不一致だった場合
                    } else if (positions[currentPos] != positions[position]) {
                        curView.setImageResource(R.drawable.card);
                        Toast.makeText(MainActivity.this, "Not Match!", Toast.LENGTH_LONG).show();
                    //一致だった場合
                    } else {
                        ((ImageView) view).setImageResource(DRAWABLE[positions[position]]);
                        countPair++;
                        if (countPair == 6) {
                            Toast.makeText(MainActivity.this, "You Win!", Toast.LENGTH_LONG).show();
                        }
                    }
                    currentPos = -1;
                }
            }
        });
    }
}