cmake_minimum_required(VERSION 3.6)
project(OSEX3)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES main.cpp Page.h PageList.h PageService.h)
add_executable(OSEX3 ${SOURCE_FILES})