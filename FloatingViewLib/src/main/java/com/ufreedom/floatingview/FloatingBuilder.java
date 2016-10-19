package com.ufreedom.floatingview;

import android.view.View;

import com.ufreedom.floatingview.effect.ScaleFloatingTransition;
import com.ufreedom.floatingview.transition.FloatingTransition;


/**
 * Author UFreedom
 * Date : 2016 十月 19
 */
public class FloatingBuilder {
    
    private FloatingElement floatingElement;

    public FloatingBuilder() {
        floatingElement = new FloatingElement();
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
    
    public FloatingBuilder target(View view) {
        floatingElement.targetView = view;
        return this;
    }

    public FloatingElement build() {

        if (floatingElement.targetView == null) {
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
