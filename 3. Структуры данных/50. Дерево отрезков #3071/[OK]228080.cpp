#include <iostream>
#include <cstdio>
#include <algorithm>

#define INF 100000000000
#define MAX_N 100000

using namespace std;

long long t_sum[MAX_N * 4];
long long t_min[MAX_N * 4];
long long t_max[MAX_N * 4];
long long t_delta[MAX_N * 4];

long long getsum(int i, int l, int r, int tl, int tr, long long add) {
    if (l > r) {
        return 0;
    }
    if (l == tl && r == tr) {
        return (t_delta[i] + add) * (r - l + 1) + t_sum[i]; 
    }
    int mid = tl + (tr - tl) / 2;
    return getsum(i * 2 + 1, l, min(mid, r), tl, mid, add + t_delta[i]) + 
           getsum(i * 2 + 2, max(mid + 1, l), r, mid + 1, tr, add + t_delta[i]);
}

long long getmin(int i, int l, int r, int tl, int tr) {
    if (l > r) {
        return INF;
    } 
    if (l == tl && r == tr) {
        return t_min[i];
    }
    int mid = tl + (tr - tl) / 2;
    return min(getmin(i * 2 + 1, l, min(mid, r), 
                      tl, mid),
               getmin(i * 2 + 2, max(mid + 1, l), r, 
                      mid + 1, tr)) + t_delta[i];
}

long long getmax(int i, int l, int r, int tl, int tr) {
    if (l > r) {
        return -INF;
    } 
    if (l == tl && r == tr) {
        return t_max[i];
    }
    int mid = tl + (tr - tl) / 2 ;
    return max(getmax(i * 2 + 1, l, min(mid, r), 
                      tl, mid),
               getmax(i * 2 + 2, max(mid + 1, l), r, 
                      mid + 1, tr)) + t_delta[i];
}

void add(int i, int l, int r, int tl, int tr, long long delta) {
    if (l > r) {
        return; 
    }
    if (l == tl && r == tr) {
        t_delta[i] += delta;
        t_max[i] += delta;
        t_min[i] += delta;
        return;
    }
    int mid = tl + (tr - tl) / 2;
    add(i * 2 + 1, l, min(r, mid), tl, mid, delta);
    add(i * 2 + 2, max(mid + 1, l), r, mid + 1, tr, delta);
    t_sum[i] += delta * (r - l + 1);
    t_min[i] = min(t_min[i * 2 + 1], t_min[i * 2 + 2]) + t_delta[i];
    t_max[i] = max(t_max[i * 2 + 1], t_max[i * 2 + 2]) + t_delta[i];
}

long long get(int i, int pos, int tl, int tr, long long add) {
    if (pos == tl && pos == tr) {
        return t_sum[i] + add + t_delta[i];
    }
    int mid = tl + (tr - tl) / 2;
    if (pos <= mid) {
        return get(i * 2 + 1, pos, tl, mid, add + t_delta[i]);
    } else {
        return get(i * 2 + 2, pos, mid + 1, tr, add + t_delta[i]);
    }
}

void modify(int i, int pos, long long value, int tl, int tr) {
    long long prev = get(0, pos, tl, tr, 0);
    add(0, pos, pos, tl, tr, -prev);
    add(0, pos, pos, tl, tr, value);
}

int main() {
    freopen("input.txt", "rb", stdin);
    freopen("output.txt", "wb", stdout);

    int n;
    scanf("%d", &n);
    int qtype;
    do {
        scanf("%d", &qtype);
        int a, b, i;
        long long v;
        switch (qtype) {
            case 1:
                scanf("%d%lld", &i, &v);
                modify(0, i, v, 0, n - 1);
                break;
            case 2:
                scanf("%d%d%lld", &a, &b, &v);
                add(0, a, b, 0, n - 1, v);
                break;
            case 3:
                scanf("%d%d", &a, &b);
                printf("%lld\n", getsum(0, a, b, 0, n - 1, 0));
                break;
            case 4:
                scanf("%d%d", &a, &b);
                printf("%lld\n", getmin(0, a, b, 0, n - 1));
                break;
            case 5:
                scanf("%d%d", &a, &b);
                printf("%lld\n", getmax(0, a, b, 0, n - 1));
                break;
            default:
                break;
        }
    } while (qtype);

    return 0;
}
