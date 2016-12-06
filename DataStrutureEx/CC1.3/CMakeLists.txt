cmake_minimum_required(VERSION 3.6)
project(CC1_3)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES main.cpp Node.h List.h Seq.h)
add_executable(CC1_3 ${SOURCE_FILES})