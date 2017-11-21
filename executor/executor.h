//
// Created by wxy on 2017/11/5.
//

#ifndef EXECUTOR_EXECUTOR_H
#define EXECUTOR_EXECUTOR_H

#include <iostream>
#include <map>
#include <vector>
#include "product.h"
#include "schedule.h"
#include "frame.h"
using namespace std;

enum eStates{
    started=1,
    stoped
};

class Executor
{
private:
    string exName;
    map<string, shared_ptr<Product>> productMap;
    Schedule* pSchedule;
    vector<Frame> frames;
    enum eStates state;
public:
    string id;
public:
    Executor(string name);
    R_Result start();
    R_Result stop();
    eStates getState();
    R_Result setState(eStates state);

    R_Result addProduct(shared_ptr<Product> pObj);
    R_Result deleteProduct(const string id);
};
#endif //EXECUTOR_EXECUTOR_H