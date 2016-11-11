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

import android.graphics.Path;
import android.graphics.PathMeasure;

/**
 * Author UFreedom
 * 
 */
public class FloatingPath {

    private Path mPath;
    private PathMeasure mPathMeasure;

    protected FloatingPath() {
        this.mPath = new Path();
    }
   
    protected FloatingPath(Path path) {
        this.mPath = path;
    }

    public static FloatingPath create(Path path, boolean forceClose) {
        FloatingPath floatingPath = new FloatingPath(path);
        floatingPath.mPathMeasure = new PathMeasure(path, forceClose);
        return floatingPath;
    }

    public Path getPath() {
        return mPath;
    }

    public PathMeasure getPathMeasure() {
        return mPathMeasure;
    }
}
