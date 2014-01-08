#include <stdio.h>
#include <string.h>

struct Books
{
  char title[50];
  char author[50];
  char subject[100];
  int book_id;
};

void printBook(struct Books book);
void ptrintBook2(struct Books *ptr); // pointer refrence to structure

int main ()
{
  struct Books Book1;

  strcpy(Book1.title,"HeadFirst");
  Book1.book_id=123;

  printBook(Book1);
  printBook2(&Book1);

}

void printBook(struct Books book)
{
  printf(" print by value \n ");
  printf(" title = %s \n",book.title);
}

void printBook2(struct Books *book)
{
  printf(" print by name \n ");
  printf(" title = %s \n",book->title);
}
