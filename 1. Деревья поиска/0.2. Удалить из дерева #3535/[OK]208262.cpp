#include <iostream>
#include <fstream>
#include <ctime>
using namespace std;


class Tree;


class Node
{
private:
	int data;
	Node *left;
	Node *right;
public:
	friend Tree;
	Node() { data = 0; left = 0; right = 0; }
	Node(int x) : data(x), left(0), right(0) {}
	int getdata() { return data; }
	Node *getleft() { return left; }
	Node *getright() { return right; }
};





class Tree
{
	Node *root;
public:
	Tree() :root(0) {}



	bool insert(int x)
	{
		bool b = true;
		Node** ppPrev = &root;
		Node* p = root;
		while (p)
		{
			if (x < p->data)
			{
				ppPrev = &(p->left);
				p = p->left;
			}
			else
			{
				if (x > p->data)
				{
					ppPrev = &(p->right);
					p = p->right;
				}
				else
				{
					b = false;
					return b;
				}
			}
		}
		if (b == true)
		{
			*ppPrev = new Node(x);
		}
		return b;
	}



	void delete_element(int x) {

		bool c = false;

		Node* prev = nullptr;
		Node* cur = root;

		while (cur)
		{



			if (x < cur->data)
			{
				prev = cur;
				cur = cur->left;
			}





			else if (x > cur->data)
				{
					prev = cur;
					cur = cur->right;
				}








			else
				{



				if (prev == nullptr)
				{
					if (cur->right == nullptr && cur->left == nullptr)
					{
						root = nullptr; return;
					}
					else if (cur->right == 0) { root = cur->left; return; }
					else if (cur->left == 0) { root = cur->right; return; }

				}



				

					if (cur->right == nullptr)
					{

						if (cur->left == nullptr)
						{
							if (prev->left == cur) prev->left = nullptr;
							else prev->right = nullptr;
						}

						else
						{
							if (prev->left == cur) prev->left = cur->left;
							else prev->right = cur->left;
						}

					}



					else if (cur->left == nullptr)
					{
						if (prev->left == cur) prev->left = cur->right;
						else prev->right = cur->right;
					}

					else {

						Node* temp = cur;
						Node* prevtemp = cur;

						cur = cur->right;

						while (cur) {
							if (c == false)
							{
								prev = cur;
								cur = cur->left;
								c = true;
							}
							else {
								prevtemp = prev;
								prev = cur;
								cur = cur->left;
							}
						}


						temp->data = prev->data;
						if (prevtemp->left == prev)
							prevtemp->left = prev->right;
						else prevtemp->right = prev->left;



					   }

				
					break;
				}
			
		}
		


		return;

		 
	}






	void remove_node(int x) {



		Node* prev = nullptr;
		Node* cur = root;

		while (cur)
		{



			if (x < cur->data)
			{
				prev = cur;
				cur = cur->left;
			}





			else if (x > cur->data)
			{
				prev = cur;
				cur = cur->right;
			}








			else
			{

				if (cur->right == nullptr && cur->left == nullptr)
				{
					if (prev)
					{
						if (prev->left == cur) prev->left = nullptr;
						else prev->right = nullptr;
						return;
					}
					else { root = nullptr; return; }
				}

				else if (cur->right == nullptr)
				{
					if (prev)
					{
						if (prev->left == cur) prev->left = cur->left;
						else prev->right = cur->left;
						return;
					}
					else
					{
						root = cur->left; return;
					}
				}

				else if (cur->left == nullptr)
				{
					if (prev)
					{
						if (prev->left == cur) prev->left = cur->right;
						else prev->right = cur->right;
						return;
					}
					else
					{
						root = cur->right; return;
					}
				}


				else
				{




					if (cur->right->left == nullptr)
					{
						cur->data = cur->right->data;
						cur->right = cur->right->right; return;
					}
					else
					{
						Node* temp = cur;
						Node* predok = cur->right;
						cur = cur->right->left;

						while (cur)
						{
							if (cur->left)
							predok = predok->left;
							prev = cur;
							cur = cur->left;
						}


						temp->data = prev->data;
						predok->left = prev->right;

					}




				}



			}


		}
		return;

	}



	/////////////////////////////////////////////////
	//              Рекурсивный отсортированный вывод (inorder)
	void inorder_rec(ofstream& fo)
	{
		inorder_rec2(root, fo);
	}

	void inorder_rec2(Node *t, ofstream& fo)
	{
		if (t == nullptr)
		{
			return;
		}
		else
		{
			inorder_rec2(t->left, fo);
			fo << t->data << "\n";
			inorder_rec2(t->right, fo);
		}
	}




	/////////////////////////////////////////////////
	//                       Рекурсивный прямой вывод (preorder)
	void preorder_rec(ofstream& fo)
	{
		preorder_rec2(root, fo);
	}

	void preorder_rec2(Node *t, ofstream& fo)
	{
		if (t == nullptr)
		{
			return;
		}
		else
		{

			
			fo << t->data << endl;
			preorder_rec2(t->left, fo);
			preorder_rec2(t->right, fo);
		}
	}




	/////////////////////////////////////////////////
	//                     Рекурсивный обратный вывод (postorder)
	void postorder_rec(ofstream& fo)
	{
		postorder_rec2(root, fo);
	}

	void postorder_rec2(Node *t, ofstream& fo)
	{
		if (t == nullptr)
		{
			return;
		}
		else
		{
			postorder_rec2(t->left, fo);
			postorder_rec2(t->right, fo);
			fo << t->data << endl;
		}
	}



};




int main() {

	Tree tree;
	int x; int y;

	ifstream fi("input.txt");

	fi >> y;

	while (fi >> x)
	{
		tree.insert(x);
	}


	

	ofstream fo("output.txt");


	tree.remove_node(y);
	tree.preorder_rec(fo);

	cout << endl << clock();

	system("pause");
	return 0;

}