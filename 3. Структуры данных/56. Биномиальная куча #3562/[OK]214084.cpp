#include <iostream>
#include <fstream>
int main() {

    std::ifstream file("input.txt");
    std::ofstream fl("output.txt");
    long long n = 0;
    bool flag = false;
    file >> n;
    for (int i = 0; i < 64; i++){
        long long a = (n >> i) & 1;
        if (a == 1){
            flag = true;
            fl << i << std::endl;
        }
    }

    if (!flag){
        fl << -1;
    }

}