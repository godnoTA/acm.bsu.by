#include <iostream>
#include <string.h>
#include <stack>
#include <fstream>
#include <vector>
#include <deque>
const int N = 27;

struct vertex{
    int character;
    std::vector<vertex> adjacent;
    vertex(int character){
        this->character = character;
    }
    vertex(){

    }
};

struct edge{
    int from;
    int to;
    edge(int from, int to){
        this->from = from;
        this->to = to;
    }
};

void dfs_2(int x, vertex inversed[N], int marks[N]){
    marks[x] = 1;

    for (int i = 0; i < inversed[x].adjacent.size(); i++) {
        if (marks[inversed[x].adjacent[i].character] == 0) {
            dfs_2(inversed[x].adjacent[i].character, inversed, marks);
        }
    }
}

void dfs_1(int x, std::stack<vertex>& stack, vertex vertices[N], int marks[N]){
    marks[x] = 1;
    for (int i = 0; i < vertices[x].adjacent.size(); i++) {
        if (marks[vertices[x].adjacent[i].character] == 0)
            dfs_1(vertices[x].adjacent[i].character, stack, vertices, marks);
    }
    stack.push(vertices[x]);
}

int components_count(std::stack<vertex> &stack, vertex inversed[N], int marks[N]){
    int stamp = 0;
    while (!stack.empty()){
        vertex& vertex = stack.top();
        if (marks[vertex.character] == 0){
            stamp++;
            dfs_2(vertex.character, inversed, marks);
        }
        stack.pop();
    }
    return stamp;
}

bool is_validate(int indeg[N], int outdeg[N], std::stack<vertex>& stack, vertex inversed[N], int marks[N]){
    for (int i = 1; i <= N; i++){
        if (indeg[i] != outdeg[i])
            return false;
    }

    int cmps_count = components_count(stack, inversed, marks);

    return cmps_count == 1;
}


/*std::stack<int> eulerian_cycle(vertex vertices[N], int initialised[N]){
    std::stack<int> stack;
    std::stack<int> queue_stack_;
    int v;
    for (int i = 1; i <= N; i++) {
        if (initialised[i] == 1) {
            v = i;
            break;
        }
    }

    stack.push(v);

    while (!stack.empty()){
        int w = stack.top();
        if (!vertices[w].adjacent.empty()){
            stack.push(vertices[w].adjacent.back().character);
            vertices[w].adjacent.pop_back();
        }
        if (w == stack.top()){
            stack.pop();
            queue_stack_.push(w);
        }
    }

    return queue_stack_;
}*/


std::vector<edge> euler_circuit(vertex vertices[N], int initialised[N]){
    std::stack<int> path;

    std::vector<int> circuit;
    int v;
    for (int i = 1; i <= N; i++) {
        if (initialised[i] == 1) {
            v = i;
            break;
        }
    }

    int current_vertice = v;
    path.push(current_vertice);
    while (!path.empty())
    {

        if (!vertices[current_vertice].adjacent.empty())
        {
            path.push(current_vertice);


            int aux_ = vertices[current_vertice].adjacent.back().character;

            vertices[current_vertice].adjacent.pop_back();
            current_vertice = aux_;
        }

        else
        {
            circuit.push_back(current_vertice);
            current_vertice = path.top();
            path.pop();
        }
    }

    std::vector<edge> edges;

    for (int i = circuit.size() - 1; i >= 1; i--)
    {
        edges.emplace_back(edge(circuit[i], circuit[i - 1]));
    }
    return edges;
}




int main(){
    const int shift = 96;
    std::stack<std::string>* table[N][N];
    std::ifstream input("input.txt");
    vertex vertices[N];
    vertex inversed[N];
    int indeg[N];
    int outdeg[N];
    int initialised[N];
    int marks[N];

    for (int i = 0; i < N; i++){
        indeg[i] = outdeg[i] = initialised[i] = marks[i] = 0;
        for (int j = 0; j < N; j++){
            table[i][j] = nullptr;
        }
    }

    int n;
    input >> n;

    for (int i = 0; i < n; i++){
        std::string str;
        input >> str;
        int first_char = (int) str[0] - shift;
        int last_char = (int) str[str.length() - 1] - shift;

        if (!initialised[first_char]){
            vertices[first_char] = vertex(first_char);
            inversed[first_char] = vertex(first_char);
        }
        if (!initialised[last_char]){
            vertices[last_char] = vertex(last_char);
            inversed[last_char] = vertex(last_char);
        }

        vertices[first_char].adjacent.emplace_back(vertex(last_char));
        inversed[last_char].adjacent.emplace_back(vertex(first_char));

        if (table[first_char][last_char] == nullptr){
            table[first_char][last_char] = new std::stack<std::string>();
        }
        table[first_char][last_char]->push(str);
        outdeg[first_char]++;
        indeg[last_char]++;
        initialised[first_char] = 1;
        initialised[last_char] = 1;
    }

    input.close();


    std::stack<vertex> stack_v;

    for (int i = 1; i <= N; i++){
        if (initialised[i] == 1 && marks[i] == 0){
            dfs_1(i, stack_v, vertices, marks);
        }
    }


    for (int &mark : marks) {
        mark = 0;
    }


    std::ofstream output("output.txt");

    if (is_validate(indeg, outdeg, stack_v, inversed, marks)){
        output << "Yes" << std::endl;
        std::vector<edge> circuit_edges = euler_circuit(vertices, initialised);

        for (edge& edge_ : circuit_edges) {
            std::string &str = table[edge_.from][edge_.to]->top();
            output << str << std::endl;
            table[edge_.from][edge_.to]->pop();
        }

        /*std::stack<int> p = eulerian_cycle(vertices, initialised);
        while (!p.empty()) {
            if (p.size() == 2){
                int from = p.top();
                p.pop();
                int to = p.top();
                p.pop();
                std::string &str = table[from][to]->top();
                output << str << std::endl;
            }
            else {
                int from = p.top();
                p.pop();
                int to = p.top();
                std::string &str = table[from][to]->top();
                output << str << std::endl;
                table[from][to]->pop();
            }
        }*/

    } else{
        output << "No";
    }

    output.close();
}