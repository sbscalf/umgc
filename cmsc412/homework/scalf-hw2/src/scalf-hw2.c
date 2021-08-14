#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

/*
 * An exercise for tracking PIDs between parent and child processes.
 * 
 * Variables gpid, ppid, and cpid are for grandparent PID, parent PID,
 * and child PID, respectively.
 *
 * Output is done in the desired order due to parent processes waiting
 * for child processes to finish with `wait(&status)
 */
int main() {
    int gpid, ppid, cpid, status;
    gpid = getpid();
    ppid = fork();
    switch(ppid)
    {
    case -1: 
        printf("Fork error"); 
        break;
    case 0:  
        /* 
         * ppid is being assigned again here so the parent and child
         * processes have access to the value assigned on line 14.
         *
         * `ppid = getpid();` immediately after fork() would not work, because
         *  the grandparent process would assign its pid to ppid.
         */
        ppid = getpid();
        cpid = fork();
        switch(cpid)
        {
            case -1:
                printf("Fork error");
                break;
            case 0:
                printf("I am the child process C and my pid is %d. ",
                    getpid());
                printf("My parent P has pid %d. ", ppid);
                printf("My grandparent G has pid %d.\n", gpid);
                break;
            default:
                wait(&status);
                printf("I am the parent process P and my pid is %d.", ppid);
                printf(" My parent G has pid %d.\n", gpid);
        }
        break;
    default: 
        /* this code is executed by the parent process */
        wait(&status);
        printf("I am the grandparent process G and my pid is %d.\n", gpid);
    }
}
