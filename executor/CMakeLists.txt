cmake_minimum_required(VERSION 3.7)
project(executor)

set(CMAKE_CXX_STANDARD 11)


set(SOURCE_FILES main.cpp executorCtl.cpp executorCtl.h camera.cpp camera.h product.cpp product.h processor.cpp processor.h executor.cpp executor.h schedule.cpp schedule.h algorithm.cpp algorithm.h util/utils.cpp util/utils.h util/definition.h util/thread.cpp util/thread.h util/gason.cpp json/jsoncpp.cpp json/json.h json/json-forwards.h util/gason.h frame.cpp frame.h command/command.cpp command/command.h command/commandStartDefect.cpp command/commandStartExecutor.cpp command/commandStartExecutor.h command/commandStartDefect.h command/commandStopExecutor.cpp command/commandStopExecutor.h command/commandStopDefect.cpp command/commandStopDefect.h command/commandFactory.cpp command/commandFactory.h)

add_executable(executor ${SOURCE_FILES})
if(MINGW)
    target_link_libraries(executor ws2_32)
endif()