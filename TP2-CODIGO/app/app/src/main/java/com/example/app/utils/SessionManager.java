package com.example.app.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static String TAG = SessionManager.class.getSimpleName();
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "Grupo16";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public boolean saveStringData(String key, String value) {
        this.editor.putString(key, value);
        return this.editor.commit ();
    }

    public String getStringData(String key) {
        return this.pref.getString(key, "");
    }
}