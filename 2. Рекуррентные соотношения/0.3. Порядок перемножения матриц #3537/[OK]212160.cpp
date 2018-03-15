#include <iostream>
#include <fstream>


int task03(int *matrixN, int *matrixM, int size) {
    int **matrix = new int *[size];
    for (int i = 0; i < size; i++) {
        matrix[i] = new int[size];
        matrix[i][i] = 0;
    }
    int min;
    int tmpMin;
    for (int i = 1; i < size; i++) {
        for (int j = 0; j < size - i; j++) {
            min = INT32_MAX;
            for (int k = 0; k < i; k++) {
                tmpMin = matrix[j][j + k] + matrix[j + k + 1][i + j] + matrixN[j] * matrixM[j + k] * matrixM[i + j];
                if (tmpMin < min)
                    min = tmpMin;
            }
            matrix[j][j + i] = min;
        }
    }
    return matrix[0][size - 1];
}

int main() {
    std::ifstream input("input.txt");
    std::ofstream output("output.txt");
    int tmp;
    input >> tmp;
    int* matrixN = new int[tmp];
    int* matrixM = new int[tmp];
    for(int i = 0; i < tmp; i++){
        input >> matrixN[i];
        input >> matrixM[i];
    }
    output << task03(matrixN,matrixM,tmp);
}