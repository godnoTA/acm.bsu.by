#include <iostream>
#include <fstream>
#include <cmath>

int main() {
    std::ifstream input("input.txt");
    std::ofstream output("output.txt");
    if(input.is_open()) {
        int_fast64_t num;
        input >> num;
        for(int i = 0; num != 0; i++, num/=2){
            if(num % 2 == 1 ){
                output << i << " ";
            }
        }
    }
    return 0;
}