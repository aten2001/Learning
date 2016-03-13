#include <stdio.h>

const int MAX = 3;

void incr();
void comparison();
int main()
{
  incr();
  comparison();
  return 0;
}

void incr()
{
  int var[] = { 10, 100, 200 };
  int i, *ptr; // ptr is pointer to integer type

  ptr = var; // ptr and var point to first index of array

  for(i = 0; i < MAX; i++)
  {
    printf(" var[%d] = %x => %d \n",i,ptr,*ptr);
    ptr++;
    /* if ptr = 1000 at first 1000 to 10004 (4 bytes in case of int) is used by the first digint 10, then 1004 to 1008 by 100 and so on.. hence ptr++ works well as its of type int. 
     * If we declared the pointer as char *ptr .. then we would run into problems
     * */

  }

}

void comparison()
{
  int var[] = { 10, 100, 200 };
  int i, *ptr;

  ptr = var;
  i = 0;

  while( ptr <= &var[MAX - 1] ) // pointer used in comparison
  {
    printf(" var[%d] = %x => %d \n",i,ptr,*ptr);
    ptr++; // incr address by 4 bytes as ptr type is int
    i++;
  }
}
