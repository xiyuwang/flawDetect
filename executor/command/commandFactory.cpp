//
// Created by wxy on 2017/11/12.
//

#include "commandFactory.h"
CommandFactory*  CommandFactory::pInstance = NULL;

CommandFactory::CommandFactory(ExecutorCtl* exCtl)
{
    this->apExecCtl = shared_ptr<ExecutorCtl>(exCtl);
}
CommandFactory* CommandFactory::instance(ExecutorCtl* exCtl)
{
    if(CommandFactory::pInstance == NULL)
    {
        CommandFactory::pInstance = new CommandFactory(exCtl);
    }
    return CommandFactory::pInstance;
};
Command* CommandFactory::buildCommand(Json::Value command)
{
    unsigned int jsonMsg = command["msg"].asUInt();
    switch (jsonMsg) {
        case COMMAND_EXECUTOR_START: {
            CommandStartExecutor *pCommand = new CommandStartExecutor(apExecCtl.get());
            return pCommand;
        }
        case COMMAND_EXECUTOR_STOP: {
            CommandStopExecutor *pCommand = new CommandStopExecutor(apExecCtl.get());
            return pCommand;
        }
        case COMMAND_DEFECT_START:
        {
            CommandStartDefect *pCommand = new CommandStartDefect(apExecCtl.get());
            return pCommand;
        }
        case COMMAND_DEFECT_STOP:
        {
            CommandStopDefect* pCommand = new CommandStopDefect(apExecCtl.get());
            return pCommand;
        }
        default :
            break;
    }
}