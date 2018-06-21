//
// Created by aleksei on 27/04/18.
//

#include <cstring>
#include <fstream>

#include <iostream>
#include <climits>
#include <vector>
#include <set>



int main(int argc, char* argv[]){
    std::ofstream output("output.txt");
    std::ifstream input("input.txt");

    int n;
    int m;


    input >> n;
    input >> m;


    long long threshhold = LLONG_MAX;

    long long* distances = new long long[n];


    std::vector<std::vector<std::pair<int, long long >>> vertices(n);


    for (int i = 0; i < n; i++){
        distances[i] = threshhold;
    }


    for (int i = 1; i <= m; i++){

        int a, b, w;

        input >> a;
        input >> b;
        input >> w;

        vertices[a - 1].push_back(std::make_pair<int, long long >(b - 1, w));
        vertices[b - 1].push_back(std::make_pair<int, long long >(a - 1, w));

    }


    std::set<std::pair<long long, int >> set;

    distances[0] = 0;

   // pq.push(std::make_pair(0, 0));

    set.insert(std::make_pair(0, 0));

    while (!set.empty()) {
        auto u = set.begin();
        set.erase(u);
      //  int value = u.getValue();

        auto v_num = u->second;

        auto dist = u->first;

        for (int i = 0; i < vertices[v_num].size(); i++) {
            auto v__ = vertices[v_num][i].first;
            auto len = vertices[v_num][i].second;


            if (distances[v__] > distances[v_num] + len) {
                set.erase(std::make_pair(distances[v__], v__));
                distances[v__] = distances[v_num] + len;
             //   pq.push(std::make_pair(distances[v__], v__));
                set.insert(std::make_pair(distances[v__], v__));
            }
        }
    }

    output << distances[n - 1];



}
