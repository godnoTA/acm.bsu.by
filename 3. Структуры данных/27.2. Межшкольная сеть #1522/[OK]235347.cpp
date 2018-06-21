#include <iostream>
#include <fstream>
#include <vector>
using namespace std;





vector<int> order;
vector<int> representators;
vector<int> unique_representators;


vector<int> sinks;
vector<int> sources;
vector<int> temporary;
vector<int> isolated;

vector<bool> marked;

vector<int> sources_sorted;
vector<int> sinks_sorted;

vector<bool> added_sources_sorted;
vector<bool> added_sinks_sorted;

vector<pair<int, int>> desired_edges;



const int MAX_AMOUNT = 101;

bool sink_not_found = true;
int w = -1;

void dfs1(vector<vector<int>>& net, int v, vector<char>& used) {
	used[v] = true;
	for (size_t i=0; i<net[v].size(); ++i)
		if (!used[ net[v][i] ])
			dfs1 (net, net[v][i], used);
	order.push_back (v);
}
void dfs2(int v, vector<char>& used, vector<int>& comp, vector<vector<int>>& t_net) {
	used[v] = true;
	comp.push_back(v);
	for (int i = 0; i < t_net[v].size(); i++) {
		if (!used[t_net[v][i]]) {
			dfs2(t_net[v][i], used, comp, t_net);
		}
	}
}

void search(vector<vector<int>>& net, int x, vector<vector<int>>& merged_graph) {
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
		for (int i = 0; i < net.size(); i++) {
			if (merged_graph[x][i] == 1) {
				if (sink_not_found) {
					search(net, i, merged_graph);
				}
			}
		}
	}
}

int strong_connectivity_sort(vector<vector<int>>& net, vector<vector<int>>& merged_graph) {
	for (int k = 0; k < marked.size(); k++) {
		marked[k] = false;
	}

	int appropriate = 0;

	while (!temporary.empty()) {
		int v = temporary.back();
		if (!marked[v]) {
			w = -1;
			sink_not_found = true;
			search(net, v, merged_graph);
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

	ifstream cin("input.txt");
	ofstream cout("output.txt");
	int n;
	cin >> n;
	vector<vector<int>> net(n);
	vector<vector<int>> t_net(n);
	vector<char> used(n);
	vector<int> component;
	vector<vector<int>> merged_graph(MAX_AMOUNT, vector<int>(MAX_AMOUNT));
	vector<vector<int>> components;
	for (int i = 0; i < n; i++) {
		int a = -1;
		while (a != 0) {
			cin >> a;
			if (a != 0) {
				net[i].push_back(a - 1);
				t_net[a - 1].push_back(i);
			}
		}
	}
	used.assign(n, false);
	for (int i = 0; i < n; i++) {
		if (!used[i]){
			dfs1(net, i, used);
		}
	}
	used.assign(n, false);
	for (int i = n - 1; i >= 0; i--) {
		int v = order[i];
		if (!used[v]) {
			dfs2(v, used, component, t_net);
			components.push_back(component);
			component.clear();
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
			for (int k = 0; k < net[components[i][j]].size(); k++) {
				int element = net[components[i][j]][k];
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
	int ans = fmax(sources.size(), sinks.size()) + isolated.size();

	marked.resize(n, false);
	w = -1;
	for (int i = 0; i < sources.size(); i++) {
		temporary.push_back(sources[i]);
	}
	added_sinks_sorted.resize(n, false);
	added_sources_sorted.resize(n, false);

	int appropriate_amount = strong_connectivity_sort(net, merged_graph);
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

	int first = 0;
	int second = 0;

	if (source_amount <= sink_amount) {
		for (int i = 1; i < appropriate_amount; i++) {
			first = sinks_sorted[i - 1];
			second = sources_sorted[i];
			desired_edges.push_back(make_pair(first, second));
		}
		for (int i = appropriate_amount + 1; i <= source_amount; i++) {
			first = sinks_sorted[i - 1];
			second = sources_sorted[i - 1];
			desired_edges.push_back(make_pair(first, second));
		}
		for (int i = source_amount + 1; i < sink_amount; i++) {
			first = sinks_sorted[i - 1];
			second = sinks_sorted[i];
			desired_edges.push_back(make_pair(first, second));
		}

		if (isolated_amount == 0 && source_amount == sink_amount && source_amount != 0) {
			first = sinks_sorted[appropriate_amount - 1];
			second = sources_sorted[0];
			desired_edges.push_back(make_pair(first, second));
		}
		if (isolated_amount == 0 && source_amount < sink_amount && source_amount != 0) {
			first = sinks_sorted[sink_amount - 1];
			second = sources_sorted[0];
			desired_edges.push_back(make_pair(first, second));
		}
		if (source_amount < sink_amount && source_amount != 0) {
			first = sinks_sorted[appropriate_amount - 1];
			second = sinks_sorted[source_amount];
			desired_edges.push_back(make_pair(first, second));
		}
		if (isolated_amount != 0 && sink_amount != 0) {
			for (int i = 1; i < isolated_amount; i++) {
				first = isolated[i - 1];
				second = isolated[i];
				desired_edges.push_back(make_pair(first, second));
			}
			if (source_amount < sink_amount && sink_amount != 0) {
				first = sinks_sorted[sink_amount - 1];
				second = isolated[0];
				desired_edges.push_back(make_pair(first, second));
			}

			if (source_amount == sink_amount && sink_amount != 0) {
				first = sinks_sorted[appropriate_amount - 1];
				second = isolated[0];
				desired_edges.push_back(make_pair(first, second));
			}
			if (source_amount != 0) {
				first = isolated[isolated_amount - 1];
				second = sources_sorted[0];
				desired_edges.push_back(make_pair(first, second));
			}

		}
		if (isolated_amount != 0 && sink_amount == 0) {
			for (int i = 1; i < isolated_amount; i++) {
				first = isolated[i - 1];
				second = isolated[i];
				desired_edges.push_back(make_pair(first, second));
			}
			first = isolated[isolated_amount - 1];
			second = isolated[0];
			desired_edges.push_back(make_pair(first, second));

		}


	}
	else if (source_amount > sink_amount) {
		for (int i = 1; i < appropriate_amount; i++) {
			first = sinks_sorted[i - 1];
			second = sources_sorted[i];
			desired_edges.push_back(make_pair(first, second));
		}
		for (int i = appropriate_amount + 1; i <= sink_amount; i++) {
			first = sinks_sorted[i - 1];
			second = sources_sorted[i - 1];
			desired_edges.push_back(make_pair(first, second));
		}
		for (int i = sink_amount + 1; i < source_amount; i++) {
			first = sources_sorted[i - 1];
			second = sources_sorted[i];
			desired_edges.push_back(make_pair(first, second));
		}

		if (source_amount > sink_amount) {
			first = sources_sorted[appropriate_amount - 1];
			second = sources_sorted[sink_amount];
			desired_edges.push_back(make_pair(first, second));
		}
		if (isolated_amount == 0 && source_amount > sink_amount) {
			first = sinks_sorted[sink_amount - 1];
			second = sources_sorted[0];
			desired_edges.push_back(make_pair(first, second));
		}

		if (isolated_amount != 0) {
			for (int i = 1; i < isolated_amount; i++) {
				first = isolated[i - 1];
				second = isolated[i];
				desired_edges.push_back(make_pair(first, second));
			}

			if (sink_amount != 0) {
				first = sinks_sorted[sink_amount - 1];
				second = isolated[0];
				desired_edges.push_back(make_pair(first, second));
			}
			if (source_amount != 0) {
				first = isolated[isolated_amount - 1];
				second = sources_sorted[0];
				desired_edges.push_back(make_pair(first, second));
			}
		}

	}
	cout << answer_a <<endl;
	if (components.size() == 1) {
		ans = 0;
	}
	else {
		ans = desired_edges.size();
	}
	cout << ans << endl;
	for (int i = 0; i < ans; i++) {
		cout << desired_edges[i].first + 1 << " " << desired_edges[i].second + 1 << endl;
	}

	return 0;
}
