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
    
    private FloatingElement mFloatingElement;

    public FloatingBuilder() {
        mFloatingElement = new FloatingElement();
        mFloatingElement.targetViewLayoutResId = -1;
    }
    
    public FloatingBuilder offsetX(int offsetX) {
        mFloatingElement.offsetX = offsetX;
        return this;
    }
    
    public FloatingBuilder offsetY(int offsetY) {
        mFloatingElement.offsetY = offsetY;
        return this;
    }
  

    public FloatingBuilder floatingTransition(FloatingTransition floatingTransition) {
        mFloatingElement.floatingTransition = floatingTransition;
        return  this;
    }
    
    
    public FloatingBuilder anchorView(View view){
        mFloatingElement.anchorView = view;
        return this;
    }
    
    public FloatingBuilder targetView(View view) {
        mFloatingElement.targetView = view;
        return this;
    }

    public FloatingBuilder targetView(@LayoutRes int layResId) {
        mFloatingElement.targetViewLayoutResId = layResId;
        return this;
    } 

    public FloatingElement build() {

        if (mFloatingElement.targetView == null && mFloatingElement.targetViewLayoutResId == -1) {
            throw new NullPointerException("TargetView should not be null");
        }
        
        if (mFloatingElement.anchorView == null){
            throw new NullPointerException("AnchorView should not be null");
        }

        if (mFloatingElement.floatingTransition == null) {
            mFloatingElement.floatingTransition = new ScaleFloatingTransition();
        }
        return mFloatingElement;
    }

}
