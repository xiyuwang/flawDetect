//
// Created by wxy on 2017/11/8.
//

#ifndef EXECUTOR_COMMANDSTOPDEFECT_H
#define EXECUTOR_COMMANDSTOPDEFECT_H

#include "command.h"

class CommandStopDefect: public Command
{
public:
    CommandStopDefect(ExecutorCtl* exCtl);
};
#endif //EXECUTOR_COMMANDSTOPDEFECT_H
