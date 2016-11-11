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
