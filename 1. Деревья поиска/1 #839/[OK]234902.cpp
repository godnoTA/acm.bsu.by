#include <fstream>
#include <vector>

typedef int Val;
typedef int KeyM;


    template <class KeyM, class TValue>
    struct Node
{
        KeyM   key;
        TValue value;
        Node* left;
        Node* right;
    
        Node(KeyM   key, TValue value)
        :key(key), value(value)
    {
            left = 0;
            right = 0;
    }
};

class Tree
{
    private:
        Node<KeyM, Val>*& findNode(Node<KeyM, Val>*& node, const KeyM key);
        void deleteNode(Node< KeyM, Val>*& node);
        std::vector <int> v;
    public:
        Tree();
        bool Add(const KeyM key);
        bool deleteByKey(const KeyM key);
        ~Tree();
        bool getValue(const KeyM key, Val& value);
        void cleare();
        int  SetLabels(Node<KeyM, Val>*& n);
        void Round(Node<KeyM, Val>*& n);
        bool Solve();
        int ColV;
        Node<KeyM, Val> *Kor;
        void result(Node<KeyM, Val>*& n, std::ostream& out);
};


Tree::Tree()
{
    Kor = 0;
    ColV = 0;
}

Node<KeyM, Val>*& Tree::findNode(Node<KeyM, Val>*& node, const KeyM key)
{
    if (node == 0)
        return node;
    if (node->key == key)
        return node;
    if (key < node->key){
        return findNode(node->left, key);
    }
    else{
        return findNode(node->right, key);
    };
}
bool Tree::Add(const KeyM key)
{
    Node<KeyM, Val>*& tmp = findNode(Kor, key);
    if (tmp != 0)
    {
        return false;
    }
    else
    {
        tmp = new Node<KeyM, Val>(key, 0);
        return true;
    };
}

int Tree::SetLabels(Node<KeyM, Val>*& n)
{
    int left = 0;
    int right = 0;
    if (n == 0)
        return 0;
    else{
        left = SetLabels(n->left);
        right = SetLabels(n->right);
        if (left != right){
            ColV++;
            n->value = 1;
        }
        return left + right + 1;
    }
}


void Tree::deleteNode(Node< KeyM, Val>*& node)
{
    if (node == 0) return;
    if ((node->right == 0) || (node->left == 0)){
        Node<KeyM, Val>* t = node;
        node = (node->right != 0) ? node->right : node->left;
        delete t;
        return;
    };
    Node<KeyM, Val>** q = &(node->right);
    if ((*q)->left != 0){
        q = &((*q)->left);
    };
    node->key = (*q)->key;
    node->value = (*q)->value;
    deleteNode(*q);
    return;
}



bool Tree::deleteByKey(const KeyM key)
{
    Node<KeyM, Val>*& n = findNode(Kor, key);
    if (n == 0) return false;
    else
    {
        deleteNode(n);
        return true;
    };
}

void rec_del(Node<KeyM, Val>*& n)
{
    if (n != 0){
        rec_del(n->left);
        rec_del(n->right);
        delete n;
    };
}


Tree::~Tree()
{
    cleare();
}

bool Tree::getValue(const KeyM key, Val& value)
{
    Node<KeyM, Val>*& n = findNode(Kor, key);
    if (n == 0) return false;
    else{
        value = n->value;
        return true;
    };
}

void Tree::cleare()
{
    rec_del(Kor);
    Kor = 0;
}

bool Tree::Solve()
{
    SetLabels(Kor);
    if (ColV % 2 == 0)
        return false;
    Round(Kor);
    int k = v.size() / 2;
    deleteByKey(v[k]);
    return true;
}

void Tree::Round(Node<KeyM, Val>*& n)
{
    if (n != 0)
    {
        Round(n->left);
        if (n->value != 0)
            v.push_back(n->key);
        Round(n->right);
    }
}

void Tree::result(Node<KeyM, Val>*& n, std::ostream& out)
{
    if (n != 0)
    {
        out << n->key << std::endl;
        if (n->left != 0)
            result(n->left, out);
        if (n->right != 0)
            result(n->right, out);
    };
}


int main(int argc, char* argv[])
{
        std::ifstream f1("in.txt");
        std::ofstream f2("out.txt");
        Tree tree;
    do
    {
        int m;
        f1 >> m;
        if (!f1.fail())
            tree.Add(m);
    }
    
    while (!f1.fail());
    
        tree.Solve();
        tree.result(tree.Kor, f2);
        f1.close();
        f2.close();
    return 0;
}
