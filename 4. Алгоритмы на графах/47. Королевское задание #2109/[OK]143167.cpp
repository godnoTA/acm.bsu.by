#include <iostream>
#include <fstream>
#include <vector>
#include <set>
#include <algorithm>
#include <stack>

using namespace std;

void readGraph(const string &file_name, vector <vector <int>> &g,
               vector <int> &perfect_matching) {
	int n, m, id;
	ifstream in(file_name);
	in >> n;
	g.assign(2 * n, vector <int> ());
	for (int i = 0; i < n; ++i) {
		in >> m;
		for (int j = 0; j < m; ++j) {
			in >> id;
			id += n - 1;
			g[i].push_back(id);
		}
	}

	// Read one of the perfect matchings in the graph.
	// We need to reverse edges that are in this matching
	perfect_matching.resize(n);
	for (int i = 0; i < n; ++i) {
		in >> id;
		id += n - 1;
		perfect_matching[i] = id;
		g[i].erase(remove(g[i].begin(), g[i].end(), id));
		g[id].push_back(i);
	}
}

void dfs(int v, const vector <vector <int>> &g, int &curid, vector <int> &ids,
         vector <int> &lowlink, stack <int> &s, vector <bool> &is_on_stack,
         vector <int> &cids, int &cnum) {
	ids[v] = curid++;
	lowlink[v] = ids[v];
	s.push(v);
	is_on_stack[v] = true;

	for (int i = 0; i < g[v].size(); ++i) {
		int w = g[v][i];
		if (ids[w] == 0) {
			dfs(w, g, curid, ids, lowlink, s, is_on_stack, cids, cnum);
			// We can reaach w => we can reach minimal vertex for w
			// (which's the one stored in lowlink)
			lowlink[v] = min(lowlink[v], lowlink[w]);
		} else if (is_on_stack[w]) {
			// w is in the current SCC
			lowlink[v] = min(lowlink[v], ids[w]);
		}
	}

	if (ids[v] == lowlink[v]) {
		++cnum;
		int curv = s.top();
		while (curv != v) {
			cids[curv] = cnum;
			is_on_stack[curv] = false;
			s.pop();
			curv = s.top();
		}
		cids[v] = cnum;
		is_on_stack[v] = false;
		s.pop();
	}
}

// Tarjan's algorithm
vector <int> findStronglyConnectedComponents(const vector <vector <int>> &g) {
	int curid = 1;
	vector <int> ids(g.size(), 0);
	vector <int> lowlink(g.size(), 0);
	stack <int> s;
	vector <bool> is_on_stack(g.size(), false);
	vector <int> cids(g.size(), 0);
	int cnum = 0;
	for (int i = 0; i < g.size(); ++i) {
		if (ids[i] == 0) {
			dfs(i, g, curid, ids, lowlink, s, is_on_stack, cids, cnum);
		}
	}
	return cids;
}

void printAnswer(const string &file_name, const vector <vector <int>> &g) {
	ofstream out(file_name);
	for (const auto& n : g) {
		out << n.size() << " ";
		for (auto v : n) {
			out << (v - g.size() + 1) << " ";
		}
		out << endl;
	}
}

int main() {
	vector <vector <int>> g;
	vector <int> perfect_matching;
	readGraph("king.in", g, perfect_matching);
	vector <int> cids = findStronglyConnectedComponents(g);

	// Reorient edges in perfect matching
	for (int i = perfect_matching.size(); i < g.size(); ++i) {
		int w = g[i][0];
		g[w].push_back(i);
		g[i].clear();
	}

	// Edge is unmatched (i.e. doesn't lie in any of the pefect matchings)
	// iff it connects two differenct SCCs. We want to remove edges with that property.
	for (int i = 0; i < perfect_matching.size(); ++i) {
		vector <int> new_neighbour;
		for (int j = 0; j < g[i].size(); ++j) {
			if (cids[i] == cids[g[i][j]] || perfect_matching[i] == g[i][j]) {
				new_neighbour.push_back(g[i][j]);
			}
		}
		g[i] = new_neighbour;
	}

	g.resize(g.size() / 2);

	printAnswer("king.out", g);
	return 0;
}
