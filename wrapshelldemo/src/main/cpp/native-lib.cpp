//
// Created by xqli on 20-3-21.
//

#include <jni.h>
#include <string>
#include <malloc.h>
#include <stdlib.h>
#include <android/log.h>

extern "C" JNIEXPORT jstring JNICALL
Java_com_demo_wrapshell_MainActivity_stringFromJNI(JNIEnv *env,jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}


extern "C"
JNIEXPORT void JNICALL
Java_com_demo_wrapshell_MainActivity_testNativeLeak(JNIEnv *env, jobject instance) {

    __android_log_print(ANDROID_LOG_ERROR, "cww", " Java_com_demo_wrapshell_MainActivity_testNativeLeak ");
    char *p;

    p = (char *)malloc(1024 * 1024);

    // memset(NULL, 97, 1024 * 1024);
    memset(p, 97, 1024 * 1024);

    // 不断的申请内存，但是不释放。
    // free(p);
}



extern "C"
JNIEXPORT jint JNI_OnLoad(JavaVM *vm, void *reserved) {
    char *pathvar;
    pathvar = getenv("LIBC_DEBUG_MALLOC_OPTIONS");
    __android_log_print(ANDROID_LOG_ERROR, "cww", "LIBC_DEBUG_MALLOC_OPTIONS: %s", pathvar);

    return JNI_VERSION_1_6;
}