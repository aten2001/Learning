#include <stdio.h>
#include <string.h>

union Data
{
  int i;
  float f;
  char str[10];
};

int main()
{
  union Data data;
  data.i=10;
  printf(" i val = %d \n",data.i);
  
  data.f=22.32;
  printf(" i val = %d \n",data.i); // becomes junk
  printf(" f val = %f \n",data.f);

  strcpy(data.str,"test");
  printf(" i val = %d \n",data.i); // becomes junk
  printf(" f val = %f \n",data.f); // becomes junk
  printf(" str val = %s \n",data.str);

  return 0;
}
