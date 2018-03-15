#include <iostream>
#include <vector>
#include <fstream>

using namespace std;

class FenwickTree {
public:
	FenwickTree(int n) :
		m_data(n) {
	}
	void set(int id, long long new_value) {
		long long old_value = getPrefixSum(id) - getPrefixSum(id - 1);
		while (id < m_data.size()) {
			m_data[id] = m_data[id] - old_value + new_value;
			id |= (id + 1);
		}
	}
	long long getPrefixSum(int id) {
		if (id < 0) return 0;
		long long sum = 0;
		while (id >= 0) {
			sum += m_data[id];
			id = (id & (id + 1)) - 1;
		}
		return sum;
	}
	void print() {
		for (int i = 0; i < m_data.size(); ++i) {
			cout << m_data[i] << " ";
		}
	}
private:
	vector<long long> m_data;
};

int main() {
	ifstream in("input.txt");

	long long ans = 0;
	int n;
	in >> n;
	FenwickTree s1(n + 1);
	FenwickTree s2(n + 1);
	vector<int> a(n);
	for (int i = 0; i < n; ++i) {
		in >> a[i];
	}

	for (int i = n - 1; i >= 0; --i) {
		s1.set(a[i], 1);
		s2.set(a[i], s1.getPrefixSum(a[i] - 1));
		ans += s2.getPrefixSum(a[i] - 1);
	}

	ofstream out("output.txt");
	out << ans << endl;
	return 0;
}
