//
// Created by wxy on 2017/11/5.
//

#ifndef EXECUTOR_PROCESSOR_H
#define EXECUTOR_PROCESSOR_H

#include <iostream>
#include <map>
#include "util/thread.h"
#include "algorithm.h"
using namespace std;

class Processor: public Thread
{
private:
    Algorithm* pAlgorithm;
public:
    string name;
    Processor(string procName);
    void run();

};
#endif //EXECUTOR_PROCESSOR_H