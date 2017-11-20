//
// Created by wxy on 2017/11/5.
//

#ifndef EXECUTOR_DEFINITION_H
#define EXECUTOR_DEFINITION_H

#endif //EXECUTOR_DEFINITION_H

/* the return code definition */
typedef void (*AL_Handler)(void);
typedef int R_Result;
#define R_Success (0)
#define R_Fail (1)
#define R_Fail_Executor_State_Mistake (2)
#define R_Fail_Executor_Build_Mistake (3)

#define STATIC_CHECK_INTEVAL (100);

/* the command code definition */
#define COMMAND_EXECUTOR_START (1)
#define COMMAND_EXECUTOR_STOP (2)
#define COMMAND_DEFECT_START (3)
#define COMMAND_DEFECT_STOP (4)

#define COMMAND_TAG_NAME ("name")
#define COMMAND_TAG_PROPRODUCTS ("products")
#define COMMAND_TAG_PROCESSORS ("processors")