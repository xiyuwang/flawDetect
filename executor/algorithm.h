//
// Created by wxy on 2017/11/5.
//

#ifndef EXECUTOR_ALGORITHM_H
#define EXECUTOR_ALGORITHM_H

#include <iostream>
#include <map>
#include "util/definition.h"
using namespace std;

class Algorithm{
private:
    map<string, string> paraMap;
    AL_Handler alHandler;
public:
    string id;
public:
    Algorithm();
    ~Algorithm();

};
#endif //EXECUTOR_ALGORITHM_H