//
// Created by wxy on 2017/11/5.
//

#ifndef EXECUTOR_CAMERA_H
#define EXECUTOR_CAMERA_H

#include <iostream>
#include <map>
#include <queue>
#include "util/thread.h"
#include "frame.h"

using namespace std;

class Camera: public Thread
{
public:
    string name;
    string driver;
    int mode;
    queue<Frame> outQueue;
    unsigned  int exposureTime;
    Camera(string name);
    void run();
};
#endif //EXECUTOR_CAMERA_H