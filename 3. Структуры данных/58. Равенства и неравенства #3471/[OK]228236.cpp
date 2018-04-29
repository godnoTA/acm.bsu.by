#include <iostream>
#include <fstream>
#include <stack>

using namespace std;

struct Pair {
	int a;
	int b;
	Pair(int _a, int _b) {
		a = _a;
		b = _b;
	}
};

bool fisrt_second_number(char arr[], int &a, int& b) {
	bool f;
	a = 0;
	b = 0;
	int i = 1;
	while (arr[i] != ' ') {
		a *= 10;
		a += arr[i] - '0';
		i++;
	}
	if (arr[i + 1] == '!') f = false;
	else f = true;
	i += 5;
	while (arr[i]) {
		b *= 10;
		b += arr[i] - '0';
		i++;
	}
	return f;
}

void main() {
	ifstream in("equal-not-equal.in");
	ofstream out("equal-not-equal.out");

	int a, b;
	int n, m;
	int id = 1;
	char buff[20];

	in >> n >> m;

	int* arr = new int[n + 1];

	stack<Pair> unequ;

	for (int i = 0; i < n + 1; i++) {
		arr[i] = 0;
	}

	in.getline(buff, 20);
	for (int i = 0; i < m; i++) {
		in.getline(buff, 20);
		if (fisrt_second_number(buff, a, b)) {
			if (a != b) {
				if (arr[a] == 0 && arr[b] == 0) {
					arr[a] = id;
					arr[b] = id;
					id++;
				}else
				if (arr[a] != 0 && arr[b] == 0) {
					arr[b] = arr[a];
				}else
				if (arr[a] == 0 && arr[b] != 0) {
					arr[a] = arr[b];
				}else
				if (arr[a] != 0 && arr[b] != 0 && arr[b] != arr[a]) {
					int q = arr[b];
					for (int j = 1; j < n + 1; j++) {
						if (arr[j] == q) arr[j] = arr[a];
					}
				}
			}
		}
		else {
			if (a == b) {
				out << "No";
				//system("pause");
				return;
			}
			else {
				unequ.push(Pair(a, b));
			}
		}
	}
	
	while (!unequ.empty()) {
		if (arr[unequ.top().a] == arr[unequ.top().b] && arr[unequ.top().a] != 0) {
			out << "No";
			//system("pause");
			return;
		}
		unequ.pop();
	}

	out << "Yes";
	//system("pause");
	return;
}