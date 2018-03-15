#include <iostream>
#include <memory>
#include <cstdio>
#include <vector>

using ll = long long;

template <typename T, typename... Args>
std::unique_ptr<T> make_unique(Args&&... args) {
    return std::unique_ptr<T>(new T(std::forward<Args>(args)...));
}

template <class T>
class BST {
public:
    struct Node {
        T data;
        std::unique_ptr<Node> left;
        std::unique_ptr<Node> right;
        Node *parent = nullptr;

        Node() {};
        Node(T val) : data(val) {};

        Node* l_get() {
            return (this->left).get();
        }
        Node* r_get() {
            return (this->right).get();
        }
    };

    BST(ll k) {
        _key = k;
    }

    void insert(T val) {
        insert_(std::move(_root), val, nullptr);
    }

    void remove_node() {
       // find_nodes(_root.get());
        if (remNode != nullptr) {
            _remove(remNode, remNode);
        }
    }

    void traversal() {
        preOrder_travers(_root.get());
    }

private:
    std::unique_ptr<Node> _root = nullptr;
    Node* remNode = nullptr;
    ll _key;

    void insert_(std::unique_ptr<Node>&& curNode, T val, Node* p) {
        if (curNode == nullptr) {
            curNode = make_unique<Node>(val);
            curNode->parent = p;
            if (val == _key) {
                remNode = curNode.get();
            }
            return;
        }

        if (curNode.get()->data > val) {
            insert_(std::move(curNode.get()->left), val, curNode.get());
        } else if (curNode.get()->data < val) {
            insert_(std::move(curNode.get()->right), val, curNode.get());
        }
    }

    Node* find_min(Node* curNode) {
        if (curNode->left == nullptr) {
            return curNode;
        }
        return find_min(curNode->l_get());
    }

    void _remove(Node* curNode, Node* node) {
        if (curNode == node) {
            if (curNode->left != nullptr && curNode->right != nullptr) {
                Node* minNode = find_min(node->r_get());
                Node* pMin = minNode->parent;
                curNode->data = minNode->data;
                if (pMin->l_get() == minNode) {
                    pMin->left = std::move(minNode->right);
                } else {
                    pMin->right = std::move(minNode->right);
                }
            } else {
                auto ptr = std::move((curNode->left != nullptr) ?
                                      curNode->left : curNode->right);
                if (curNode->parent == nullptr) {
                    _root = std::move(ptr);
                } else {
                    if ((curNode->parent->left).get() == curNode) {
                        curNode->parent->left = std::move(ptr);
                    } else {
                        curNode->parent->right = std::move(ptr);
                    }
                }
            }
        }
    }

    void preOrder_travers(Node* curNode) {
        if (curNode == nullptr) {
            return;
        }
        std::cout << curNode->data << std::endl;
        preOrder_travers(curNode->l_get());
        preOrder_travers(curNode->r_get());
    }
};

int main()
{
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);

    long long x, key;
    std::cin >> key;
    BST<long long> bst(key);

    while (!std::cin.eof()) {
        std::cin >> x;
        bst.insert(x);
    }

    bst.remove_node();
    bst.traversal();

    return 0;
}
