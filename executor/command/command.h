//
// Created by wxy on 2017/11/7.
//

#ifndef EXECUTOR_COMMAND_H
#define EXECUTOR_COMMAND_H
#include <iostream>
#include <memory>
#include <vector>
#include "../util/definition.h"
#include "../json/json.h"
using namespace std;

class ExecutorCtl;
class Command
{
public:
    unsigned int code;
    shared_ptr<ExecutorCtl> execCtl;
    Command(ExecutorCtl* exCtl);
    ExecutorCtl* getExecutorCtl(){ return execCtl.get();};
    virtual R_Result execute(Json::Value& root){ return R_SUCCESS;};
};
#endif //EXECUTOR_COMMAND_H
