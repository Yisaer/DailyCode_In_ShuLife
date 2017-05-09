cmake_minimum_required(VERSION 3.7)
project(Threadpool)

set(CMAKE_C_STANDARD 11)

set(SOURCE_FILES main.c dbg.h threadpool.c threadpool.h)
set(CMAKE_C_FLAGS ${CMAKE_C_FLAGS} "-g -O2 -Wall -Wextra -pthread -DNDEBUG")
add_executable(Threadpool ${SOURCE_FILES})