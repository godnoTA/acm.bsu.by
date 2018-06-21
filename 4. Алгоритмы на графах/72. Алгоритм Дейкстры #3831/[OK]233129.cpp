#include<iostream>
#include<fstream>
#include<vector>
#include<set>
#include<utility>
#include<queue>
using namespace std;
static long long n;
static long long m;
const long long MAX = INT64_MAX;
void main() {
		fstream in("input.txt");
		in >> n >> m; 
		vector<vector<pair <long long, long long> > > mas(n);
		long long count = 0;
		long long begin, finish, weight;
		while (count<m) {
			in >> begin >> finish >> weight;
			mas[begin-1].push_back(make_pair(weight, finish-1));
			mas[finish-1].push_back(make_pair(weight, begin-1));
			count++;
		}
		in.close();
		vector<long long> distance(n, MAX);
		distance[0] = 0;
		set<pair<long long, long long>> myQueue;
		myQueue.insert(make_pair(distance[0], 0));
		while (!myQueue.empty()) {
			long long v = myQueue.begin()->second;
			myQueue.erase(myQueue.begin());
			for (long long i = 0; i < mas[v].size(); i++)
			{
				long long toAnother = mas[v][i].second;
				long long Weight_d = mas[v][i].first;
				if (distance[v] + Weight_d < distance[toAnother])
				{
					myQueue.erase(make_pair(distance[toAnother], toAnother));
					distance[toAnother] = distance[v] + Weight_d;
					myQueue.insert(make_pair(distance[toAnother], toAnother));
				}
			}
		}
		ofstream out("output.txt");
		long long otvet =distance[n-1];
		cout << otvet<<endl;
		out << otvet<<endl;
		out.close();
}