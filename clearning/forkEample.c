
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

//int i = 0; // gobal vairable is copied to the new child process
void subroutine(char* name)
{
    const int NUM = 5;
    for(int i = 0; i< NUM; i++){
        sleep(rand() %4);
        printf("Done pass %d for %s \n",i,name);
    }
}

int main(int argc, char* argv[])
{
   // returns pid of this process
    printf(" I am : %d\n", (int) getpid());

    pid_t pid = fork();
    srand((int) pid);
    printf(" fork returned : %d\n", (int) pid);
    //error case
    if(pid < 0)
    {
        printf("fork failed");
    }
    if (pid == 0)
    {
        // scope for child thread
        printf(" child pid %d \n", (int) getpid());
        subroutine("child");
        printf(" Child exiting \n");
        exit(42); //child thread exits here
    }

    subroutine("parent");
    printf(" parent pid %d \n", (int) getpid());
    int status = 0;
    /* if call to this wait happens late.. and
     * child is already finished then the process
     * is listed as defunct process i.e. child process
     * is waiting for master to call wait
     * this cause return val of child is still kept
     * in kernel memory space to be used by master
     *
     */
    pid_t childPid = wait(&status); //wait till child terminators
    printf(" parent ending as child %d finished  with status %d \n",(int) childPid,status);
    int childRevVal = WEXITSTATUS(status); //other macros present here
    printf(" val %d\n",childRevVal);
    return 0;
}
