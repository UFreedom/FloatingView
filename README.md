<p align="center">
  <a href="https://dribbble.com/shots/2583144-Aviate-Badge" target="_blank">
  <img width="320" height="240" src="https://github.com/UFreedom/FloatingView/blob/master/images/floating_anim.gif">
  </a>
 </p>

<h3 align="center">FloatingView</h3>

<p align="center">
  FloatingView can make the target view  floating above the anchor view with cool animation
</p>

<p align="center">
<a target="_blank" href="https://github.com/UFreedom/FloatingView"><img src="https://travis-ci.org/UFreedom/FloatingView.svg?branch=master"></a>
<a target="_blank" href="https://bintray.com/ufreedom/maven/FloatingViewLibrary/_latestVersion"><img src="https://api.bintray.com/packages/ufreedom/maven/FloatingViewLibrary/images/download.svg"></a>
<a target="_blank" href="https://github.com/UFreedom/FloatingView"><img src="https://img.shields.io/badge/android-3.0-brightgreen.svg" ></a>
<a target="_blank" href='https://github.com/UFreedom/FloatingView/blob/master/LICENSE.txt'><img src='https://img.shields.io/crates/l/rustc-serialize.svg'  /></a>
</p>

</br></br>

<p align="center">
  <img  with="288"  height="512" src="https://github.com/UFreedom/FloatingView/blob/master/images/demo.gif">
</p>

Links
-----
- [中文版 README](https://github.com/UFreedom/FloatingView/blob/master/README_CN.md)
- [Blog about FloatingView](http://www.jianshu.com/p/6aaa258d77f1)
- [demo.apk](http://oeapkptbn.bkt.clouddn.com/FloatingView-demo-v1.0.1.apk)


Usage
-----

### Step 1

Add dependencies in build.gradle.

```groovy
    dependencies {
        compile 'com.ufreedom.uikit:FloatingViewLib:1.0.1'
    }

```



### Step 2

Use FloatingBuilder to create a FloatingElement

```java

    FloatingElement builder = new FloatingBuilder()
                            .anchorView(View)
                            .targetView(View)
                            .offsetX(int)
                            .offsetY(int)
                            .floatingTransition(FloatingTransition)
                            .build();

```

The use of FloatingBuilder can be configured to have：

* anchorView ：Anchor, is you want to float animation in which View above
* target：Target, The view which you want to float 
* offsetX：X direction of offset, unit PX
* offsetY: Y direction of offset, unit PX
* floatingTransition : Floating effect, the default is ScaleFloatingTransition


### Step 3 

Create a Floating as a FloatingElement container, and then let your View fly up
 
```java
    Floating floating = new Floating(getActivity());
    floating.startFloating(builder);
```

 
Customisation
-------------
 
 
####1.Coordinates
 
  <img src="http://oeapkptbn.bkt.clouddn.com/coordinate.png" width="572" height="427"/>
 
 
####2.Class Diagram
 
  <img src="http://oeapkptbn.bkt.clouddn.com/class_diagram.png" width="831" height="428"/>

####3.Floating Animation 

Implementation of floating animation is very simple, you only need to implement the [FloatingTransition][4] interface.

```java

    public interface FloatingTransition {
        public void applyFloating(YumFloating yumFloating);
    }

```


In the `applyFloating` method, you can use Android Animation to do the animation, and then use the [YumFloating][6] to do Alpha  , Scale, Translate, Rotate and other transformations.
If you want to add the [Facebook Rebound][5]  animation effect, you can use the [SpringHelper][7], for example, [ScaleFloatingTransition][8]:

```java
    public class ScaleFloatingTransition implements FloatingTransition {

    ...
    
    @Override
    public void applyFloating(final YumFloating yumFloating) {
        
        ValueAnimator alphaAnimator = ObjectAnimator.ofFloat(1.0f, 0.0f);
        alphaAnimator.setDuration(duration);
        alphaAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                yumFloating.setAlpha((Float) valueAnimator.getAnimatedValue());
            }
        });
        alphaAnimator.start();

        SpringHelper.createWithBouncinessAndSpeed(0.0f, 1.0f,bounciness, speed)
                .reboundListener(new SimpleReboundListener(){
                    @Override
                    public void onReboundUpdate(double currentValue) {
                        yumFloating.setScaleX((float) currentValue);
                        yumFloating.setScaleY((float) currentValue);
                    }
                }).start(yumFloating);
    }
    
}


```

If [SpringHelper][7] can not meet your needs, you can directly use the `createSpringByBouncinessAndSpeed(double bounciness, double speed)` or
`createSpringByTensionAndFriction(double tension, double friction)` to create the Spring, and then use `transition (Progress double, startValue float, endValue float)` for numerical conversion
 
####4.Floating Path Animation
The floating path animation is also very simple, such as [CurveFloatingPathTransition][9], first you need to inherit from the [BaseFloatingPathTransition][10] class ,The difference is, you need to implement a `getFloatingPath (`) method.
Use `Path` in the `getFloatingPath () `method to create the path you want to float, and then call `FloatingPath.create (path, false)` to return. For example, [CurveFloatingPathTransition][9] implementation:

```java
    public class CurveFloatingPathTransition extends BaseFloatingPathTransition {

        ...
      
        @Override
        public FloatingPath getFloatingPath() {
            if (path == null){
                path = new Path();
                path.moveTo(0, 0);
                path.quadTo(-100, -200, 0, -300);
                path.quadTo(200, -400, 0, -500);
            }
            return FloatingPath.create(path, false);
        }

        @Override
        public void applyFloating(final YumFloating yumFloating) {
            ValueAnimator translateAnimator;
            ValueAnimator alphaAnimator;
    
            
            translateAnimator = ObjectAnimator.ofFloat(getStartPathPosition(), getEndPathPosition());
            translateAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float value = (float) valueAnimator.getAnimatedValue();
                    PathPosition floatingPosition = getFloatingPosition(value);
                    yumFloating.setTranslationX(floatingPosition.x);
                    yumFloating.setTranslationY(floatingPosition.y);
    
                }
            });
               
           ...
        }
    
}
```

Use Path to describe the path you want to float, and then in `applyFloating (YumFloating yumFloating)`:

* Use `getStartPathPosition () ` method to obtain the starting position of the path
* Use `getEndPathPosition () ` method to obtain the end position of the path
* Use `getFloatingPosition(float progress)` to get the position of the current progress

`getFloatingPosition(float progress)`method will return a `PathPosition` object, its properties x an y representing the current path animation x coordinates and Y coordinates.

License 
--------

    Copyright 2015 UFreedom

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


[1]:https://github.com/UFreedom/FloatingView/blob/master/FloatingViewLib/src/main/java/com/ufreedom/floatingview/FloatingBuilder.java
[2]:https://github.com/UFreedom/FloatingView/blob/master/FloatingViewLib/src/main/java/com/ufreedom/floatingview/FloatingElement.java
[3]:https://github.com/UFreedom/FloatingView/blob/master/FloatingViewLib/src/main/java/com/ufreedom/floatingview/Floating.java
[4]:https://github.com/UFreedom/FloatingView/blob/master/FloatingViewLib/src/main/java/com/ufreedom/floatingview/transition/FloatingTransition.java
[5]:http://facebook.github.io/rebound/
[6]:https://github.com/UFreedom/FloatingView/blob/master/FloatingViewLib/src/main/java/com/ufreedom/floatingview/transition/YumFloating.java
[7]:https://github.com/UFreedom/FloatingView/blob/master/FloatingViewLib/src/main/java/com/ufreedom/floatingview/spring/SpringHelper.java
[8]:https://github.com/UFreedom/FloatingView/blob/master/FloatingViewLib/src/main/java/com/ufreedom/floatingview/effect/ScaleFloatingTransition.java
[9]:https://github.com/UFreedom/FloatingView/blob/master/FloatingViewLib/src/main/java/com/ufreedom/floatingview/effect/CurveFloatingPathTransition.java
[10]:https://github.com/UFreedom/FloatingView/blob/master/FloatingViewLib/src/main/java/com/ufreedom/floatingview/transition/BaseFloatingPathTransition.java