#include<fstream>
#include<iostream>

using namespace std;

ifstream fin("input.txt");
ofstream fout("output.txt");

struct Node{
	int key;
	Node* left;
	Node* right;
};


void push(int x, Node*& berezka){
	if (berezka == NULL){
		berezka = new Node;
		berezka->key = x;
		berezka->left = NULL;
		berezka->right = NULL;
	}

	if (x < berezka->key){
		if (berezka->left == NULL){
			berezka->left = new Node();
			berezka->left->key = x;
			berezka->left->left = NULL;
			berezka->left->right = NULL;
		}
		else{
			push(x, berezka->left);
		}
	}

	if (x > berezka->key){
		if (berezka->right == NULL){
			berezka->right = new Node();
			berezka->right->key = x;
			berezka->right->left = NULL;
			berezka->right->right = NULL;
		}
		else{
			push(x, berezka->right);
		}
	}
}

void show(Node* drevo){
	if (drevo == NULL){
		return;
	}
	//cout << drevo->key <<" Ключ"<< endl;
	fout << drevo->key << endl;
	show(drevo->left);
	show(drevo->right);
}

int main(){
	//setlocale(LC_ALL, ".1251");
	Node* bereza = NULL;
	int a = 0;
	while (!fin.eof()){
		fin >> a;
		push(a, bereza);
	}

	show(bereza);

	return 0;
}