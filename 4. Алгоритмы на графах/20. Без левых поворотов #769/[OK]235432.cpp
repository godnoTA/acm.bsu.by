#include <iostream>
#include <vector>
#include <cmath>
#include <queue>
using namespace std;


struct  coord{
    long long x,y;
};


vector<coord> vertex{10002};
deque<long long> myQueue;
long long arr[10001][10001];
bool array[10001][10001]={false};

void dfs(long long ver1,long long ver2,long long ver3, int n) {
    if(ver2==ver3)
    {
        printf("Yes\n");
        myQueue.push_back(ver3);
        while(!myQueue.empty())
        {
            long long val = myQueue.front();
            myQueue.pop_front();
            printf("%lld ",val);
        }
        exit(0);
    }

    //array[ver1][ver2]=false;
    for (int i = 1; i <= n; i++) {
        if (i != ver2 ) {
            if (arr[ver2][i] == 1 && array[ver2][i]) {

                long long  D = (vertex[i].x - vertex[ver1].x) * (vertex[ver2].y - vertex[ver1].y) - (vertex[i].y - vertex[ver1].y) * (vertex[ver2].x - vertex[ver1].x);
                if (D < 0)
                    continue;
                if (D == 0) {
                    double  k=sqrt(pow(abs(vertex[i].x - vertex[ver2].x),2) +pow( (abs(vertex[i].y - vertex[ver2].y)),2)) ;
                    double f=(sqrt(pow(abs(vertex[ver1].x - vertex[ver2].x ), 2) + pow(abs(vertex[ver1].y - vertex[ver2].y), 2)) + sqrt(pow(abs(vertex[i].x - vertex[ver1].x),2) + pow(abs(vertex[i].y - vertex[ver1].y),2)));
                    if (k==f)
                        continue;
                }
                myQueue.push_back(ver2);
                array[ver2][i] = false;
                dfs(ver2, i, ver3, n);
                myQueue.pop_back();
            }
        }
    }
}


int main() {
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    int  m ;
    int n;
    scanf("%d%d\n", &n, &m);
    long long x1,y1,x2,y2,vertex1,vertex2;
    for (int i =0 ; i <m;i++)
    {
        scanf("%lld%lld%lld%lld%lld%lld\n", &x1, &y1,&x2, &y2,&vertex1,&vertex2);
        vertex[vertex1].x=x1;
        vertex[vertex1].y=y1;
        vertex[vertex2].x=x2;
        vertex[vertex2].y=y2;
        arr[vertex1][vertex2]=1;
        arr[vertex2][vertex1]=1;
        array[vertex2][vertex1]= true;
        array[vertex1][vertex2]=true;
    }

    long long ver1,ver2;
    scanf("%lld%lld\n", &ver1, &ver2);
    vertex[0].x= vertex[ver1].x;
    vertex[0].y=vertex[ver1].y-1;

    array[0][ver1]=false;
    dfs(0,ver1,ver2,n);
    printf("No\n");

}