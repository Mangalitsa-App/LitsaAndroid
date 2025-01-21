package com.example.litsaandroid.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

public class TokenStorage {
        private static final String SHARED_PREFS_NAME = "secure_prefs";
        private static final String TOKEN_KEY = "auth_token";
        private final SharedPreferences sharedPreferences;
        UserResponse userResponse;

        public TokenStorage(Context context) throws Exception {
            saveToken(userResponse.getToken());

            MasterKey masterKey = new MasterKey.Builder(context)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build();

            sharedPreferences = EncryptedSharedPreferences.create(
                    context,
                    SHARED_PREFS_NAME,
                    masterKey,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
            Log.i("TOKEN",getToken());
        }

        public void saveToken(String token) {
            sharedPreferences.edit().putString(TOKEN_KEY, token).apply();
        }

        public String getToken() {
            return sharedPreferences.getString(TOKEN_KEY, null);
        }

        public void clearToken() {
            sharedPreferences.edit().remove(TOKEN_KEY).apply();
        }
}
