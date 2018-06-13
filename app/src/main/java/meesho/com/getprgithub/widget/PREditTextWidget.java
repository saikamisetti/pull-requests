package meesho.com.getprgithub.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import meesho.com.getprgithub.R;
import meesho.com.getprgithub.util.Utils;

/**
 * Created by Sai on 13/06/18.
 */
public class PREditTextWidget extends LinearLayout {


    TextView tvLabel;
    EditText editText;
    TextView helpText;

    public PREditTextWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View.inflate(context, R.layout.pr_edit_text_widget, this);
        tvLabel = (TextView) findViewById(R.id.label);
        editText = (EditText) findViewById(R.id.edit_text);
        helpText = (TextView) findViewById(R.id.help_text);

        if (attrs != null) {
            TypedArray attrArray =
                    context.getTheme().obtainStyledAttributes(attrs, R.styleable.PREditTextWidget, 0, 0);
            String label = attrArray.getString(R.styleable.PREditTextWidget_label);
            tvLabel.setText(label);
            editText.setHint(attrArray.getString(R.styleable.PREditTextWidget_hintText));
            helpText.setText(attrArray.getString(R.styleable.PREditTextWidget_helpText));

            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    editText.setTextColor(Color.parseColor("#de000000"));
                    DrawableCompat.setTint(editText.getBackground(), Color.parseColor("#3f51b5"));
                    tvLabel.setTextColor(Color.parseColor("#3f51b5"));
                    helpText.setTextColor(Color.parseColor("#3f51b5"));
                    helpText.setText("");
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    editText.setTextColor(Color.parseColor("#de000000"));
                    DrawableCompat.setTint(editText.getBackground(), Color.parseColor("#3f51b5"));
                    tvLabel.setTextColor(Color.parseColor("#3f51b5"));
                    helpText.setTextColor(Color.parseColor("#3f51b5"));
                    helpText.setText("");
                } else {
                    editText.setTextColor(Color.parseColor("#66000000"));
                    DrawableCompat.setTint(editText.getBackground(), Color.parseColor("#8a000000"));
                    tvLabel.setTextColor(Color.parseColor("#8a000000"));
                    helpText.setTextColor(Color.parseColor("#8a000000"));
                    helpText.setText("");
                }
            }
        });

    }

    public String getEditText() {
        return this.editText.getText().toString();
    }

    public void setEditText(String s) {
        this.editText.setText(s);
    }

    public void setError(String s) {
        this.helpText.setText(s);
        this.helpText.setTextColor(Color.parseColor("#dee12b2b"));
        DrawableCompat.setTint(editText.getBackground(), Color.parseColor("#dee12b2b"));
        this.tvLabel.setTextColor(Color.parseColor("#dee12b2b"));
    }

    public void hideKeyboard() {
        Utils.hideKeyboard(this.editText);
    }

    public void setOnEditorActionListener(TextView.OnEditorActionListener onEditorActionListener) {
        this.editText.setOnEditorActionListener(onEditorActionListener);
    }

    public void setNonEditable(boolean isNonEditable) {
        if (this.editText != null) {
            if (isNonEditable) {
                //this.editText.setFocusable(false);
                this.editText.setClickable(true);
                this.editText.setEnabled(false);
            } else {
                //this.editText.setFocusable(true);
                this.editText.moveCursorToVisibleOffset();
                this.editText.setEnabled(true);
                this.editText.setClickable(true);
            }
        }
    }
}
