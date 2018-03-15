#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>

using namespace std;

struct Node {
    Node(long long newValue = 0) {
        left = nullptr;
        right = nullptr;
        value = newValue;
    };
    Node *left;
    Node *right;
    long long value;

    bool isLeaf() {
        return (left == nullptr && right == nullptr);
    }
};

Node *del = NULL;

void paste(Node *&root, long long newValue) {
    if (!root)
        root = new Node(newValue);
    else if (newValue > root->value) if (!root->right)
        root->right = new Node(newValue);
    else
        paste(root->right, newValue);
    else if (newValue < root->value) if (!root->left)
        root->left = new Node(newValue);
    else
        paste(root->left, newValue);
}

void show(Node *root, ofstream &fout) {
    if (!root)
        return;
    fout << root->value << endl;
    show(root->left, fout);
    show(root->right, fout);
}

Node *min(Node *root) {
    while (root->left) {
        root = root->left;
    }
    return root;
}

Node *deleteNode(Node *root, int value) {
    if (!root)
        return NULL;
    if (value < root->value) {
        root->left = deleteNode(root->left, value);
    }
    else if (value > root->value) {
        root->right = deleteNode(root->right, value);
    }
    else if (root->left && root->right) {
        root->value = min(root->right)->value;
        root->right = deleteNode(root->right, min(root->right)->value);
    }
    else if (root->left)
        root = root->left;
    else if (root->right)
        root = root->right;
    else if (!root->left && !root->right)
        root = NULL;
    return root;
}

void algorithm(Node *node, vector<Node> &vectorOfLists, vector<Node> &vectorOfParents) {
    if (!node) {
        return;
    }

    if (!node->left && !node->right) {
        del = node;
        return;
    }

    algorithm(node->left, vectorOfLists, vectorOfParents);
    algorithm(node->right, vectorOfLists, vectorOfParents);

    if (node->left && node->right) {
        if (node->left->isLeaf() && node->right->isLeaf()) {
            vectorOfParents.push_back(*node);
            vectorOfParents.push_back(*node);
            vectorOfLists.push_back(*node->left);
            vectorOfLists.push_back(*node->right);
            return;
        }
    }
    if (node->right) {
        if (node->right->isLeaf()) {
            vectorOfParents.push_back(*node);
            vectorOfLists.push_back(*node->right);
            return;
        }
    }
    if (node->left) {
        if (node->left->isLeaf()) {
            vectorOfParents.push_back(*node);
            vectorOfLists.push_back(*node->left);
            return;
        }
    }
}

bool worseThan(const Node &lhs, const Node &rhs) {
    if (lhs.value < rhs.value) {
        return true;
    } else {
        return false;
    }
}

int main() {
    ifstream fin("tst.in");
    ofstream fout("tst.out");

    Node *root = NULL;
    long long a;

    while (!fin.eof()) {
        fin >> a;
        paste(root, a);
    }

    vector<Node> vectorOfLists(0);
    vector<Node> vectorOfSortLists(0);
    vector<Node> vectorOfParents(0);

    algorithm(root, vectorOfLists, vectorOfParents);

    for (int i = 0; i < vectorOfLists.size(); i++) {
        vectorOfSortLists.push_back(vectorOfLists.at(i));
    }

    sort(vectorOfSortLists.begin(), vectorOfSortLists.end(), worseThan);

    if (vectorOfLists.size() == 0 && vectorOfParents.size() == 0) {
        root = deleteNode(root, del->value);
        show(root, fout);
    }

    if (vectorOfSortLists.size() % 2 != 0) {
        int index = (vectorOfSortLists.size() - 1) / 2;
        int indexParents = 0;
        for (int i = 0; i < vectorOfLists.size(); i++) {
            if (vectorOfSortLists.at(index).value == vectorOfLists.at(i).value) {
                indexParents = i;
            }
        }

        del = &vectorOfParents.at(indexParents);
        root = deleteNode(root, del->value);
        show(root, fout);

    } else {
        show(root, fout);
    }

    return 0;
}
