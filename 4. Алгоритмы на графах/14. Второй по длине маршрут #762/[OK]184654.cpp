#include <iostream>
#include <vector>
#include <algorithm>
#include <set>
#include <cstdio>
#include <fstream>

const long long INF = 1e18;

struct Edge {
    int adj_vertex;
    long long length;
    bool in_short_path;
    Edge(int vertex, long long len, bool flag = false) :
          adj_vertex(vertex), length(len), in_short_path(flag) {};
};

struct Path {
    long long dist;
    int from;
    int edge_id;
    Path(long long distance = INF, int v = -1,
              int e = -1) : dist(distance), from(v), edge_id(e) {}
};

class ShortestPath {
public:

    ShortestPath(std::vector< std::vector<Edge> > &g) : graph(g) {
        num_of_vertex = graph.size();
    }

    void findSecondPath(int start_vertex, int finish_vertex) {
        std::vector<Path> first =
                    findShortestPath(start_vertex, finish_vertex);

        Path cur = first[finish_vertex];
        while (cur.from != -1) {
            graph[cur.from][cur.edge_id].in_short_path = true;
            cur = first[cur.from];
        }
        buildSecondLayer();

        std::vector<Path> second = findShortestPath(
                     start_vertex + num_of_vertex, finish_vertex);
        second_path_len = second[finish_vertex].dist;
        second_path = getPath(second, finish_vertex);
    }

    long long getSecondLength() const {
        return second_path_len;
    }

    std::vector<int> getSecondPath() const {
        return second_path;
    }

    void buildSecondLayer() {
        for (int i = 0; i < num_of_vertex; i++) {
            std::vector<Edge> new_vertex = graph[i];
            for (int j = 0; j < graph[i].size(); j++) {
                int ind = j;
                if (!graph[i][j].in_short_path) {
                    new_vertex.push_back(new_vertex[j]);
                    ind = new_vertex.size() - 1;
                }
                new_vertex[ind].adj_vertex += num_of_vertex;
            }
            graph.push_back(std::move(new_vertex));
        }
    }

    std::vector<int> getPath(
                        const std::vector<Path> &prev, int finish) {
        std::vector<int> path;
        int v = finish;
        while (v != -1) {
            int ind = (v >= num_of_vertex) ? v - num_of_vertex : v;
            path.push_back(ind + 1);
            v = prev[v].from;
        }
        std::reverse(path.begin(), path.end());
        return path;
    }

private:
    std::vector<Path> findShortestPath(int start_v, int finish_v) {
        std::vector<Path> path(graph.size());
        std::set< std::pair<long long, int> > q;
        path[start_v] = Path(0);
        q.insert({0ll, start_v});

        while (!q.empty()) {
            int vertex = q.begin()->second;
            long long dist = q.begin()->first;
            q.erase(q.begin());

            for (int i = 0; i < graph[vertex].size(); i++) {
                int to = graph[vertex][i].adj_vertex;
                long long new_dist = graph[vertex][i].length + dist;
                if (path[to].dist > new_dist) {
                    q.erase({path[to].dist, to});
                    path[to] = Path(new_dist, vertex, i);
                    q.insert({path[to].dist, to});
                }
            }
        }

        return path;
    }

    int num_of_vertex;
    long long second_path_len;
    std::vector<int> second_path;
    std::vector< std::vector<Edge> > graph;
};

int main()
{
    std::ifstream fin("input.in");
    std::ofstream fout("output.out");

    int num_of_vertex, num_of_edges;
    fin >> num_of_vertex >> num_of_edges;

    int u, v;
    long long dist;
    std::vector< std::vector<Edge> > graph(num_of_vertex);
    for (int i = 0; i < num_of_edges; i++) {
        fin  >> v >> u >> dist;
        graph[--v].push_back(Edge(--u, dist));
    }

    ShortestPath shortest_path(graph);

    int start_vertex, finish_vertex;
    fin >> start_vertex >> finish_vertex;

    shortest_path.findSecondPath(start_vertex - 1, finish_vertex - 1);

    fout << shortest_path.getSecondLength() << "\n";
    std::vector<int> path = shortest_path.getSecondPath();
    for (int i = 0; i < path.size(); i++) {
        fout << path[i];
        if (i < path.size() - 1) {
            fout << " ";
        }
    }

    return 0;
}
