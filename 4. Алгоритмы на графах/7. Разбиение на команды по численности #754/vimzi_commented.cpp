#include <bits/stdc++.h>

using namespace std;

using adj_list = vector<vector<int>>;
using find_scc_result = pair<int, vector<int>>;

// найти все сильно связные компоненты в графе
// https://github.com/larandaA/alg-ds-snippets/blob/master/dfs-bfs.md#сильно-связные-компоненты
find_scc_result find_strongly_connected_components(adj_list &adjacency_list, adj_list &transposed_adjacency_list);

// разделить векор (индекс - номер, значение - компонента) на несколько векторов
vector<vector<int>> separate_components(int n_components, vector<int> &vertex_components);

// разбить компоненту на доли
// возвр. - двудольность графа
bool partition_component(int v, int component, vector<int> &components, adj_list &adjacency_list, vector<bool> &visited,
                         vector<bool> &part);

int main() {
    freopen("input.in", "r", stdin);
    freopen("output.out", "w", stdout);
    int n;
    cin >> n;
    if (n == 1) {
        cout << "NO\n";
        return 0;
    }
    adj_list adjacency_list(n, vector<int>());
    adj_list transposed_adjacency_list(n, vector<int>());
    int tmp;
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            cin >> tmp;
            if (!tmp && i != j) { // мы работаем с обратным графом: нет ребра - знакомы
                adjacency_list[i].emplace_back(j);
                transposed_adjacency_list[j].emplace_back(i);
            }
        }
    }
    auto[n_components, vertex_components] = find_strongly_connected_components(adjacency_list,
                                                                               transposed_adjacency_list);
    auto connected_components = separate_components(n_components, vertex_components);
    vector<pair<vector<int>, vector<int>>> component_partitions(n_components);

    // разделяем все компоненты связности на доли
    // если хоть одна из них не двудольный граф, то решения нет
    for (int i = 0; i < n_components; ++i) {
        vector<bool> visited(n);
        vector<bool> part(n);
        auto result = partition_component(connected_components[i][0], i, vertex_components, adjacency_list, visited,
                                          part);
        if (!result) {
            cout << "NO\n";
            return 0;
        }
        for (int j = 0; j < part.size(); ++j) {
            if (vertex_components[j] == i) {
                if (part[j])
                    component_partitions[i].first.emplace_back(j);
                else
                    component_partitions[i].second.emplace_back(j);
            }
        }
        visited.assign(n, false);
    }


    auto sort_predicate = [](const pair<vector<int>, vector<int>> &A, const pair<vector<int>, vector<int>> &B) {
        return max(A.first.size(), A.second.size()) > max(B.first.size(), B.second.size());
    };
    sort(component_partitions.begin(), component_partitions.end(), sort_predicate);

    vector<int> teamA;
    vector<int> teamB;
    teamA.reserve(n);
    teamB.reserve(n);

    // Собираем команды жадным алгоритмом
    // Начинаем с самой крупной доли и всегда добавляем большую долю к меньшей команде, а противоположную - к большей
    for (auto &cp : component_partitions) {
        auto &[p1, p2] = cp;
        if (teamA.size() > teamB.size()) {
            if (p1.size() > p2.size()) {
                teamA.insert(teamA.end(), p2.begin(), p2.end());
                teamB.insert(teamB.end(), p1.begin(), p1.end());
            } else {
                teamA.insert(teamA.end(), p1.begin(), p1.end());
                teamB.insert(teamB.end(), p2.begin(), p2.end());
            }
        } else {
            if (p1.size() > p2.sie()) {
                teamA.insert(teamA.end(), p1.begin(), p1.end());
                teamB.insert(teamB.end(), p2.begin(), p2.end());
            } else {
                teamA.insert(teamA.end(), p2.begin(), p2.end());
                teamB.insert(teamB.end(), p1.begin(), p1.end());
            }
        }
    }
    if (teamA.size() < teamB.size()) {
        swap(teamA, teamB);
    }
    if ((double) teamA.size() / (double) teamB.size() > 2.0) {
        cout << "NO\n";
        return 0;
    }
    sort(teamA.begin(), teamA.end());
    cout << "YES\n";
    for (int i = 0; i < teamA.size(); ++i) {
        cout << teamA[i] + 1 << " \n"[i == teamA.size() - 1];
    }
}


void dfs1(int v, adj_list &adjacency_list, vector<bool> &visited, vector<int> &ordered) {
    visited[v] = true;
    for (auto &u : adjacency_list[v]) {
        if (!visited[u])
            dfs1(u, adjacency_list, visited, ordered);
    }
    ordered.emplace_back(v);
}


void
dfs2(int v, adj_list &adjacency_list_transposition, vector<bool> &visited, vector<int> &components, int n_component) {
    visited[v] = true;
    components[v] = n_component;
    for (auto &u : adjacency_list_transposition[v]) {
        if (!visited[u]) {
            dfs2(u, adjacency_list_transposition, visited, components, n_component);
        }
    }
}


find_scc_result find_strongly_connected_components(adj_list &adjacency_list, adj_list &transposed_adjacency_list) {
    size_t n = adjacency_list.size();
    vector<bool> visited(n);
    vector<int> ordered(0);
    for (int v = 0; v < n; ++v) {
        if (!visited[v])
            dfs1(v, adjacency_list, visited, ordered);
    }
    visited.assign(n, false);
    int n_components = 0;
    vector<int> component(n);
    for (auto iv = rbegin(ordered); iv != rend(ordered); ++iv) {
        if (!visited[*iv]) {
            dfs2(*iv, transposed_adjacency_list, visited, component, n_components);
            ++n_components;
        }
    }
    return {n_components, component};
}


vector<vector<int>> separate_components(int n_components, vector<int> &vertex_components) {
    vector<vector<int>> connected_components(n_components);
    for (int i = 0; i < vertex_components.size(); ++i) {
        connected_components[vertex_components[i]].emplace_back(i);
    }
    return connected_components;
}


bool partition_component(int v, int component, vector<int> &components, adj_list &adjacency_list, vector<bool> &visited,
                         vector<bool> &part) {
    visited[v] = true;
    bool is_bipartite = true;
    for (auto &u : adjacency_list[v]) {
        if (components[u] == component) {
            if (visited[u] && part[u] == part[v]) {
                is_bipartite = false;
            }
            if (!visited[u]) {
                part[u] = !part[v];
                is_bipartite = partition_component(u, component, components, adjacency_list, visited, part);
            }
        }
    }
    return is_bipartite;
}
