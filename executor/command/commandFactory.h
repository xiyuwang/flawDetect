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
    CommandFactory(){};
public:
    static CommandFactory* instance();
    Command* buildCommand(Json::Value command);
};
#endif //EXECUTOR_COMMANDFACTORY_H
