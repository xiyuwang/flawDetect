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
    vector<shared_ptr<Product>> productVec;
    CommandStartExecutor(ExecutorCtl* exCtl);
    Executor* build(Json::Value& root);
    R_Result execute(Json::Value& root);
private:
    Executor* pExec;
    Product* buildProduct(Json::Value& product);
    Processor* buildProcessor(Json::Value& processor);
    Camera* buildCamera(Json::Value& camera);
};
#endif //EXECUTOR_COMMANDSTARTEXECUTOR_H
