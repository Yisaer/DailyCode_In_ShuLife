cmake_minimum_required(VERSION 3.7)
project(TinyHtttpd)

set(CMAKE_C_STANDARD 11)

set(SOURCE_FILES main.c)
add_executable(TinyHtttpd ${SOURCE_FILES})