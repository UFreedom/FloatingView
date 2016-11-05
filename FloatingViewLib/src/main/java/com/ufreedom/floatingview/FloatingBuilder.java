package com.ufreedom.floatingview;

import android.support.annotation.LayoutRes;
import android.view.View;

import com.ufreedom.floatingview.effect.ScaleFloatingTransition;
import com.ufreedom.floatingview.transition.FloatingTransition;


/**
 * 
 * Helped class for building {@link FloatingElement}
 * 
 * Author UFreedom
 * Date : 2016 十月 19
 */
public class FloatingBuilder {
    
    private FloatingElement floatingElement;

    public FloatingBuilder() {
        floatingElement = new FloatingElement();
        floatingElement.targetViewLayoutResId = -1;
    }
    
    public FloatingBuilder offsetX(int offsetX) {
        floatingElement.offsetX = offsetX;
        return this;
    }
    
    public FloatingBuilder offsetY(int offsetY) {
        floatingElement.offsetY = offsetY;
        return this;
    }
  

    public FloatingBuilder floatingTransition(FloatingTransition floatingTransition) {
        floatingElement.floatingTransition = floatingTransition;
        return  this;
    }
    
    
    public FloatingBuilder anchorView(View view){
        floatingElement.anchorView = view;
        return this;
    }
    
    public FloatingBuilder targetView(View view) {
        floatingElement.targetView = view;
        return this;
    }

    public FloatingBuilder targetView(@LayoutRes int layResId) {
        floatingElement.targetViewLayoutResId = layResId;
        return this;
    } 

    public FloatingElement build() {

        if (floatingElement.targetView == null && floatingElement.targetViewLayoutResId == -1) {
            throw new NullPointerException("TargetView should not be null");
        }
        
        if (floatingElement.anchorView == null){
            throw new NullPointerException("AnchorView should not be null");
        }

        if (floatingElement.floatingTransition == null) {
            floatingElement.floatingTransition = new ScaleFloatingTransition();
        }
        return floatingElement;
    }

}
