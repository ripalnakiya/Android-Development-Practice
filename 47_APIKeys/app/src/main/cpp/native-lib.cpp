#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_a47apikeys_ApiKeyManager_getApiKey(JNIEnv *env, jobject thiz) {
    std::string API_KEY = "JohnGamer5645";
    return env->NewStringUTF(API_KEY.c_str());
}