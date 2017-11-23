//
// Created by wxy on 2017/11/8.
//

#include "commandStartExecutor.h"
CommandStartExecutor::CommandStartExecutor(ExecutorCtl* exCtl):Command(exCtl)
{

}
Executor* CommandStartExecutor::build(Json::Value& root)
{
    jsonRoot.copy(root);

    if(!root.isMember(COMMAND_TAG_NAME) || root[COMMAND_TAG_NAME].type() != Json::stringValue)
    {
        return NULL;
    }

    if(!root.isMember(COMMAND_TAG_PROPRODUCTS) || root[COMMAND_TAG_PROPRODUCTS].type() != Json::arrayValue)
    {
        return NULL;
    }

    shared_ptr<Executor> apExec(new Executor(root[COMMAND_TAG_NAME].asString()));

    Json::Value jsonProducts = root[COMMAND_TAG_PROPRODUCTS];
    for (auto sub= jsonProducts.begin(); sub != jsonProducts.end(); sub++)
    {
        Product* pProd = buildProduct(*sub);
        if(pProd == NULL)  return NULL;
        shared_ptr<Product> apProd(pProd);
        apExec->addProduct(apProd);
    }

    return apExec.get();
 //   exeCtl->getExecutorMap().insert(pair<string,Executor*>(extrName,pExtr));
}
Product* CommandStartExecutor::buildProduct(Json::Value& product)
{
    if(!product.isMember(COMMAND_TAG_NAME) || product[COMMAND_TAG_NAME].type() != Json::stringValue)
    {
        return NULL;
    }

    if(!product.isMember(COMMAND_TAG_PROCESSORS) || product[COMMAND_TAG_PROCESSORS].type() != Json::arrayValue)
    {
        return NULL;
    }

    if(!product.isMember(COMMAND_TAG_CAMERA) || product[COMMAND_TAG_CAMERA].type() != Json::objectValue)
    {
        return NULL;
    }

    // create product instance
    string name  = product[COMMAND_TAG_NAME].asString();
    shared_ptr<Product> apProd(new Product(name));

    // create camera
    Camera* pCamera = buildCamera(product[COMMAND_TAG_CAMERA]);
    if(pCamera == NULL) return NULL;
    apProd->setCamera(shared_ptr<Camera>(pCamera));
    // create processor
    Json::Value jsonProcessors = product[COMMAND_TAG_PROPRODUCTS];
    for (auto sub= jsonProcessors.begin(); sub != jsonProcessors.end(); sub++)
    {
        Processor* pProc = buildProcessor(*sub);
        if(pProc == NULL)  return NULL;
        shared_ptr<Processor> apProc(pProc);
        apProd->addProcessor(apProc);
    }

    return apProd.get();
}
Camera* CommandStartExecutor::buildCamera(Json::Value& camera)
{
    if(!camera.isMember(COMMAND_TAG_NAME) || camera[COMMAND_TAG_NAME].type() != Json::stringValue)
    {
        return NULL;
    }

    string name  = camera[COMMAND_TAG_NAME].asString();
    Camera* pCamera = new Camera(name);
    shared_ptr<Camera> ppCamera(pCamera);
    return ppCamera.get();
}
Processor* CommandStartExecutor::buildProcessor(Json::Value& processor)
{
    if(!processor.isMember(COMMAND_TAG_NAME) || processor[COMMAND_TAG_NAME].type() != Json::stringValue)
    {
        return NULL;
    }

    string name  = processor[COMMAND_TAG_NAME].asString();
    shared_ptr<Processor> apProc(new Processor(name));
    return apProc.get();
}
R_Result CommandStartExecutor::execute(Json::Value& root)
{
    Executor* pExec = build(root);
    if(pExec == NULL)
        return R_FAIL_EXEC_BUILD_MISTAKE;

    Json::Value exName = root["name"];
    shared_ptr<Executor> apExec(pExec);

    map<string, shared_ptr<Product>> prodMap = apExec->getProductMap();
    map<string, shared_ptr<Product>>::iterator it;
    it = prodMap.begin();
    while(it != prodMap.end())
    {
        shared_ptr<Product> apProd = it->second;
        Camera* pCam = apProd->getCamera();
        bool bSuccess = pCam->start();
        if(!bSuccess)
            return R_FAIL_EXEC_START_MISTAKE;
        apProd->setCamThreadId(pCam->getThreadID()) ;
        it ++;
    }

    execCtl->getExecutorMap().insert(pair<string,shared_ptr<Executor>>(exName.asString(),apExec));
    return R_SUCCESS;
}