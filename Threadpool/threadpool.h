
/*
 * Copyright (C) Zhu Jiashun
 * Copyright (C) Zaver
 */

#ifndef THREADPOOL_H
#define THREADPOOL_H

#ifdef __cplusplus
extern "C" {
#endif

#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#include <stdint.h>
#include "dbg.h"

#define THREAD_NUM 8

typedef struct Y_task_s {
    void (*func)(void *);
    void *arg;
    struct Y_task_s *next;
} Y_task_t;

typedef struct {
    pthread_mutex_t lock;
    pthread_cond_t cond;
    pthread_t *threads;
    Y_task_t *head;
    int thread_count;
    int queue_size;
    int shutdown;
    int started;
} Y_threadpool_t;

typedef enum {
    Y_tp_invalid   = -1,
    Y_tp_lock_fail = -2,
    Y_tp_already_shutdown  = -3,
    Y_tp_cond_broadcast    = -4,
    Y_tp_thread_fail       = -5,

} Y_threadpool_error_t;

Y_threadpool_t *threadpool_init(int thread_num);

int threadpool_add(Y_threadpool_t *pool, void (*func)(void *), void *arg);

int threadpool_destroy(Y_threadpool_t *pool, int gracegul);

#ifdef __cplusplus
}
#endif

#endif
