#include<fstream>
#include<vector>

using namespace std;

typedef int Tinfo;
typedef int Tkey;
template<class Tkey, class Tinfo>

struct Node{
	Tkey   key;
	Tinfo info;
	Node* left;
	Node* right;

	Node(Tkey   key, Tinfo info)
		:key(key), info(info)
	{
		left = right = 0;
	}
};

class Tree{
private:

	vector <int> vec;
	Node<Tkey, Tinfo>*& find_node(Node<Tkey, Tinfo>*& node, const Tkey key);
	void delete_node(Node< Tkey, Tinfo>*& node);

public:

	Node<Tkey, Tinfo> *root;
	int count_ver;
	Tree();
	~Tree();

	bool get_info(const Tkey key, Tinfo& value);
	int  set_info(Node<Tkey, Tinfo>*& node);
	bool add(const Tkey key);
	bool delete_key(const Tkey key);
	bool solve();
	void go(Node<Tkey, Tinfo>*& node);
	void res(Node<Tkey, Tinfo>*& n, ostream& out);
	

};


Tree::Tree()
{
	root = 0;  count_ver = 0;
}

void rec_del(Node<Tkey, Tinfo>*& node){
	if (node != 0){
		rec_del(node->left);
		rec_del(node->right);
		delete node;
	};
}

Tree::~Tree()
{
	rec_del(root);
	root = 0;
}


bool Tree::get_info(const Tkey key, Tinfo& info)
{
	Node<Tkey, Tinfo>*& node = find_node(root, key);
	if (node == 0) return false;
	else{
		info = node->info;
		return true;
	};
}

int Tree::set_info(Node<Tkey, Tinfo>*& node)
{
	int left = 0, right = 0;
	if (node == 0)
		return 0;
	else{
		left = set_info(node->left);
		right = set_info(node->right);
		if (left != right){
			count_ver++;
			node->info = 1;
		}
		return left + right + 1;
	}
}

bool Tree::add(const Tkey key)
{
	Node<Tkey, Tinfo>*& tmp = find_node(root, key);
	if (tmp != 0){
		return false;
	}
	else{
		tmp = new Node<Tkey, Tinfo>(key, 0);
		return true;
	};
}


bool Tree::delete_key(const Tkey key)
{
	Node<Tkey, Tinfo>*& node = find_node(root, key);
	if (node == 0) return false;
	else{
		delete_node(node);
		return true;
	};
}



bool Tree::solve()
{
	set_info(root);
	if (count_ver % 2 == 0)
		return false;
	go(root);
	int k = vec.size() / 2;
	delete_key(vec[k]);
	return true;
}

void Tree::go(Node<Tkey, Tinfo>*& node)
{
	if (node != 0)
	{
		go(node->left);
		if (node->info != 0)
			vec.push_back(node->key);
		go(node->right);
	}
}

void Tree::res(Node<Tkey, Tinfo>*& node, ostream& out)
{
	if (node != 0){
		out << node->key << endl;
		if (node->left != 0)
			res(node->left, out);
		if (node->right != 0)
			res(node->right, out);
	};
}




void Tree::delete_node(Node< Tkey, Tinfo>*& node)
{
	if (node == 0) return;
	if ((node->right == 0) || (node->left == 0)){
		Node<Tkey, Tinfo>* t = node;
		node = (node->right != 0) ? node->right : node->left;
		delete t;
		return;
	};
	Node<Tkey, Tinfo>** q = &(node->right);
	while ((*q)->left != 0){
		q = &((*q)->left);
	};
	node->key = (*q)->key;
	node->info = (*q)->info;
	delete_node(*q);
	return;
}

Node<Tkey, Tinfo>*& Tree::find_node(Node<Tkey, Tinfo>*& node, const Tkey key)
{
	if (node == 0)
		return node;
	if (node->key == key)
		return node;
	if (key < node->key){
		return find_node(node->left, key);
	}
	else{
		return find_node(node->right, key);
	};
}


int main()
{
	ifstream fin("in.txt");
	Tree beresa;
	do
	{
		int N;
		fin >> N;
		if (!fin.fail())
			beresa.add(N);
	} while (!fin.fail());
	fin.close();

	beresa.solve();

	ofstream fout("out.txt");
	beresa.res(beresa.root, fout);
	
	fout.close();
	return 0;
}