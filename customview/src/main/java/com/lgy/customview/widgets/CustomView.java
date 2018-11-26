package com.lgy.customview.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomView extends TextView {
    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @androidx.annotation.Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, @androidx.annotation.Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
