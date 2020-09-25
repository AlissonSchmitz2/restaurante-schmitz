package br.com.unesc.pdm.restauranteschmitz.lib;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class LoggerPreferences {

    private static final String TAG = "LoggerPreferences";
    private static boolean shouldLogPut = false;
    private static boolean shouldLogGet = false;
    private Object targetObject;
    private SharedPreferences sharedPreferences;

    private LoggerPreferences(final Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        targetObject = this;
    }

    private LoggerPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        targetObject = this;
    }

    public static void init(final boolean logPut, final boolean logGet) {
        shouldLogPut = logPut;
        shouldLogGet = logGet;
    }

    public static LoggerPreferences get(final Context context) {
        return new LoggerPreferences(context);
    }

    public static LoggerPreferences get(final SharedPreferences preferences) {
        return new LoggerPreferences(preferences);
    }

    public LoggerPreferences with(Object targetClass) {
        targetObject = targetClass;
        return this;
    }

    public Editor edit() {
        return new Editor();
    }

    /**
     * CRIA OS MÉTODOS GET PARA ACESSO AO SHARED.
     */
    public String getString(final String key, final String defaultValue) {
        final String value = sharedPreferences.getString(key, defaultValue);
        if (shouldLogGet) {
            final String action = getAction(targetObject, key, value, "String");
            Log.d(TAG, action);
        }
        return value;
    }

    public int getInt(final String key, final int defaultValue) {
        final int value = sharedPreferences.getInt(key, defaultValue);
        if (shouldLogGet) {
            final String action = getAction(targetObject, key, ""+value, "Int");
            Log.d(TAG, action);
        }
        return value;
    }

    private String getAction(final Object targetObject, final String key, final String value, final String dataType) {
        return
        (
            "Activity: ["+targetObject+"], "
            +"Tipo de dado: ["+dataType+"], "
            +"Usando a chave: ["+key+"], "
            +"Obtendo o valor: ["+value+"]"
        );
    }

    /**
     * Inner-class para controle do editor de preferencias.
     */
    public class Editor {

        private SharedPreferences.Editor editor = sharedPreferences.edit();

        private Editor() {

        }

        public Editor putString(final String key, final String value) {
            if (shouldLogPut) {
                final String action = getAction(targetObject, key, value, "String");
                Log.d(TAG, action);
            }

            editor.putString(key, value);
            return this;
        }

        public Editor putInt(final String key, final int value) {
            if (shouldLogPut) {
                final String action = getAction(targetObject, key, ""+value, "Int");
                Log.d(TAG, action);
            }

            editor.putInt(key, value);
            return this;
        }

        private String getAction(final Object targetObject, final String key, final String value, final String dataType) {
            return
                    (
                            "Activity: ["+targetObject+"], "
                                    +"Tipo de dado: ["+dataType+"], "
                                    +"Usando a chave: ["+key+"], "
                                    +"Obtendo o valor: ["+value+"]"
                    );
        }

        public void apply() {
            editor.apply();
        }

        public void commit() {
            editor.commit();
        }

        public void clear() {
            editor.clear();
        }

        public void remove(final String key) {
            editor.remove(key);
        }

    }

}
