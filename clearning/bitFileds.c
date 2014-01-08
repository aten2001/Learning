#include <stdio.h>

struct
{
  unsigned int val : 3; // max value of decimal 7
} obj;

int main()
{
  printf(" Size of val : %d \n",sizeof(obj));
  obj.val = 4;
  printf("obj.val = %d \n",obj.val);

  obj.val = 7;
  printf("obj.val = %d \n",obj.val);

  obj.val = 8;
  printf("obj.val = %d \n",obj.val); // prints 0 as 8 width is greater than 3 bits i.e. 1000 - the last 3 bits are 000;

}

