#include <fstream>
#include <stack>

struct node {
    node *leftSon = nullptr,
            *rightSon = nullptr;
    int value;
    bool isLeftVisited;
    bool isRightVisited;

    explicit node(int value): value(value){
        isLeftVisited = false;
        isRightVisited = false;
    }
};

node *root = nullptr;
node *current = nullptr;
node *maxNode = nullptr;


void cleverPush(int value, std::stack<node*> &nodesWithoutRight) {
    if (root == nullptr) {
        root = new node(value);
        current = root;
    } else {
        if (value < current->value) {
            current->leftSon = new node(value);
            nodesWithoutRight.push(current);
            current = current->leftSon;
        } else {
            while (!nodesWithoutRight.empty() && nodesWithoutRight.top()->value <= value) {
                current = nodesWithoutRight.top();
                nodesWithoutRight.pop();
            }
            current->rightSon = new node(value);
            current = current->rightSon;
        }
    }
}

void inOrder(std::ofstream& out) noexcept {
    std::stack<node*> stack;
    stack.push(root);
    while (!stack.empty()) {
        node* current = stack.top();
        if (current != nullptr) {
            if (current->isLeftVisited) {
                current->isLeftVisited = false;
                stack.push(current->leftSon);
            } else if (current->isRightVisited) {
                out << current->value;
                if (current != maxNode) {
                    out << " ";
                }
                current->isRightVisited = false;
                stack.push(current->rightSon);
            } else {
                stack.pop();
            }
        } else {
            stack.pop();
        }
    }
}

void postOrder(std::ofstream& out) noexcept {
    std::stack<node*> stack;
    stack.push(root);
    while (!stack.empty()) {
        node* currentNode = stack.top();
        if (currentNode != nullptr) {
            if (!currentNode->isLeftVisited) {
                currentNode->isLeftVisited = true;
                stack.push(currentNode->leftSon);
            } else if (!currentNode->isRightVisited) {
                currentNode->isRightVisited = true;
                stack.push(currentNode->rightSon);
            } else {
                out << currentNode->value;
                if (currentNode != root) {
                    out << " ";
                }
                stack.pop();
            }
        } else {
            stack.pop();
        }
    }
}

int main() {
    std::ifstream in("input.txt");
    std::ofstream out("output.txt");
    size_t size;
    in >> size;
    std::stack<node*> nodesWithoutRight;
    for (size_t i = 0; i < size; ++i) {
        int element;
        in >> element;
       cleverPush(element, nodesWithoutRight);
    }
    maxNode = root;
    while (maxNode->rightSon != nullptr) {
        maxNode = maxNode->rightSon;
    }
    postOrder(out);
    out << "\n";
    inOrder(out);
    in.close();
    out.close();
}