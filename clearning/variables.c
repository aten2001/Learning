#include <stdio.h>

// Variable declaration:
extern int a, b;
extern int c;
extern float f;

int main ()
{
  /* variable definition: */
  int a, b;
  int c;
  float f;
 
  /* actual initialization */
  a = 10;
  b = 20;

  int d;  
  c = a + b;
  printf("value of c : %d \n", c);
  d = c;
  printf("value of d : %d \n", d);
  f = 70.0/3.0;
  printf("value of f : %f \n", f);
 
  return 0;
}
