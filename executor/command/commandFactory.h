//
// Created by wxy on 2017/11/12.
//

#ifndef EXECUTOR_COMMANDFACTORY_H
#define EXECUTOR_COMMANDFACTORY_H

#include <iostream>
#include <vector>
#include <memory>
#include "../util/definition.h"
#include "../json/json.h"
#include "../executorCtl.h"
#include "command.h"
#include "commandStartDefect.h"
#include "commandStopDefect.h"
#include "commandStartExecutor.h"
#include "commandStopExecutor.h"

using namespace std;
class CommandFactory
{
private:
    static CommandFactory*  pInstance;
    shared_ptr<ExecutorCtl> apExecCtl;
    CommandFactory(ExecutorCtl* exCtl);
public:
    static CommandFactory* instance(ExecutorCtl* exCtl);
    Command* buildCommand(Json::Value command);
};
#endif //EXECUTOR_COMMANDFACTORY_H
