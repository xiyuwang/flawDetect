//
// Created by wxy on 2017/11/8.
//
#include "commandStartDefect.h"

CommandStartDefect::CommandStartDefect(ExecutorCtl* exCtl):Command(exCtl)
{
}
R_Result CommandStartDefect::build(Json::Value& root)
{
    if(!root.isMember(COMMAND_TAG_NAME) || root[COMMAND_TAG_NAME].type() != Json::stringValue)
    {
        return R_FAIL_DEFECT_BUILD_MISTAKE;
    }
    string execName = root[COMMAND_TAG_NAME].asString();
    apExec = execCtl->getExecutorMap().at(execName);
    if(apExec == NULL)  return R_FAIL_DEFECT_BUILD_MISTAKE;

    map<string, shared_ptr<Product>> prodMap = apExec->getProductMap();

    return R_SUCCESS;
}
R_Result CommandStartDefect::execute(Json::Value& root)
{

}