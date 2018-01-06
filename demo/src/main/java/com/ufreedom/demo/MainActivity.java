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
import android.widget.TextView;

import com.ufreedom.floatingview.Floating;
import com.ufreedom.floatingview.FloatingBuilder;
import com.ufreedom.floatingview.FloatingElement;
import com.ufreedom.floatingview.effect.ScaleFloatingTransition;
import com.ufreedom.floatingview.effect.TranslateFloatingTransition;
import com.ufreedom.floatingview.spring.ReboundListener;
import com.ufreedom.floatingview.spring.SimpleReboundListener;
import com.ufreedom.floatingview.spring.SpringHelper;
import com.ufreedom.floatingview.transition.BaseFloatingPathTransition;
import com.ufreedom.floatingview.transition.FloatingPath;
import com.ufreedom.floatingview.transition.FloatingTransition;
import com.ufreedom.floatingview.transition.PathPosition;
import com.ufreedom.floatingview.transition.YumFloating;

public class MainActivity extends AppCompatActivity {

    private Floating mFloating;
    private View mIcPlaneView;
    private View mIcPaperAirPlaneView;
    private int mScreenWidth;
    private int mScreenHeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFloating = new Floating(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }

        mScreenWidth = UIUtils.getScreenWidth(this);
        mScreenHeight = UIUtils.getScreenWidth(this);
        initLayout();
    }

    private void initLayout() {

        int margin = UIUtils.dip2px(this, 15);
        int w = mScreenWidth - margin * 2;
        int h = (int) (w * 0.53f);

        RelativeLayout bikeRootView = findViewById(R.id.itemBikeContainerView);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) bikeRootView.getLayoutParams();
        layoutParams.width = w;
        layoutParams.height = h;

        RelativeLayout clockRootView = findViewById(R.id.itemClockContainerView);
        RelativeLayout.LayoutParams clockRootViewLayoutParams = (RelativeLayout.LayoutParams) clockRootView.getLayoutParams();
        clockRootViewLayoutParams.width = w;
        clockRootViewLayoutParams.height = h;

        mIcPlaneView = findViewById(R.id.icPlane);
        mIcPlaneView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(mIcPlaneView.getMeasuredWidth(), mIcPlaneView.getMeasuredHeight()));
                imageView.setImageResource(R.drawable.floating_plane);

                FloatingElement floatingElement = new FloatingBuilder()
                        .anchorView(v)
                        .targetView(imageView)
                        .floatingTransition(new PlaneFloating())
                        .build();
                mFloating.startFloating(floatingElement);
            }
        });

        mIcPaperAirPlaneView = findViewById(R.id.icPaperAirPlane);
        mIcPaperAirPlaneView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(mIcPaperAirPlaneView.getMeasuredWidth(), mIcPaperAirPlaneView.getMeasuredHeight()));
                imageView.setImageResource(R.drawable.paper_airplane);

                FloatingElement floatingElement = new FloatingBuilder()
                        .anchorView(v)
                        .targetView(imageView)
                        .floatingTransition(new TranslateFloatingTransition())
                        .build();
                mFloating.startFloating(floatingElement);
            }
        });

        View icCommandLineView = findViewById(R.id.icCommandLine);
        icCommandLineView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = new TextView(MainActivity.this);
                textView.setText("Hello FloatingView");

                FloatingElement floatingElement = new FloatingBuilder()
                        .anchorView(v)
                        .targetView(textView)
                        .offsetY(-v.getMeasuredHeight())
                        .floatingTransition(new ScaleFloatingTransition())
                        .build();
                mFloating.startFloating(floatingElement);
            }
        });

        View icLikeView = findViewById(R.id.icLike);
        icLikeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FloatingElement floatingElement = new FloatingBuilder()
                        .anchorView(v)
                        .targetView(R.layout.ic_like)
                        .floatingTransition(new TranslateFloatingTransition())
                        .build();
                mFloating.startFloating(floatingElement);

            }
        });


        final View icStarView = findViewById(R.id.icStar);
        icStarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(icStarView.getMeasuredWidth(), icStarView.getMeasuredHeight()));
                imageView.setImageResource(R.drawable.star_floating);

                final FloatingElement floatingElement = new FloatingBuilder()
                        .anchorView(v)
                        .targetView(imageView)
                        .floatingTransition(new StarFloating())
                        .build();

                SpringHelper.createWithBouncinessAndSpeed(0f, 1f, 11, 15).reboundListener(new ReboundListener() {
                    @Override
                    public void onReboundUpdate(double currentValue) {
                        v.setScaleX((float) currentValue);
                        v.setScaleY((float) currentValue);
                    }

                    @Override
                    public void onReboundEnd() {
                        mFloating.startFloating(floatingElement);
                    }
                }).start();


            }
        });


        final View icBeerView = findViewById(R.id.icBeer);
        icBeerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(icBeerView.getMeasuredWidth(), icBeerView.getMeasuredHeight()));
                imageView.setImageResource(R.drawable.beer);

                FloatingElement floatingElement = new FloatingBuilder()
                        .anchorView(v)
                        .targetView(imageView)
                        .floatingTransition(new BeerFloating())
                        .build();
                mFloating.startFloating(floatingElement);
            }
        });
    }

    class PlaneFloating extends BaseFloatingPathTransition {

        @Override
        public FloatingPath getFloatingPath() {
            Path path = new Path();
            path.moveTo(0, 0);
            path.quadTo(100, -300, 0, -600);
            path.rLineTo(0, -mScreenHeight - 300);
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


            SpringHelper.createWithBouncinessAndSpeed(0.0f, 1.0f, 14, 15)
                    .reboundListener(new SimpleReboundListener() {
                        @Override
                        public void onReboundUpdate(double currentValue) {
                            yumFloating.setScaleX((float) currentValue);
                            yumFloating.setScaleY((float) currentValue);
                        }
                    }).start(yumFloating);

            translateAnimator.setDuration(3000);
            translateAnimator.start();
        }
    }

    class StarFloating implements FloatingTransition {

        @Override
        public void applyFloating(final YumFloating yumFloating) {
            SpringHelper.createWithBouncinessAndSpeed(0.0f, 1.0f, 10, 15)
                    .reboundListener(new SimpleReboundListener() {
                        @Override
                        public void onReboundUpdate(double currentValue) {
                            yumFloating.setScaleX((float) currentValue);
                            yumFloating.setScaleY((float) currentValue);
                        }
                    }).start(yumFloating);


            ValueAnimator rotateAnimator = ObjectAnimator.ofFloat(0, 360);
            rotateAnimator.setDuration(500);
            rotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
            rotateAnimator.setRepeatMode(ValueAnimator.RESTART);
            rotateAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    yumFloating.setRotation((float) valueAnimator.getAnimatedValue());
                }
            });

            ValueAnimator translateAnimator = ObjectAnimator.ofFloat(0, 500);
            translateAnimator.setDuration(600);
            translateAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    yumFloating.setTranslationY(-(Float) valueAnimator.getAnimatedValue());
                }
            });
            translateAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    yumFloating.setAlpha(0f);
                    yumFloating.clear();
                }
            });
            rotateAnimator.start();
            translateAnimator.start();
        }
    }


    class BeerFloating extends BaseFloatingPathTransition {


        @Override
        public FloatingPath getFloatingPath() {
            Path path = new Path();
            path.rLineTo(-100, 0);
            path.quadTo(0, -200, 100, 0);
            path.quadTo(0, 200, -100, 0);
            return FloatingPath.create(path, false);
        }

        @Override
        public void applyFloating(final YumFloating yumFloating) {
            ValueAnimator translateAnimator = ObjectAnimator.ofFloat(0, 500);
            translateAnimator.setDuration(600);
            translateAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float value = (float) valueAnimator.getAnimatedValue();
                    PathPosition floatingPosition = getFloatingPosition(value);
                    yumFloating.setTranslationX(floatingPosition.x);
                    yumFloating.setTranslationY(floatingPosition.y);

                }
            });


            ValueAnimator alphaAnimation = ObjectAnimator.ofFloat(1f, 0f);
            alphaAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    yumFloating.setAlpha((Float) animation.getAnimatedValue());
                }
            });
            alphaAnimation.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    yumFloating.clear();
                }
            });
            alphaAnimation.setStartDelay(550);
            alphaAnimation.setDuration(300);
            translateAnimator.start();
            alphaAnimation.start();
        }
    }

}
