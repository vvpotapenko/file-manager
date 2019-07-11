package vvpotapenko.fmanager;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class Resources {

    private static ResourceBundle messages;

    static {
        messages = ResourceBundle.getBundle("labels", Locale.getDefault());
    }

    public static String getString(String key) {
        return messages.getString(key);
    }

    public static String getString(String key, Object... param) {
        return MessageFormat.format(messages.getString(key), param);
    }
}
