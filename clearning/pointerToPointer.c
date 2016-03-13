#include <stdio.h>

int main()
{
  int var;
  int *p;
  int **pr;
//  int ***pd;

  var = 10;
  p = &var;
  pr = &p;
 // pd = &pr;

  printf(" %d " ,**pr);
  //printf(" %d " ,***pd); // the prefix of * can go on and on
}
