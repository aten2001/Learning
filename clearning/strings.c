#include <stdio.h>
#include <string.h>

int main()
{
  char hello[12] = "Hello";
  char world[12] = "World";
  char buff[12];
  int  len;

  strcpy(buff,hello);
  printf( " %s \n",buff);

  strcat(hello,world);
  printf(" %s \n ",hello);

  printf("  %d \n",strlen(buff));

  printf(" %c \n",strchr(hello,'e'));

  printf(" %s \n",strstr(hello,"ello"));


}

