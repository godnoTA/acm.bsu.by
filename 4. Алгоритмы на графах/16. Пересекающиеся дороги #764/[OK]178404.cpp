#include <iostream>
#include <fstream>
#include <set>
#include <vector>
#include <queue>
#include <map>
#include <list>

using namespace std;

vector <vector < pair <int, int> > > vect;
vector <int> top1;
vector <int> rez1;
vector <bool>used;
vector <long> d;
vector <int> pl;



int main() {


	ifstream in("input.in");
	int N, K, a, b;
	in >> N;
	in >> K;
	in >> a;
	in >> b;
	
	for (int i = 0; i < N; i++) {
		vector <pair <int, int>> vec;
		vect.push_back(vec);
		pl.push_back(0);
		d.push_back(1000000001);
		used.push_back(false);

	}

	for (int i = 0; i < K; i++) {

		int I, J, ves;
		in >> I;
		in >> J;
		in >> ves;

		pair <int, int> p;
		p.first = J - 1;
		p.second = ves;
		vect[I - 1].push_back(p);

		pair <int, int> p2;
		p2.first = I - 1;
		p2.second = ves;
		vect[J - 1].push_back(p2);

		used[I - 1] = false;

	}



	for (int i = 0; i < N; i++) {
		pl[i] = vect[i].size();
		d[i] = 1000000001;
	}

	for (int i = 0; i < N; i++) {
		vector<pair <int, int>> vec;
		vec = vect[i];
		for (int j = 0; j < vec.size(); j++) {
			vec[j].second += pl[vec[j].first];
		}
		vect[i] = vec;
	}

	d[a - 1] = 0;
	long min = 0;
	while (min != 1000000001) {
		int index = a - 1;
		min = 1000000001;
		for (int i = 0; i < N; i++) {
			if (used[i] == false && d[i] < min) {
				index = i;

				min = d[i];
			}
		}
		if (min != 1000000001) {
			vector<pair <int, int>> vec;
			vec = vect[index];
			for (int i = 0; i < vec.size(); i++) {
				if (vec[i].second > 0) {
					long temp = min + vec[i].second;
					if (temp < d[vec[i].first]) {
						d[vec[i].first] = temp;
						top1.push_back(index + 1);
						top1.push_back(vec[i].first + 1);
					}

				}
			}
			used[index] = true;
		}
	}

	int z = b;

	for (int u = top1.size() - 1; u >= 0; u--) {
		rez1.push_back(z);
		for (int i = u; i>0; i -= 2) {
			if (top1[i] == z) {
				z = top1[i - 1];
				rez1.push_back(z);
			}
		}
		if (rez1.size() != 0 && rez1[rez1.size() - 1] == a) {
			break;
		}
		else {
			rez1.clear();
		}
	}
	d[b - 1] -= pl[b - 1];
	ofstream out("output.out");
	if (a == b) {
		out << 0 << endl;
	}
	else{

		out << d[b - 1] << endl;
		for (int i = rez1.size() - 1; i >= 0; i--) {
			out << rez1[i] << " ";
		}
		out << endl;

	}
	out.close();
}