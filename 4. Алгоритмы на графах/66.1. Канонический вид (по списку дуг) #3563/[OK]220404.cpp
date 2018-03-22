#include <iostream>
#include <fstream>

int main() {
    std::ifstream input("input.txt");
    std::ofstream output("output.txt");
    int n;
    int first,second;
    input >> n;
    int*mas = new int[n];
    for(int i = 0; i < n; i++){
        mas[i] = 0;
    }
    for(int i = 1; i < n; i++){
        input >> first >> second;
        second--;
        mas[second] = first;
    }
    for(int i = 0; i < n; i++){
        output << mas[i] << " ";
    }
}