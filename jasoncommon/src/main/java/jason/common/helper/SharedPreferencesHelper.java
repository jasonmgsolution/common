package jason.common.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by jasonmg_0302 on 2016-03-08.
 */
public class SharedPreferencesHelper {

    private SharedPreferences sp;
    private SharedPreferences.Editor spEditor;

    public SharedPreferencesHelper(Context context) {
        super();
        sp = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public SharedPreferencesHelper(Context context, String name) {
        super();
        sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void put(String key, String value) {
        spEditor.putString(key, value).apply();
    }

    public void put(String key, int value) {
        spEditor.putInt(key, value).apply();
    }

    public void put(String key, boolean value) {
        spEditor.putBoolean(key, value).apply();
    }


    public String getString(String key) {
        return sp.getString(key, null);
    }

    public String getString(String key, String defVal){
        return sp.getString(key, defVal);
    }

    public int getInt(String key) {
        return sp.getInt(key, 0);
    }

    public boolean getBoolean(String key) {
        return sp.getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defVal) {
        return sp.getBoolean(key, defVal);
    }

    public void remove(String key) {
        spEditor.remove(key).apply();
    }

    public void clear() {
        spEditor.clear().apply();
    }

    public void logPreferenceAllValue() {

        Map<String, ?> all = sp.getAll();
        Iterator<String> keyIterator = all.keySet().iterator();

        while (keyIterator.hasNext()) {
            String key = keyIterator.next();
            Object value = all.get(key);

            Log.i("logPreferenceAllValue", key + " : " + value);
        }

    }

}
