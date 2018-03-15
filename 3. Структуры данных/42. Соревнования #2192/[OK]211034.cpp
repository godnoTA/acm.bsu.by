#include <iostream>
#include <algorithm>
#include <fstream>
#include <vector>
#include <cassert>

template<class First, class Second>
class SplayMinTree {

    using T = std::pair<First, Second>;

    struct Node {
        T key;
        Second min;
        Node *left, *right, *father;

        explicit Node(T key, Node* father = nullptr,
                      Node* left = nullptr, Node* right = nullptr)noexcept:
                key(key),left(left),right(right),father(father){
            min = key.second;
        }

        Node(const Node& arg) noexcept : Node(arg.key){
            if (arg.left != nullptr) {
                left = new Node(*(arg.left));
            }
            if (arg.right != nullptr) {
                right = new Node(*(arg.right));
            }
            if (arg.father != nullptr) {
                father = new Node(*(arg.father));
            }
        }

        void swap(Node& arg)noexcept {
            std::swap(key, arg.key);
            std::swap(father, arg.father);
            std::swap(left, arg.left);
            std::swap(right, arg.right);
            std::swap(min, arg.min);
        }

        Node& operator=(const Node& other)noexcept {
            if (this != &other) {
                Node copy(other);
                this->swap(copy);
            }
            return *this;
        }

        void setMin() {
            min = key.second;
            if (left != nullptr) {
                min = std::min(min, left->min);
            }
            if (right != nullptr) {
                min = std::min(min, right->min);
            }
        }

        virtual ~Node()noexcept
        {
            if (left != nullptr) {
                delete left;
            }
            if (right != nullptr) {
                delete right;
            }
        }
    };

    Node* top;

    void appropriateRotation(Node *n)noexcept {
        if (n == n->father->left) {
            zig(n);
        } else {
            zag(n);
        }
    }

    //when v is left son
    void zig(Node* v)noexcept {
        Node* previousRightSon = v->right;
        v->right = v->father;
        v->father = v->right->father;
        if (v->father != nullptr) {
            if (v->father->left == v->right) {
                v->father->left = v;
            } else {
                v->father->right = v;
            }
        }
        v->right->father = v;
        v->right->left = previousRightSon;
        if (previousRightSon != nullptr) {
            previousRightSon->father = v->right;
        }
        v->right->setMin();
        v->setMin();
    }

    // when v is right son
    void zag(Node* v)noexcept {
        Node* previousLeftSon = v->left;
        v->left = v->father;
        v->father = v->left->father;
        if (v->father != nullptr) {
            if (v->father->left == v->left) {
                v->father->left = v;
            } else {
                v->father->right = v;
            }
        }
        v->left->father = v;
        v->left->right = previousLeftSon;
        if (previousLeftSon != nullptr) {
            previousLeftSon->father = v->left;
        }
        v->left->setMin();
        v->setMin();
    }

    //lift Node v to the top of the tree
    void splay(Node* v)noexcept {
        while (v->father != nullptr) {
            if (v->father->father == nullptr) {
                appropriateRotation(v);
            } else {
                if ((v == v->father->left && v->father == v->father->father->left) ||
                    (v == v->father->right && v->father == v->father->father->right)) {
                    appropriateRotation(v->father);
                    appropriateRotation(v);
                }
                else {
                    appropriateRotation(v);
                    appropriateRotation(v);
                }
            }
        }
    }

public:
    explicit SplayMinTree(Node* top = nullptr)noexcept:top(top){}

    SplayMinTree(const SplayMinTree& st)noexcept = default;

    void swap(SplayMinTree& st)noexcept {
        std::swap(top, st.top);
    }

    SplayMinTree& operator=(const SplayMinTree& st)noexcept {
        if (this != &st) {
            SplayMinTree copy(st);
            this->swap(copy);
        }
        return *this;
    }

    void insert(const T& key)noexcept {
        if (top == nullptr) {
            top = new Node(key);
        } else {
            Node* value = top;
            while (true) {
                if (value->key == key) {
                    break;
                } else if (value->key > key) {
                    if (value->left == nullptr) {
                        value->left = new Node(key, value);
                        value = value->left;
                    } else {
                        value = value->left;
                    }
                } else {
                    if (value->right == nullptr) {
                        value->right = new Node(key, value);
                        value = value->right;
                    } else {
                        value = value->right;
                    }
                }
            }
            splay(value);
            top = value;
        }
    }

    Node* find(const T& key) noexcept {
        if (top == nullptr) {
            return nullptr;
        }
        Node* beforeKey = nullptr;
        Node* keyFinder = top;
        while (keyFinder != nullptr && !(keyFinder->key == key)) {
            beforeKey = keyFinder;
            if (keyFinder->key < key) {
                keyFinder = keyFinder->right;
            }
            else keyFinder = keyFinder->left;
        }

        if (keyFinder == nullptr) {
            splay(beforeKey);
            top = beforeKey;
        }
        else {
            splay(keyFinder);
            top = keyFinder;
        }
        return keyFinder;
    }

    void remove(const T& key) {
        Node* nodeOfKey = find(key);
        if (nodeOfKey != nullptr && key == nodeOfKey->key) {
            Node* left = nodeOfKey->left;
            if (left == nullptr) {
                top = nodeOfKey->right;
                if (top != nullptr) {
                    top->father = nullptr;
                }
                nodeOfKey->right = nullptr;
                delete nodeOfKey;
            } else {
                if (left->right == nullptr) {
                    left->right = top->right;
                    if (left->right != nullptr) {
                        left->right->father = left;
                    }
                    left->father = nullptr;
                    top->left = nullptr;
                    top->right = nullptr;
                    delete top;
                    top = left;
                    top->setMin();
                } else {
                    while (left->right != nullptr) {
                        left = left->right;
                    }
                    left->father->right = left->left;
                    if (left->left != nullptr) {
                        left->left->father = left->father;
                    }
                    Node* father = left->father;
                    left->left = top->left;
                    left->left->father = left;
                    left->right = top->right;
                    if (top->right != nullptr) {
                        top->right->father = left;
                    }
                    left->father = nullptr;
                    top->left = nullptr;
                    top->right = nullptr;
                    delete top;
                    top = left;
                    while (father->father != nullptr) {
                        father->setMin();
                        father = father->father;
                    }
                }
            }
        }
    }

    bool cleverInsert(const T& key) {
        insert(key);
        if (top->left != nullptr && top->left->min < key.second) {
            remove(key);
            return false;
        }
        return true;
    }

    virtual ~SplayMinTree() noexcept {
        if (top != nullptr) {
            delete top;
        }
    }
};

int main() {
    std::ifstream in("input.txt");
    std::size_t size;
    in >> size;
    std::vector<std::vector<std::size_t>> results;
    for (std::size_t i = 0; i < size; ++i) {
        std::size_t result;
        std::vector<std::size_t> programmerResults;
        in >> result;
        programmerResults.push_back(result);
        in >> result;
        programmerResults.push_back(result);
        in >> result;
        programmerResults.push_back(result);
        results.emplace_back(std::move(programmerResults));
    }
    std::sort(results.begin(), results.end());
    SplayMinTree<std::size_t, std::size_t> tree;
    size_t counter = 0;
    for (auto & result : results) {
        if (tree.cleverInsert(std::pair<size_t, size_t>(result[1], result[2]))){
            ++counter;
        }
    }
    std::ofstream out("output.txt");
    out << counter;
    return 0;
}