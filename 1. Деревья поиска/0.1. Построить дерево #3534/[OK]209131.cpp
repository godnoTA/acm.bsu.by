#include <algorithm>
#include <random>
#include <vector>
#include <iostream>
#include <fstream>
#include <set>
#include <map>

using namespace std;

struct vertex{
    int value;
    int left = -1, right = -1;
    int batya = -1;
};

void add(vector <vertex>& tree, int item) {
    if(tree.empty()) {
        vertex a;
        a.value = item;
        tree.push_back(a);
    } else {
        vertex a;
        a.value = item;
        tree.push_back(a);
        int tek = 0;
        while(true) {
            if(item > tree[tek].value) {
                if(tree[tek].right == -1) {
                    tree.back().batya = tek;
                    tree[tek].right = tree.size() - 1;
                    break;
                } else {
                    tek = tree[tek].right;
                }
            } else {
                if(tree[tek].left == -1) {
                    tree.back().batya = tek;
                    tree[tek].left = tree.size() - 1;
                    break;
                } else {
                    tek = tree[tek].left;
                }
            }
        }
    }
}

vector <bool> use;
vector <int> order;

void left(vector <vertex>& tree, int tek) {
    if(use[tek] == false) {
        order.push_back(tree[tek].value);
        use[tek] = true;
    }
    if(tree[tek].left != -1 && use[tree[tek].left] == false) {
        left(tree, tree[tek].left);
    } else if(tree[tek].right != -1 && use[tree[tek].right] == false) {
        left(tree, tree[tek].right);
    } else if(tree[tek].batya != -1){
        left(tree, tree[tek].batya);
    }
}

int main() {
    ifstream in("input.txt");
    vector <vertex> tree;
    vector <int> value;
    map <int,int> n;
    while(in) {
        int a; in >> a;
        n[a]++;
        if(n[a] == 1) {
            value.push_back(a);
        }
    }
    for(int item : value) {
        add(tree, item);
    }
    use.assign(tree.size(), false);
    left(tree, 0);
    ofstream out("output.txt");
    for(int item : order) {
        out << item << endl;
    }
    in.close();
    out.close();
    return 0;
}
