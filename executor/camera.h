//
// Created by wxy on 2017/11/5.
//

#ifndef EXECUTOR_CAMERA_H
#define EXECUTOR_CAMERA_H

#include <iostream>
#include <map>
#include "util/thread.h"

using namespace std;

class Camera: public Thread
{
public:
    string name;
    string driver;
    int mode;
    unsigned  int exposureTime;
    Camera(string name);
    void run();
};
#endif //EXECUTOR_CAMERA_H