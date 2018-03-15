#include <fstream>
#include <vector>
#include <iostream>

using namespace std;

class RectangleSummator {
public:
	RectangleSummator(int n, int m, int k) :
		data(n, vector<int>(m)) {
		this->k = k;
	}

	inline int mod(int v) {
		return ((v % k) + k) % k;
	}

	void setvalue(int i, int j, int v) {
		int tmp = v;
		if (i > 0) {
			tmp += data[i - 1][j];
		}
		if (j > 0) {
			tmp += data[i][j - 1];
		}
		if ((i > 0) && (j > 0)) {
			tmp -= data[i - 1][j - 1];
		}
		data[i][j] = mod(tmp);
	}

	int getsum(int i, int j, int n1, int m1) {
		int tmp = data[i][j];
		if (i >= n1) {
			tmp -= data[i - n1][j];
		}
		if (j >= m1) {
			tmp -= data[i][j - m1];
		}
		if ((i >= n1) && (j >= m1)) {
			tmp += data[i - n1][j - m1];
		}
		return mod(tmp);
	}
private:
	vector<vector<int> > data;
	int k;
};

int main() {
	ifstream in("input.txt");
	int n, m, n1, m1, k;
	in >> n >> m;
	in >> n1 >> m1;
	in >> k;
	vector<vector<int> > a(n, vector<int>(m, 0));
	for (int i = 0; i < n; ++i) {
		for (int j = 0; j < m; ++j) {
			in >> a[i][j];
		}
	}

	int answer = 0;
	RectangleSummator summator(n, m, k);
	bool can_convert = true;

	for (int i = 0; i <= n - n1; ++i) {
		for (int j = 0; j <= m - m1; ++j) {
			summator.setvalue(i, j, 0);
			a[i][j] += summator.getsum(i, j, n1, m1);
			a[i][j] %= k;
			int required_steps = (k - a[i][j]) % k;
			answer += required_steps;
			summator.setvalue(i, j, required_steps);
		}

		for (int j = m - m1 + 1; j < m; ++j) {
			summator.setvalue(i, j, 0);
			a[i][j] += summator.getsum(i, j, n1, m1);
			a[i][j] %= k;
			if (a[i][j] != 0) {
				cout << i << endl;
				can_convert = false;
			}
		}
	}

	for (int i = n - n1 + 1; i < n; ++i) {
		for (int j = 0; j < m; ++j) {
			summator.setvalue(i, j, 0);
			a[i][j] += summator.getsum(i, j, n1, m1);
			a[i][j] %= k;
			if (a[i][j] != 0) {
				can_convert = false;
			}
		}
	}

	ofstream out("output.txt");
	if (can_convert) {
		out << answer << endl;
	} else {
		out << "impossible" << endl;
	}

	return 0;
}
