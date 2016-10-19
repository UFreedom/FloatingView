package com.ufreedom.floatingview;

import android.view.View;

/**
 * Author UFreedom
 * Date : 2016 十月 19
 */

public class YumFloating implements IFloating {

    private View targetView;


    public View getTargetView() {
        return targetView;
    }

    public YumFloating(View targetView) {
        this.targetView = targetView;
    }
    
    @Override
    public void setAlpha(float alpha) {
        targetView.setAlpha(alpha);
    }

    @Override
    public void setRotation(float rotation) {
        targetView.setRotation(rotation);
    }

    @Override
    public void setRotationX(float rotationX) {
        targetView.setRotationX(rotationX);
    }

    @Override
    public void setRotationY(float rotationY) {
        targetView.setRotationY(rotationY);
    }

    @Override
    public void setScaleX(float scaleX) {
        targetView.setScaleX(scaleX);
    }

    @Override
    public void setScaleY(float scaleY) {
        targetView.setScaleY(scaleY);
    }

    @Override
    public void setScrollX(int scrollX) {
        targetView.setScaleX(scrollX);
    }

    @Override
    public void setScrollY(int scrollY) {
        targetView.setScaleY(scrollY);
    }

    @Override
    public void setTranslationX(float translationX) {
        targetView.setTranslationX(translationX);
    }

    @Override
    public void setTranslationY(float translationY) {
        targetView.setTranslationY(translationY);
    }

    @Override
    public void setX(float x) {
        targetView.setX(x);
    }

    @Override
    public void setY(float y) {
        targetView.setY(y);
    }
}
