/*Найти все вершины, через которые проходят полупути длины K между висячими вершинами.
Выбрать из них те, которые находятся на наименьшей глубине,
и удалить(левым удалением) из них среднюю по значению, если она существует.*/

/*Входной файл содержит в первой строке число K (2 < K ≤ 109),
а в последующих строках — последовательность чисел — ключи вершин в порядке добавления в дерево.*/

/*Выходной файл должен содержать последовательность ключей вершин, полученную прямым левым обходом итогового дерева.*/

#include <iostream>
#include <queue>
#include <stack>
#include <fstream>
#include <vector>
#include <algorithm>
#include <iterator>


using namespace std;

class Node {
	public:
		Node(int k) : key(k) {}
		int key;
		Node *left = nullptr;
		Node *right = nullptr;
		Node *parent = nullptr;
		bool no_list = 1;
		int list_key = -1;
		int list_height = -1;
		int depth = -1;
};

class Tree {
public:
	Node *Root;
	Tree() : Root(0) {}
	~Tree() {
		DestroyNode(Root);
	}
	void DestroyNode(Node* node) {
		if (node) {
			DestroyNode(node->left);
			DestroyNode(node->right);
			delete node;
		}
	}
	
	void Add (int x) {
		
		Node *node = this->Root;
		Node *myNode = new Node(x);
		if (node == NULL) {
			Root = myNode;
			Root->depth = 0;
			return;
		}
		while (node != NULL) {
			
			if ( x < node->key) {
				if (node->left != NULL)
					node = node->left;
				else {
					myNode->parent = node;
					node->left = myNode;
					myNode->depth = node->depth + 1;
					break;
				}
					
			}
			else if (x > node->key) {
				if (node->right != NULL)
					node = node->right;
				else {
					myNode->parent = node;
					node->right = myNode;
					myNode->depth = node->depth + 1;
					break;
				}
			
			}
			else {
				return;
			}
		}


	}
	void Left_Go_Round(ofstream &out, vector <int> &l, bool &flag) {
		Node *root = this->Root;
		if (root == nullptr) {
			return;
		}
		stack<Node *> st;
		st.push(root);
		while (!st.empty()) {
			Node *temp = st.top();
			//temp->no_list = 0;
			 
			if (temp->right == NULL && temp->left == NULL) {
				temp->no_list = 0;
l.push_back(temp->key);

			}
			//l.push_back(temp->key);

			st.pop();
			if (flag == true)
				out << temp->key << endl;


			if (temp->right != NULL) {
				st.push(temp->right);
				//if (temp->right == NULL && temp->left == NULL)
					//temp->list = 1;
			}

			if (temp->left != NULL) {
				st.push(temp->left);
				//if (temp->right == NULL && temp->left == NULL)
					//temp->list = 1;
			}
		}


	}
	bool Contains(int value) {
		Node n = this->Find(value);
		return (n.left != nullptr || n.right != nullptr);
	}
	Node Find(int value) {
		Node *tmp = this->Root;
		while (tmp->left != nullptr || tmp->right != nullptr) {
			if (tmp->key - value > 0) {
				tmp = tmp->left;
			}
			else
				if (tmp->key - value < 0) {
					tmp = tmp->right;
				}
				else
					break;
		}

		return *tmp;
	}
	void Delete(Node *node, int value) {
		if (node == NULL)
			return;
		if (value < node->key)
			Delete(node->left, value);
		else
			if (value > node->key)
				Delete(node->left, value);
			else {
				Node *q = node;
				if (q->right == NULL) {
					node = q->left;
					delete q;
				}
				else
					if (q->left == NULL) {
						node = q->right;
						delete q;
					}
					else Delete_1(&(q->left), &q);
			}
	}
	void Delete_1(Node **r, Node **q)
	{
		Node *s;

		if ((**r).right == nullptr)
		{
			(**q).key = (**r).key;
			//(**q).Count = (**r).Count; 
			*q = *r;
			s = *r;
			*r = (**r).left;
			delete s;
		}
		else Delete_1(&((**r).right), q);
	}

};

int height(Node *p) {
	Node *temp = p;
	int h1 = 0, h2 = 0;
	if (p == NULL) return(0);
	if (p->left) { h1 = height(p->left); }
	if (p->right) { h2 = height(p->right); }
	return(max(h1, h2) + 1);
}
void MainWork(Tree *TTree, int &K, vector <int> list) {

	vector <vector<int> > Parent;

	int Deep = RAND_MAX;
	vector<int> poisk;
	int depth = 10000000;
	vector<Node* > vert;
	for (int i = 0; i < list.size(); i++)
		for (int j = i+1; j < list.size(); j++) {
			Node list1 = TTree->Find(list[i]);
			Node list2 = TTree->Find(list[j]);
			Node *par = list1.parent;
			int dl = 1;
			while (par != NULL) {
				par->list_key = list1.key;
				par->list_height = dl;
				dl++;
				par = par->parent;
			}
			par = list2.parent;
			dl = 1;
			while (par != NULL) {
				if (par->list_key == list1.key)
					break;
				dl++;
				par = par->parent;
			}
			if (par->list_height + dl == K) {
				if (par->depth < depth) {
					vert.clear();
					depth = par->depth;
				}
				if (par->depth == depth)
					vert.push_back(par);
			}
		}
	int sum = 0;
	for (int i = 0; i < vert.size(); i++) {
		sum += vert[i]->key;
	}
	if (vert.size() == 0 || sum % vert.size() != 0) return;
	for (int i = 0; i < vert.size(); i++)
		if (vert[i]->key == (sum / vert.size()))
			TTree->Delete(vert[i], vert[i]->key);
		
	/*for (int i = 0; i < list.size(); i++) {
		Node list1 = TTree->Find(list[i]);
		Node *par = list1.parent;
		while (par != NULL) {
			Parent[i].push_back(par->key);
			par = par->parent;
		}

		vector<int>::iterator it1, it2;
		for (int i = 0; i < Parent.size(); i++) {
			for (int j = i + 1; j < Parent[i].size(); j++) {
				it1 = find_first_of(Parent[i].begin(), Parent[i].end(), Parent[j].begin(), Parent[j].end());
				it2 = find_first_of(Parent[j].begin(), Parent[j].end(), Parent[i].begin(), Parent[i].end());
				if (K == distance(Parent[i].begin(), it1) + distance(Parent[j].begin(), it2) && 
					distance(Parent[i].begin(),it1) == distance(Parent[j].begin(),it2)) {

					Node  n = TTree->Find(*it1);

					if ((Parent[i].size() - distance(it1, Parent[i].end()) < Deep)) {

						Deep = Parent[i].size() - distance(it1, Parent[i].end());
						poisk.clear();
						poisk.push_back(*it1);
					}
					else
						if (Parent[i].size() - distance(it1, Parent[i].end()) == Deep)
							poisk.push_back(*it1);
				}
			}
		}
	}
	for(int i = 0; i < poisk.size(); i++ )
		TTree->Delete(TTree->Root, poisk[i]);*/
}

int main() {

	ifstream in("in.txt");
	int key_of_lenght; in >> key_of_lenght;
	Tree TTree;
	int temp;
	while (in >> temp) 
		TTree.Add(temp);
	in.close();

	ofstream out("out.txt");
	vector<int> list;

	
	bool fl = false;
	TTree.Left_Go_Round(out, list, fl);
	MainWork(&TTree, key_of_lenght, list);
	fl = true;
	TTree.Left_Go_Round(out, list, fl);

	out.close();
	cout << endl;

	return 0;
}