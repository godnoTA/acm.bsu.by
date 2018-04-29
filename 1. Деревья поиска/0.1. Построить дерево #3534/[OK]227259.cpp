#include <fstream>
#include <cstdio>
#include <memory>

std::ifstream fin("input.txt");
std::ofstream fout("output.txt");

class Tree {
private:
  class Node {
  public:
    Node(int key, Node* parent) : key_(key), parent_(parent) {}

    void setKey(int key);
    void setLeftSon(Node* son);
    void setRightSon(Node* son);
    void setParent(Node* parent);

    int getKey();
    Node* getLeftSon();
    Node* getRightSon();
    Node* getParent();

    bool hasLeftSon();
    bool hasRightSon();
    bool hasParent();

    ~Node() {}

  private:
    int key_;
    Node* parent_ = nullptr;
    Node* left_son_ = nullptr;
    Node* right_son_ = nullptr;
  };

public:
  Tree() {}

  void insert(int key);
  void erase(Node* node);
  void rootLeftRight(Node* node);
  void rootLeftRight();

  ~Tree();

private:
  Node* root = nullptr;

  void setSon(Node* parent, Node* old_son,
              Node* new_son);
  void setLeftSon(Node* parent, Node* son);
  void setRightSon(Node* parent, Node* son);
};

void Tree::insert(int key) {
  if (root == nullptr) {
    root = new Node(key, nullptr);
  } else {
    Node* node = root;
    while (true) {
      if (node->getKey() > key) {
        if (node->getLeftSon() == nullptr) {
          node->setLeftSon(new Node(key, node));
          node->getLeftSon()->setParent(node);
        } else {
          node = node->getLeftSon();
        }
      } else if (node->getKey() < key){
        if (node->getRightSon() == nullptr) {
          node->setRightSon(new Node(key, node));
          node->getRightSon()->setParent(node);
        } else {
          node = node->getRightSon();
        }
      } else {
        return;
      }
    }
  }
}

void Tree::erase(Node* node) {
  if (node->hasLeftSon() && node->hasRightSon()) {
    Node* to_swap = node->getRightSon();
    while (to_swap->hasLeftSon()) {
      to_swap = to_swap->getLeftSon();
    }
    setSon(to_swap->getParent(), to_swap, to_swap->getRightSon());
    setSon(node->getParent(), node, to_swap);
    setLeftSon(to_swap, node->getLeftSon());
    setRightSon(to_swap, node->getRightSon());
    if (root == node) {
      root = to_swap;
    }
  } else {
    Node* son = node->hasLeftSon() ? node->getLeftSon()
                                                   : node->getRightSon();
    setSon(node->getParent(), node, son);
    if (node == root) {
      root = son;
    }
  }
}

void Tree::rootLeftRight(Node* node) {
  while (true) {
    if (node != nullptr) {
      fout << node->getKey() << std::endl;
      rootLeftRight(node->getLeftSon());
      node = node->getRightSon();
    } else {
      break;
    }
  }
}

void Tree::rootLeftRight() {
  rootLeftRight(root);
}

void Tree::setSon(Node* parent,
                  Node* old_son,
                  Node* new_son) {
  if (parent != nullptr) {
    if (parent->getLeftSon() == old_son) {
      parent->setLeftSon(new_son);
    } else {
      parent->setRightSon(new_son);
    }
  }
  if (new_son != nullptr) {
    new_son->setParent(parent);
  }
}

void Tree::setLeftSon(Node* parent,
                      Node* son) {
  if (parent != nullptr) {
    parent->setLeftSon(son);
  }
  if (son != nullptr) {
    son->setParent(parent);
  }
}

void Tree::setRightSon(Node* parent,
                       Node* son) {
  if (parent != nullptr) {
    parent->setRightSon(son);
  }
  if (son != nullptr) {
    son->setParent(parent);
  }
}

Tree::~Tree() {
  Node* node = root;
  while (root != nullptr) {
    while (node->hasLeftSon()) {
      node = node->getLeftSon();
    }
    erase(node);
    if (node->hasParent()) {
      node = node->getParent();
    } else {
      node = root;
    }
  }
}

void Tree::Node::setKey(int key) {
  key_ = key;
}

void Tree::Node::setLeftSon(Node* son) {
  left_son_ = son;
}

void Tree::Node::setRightSon(Node* son) {
  right_son_ = son;
}

void Tree::Node::setParent(Node* parent) {
  parent_ = parent;
}

int Tree::Node::getKey() {
  return key_;
}

Tree::Node* Tree::Node::getLeftSon() {
  return left_son_;
}

Tree::Node* Tree::Node::getRightSon() {
  return right_son_;
}

Tree::Node* Tree::Node::getParent() {
  return parent_;
}

bool Tree::Node::hasLeftSon() {
  return left_son_ != nullptr;
}

bool Tree::Node::hasRightSon() {
  return right_son_ != nullptr;
}

bool Tree::Node::hasParent() {
  return parent_ != nullptr;
}

int main() {
  Tree tree;
  while (!fin.eof()) {
    int a;
    fin >> a;
    tree.insert(a);
  }
  tree.rootLeftRight();
  return 0;
}