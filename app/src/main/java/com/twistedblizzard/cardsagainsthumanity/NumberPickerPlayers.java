package com.twistedblizzard.cardsagainsthumanity;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.DialogPreference;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

public class NumberPickerPlayers extends DialogPreference {
    private NumberPicker mPicker;
    private int mNumber = 0;

    public NumberPickerPlayers(Context context) {
        this(context, null, 0);
    }

    public NumberPickerPlayers(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberPickerPlayers(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setPositiveButtonText(android.R.string.ok);
        setNegativeButtonText(android.R.string.cancel);
    }

    @Override
    protected void onBindView(@NonNull View view) {
        super.onBindView(view);

        TextView title = (TextView) view.findViewById(android.R.id.title);
        if (title != null) {
            title.setSingleLine(false);
            title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        }
    }

    @Override
    protected View onCreateDialogView() {
        mPicker = new NumberPicker(getContext());
        mPicker.setMinValue(2);
        mPicker.setMaxValue(8);
        // should be after setMinValue and setMaxValue calls
        mPicker.setValue(mNumber);
        return mPicker;
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        if (positiveResult) {
            // needed when user edits the text field and clicks OK
            mPicker.clearFocus();
            setValue(mPicker.getValue());
        }
    }

    @Override
    protected void onSetInitialValue(boolean restoreValue, Object defaultValue) {
        setValue(restoreValue ? getPersistedInt(mNumber) : (Integer) defaultValue);
    }

    public int getValue() {
        return mNumber;
    }

    private void setValue(int value) {
        if (shouldPersist()) {
            persistInt(value);
        }

        if (value != mNumber) {
            mNumber = value;
            notifyChanged();
        }
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        return a.getInt(index, 0);
    }
}