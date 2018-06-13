package meesho.com.getprgithub.util;

import android.content.Context;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by Sai on 13/06/18.
 */
public class Utils {
    /**
     * EditText show or hide password.
     */
    public static void editTextPassword(EditText editText, boolean showPassword) {
        if (editText == null) {
            return;
        }

        if (showPassword) {
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            editText.setSelection(editText.getText().length());
        } else {
            editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            editText.setSelection(editText.getText().length());
        }
    }

    /**
     * Hide keyboard if shown to the user.
     */
    public static void hideKeyboard(EditText editText) {
        if (editText != null) {
            InputMethodManager imm =
                    (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }
}
