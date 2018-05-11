#pragma warning(disable : 4996)
#include<iostream>
#include<fstream>
#include<vector>
#include<set>
#include<map>
#include<algorithm>
#include<ctime>
using namespace std;
const int INF = 1e9;

int main(){
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	srand(time(NULL));
	freopen("input.in", "r", stdin);
	freopen("output.out", "w", stdout);

	int n, m;
	cin >> n >> m;
	vector<vector<int>> d(n, vector<int>(n, INF));
	for (int i = 0; i < n; i++) d[i][i] = 0;
	
	vector<vector<int>> g(n, vector<int>(n));

	int first, second, len;
	for (int i = 0; i < m; i++)
		cin >> first >> second >> len, d[first - 1][second - 1] = len, d[second - 1][first - 1] = len, g[first - 1][second - 1]=len, g[second - 1][first - 1]=len;



	for (int k = 0; k<n; ++k)
	for (int i = 0; i<n; ++i)
	for (int j = 0; j<n; ++j)
		d[i][j] = min(d[i][j], d[i][k] + d[k][j]);



	vector<int> dist(n,0);
	for (int i = 0;  i < n; i++)
	for (int j = 0; j < n; j++)
		dist[i] += d[i][j];

	int min = 0;

	for (int i = 1; i < n; i++)
	if (dist[i] < dist[min]) min = i;


	/*for (int i = 0; i < n; i++)
	if (i != min && dist[i] == dist[min] && g[i][min]!=0){
		cout << min + 1 <<" "<< i + 1 <<" "<< g[i][min] / 2;
		return 0;
	}*/
	
	if (n == 2) cout << 1 << " " << 2 <<" "<< g[0][1] / 2; 
	else

	cout << min+1 << " " << dist[min];

	return 0;
}