#include <stdio.h>
#include <time.h>

void getSeconds(unsigned long *params);

int main()
{
  unsigned long sec;
  getSeconds(&sec);
  printf(" seconds is %ld \n ", sec);

  int *p;
  int i;

  p = getRandom();
  for( i=0; i<10; ++i)
  {
    printf("*(p + [%d]) : %d \n",i, *(p+i));
  }
  return 0;
}

void getSeconds(unsigned long *params)
{
  *params = time( NULL ); //asign value to varible addressed by this pointer
  return;
}

int * getRandom()
{
  static int r[10];
  int i;

  /* set the seed */
  srand( (unsigned) time( NULL ));
  for( i=0; i<10; ++i)
  {
    r[i]=rand(); // assign random values
  }

  return r;
}
