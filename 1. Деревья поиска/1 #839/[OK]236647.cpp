#include<fstream>
#include  <vector>
using namespace std;
typedef int TreeKey;
typedef int TreeValue;
#include <sstream>

template<class TKey, class TValue>
struct Node {
	TreeKey   key;
	TreeValue value;
	Node* l;
	Node* r;

	Node(TreeKey key, TreeValue value) :key(key), value(value)
	{
		l = 0;
		r = 0;
	}
};

class TMyTree {
private:
	Node<TreeKey, TreeValue>*& findNode(Node<TreeKey, TreeValue>*& node, const TreeKey key);
	void deleteNode(Node< TreeKey, TreeValue>*& node);
	std::vector <int> v;
public:
	TMyTree();
	bool Add(const TreeKey key);
	bool deleteByKey(const TreeKey key);
	~TMyTree();
	bool getValue(const TreeKey key, TreeValue& value);
	void cleare();
	int  SetLabels(Node<TreeKey, TreeValue>*& n);
	void Traversal(Node<TreeKey, TreeValue>*& n);
	bool Solve();
	int KolVer;
	Node<TreeKey, TreeValue> *root;
	void result(Node<TreeKey, TreeValue>*& n, std::ostream& out);
};


TMyTree::TMyTree()
{
	root = 0;
	KolVer = 0;
}

Node<TreeKey, TreeValue>*& TMyTree::findNode(Node<TreeKey, TreeValue>*& node, const TreeKey key)
{
	if (node == 0)
		return node;
	if (node->key == key)
		return node;
	if (key < node->key) {
		return findNode(node->l, key);
	}
	else {
		return findNode(node->r, key);
	};
}
bool TMyTree::Add(const TreeKey key)
{
	Node<TreeKey, TreeValue>*& tmp = findNode(root, key);
	if (tmp != 0) {
		return false;
	}
	else {
		tmp = new Node<TreeKey, TreeValue>(key, 0);
		return true;
	};
}

int TMyTree::SetLabels(Node<TreeKey, TreeValue>*& n) // начало setLabels(root) рекурсивно считает кол-во отличий в правом поддереве
{
	int left = 0;
	int right = 0;
	if (n == 0)
		return 0;
	else {
		left = SetLabels(n->l);
		right = SetLabels(n->r);
		if (left != right) {
			KolVer++;
			n->value = 1;
		}
		return left + right + 1;
	}
}


void TMyTree::deleteNode(Node< TreeKey, TreeValue>*& node)
{
	if (node == 0) return;
	if ((node->r == 0) || (node->l == 0)) {
		Node<TreeKey, TreeValue>* t = node;
		node = (node->r != 0) ? node->r : node->l;
		delete t;
		return;
	};
	Node<TreeKey, TreeValue>** q = &(node->r);
	while ((*q)->l != 0) {
		q = &((*q)->l);
	};
	node->key = (*q)->key;
	node->value = (*q)->value;
	deleteNode(*q);
	return;
}



bool TMyTree::deleteByKey(const TreeKey key)
{
	Node<TreeKey, TreeValue>*& n = findNode(root, key);
	if (n == 0) return false;
	else {
		deleteNode(n);
		return true;
	};
}




bool TMyTree::getValue(const TreeKey key, TreeValue& value)
{
	Node<TreeKey, TreeValue>*& n = findNode(root, key);
	if (n == 0) return false;
	else {
		value = n->value;
		return true;
	};
}

TMyTree::~TMyTree() // деструктор
{
	cleare();
}
void rec_del(Node<TreeKey, TreeValue>*& n) { // рекурсивное удаление листов
	if (n != 0) {
		rec_del(n->l);
		rec_del(n->r);
		delete n;
	};
}
void TMyTree::cleare() // удаление дерева
{
	rec_del(root);
	root = 0;
}

bool TMyTree::Solve()
{
	SetLabels(root); // считает количество листьев в правом и левом поддеревьях
	if (KolVer % 2 == 0) return false;
	Traversal(root);
	int k = v.size() / 2;
	deleteByKey(v[k]); // удаление листа по ключу
	return true;
}



void TMyTree::result(Node<TreeKey, TreeValue>*& n, ostream& out)
{
	if (n != 0) {
		out << n->key << endl;
		if (n->l != 0) result(n->l, out);
		if (n->r != 0) result(n->r, out);
	};
}

void TMyTree::Traversal(Node<TreeKey, TreeValue>*& n) //левый обход
{
	if (n != 0)
	{
		Traversal(n->l);
		if (n->value != 0)
			v.push_back(n->key);
		Traversal(n->r);
	}
}
int main(int argc, char* argv[])
{
	ifstream fi("in.txt");
	ofstream fo("out.txt");
	string line;
	TMyTree tree;
	while (!(fi.eof()))
	{
		int n;
		fi >> n;
		tree.Add(n);
	}

	tree.Solve();
	tree.result(tree.root, fo);
	fi.close();
	fo.close();
	return 0;
}