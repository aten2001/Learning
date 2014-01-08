#include <stdio.h>

int main ()
{
  int var = 20;
  int *ip = NULL; //assign null value as default to avoid random address to be assigned

  ip = &var;

  printf("Address of var variable %x \n",&var);
  printf("Address stored in ip  variable %x \n", ip);
  printf("value ip is pointing to %d \n", *ip);
  return 0;
}
