#include <iostream>
#include <memory>
#include <cstdio>
#include <vector>
#include <cmath>

template <typename T, typename... Args>
std::unique_ptr<T> make_unique(Args&&... args) {
    return std::unique_ptr<T>(new T(std::forward<Args>(args)...));
}

class BST {
public:
    struct Node {
        int data;
        long long height = 0;
        long long leaves_cnt = 1;
        long long a = 0;
        long long b = 0;
        std::unique_ptr<Node> left;
        std::unique_ptr<Node> right;
        Node *parent = nullptr;

        Node() {};
        Node(int val) : data(val) {};
        Node* l_get() {
            return (this->left).get();
        }
        Node* r_get() {
            return (this->right).get();
        }
    };

    BST() {}

    void insert(int val) {
        insert_(std::move(root_), val, nullptr);
    }

    long long get_MSL() {
        postOrder_travers(root_.get());
        cntNumOfDiffPath(root_.get());
        cntPathThroughParent(root_.get());
        removeMaxPath(root_.get());
        return MSL;
    }

    void traversal() {
        preOrder(root_.get());
    }

private:

    void preOrder(Node *curNode) {
        if (curNode == nullptr) {
            return;
        }
        std::cout << curNode->data << "\n";
        preOrder(curNode->l_get());
        preOrder(curNode->r_get());
    }

    void insert_(std::unique_ptr<Node>&& curNode, int val, Node* p) {
        if (curNode == nullptr) {
            curNode = make_unique<Node>(val);
            curNode->parent = p;
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

    void remove_(Node* curNode) {
        if (curNode->left != nullptr && curNode->right != nullptr) {
            Node* minNode = find_min(curNode->r_get());
            Node* min_parent = minNode->parent;
            curNode->data = minNode->data;
            if (minNode->r_get() != nullptr) {
                minNode->r_get()->parent = min_parent;
            }
            if (min_parent->l_get() == minNode) {
                min_parent->left = std::move(minNode->right);
            } else {
                min_parent->right = std::move(minNode->right);
            }
        } else {
            std::unique_ptr<Node> ptr = std::move((curNode->left != nullptr) ?
                                        curNode->left : curNode->right);
            if (curNode->parent == nullptr) {
                root_ = std::move(ptr);
            } else {
                if (ptr != nullptr) {
                    ptr.get()->parent = curNode->parent;
                }
                if ((curNode->parent->left).get() == curNode) {
                    curNode->parent->left = std::move(ptr);
                } else {
                    curNode->parent->right = std::move(ptr);
                }
            }
        }
    }

    void removeMaxPath(Node *curNode) {
        if (curNode == nullptr) {
            return;
        }
        removeMaxPath(curNode->l_get());
        removeMaxPath(curNode->r_get());
        if (curNode->a + curNode->b == maxNumOfPath) {
            remove_(curNode);
        }
    }

    long long cntChildUpPath(Node* node, long long num_of_up_path) {
        if (node == nullptr) {
            return -1;
        }
        node->a = num_of_up_path;
        return node->height;
    }

    void cntPathThroughParent(Node* curNode) {
        if (curNode == nullptr) {
            return;
        }

        long long left_height = cntChildUpPath(curNode->l_get(), curNode->b);
        long long right_height = cntChildUpPath(curNode->r_get(), curNode->b);
        Node* highest_child = (left_height > right_height) ?
                                         curNode->l_get() : curNode->r_get();
        if (highest_child != nullptr) {
            if (left_height == right_height) {
                curNode->l_get()->a += curNode->a *
                          curNode->l_get()->leaves_cnt / curNode->leaves_cnt;
                curNode->r_get()->a += curNode->a *
                          curNode->r_get()->leaves_cnt / curNode->leaves_cnt;
            } else {
                highest_child->a += curNode->a;
            }
        }

        maxNumOfPath = std::max(maxNumOfPath, curNode->a + curNode->b);

        cntPathThroughParent(curNode->l_get());
        cntPathThroughParent(curNode->r_get());
    }

    std::pair<long long, long long> getHeightAndLeaves(Node* node) {
        return (node == nullptr) ? std::make_pair(-1ll, -1ll) :
                              std::make_pair(node->height, node->leaves_cnt);
    }

    void cntNumOfDiffPath(Node* curNode) {
        if (curNode == nullptr) {
            return;
        }
        cntNumOfDiffPath(curNode->l_get());
        cntNumOfDiffPath(curNode->r_get());
        std::pair<long long, long long> left = getHeightAndLeaves(curNode->l_get());
        std::pair<long long, long long> right = getHeightAndLeaves(curNode->r_get());
        if (left.first + right.first + 2 == MSL) {
            curNode->b = std::max(left.second, 1ll) * std::max(1ll, right.second);
        }
    }

    std::pair<long long, long long> postOrder_travers(Node* curNode) {
        if (curNode == nullptr) {
            return {-1ll, 1ll};
        }
        std::pair<long long, long long> left = postOrder_travers(curNode->l_get());
        std::pair<long long, long long> right = postOrder_travers(curNode->r_get());

        if (left.first > right.first) {
            curNode->height = left.first + 1;
            curNode->leaves_cnt = left.second;
        } else {
            curNode->height = right.first + 1;
            curNode->leaves_cnt = right.second;
            if (left.first == right.first && left.first != -1) {
                curNode->leaves_cnt += left.second;
            }
        }
        MSL = std::max(MSL, left.first + right.first + 2ll);
        return {curNode->height, curNode->leaves_cnt};
    }

    long long MSL = 0;
    long long maxNumOfPath = 0;
    std::unique_ptr<Node> root_ = nullptr;
};

int main()
{
    freopen("in.txt", "r", stdin);
    freopen("out.txt", "w", stdout);

    BST bst;
    long long x;

    while (!std::cin.eof()) {
        std::cin >> x;
        bst.insert(x);
    }

    bst.get_MSL();
    bst.traversal();

    return 0;
}
