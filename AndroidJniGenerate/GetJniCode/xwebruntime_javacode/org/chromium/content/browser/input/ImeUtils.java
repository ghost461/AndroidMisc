package org.chromium.content.browser.input;

import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.util.StringBuilderPrinter;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.CorrectionInfo;
import android.view.inputmethod.EditorInfo;
import java.util.Locale;
import org.chromium.base.ThreadUtils;

public class ImeUtils {
    public ImeUtils() {
        super();
    }

    static void checkCondition(String arg0, boolean arg1) {
        if(!arg1) {
            throw new AssertionError(arg0);
        }
    }

    static void checkCondition(boolean arg0) {
        if(!arg0) {
            throw new AssertionError();
        }
    }

    static void checkOnUiThread() {
        ImeUtils.checkCondition("Should be on UI thread.", ThreadUtils.runningOnUiThread());
    }

    public static void computeEditorInfo(int arg10, int arg11, int arg12, int arg13, int arg14, EditorInfo arg15) {
        arg15.inputType = 0xA1;
        if((arg11 & 2) != 0) {
            arg15.inputType |= 0x80000;
        }

        int v0 = 0x2002;
        int v1 = 3;
        int v2 = 209;
        int v3 = 17;
        int v4 = 0xE1;
        boolean v5 = true;
        int v6 = 0x20000;
        int v7 = 0x8000;
        int v8 = 2;
        if(arg12 != 0) {
            switch(arg12) {
                case 3: {
                    goto label_76;
                }
                case 4: {
                    goto label_74;
                }
                case 5: {
                    goto label_72;
                }
                case 6: {
                    goto label_70;
                }
                case 7: {
                    goto label_68;
                }
            }

            arg15.inputType |= v6;
            if((arg11 & 8) != 0) {
                goto label_77;
            }

            arg15.inputType |= v7;
            goto label_77;
        label_68:
            arg15.inputType = v0;
            goto label_77;
        label_70:
            arg15.inputType = v8;
            goto label_77;
        label_72:
            arg15.inputType = v2;
            goto label_77;
        label_74:
            arg15.inputType = v3;
            goto label_77;
        label_76:
            arg15.inputType = v1;
        }
        else if(arg10 != 1) {
            if(arg10 != 14) {
                if(arg10 == 15) {
                }
                else if(arg10 == v8) {
                    arg15.inputType = v4;
                    goto label_77;
                }
                else if(arg10 == 7) {
                    arg15.inputType = v3;
                    goto label_77;
                }
                else if(arg10 == 4) {
                    arg15.inputType = v2;
                    goto label_77;
                }
                else {
                    if(arg10 == 6) {
                        arg15.inputType = v1;
                    }
                    else if(arg10 == 5) {
                        arg15.inputType = v0;
                    }
                    else {
                    }

                    goto label_77;
                }
            }

            arg15.inputType |= v6;
            if((arg11 & 8) != 0) {
                goto label_77;
            }

            arg15.inputType |= v7;
        }
        else if((arg11 & 8) == 0) {
            arg15.inputType |= v7;
        }

    label_77:
        v0 = arg15.imeOptions;
        if((arg15.inputType & v6) != 0) {
        }
        else {
            v5 = false;
        }

        arg15.imeOptions = ImeUtils.getImeAction(arg10, arg11, arg12, v5) | v0;
        if((arg11 & 0x80) != 0) {
            arg15.inputType |= 0x1000;
        }
        else if((arg11 & 0x100) != 0) {
            arg15.inputType |= 0x2000;
        }
        else if((arg11 & 0x200) != 0) {
            arg15.inputType |= 0x4000;
        }

        if((arg11 & 0x1000) != 0) {
            arg15.inputType = v4;
        }

        arg15.initialSelStart = arg13;
        arg15.initialSelEnd = arg14;
    }

    static String getCorrectionInfoDebugString(CorrectionInfo arg0) {
        return arg0.toString();
    }

    static String getEditableDebugString(Editable arg5) {
        return String.format(Locale.US, "Editable {[%s] SEL[%d %d] COM[%d %d]}", arg5.toString(), Integer.valueOf(Selection.getSelectionStart(((CharSequence)arg5))), Integer.valueOf(Selection.getSelectionEnd(((CharSequence)arg5))), Integer.valueOf(BaseInputConnection.getComposingSpanStart(((Spannable)arg5))), Integer.valueOf(BaseInputConnection.getComposingSpanEnd(((Spannable)arg5))));
    }

    static String getEditorInfoDebugString(EditorInfo arg3) {
        StringBuilder v0 = new StringBuilder();
        arg3.dump(new StringBuilderPrinter(v0), "");
        return v0.toString();
    }

    private static int getImeAction(int arg1, int arg2, int arg3, boolean arg4) {
        int v0 = 3;
        if(arg3 == 0 && arg1 == v0) {
        }
        else if(arg4) {
            v0 = 1;
        }
        else if((arg2 & 0x400) != 0) {
            v0 = 5;
        }
        else {
            v0 = 2;
        }

        return v0;
    }
}

