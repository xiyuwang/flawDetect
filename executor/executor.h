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
    stoped=0,
    execStarted=1,
    defectStarted=2
};

class Executor
{
private:
    string exName;
    map<string, shared_ptr<Product>> productMap;
    vector<pthread_t> procThreadVec;
    Schedule* pSchedule;
    vector<Frame> frames;
    enum eStates state;
public:
    string id;
public:
    Executor(string name);
    eStates getState();
    R_Result setState(eStates state);

    R_Result addProduct(shared_ptr<Product> pObj);
    map<string, shared_ptr<Product>>& getProductMap(){ return productMap;};
    R_Result deleteProduct(const string id);

    vector<pthread_t>& getProdThreadVec(){ return procThreadVec;};
};
#endif //EXECUTOR_EXECUTOR_H