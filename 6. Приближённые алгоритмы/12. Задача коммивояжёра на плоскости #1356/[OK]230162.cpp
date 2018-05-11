#include <iostream>
#include <fstream>
#include <algorithm>
#include <vector>
#include <cmath>

using namespace std;

struct pt {
    int  label;
    long long x, y;

    pt(  long long x1,   long long y1, int label1) {
        x = x1;
        y = y1;
        label = label1;
    }
};
double length(int ax,int ay, int bx,int by) {
    return sqrt((ax - bx) * (ax - bx) + (ay- by) * (ay - by));
}

vector<pt> vector1;

int main() {
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    int amount,n, x, y,counter=0;

    scanf("%d\n", &amount);
    n=amount;
    while (amount != 0) {
        scanf("%d %d\n", &x, &y);
        vector1.push_back({x , y, counter});
        counter++;
        amount--;
    }

    double arr[n][n];
    for (int i = 0 ; i< n;i++)
    {
        for (int j = 0 ; j < n;j++) {
            arr[i][j] =0;
        }
    }
    for (int i = 0 ; i< n;i++)
    {
        for (int j = 0 ; j < n;j++)
        {
            if ( i!=j)
                arr[i][j]=sqrt((vector1[i].x- vector1[j].x) *(vector1[i].x - vector1[j].x) + (vector1[i].y- vector1[j].y) * (vector1[i].y - vector1[j].y));
        }
    }


    const int INF = 1000000000;

    vector<int> sel ;

    double  res = INF;
    for (int vertex= 0 ; vertex<n; vertex++) {
        vector<bool> used (n);
        vector<int> sel_e ;
        int v = vertex;
        int k;
        double minimum = 0;
        for (int i = 0; i < n; ++i) {
            if (i == n - 1) {
                used[vertex] = false;
            }
            double min = INF;
            used[v] = true;
            for (int to = 0; to < n; ++to)
                if (!used[to] && arr[v][to] < min) {
                    min = arr[v][to];
                    k = to;
                }
            sel_e.push_back(k);
            minimum += min;
            v = k;
        }

        if (res> minimum) {
            res = minimum;
            sel = sel_e;
        }
    }
    printf("%.3f\n", res);
    for (int i = 0; i < n ; i ++)
    {
        cout <<sel[i]+1<< " ";
    }
    cout <<endl;

    return 0;
}