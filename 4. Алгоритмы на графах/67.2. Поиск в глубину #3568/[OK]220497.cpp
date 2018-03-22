#include <iostream>
#include <fstream>

int smth(int& n,int*mas,int**matrix, int& indexQueue, int& vershina){
    mas[vershina] = indexQueue++;
    for(int i = 0; i < n;i++){
        if(matrix[vershina][i] == 1 && mas[i] == 0){
            indexQueue = smth(n,mas,matrix, indexQueue, i);
        }
    }
    return indexQueue;

}

int main() {
    std::ifstream input("input.txt");
    std::ofstream output("output.txt");
    int n;
    input >> n;
    int index = 1;
    int**matrix = new int*[n];
    int*mas = new int[n];
    for(int i = 0; i < n; i++){
        matrix[i] = new int[n];
        mas[i] = 0;
    }
    for(int i = 0; i < n; i++){
        for(int j = 0; j < n; j++){
            input >> matrix[i][j];
        }
    }
    for(int i = 0; i < n; i++){
        if(mas[i] == 0)
        smth(n,mas,matrix,index,i);
    }
    for(int i = 0;i < n;i++){
        output << mas[i] << " ";
    }
}