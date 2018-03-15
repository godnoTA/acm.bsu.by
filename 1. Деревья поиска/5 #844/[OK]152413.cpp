#include <iostream>
#include <iomanip>
#include <fstream>
#include <algorithm>
#include <vector>

#pragma comment(linker, "/STACK:66777216")

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
    vector<int> vector;

    bool isLeaf() {
        return (left == nullptr && right == nullptr);
    }
};

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

void algorithm(Node *node, vector<Node> &vectorOfNodes, vector<int> &vectorOfLength) {
    if (!node)
        return;
    if (node->left)
        algorithm(node->left, vectorOfNodes, vectorOfLength);
    if (node->right)
        algorithm(node->right, vectorOfNodes, vectorOfLength);
    if (node->isLeaf()) {
        node->vector.push_back(1);
        return;
    }
    else {
        if (node->left) {
            int index = 0;
            for (int i = 0; i < node->left->vector.size(); i++) {
                if ((node->left)->vector[i] == 1) {
                    index = i + 1;
                    node->vector.resize(index + 1);
                    node->vector[index] = 1;
                }
            }
        }
        if (node->right) {
            int index = 0;
            for (int i = 0; i < node->right->vector.size(); i++) {
                if ((node->right)->vector[i] == 1) {
                    index = i + 1;
                    node->vector.resize(index + 1);
                    node->vector[index] = 1;
                }
            }
        }
        if (node->left && node->right) {
            int count = 0;
            for (int i = 0; i < node->left->vector.size(); i++) {
                if (node->left->vector[i] == 1) {
                    count += i;
                    break;
                }
            }
            for (int i = 0; i < node->right->vector.size(); i++) {
                if (node->right->vector[i] == 1) {
                    count += i;
                    break;
                }
            }
            count += 2;
            vectorOfLength.push_back(count);
            vectorOfNodes.push_back(*node);
        }

    }
    return;
}

void sortOfLength(vector<Node> &vectorOfNodes, vector<int> &vectorOfLength, vector<Node> &vectOfNodes,
                  vector<int> &vectOfLength) {

    int min = vectorOfLength.at(0);
    for (int i = 1; i < vectorOfLength.size(); i++) {
        if (vectorOfLength.at(i) <= min) {
            min = vectorOfLength.at(i);
        }
    }
    for (int i = 0; i < vectorOfLength.size(); i++) {
        if (vectorOfLength.at(i) == min) {
            vectOfLength.push_back(vectorOfLength.at(i));
            vectOfNodes.push_back(vectorOfNodes.at(i));
        }
    }
}


Node *nodyara = NULL;

void sumOfvalues(vector<Node> &vectOfNodes, vector<int> &vectorOfSum) {
    for (int i = 0; i < vectOfNodes.size(); i++) {
        int count = 0;
        count += vectOfNodes.at(i).value;
        nodyara = &vectOfNodes.at(i);
        while (nodyara->left || nodyara->right) {
            if (nodyara->left) {
                nodyara = nodyara->left;
                count += nodyara->value;
            } else {
                nodyara = nodyara->right;
                count += nodyara->value;
            }
        }
        nodyara = &vectOfNodes.at(i);


        while (nodyara->left || nodyara->right) {
            if (nodyara->right) {
                nodyara = nodyara->right;
                count += nodyara->value;
            } else {
                nodyara = nodyara->left;
                count += nodyara->value;
            }

        }
        vectorOfSum.push_back(count);
    }
}


/*int sortOfSum(vector<Node> &vectOfNodes, vector<int> &vectorOfSum) {
    sumOfvalues(vectOfNodes, vectorOfSum);
    int min_value_of_sum = vectorOfSum.at(0);
    int min_index = 0;
    for (int i = 1; i < vectorOfSum.size(); i++) {
        if (vectorOfSum.at(i) < min_value_of_sum) {
            min_index = i;
        }
    }

    return min_index;
}*/

void sortOfSum(vector<Node> &vectOfNodes, vector<Node> &veOfNodes, vector<int> &vectorOfSum, vector<int> &vectOfSum,
               vector<int> &vectOfLength, vector<int> &veOfLength) {
    int min = vectorOfSum.at(0);
    for (int i = 1; i < vectorOfSum.size(); i++) {
        if (vectorOfSum.at(i) <= min) {
            min = vectorOfSum.at(i);
        }
    }
    for (int i = 0; i < vectorOfSum.size(); i++) {
        if (vectorOfSum.at(i) == min) {
            vectOfSum.push_back(vectorOfSum.at(i));
            veOfNodes.push_back(vectOfNodes.at(i));
            veOfLength.push_back(vectOfLength.at(i));
        }
    }

}

int sortOfKey(vector<Node> &vecOfNodes) {
    int min_value_of_key = vecOfNodes.at(0).value;
    int min_index = 0;
    for (int i = 1; i < vecOfNodes.size(); i++) {
        if (vecOfNodes.at(i).value < min_value_of_key) {
            min_index = i;
        }
    }
    return min_index;
}

int edinichka(Node *node) {
    int index = 0;
    for (int i = 0; i < node->vector.size(); i++) {
        if (node->vector.at(i) == 1) {
            index = i;
            //cout<<index<<endl;
            return index;
        }
    }
}

Node *kek = NULL;

void center(Node *node) {

    int node_left;
    int node_right;

    if (node->left) {
        node_left = edinichka(node->left);
    }

    if (node->right) {
        node_right = edinichka(node->right);
    }

    if (node_left == node_right) {
        kek = node;
        return;
    }
    if (node_left < node_right) {
        int sum = (node_left + node_right) / 2;

        kek = node->right;

        for (int i = 0; i < sum - 1; i++) {
            if (kek->right) {
                kek = kek->right;
            }
            if (kek->left) {
                kek = kek->left;
            }
        }
        return;

    }
    if (node_left > node_right) {
        int sum = (node_left + node_right) / 2;

        kek = node->left;

        for (int i = 0; i < sum - 1; i++) {
            if (kek->left) {
                kek = kek->left;
            }
            if (kek->right) {
                kek = kek->right;
            }
        }
        return;

    }
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

void show(Node *root, ofstream &fout) {
    if (!root) return;
    fout << root->value << endl;
    show(root->left, fout);
    show(root->right, fout);
}

int main() {
    ifstream fin("in.txt");
    ofstream fout("out.txt");

    Node *root = NULL;
    long long a;
    int countik = 0;

    while (fin >> a) {
        paste(root, a);
        countik++;
    }
    if (countik == 1) {
        show(root, fout);
        return 0;
    } else {
        vector<Node> vectorOfNodes(0);
        vector<Node> vectOfNodes(0);
        vector<Node> vecOfNodes(0);
        vector<int> vectorOfLength(0);
        vector<int> vectOfLength(0);
        vector<int> vecOfLength(0);
        vector<int> vectorOfSum(0);
        vector<int> vectOfSum(0);


        algorithm(root, vectorOfNodes, vectorOfLength);
        sortOfLength(vectorOfNodes, vectorOfLength, vectOfNodes, vectOfLength);

        //int minimum = sortOfSum(vectOfNodes, vectOfSum);
        // int key = sortOfKey(vectOfNodes);
        //cout<<key<<endl;

        /*for (int i = 0; i < vectOfLength.size(); i++) {
            cout << vectOfLength.at(i) << endl;
        }*/
        /*for (int i = 0; i < vectorOfLength.size(); i++) {
            cout << vectorOfLength.at(i) << endl;
        }
        cout<<endl;
        for (int i = 0; i < vectorOfNodes.size(); i++) {
            cout << vectorOfNodes.at(i).value << " ";
        }*/
        /*for (int i = 0; i < vectOfNodes.size(); i++) {
            cout << vectOfNodes.at(i).value << endl;
        }*/
        /*for (int i = 0; i < vectOfSum.size(); i++) {
            cout << vectOfSum.at(i) << endl;
        }*/

        /*for(int i = 1; i < vectOfSum.size(); i++){
            flag = false;
            if(vectOfSum.at(0) ==  vectOfSum.at(i)) {
                flag = true;
            }
        }*/

        //cout<<flag<<endl;

        if (vectOfLength.size() == 1) {
            if (vectOfLength.at(0) % 2 == 0) {
                center(&vectOfNodes.at(0));
                root = deleteNode(root, kek->value);
                show(root, fout);
            } else {
                show(root, fout);
            }
        } else {
            sumOfvalues(vectOfNodes, vectorOfSum);
            sortOfSum(vectOfNodes, vecOfNodes, vectorOfSum, vectOfSum, vectOfLength, vecOfLength);
            if (vectOfSum.size() == 1) {
                if (vecOfLength.at(0) % 2 == 0) {
                    center(&vecOfNodes.at(0));
                    root = deleteNode(root, kek->value);
                    show(root, fout);
                } else {
                    show(root, fout);
                }
            } else {
                int minKey = sortOfKey(vecOfNodes);
                if (vecOfLength.at(minKey) % 2 == 0) {
                    center(&vecOfNodes.at(minKey));
                    root = deleteNode(root, kek->value);
                    show(root, fout);
                } else {
                    show(root, fout);
                }
            }
        }
        return 0;
    }
}