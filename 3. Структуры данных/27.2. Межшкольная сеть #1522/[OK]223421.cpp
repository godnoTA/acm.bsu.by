#include <iostream>
#include <fstream>
#include <stack>
#include <math.h>
#include <algorithm>
#include <vector>



const int N = 101;

void search(int x, bool& sfn, int& w, int flags[N], int sources[N], int sifts[N], int matrix[N][N], int n) {
    if (flags[x] == 0) {
        if(!sifts[x] && sources[x]){
            w = x;
            sfn = false;
        }
        flags[x] = 1;
        for (int i = 1; i <= n; i++) {
            if (matrix[x][i] && sfn) {
                search(i, sfn, w, flags, sources, sifts, matrix, n);
            }
        }

    }
}


struct sc_component{
    int num;
    std::vector<int> vertices;
};


void dfs_1(int x, std::stack<int>& stack, int matrix[N][N], int n, int marks[N]){
    marks[x] = 1;
    for (int i = 1; i <= n; i++) {
        if (matrix[x][i] != 0 && marks[i] == 0)
            dfs_1(i, stack, matrix, n, marks);
    }
    stack.push(x);
}

void dfs_2(int vertex, int matrix[N][N], int n, int t, int marks[N], int components[N], sc_component &cmp){
    marks[vertex] = 1;
    components[vertex] = t;
   // cmp.vertices.push_back(vertex);
    for (int i = 1; i <= n; i++) {
        if (matrix[i][vertex] && marks[i] == 0)
            dfs_2(i, matrix, n, t, marks, components, cmp);
    }
}

int mark_components(std::stack<int> &stack, int martix[N][N], int marks[N], int n, int components[N], std::vector<sc_component> &cmps){
    int t = 0;
    while (! stack.empty()){
        int vertex = stack.top();
        //std::vector<sc_component> sc_components;
        sc_component cmp;
        // cmp.vertices.push_back(vertex);
        if (marks[vertex] == 0){
            t++;
            dfs_2(vertex, martix, n, t, marks, components, cmp);
          //  cmps.push_back(cmp);
        }
        stack.pop();
    }
    return t;
}

int main() {
    int n;

    std::ifstream file("input.txt");

    file >> n;

    int vertices_types[N];


    int flags[N];
    int source_aux[N];
    int sifts_aux[N];

    int marks[N];
    int adjacency_matrix[N][N];
    int components_array[N];
    int SCC_adjacency_mtrx[N][N];
    int sources[N];
    int sifts[N];


    for (int i = 0; i < N; i++){
        for (int j = 0; j < N; j++){
            adjacency_matrix[i][j] = 0;
            SCC_adjacency_mtrx[i][j] = 0;
        }
        flags[i] = 0;
        source_aux[i] = 0;
        sifts_aux[i] = 0;
        marks[i] = 0;
        components_array[i] = 0;
        vertices_types[i] = 0;
        sources[i] = 0;
        sifts[i] = 0;
    }

    int aux;


    for (int i = 1; i <= n; i++) {
        file >> aux;
        while (aux) {
            adjacency_matrix[i][aux] = 1;
            file >> aux;
        }
    }

    std::stack<int> stack1;

    for (int i = 1; i <= n; i++){
        if (marks[i] == 0){
            dfs_1(i, stack1, adjacency_matrix, n, marks);
        }
    }

    int u = stack1.size();


    for (int i = 1; i <= n; ++i) marks[i] = 0;


    std::vector<sc_component> components;
   // components.push_back(sc_component());

    int components_num = mark_components(stack1, adjacency_matrix, marks, n, components_array, components);



    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            if (components_array[i] != components_array[j] && adjacency_matrix[i][j] != 0)
                SCC_adjacency_mtrx[components_array[i]][components_array[j]] = 1;
        }
    }


    int ans = 0;


    for (int i = 1; i <= components_num; i++) {
        for (int j = 1; j <= components_num; j++) {
            if (SCC_adjacency_mtrx[j][i] == 1) {
               // sifts[i]++;
                sources[i]++;
            }
        }
        if (sources[i] == 0) ans++;
    }

    std::cout << ans;

    for (int i = 1; i <= components_num; i++) {
        for (int j = 1; j <= components_num; j++) {
            if (SCC_adjacency_mtrx[i][j] == 1) {
               // sources[i]++;
                sifts[i]++;
            }
        }
    }


    std::vector< std::vector<int> > SCC_components;

    std::vector<int> isolated_vertices;

    std::stack<int> source_stack;



    std::vector<int> aux_;

    SCC_components.push_back(aux_);

    for (int i = 1; i <= components_num; i++) {
        for (int j = 1; j <= n; j++) {
            if (components_array[j] == i) {
                aux_.push_back(j);
            }
        }
        SCC_components.push_back(aux_);
        aux_.clear();
    }



    for (int i = 1; i <= components_num; i++) {
        if (sifts[i] && !sources[i]) {
            source_stack.push(i);
            vertices_types[i] = 1;
        }
        else if (!sources[i] && !sifts[i]) {
            isolated_vertices.push_back(i);
            vertices_types[i] = 3;
        }
        else if(sources[i] && !sifts[i]) {
            vertices_types[i] = 2;
        }
        flags[i] = 0;
    }

    int q = isolated_vertices.size();
    ;

    std::vector<int> sources_1;
    std::vector<int> sifts_1;

    int p = 0;

    

    while(!source_stack.empty()){
        int vertex = source_stack.top();
        if (flags[vertex] == 0) {
            int w = 0;
            bool snf = true;
            search(vertex, snf, w, flags, sources, sifts, SCC_adjacency_mtrx, components_num);
            if (w != 0) {
                p++;
                sources_1.push_back(vertex);

                source_aux[vertex] = 1;
                sifts_1.push_back(w);

                sifts_aux[w] = 1;
            }
        }
        source_stack.pop();
    }


    for (int i = 1; i <= components_num; i++) {
        if (vertices_types[i] == 1 && source_aux[i] == 0)
            sources_1.push_back(i);
        if (vertices_types[i] == 2 && sifts_aux[i] == 0)
            sifts_1.push_back(i);
    }

    int s = sources_1.size();
    int t = sifts_1.size();

    std::vector< std::pair<int, int> > edges;


     if (s <= t && s) {
         for (int i = 1; i < p; i++) {
             edges.push_back(std::make_pair(SCC_components[sifts_1[i - 1]][0], SCC_components[sources_1[i]][0]));
         }
         for (int i = p + 1; i <= s; i++) {
             edges.push_back(std::make_pair(SCC_components[sifts_1[i - 1]][0], SCC_components[sources_1[i - 1]][0]));
         }
         if (q == 0 && s == t) {
             edges.push_back(std::make_pair(SCC_components[sifts_1[p - 1]][0], SCC_components[sources_1[0]][0]));
         }
         if (s < t) edges.push_back(std::make_pair(SCC_components[sifts_1[p - 1]][0], SCC_components[sifts_1[s]][0]));

         for (int i = s + 1; i < t; i++) {
             edges.push_back(std::make_pair(SCC_components[sifts_1[i - 1]][0], SCC_components[sifts_1[i]][0]));
         }

         if (q == 0 && s < t) {
             edges.push_back(std::make_pair(SCC_components[sifts_1[t - 1]][0], SCC_components[sources_1[0]][0]));
         }
     }

     if (s > t && t) {
         for (int i = 1; i < p; i++) {
             edges.push_back(std::make_pair(SCC_components[sifts_1[i - 1]][0], SCC_components[sources_1[i]][0]));
         }
         for (int i = p + 1; i <= t; i++) {
             edges.push_back(std::make_pair(SCC_components[sifts_1[i - 1]][0], SCC_components[sources_1[i - 1]][0]));
         }
         if (q == 0 && s == t) {
             edges.push_back(std::make_pair(SCC_components[sifts_1[p - 1]][0], SCC_components[sources_1[0]][0]));
         }
         edges.push_back(std::make_pair(SCC_components[sources_1[p - 1]][0], SCC_components[sources_1[t]][0]));
         for (int i = t + 1; i < s; i++) {
             edges.push_back(std::make_pair(SCC_components[sources_1[i - 1]][0], SCC_components[sources_1[i]][0]));
         }
         if (q == 0) {
             edges.push_back(std::make_pair(SCC_components[sifts_1[t - 1]][0], SCC_components[sources_1[0]][0]));
         }

     }
     if (q && t) {
         if (s == t) {
             edges.push_back(std::make_pair(SCC_components[sifts_1[p - 1]][0], SCC_components[isolated_vertices[0]][0]));
         }
         else edges.push_back(std::make_pair(SCC_components[sifts_1[t - 1]][0], SCC_components[isolated_vertices[0]][0]));
         for (int i = 1; i < q; i++) {
             edges.push_back(std::make_pair(SCC_components[isolated_vertices[i - 1]][0], SCC_components[isolated_vertices[i]][0]));
         }
         edges.push_back(std::make_pair(SCC_components[isolated_vertices[q - 1]]
                                           [0], SCC_components[sources_1[0]][0]));

     }
     if (q && !t) {

         for (int i = 1; i < q; i++) {
             edges.push_back(std::make_pair(SCC_components[isolated_vertices[i - 1]]
                                            [0], SCC_components[isolated_vertices[i]][0]));
         }
         edges.push_back(std::make_pair(SCC_components[isolated_vertices[q - 1]]
                                        [0], SCC_components[isolated_vertices[0]][0]));
     }

    std::ofstream fl("output.txt");
    fl << ans << std::endl;

    if (components_num == 1){
        fl << 0 << std::endl;
    }
    else {
        fl << edges.size() << std::endl;

        for (int i = 0; i < edges.size(); i++) {
            fl << edges[i].first << " " << edges[i].second << std::endl;
        }
    }

}

