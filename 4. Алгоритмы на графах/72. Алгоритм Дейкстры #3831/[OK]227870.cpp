#include<iostream>
#include<algorithm>
#include<map>
#include<vector>
#include<set>
#include<cstdio>

#define INF 100000000000

using namespace std;
typedef pair<int, long long> mypair;

int main() {
#ifndef _MSC_VER
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);
#endif // !_MSC_VER
	int n, m, a, b;
        long long len;
	map<int, vector<pair<int, long long>>>mas;
	scanf("%d%d", &n, &m);
	map<long long, long long> met;
	for (int i = 2;i < n + 1;i++) met[i] = INF;
	
	for (int i = 0;i < m;i++) {
		scanf("%d%d%lld", &a, &b, &len);
		mas[a].push_back(mypair(b, len));
		mas[b].push_back(mypair(a, len));
	}
	set<mypair> myset;
	int i = 1;
	myset.insert(mypair(0, i));
	while (!myset.empty()) {
		i = myset.begin()->second;
		myset.erase(myset.begin());
		for (auto j : mas[i]) {
			if (met[i] + j.second < met[j.first]) {
				myset.erase(mypair(met[j.first], j.first));
				met[j.first] = met[i] + j.second;
				myset.insert(mypair(met[j.first], j.first));
			}
		}
	}
	printf("%lld", met[n]);
}