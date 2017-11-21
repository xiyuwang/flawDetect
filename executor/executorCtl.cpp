//
// Created by wxy on 2017/11/5.
//

#include "executorCtl.h"

ExecutorCtl::ExecutorCtl(void)
{
}
ExecutorCtl::~ExecutorCtl(void)
{
}


string ExecutorCtl::handleCommand(string command)
{
    char *endptr;
    char source[BUF_SIZE];
    int len = command.length();
    command.copy(source,len,0);
    source[len] = '\0';

    Json::Value jsonRoot;
    Json::CharReaderBuilder builder;
    builder["collectComments"] = false;
    JSONCPP_STRING errs;
    Json::CharReader* reader = builder.newCharReader();
    if (!reader->parse(command.data(), command.data() + command.size(), &jsonRoot, &errs)) //从jsonStr中读取数据到jsonRoot
    {
        return "{\"code\":1}";
    }

    if(!jsonRoot.isMember("msg"))
    {
        return "{\"code\":2}";
    }
    CommandFactory* pFactary = CommandFactory::instance(this);
    shared_ptr<Command> pCommand(pFactary->buildCommand(jsonRoot));
    if(pCommand==NULL)
    {
        return "{\"code\":3}";
    }
    R_Result ret = pCommand->execute(jsonRoot);

    if(ret != R_Success)
    {
        return "{\"code\":4}";
    }
    return "{\"code\":0}";
}
void ExecutorCtl::startServer()
{
#ifdef MINGW32
    int connectfd;
    struct sockaddr_in serveraddr;
    R_Result ret;

    //Winsows下启用socket
    WSADATA wsadata;
    if(WSAStartup(MAKEWORD(1,1),&wsadata)==SOCKET_ERROR)
    {
        cout <<"WSAStartup() fail\n";
        return;
    }

    //新建socket
    serverfd=socket(AF_INET,SOCK_STREAM,0);
    if(serverfd==-1)
    {
        cout << "create server fail\n";
        WSACleanup();           //释放套接字资源;
        return ;
    }
    //服务器套接字地址
    memset(&serveraddr,0,sizeof(serveraddr));
    serveraddr.sin_family = AF_INET;
    serveraddr.sin_port = htons(4999);
    serveraddr.sin_addr.s_addr = INADDR_ANY;

    //绑定端口，监听1024端口的任何请求
    printf("bind()\n");
    ret=bind(serverfd,(struct sockaddr*)&serveraddr,sizeof(serveraddr));
    if(ret==-1)
    {
        cout << "bind server socket fail\n";
        closesocket(serverfd);   //关闭套接字
        WSACleanup();           //释放套接字资源;
        return;
    }

    //监听端口，最大并发数10
    ret=listen(serverfd,SERVER_SOCKET_LISTENQ);
    if(ret==-1)
    {
        cout << "listen server socket fail\n";
        closesocket(serverfd);   //关闭套接字
        WSACleanup();           //释放套接字资源;
    }

    //接受请求
    while(true)
    {
        //接受请求
        connectfd=accept(serverfd,(struct sockaddr*)NULL,NULL);
        if(INVALID_SOCKET == connectfd)
        {
            cout << "accept failed!" << endl;
            closesocket(connectfd);   //关闭套接字
            continue;
        }

        char recvBbuf[BUF_SIZE];
        ZeroMemory(recvBbuf, BUF_SIZE);
        ret = recv(connectfd, recvBbuf, BUF_SIZE, 0);
        if (SOCKET_ERROR == ret)
        {
            cout << "recv failed!" << endl;
            closesocket(connectfd);   //关闭套接字
            return ;
        }
        string commandStr(recvBbuf);
        string respStr = handleCommand(commandStr);

        char sendBuf[BUF_SIZE];
        ZeroMemory(recvBbuf, BUF_SIZE);

        printf(sendBuf);
        ret = send(connectfd,respStr.c_str(), strlen(respStr.c_str()),0);
        if (ret < 0)
        {
            cout << "send response failed!" << endl;
            closesocket(connectfd);   //关闭套接字
            return ;
        }
        closesocket(connectfd);
    }
#else
    int connectfd;
    struct sockaddr_un serveraddr;
    R_Result ret;

    //新建socket
    serverfd=socket(AF_INET,SOCK_STREAM,0);
    if(serverfd==-1)
    {
        perror("create server socket fail");
        return ;
    }

    //create local IP and PORT
    memset(&serveraddr,0,sizeof(serveraddr));
    serveraddr.sun_family = AF_UNIX;
    strncpy(serveraddr.sun_path, UNIX_DOMAIN, sizeof(serveraddr.sun_path) - 1);
    unlink(UNIX_DOMAIN);

    //绑定端口，监听1024端口的任何请求
    cout << "bind()\n";
    ret=bind(serverfd,(struct sockaddr*)&serveraddr,sizeof(serveraddr));
    if(ret==-1)
    {
        perror("bind server socket fail");
        close(serverfd);
        unlink(UNIX_DOMAIN);
        return;
    }

    //监听端口，最大并发数10
    ret=listen(serverfd,SERVER_SOCKET_LISTENQ);
    if(ret==-1)
    {
        perror("listen server socket fail");
        close(serverfd);
        unlink(UNIX_DOMAIN);
        return;
    }

    //接受请求
    while(true)
    {
        //接受请求
        connectfd=accept(serverfd,(struct sockaddr*)NULL,NULL);
        if(connectfd < 0)
        {
            cout <<"can't listen client connect request"<<endl;
            close(connectfd);
            continue;
        }

        char buf[BUF_SIZE];
        for(i = 0; i < 4; i++)
        {
          memset(recv_buf, 0, 1024);
          int rcv_num = read(apt_fd, recv_buf, sizeof(recv_buf));
          printf("Message from client (%d) :%s\n", rcv_num, recv_buf);
        }
        string commandStr(buf);
        handleCommand(commandStr);
    }
#endif

}
R_Result ExecutorCtl::stopServer()
{
#ifdef MINGW32
    //Winsows下关闭socket
    closesocket(serverfd);
    WSACleanup();
#else
    close(serverfd);
#endif
    return R_Success;
}