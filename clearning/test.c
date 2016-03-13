#include <stdio.h>
#include <float.h> // needed for FLT_MAX ,FLT_MIN and FLT_DIG

int main()
{
  int test = 0;
  printf(" Hello World \n");
  printf(" Storage size of int (variable ref) : %ld \n", sizeof(test));
  printf(" Storage size of int (data type ref) : %ld \n", sizeof(int));
  printf(" Storage size of long (data type ref) : %ld \n", sizeof(long));
  printf(" Storage size of float (data type ref) : %ld \n", sizeof(float));
  printf(" Maximum float positive value %E \n", FLT_MAX);
  return 0;
}
