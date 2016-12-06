cmake_minimum_required(VERSION 3.6)
project(CC1_1)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES main.cpp)
add_executable(CC1_1 ${SOURCE_FILES})