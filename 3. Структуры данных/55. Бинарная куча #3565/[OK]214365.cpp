#include <iostream>
#include <fstream>
#include <cmath>

int main() {
    std::ifstream input("input.txt");
    std::ofstream output("output.txt");
    if(input.is_open()) {
        int num;
        input >> num;
        int*mas = new int[num];
        for(int i = 0; i < num; i++){
            input >> mas[i];
        }
        bool answer = true;
        for(int i = 1; i < num; i++){
            if(mas[i] < mas[(i-1)/2]) {
                answer = false;
                break;
            }
        }
        if(answer)
            output << "YES";
        else
            output << "NO";
    }
    return 0;
}