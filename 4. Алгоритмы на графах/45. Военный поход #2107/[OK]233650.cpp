#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <fstream>
#include <queue>
#include <vector>
#include <algorithm>
#include <iterator>

const int MAX_N = 2001;
int roads_indices[MAX_N][MAX_N];

int main() {
	std::ios_base::sync_with_stdio(false);
	freopen("campaign.in", "r", stdin);
	freopen("campaign.out", "w", stdout);

	int n, m;
	std::cin >> n >> m;
	std::vector<int> roads_to_sell;
	std::vector<int> cities_cost(n);
	for (int i = 0; i < n; i++) {
		std::cin >> cities_cost[i];
	}
	std::vector<int> roads_owners(m, -1);
	for (int i = 0; i < MAX_N; i++) {
		for (int j = 0; j < MAX_N; j++) {
			roads_indices[i][j] = -1;
			roads_indices[j][i] = -1;
		}
	}
	
	int n1, n2, n3, n4;
	int money = 0;
	std::vector<std::vector<std::pair<int, int>>> adjacent_list(n);
	for (int i = 0; i < m; i++) {
		std::cin >> n1 >> n2 >> n3 >> n4;
		n1--;
		n2--;
		n3--;
		(adjacent_list[n1]).push_back(std::make_pair(n2, n4 + cities_cost[n2]));
		(adjacent_list[n2]).push_back(std::make_pair(n1, n4 + cities_cost[n1]));
		roads_indices[n1][n2] = i;
		roads_indices[n2][n1] = i;
		roads_owners[i] = n3;
		if (n3 == 0) {
			money += n4;
			roads_to_sell.push_back(i);
		}
	}
	const int INF = 1000000000;
	std::vector<int> distances(n, INF);
	std::vector<int> previous(n);
	std::vector<bool> visited(n, false);
	distances[0] = 0;

	for (int i = 0; i < n; i++) {
		int vertex = -1;
		for (int j = 0; j < n; j++)
			if (!visited[j] && (vertex == -1 || distances[j] < distances[vertex])) {
				vertex = j;
			}
		if (distances[vertex] == INF) {
			break;
		}
		visited[vertex] = true;

		for (int j = 0; j < adjacent_list[vertex].size(); j++) {
			if (distances[vertex] + adjacent_list[vertex][j].second < distances[adjacent_list[vertex][j].first]) {
				distances[adjacent_list[vertex][j].first] = distances[vertex] + adjacent_list[vertex][j].second;
				previous[adjacent_list[vertex][j].first] = vertex;
			}
		}
	}

	
	if (money < distances[n - 1] || !visited[n - 1]) {
		std::cout << "-1";
	}
	else {
		
		std::vector<int> roads_to_buy;
		std::vector<int> path;
		int v = n - 1;
		while (v != 0) {
			path.push_back(v);
			v = previous[v];
		}
		path.push_back(0);
		reverse(path.begin(), path.end());

		for (int i = 1; i < path.size(); i++) {
			int road = roads_indices[path[i - 1]][path[i]];
			if (road != -1) {
				int owner = roads_owners[road];
				if (owner != 0) {
					roads_to_buy.push_back(road);
				}
				else {
					std::vector<int>::iterator pointer = std::find(roads_to_sell.begin(), roads_to_sell.end(), road);
					if (pointer != roads_to_sell.end()) {
						roads_to_sell.erase(pointer);
					}
				}
			}
		}

		std::cout << roads_to_sell.size() << " ";
		for (int i = 0; i < roads_to_sell.size(); i++) {
			std::cout << roads_to_sell[i] + 1 << " ";
		}
		std::cout << std::endl;
		std::cout << roads_to_buy.size() << " ";
		for (int i = 0; i < roads_to_buy.size(); i++) {
			std::cout << roads_to_buy[i] + 1 << " ";
		}
		std::cout << std::endl;
		for (int i = 0; i < path.size(); i++) {
			std::cout << path[i] + 1 << " ";
		}
	
	}


	fclose(stdout);
	fclose(stdin);
	system("pause");
	return 0;

}
