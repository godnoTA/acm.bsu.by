#include <iostream>
#include <fstream>

int buttons;
int letters;
long long* weighs;
long long** matrix;
int k;

void func(int n, int m){
    if (n == 1){
        if (m == 1){
            matrix[n - 1][m - 1] = weighs[m - 1];
        }
        else matrix[n - 1][m - 1] = matrix[n - 1][m - 2] + weighs[m - 1]  * m;
    }
    else {
        if (n > m){
            matrix[n - 1][m - 1] = matrix[m - 1][m - 1];
        }
        else {
            int mn = k;
            long long res = matrix[n - 2][m - 2] + weighs[m - 1];
            long long sum = weighs[m - 1];
            long long acc = weighs[m - 1];
            for (int i = k; i > 0; i--){
                sum += weighs[m - 1 - (k - i) - 1] + acc;
                acc += weighs[m - 1 - (k - i) - 1];
                if (matrix[n - 2][m - 1 - (k - i + 1) - 1] + sum < res){
                    res = matrix[n - 2][m - 1 - (k - i + 1) - 1] + sum;
                    mn = k - i + 1;
                }
            }
            matrix[n - 1][m - 1] = res;
            k = mn + 1;
        }
    }
}

int main() {
    std::ifstream file("in.txt");

    file >> buttons;
    file >> letters;

    weighs = new long long[letters];
    matrix = new long long*[buttons];
    for (int i = 0; i < buttons; i++){
        matrix[i] = new long long[letters];
    }

    for (int i = 0; i < letters; i++){
        file >> weighs[i];
    }

    for (int i = 1; i < buttons + 1; i++){
        k = 0;
        for (int j = 1; j < letters + 1; j++){
            func(i, j);
        }
    }

    std::ofstream fl("out.txt");
    fl << matrix[buttons - 1][letters - 1];

    for (int i = 0; i < buttons; i++){
        for (int j = 0; j < letters; j++) std::cout << matrix[i][j] << "  ";
        std::cout << std::endl;
    }
}