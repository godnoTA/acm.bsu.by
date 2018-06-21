#include <iostream>
#include <fstream>
#include <math.h>

int main() {
    std::ifstream input("in.dat");
    std::ofstream output("out.dat");

    long long maxWeight = 0;
    long long weight = 0;

    int num;

    input >> num;

    int sizeDemand = 0,sizeOffer = 0;
    long long *demand = new long long[num];
    long long *offer = new long long[num];
    long long *outputMas = new long long[num];

    long long tmp;
    for(int i = 0; i < num; i++){
        input >> tmp;
        if(tmp > 0){
            offer[sizeOffer++] = tmp;
        }else{
            demand[sizeDemand++] = tmp;
        }
    }

    int numDemand = 0, numOffer = 0, numOutput = 0;

    while(numDemand != sizeDemand){
        if(weight >= -demand[numDemand]){
            if(weight >= maxWeight){
                maxWeight = weight;
            }
            outputMas[numOutput++] = demand[numDemand];
            weight += demand[numDemand++];
        }else{
            outputMas[numOutput++] = offer[numOffer];
            weight += offer[numOffer++];
        }
    }
    output << maxWeight << std::endl;
    for(int i = 0; i < numOutput; i++){
        output << outputMas[i] << " ";
    }
}