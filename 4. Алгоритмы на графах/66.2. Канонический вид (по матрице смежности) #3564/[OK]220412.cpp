#include <iostream>
#include <fstream>

int main() {
    std::ifstream input("input.txt");
    std::ofstream output("output.txt");
    int n;
    input >> n;
    int tmp;
    int*mas = new int[n];
    for(int i = 0; i < n;i++){
        mas[i]=0;
    }
    for(int i = 0; i < n; i++){
        for(int j = 0; j < n; j++){
            input >> tmp;
            if(tmp == 1){
                mas[j] = i + 1;
            }
        }
    }
    for(int i = 0; i < n; i++){
        output << mas[i] << " ";
    }
}