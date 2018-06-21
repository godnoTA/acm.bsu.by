#include <iostream>
#include <fstream>

using namespace std;

ifstream in("input.txt");
ofstream out("output.txt");

struct trnode{
    int key;
    trnode *left;
    trnode *right;
};

trnode * tree=NULL;

void push(int a,trnode *&Tree)
{
    
    if (Tree == NULL)
    {
        Tree = new trnode;
        Tree -> key = a;
        Tree -> left = Tree -> right = NULL;
        return;
    }
    if ((a > Tree->key)&&(Tree->right == NULL))
    {
        Tree->right = new trnode();
        Tree->right->key = a;
        Tree->right->left = NULL;
        Tree->right->right = NULL;
    }
    else if ((a > Tree->key)&&(Tree->right != NULL))
    {
        push(a, Tree->right);
    }
    if ((a < Tree->key)&&(Tree->left == NULL))
    {
        Tree->left = new trnode();
        Tree->left->key = a;
        Tree->left->left = NULL;
        Tree->left->right = NULL;
    }
    else if ((a < Tree->key)&&(Tree->left != NULL))
    {
        push(a, Tree->left);
    }
}

void printTree(trnode *&Tree){
    if (Tree != NULL){
        out << Tree->key << endl;
        printTree(Tree->left);
        printTree(Tree->right);
    }
    else{
        return;
    }
}

int main(){
    
    trnode* Tree = NULL;
    int a = 0;
    while ( !in.eof() ){
        in >> a;
        push(a, Tree);
    }
    
    printTree(Tree);
    
    return 0;
}
