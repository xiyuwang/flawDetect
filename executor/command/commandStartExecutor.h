//
// Created by wxy on 2017/11/8.
//

#ifndef EXECUTOR_COMMANDSTARTEXECUTOR_H
#define EXECUTOR_COMMANDSTARTEXECUTOR_H

#include <memory>
#include "command.h"
#include "../executor.h"
#include "../executorCtl.h"
#include "../product.h"

class CommandStartExecutor: public Command
{
public:
    Json::Value jsonRoot;
    string extrName;
    vector<shared_ptr<Product>> productVec;
    R_Result build(Json::Value& root);
    R_Result execute(ExecutorCtl* exeCtl, Json::Value& root);
private:
    ExecutorCtl* exeCtl;
    Executor* pExecutor;
    Product* buildProduct(Json::Value& product);
    Processor* buildProcessor(Json::Value& processor);
};
#endif //EXECUTOR_COMMANDSTARTEXECUTOR_H
