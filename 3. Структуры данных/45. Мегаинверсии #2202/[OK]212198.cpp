#include <iostream>
#include <vector>
#include <algorithm>
#include <fstream>

using namespace std;

class Segment_Tree {

    vector<long long> tree;
public:
    Segment_Tree (int n)  {
        tree.assign(4 * n, 0);
    }

    long long summ(int look_r, int node, int left, int right, int look_l) {
        if (look_l > look_r) {
            return 0;
        } else if (look_l == left && look_r == right)  {
            return tree[node];
        } else {
            int middle = (left + right) / 2;
            return summ(min(look_r, middle), node * 2, left, middle, look_l) +
                    summ(look_r, node * 2 + 1, middle + 1, right, max(look_l, middle + 1));
        }
    }

    void push(int node, int left, int right, int pos, int value) {
        if (left == right)
            tree[node] = value;
        else {
            int middle = (left + right) / 2;
            if (pos > middle) {
                push(node * 2 + 1, middle + 1, right, pos, value);
            } else {
                push(node * 2, left, middle, pos, value);
            }
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }
    }
};

int main() {
    ifstream in ("input.txt");
    ofstream out ("output.txt");
    int size;
    in >> size;
    Segment_Tree for_pairs = Segment_Tree(size);
    Segment_Tree for_triples = Segment_Tree(size);
    int value;
    long long result = 0;
    for (int i = 0; i < size; ++i) {
        in >> value;
        for_pairs.push(1, 0, size - 1, value - 1, 1);
        for_triples.push(1, 0, size - 1, value - 1, for_pairs.summ(size - 1, 1,0,size -1, 0) - for_pairs.summ(value - 1,1,0,size -1, 0));
        result += (for_triples.summ(size - 1, 1,0,size -1, 0) - for_triples.summ(value - 1, 1,0,size -1, 0));
    }
    out << result;

    return 0;
}