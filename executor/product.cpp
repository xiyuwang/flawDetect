//
// Created by wxy on 2017/11/5.
//

#include "product.h"
Product::Product(string proName)
{
    this->name = proName;
}
R_Result Product::addProcessor(shared_ptr<Processor> pObj)
{
    try {
        processorMap.insert(pair<string, shared_ptr<Processor>>(pObj->name, pObj));
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
R_Result Product::setCamera(shared_ptr<Camera> pCamera)
{
    this->pCam = pCamera;
}
Camera* Product::getCamera()
{
    return this->pCam.get();
}