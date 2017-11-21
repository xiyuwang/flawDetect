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
public:
    string name;
public:
    Product(string proName);
    R_Result setCamera(shared_ptr<Camera> pCamera);
    Camera* getCamera();
    R_Result addProcessor(shared_ptr<Processor> pObj);
    R_Result deleteProcessor(const string id);

};
#endif //EXECUTOR_PRODUCT_H