package com.ufreedom.floatingview.transition;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;
import com.facebook.rebound.SpringUtil;

import java.lang.ref.WeakReference;

/**
 * Author UFreedom
 * Date : 2016 十月 19
 */

public class YumFloating implements ITransition, Rebound {

    private SpringSystem springSystem;
    private WeakReference<View> targetViewWeakReference;
    
    public YumFloating(View targetView, SpringSystem springSystem) {
        targetViewWeakReference = new WeakReference<View>(targetView);
        this.springSystem = springSystem;
    }


    public YumFloating(View targetView) {
        targetViewWeakReference = new WeakReference<View>(targetView);

    }

    public View getTargetView() {
        return targetViewWeakReference.get();
    }

    @Override
    public void setAlpha(float alpha) {
        View targetView;
        if ((targetView = getTargetView()) !=null){
            targetView.setAlpha(alpha);
        }
    }

    @Override
    public void setRotation(float rotation) {
        View targetView;
        if ((targetView = getTargetView()) !=null){
            targetView.setRotation(rotation);
        }
    }

    @Override
    public void setRotationX(float rotationX) {
        View targetView;
        if ((targetView = getTargetView()) !=null){
            targetView.setRotationX(rotationX);
        }
    }

    @Override
    public void setRotationY(float rotationY) {
        View targetView;
        if ((targetView = getTargetView()) !=null){
            targetView.setRotationY(rotationY);
        }
    }

    @Override
    public void setScaleX(float scaleX) {
        View targetView;
        if ((targetView = getTargetView()) !=null){
            targetView.setScaleX(scaleX);
        }
    }

    @Override
    public void setScaleY(float scaleY) {
        View targetView;
        if ((targetView = getTargetView()) !=null){
            targetView.setScaleY(scaleY);
        }
    }

    @Override
    public void setScrollX(int scrollX) {
        View targetView;
        if ((targetView = getTargetView()) !=null){
            targetView.setScaleX(scrollX);
        }
    }

    @Override
    public void setScrollY(int scrollY) {
        View targetView;
        if ((targetView = getTargetView()) !=null){
            targetView.setScaleY(scrollY);
        }
      
    }

    @Override
    public void setTranslationX(float translationX) {
        View targetView;
        if ((targetView = getTargetView()) !=null){
            targetView.setTranslationX(translationX);
        }
    }

    @Override
    public void setTranslationY(float translationY) {
        View targetView;
        if ((targetView = getTargetView()) !=null){
            targetView.setTranslationY(translationY);
        }
    }

    @Override
    public void setX(float x) {
        View targetView;
        if ((targetView = getTargetView()) !=null){
            targetView.setX(x);
        }
    }

    @Override
    public void setY(float y) {
        View targetView;
        if ((targetView = getTargetView()) !=null){
            targetView.setY(y);
        }
    }


    @Override
    public Spring createSpringByBouncinessAndSpeed(double bounciness, double speed) {
        return springSystem.createSpring()
                .setSpringConfig(SpringConfig.fromBouncinessAndSpeed(bounciness, speed));
    }

    @Override
    public Spring createSpringByTensionAndFriction(double tension, double friction) {
        return springSystem.createSpring()
                .setSpringConfig(SpringConfig.fromOrigamiTensionAndFriction(tension, friction));
    }

    @Override
    public float transition(double progress, float startValue, float endValue) {
        return (float) SpringUtil.mapValueFromRangeToRange(progress, 0, 1, startValue, endValue);
    }
    
    
    public void clear(){
        View view = getTargetView();
        if (view != null){
            ViewParent viewParent =  view.getParent();
            if (viewParent instanceof ViewGroup){
                ViewGroup parent = (ViewGroup) viewParent;
                parent.removeView(view);
                targetViewWeakReference.clear();
            }
        }
    }
    
}
