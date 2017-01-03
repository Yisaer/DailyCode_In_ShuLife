cmake_minimum_required(VERSION 3.6)
project(P11)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES main.cpp Node.h)
add_executable(P11 ${SOURCE_FILES})