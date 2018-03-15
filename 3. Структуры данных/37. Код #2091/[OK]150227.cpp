#include <iostream>
#include <fstream>

using namespace std;

int search(int a, int *root_mas) {
    int index = a;

    while (root_mas[a] > 0) {
        index = root_mas[a];
        a = root_mas[a];
    }

    return index;
}

int main() {

    ifstream fin("input.txt");
    ofstream fout("output.txt");

    int N;
    int R;
    int a, b;
    int count = 0;
    fin >> N >> R;

    int *root_mas = new int[N + 1];
    for (int i = 0; i < N + 1; i++) {
        root_mas[i] = -1;
    }

    for (int i = 0; i < R; i++) {
        fin >> a >> b;

        int first = search(a, root_mas);
        int second = search(b, root_mas);

        if (first == second) {
            count++;
        }
        else {
            if (root_mas[first] <= root_mas[second]) {
                root_mas[first] += -1;
                root_mas[second] = first;
            }
            else {
                root_mas[second] += -1;
                root_mas[first] = second;
            }
        }
    }

    fout << count;

    fin.close();
    fout.close();
}