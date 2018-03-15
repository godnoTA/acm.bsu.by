#include <iostream>
#include <iterator>
#include <list>
#include <fstream>
#include <vector>
#include <queue>
#include <functional>
#include <algorithm>
using namespace std;

struct Element {
	int in;
	int out;
	int cena;
};

class Compare
{
public:
	bool operator()(pair<int, int> n1, pair<int, int> n2) {
		return n1.second>n2.second;
	}
};

int main() {

	int N, M, K;

	vector<Element> rebra;
	FILE *in;
	FILE *out;
	freopen_s(&in, "input.txt", "r", stdin);
	freopen_s(&out, "output.txt", "w", stdout);
	fscanf_s(in, "%d %d %d", &N, &M, &K);

	vector <list<pair<int, long>>> sp1(N);
	vector <list<pair<int, long>>> sp2(N);
	int tempX, tempY, tempValue;
	for (int i = 0; i < M; i++) {
		fscanf_s(in, "%d %d %d", &tempX, &tempY, &tempValue);
		tempX--;
		tempY--;
		sp1[tempX].push_back(make_pair(tempY, tempValue));
		sp2[tempY].push_back(make_pair(tempX, tempValue));
		Element st;
		st.in = tempX;
		st.out = tempY;
		st.cena = tempValue;
		rebra.push_back(st);
	}
	
	priority_queue<pair<int, int>, vector<pair<int, int>>, Compare> q;
	q.push(make_pair(0, 0));
	
	vector<long> values(N);
	values[0] = 0;
	for (int i = 1; i < N; i++)
		values[i] = LONG_MAX;
	
	while (!q.empty()) {
		pair<int, int> temp = q.top();
		q.pop();

		if (values[temp.first] < temp.second)
			continue;
			for (auto it = sp1[temp.first].begin(); it != sp1[temp.first].end(); it++){
				int v = (*it).first, len = (*it).second;
					if (values[v] > temp.second + len) {
						q.push(make_pair(v, temp.second + len));
						values[v] = temp.second + len;
					}
				}		
	}

	vector<long> values2(N);
	values2[N-1] = 0;
	for (int i = 0; i < N-1; i++)
		values2[i] = LONG_MAX;
	q.push(make_pair(N-1, 0));

	while (!q.empty()) {
		pair<int, int> temp = q.top();
		q.pop();

		if (values2[temp.first] < temp.second)
			continue;
		for (auto it = sp2[temp.first].begin(); it != sp2[temp.first].end(); it++) {
			int v = (*it).first, len = (*it).second;
			if (values2[v] > temp.second + len) {
				q.push(make_pair(v, temp.second + len));
				values2[v] = temp.second + len;
			}
		}
	}
	vector<int> result;

	for (int i = 0; i < M; i++) {
		if (values[rebra[i].in] + rebra[i].cena + values2[rebra[i].out] <= values[N - 1] + K)
			if(values[rebra[i].in] != LONG_MAX && values2[rebra[i].out] != LONG_MAX)
				result.push_back(i);
	}
	fprintf_s(out, "%d\n", result.size());

	for (int i = 0; i < result.size(); i++)
		fprintf_s(out, "%d\n", result[i] + 1);
	return 0;
}