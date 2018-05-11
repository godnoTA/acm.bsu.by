#include <iostream>
#include <string>
#include <algorithm>
#include <vector>
#include <queue>
using namespace std;
int n, t, gr[100][100], res[100];
void BFS(int k) {
queue<int> z;z.push(k);
while (!z.empty()){
k = z.front();z.pop();
if (!res[k]) {res[k] = ++t;
for (int i = 0; i < n; i++) {
if (gr[k][i] && !res[i])z.push(i);}}}}
int main() {

#ifndef _MSC_VER
freopen("input.txt", "r", stdin);
freopen("output.txt", "w", stdout);
#endif

cin >> n;
for (int i = 0; i < n; i++) {
for (int j = 0; j < n; j++)
cin >> gr[i][j];}
for (int i = 0; i < n; i++)
if (!res[i])BFS(i);
for (int i = 0; i < n; i++)
cout << res[i] << ' ';
return 0;
}