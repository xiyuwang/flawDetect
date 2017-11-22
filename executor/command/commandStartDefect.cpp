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
    map<string, shared_ptr<Product>>::iterator it;
    it = prodMap.begin();
    while(it != prodMap.end())
    {
        shared_ptr<Product> apProd = it->second;
        map<string, shared_ptr<Processor>> procMap = apProd->getProcessorMap();
        map<string, shared_ptr<Processor>>::iterator it2;
        it2 = procMap.begin();
        while(it2 != procMap.end())
        {
            processorVec.push_back(it2->second);
            it2++;
        }
        it ++;
    }

    return R_SUCCESS;
}
R_Result CommandStartDefect::execute(Json::Value& root)
{
    R_Result ret = build(root);
    if(ret != R_SUCCESS)  return ret;

    // start processors
    for(int i=0; i<processorVec.size(); i++)
    {
        bool bStarted = processorVec[i]->start();
        if(!bStarted) return R_FAIL_DEFECT_START_MISTAKE;
        processorVec[i]->join();
        apExec->getProdThreadVec().push_back(processorVec[i]->getThreadID());
    }
    return R_SUCCESS;
}