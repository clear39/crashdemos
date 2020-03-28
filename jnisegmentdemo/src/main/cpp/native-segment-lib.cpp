//
// Created by xqli on 20-3-21.
//

#include <jni.h>
#include <string>
#include <malloc.h>
#include <stdlib.h>
#include <android/log.h>
#include <unistd.h>


//自定义 .mytext 段
__attribute__((section(".mytext")))


jstring getString(JNIEnv* ) __attribute__((section(".mytext")));

jstring getString(JNIEnv* env){
    __android_log_print(ANDROID_LOG_ERROR, "cww", "%s",__func__);
    return env->NewStringUTF("Native method return");
}


void init_getString() __attribute__((constructor));


unsigned long getLibAddr(){
    unsigned long ret = 0;
    char name[] = "libdemo.so";
    char buf[4096],*temp;
    int pid;
    FILE *fp;
    pid = getpid();
    sprintf(buf,"/proc/%d/maps",pid);
    fp = fopen(buf,"r");
    if(fp == nullptr){
        __android_log_print(ANDROID_LOG_ERROR, "cww", "%s open file is fail.",__func__);
        goto __error;
    }

    while (fgets(buf,sizeof(buf),fp)){
        // strstr 判断 name 是否为 buf子 串
        if(strstr(buf,name)){
            temp = strtok(buf,"-");// 分解字符串为一组，“-”为分割字符串
            ret = strtoul(temp, nullptr,16);
        }
    }

__error:
    fclose(fp);
    return  ret;

}

extern "C" JNIEXPORT jstring JNICALL
Java_com_demo_jnisegment_SegmentActivity_stringFromJNI(JNIEnv *env,jobject /* this */) {
    __android_log_print(ANDROID_LOG_ERROR, "cww", "%s",__func__);
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}






extern "C"
JNIEXPORT jint JNI_OnLoad(JavaVM *vm, void *reserved) {
    __android_log_print(ANDROID_LOG_ERROR, "cww", "%s",__func__);
    char *pathvar;
    pathvar = getenv("LIBC_DEBUG_MALLOC_OPTIONS");
    __android_log_print(ANDROID_LOG_ERROR, "cww", "LIBC_DEBUG_MALLOC_OPTIONS: %s", pathvar);

    return JNI_VERSION_1_6;
}