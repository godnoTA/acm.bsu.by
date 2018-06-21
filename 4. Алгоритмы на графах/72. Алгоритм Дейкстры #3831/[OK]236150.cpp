#include<iostream>
#include<fstream>
#include<vector>
#include<set>
#include<utility>
#include<queue>
using namespace std;
int n;
int m;
long long MAX = INT64_MAX;
long long Deikstra(vector<vector<pair <long long, long long> > > smej)
{
	vector<long long> length(n, MAX);
	length[0] = 0;
	set<pair<long long, long long>> Qu;
	Qu.insert(make_pair(length[0], 0));
	while (!Qu.empty()) {
		long long v = Qu.begin()->second;
		Qu.erase(Qu.begin());
		for (long long i = 0; i < smej[v].size(); i++)
		{
			long long toAnother = smej[v][i].second;
			long long Weight_d = smej[v][i].first;
			if (length[v] + Weight_d < length[toAnother])
			{
				Qu.erase(make_pair(length[toAnother], toAnother));
				length[toAnother] = length[v] + Weight_d;
				Qu.insert(make_pair(length[toAnother], toAnother));
			}
		}
	}
	return  length[n - 1];

}
void main() {
	fstream fin("input.txt");
	fin >> n;
	fin >> m;
	vector<vector<pair <long long, long long> > > mas(n);
	long long a, b, c;
	for(int i = 0; i <m;i++){
		fin >> a >> b >> c;
		mas[a - 1].push_back(make_pair(c, b - 1));
		mas[b - 1].push_back(make_pair(c, a - 1));
	}
	fin.close();
	ofstream fout("output.txt");
	fout << Deikstra(mas) << endl;
	fout.close();
}