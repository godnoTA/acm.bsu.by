#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <fstream>
#include <queue>
#include <vector>

auto compare = [](const std::pair<long long, long long> &p1, const std::pair<long long, long long> &p2) {
	return p1.second > p2.second;
};

int main() {
	std::ios_base::sync_with_stdio(false);
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);

	long long n;
	long long m;
	bool *blocked;
	long long *priorities;
	std::cin >> n >> m;
	std::vector<std::vector<std::pair<long long,long long>>> adjacent_list(n);
	std::priority_queue<std::pair<long long, long long>, std::vector<std::pair<long long, long long>>,decltype(compare)> min_heap(compare);
	blocked = new bool[n];
	priorities = new long long[n];
	for (long long i = 0; i < n; i++) {
		blocked[i] = false;
		priorities[i] = 0;
	}
	long long v1, v2, weight;
	for (long long i = 0; i < m; i++) {
		std::cin >> v1 >> v2 >> weight;
		(adjacent_list[v1-1]).push_back(std::make_pair(v2-1, weight));
		(adjacent_list[v2-1]).push_back(std::make_pair(v1-1, weight));
	}

	min_heap.push(std::make_pair(0,0));
	while (!min_heap.empty()) {
		auto vertex = (min_heap.top());
		min_heap.pop();
		if (!blocked[vertex.first]) {
			blocked[vertex.first] = true;
			priorities[vertex.first] = vertex.second;
			for (long long i = 0; i < adjacent_list[vertex.first].size(); i++) {
				if (!blocked[adjacent_list[vertex.first][i].first]) {
					long long new_priority = (vertex.second) + (adjacent_list[vertex.first][i]).second;
					priorities[adjacent_list[vertex.first][i].first] = new_priority;
					min_heap.push(std::make_pair(adjacent_list[vertex.first][i].first, new_priority));
					
				}
			}
		}
	}
	std::cout << priorities[n-1] << std::endl;
	
	
	fclose(stdout);
	fclose(stdin);
	system("pause");
	return 0;
}