//
// Created by wxy on 2017/11/5.
//

#include "executor.h"

Executor::Executor(string name)
{
    exName = name;
}
R_Result Executor::start()
{
    this->state = started;
    return R_Success;
}
R_Result Executor::stop()
{
    this->state = stoped;
    return R_Success;
}
eStates Executor::getState()
{
    return this->state;
}
R_Result Executor::setState(eStates st)
{
    this->state = st;
    return R_Success;
}

R_Result Executor::addProduct(shared_ptr<Product> pObj)
{
    try {
        productMap.insert(pair<string, shared_ptr<Product>>(pObj->name,pObj));
    }
    catch (exception ex){
        return R_Fail;
    }
    return R_Success;
}
R_Result Executor::deleteProduct(const string id)
{
    productMap.erase(id);
    return R_Success;
}