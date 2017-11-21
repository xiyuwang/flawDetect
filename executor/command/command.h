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
    virtual R_Result execute(Json::Value& root){ return R_Success;};
};
#endif //EXECUTOR_COMMAND_H
