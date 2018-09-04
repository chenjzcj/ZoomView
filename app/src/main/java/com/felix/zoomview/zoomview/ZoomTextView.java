package com.felix.zoomview.zoomview;

import android.util.TypedValue;
import android.widget.TextView;

/**
 * 可以缩放的TextView
 */
public class ZoomTextView extends BaseZoomView<TextView> {
    /**
     * 最小字体
     */
    private static final float MIN_TEXT_SIZE = 20f;

    /**
     * 最大字体
     */
    private static final float MAX_TEXT_SIZE = 100.0f;

    /**
     * 缩放比例
     */
    private float scale;

    /**
     * 设置字体大小(单位是px)
     */
    private float textSize;

    public ZoomTextView(TextView view, float scale) {
        super(view);
        this.scale = scale;
        textSize = view.getTextSize();
    }

    /**
     * 放大
     */
    @Override
    protected void zoomOut() {
        textSize += scale;
        textSize = (textSize > MAX_TEXT_SIZE) ? MAX_TEXT_SIZE : textSize;
        //不能使用view.setTextSize(textSize);因为这个方法默认以sp为单位,此处需要指定以px为单位设置
        view.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
    }

    /**
     * 缩小
     */
    @Override
    protected void zoomIn() {
        textSize -= scale;
        textSize = (textSize < MIN_TEXT_SIZE) ? MIN_TEXT_SIZE : textSize;
        view.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
    }
}
