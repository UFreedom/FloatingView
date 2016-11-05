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
    
    private float startValue;
    private float endValue;
    private double configValueOne;
    private double configValueTwo;
    private int config;
    private ReboundListener reboundListener;
    private SpringListener springListener;

    private SpringHelper(float startValue, float endValue) {
        this.startValue = startValue;
        this.endValue = endValue;
    }

    public static SpringHelper create(float startValue, float endValue){
        return new SpringHelper(startValue,endValue);
    }

    public static  SpringHelper createWidthBouncinessAndSpeed(float startValue, float endValue,double bounciness, double speed){
        SpringHelper springHelper = new SpringHelper(startValue,endValue);
        return springHelper.configBouncinessAndSpeed(bounciness,speed);
    }

    public static SpringHelper createWidthTensionAndFriction(float startValue, float endValue,double tension, double friction){
        SpringHelper springHelper = new SpringHelper(startValue,endValue);
        return springHelper.configTensionAndFriction(tension,friction);
    }
    
    
    public SpringHelper configBouncinessAndSpeed(double bounciness, double speed){
        configValueOne = bounciness;
        configValueTwo = speed;
        config = 0;
        return this;
    }
    
    public SpringHelper configTensionAndFriction(double tension, double friction){
        configValueOne = tension;
        configValueTwo = friction;
        config = 1;
        return this;
    }
    
    public SpringHelper reboundListener(ReboundListener reboundListener){
        this.reboundListener = reboundListener;
        return this;
    }
    
    public SpringHelper springListener(SpringListener springListener){
        this.springListener = springListener;
        return this;
    }
    
    public void start(){
        SpringSystem springSystem = SpringSystem.create();
        Spring spring  = springSystem.createSpring();
        if (config == 0){
            spring.setSpringConfig(SpringConfig.fromBouncinessAndSpeed(configValueOne, configValueTwo));
        }else if (config == 1){
            spring.setSpringConfig(SpringConfig.fromOrigamiTensionAndFriction(configValueOne,configValueTwo));
        }
        start(spring);
    }
    
    private void start(Spring spring){
        if (spring != null){

            if (springListener != null){
                spring.addListener(springListener);
            }
            
            spring.addListener(new SimpleSpringListener(){
                @Override
                public void onSpringUpdate(Spring spring) {
                    if (spring.getCurrentValue() == spring.getEndValue()){
                        if (reboundListener != null){
                            reboundListener.onReboundEnd();
                        }
                    }
                    if (reboundListener != null){
                        reboundListener.onReboundUpdate(transition(spring.getCurrentValue(),startValue,endValue));
                    }
                }
            }).setEndValue(endValue);
        }else {
            throw new NullPointerException("Spring should not be null");
        }
    }
    
    
    public void start(final YumFloating yumFloating){
        if (config == -1){
            throw new IllegalStateException("Hi , You mast call one of the method configBouncinessAndSpeed and configTensionAndFriction to make config");
        }
        
        Spring spring = null;
        if (config == 0){
            spring = yumFloating.createSpringByBouncinessAndSpeed(configValueOne,configValueTwo);
        }else if (config == 1){
            spring = yumFloating.createSpringByTensionAndFriction(configValueOne,configValueTwo);
        }
        start(spring);
    }

    public float transition(double progress, float startValue, float endValue) {
        return (float) SpringUtil.mapValueFromRangeToRange(progress, 0, 1, startValue, endValue);
    }
  
}
