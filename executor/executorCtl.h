//
// Created by wxy on 2017/11/5.
//

#ifndef EXECUTOR_EXECUTORCTL_H
#define EXECUTOR_EXECUTORCTL_H

#include <iostream>
#include <map>
#include <queue>
#include "util/definition.h"
#include "util/thread.h"
#include "util/gason.h"
#include "command/command.h"
#include "command/commandFactory.h"
#include "executor.h"
#include "product.h"
#include "schedule.h"
#include "json/json.h"

#define MINGW32

#ifdef MINGW32
#include <winsock2.h>
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#pragma comment(lib, "ws2_32.lib")
#else
#include <sys/socket.h>
#include <arpa/inet.h>
#include <sys/un.h>
#include <unistd.h>
#define UNIX_DOMAIN "/tmp/UNIX.domain"
#endif

#define MAXLINE 1024
#define BUF_SIZE 1024
#define SERVER_SOCKET_LISTENQ 10
using namespace std;

#define RESULT_CODE_SUCESS (0)
#define RESULT_CODE_PARSE_FAIL (1)
#define RESULT_CODE_FORMAT_MISTAKE (2)
class ExecutorCtl
{
private:
    int serverfd;
    bool started;
    string handleCommand(string command);
public:
    map<string, shared_ptr<Executor>> execMap;

    ExecutorCtl();
    ~ExecutorCtl();
    void startServer();
    R_Result stopServer();
    map<string, shared_ptr<Executor>>& getExecutorMap(){ return execMap;};
    Executor* getExecutor(string key){ return execMap[key].get();};
};

#endif //EXECUTOR_EXECUTORCTL_H