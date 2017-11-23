//
// Created by wxy on 2017/11/5.
//

#ifndef EXECUTOR_DEFINITION_H
#define EXECUTOR_DEFINITION_H

#endif //EXECUTOR_DEFINITION_H

/* the return code definition */
typedef void (*AL_Handler)(void);
typedef int R_Result;
#define R_SUCCESS (0)
#define R_FAIL (1)
#define R_FAIL_EXEC_STAT_MISTAKE (2)
#define R_FAIL_EXEC_BUILD_MISTAKE (3)
#define R_FAIL_EXEC_START_MISTAKE (4)
#define R_FAIL_DEFECT_BUILD_MISTAKE (5)
#define R_FAIL_DEFECT_START_MISTAKE (6)

#define STATIC_CHECK_INTEVAL (100);

/* the command code definition */
#define COMMAND_EXECUTOR_START (1)
#define COMMAND_EXECUTOR_STOP (2)
#define COMMAND_DEFECT_START (3)
#define COMMAND_DEFECT_STOP (4)

#define COMMAND_TAG_NAME ("name")
#define COMMAND_TAG_PROPRODUCTS ("products")
#define COMMAND_TAG_PROCESSORS ("processors")
#define COMMAND_TAG_CAMERA ("camera")