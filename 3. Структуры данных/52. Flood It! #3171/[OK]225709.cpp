#include <iostream>
#include <fstream>
#include <stack>
#include <stdio.h>
#include <vector>
 
#pragma warning(disable:4996)

using namespace std;
ofstream fout("output.txt");
void repaint(vector< vector <pair<int, pair<bool, bool> > > >& field,
	vector <stack < pair <int, int> > >& districts, pair<int, int>& sq_distr,
	int& set_color, int& n, int& m, int& kol_conquered) {
	while (!districts[set_color - 1].empty()) {
		sq_distr = districts[set_color - 1].top();
		districts[set_color - 1].pop();
		field[sq_distr.first][sq_distr.second].second.first = true;
		field[sq_distr.first][sq_distr.second].second.second = true;
		kol_conquered++;
		if (sq_distr.first > 0 && !field[sq_distr.first - 1][sq_distr.second].second.first) {
			field[sq_distr.first - 1][sq_distr.second].second.first = true;
			districts[field[sq_distr.first - 1][sq_distr.second].first - 1].push(make_pair(sq_distr.first - 1, sq_distr.second));
		}
		if (sq_distr.second > 0 && !field[sq_distr.first][sq_distr.second - 1].second.first) {
			field[sq_distr.first][sq_distr.second - 1].second.first = true;
			districts[field[sq_distr.first][sq_distr.second - 1].first - 1].push(make_pair(sq_distr.first, sq_distr.second - 1));
		}
		if (sq_distr.first < n - 1 && !field[sq_distr.first + 1][sq_distr.second].second.first) {
			field[sq_distr.first + 1][sq_distr.second].second.first = true;
			districts[field[sq_distr.first + 1][sq_distr.second].first - 1].push(make_pair(sq_distr.first + 1, sq_distr.second));
		}
		if (sq_distr.second < m - 1 && !field[sq_distr.first][sq_distr.second + 1].second.first) {
			field[sq_distr.first][sq_distr.second + 1].second.first = true;
			districts[field[sq_distr.first][sq_distr.second + 1].first - 1].push(make_pair(sq_distr.first, sq_distr.second + 1));
		}
	}
}

bool read_int_unlocked(int & out) {
	int c = _getchar_nolock();
	int x = 0;
	for (; !('0' <= c && c <= '9') && c != '-'; c = _getchar_nolock()) {
		if (c == EOF)
			return false;
	}
	if (c == EOF)
		return false;
	for (; '0' <= c && c <= '9'; c = _getchar_nolock()) {
		x = x * 10 + c - '0';
	}
	out = x;
	return true;
}
FILE* stream;

int main() {
	ios_base::sync_with_stdio(false);
	freopen("floodit.in", "r", stdin);
	freopen("floodit.out", "w", stdout);
	
	int n, m, k, t;
	int set_color, prev_color;
	int kol_conquered = 0;

	scanf("%d%d%d%d", &n, &m, &k, &t);

	vector< vector <pair<int, pair<bool, bool> > > > field(n, vector <pair<int, pair<bool, bool> > >(m + 1));
	vector <stack < pair <int, int> > > districts(k);
	pair<int, int> sq_distr;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			read_int_unlocked(k);
			field[i][j] = make_pair(k, make_pair(false, false));
		}
		//field[i][m] = make_pair(0, make_pair(true, false));
	}
	set_color = prev_color = field[0][0].first;
	districts[field[0][0].first - 1].push(make_pair(0, 0));
	repaint(field, districts, sq_distr, set_color, n, m, kol_conquered);
	bool exc = false;
	for (int i = 0; i < t; i++) {
		read_int_unlocked(set_color);
		if (kol_conquered - n * m) {
			if (set_color != prev_color) {
				repaint(field, districts, sq_distr, set_color, n, m, kol_conquered);
			}
			prev_color = set_color;
		}
		else {
			exc = true;
			break;
		}
	}
	if (exc) {
		while (read_int_unlocked(set_color)) {}
	}
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (!exc && !(field[i][j].second.second)) {
				printf("%d", field[i][j].first);
				printf("%c", ' ');
			}
			else {
				printf("%d", set_color);
				printf("%c", ' ');
			}
		}
		printf("%s", "\n");
	}
	
	fout << set_color;
	return 0;
}
