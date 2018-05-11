#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

struct Node
{
    long x;
    Node *l,*r;
};
Node *Tree=NULL;
vector <Node> vect1;
vector <long> vect2;

void show(Node *&Tree)
{
    if (Tree!=NULL)
    {
        printf("%d\n" ,Tree->x);
        show(Tree->l);
        show(Tree->r);
    }
}
void add_node(long x,Node *&MyTree)
{
    if (NULL==MyTree)
    {
        MyTree=new Node;
        MyTree->x=x;
        MyTree->l=MyTree->r=NULL;
    }

    if (x<MyTree->x)
    {
        if (MyTree->l!=NULL) add_node(x,MyTree->l);
        else
        {
            MyTree->l=new Node;
            MyTree->l->l=MyTree->l->r=NULL;
            MyTree->l->x=x;
        }
    }

    if (x>MyTree->x)
    {
        if (MyTree->r!=NULL)
            add_node(x,MyTree->r);
        else
        {
            MyTree->r=new Node;
            MyTree->r->l=MyTree->r->r=NULL;
            MyTree->r->x=x;
        }
    }
}
Node* minimum(Node *&x) {
    if (x->l == nullptr)
        return x;
    return minimum( x->l );
}
Node * delete1(Node *&node, long z) {
    if (node == nullptr)
        return node;
    if (z<node->x)
        node->l = delete1( node->l, z );

    else {
        if (z > node->x)
            node->r = delete1(node->r, z);
        else {
            if (node->l != nullptr && node->r != nullptr) {
                node->x = minimum(node->r)->x;
                node->r = delete1(node->r, node->x);
            } else {
                if (node->l != nullptr)
                    node = node->l;
                else
                    node = node->r;
            }
        }
    }
    return node;
}
int heightOfBinarySubtree(Node *&node) {
    if (node == NULL) {
        return 0;
    } else {
        return 1 + max( heightOfBinarySubtree( node->l ), heightOfBinarySubtree( node->r ) );
    }

}
 int heightOfBinaryTree(Node *&node) {
    if (node == NULL) {
        return 0;
    } else {

        if (node->l == NULL || node->r == NULL) {
            return -1;
        } else {
            if (heightOfBinarySubtree(node->l) == heightOfBinarySubtree(node->r))
                return 1;
            else
                return 2;
        }
    }
}
void equalHeight(Node *&localRoot) {    // запускается первой
    if (localRoot != NULL) {
        if (heightOfBinaryTree( localRoot ) == 1) {
            vect1.push_back(*localRoot);
        }
        equalHeight( localRoot->l );
        equalHeight( localRoot->r );
    }
}
int amount(Node *&node) {
    if (node == NULL)
        return 0;
    else {
        return 1 + amount( node->l ) + amount( node->r );
    }

}
void amount() {
    for (int i = 0; i < vect1.size(); i++) {
        if (amount(vect1[i].r) != amount(vect1[i].l)) {
            vect2.push_back(vect1[i].x);
        }
    }
    sort(vect2.begin(), vect2.end());
}

int main()
{
    freopen("in.txt", "r", stdin);
    freopen("out.txt", "w", stdout);
    long x;
    char s[32];
    while (fgets(s, sizeof(s), stdin) != 0 && *s != '\n') {
        x = atoi(s);
        add_node(x,Tree);
    }
 /*   while (cin >> x) {

    };*/
    equalHeight(Tree);
    amount();
    if ((vect2.size() - 1) % 2 == 0) {
        delete1(Tree, vect2[vect2.size() / 2]);
    }
    show(Tree);
}