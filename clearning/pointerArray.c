#include <stdio.h>

const int MAX = 3;

int main()
{
  char *names[] = { //array of pointers where each pointer points to start of char array
    "Gokul",
    "Vik",
    "Preet"
  };

  int i = 0;

  for( i=0; i<MAX; i++)
  {
    printf("address => values of names[%d] = %x => %s\n",i,names[i],names[i]); // %x pull out address value store in it and %s runs through that address to get you the full string printed. This is because string have \0 null char at end symbolizing their termination
  }
  return 0;
}
