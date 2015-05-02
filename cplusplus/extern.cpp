#include <iostream>
using namespace std;

extern int a, b ,c;
extern float f;

void func()
{

    int a, b, c;
    float f;

    a = 10;
    b = 20;
    c = a + b;
}
int main()
{
    int a, b, c;
    float f;

    c = 10;
    func();
    cout << c << endl ;

    f = 70.0/3.0;
    cout << f << endl;

    return 0;
}
