#include <iostream>
#include <fstream>
#include <algorithm>
#include <vector>

using namespace std;

const int INF = 1000000000;
struct road
{
	int A;
	int B;
	int P;
	int C;
};

struct MyStruct
{
	int v;
	int price;
	int e;
};

int n, m;
int *payForTown;
bool *visit;
int coffers;
road *aRoad;

int main() {
	fstream f("campaign.in");
	f >> n >> m;
	payForTown = new int[n];
	visit = new bool[n];
	for (int i = 0; i < n; i++) {
		f >> payForTown[i];
		visit[i] = false;
	}
	aRoad = new road[m];
	for (int i = 0; i < m; i++){
		f >> aRoad[i].A;
		f >> aRoad[i].B;
		f >> aRoad[i].P;
		f >> aRoad[i].C;
		aRoad[i].A--;
		aRoad[i].P--;
		aRoad[i].B--;
	}
	f.close();
	//продажа дорог
	coffers = 0;
	for (int i = 0; i < m; i++) {
		if (aRoad[i].P == 0){
			aRoad[i].P = -1;
			coffers += aRoad[i].C;
		}
	}
	//cout << coffers;
	//продажа дорог

	//заполнение графа g 
	vector<vector<MyStruct>> g(n);
	MyStruct my;
		for (int i = 0; i < m; i++) {
			my.v = aRoad[i].B;
			my.price = aRoad[i].C + payForTown[aRoad[i].B];
			my.e = i;
			g[aRoad[i].A].push_back(my);
			my.v = aRoad[i].A;
			my.price = aRoad[i].C + payForTown[aRoad[i].A];
			my.e = i;
			g[aRoad[i].B].push_back(my);
		}

	//дейкстры
		vector<int> d(n, INF);
	    vector<pair<int,int>>	p(n);
	d[0] = 0;
	for (int i = 0; i<n; ++i) {
		int v = -1;
		for (int j = 0; j<n; ++j)
			if (!visit[j] && (v == -1 || d[j] < d[v]))
				v = j;
		if (d[v] == INF)
			break;
		visit[v] = true;
		for (int j = 0; j<g[v].size(); ++j) {
			int to = g[v][j].v,
				k = g[v][j].price;
			if (d[v] + k < d[to]) {
				d[to] = d[v] + k;
				p[to] = make_pair(v, g[v][j].e);
			}
		}
	}
	//нехватка денег 
	if (d[n - 1] > coffers) {
		ofstream of("campaign.out");
		of << -1;
		of.close();
		return 0;
	}
	//востановление пути
	//cout << endl;
	vector<pair<int,int>> path;
	for (int v = n-1; v != 0; v = p[v].first)
		path.push_back(p[v]);
	reverse(path.begin(), path.end());
	/*
	for (int i = 0; i < path.size(); i++)
		cout << path[i].first << ' '<<path[i].second<<endl;
    */
	
	//купить дороги обратно
    vector<int> roadsell;
	vector<int> roadbuy;
	for (int i = 0; i < path.size(); i++) {
		if (aRoad[path[i].second].P == -1)
			aRoad[path[i].second].P = 0;
		else
			roadbuy.push_back(path[i].second+1);
	}

	for (int i = 0; i < m; i++) {
		if (aRoad[i].P == -1)
			roadsell.push_back(i+1);
	}
	ofstream of("campaign.out");
	of << roadsell.size()<<' ';
	for (int i = 0; i < roadsell.size(); i++) {
		of << roadsell[i] << ' ';
	}
	of << endl;
	of<< roadbuy.size() << ' ';
	for (int i = 0; i < roadbuy.size(); i++) {
		of << roadbuy[i] << ' ';
	}
	of << endl;
	for (int i = 0; i < path.size(); i++) {
		of << path[i].first+1 << ' ';
	}
	of << n;
	of.close();

	//system("pause");
	return 0;
}