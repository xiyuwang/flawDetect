//
// Created by wxy on 2017/11/8.
//

#ifndef EXECUTOR_COMMANDSTOPEXECUTOR_H
#define EXECUTOR_COMMANDSTOPEXECUTOR_H

#include "command.h"

class CommandStopExecutor: public Command
{
public:
    CommandStopExecutor(ExecutorCtl* exCtl);
};
#endif //EXECUTOR_COMMANDSTOPEXECUTOR_H
