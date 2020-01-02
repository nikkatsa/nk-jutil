#include <sys/time.h>
#include "com_nikoskatsanos_time_NativeNanoClock.h"

#define BILLION  1000000000
#define THOUSAND 1000

long currentTimeNanos(void);

long currentTimeNanos()
{
    struct timeval now;
    gettimeofday(&now, NULL);
    return now.tv_sec * BILLION + now.tv_usec * THOUSAND;
}

JNIEXPORT jlong JNICALL Java_com_nikoskatsanos_time_NativeNanoClock_currentNanos(JNIEnv *env, jobject obj)
{
    return currentTimeNanos();
}

JNIEXPORT jlong JNICALL JavaCritical_com_nikoskatsanos_time_NativeNanoClock_currentNanos()
{
    return currentTimeNanos();
}