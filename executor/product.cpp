//
// Created by wxy on 2017/11/5.
//

#include "product.h"
Product::Product(string proName)
{
    this->name = proName;
}
R_Result Product::addProcessor(Processor* pObj)
{
    try {
        processorMap.insert(pair<string, Processor*>(pObj->name, pObj));
    }
    catch (exception ex){
        return R_Fail;
    }
    return R_Success;
}
R_Result Product::deleteProcessor(const string id)
{
    processorMap.erase(id);
    return R_Success;
}