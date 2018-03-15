#include<iostream>
#include<fstream>
#include<vector>
using std::vector;
using std::ifstream;
using std::ofstream;
using std::endl;

bool sumToK(vector<bool>fst, vector<bool>snd, int k) {
	for (int i = 0; i < k + 1; i++) {
		if (fst[i] && snd[k - i]) {
			return true;
		}
	}
	return false;
}

struct Node {
	long long key;
	Node* left_son;
	Node* right_son;
	vector<bool> left_pathes;
	vector<bool> right_pathes;
	vector<bool> up_pathes;

	Node() {
		Node(100);
	}

	Node(int k) {
		left_pathes = vector<bool>(k + 1,false);
		right_pathes = vector<bool>(k + 1,false);
		up_pathes = vector<bool>(k + 1,false);
	}

	bool isremovable(int k) {
		if (sumToK(left_pathes, right_pathes, k) || 
			sumToK(left_pathes, up_pathes, k) ||
			sumToK(right_pathes, up_pathes, k)||
			left_pathes[k]||
			right_pathes[k]||
			up_pathes[k]) {
			return false;
		}
		return true;
	}
};


vector<bool> shift(vector<bool> given_vector) {
	vector<bool> shifted;
	shifted.push_back(false);
	for (int i = 0; i < (int)given_vector.size() - 1; ++i) {
		shifted.push_back(given_vector.at(i));
	}
	if (given_vector.back() ){
		shifted.pop_back();
		shifted.push_back(true);
	}
	return shifted;
}

vector<bool> add(vector<bool>fst, vector<bool>snd) {
	vector<bool> sum(fst.size());
	for (int i = 0; i < (int)fst.size(); ++i) {
		sum[i] = fst[i] || snd[i];
	}
	return sum;
}

class Tree {
	Node* root;
public:
	Tree() {
		root = NULL;
	}

	Tree(Node*given_root) {
		root = given_root;
	}

	Node* getroot() {
		return root;
	}
	void insert(long long key, int k) {
		if (root == NULL) {
			root = new Node(k);
			root->key = key;
			root->left_son = NULL;
			root->right_son = NULL;
		}
		else {
			Node * next = root;
			Node * previous = NULL;
			while (next!=NULL) {
				previous = next;
				if (key > next->key) {
					next = next->right_son;
				}

				else if (key < next->key) {
					next = next->left_son;
				}

				else if (key == next->key) {
					break;
				}
			}

			if (key > previous->key) {
				previous->right_son = new Node(k);
				previous->right_son->key = key;
				previous->right_son->left_son = NULL;
				previous->right_son->right_son = NULL;
			}

			if (key < previous->key) {
				previous->left_son = new Node(k);
				previous->left_son->key = key;
				previous->left_son->left_son = NULL;
				previous->left_son->right_son = NULL;
			}
		}
		
	}

	
	void remove(Node*node) {
		if (node != NULL) {
			if (node->right_son != NULL) {
				Node*node_to_remove = node->right_son;
				while (node_to_remove->left_son != NULL)
				{
					node_to_remove = node_to_remove->left_son;
				}

				node->key = node_to_remove->key;
				remove(node_to_remove);
			}
			else {
				if (node->left_son != NULL) {
					node->key = node->left_son->key;
					node->right_son = node->left_son->right_son;
					//delete old pointer
					node->left_son = node->left_son->left_son;
				}
				else {
					node->key = -1;
				}
			}
	 }
		
	}
};

//right and left
void postorder(Node*root, Node*new_root,int k) {

	 {
		if (root->left_son != NULL) {
			
			postorder(root->left_son, new_root, k);
		
			root->left_pathes = shift(add(root->left_son->left_pathes, root->left_son->right_pathes));
		}
		
		if (root->right_son != NULL) {
			postorder(root->right_son, new_root, k);
			
			root->right_pathes = shift(add(root->right_son->left_pathes, root->right_son->right_pathes));
		}

		if (root->left_son == NULL && root->right_son == NULL) {
			root->left_pathes[0] = true;
			root->right_pathes[0] = true;
		}
		
	}
	

	
}

//up and max

Node* preorder(Node*root, int k) {
	if (root->right_son != NULL) {
		root->right_son->up_pathes = shift(add(root->up_pathes, root->left_pathes));
		Node*right_max = preorder(root->right_son, k);
		if (right_max != NULL){
			return right_max;
				//preorder(root->right_son, k);
		}	
	}
	
	{
		if (root->isremovable(k)) {
			return root;
		}
		else {
			if (root->left_son != NULL) {
				root->left_son->up_pathes = shift(add(root->up_pathes, root->right_pathes));
				return preorder(root->left_son, k);
			}
			return NULL;
		}
	}
	
	/*if (root->up_pathes[k] == true) {
		return;
	}

	if (root->right_son != NULL) {
		root->right_son->up_pathes = shift(add(root->up_pathes, root->left_pathes));
		if (root->right_son->isremovable(k)) {
			//или не факт, что макс?
			max = root->right_son;
		}
		preorder(root->right_son, max, k);
	}
	
	if (max == NULL && root->left_son!= NULL) {
		root->left_son->up_pathes = shift(add(root->up_pathes, root->right_pathes));
		if (root->left_son->isremovable(k)) {
			*max = *root->left_son;
		}
		preorder(root->left_son, max, k);
	}
	return;*/
}

void write(Node*root, ofstream& out) {
	if (root != NULL && root->key!=-1) {
		out << root->key << endl;
		write(root->left_son, out);
		write(root->right_son, out);
	}
	

}



int main() {

	Tree tree;
	
	ifstream in("tst.in");
	ofstream out("tst.out");
	char*str = new char[100];
	in.getline(str, 100);
	long long k = atoi(str);

	if (k < 1000) {
		int num_of_verticles = 0;
		while (in) {
			in.getline(str, 100);

			if (strcmp(str, "")) {
				tree.insert(atoi(str), k);
				num_of_verticles++;
			}
			
		}

		if (num_of_verticles == 0 || num_of_verticles == 1 && k!=0) {
			out << "Empty" << endl;
		}
		//left-right-up
		
		
		else
		{
			if (num_of_verticles == 1 && k == 0) {
				out << tree.getroot()->key << endl;
			}
			else {
				Node* new_root = tree.getroot();
				postorder(tree.getroot(), new_root, k);
				if (tree.getroot()->left_son == NULL) {
					tree.getroot()->left_pathes[0] = true;
				}
				if (tree.getroot()->right_son == NULL) {
					tree.getroot()->right_pathes[0] = true;
				}
				Node* max = preorder(tree.getroot(), k);
				tree.remove(max);
				write(tree.getroot(), out);
			}
			
		}
	}

	else
	{
		int num_of_verticles = 0;
		while (in) {
			in.getline(str, 100);
			if (strcmp(str, "")) {
				tree.insert(atoi(str), 1);
				num_of_verticles++;
			}
			
		}
		if (num_of_verticles == 0 || num_of_verticles == 1) {
			out << "Empty" << endl;
		}
		else {
			
			Node*next = tree.getroot();
			while (next->right_son != NULL) {
				next = next->right_son;
			}
			tree.remove(next);
			write(tree.getroot(), out);
		}
	}
	return 0;
}