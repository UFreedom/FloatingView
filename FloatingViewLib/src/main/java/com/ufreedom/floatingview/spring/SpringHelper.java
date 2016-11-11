/*
 * Copyright (C) 2015 UFreedom
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.ufreedom.floatingview.spring;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringListener;
import com.facebook.rebound.SpringSystem;
import com.facebook.rebound.SpringUtil;
import com.ufreedom.floatingview.transition.YumFloating;

/**
 * 
 * A help class for using Facebook Rebound
 * 
 * Author UFreedom
 * Date : 2016 十月 20
 */

public class SpringHelper {
    
    private float mStartValue;
    private float mEndValue;
    private double mConfigValueOne;
    private double mConfigValueTwo;
    private int mConfig = -1;
    private ReboundListener mReboundListener;
    private SpringListener mSpringListener;

    private SpringHelper(float startValue, float endValue) {
        this.mStartValue = startValue;
        this.mEndValue = endValue;
    }

    public static SpringHelper create(float startValue, float endValue){
        return new SpringHelper(startValue,endValue);
    }

    public static  SpringHelper createWithBouncinessAndSpeed(float startValue, float endValue, double bounciness, double speed){
        SpringHelper springHelper = new SpringHelper(startValue,endValue);
        return springHelper.configBouncinessAndSpeed(bounciness,speed);
    }

    public static SpringHelper createWithTensionAndFriction(float startValue, float endValue, double tension, double friction){
        SpringHelper springHelper = new SpringHelper(startValue,endValue);
        return springHelper.configTensionAndFriction(tension,friction);
    }
    
    
    public SpringHelper configBouncinessAndSpeed(double bounciness, double speed){
        mConfigValueOne = bounciness;
        mConfigValueTwo = speed;
        mConfig = 0;
        return this;
    }
    
    public SpringHelper configTensionAndFriction(double tension, double friction){
        mConfigValueOne = tension;
        mConfigValueTwo = friction;
        mConfig = 1;
        return this;
    }
    
    public SpringHelper reboundListener(ReboundListener reboundListener){
        this.mReboundListener = reboundListener;
        return this;
    }
    
    public SpringHelper springListener(SpringListener springListener){
        this.mSpringListener = springListener;
        return this;
    }
    
    public void start(){
        SpringSystem springSystem = SpringSystem.create();
        Spring spring  = springSystem.createSpring();
        if (mConfig == 0){
            spring.setSpringConfig(SpringConfig.fromBouncinessAndSpeed(mConfigValueOne, mConfigValueTwo));
        }else if (mConfig == 1){
            spring.setSpringConfig(SpringConfig.fromOrigamiTensionAndFriction(mConfigValueOne, mConfigValueTwo));
        }
        start(spring);
    }
    
    private void start(Spring spring){
        if (spring != null){

            if (mSpringListener != null){
                spring.addListener(mSpringListener);
            }
            
            spring.addListener(new SimpleSpringListener(){
                @Override
                public void onSpringUpdate(Spring spring) {
                    if (spring.getCurrentValue() == spring.getEndValue()){
                        if (mReboundListener != null){
                            mReboundListener.onReboundEnd();
                        }
                    }
                    if (mReboundListener != null){
                        mReboundListener.onReboundUpdate(transition(spring.getCurrentValue(), mStartValue, mEndValue));
                    }
                }
            }).setEndValue(mEndValue);
        }else {
            throw new NullPointerException("Spring should not be null");
        }
    }
    
    
    public void start(final YumFloating yumFloating){
        if (mConfig == -1){
            throw new IllegalStateException("Hi , You must call one of the method configBouncinessAndSpeed and configTensionAndFriction to make mConfig");
        }
        
        Spring spring = null;
        if (mConfig == 0){
            spring = yumFloating.createSpringByBouncinessAndSpeed(mConfigValueOne, mConfigValueTwo);
        }else if (mConfig == 1){
            spring = yumFloating.createSpringByTensionAndFriction(mConfigValueOne, mConfigValueTwo);
        }
        start(spring);
    }

    public float transition(double progress, float startValue, float endValue) {
        return (float) SpringUtil.mapValueFromRangeToRange(progress, 0, 1, startValue, endValue);
    }
  
}
