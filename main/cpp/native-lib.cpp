#include <jni.h>
#include <string>
#include "math.cpp"

extern "C"
JNIEXPORT jstring

JNICALL
Java_com_ahungry_blub_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
