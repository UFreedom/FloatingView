package com.ufreedom.floatingview;

import android.view.View;

import com.ufreedom.floatingview.transition.FloatingTransition;


/**
 * Author UFreedom
 * Date : 2016 十月 19
 */

public class FloatingElement {
    
    public FloatingTransition floatingTransition;
    
    public int offsetX;
    
    public int offsetY;
    
    public View targetView;
    
    public View anchorView;
    
    public int targetViewLayoutResId;
}
