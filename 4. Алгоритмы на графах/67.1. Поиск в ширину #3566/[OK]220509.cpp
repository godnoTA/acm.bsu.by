#include <iostream>
#include <fstream>

int main() {
    std::ifstream input("input.txt");
    std::ofstream output("output.txt");
    int n;
    input >> n;
    int**matrix = new int*[n];
    int *masQueue = new int[n];
    masQueue[0] = 0;
    int indexQueue = 0;
    int endQueue = 1;
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
    bool isHere = false;
    while(endQueue != n){
        for(int i = 0; i < n; i++){
            if(matrix[masQueue[indexQueue]][i] == 1){
                isHere = false;
                for(int j = 0; j < endQueue;j++){
                    if(i == masQueue[j]){
                        isHere = true;
                        break;
                    }
                }
                if(!isHere)
                    masQueue[endQueue++] = i;
            }
        }
        mas[masQueue[indexQueue]] = indexQueue + 1;
        indexQueue++;
        if(indexQueue == endQueue){
            for(int i = 0; i < n; i++){
                if(mas[i]==0){
                    masQueue[endQueue++] = i;
                    break;
                }
            }
        }
    }
    for(int i = indexQueue; indexQueue < endQueue;indexQueue++)
        mas[masQueue[indexQueue]] = indexQueue + 1;
    for(int i = 0; i < n; i++){
        if(mas[i]==0)
            mas[i] = ++indexQueue;
    }
    for(int i = 0;i < n;i++){
        output << mas[i] << " ";
    }
}