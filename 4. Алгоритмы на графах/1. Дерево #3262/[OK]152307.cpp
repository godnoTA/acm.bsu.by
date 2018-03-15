#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <string>
#include <cstdio>
#include <vector>
#include <string>
#include <sstream>
#include <iostream>
#include <fstream>
#include <stack>
#include <set>
#include <cmath>
#include <map>
#include <algorithm>
using namespace std;


int matrix[150][150];
vector<bool> visited(25000, false);
int n, i, j;
void depth(int v) {
    visited[v] = true;
	for (int i = 0; i < n; ++i) {
        if (matrix[v][i] != 0 && !visited[i])
        {
            depth(i);
        }
    }
}

int main()
{
    ifstream cin("input.txt");
    ofstream cout("output.txt");
    cin >> n;

    i = 0;
    while (i < n)
    {
        j = 0;
        while (j < n)
        {
            cin >> matrix[i][j];
            j++;
        }
        i++;
    }

    if (n == 1) {
        cout << "Yes";
        return 0;
    }

    i = 0;
    while (i < n)
    {
        int sum = 0;
        j = 0;
        while (j < n)
        {
            sum += matrix[i][j];
            j++;
        }
        if (sum > 0)
        {
            depth(i);
            break;
        }
        i++;
    }

    i = 0;
    while (i < n)
    {
        if (visited[i] == false)
        {
            cout << "No";
            return 0;
        }
        i++;
    }

    int m = 0;

    i = 0;
    while (i < n)
    {

        j = 0;
        while (j < n)
        {
            m += matrix[i][j];
            j++;
        }
        i++;
    }

    if (2 * n == m + 2)
        cout << "Yes" << endl;
    else
        cout << "No";
    return 0;
}