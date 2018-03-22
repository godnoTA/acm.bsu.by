#include <iostream>
#include <fstream>

int main() {
    std::ifstream input("input.txt");
    std::ofstream output("output.txt");
    int n,m;
    input >> n >> m;
    int**matrix = new int*[n];
    for(int i = 0; i < n; i++){
        matrix[i] = new int[n];
        for(int j = 0; j < n; j++){
            matrix[i][j] = 0;
        }
    }
    int first,second;
    for(int i = 0; i < m; i++){
        input >> first >> second;
        first--;
        second--;
        matrix[first][second] = 1;
        matrix[second][first] = 1;
    }
    for(int i = 0; i < n; i++){
        for(int j = 0; j < n; j++){
            output << matrix[i][j] << " ";
        }
        output << std::endl;
    }
}