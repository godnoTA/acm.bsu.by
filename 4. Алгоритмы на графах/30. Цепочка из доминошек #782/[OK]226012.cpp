#include <iostream>
#include <fstream>
#include <vector>
using namespace std;

int n, m = 0;
vector< vector<int> > vec;
bool u[7];

void dfs (int v) {
	u[v] = true;
	m++;
	for(int i : vec[v]) {
        if(!u[i]) {
            dfs(i);
        }
	}
}

int main()
{
    ofstream cout("output.txt");
    ifstream cin("input.txt");
    cin >> n;
    for(int i = 0; i < 7; ++i) {
        vector<int> temp;
        vec.push_back(temp);
        u[i] = true;
    }
    for(int i = 0; i < n; ++i) {
        int a,b;
        cin >> a >> b;
        u[a] = u[b] = false;
        vec[a].push_back(b);
        vec[b].push_back(a);
    }
    n = 0;
    for(int i = 0; i < 7; ++i) {
        if(!u[i]) {
            n++;
        }
    }
    for(int i = 0; i < 7; ++i) {
        if(!u[i]) {
            dfs(i);
            break;
        }
    }
    if(n != m) {
        cout << "No" << endl;
    }
    else {
        bool t = true;
        for(int i = 0; i < 7; ++i) {
            if(vec[i].size() % 2 != 0) {
                t = false;
                break;
            }
        }
        if(t) {
            cout << "Yes" << endl;
        }
        else {
            cout << "No" << endl;
        }
    }
    return 0;
}
