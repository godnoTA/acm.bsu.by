#include <cmath>
#include <algorithm>
#include <iostream>
#include <chrono>
#include <ctime>
#include <fstream>

//#define timing
//#define old
using namespace std;

int data1[20010][10010];
int infinity = 100000000;

int p[10010];
int l[10010];

int main()
{

	ifstream in("input.txt");
	ofstream out("output.txt");

	int size;
	in >> size;

	auto start = std::chrono::system_clock::now();

	for (int i = 0; i < size; i++) {
		int lifetime, position;
		in >> position;
		in >> lifetime;

		l[i] = lifetime;
		p[i] = position;

		data1[i][i] = 0;
	}

#ifdef timing

	auto end = std::chrono::system_clock::now();

	std::chrono::duration<double> elapsed_seconds = end - start;
	std::time_t end_time = std::chrono::system_clock::to_time_t(end);

	char str[26];
	ctime_s(str, sizeof str, &end_time);
	std::cout << "finished reading at " << str
		<< "elapsed time: " << elapsed_seconds.count() << "s\n";
#endif
	
#ifdef old
	for (int k = 1; k < size; k++) {
		for (int i = 0; i < size - k; i++) {
			int first = data1[i][i + k - 1];
			int second = data1[i + k - 1][i];

			int wayFromFirst = first + (p[i + k] - p[i + k - 1]);
			int wayFromSecond = second + (p[i + k] - p[i]);

			data1[i][i + k] = min(wayFromFirst, wayFromSecond);

			if (data1[i][i + k] > l[i + k]) {
				data1[i][i + k] = infinity;
			}
		}

		for (int i = k; i < size; i++) {
			int first = data1[i][i - k + 1];
			int second = data1[i - k + 1][i];

			int wayFromFirst = first + p[i - k + 1] - p[i - k];
			int wayFromSecond = second + p[i] - p[i - k];

			data1[i][i - k] = min(wayFromFirst, wayFromSecond);
			if (data1[i][i - k] > l[i - k]) {
				data1[i][i - k] = infinity;
			}
		}
	}
	
#endif
	for (int k = 2; k < size * 2 + 2; k += 2) {
		for (int i = 0; i < size - k / 2 ; i++) {
			int first = data1[k - 2][i];
			int second = data1[k - 1][i];

			int wayFromFirst = first + (p[i + k / 2 ] - p[i + k / 2 - 1]);
			int wayFromSecond = second + (p[k / 2 + i] - p[i]);

			data1[k][i] = min(wayFromFirst, wayFromSecond);
			if (data1[k][i] > l[i + k / 2]) {
				data1[k][i] = infinity;
			}

			first = data1[k - 2][i + 1];
			second = data1[k - 1][i + 1];

			wayFromFirst = first + p[i + k / 2] - p[i];
			wayFromSecond = second + p[i + 1] - p[i];

			data1[k + 1][i] = min(wayFromFirst, wayFromSecond);
			if (data1[k + 1][i] > l[i]) {
				data1[k + 1][i] = infinity;
			}
		}
	}

	long result = min(data1[size * 2 - 2][0], data1[size * 2 - 1][0]);

	if (result != infinity) {
		out << result;
	}
	else {
		out << "No solution";
	}

	//out.close();

#ifdef timing
	end = std::chrono::system_clock::now();

	elapsed_seconds = end - start;
	end_time = std::chrono::system_clock::to_time_t(end);

	ctime_s(str, sizeof str, &end_time);
	std::cout << "finished computation at " << str
		<< "elapsed time: " << elapsed_seconds.count() << "s\n";
	system("pause");
#endif
	return 0;
}
