#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <limits>

using namespace std;

vector<int> readDimensions(const string & filename);
int solve(const vector<int> &dimensions);

int main() {

    vector<int> dimensions = readDimensions("input.txt"); 
    ofstream out("output.txt");
    out << solve(dimensions);
    out.close();
    return 0;
}

vector<int> readDimensions(const string & filename) {
    ifstream in(filename);
    int number;
    in >> number;
    vector<int> dimensions(number + 1);

    for (int i = 0; i < number; i++) {
        in >> dimensions[i] >> dimensions[i + 1];
    }
    return dimensions;
}

int solve(const vector<int> &dimensions) {
    int size = dimensions.size() - 1;
    vector<vector<int>> matrix(size, vector<int>(size));
    for (int i = 0; i < size - 1; i++) {
        matrix[i][i + 1] = dimensions[i] * dimensions[i + 1] * dimensions[i + 2];
    }
    for (int i = size - 3; i >= 0; i--) {
        for (int j = i + 2; j < size; j++) {
            int min = numeric_limits<int>::max();
            for (int k = i; k < j; k++) {
                int cost = matrix[i][k] + matrix[k + 1][j] + dimensions[i] * dimensions[k + 1] * dimensions[j + 1];
                if (cost < min) {
                    min = cost;
                }
            }
            matrix[i][j] = min;
        }
    }
    return matrix[0][size - 1];
}