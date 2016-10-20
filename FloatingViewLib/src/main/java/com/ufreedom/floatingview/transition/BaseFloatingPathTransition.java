package com.ufreedom.floatingview.transition;

/**
 * Author UFreedom
 * Date : 2016 十月 19
 */

public abstract  class BaseFloatingPathTransition  implements FloatingPathTransition {

    private PathPosition pathPosition;
    private float [] pathPositionGetter;
    
    public float getStartPathPosition(){
        return 0;
    }
    
    public float getEndPathPosition(){
        if (getFloatingPath() != null){
            return getFloatingPath().getPathMeasure().getLength();
        }
        return 0;
    }
    
    public PathPosition getFloatingPosition(float progress) {
        if (pathPosition == null){
            pathPosition = new PathPosition();
        }
        if (pathPositionGetter == null){
            pathPositionGetter = new float[2];
        }
        if (getFloatingPath() != null){
            getFloatingPath() .getPathMeasure().getPosTan(progress, pathPositionGetter, null);
            pathPosition.x = pathPositionGetter[0];
            pathPosition.y = pathPositionGetter[1];
        }
        return pathPosition;
    }
    
    
    
}
