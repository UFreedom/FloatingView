package com.ufreedom.demo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Author SunMeng
 * Date : 2016 十月 28
 */

public class BackgroundImageView extends RelativeLayout {
    
    public BackgroundImageView(Context context) {
        this(context, null);
    }

    public BackgroundImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BackgroundImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        
      
    }
}
