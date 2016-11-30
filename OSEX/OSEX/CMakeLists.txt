cmake_minimum_required(VERSION 3.6)
project(OSEX1)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES main.cpp ProcessControBlock.h ProcessList.h ProcessDispatch.h)
add_executable(OSEX1 ${SOURCE_FILES})