#include <iostream>
#include <fstream>

int main() {
    std::ifstream input("expo.in");
    std::ofstream output("expo.out");

    int n,m;
    input >> n;
    input >> m;
    n++;
    bool* masA = new bool[n];
    int answers = 0;
    for(int i = 1; i < n; i++){
        masA[i] = false;
    }
    int x,y;
    for(int i = 0; i < m; i++){
        input >> x >> y;
        if(!masA[x] && !masA[y]){
            masA[x] = true;
            masA[y] = true;
            answers+=2;
        }
    }
    output << answers << std::endl;
    for(int i = 1; i < n; i++){
        if(masA[i]){
            output << i << " ";
        }
    }
    return 0;
}