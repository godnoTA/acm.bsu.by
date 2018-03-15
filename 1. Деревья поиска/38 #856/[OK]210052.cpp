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


void left(vector <vertex>& tree, int tek, vector <int>& order, vector <bool>& use) {
    if(use[tek] == false) {
        use[tek] = true;
    }
    if(tree[tek].left != -1 && use[tree[tek].left] == false) {
        order.push_back(1);
        left(tree, tree[tek].left, order, use);
    }
    if(tree[tek].right != -1 && use[tree[tek].right] == false) {
        order.push_back(2);
        left(tree, tree[tek].right, order, use);
    }
    order.push_back(3);
}

int strToInt(string a){
    int s = 0;
    for(int i = 0; i < a.length(); ++i) {
        s*=10;
        s+=(a[i] - '0');
    }
    return s;
}

long long deleteAndCheck(vector <vertex>& tree, int ver, vector <int>& orderOfSecondTree, bool lOrR) {
    vector <int> order;
    vector <bool> use(tree.size(), false);
    if(tree[ver].left == -1 && tree[ver].right == -1) {
        if(tree[tree[ver].batya].left == ver) {
            tree[tree[ver].batya].left = -1;
            left(tree, 0, order, use);
            tree[tree[ver].batya].left = ver;
        } else {
            tree[tree[ver].batya].right = -1;
            left(tree, 0, order, use);
            tree[tree[ver].batya].right = ver;
        }
    } else if(lOrR == 0) {
        if(tree[ver].left == -1) {
            return -1E12;
        }
        if(ver == 0) {
            if(tree[ver].right == -1) {
                left(tree, tree[ver].left, order, use);
            } else {
                int tek = tree[ver].left;
                while(tree[tek].right != -1) {
                    tek = tree[tek].right;
                }
                if(tree[tek].batya != ver) {
                    tree[tree[tek].batya].right = tree[tek].left;
                    left(tree, 0, order, use);
                    tree[tree[tek].batya].right = tek;
                } else {
                    tree[tree[tek].batya].left = tree[tek].left;
                    left(tree, 0, order, use);
                    tree[tree[tek].batya].left = tek;
                }
            }
        } else {
            if(tree[ver].right == -1) {
                if(tree[tree[ver].batya].left == ver) {
                    tree[tree[ver].batya].left = tree[ver].left;
                    left(tree, 0, order, use);
                    tree[tree[ver].batya].left = ver;
                } else {
                    tree[tree[ver].batya].right = tree[ver].left;
                    left(tree, 0, order, use);
                    tree[tree[ver].batya].right = ver;
                }
            } else {
                int tek = tree[ver].left;
                while(tree[tek].right != -1) {
                    tek = tree[tek].right;
                }
                if(tree[tek].batya != ver) {
                    tree[tree[tek].batya].right = tree[tek].left;
                    left(tree, 0, order, use);
                    tree[tree[tek].batya].right = tek;
                } else {
                    tree[tree[tek].batya].left = tree[tek].left;
                    left(tree, 0, order, use);
                    tree[tree[tek].batya].left = tek;
                }
            }
        }
    } else {
        if (tree[ver].right == -1) {
            return -1E12;
        }
        if (ver == 0) {
            if (tree[ver].left == -1) {
                left(tree, tree[ver].right, order, use);
            } else {
                int tek = tree[ver].right;
                while (tree[tek].left != -1) {
                    tek = tree[tek].left;
                }
                if (tree[tek].batya != ver) {
                    tree[tree[tek].batya].left = tree[tek].right;
                    left(tree, 0, order, use);
                    tree[tree[tek].batya].left = tek;
                } else {
                    tree[tree[tek].batya].right = tree[tek].right;
                    left(tree, 0, order, use);
                    tree[tree[tek].batya].right = tek;
                }
            }
        } else {
            if (tree[ver].left == -1) {
                if (tree[tree[ver].batya].right == ver) {
                    tree[tree[ver].batya].right = tree[ver].right;
                    left(tree, 0, order, use);
                    tree[tree[ver].batya].right = ver;
                } else {
                    tree[tree[ver].batya].left = tree[ver].right;
                    left(tree, 0, order, use);
                    tree[tree[ver].batya].left = ver;
                }
            } else {
                int tek = tree[ver].right;
                while (tree[tek].left != -1) {
                    tek = tree[tek].left;
                }
                if (tree[tek].batya != ver) {
                    tree[tree[tek].batya].left = tree[tek].right;
                    left(tree, 0, order, use);
                    tree[tree[tek].batya].left = tek;
                } else {
                    tree[tree[tek].batya].right = tree[tek].right;
                    left(tree, 0, order, use);
                    tree[tree[tek].batya].right = tek;
                }
            }
        }
    }
    if(order == orderOfSecondTree) {
        return tree[ver].value;
    } else {
        return -1E12;
    }
}

struct num {
    int value, num;
};

bool compare(num a, num b){
    return a.value > b.value;
}


int main() {
    ifstream in("tst.in");
    vector <vertex> tree1;
    vector <vertex> tree2;
    vector <num> value1;
    vector <num> value2;
    map <int,int> n1;
    map <int,int> n2;
    string a; in >> a;
    num b;
    while (a != "*") {
        int k = strToInt(a);
        n1[k]++;
        if(n1[k] == 1) {
            b.value = k;
            value1.push_back(b);
            value1.back().num = value1.size() - 1;
        }
        in >> a;
    }
    while(in) {
        in >> a;
        int k = strToInt(a);
        n2[k]++;
        if(n2[k] == 1) {
            b.value = k;
            value2.push_back(b);
            value2.back().num = value2.size() - 1;
        }
    }
    ofstream out("tst.out");
    if(value1.size() - value2.size() != 1 && value1.size() - value2.size() != -1) {
        out << "NO";
        return 0;
    }
    if(value1.size() < value2.size()) {
        swap(value1, value2);
    }
    for(num item : value1) {
        add(tree1, item.value);
    }
    for(num item : value2) {
        add(tree2, item.value);
    }


    vector <int> orderOfSecondTree;
    vector <bool> use2(tree2.size(), false);
    left(tree2, 0, orderOfSecondTree, use2);

    sort(value1.begin(), value1.end(), compare);

    for(num i : value1) {
        long long s1 = deleteAndCheck(tree1, i.num, orderOfSecondTree, 0);
        if(s1 != -1E12) {
            out << "YES" << endl << s1;
            return 0;
        }
        long long s2 = deleteAndCheck(tree1, i.num, orderOfSecondTree, 1);
        if(s2 != -1E12) {
            out << "YES" << endl << s2;
            return 0;
        }
    }
    out << "NO";
    return 0;
}
