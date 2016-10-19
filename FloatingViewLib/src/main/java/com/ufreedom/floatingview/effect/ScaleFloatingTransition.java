package com.ufreedom.floatingview.effect;

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

public class ScaleFloatingTransition extends ReboundFloatingTransition {

    private long duration;

    public ScaleFloatingTransition() {
        duration = 1000;
    }

    public ScaleFloatingTransition(long duration) {
        this.duration = duration;
    }

    @Override
    public void applyFloating(final YumFloating yumFloating) {
        Spring scaleAnim = createSpringByBouncinessAndSpeed(10, 15)
                .addListener(new SimpleSpringListener() {
                    @Override
                    public void onSpringUpdate(Spring spring) {
                        float transition = transition((float) spring.getCurrentValue(), 0.0f, 1.0f);
                        yumFloating.setScaleX(transition);
                        yumFloating.setScaleY(transition);
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
    }
    
}
