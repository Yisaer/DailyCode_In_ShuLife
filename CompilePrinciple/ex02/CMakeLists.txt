cmake_minimum_required(VERSION 3.6)
project(ex02)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES main.cpp help.h)
add_executable(ex02 ${SOURCE_FILES})