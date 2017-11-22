//
// Created by wxy on 2017/11/8.
//

#ifndef EXECUTOR_COMMANDSTARTDEFECT_H
#define EXECUTOR_COMMANDSTARTDEFECT_H

#include "./command.h"
#include "../executor.h"
#include "../executorCtl.h"
#include "../product.h"

class CommandStartDefect: public Command
{
public:
    shared_ptr<Executor> apExec;
    vector<shared_ptr<Processor> > processorVec;
    CommandStartDefect(ExecutorCtl* exCtl);
    R_Result build(Json::Value& root);
    R_Result execute(Json::Value& root);
};
#endif //EXECUTOR_COMMANDSTARTDEFECT_H
