#include <iostream>
#include <memory>
#include <cstdio>

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

        Node() {};
        Node(T val) : data(val) {};
    };

    using node_ptr = std::unique_ptr<Node>;

    BST() {
        _root = make_unique<Node>(Node());
    }

    void insert(T val) {
        insert_(std::move(_root), val);
    }

    T get_sum() {
        return _sum;
    }

private:
    node_ptr _root;
    T _sum = T(0);

    void insert_(node_ptr&& curNode, T val) {
        if (curNode == nullptr) {
            curNode = make_unique<Node>(Node(val));
            _sum += val;
            return;
        }

        if (curNode.get()->data > val) {
            insert_(std::move(curNode.get()->left), val);
        } else if (curNode.get()->data < val) {
            insert_(std::move(curNode.get()->right), val);
        }
    }
};

int main()
{
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);

    BST<long long> bst;
    int x;

    while (!std::cin.eof()) {
        std::cin >> x;
        bst.insert(x);
    }
    std::cout << bst.get_sum();

    return 0;
}
