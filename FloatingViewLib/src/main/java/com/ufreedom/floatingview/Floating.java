package com.ufreedom.floatingview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;

import com.facebook.rebound.SpringSystem;
import com.ufreedom.floatingview.transition.FloatingTransition;
import com.ufreedom.floatingview.transition.YumFloating;


/**
 * Author UFreedom
 * Date : 2016 十月 17
 */

public class Floating {


    private FloatingDecorView mFloatingDecorView;
    private SpringSystem mSpringSystem;
    
    public Floating(Activity activity){

        if (activity == null){
            throw new NullPointerException("Activity should not be null");
        }
        
        ViewGroup rootView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
        View decorView = rootView.findViewById(R.id.floating_decor);
        if (decorView instanceof  FloatingDecorView){
            mFloatingDecorView = (FloatingDecorView) decorView;
        }else {
            mFloatingDecorView = new FloatingDecorView(activity);
            mFloatingDecorView.setId(R.id.floating_decor);
            rootView.addView(mFloatingDecorView);
        }
        
        if (mSpringSystem == null){
            mSpringSystem = SpringSystem.create();
        }
        
    }
    

    /*public void detach() {
        
        if (mFloatingDecorView == null) return;
        
        mFloatingDecorView.removeAllViews();
        ViewGroup rootView = (ViewGroup) mActivity.findViewById(Window.ID_ANDROID_CONTENT);
        rootView.removeView(mFloatingDecorView);
        mFloatingDecorView = null;
        mActivity = null;
    }*/

    public void startFloating(FloatingElement floatingElement) {
        
        
        View anchorView = floatingElement.anchorView;
        View targetView = floatingElement.targetView;
        
        if (targetView == null){
            targetView = LayoutInflater.from(anchorView.getContext()).inflate(floatingElement.targetViewLayoutResId,mFloatingDecorView,false);
        }
        
        Rect rect = new Rect();
        anchorView.getGlobalVisibleRect(rect);
        int[] location = new int[2];
        mFloatingDecorView.getLocationOnScreen(location);
        rect.offset(-location[0], -location[1]);
        
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec((1 << 30) - 1, View.MeasureSpec.AT_MOST);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec((1 << 30) - 1, View.MeasureSpec.AT_MOST);
        targetView.measure(widthMeasureSpec,heightMeasureSpec);
        
        int topMargin = rect.top  + ((anchorView.getMeasuredHeight() - targetView.getMeasuredHeight()) / 2) + floatingElement.offsetY;
        int leftMargin = rect.left  + ((anchorView.getMeasuredWidth() - targetView.getMeasuredWidth()) / 2) + floatingElement.offsetX;

        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.topMargin = topMargin;
        lp.leftMargin = leftMargin;
        mFloatingDecorView.addView(targetView,lp);
        
        FloatingTransition floatingAnimator = floatingElement.floatingTransition;
        floatingAnimator.applyFloating(new YumFloating(targetView, mSpringSystem));
        
    }
    
    
    
    private class FloatingDecorView extends FrameLayout{
        
        public FloatingDecorView(Context context) {
            this(context, null);
        }

        public FloatingDecorView(Context context, AttributeSet attrs) {
            this(context, attrs, 0);
        }

        public FloatingDecorView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            
        }
    }
    
}
