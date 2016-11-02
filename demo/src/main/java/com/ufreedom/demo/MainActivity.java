package com.ufreedom.demo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ufreedom.floatingview.Floating;
import com.ufreedom.floatingview.FloatingBuilder;
import com.ufreedom.floatingview.FloatingElement;
import com.ufreedom.floatingview.effect.TranslateFloatingTransition;
import com.ufreedom.floatingview.spring.SimpleReboundListener;
import com.ufreedom.floatingview.spring.SpringHelper;
import com.ufreedom.floatingview.transition.BaseFloatingPathTransition;
import com.ufreedom.floatingview.transition.FloatingPath;
import com.ufreedom.floatingview.transition.PathPosition;
import com.ufreedom.floatingview.transition.YumFloating;

public class MainActivity extends AppCompatActivity {

    private Floating floating;
    private View icPlaneView;
    private View icPaperAirPlaneView;
    private int screenWidth;
    private int screenHeight;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floating = new Floating(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }

        screenWidth = UIUtils.getScreenWidth(this);
        screenHeight = UIUtils.getScreenWidth(this);
        initLayout();
    }

    private void initLayout() {
        
        int margin = UIUtils.dip2px(this,15);
        int w = screenWidth - margin * 2;
        int h = (int) (w * 0.53f);
        
        RelativeLayout bikeRootView = (RelativeLayout) findViewById(R.id.itemBikeContainerView);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) bikeRootView.getLayoutParams();
        layoutParams.width = w;
        layoutParams.height = h;


        RelativeLayout clockRootView = (RelativeLayout) findViewById(R.id.itemClockContainerView);
        RelativeLayout.LayoutParams clockRootViewLayoutParams = (RelativeLayout.LayoutParams) clockRootView.getLayoutParams();
        clockRootViewLayoutParams.width = w;
        clockRootViewLayoutParams.height = h;


        icPlaneView = findViewById(R.id.icPlane);
        icPlaneView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(icPlaneView.getMeasuredWidth(), icPlaneView.getMeasuredHeight()));
                imageView.setImageResource(R.drawable.floating_plane);

                FloatingElement floatingElement = new FloatingBuilder()
                        .anchorView(v)
                        .target(imageView)
                        .floatingTransition(new PlaneFloating())
                        .build();
                floating.startFloating(floatingElement);
            }
        });

        icPaperAirPlaneView = findViewById(R.id.icPaperAirPlane);
        icPaperAirPlaneView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(icPlaneView.getMeasuredWidth(), icPlaneView.getMeasuredHeight()));
                imageView.setImageResource(R.drawable.paper_airplane);

                FloatingElement floatingElement = new FloatingBuilder()
                        .anchorView(v)
                        .target(imageView)
                        .floatingTransition(new TranslateFloatingTransition())
                        .build();
                floating.startFloating(floatingElement);
            }
        });
        
        View icLikeView = findViewById(R.id.icLike);
        icLikeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FloatingElement floatingElement = new FloatingBuilder()
                        .anchorView(v)
                        .target(R.layout.ic_like)
                        .floatingTransition(new TranslateFloatingTransition())
                        .build();
                floating.startFloating(floatingElement);
                
            }
        });
        
    }

    class PlaneFloating extends BaseFloatingPathTransition {

        @Override
        public FloatingPath getFloatingPath() {
            Path path = new Path();
            path.moveTo(0, 0);
            path.quadTo(100, -300, 0, -600);
            path.rLineTo(0, -screenHeight - 300);
            return FloatingPath.create(path, false);
        }

        @Override
        public void applyFloating(final YumFloating yumFloating) {
            
            ValueAnimator translateAnimator = ObjectAnimator.ofFloat(getStartPathPosition(), getEndPathPosition());
            translateAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float value = (float) valueAnimator.getAnimatedValue();
                    PathPosition floatingPosition = getFloatingPosition(value);
                    yumFloating.setTranslationX(floatingPosition.x);
                    yumFloating.setTranslationY(floatingPosition.y);

                }
            });
            translateAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    yumFloating.setTranslationX(0);
                    yumFloating.setTranslationY(0);
                    yumFloating.setAlpha(0f);
                    yumFloating.clear();
                }
            });
            

            SpringHelper.createWidthBouncinessAndSpeed(0.0f, 1.0f, 14, 15)
                    .reboundListener(new SimpleReboundListener() {
                        @Override
                        public void onReboundUpdate(double currentValue) {
                            yumFloating.setScaleX((float) currentValue);
                            yumFloating.setScaleY((float) currentValue);
                        }
                    }).start(yumFloating);

            translateAnimator.setDuration(3000);
            translateAnimator.setStartDelay(50);
            translateAnimator.start();
        }
    }
    

}
