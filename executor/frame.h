//
// Created by wxy on 2017/11/6.
//

#ifndef EXECUTOR_FRAME_H
#define EXECUTOR_FRAME_H

class Frame
{
private:
    unsigned int sequence;
    unsigned  int width;
    unsigned int height;
    unsigned short format;
    unsigned int second;
    unsigned int usecond;
    unsigned int size;
    char* pData;
};
#endif //EXECUTOR_FRAME_H
