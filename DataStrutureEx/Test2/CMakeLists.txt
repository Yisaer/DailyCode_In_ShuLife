cmake_minimum_required(VERSION 3.6)
project(Test2)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES main.cpp Noimal.h PolyNoimalList.h PolyNoimalListUtil.h PolyNoimalService.h)
add_executable(Test2 ${SOURCE_FILES})