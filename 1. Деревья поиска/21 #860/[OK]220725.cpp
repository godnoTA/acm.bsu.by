#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
#include <set>

using namespace std;

//====================================================================================================================
class Tree {
public:

    class Node {
    public:
        int key;
        int height;
        int qSon;
        Node* left;
        Node* right;

        Node(){key = 0; left = nullptr; right = nullptr; qSon = 0; height = 0;}
        Node(int i) {key = i; left = nullptr; right = nullptr; qSon =0; height = 0;}

        bool operator < (const Node& r) const {
            return key < r.key;
        }
    };

    set<int> mas;

    Node* root;

    void insert(int x) {
        root = doInsert(root, x);
    }

    Node* doInsert(Node* node, int& x) {
        if (node == nullptr) {
            return new Node(x);
        }
        if (x < node->key) {
            node->left = doInsert(node->left, x);
        } else
            node->right = doInsert(node->right, x);
        return node;
    }
//====================================================================================================================

    void preOrder(Node* t, ofstream& fout)  {
        if (t != nullptr){
            fout << (to_string(t->key) + "\n");
            preOrder(t->left, fout);
            preOrder(t->right, fout);
        }
    }
//======================================================================================================================

    void heightSonFunc(Node* h) { //метки высоты и подсчёт потомков
        if (h->left != nullptr && h->right != nullptr) {
            heightSonFunc(h->left);
            heightSonFunc(h->right);
            h->height = max(h->left->height, h->right->height) + 1;
            h->qSon = h->left->qSon + h->right->qSon + 1;
        } else if (h->left == nullptr && h->right != nullptr) {
            heightSonFunc(h->right);
            h->height = h->right->height + 1;
            h->qSon = h->right->qSon + 1;
        } else if (h->left != nullptr && h->right == nullptr) {
            heightSonFunc(h->left);
            h->height = h->left->height + 1;
            h->qSon = h->left->qSon + 1;
        } else {
            h->height = 0;
            h->qSon = 1;
        }
    }
    //====================================================================================================================

    void lucky(Node* l) {
        if (l->left != nullptr && l->right != nullptr) {
            if (l->left->height == l->right->height && l->left->qSon != l->right->qSon) {
                mas.insert(l->key);
            }
            lucky(l->left);
            lucky(l->right);
        } else if (l->left != nullptr && l->right == nullptr) {
            lucky(l->left);
        } else if (l->left == nullptr && l->right != nullptr) {
            lucky(l->right);
        }
    }

    int centralNode() {
        if (mas.size() % 2 == 0) {
            return -1;
        }
        else {
            int i = mas.size()/2;
            for (auto iter : mas){
                if (i == 0) {
                    i = iter;
                    break;
                }
                --i;
            }
            return i;
        }
    }
//===================================================================================
//===================================================================================

    Node* findMin(Node* v){
        if (v->left != nullptr)
            return findMin(v->left);
        return v;
    }

    Node* deleteNode(Node* v, int x){
        if (v == nullptr)
            return nullptr;
        if (x < v->key) {
            v->left = deleteNode(v->left, x);
            return v;
        }
        if (x > v->key) {
            v->right = deleteNode(v->right, x);
            return v;
        }
        if (v->left == nullptr) {
            return v->right;
        }
        else if (v->right == nullptr) {
            return v->left;
        }
        else {
            v->key = findMin(v->right)->key;
            v->right = deleteNode(v->right, v->key);
            return v;
        }
    }

};
//===================================================================================
//===================================================================================




 int main() {
     Tree* tree = new Tree();
     ifstream fin("in.txt");
     ofstream fout("out.txt");
     int a;
     while (fin >> a) {
         tree->insert(a);
     }
     fin.close();
     tree->heightSonFunc(tree->root);
     tree->lucky(tree->root);
     int centr = tree->centralNode();
     if (centr != -1)
         tree->deleteNode(tree->root, centr);
     tree->preOrder(tree->root, fout);
     fout.close();
     delete tree;
 }