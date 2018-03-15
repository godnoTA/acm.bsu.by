#include <iostream>
#include <fstream>
#include <vector>
#include <ctime>
using namespace std;


class Tree;


class Node
{
private:
	int data;
	Node *left;
	Node *right;

	int height;
	int depth;
	int level;
	int max_halfpath_length;

public:
	friend Tree;
	Node() { data = 0; left = 0; right = 0; height = -1; depth = -1; level = -1; max_halfpath_length = -1; }
	Node(int x) : data(x), left(0), right(0) { height = -1; depth = -1; level = -1; max_halfpath_length = -1; }
	int getdata() { return data; }
	Node *getleft() { return left; }
	Node *getright() { return right; }
};

vector<Node*> lists;
vector<pair<vector<Node*>, int>> all_pathes;
vector<Node*> all_vertexes;
vector<Node*> end_vertex;


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








	void put_depth(Node* t, Node* prevt) {

		if (t == nullptr) return;

		if (!prevt)
		{
			t->depth = 0;
		}

		else
		{
			t->depth = prevt->depth + 1;
		}

		prevt = t;
		all_vertexes.push_back(t);
		put_depth(t->left, prevt);
		put_depth(t->right, prevt);


	}

	void put_depth()
	{
		put_depth(root, nullptr);
	}






	void put_numerics(Node* t) {


		Node* cur = t;
		if (cur == nullptr) return;


		else
		{
			put_numerics(cur->left);
			put_numerics(cur->right);
			if (cur->left == nullptr && cur->right == nullptr)
			{
				cur->height = 0;
				cur->max_halfpath_length = 0;
				lists.push_back(cur);
			}
			else if (cur->left == nullptr)
			{
				cur->height = cur->right->height + 1;
				cur->max_halfpath_length = cur->height;
			}
			else if (cur->right == nullptr)
			{
				cur->height = cur->left->height + 1;
				cur->max_halfpath_length = cur->height;
			}
			else
			{
				cur->height = (cur->left->height > cur->right->height) ? 1 + cur->left->height : 1 + cur->right->height;
				cur->max_halfpath_length = 2 + cur->left->height + cur->right->height;
			}
			cur->level = cur->height - cur->depth;

		}


	}

	void put_numerics()
	{
		put_numerics(root);
	}






	void pp(ofstream& fo) {

		put_depth();
		put_numerics();




		vector<Node*> path;
		vector<pair<vector<Node*>, int>> pathes_l;
		int k1 = 0;
		int k2 = 0;
		int max_l = 0;
		int s = 0;






		if (lists.size() > 1) {





			if (root->left == nullptr || root->right == nullptr)
			{


				Node* z = root;
				Node* cur = nullptr;
				Node* central = nullptr;

				max_l = lists.at(0)->depth;
				s = lists.at(0)->data + root->data;


				for (int i = 0; i < lists.size(); i++)
				{
					path.clear();
					path.push_back(z);
					cur = lists.at(i);
					z = root;
					while (z)
					{
						if (cur->data < z->data)
						{
							z = z->left;
							path.push_back(z);
							if (cur->depth % 2 == 0 && z->depth == (cur->depth / 2)) central = z;
						}
						else if (cur->data > z->data)
						{
							z = z->right;
							path.push_back(z);
							if (cur->depth % 2 == 0 && z->depth == (cur->depth / 2)) central = z;
						}
						else if (cur->data == z->data)
						{
							break;
						}
					}

					if (central) { path.push_back(central); path.push_back(central); }
					all_pathes.push_back(make_pair(path, root->data + cur->data));

					if (path.at(path.size() - 1) == path.at(path.size() - 2))
					{
						if (path.size() - 2 > max_l) max_l = path.size() - 2;
					}
					else
					{
						if (path.size() > max_l) max_l = path.size();
					}

				}


			}






			for (int i = 0; i < lists.size() - 1; i++)
			{
				for (int j = i + 1; j < lists.size(); j++)
				{

					path.clear();
					k1 = 0; k2 = 0; s = 0;
					Node* cur1 = lists.at(i);
					Node* cur2 = lists.at(j);
					Node* central = nullptr;






					if (cur1->level != cur2->level)
					{
						s = cur1->data + cur2->data;
						Node* cur = root;
						while (cur)
						{
							if (cur1->data < cur->data && cur2->data > cur->data)
							{

								path.push_back(cur);
								Node* cur3 = cur;
								Node* cur4 = cur;

								while (cur3)
								{
									if (cur1->data < cur3->data) {
										cur3 = cur3->left; k1++;
										path.push_back(cur3);
									}
									else if (cur1->data > cur3->data) {
										cur3 = cur3->right; k1++;
										path.push_back(cur3);
									}
									else if (cur1->data == cur3->data) {
										break;
									}
								}


								while (cur4)
								{
									if (cur2->data < cur4->data) {
										cur4 = cur4->left; k2++;
										path.push_back(cur4);
									}
									else if (cur2->data > cur4->data) {
										cur4 = cur4->right; k2++;
										path.push_back(cur4);
									}
									else if (cur2->data == cur4->data) {
										break;
									}
								}



								if ((k1 + k2) % 2 == 0)
								{
									central = cur;
									if (k1 < k2)
									{
										central = cur;

										while (k1 != k2)
										{
											if (cur2->data < central->data)
											{
												central = central->left;
												k1++; k2--;
											}
											else if (cur2->data > central->data)
											{
												central = central->right;
												k1++; k2--;
											}
											else break;
										}
									}

									else if (k1 > k2)
									{
										central = cur;

										while (k1 != k2)
										{
											if (cur1->data < central->data)
											{
												central = central->left;
												k1--; k2++;
											}
											else if (cur1->data > central->data)
											{
												central = central->left;
												k1--; k2++;
											}
											else break;
										}
									}
									path.push_back(central); path.push_back(central);

								}


								all_pathes.push_back(make_pair(path, s));


								if (path.at(path.size() - 1) == path.at(path.size() - 2))
								{
									if (path.size() - 2 > max_l) max_l = path.size() - 2;
								}
								else
								{
									if (path.size() > max_l) max_l = path.size();
								}



								break;
							}


							else if (cur1->data < cur->data && cur2->data < cur->data)
							{
								cur = cur->left;
							}
							else if (cur1->data > cur->data && cur2->data > cur->data)
							{
								cur = cur->right;
							}


						}

					}







					else
					{
						s = 0;
						Node* prev1 = nullptr;
						Node* prev2 = nullptr;
						Node* prev = nullptr;
						Node* cur = root;
						while (cur)
						{
							if (cur1->data < cur->data && cur2->data > cur->data)
							{

								path.push_back(cur);
								Node* cur3 = cur;
								Node* cur4 = cur;

								while (cur3)
								{
									if (cur1->data < cur3->data) {
										prev2 = cur3;
										cur3 = cur3->left; k1++;
										if (cur3 != cur1)
											path.push_back(cur3);
									}
									else if (cur1->data > cur3->data) {
										prev2 = cur3;
										cur3 = cur3->right; k1++;
										if (cur3 != cur1)
											path.push_back(cur3);
									}
									else if (cur1->data == cur3->data) {
										break;
									}
								}


								while (cur4)
								{
									if (cur2->data < cur4->data) {
										prev1 = cur4;
										cur4 = cur4->left; k2++;
										if (cur4 != cur2)
											path.push_back(cur4);
									}
									else if (cur2->data > cur4->data) {
										prev1 = cur4;
										cur4 = cur4->right; k2++;
										if (cur4 != cur2)
											path.push_back(cur4);
									}
									else if (cur2->data == cur4->data) {
										break;
									}
								}

								if (cur1->data + prev1->data <= cur2->data + prev2->data)
								{
									s = cur1->data + prev1->data;
									prev = prev1;
									path.push_back(cur1);
									k2--;
								}
								else {
									s = cur2->data + prev2->data;
									prev = prev2;
									path.push_back(cur2);
									k1--;
								}

								if ((k1 + k2) % 2 == 0)
								{
									central = cur;
									if (k1 < k2)
									{
										central = cur;

										while (k1 != k2)
										{
											if (prev->data < central->data)
											{
												central = central->left;
												k1++; k2--;
											}
											else if (prev->data > central->data)
											{
												central = central->right;
												k1++; k2--;
											}
											else break;
										}
									}

									else if (k1 > k2)
									{
										central = cur;

										while (k1 != k2)
										{
											if (cur1->data < central->data)
											{
												central = central->left;
												k1--; k2++;
											}
											else if (cur1->data > central->data)
											{
												central = central->left;
												k1--; k2++;
											}
											else break;
										}
									}



									path.push_back(central); path.push_back(central);

								}



								if (find(end_vertex.begin(), end_vertex.end(), prev1) == end_vertex.end()) {
									all_pathes.push_back(make_pair(path, s));
									end_vertex.push_back(prev1);
								}



								if (path.at(path.size() - 1) == path.at(path.size() - 2))
								{
									if (path.size() - 2 > max_l) max_l = path.size() - 2;
								}
								else
								{
									if (path.size() > max_l) max_l = path.size();
								}


								break;
							}


							else if (cur1->data < cur->data && cur2->data < cur->data)
							{
								cur = cur->left;
							}
							else if (cur1->data > cur->data && cur2->data > cur->data)
							{
								cur = cur->right;
							}


						}
					}




				}

			}





		}









		///////////////////1 list
		else {

			if (lists.at(0)->depth % 2 == 0)
			{
				Node* nod = lists.at(0);
				fo << root->data + nod->data << endl;
				for (int i = 0; i < all_vertexes.size(); ++i)
				{
					if (all_vertexes.at(i)->depth == (lists.at(0)->depth / 2))  nod = all_vertexes.at(i);
				}


				make_root(nod);
				preorder_rec(fo);
				return;

			}
			else { fo << root->data + lists.at(0)->data << endl; preorder_rec(fo); return; }
		}







		int q = 0;
		for (int i = 0; i < all_pathes.size(); ++i)
		{
			q = all_pathes.at(i).first.size();
			if (all_pathes.at(i).first.at(q - 1) == all_pathes.at(i).first.at(q - 2))
			{
				if (all_pathes.at(i).first.size() - 2 == max_l) pathes_l.push_back(all_pathes.at(i));
			}
			else if (all_pathes.at(i).first.size() == max_l) pathes_l.push_back(all_pathes.at(i));
		}







		if (!pathes_l.empty())
		{
			vector<Node*> we_need = pathes_l.at(0).first;
			Node* centr = nullptr;
			Node* p1; Node* p2;
			int sum = 0;
			int min_q = pathes_l.at(0).second;

			for (int i = 0; i < pathes_l.size(); ++i)
			{
				if (pathes_l.at(i).second < min_q)
				{
					min_q = pathes_l.at(i).second;
					we_need = pathes_l.at(i).first;
				}
			}



			if (we_need.at(we_need.size() - 1) == we_need.at(we_need.size() - 2))
				make_root(we_need.at(we_need.size() - 1));


			fo << min_q << endl;

		}



		preorder_rec(fo);

	}







	void change(Node* prevparent, Node* parent, Node* cur)
	{


		if (parent->left == cur) {

			parent->left = cur->right;
			cur->right = parent;


			if (prevparent == nullptr)
			{
				root = cur;
			}
			else
			{
				if (prevparent->left == parent)
				{
					prevparent->left = cur;
				}
				else prevparent->right = cur;
			}

		}


		if (parent->right == cur) {

			parent->right = cur->left;
			cur->left = parent;


			if (prevparent == nullptr)
			{
				root = cur;
			}
			else
			{
				if (prevparent->left == parent)
				{
					prevparent->left = cur;
				}
				else prevparent->right = cur;
			}



		}



	}


	void make_root(Node* cur)
	{
		if (cur == nullptr || cur == root) return;
		Node* temp = root;
		Node* prevtemp = nullptr;
		Node* prprtemp = nullptr;
		while (temp->data != cur->data)
		{
			if (cur->data < temp->data)
			{
				prprtemp = prevtemp;
				prevtemp = temp;
				temp = temp->left;
			}
			else if (cur->data > temp->data)
			{
				prprtemp = prevtemp;
				prevtemp = temp;
				temp = temp->right;
			}
		}
		change(prprtemp, prevtemp, temp);

		make_root(cur);

	}










	void out_depth(Node* t)
	{
		if (t == nullptr) return;
		cout << t->data << "   " << t->depth << endl;
		out_depth(t->left);
		out_depth(t->right);
	}
	void out_depth() { out_depth(root); }



	void out_height(Node* t)
	{
		if (t == nullptr) return;
		cout << t->data << "   " << t->height << endl;
		out_height(t->left);
		out_height(t->right);
	}
	void out_height() { out_height(root); }



	void out_level(Node* t)
	{
		if (t == nullptr) return;
		cout << t->data << "   " << t->level << endl;
		out_level(t->left);
		out_level(t->right);
	}
	void out_level() { out_level(root); }


	void out_path(Node* t)
	{
		if (t == nullptr) return;
		cout << t->data << "   " << t->max_halfpath_length << endl;
		out_path(t->left);
		out_path(t->right);
	}
	void out_path() { out_path(root); }


};




int main() {

	Tree tree;
	int x; int y;

	ifstream fi("in.txt");


	while (fi >> x)
	{
		tree.insert(x);
	}




	ofstream fo("out.txt");


	//tree.remove_node(y);

	tree.pp(fo);
	cout << clock();



	system("pause");
	return 0;

}