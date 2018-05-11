#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <fstream>
#include <vector>

std::vector<std::vector<int>> graph;
std::vector<std::vector<int>> transposed_graph;
std::vector<bool> visited;
std::vector<int> order;
std::vector<int> current_component;

std::vector<std::vector<int>> components;

std::vector<int> representators;
std::vector<int> unique_representators;


std::vector<int> sinks;
std::vector<int> sources;
std::vector<int> temporary;
std::vector<int> isolated;

std::vector<bool> marked;

std::vector<int> sources_sorted;
std::vector<int> sinks_sorted;

std::vector<bool> added_sources_sorted;
std::vector<bool> added_sinks_sorted;

std::vector<std::pair<int, int>> desired_edges;



const int MAX_AMOUNT = 101;

int merged_graph[MAX_AMOUNT][MAX_AMOUNT];
bool sink_not_found = true;
int w = -1;

void order_dfs(int vertex) {
	visited[vertex] = true;
	for (int i = 0; i < graph[vertex].size(); i++) {
		if (!visited[graph[vertex][i]]) {
			order_dfs(graph[vertex][i]);
		}
	}
	order.push_back(vertex);
}
void component_definer_dfs(int vertex) {
	visited[vertex] = true;
	current_component.push_back(vertex);
	for (int i = 0; i < transposed_graph[vertex].size(); i++) {
		if (!visited[transposed_graph[vertex][i]]) {
			component_definer_dfs(transposed_graph[vertex][i]);
		}
	}
}

void search(int x) {
	if (!marked[x]) {
		bool is_sink = false;
		for (int i = 0; i < sinks.size() && !is_sink; i++) {
			if (x == sinks[i]) {
				is_sink = true;
				break;
			}
		}
		if (is_sink) {
			w = x;
			sink_not_found = false;
		}
		marked[x] = true;
		for (int i = 0; i < graph.size(); i++) {
			if (merged_graph[x][i] == 1) {
				if (sink_not_found) {
					search(i);
				}
			}
		}
	}
}

int strong_connectivity_sort() {
	for (int k = 0; k < marked.size(); k++) {
		marked[k] = false;
	}

	int appropriate = 0;

	while (!temporary.empty()) {
		int v = temporary.back();
		if (!marked[v]) {
			w = -1;
			sink_not_found = true;
			search(v);
			if (w != -1) {
				appropriate++;
				sources_sorted.push_back(v);
				sinks_sorted.push_back(w);
				added_sources_sorted[v] = true;
				added_sinks_sorted[w] = true;
			}
		}

		temporary.pop_back();
	}

	return appropriate;
}



int main() {

	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);
	int n;
	std::cin >> n;
	graph.resize(n);
	transposed_graph.resize(n);
	visited.assign(n, false);

	for (int i = 0; i < n; i++) {
		int a = -1;
		while (a != 0) {
			std::cin >> a;
			if (a != 0) {
				graph[i].push_back(a - 1);
				transposed_graph[a - 1].push_back(i);
			}
		}
	}
	visited.assign(n, false);
	for (int i = 0; i < n; i++) {
		if (!visited[i]) {
			order_dfs(i);
		}
	}
	visited.assign(n, false);
	for (int i = n - 1; i >= 0; i--) {
		int vertex = order[i];
		if (!visited[vertex]) {
			component_definer_dfs(vertex);

			components.push_back(current_component);

			current_component.clear();
		}
	}

	representators.resize(n);

	for (int i = 0; i < components.size(); i++) {
		int representator = components[i][0];
		unique_representators.push_back(representator);
		for (int j = 0; j < components[i].size(); j++) {
			representators[components[i][j]] = representator;
		}
	}

	for (int i = 0; i < components.size(); i++) {
		for (int j = 0; j < components[i].size(); j++) {
			for (int k = 0; k < graph[components[i][j]].size(); k++) {
				int element = graph[components[i][j]][k];
				merged_graph[components[i][j]][representators[element]] = 1;
			}
		}
	}

	for (int i = 0; i < n; i++) {
		merged_graph[i][i] = 0;
	}



	for (int i = 0; i < unique_representators.size(); i++) {
		int temp1 = 0;
		int temp2 = 0;
		for (int j = 0; j < n; j++) {
			if (merged_graph[unique_representators[i]][j] == 0) {
				temp1++;
			};
		}
		for (int k = 0; k < n; k++) {
			if (merged_graph[k][unique_representators[i]] == 0 || representators[k] == unique_representators[i]) {
				temp2++;
			}
		}

		if (temp1 == n && temp2 != n) {
			sinks.push_back(unique_representators[i]);
		}

		if (temp2 == n && temp1 != n) {
			sources.push_back(unique_representators[i]);
		}
		if (temp2 == n && temp1 == n) {
			isolated.push_back(unique_representators[i]);
		}


	}

	int answer_a = sources.size() + isolated.size();
	int answer_b = fmax(sources.size(), sinks.size()) + isolated.size();

	marked.resize(n, false);
	w = -1;
	for (int i = 0; i < sources.size(); i++) {
		temporary.push_back(sources[i]);
	}
	added_sinks_sorted.resize(n, false);
	added_sources_sorted.resize(n, false);

	int appropriate_amount = strong_connectivity_sort();
	for (int i = 0; i < sources.size(); i++) {
		if (!added_sources_sorted[sources[i]]) {
			sources_sorted.push_back(sources[i]);
		}
	}
	for (int i = 0; i < sinks.size(); i++) {
		if (!added_sinks_sorted[sinks[i]]) {
			sinks_sorted.push_back(sinks[i]);
		}
	}
	int source_amount = sources_sorted.size();
	int sink_amount = sinks_sorted.size();
	int isolated_amount = isolated.size();

	// connection

	int first = 0;
	int second = 0;

	if (source_amount <= sink_amount) {
		for (int i = 1; i < appropriate_amount; i++) {
			first = sinks_sorted[i - 1];
			second = sources_sorted[i];
			desired_edges.push_back(std::pair<int, int>(first, second));
		}
		for (int i = appropriate_amount + 1; i <= source_amount; i++) {
			first = sinks_sorted[i - 1];
			second = sources_sorted[i - 1];
			desired_edges.push_back(std::pair<int, int>(first, second));
		}
		for (int i = source_amount + 1; i < sink_amount; i++) {
			first = sinks_sorted[i - 1];
			second = sinks_sorted[i];
			desired_edges.push_back(std::pair<int, int>(first, second));
		}

		if (isolated_amount == 0 && source_amount == sink_amount && source_amount != 0) {
			first = sinks_sorted[appropriate_amount - 1];
			second = sources_sorted[0];
			desired_edges.push_back(std::pair<int, int>(first, second));
		}
		if (isolated_amount == 0 && source_amount < sink_amount && source_amount != 0) {
			first = sinks_sorted[sink_amount - 1];
			second = sources[0];
			desired_edges.push_back(std::pair<int, int>(first, second));
		}
		if (source_amount < sink_amount && source_amount != 0) {
			first = sinks_sorted[appropriate_amount - 1];
			second = sinks_sorted[source_amount];
			desired_edges.push_back(std::pair<int, int>(first, second));
		}
		if (isolated_amount != 0 && sink_amount != 0) {
			for (int i = 1; i < isolated_amount; i++) {
				first = isolated[i - 1];
				second = isolated[i];
				desired_edges.push_back(std::pair<int, int>(first, second));
			}
			if (source_amount < sink_amount && sink_amount != 0) {
				first = sinks_sorted[sink_amount - 1];
				second = isolated[0];
				desired_edges.push_back(std::pair<int, int>(first, second));
			}
			if (source_amount == sink_amount && sink_amount != 0) {
				first = sinks_sorted[appropriate_amount - 1];
				second = isolated[0];
				desired_edges.push_back(std::pair<int, int>(first, second));
			}
			if (source_amount != 0) {
				first = isolated[isolated_amount - 1];
				second = sources_sorted[0];
				desired_edges.push_back(std::pair<int, int>(first, second));
			}

		}
		if (isolated_amount != 0 && sink_amount == 0) {
			for (int i = 1; i < isolated_amount; i++) {
				first = isolated[i - 1];
				second = isolated[i];
				desired_edges.push_back(std::pair<int, int>(first, second));
			}
			first = isolated[isolated_amount - 1];
			second = isolated[0];
			desired_edges.push_back(std::pair<int, int>(first, second));

		}


	}
	else if (source_amount > sink_amount) {
		for (int i = 1; i < appropriate_amount; i++) {
			first = sinks_sorted[i - 1];
			second = sources_sorted[i];
			desired_edges.push_back(std::pair<int, int>(first, second));
		}
		for (int i = appropriate_amount + 1; i <= sink_amount; i++) {
			first = sinks_sorted[i - 1];
			second = sources_sorted[i - 1];
			desired_edges.push_back(std::pair<int, int>(first, second));
		}
		for (int i = sink_amount + 1; i < source_amount; i++) {
			first = sources_sorted[i - 1];
			second = sources_sorted[i];
			desired_edges.push_back(std::pair<int, int>(first, second));
		}

		if (source_amount > sink_amount) {
			first = sources_sorted[appropriate_amount - 1];
			second = sources_sorted[sink_amount];
			desired_edges.push_back(std::pair<int, int>(first, second));
		}
		if (isolated_amount == 0 && source_amount > sink_amount) {
			first = sinks_sorted[sink_amount - 1];
			second = sources_sorted[0];
			desired_edges.push_back(std::pair<int, int>(first, second));
		}

		if (isolated_amount != 0) {
			for (int i = 1; i < isolated_amount; i++) {
				first = isolated[i - 1];
				second = isolated[i];
				desired_edges.push_back(std::pair<int, int>(first, second));
			}

			if (sink_amount != 0) {
				first = sinks_sorted[sink_amount - 1];
				second = isolated[0];
				desired_edges.push_back(std::pair<int, int>(first, second));
			}
			if (source_amount != 0) {
				first = isolated[isolated_amount - 1];
				second = sources_sorted[0];
				desired_edges.push_back(std::pair<int, int>(first, second));
			}
		}

	}
	std::cout << answer_a << std::endl;
	if (components.size() == 1) {
		answer_b = 0;
	}
	else {
		answer_b = desired_edges.size();
	}
	std::cout << answer_b << std::endl;
	for (int i = 0; i < answer_b; i++) {
		std::cout << desired_edges[i].first + 1 << " " << desired_edges[i].second + 1 << std::endl;
	}

	fclose(stdout);
	fclose(stdin);
	system("pause");
	return 0;
}
