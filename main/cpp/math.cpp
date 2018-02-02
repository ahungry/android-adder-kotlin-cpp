#include <jni.h>
#include <string>
#include <stdlib.h>
#include <stdio.h>

extern "C"
JNIEXPORT jstring

JNICALL
Java_com_ahungry_blub_MainActivity_addOne
(JNIEnv *env,
 jobject, /* this */
 jint y)
{
  int a = 1;
  char *msg = NULL;
  msg = (char *) malloc (sizeof (char) * 40);
  sprintf (msg, "%d", a + y);

  return env->NewStringUTF (msg);
}
