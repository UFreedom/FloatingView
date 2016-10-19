package com.ufreedom.floatingview.transition;

import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;
import com.facebook.rebound.SpringUtil;

/**
 * Author UFreedom
 * Date : 2016 十月 19
 */

public abstract class ReboundFloatingTransition implements FloatingTransition {

    protected SpringSystem springSystem;
    
    
    public ReboundFloatingTransition() {
        springSystem = SpringSystem.create();
    }

    public Spring createSpringByBouncinessAndSpeed(double bounciness, double speed) {
        return springSystem.createSpring()
                .setSpringConfig(SpringConfig.fromBouncinessAndSpeed(bounciness, speed));
    }

    public Spring createSpringByTensionAndFriction(double bounciness, double speed) {
        return springSystem.createSpring()
                .setSpringConfig(SpringConfig.fromOrigamiTensionAndFriction(bounciness, speed));
    }

    protected float transition(float progress, float startValue, float endValue) {
        return (float) SpringUtil.mapValueFromRangeToRange(progress, 0, 1, startValue, endValue);
    }
}
