package com.ufreedom.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ufreedom.floatingview.Floating;
import com.ufreedom.floatingview.FloatingBuilder;
import com.ufreedom.floatingview.FloatingElement;
import com.ufreedom.floatingview.effect.CurveFloatingPathTransition;
import com.ufreedom.floatingview.effect.TranslateFloatingTransition;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Floating floatingView = new Floating(this);

        final View normalView = findViewById(R.id.normalView);


        assert normalView != null;
        normalView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textView = new TextView(MainActivity.this);
                textView.setBackgroundColor(Color.RED);
                textView.setText("UFreedom");
                FloatingElement builder = new FloatingBuilder().anchorView(view)
                        .floatingTransition(new TranslateFloatingTransition())
                        .target(textView).build();
                floatingView.startFloating(builder);
            }
        });
        
        
        View curveView = findViewById(R.id.curveView);

        assert curveView != null;
        curveView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout linearLayout = new LinearLayout(MainActivity.this);
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setImageDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
                linearLayout.addView(imageView);

                TextView textView = new TextView(MainActivity.this);
                textView.setBackgroundColor(Color.RED);
                textView.setText("UFreedom");
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.gravity = Gravity.CENTER_VERTICAL;
                linearLayout.addView(textView,layoutParams);

                FloatingElement builder = new FloatingBuilder().anchorView(view)
                        .target(linearLayout).floatingTransition(new CurveFloatingPathTransition()).build();
                floatingView.startFloating(builder);

            }
        });

        View scaleView = findViewById(R.id.scaleView);


        assert scaleView != null;
        scaleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView textView = new TextView(MainActivity.this);
                textView.setBackgroundColor(Color.RED);
                textView.setText("UFreedom");
                FloatingElement builder = new FloatingBuilder().anchorView(view)
                        .target(textView).build();
                floatingView.startFloating(builder);
            }
        });
    }
}
