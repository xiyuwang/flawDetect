//
// Created by wxy on 2017/11/7.
//

#include "command.h"
#include "../executorCtl.h"

Command::Command(ExecutorCtl* exCtl)
{
    shared_ptr<ExecutorCtl> apExecCtl(exCtl);
    this->execCtl = apExecCtl;
}