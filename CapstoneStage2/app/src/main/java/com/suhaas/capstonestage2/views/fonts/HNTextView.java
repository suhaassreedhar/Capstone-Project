package com.suhaas.capstonestage2.views.fonts;


import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class HNTextView extends TextView {

    private final TypefaceFactory typeFaceFactory;

    public HNTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.typeFaceFactory = new TypefaceFactory();
        initTypeface(context, attrs);
    }

    public HNTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.typeFaceFactory = new TypefaceFactory();
        initTypeface(context, attrs);
    }

    private void initTypeface(Context context, AttributeSet attrs) {
        if (isInEditMode()) {
            return;
        }

        Typeface typeface = typeFaceFactory.createFrom(context, attrs);
        setTypeface(typeface);
    }

}
