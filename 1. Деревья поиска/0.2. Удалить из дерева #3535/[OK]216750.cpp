#include<iostream>
#include<fstream>
#include<string>


using namespace std;

	
	class Tree
	{
	private:
		struct TreeItem
		{
			int Info;
			TreeItem* LSon;
			TreeItem* RSon;
			TreeItem* Father;
			TreeItem() { LSon = RSon = Father = NULL; }
		};
		TreeItem* Root; //как отдельная структура

	public:
		Tree()   //конструктор без параметров, создающий пустое дерево
		{
			Root = NULL;
		}
		bool Find(int, int&, TreeItem* &);
		void Insert(int);
		void Delete(int);
		void straight_walk(TreeItem *t, ostream&);
		void EraseNode(TreeItem* &);
		TreeItem* get_root();
	};
	Tree::TreeItem* Tree::get_root()
	{
		return Root;
	}

	bool Tree::Find(int el, int&k, TreeItem* &t) //int el-тот элемент, которрый мы ищем
	{                                            //int &k-кол-во сравнений(& потому что потом будем работать с ним через адрес)
		if (Root == NULL)                        //TreeItem* &t-указатель на найденный эл-т или последний просмотренный
		{
			t = NULL;
			return false;
		}
		t = Root;
		while (true)
		{
			if (t->Info == el)
			{
				k++;
				return true;
			}
			if (t->Info > el)
			{
				if (!t->LSon)
					return false;
				t = t->LSon;
			}
			else
			{
				if (!t->RSon)
					return false;
				t = t->RSon;
			}

		}
	}
	void Tree::Insert(int n)
	{
		TreeItem *place, *pointer;
		int k = 0;
		if (Find(n, k, place))
			return;
		pointer = new TreeItem;
		pointer->Info = n;
		if (place == NULL)
		{
			Root = pointer;
		}
		else
		{
			pointer->Father = place;
			if (place->Info < n)
				place->RSon = pointer;
			else
				place->LSon = pointer;
		}

	}
	void Tree::EraseNode(TreeItem* &s)
	{
		TreeItem *q;
		if (s->LSon != NULL)
			q = s->LSon;
		else
			q = s->RSon;
		if (q != NULL)
			q->Father = s->Father;
		if (s->Father == NULL)
			Root = q;
		else
			if ((s->Father)->LSon == s)
				(s->Father)->LSon = q;
			else
				(s->Father)->RSon = q;
		delete s;
		s = NULL;
	}

	void Tree::Delete(int e)
	{
		TreeItem*s = new TreeItem;
		TreeItem*q;
		int ch = 0;
		if (!Find(e, ch, s))
			return;
		else
		{
			if ((s->LSon != NULL) && (s->RSon != NULL))
			{
				q = s->RSon;
				while (q->LSon != NULL)
					q = q->LSon;
				s->Info = q->Info;
				EraseNode(q);
			}
			else EraseNode(s);

		}
	}
	void Tree::straight_walk(TreeItem *t, ostream &fout) //прямой
	{
		if (!t) return;
		cout << t->Info << endl;
		fout << t->Info<<"\n";
		if (t->LSon != NULL) straight_walk(t->LSon, fout);
		if (t->RSon != NULL) straight_walk(t->RSon, fout);
	}
	
	int main()
	{
		fstream fin("input.txt");
		ofstream fout("output.txt");
		int el, el1, el2, el3;
		string str;
		Tree T;
		fin >> el;
		
		//fin >> el1; fin >> el2;
		while (!fin.eof())
		{
			fin >> el3;
			T.Insert(el3);
			cout << el3 << endl;
		}
		T.Delete(el);
		cout << "\nAfter:" << endl;
		T.straight_walk(T.get_root(), fout);
		fin.close();
		return 0;
	}
