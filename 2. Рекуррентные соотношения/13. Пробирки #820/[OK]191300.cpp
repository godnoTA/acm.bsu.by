#include <iostream>
#include <queue>
#include <vector>
#include <fstream>
using namespace std;

bool isGood(pair<int, int> p) {
	if (p.first <= 100 && p.first >= 0 && p.second <= 100 && p.second >= 0)
		return true;
	return false;
}


int main() {
	int X, K, L, temp;
	vector<int> v;
	ifstream fin("in.txt");
	ofstream fout("out.txt");
	int matrix[101][101];

	for (int i = 0; i < 101; i++) 
		for (int j = 0; j < 101; j++)
			matrix[i][j] = -1;
	
	fin >> X >> K >> L;
	while (true) {
		fin >> temp;
		if (temp == 0) break;
		v.push_back(temp);
	}

	if (v.empty()) v.push_back(0);
	
	matrix[K][L] = 0;
	queue<pair<int, int>> q;
	int KEY = 100 - X;
	q.push(make_pair(K, L));

	while (!q.empty()) {
		pair<int, int> pr = q.front();
		q.pop();
		int znac = matrix[pr.first][pr.second];
		int A = pr.first;
		int B = pr.second;
		int zamena = znac + 1;

		if (A + B == KEY) {
			fout << matrix[A][B];
			return 0;
		}

		for (int i = 0; i < v.size(); i++) {
				if (isGood(make_pair(v[i], B + A - v[i])) && matrix[v[i]][B + A - v[i]] == -1) {
					q.push(make_pair(v[i], B + A - v[i]));
					matrix[v[i]][B + A - v[i]] = zamena;
				}
				if (isGood(make_pair(A + B - v[i], v[i])) && matrix[A + B - v[i]][v[i]] == -1) {
					q.push(make_pair(A + B - v[i], v[i]));
					matrix[A + B - v[i]][v[i]] = zamena;
				}
			
				if (isGood(make_pair(A, 0)) && matrix[A][0] == -1) {
					q.push(make_pair(A, 0));
					matrix[A][0] = zamena;
				}
				if (isGood(make_pair(0, B)) && matrix[0][B] == -1) {
					q.push(make_pair(0, B));
					matrix[0][B] = zamena;
				}
				if (A > v[i])
					if (isGood(make_pair(v[i], B)) && matrix[v[i]][B] == -1) {
						q.push(make_pair(v[i], B));
						matrix[v[i]][B] = zamena;
				}
				if (B > v[i])
					if (isGood(make_pair(A, v[i])) && matrix[A][v[i]] == -1) {
						q.push(make_pair(A, v[i]));
						matrix[A][v[i]] = zamena;
				}
				if (isGood(make_pair(A + B, 0)) && matrix[A + B][0] == -1) {
					q.push(make_pair(A + B, 0));
					matrix[A + B][0] = zamena;
				}
				if (isGood(make_pair(0, A + B)) && matrix[0][A + B] == -1) {
					q.push(make_pair(0, A + B));
					matrix[0][A + B] = zamena;
				}
				if (A < v[i] && v[i] + B <= 100)
					if (isGood(make_pair(v[i], B)) && matrix[v[i]][B] == -1) {
							q.push(make_pair(v[i], B));
							matrix[v[i]][B] = zamena;
						}
				if (B < v[i] && A + v[i] <= 100)
					if (isGood(make_pair(A, v[i])) && matrix[A][v[i]] == -1) {
							q.push(make_pair(A, v[i]));
							matrix[A][v[i]] = zamena;
						}
				if (isGood(make_pair(100 - B, B)) && matrix[100 - B][B] == -1) {
						q.push(make_pair(100 - B, B));
						matrix[100 - B][B] = zamena;
					}
				if (isGood(make_pair(A, 100 - A)) && matrix[A][100 - A] == -1) {
						q.push(make_pair(A, 100 - A));
						matrix[A][100 - A] = zamena;
					}
				
		}

		}
	fout << "No solution";
	return 0;
}