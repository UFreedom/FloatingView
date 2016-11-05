package com.ufreedom.floatingview.transition;

/**
 * Author UFreedom
 * Date : 2016 十月 18
 */

public interface ITransition {


    public void setAlpha(float alpha);


    public void setRotation(float rotation);
    
    public void setRotationX(float rotationX);

    public void setRotationY(float rotationY);


    public void setScaleX(float scaleX);

    public void setScaleY(float scaleY);


    public void setScrollX(int scrollX);

    public void setScrollY(int scrollY);


    public void setTranslationX(float translationX);
    
    public void setTranslationY(float translationY);


    public void setX(float x);
    
    public void setY(float y);


}
