//
// Created by wxy on 2017/11/7.
//

#ifndef EXECUTOR_COMMAND_H
#define EXECUTOR_COMMAND_H
#include <iostream>
#include <vector>
#include "../util/definition.h"
#include "../json/json.h"
using namespace std;

class ExecutorCtl;
class Command
{
public:
    unsigned int code;
    virtual R_Result execute(ExecutorCtl* exeCtl, Json::Value& root){ return R_Success;};
};
#endif //EXECUTOR_COMMAND_H
