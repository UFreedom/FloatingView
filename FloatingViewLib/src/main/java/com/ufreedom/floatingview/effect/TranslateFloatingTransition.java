package com.ufreedom.floatingview.effect;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.ufreedom.floatingview.YumFloating;
import com.ufreedom.floatingview.transition.ReboundFloatingTransition;


/**
 * Author UFreedom
 * Date : 2016 十月 19
 */

public class TranslateFloatingTransition extends ReboundFloatingTransition {

    private float translateY;
    private long duration;

    public TranslateFloatingTransition() {
        translateY = -200f;
        duration = 1500;
    }


    public TranslateFloatingTransition(float translateY, long duration) {
        this.translateY = translateY;
        this.duration = duration;
    }

    @Override
    public void applyFloating(final YumFloating yumFloating) {

        yumFloating.setTranslationY(0);
        yumFloating.setAlpha(1f);
        yumFloating.setScaleX(0f);
        yumFloating.setScaleY(0f);
        Spring scaleAnim = createSpringByBouncinessAndSpeed(10, 15)
                .addListener(new SimpleSpringListener() {
                    @Override
                    public void onSpringUpdate(Spring spring) {
                        float transition = transition((float) spring.getCurrentValue(), 0.0f, 1.0f);
                        yumFloating.setScaleX(transition);
                        yumFloating.setScaleY(transition);
                    }
                });


        ValueAnimator translateAnimator = ObjectAnimator.ofFloat(0, translateY);
        translateAnimator.setDuration(duration);
        translateAnimator.setStartDelay(50);
        translateAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                yumFloating.setTranslationY((Float) valueAnimator.getAnimatedValue());
            }
        });
        translateAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                yumFloating.setTranslationY(0);
                yumFloating.setAlpha(0f);

            }
        });


        ValueAnimator alphaAnimator = ObjectAnimator.ofFloat(1.0f, 0.0f);
        alphaAnimator.setDuration(duration);
        alphaAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                yumFloating.setAlpha((Float) valueAnimator.getAnimatedValue());
            }
        });


        scaleAnim.setEndValue(1f);
        alphaAnimator.start();
        translateAnimator.start();
    }

}
