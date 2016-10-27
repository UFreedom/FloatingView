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


Links
-----
- [中文版 README](https://github.com/UFreedom/FloatingView/blob/master/README_CN.md)



Usage
-----

### Step 1

Add dependencies in build.gradle.

```groovy
    dependencies {
        compile 'com.ufreedom.uikit:FloatingViewLib:1.0.0'
    }

```



### Step 2

Use FloatingBuilder to create a FloatingElement

```java

    FloatingElement builder = new FloatingBuilder()
                            .anchorView(View)
                            .target(View)
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
 ---
 
 
 #### Coordinates
 
  <img src="https://github.com/UFreedom/FloatingView/blob/master/images/coordinate.png" width="572" height="427"/>
 
 
 #### Class diagram
 
  <img src="https://github.com/UFreedom/FloatingView/blob/master/images/classdiagram.png" width="831" height="428"/>


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
