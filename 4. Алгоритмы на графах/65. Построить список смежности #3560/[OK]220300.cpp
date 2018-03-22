#include <iostream>
#include <fstream>

int main() {
    std::ifstream input("input.txt");
    std::ofstream output("output.txt");
    int n,m;
    input >> n >> m;
    int* mas = new int[n];
    for(int i = 0; i < n; i++){
        mas[i] = 0;
    }
    int first,second;
    for(int i = 0; i < m; i++){
        input >> first >> second;
        first--;
        second--;
        mas[first]++;
        mas[second]++;
    }
    input.close();
    input.open("input.txt");
    input >> n >> m;
    int**matrix = new int*[n];
    for(int i = 0; i < n; i++){
        matrix[i] = new int[mas[i] + 1];
        matrix[i][0] = 0;
    }
    for(int i = 0; i < m; i++){
        input >> first >> second;
        first--;
        second--;
        matrix[first][0]++;
        matrix[first][matrix[first][0]] = second + 1;
        matrix[second][0]++;
        matrix[second][matrix[second][0]] = first + 1;
    }
    for(int i = 0; i < n; i++){
        for(int j = 0; j <= matrix[i][0]; j++){
            output << matrix[i][j] << " ";
        }
        output << std::endl;
    }
}