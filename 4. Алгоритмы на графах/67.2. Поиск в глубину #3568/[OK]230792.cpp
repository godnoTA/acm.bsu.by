#include <iostream>
#include <string>
#include <algorithm>
#include <vector>
using namespace std;
int n, t;
int gr[100][100];
int res[100];
void DFS(int k) {res[k] = ++t;for (int i = 0; i < n; i++) {if (gr[k][i] && !res[i])DFS(i);}}
int main() {
#ifndef _MSC_VER
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);
#endif
	cin >> n;
	for (int i = 0; i < n; i++) {for (int j = 0; j < n; j++)cin >> gr[i][j];}
	for (int i = 0; i < n; i++) {if (!res[i])DFS(i);}
	for (int i = 0; i < n; i++)cout << res[i] << ' ';}