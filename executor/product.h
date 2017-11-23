//
// Created by wxy on 2017/11/5.
//

#ifndef EXECUTOR_PRODUCT_H
#define EXECUTOR_PRODUCT_H

#include <iostream>
#include <map>
#include <memory>
#include "processor.h"
#include "camera.h"
using namespace std;

class Product
{
private:
    map<string, shared_ptr<Processor> > processorMap;
    shared_ptr<Camera> pCam;
    int camThreadId;
public:
    string name;
public:
    Product(string proName);
    R_Result setCamera(shared_ptr<Camera> pCamera);
    R_Result setCamThreadId(const int threadId);
    Camera* getCamera();
    R_Result addProcessor(shared_ptr<Processor> pObj);
    map<string, shared_ptr<Processor>>& getProcessorMap(){ return processorMap;};
    R_Result deleteProcessor(const string id);

};
#endif //EXECUTOR_PRODUCT_H