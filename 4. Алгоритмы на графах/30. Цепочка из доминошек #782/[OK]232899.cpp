#include <iostream>
#include <fstream>
#include <queue>
#include <set>

using namespace std;

int main() {
	ifstream in("input.txt");
	ofstream of("output.txt");

	int n;
	int bufferOne, bufferTwo;
	in >> n;

	set<int> *info = new set<int>[7];
	int quantity[7] = { 0,0,0,0,0,0,0 };
	bool isMarked[7] = { false, false, false, false, false, false, false };
	bool isPresent[7] = { false, false, false, false, false, false, false };

	for (int i = 0; i < n; i++) {
		in >> bufferOne;
		in >> bufferTwo;

		info[bufferOne].insert(bufferTwo);
		info[bufferTwo].insert(bufferOne);

		quantity[bufferOne]++;
		quantity[bufferTwo]++;

		isPresent[bufferOne] = true;
		isPresent[bufferTwo] = true;
	}

	for (int i = 0; i < 7; i++) {
		if (quantity[i] % 2 != 0) {
			of << "No";
			in.close();
			of.close();
			return 0;
		}
	}

	for (int i = 0; i < 7; i++) {
		if (isPresent[i]) {
			queue<int> queueB = queue<int>();
			queueB.push(i);

			while (!queueB.empty()) {
				int j = queueB.front();
				isMarked[j] = true;
				queueB.pop();

				for (int k = 0; k < info[j].size(); k++) {
					if (!isMarked[*std::next(info[j].begin(), k)]) {
						queueB.push(*std::next(info[j].begin(), k));
					}
				}
			}
			break;
		}
	}

	for (int i = 0; i < 7; i++) {
		if (isPresent[i] && !isMarked[i]) {
			of << "No";
			in.close();
			of.close();
			return 0;
		}
	}
	of << "Yes";
	in.close();
	of.close();
	return 0;
}