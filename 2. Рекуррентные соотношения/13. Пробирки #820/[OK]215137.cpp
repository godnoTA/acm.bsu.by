#include <iostream>
#include <fstream>
#include <vector>

struct cell{
    bool marked;
    int step;
    bool first = true;
};

int x;
int* delimeters;
int sz;
int mn = -1;
bool flag = true;
cell matrix[101][101];


void mark(int fst, int scnd, int step){

    if (step >= mn && mn != -1) return;

    if (matrix[fst][scnd].marked) {
        if (step >= matrix[fst][scnd].step) {
            return;
        }
    }

        if (100 - fst - scnd == x) {
            if (flag) {
                mn = step;
                flag = !flag;
            } else if (step < mn) mn = step;
        }

        if (mn == 0)
            return;

        matrix[fst][scnd].marked = true;
        matrix[fst][scnd].step = step;

        // 1 -> 2 (2 up)
        for (int i = 0; i < sz; i++) {
            if (scnd < delimeters[i]) {
                int mes = delimeters[i] - scnd;
                if (fst >= mes) mark(fst - mes, scnd + mes, step + 1);
            }
        }

        // 2 -> 1 (1 up)
        for (int i = 0; i < sz; i++) {
            if (fst < delimeters[i]) {
                int mes = delimeters[i] - fst;
                if (scnd >= mes) mark(fst + mes, scnd - mes, step + 1);
            }
        }

        // 1 -> 3
        for (int i = 0; i < sz; i++) {
            if (fst > delimeters[i]) {
                int mes = fst - delimeters[i];
                mark(delimeters[i], scnd, step + 1);
            }
        }

        // 2 -> 3
        for (int i = 0; i < sz; i++) {
            if (scnd > delimeters[i]) {
                int mes = scnd - delimeters[i];
                mark(fst, delimeters[i], step + 1);
            }
        }


        // 1 -> 2 (1 down)
        for (int i = 0; i < sz; i++){
            if (fst > delimeters[i]){
                int mes = fst - delimeters[i];
                mark(delimeters[i], scnd + mes, step + 1);
            }
        }

        // 2 -> 1 (2 down)
        for (int i = 0; i < sz; i++){
            if (scnd > delimeters[i]){
                int mes = scnd - delimeters[i];
                mark(fst + mes, delimeters[i], step + 1);
            }
        }

        // 3 -> 2
        for (int i = 0; i < sz; i++) {
            if (scnd < delimeters[i]) {
                int mes = delimeters[i] - scnd;
                if (100 - fst - scnd >= mes) mark(fst, delimeters[i], step + 1);
            }
        }

        // 3 -> 1
        for (int i = 0; i < sz; i++) {
            if (fst < delimeters[i]) {
                int mes = delimeters[i] - fst;
                if (100 - fst - scnd >= mes) mark(delimeters[i], scnd, step + 1);
            }
        }

        // 3 -> 1(all)
        if (100 - fst - scnd != 0) {
            mark(100 - scnd, scnd, step + 1);
        }

        // 3 -> 2(all)
        if (100 - fst - scnd != 0) {
            mark(fst, 100 - fst, step + 1);
        }


}


int main() {

    std::ifstream file("in.txt");
    std::ofstream fl("out.txt");
    int a1 = 0;
    int a2 = 0;
    file >> x;
    file >> a1;
    file >> a2;
    std::vector<int> vector;

    int y;
    while (file >> y){
        vector.push_back(y);
    }
    delimeters = vector.data();
    sz = vector.size();

    mark(a1, a2, 0);

    if (mn == -1) fl << "No solution";
    else fl << mn;
}