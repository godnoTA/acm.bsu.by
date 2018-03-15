#include <iostream>
#include <fstream>

using namespace std;

ifstream in("input.txt");
ofstream out("output.txt");

struct treeNode{
	int key;
	treeNode* left;
	treeNode* right;
};

void push (int newKey, treeNode*& Tree){
	// Если дерево NULL, то создаем дерево
	if( Tree == NULL ){
		Tree = new treeNode;
		Tree->key = newKey;
		Tree->right = Tree->left = NULL;
	}
	// Если ключ меньше, то добавляем рекурсивно влево
	if( newKey < Tree->key ){
		if( Tree->key != NULL ){
			push( newKey, Tree->left);
		}
		else{
			Tree->left = new treeNode;
			Tree->left->left = Tree->left->right = NULL;
			Tree->left->key = newKey;
		}
	}
	// Если ключ больше, то добавляем рекурсивно вправо
	if( newKey > Tree->key ){
		if( Tree->key != NULL ){
			push( newKey, Tree->right);
		}
		else{
			Tree->right = new treeNode;
			Tree->right->left = Tree->right->right = NULL;
			Tree->right->key = newKey;
		}
	}
}

void printTree(treeNode *&Tree){
	if (Tree != NULL){      // Пока не встретится пустое звено
	   out << Tree->key << endl;   // Отображаем корень дерева
	   printTree(Tree->left);    // Рекурсивная функция для вывода левого поддерева
	   printTree(Tree->right);   // Рекурсивная функции для вывода правого поддерева
	}
	else{
		return;
	}
}

int main(){

	treeNode* Tree = NULL;
	int a = 0;
	while ( !in.eof() ){
		in >> a;
		push(a, Tree);
	}

	printTree(Tree);

	return 0;
}