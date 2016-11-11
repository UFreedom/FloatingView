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

import android.view.View;

import com.ufreedom.floatingview.transition.FloatingTransition;

/**
 * 
 * Data structure for a floating element.
 * 
 * 
 * Author UFreedom
 * Date : 2016 十月 19
 */

public class FloatingElement {

    
    /**
     * Floating effect, the default is ScaleFloatingTransition
     */
    public FloatingTransition floatingTransition;


    /**
     * X direction of offset, unit px
     */
    public int offsetX;

    /**
     * Y direction of offset, unit px
     */
    public int offsetY;


    /**
     * The view which you want to float
     */
    public View targetView;


    /**
     * The layout resources id of the view which you want to float
     */
    public int targetViewLayoutResId;

    /**
     * The view that you want to float above
     */
    public View anchorView;



}
