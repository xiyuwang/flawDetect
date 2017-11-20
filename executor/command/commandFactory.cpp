//
// Created by wxy on 2017/11/12.
//

#include "commandFactory.h"
CommandFactory*  CommandFactory::pInstance = NULL;
CommandFactory* CommandFactory::instance()
{
    if(CommandFactory::pInstance == NULL)
    {
        CommandFactory::pInstance = new CommandFactory();
    }
    return CommandFactory::pInstance;
};
Command* CommandFactory::buildCommand(Json::Value command)
{
    unsigned int jsonMsg = command["msg"].asUInt();
    switch (jsonMsg) {
        case COMMAND_EXECUTOR_START: {
            CommandStartExecutor *pCommand = new CommandStartExecutor();
            return pCommand;
        }
        case COMMAND_EXECUTOR_STOP: {
            CommandStopExecutor *pCommand = new CommandStopExecutor();
            return pCommand;
        }
        case COMMAND_DEFECT_START:
        {
            CommandStartDefect *pCommand = new CommandStartDefect();
            return pCommand;
        }
        case COMMAND_DEFECT_STOP:
        {
            CommandStopDefect* pCommand = new CommandStopDefect();
            return pCommand;
        }
        default :
            break;
    }
}