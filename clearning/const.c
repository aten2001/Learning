#include <stdio.h>

#define LENGTH 10 // macros
#define WIDTH  5
#define NEWLINE '\n'

int main()
{

   const NEWL_CONST = 'n';
   int area;

   area = LENGTH * WIDTH;
   printf("value of area : %d", area);
   printf("%c", NEWLINE);
   printf("%c", NEWL_CONST);

   return 0;
}
