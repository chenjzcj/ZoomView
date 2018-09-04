package com.felix.zoomview.zoomview;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/**
 * view缩放
 *
 * @param <V>
 * @author Administrator
 */
public abstract class BaseZoomView<V extends View> {

    V view;

    /**
     * 空
     */
    private static final int NONE = 0;
    /**
     * 按下第一个点
     */
    private static final int DRAG = 1;
    /**
     * 按下第二个点
     */
    private static final int ZOOM = 2;

    /**
     * 屏幕上点的数量
     */
    private int mode = NONE;

    /**
     * 两指间的最小距离
     */
    private static final float MIN_DISTANCE = 10f;

    /**
     * 记录按下第二个点距第一个点的距离
     */
    private float oldDist;

    BaseZoomView(V view) {
        this.view = view;
        setTouchListener();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setTouchListener() {
        view.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        mode = DRAG;
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                        mode = NONE;
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        oldDist = spacing(event);
                        if (oldDist > MIN_DISTANCE) {
                            mode = ZOOM;
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (mode == ZOOM) {
                            // 正在移动的点距初始点的距离
                            float newDist = spacing(event);
                            if (newDist > oldDist) {
                                zoomOut();
                            }
                            if (newDist < oldDist) {
                                zoomIn();
                            }
                        }
                        break;
                    default:
                }
                return true;
            }

            /**
             * 求出2个触点间的 距离
             *
             * @param event MotionEvent
             * @return 2个触点间的 距离
             */
            private float spacing(MotionEvent event) {
                float x = event.getX(0) - event.getX(1);
                float y = event.getY(0) - event.getY(1);
                return (float) Math.sqrt(x * x + y * y);
            }
        });
    }

    /**
     * 缩小
     */
    protected abstract void zoomIn();

    /**
     * 放大
     */
    protected abstract void zoomOut();
}
