package com.ufreedom.floatingview.spring;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.ufreedom.floatingview.transition.YumFloating;

/**
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
    
    
    public void start(final YumFloating yumFloating){
        if (config == -1){
            throw new IllegalStateException("Hi , You mast call one of the method configBouncinessAndSpeed and configTensionAndFriction to make config");
        }
        
        Spring spring = null;
        if (config == 0){
            spring = yumFloating.createSpringByBouncinessAndSpeed(configValueOne,configValueTwo);
        }else if (config == 1){
            yumFloating.createSpringByTensionAndFriction(configValueOne,configValueTwo);
        }
        if (spring != null){
            spring.addListener(new SimpleSpringListener(){
                @Override
                public void onSpringUpdate(Spring spring) {
                    if (spring.getCurrentValue() == spring.getEndValue()){
                        if (reboundListener != null){
                            reboundListener.onReboundEnd();
                        }
                    }
                    if (reboundListener != null){
                        reboundListener.onReboundUpdate(yumFloating.transition(spring.getCurrentValue(),startValue,endValue));
                    }
                }
            }).setEndValue(endValue);
        }
    }
  
}
