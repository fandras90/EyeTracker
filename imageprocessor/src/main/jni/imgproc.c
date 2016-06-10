#include <jni.h>
#include <stddef.h>

JNIEXPORT void JNICALL
Java_com_vicker_imageprocessor_ImageProcessor_processImageNative(JNIEnv *env, jobject instance,
                                                                 jint width, jint height,
                                                                 jbyteArray yuv_, jintArray rgba_) {
    jbyte *yuv = (*env)->GetByteArrayElements(env, yuv_, NULL);
    jint *rgba = (*env)->GetIntArrayElements(env, rgba_, NULL);

    // TODO

    (*env)->ReleaseByteArrayElements(env, yuv_, yuv, 0);
    (*env)->ReleaseIntArrayElements(env, rgba_, rgba, 0);
}