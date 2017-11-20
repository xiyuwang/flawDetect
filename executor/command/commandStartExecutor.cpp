//
// Created by wxy on 2017/11/8.
//

#include "commandStartExecutor.h"

R_Result CommandStartExecutor::build(Json::Value& root)
{
    jsonRoot.copy(root);

    if(!jsonRoot.isMember(COMMAND_TAG_NAME) || jsonRoot[COMMAND_TAG_NAME].type() != Json::stringValue)
    {
        return R_Fail_Executor_Build_Mistake;
    }
    extrName = jsonRoot[COMMAND_TAG_NAME].asString();

    if(!jsonRoot.isMember(COMMAND_TAG_PROPRODUCTS) || jsonRoot[COMMAND_TAG_PROPRODUCTS].type() != Json::arrayValue)
    {
        return R_Fail_Executor_Build_Mistake;
    }

    Json::Value jsonProducts = jsonRoot[COMMAND_TAG_PROPRODUCTS];
    for (auto sub= jsonProducts.begin(); sub != jsonProducts.end(); sub++)
    {
        Product* pProd = buildProduct(*sub);
        if(pProd == NULL)  return R_Fail_Executor_Build_Mistake;
        pExecutor->addProduct(pProd);
    }


 //   exeCtl->getExecutorMap().insert(pair<string,Executor*>(extrName,pExtr));
}
Product* CommandStartExecutor::buildProduct(Json::Value& product)
{
    if(!product.isMember(COMMAND_TAG_NAME) || product[COMMAND_TAG_NAME].type() != Json::stringValue)
    {
        return NULL;
    }

    if(!jsonRoot.isMember(COMMAND_TAG_PROCESSORS) || jsonRoot[COMMAND_TAG_PROCESSORS].type() != Json::arrayValue)
    {
        return NULL;
    }

    string name  = product[COMMAND_TAG_NAME].asString();
    Product* pProduct = new Product(name);

    Json::Value jsonProcessors = jsonRoot[COMMAND_TAG_PROPRODUCTS];
    for (auto sub= jsonProcessors.begin(); sub != jsonProcessors.end(); sub++)
    {
        Processor* pProcessor = buildProcessor(*sub);
        if(pProcessor == NULL)  return NULL;
        pProduct->addProcessor(pProcessor);
    }

    return pProduct;
}
Processor* CommandStartExecutor::buildProcessor(Json::Value& processor)
{
    if(!processor.isMember(COMMAND_TAG_NAME) || processor[COMMAND_TAG_NAME].type() != Json::stringValue)
    {
        return NULL;
    }

    string name  = processor[COMMAND_TAG_NAME].asString();
    Processor* pProcessor = new Processor(name);

}
R_Result CommandStartExecutor::execute(ExecutorCtl* exeCtl,Json::Value& root)
{
    build(root);
    Json::Value exName = jsonRoot["name"];
    pExecutor = new Executor(exName.asString());
    exeCtl->getExecutorMap().insert(pair<string,Executor*>(exName.asString(),pExecutor));
    return R_Success;
}