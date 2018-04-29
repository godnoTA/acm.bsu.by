#include <iostream>
#include <fstream>
#include <queue>
#include <algorithm>
#include <string>
#include <stack>
#include <list>

#define WHITE 0
#define GREY 1
#define BLACK 2

using namespace std;

int inf = 2000000;
int mas[2000][2000];

int flow[2000][2000], color[2000], pred[2000];
int head, tail;
int q[2001];

void enque(int x) {
    q[tail] = x;
    tail++;
    color[x] = GREY;
}
int de() {
    int x = q[head];
    head++;
    color[x] = BLACK;
    return x;
}

int bfs(int start, int end, int n) {
    int u, v;
    for (u = 0; u < n; u++)
        color[u] = WHITE;

    head = 0;
    tail = 0;
    enque(start);
    pred[start] = -1;
    while (head != tail) {
        u = de();
        for (v = 0; v < n; v++) {
            if (color[v] == WHITE && (mas[u][v] - flow[u][v]) > 0) {
                enque(v);
                pred[v] = u;
            }
        }
    }
        if (color[end] == BLACK)
            return 0;
        else
            return 1;
}

int max_flow(int source, int stock, int n) {
    int i, j, u;
    int maxflow = 0;
    for (i = 0; i < n; i++) {
        for (j = 0; j < n; j++)
            flow[i][j] = 0;
    }
    while (bfs(source, stock, n) == 0) {
        int delta = inf;
        for (u = n - 1; pred[u] >= 0; u = pred[u])
            delta = min(delta, (mas[pred[u]][u] - flow[pred[u]][u]));
        for (u = n - 1; pred[u] >= 0; u = pred[u]) {
            flow[pred[u]][u] += delta;
            flow[u][pred[u]] -= delta;
        }
        maxflow += delta;
    }
    return maxflow;
}

int main() {
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    int number;
    int n1, n2, n3, all;


    scanf("%d%d%d\n", &n1, &n2, &n3);
    all = n1 + n2 + n3;


    for (int i = 1; i <= n1; i++) {
        mas[0][i] = 1;
    }
    for (int i = n1 + n2 + 1; i <= all; i++) {
        mas[i][1+n1+(2*n2)+n3] = 1;
    }

    for (int i = n1 + 1; i <= n1 + n2; i++) {
        scanf("%d\n", &number);
        mas[i][n2 + n3 + i] = number;
    }

    for (int i = 1; i <= n1; ++i) {
        int k;
        scanf("%d", &k);
        while (k != 0) {
            scanf("%d", &number);
            mas[i][number] = inf;
            --k;
        }
        scanf("\n");
    }

    for (int i = n1 + 1; i <= n1 + n2; ++i) {
        int k;
        scanf("%d", &k);
        while (k != 0) {
            scanf("%d", &number);
            mas[i + n2 + n3][number] = inf;
            --k;
        }
        scanf("\n");
    }

    printf("%d", max_flow(0,  1+n1+(2*n2)+n3, 2+n1+(2*n2)+n3));
    return 0;
}