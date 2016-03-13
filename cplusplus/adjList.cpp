
#include<iostream>
#include<vector>

using namespace std;

vector <int> adj[10];

int main()
{
    int x, y , nodes, edges;
    cin >> nodes;
    cin >> edges;
    for(int i = 0; i< edges; ++i)
    {
        cin >> x >> y;
        adj[x].push_back(y);
    }


    for (int i=0 ; i<= nodes; ++i)
    {
        cout << " index " << i << " adj[i].size() " << adj[i].size() <<endl;
    }
}
