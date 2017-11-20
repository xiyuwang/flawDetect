//
// Created by wxy on 2017/11/5.
//

#ifndef EXECUTOR_PRODUCT_H
#define EXECUTOR_PRODUCT_H

#include <iostream>
#include <map>
#include <memory>
#include "processor.h"
using namespace std;

class Product
{
private:
    map<string, Processor* > processorMap;
public:
    string name;
public:
    Product(string proName);
    R_Result addProcessor(Processor* pObj);
    R_Result deleteProcessor(const string id);

};
#endif //EXECUTOR_PRODUCT_H