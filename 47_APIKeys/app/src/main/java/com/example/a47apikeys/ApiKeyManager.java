package com.example.a47apikeys;

public class ApiKeyManager {
    static {
        System.loadLibrary("native-lib");
    }
    public native String getApiKey();
}
