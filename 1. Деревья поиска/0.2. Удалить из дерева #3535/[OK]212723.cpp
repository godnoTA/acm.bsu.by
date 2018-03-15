#include <iostream> 
#include <fstream> 
#include <vector>
#include <iterator> 
#include <algorithm>

using namespace std;

ofstream fout("output.txt");
ifstream fin("input.txt");

struct TreeItem {
	int Info;
	TreeItem* Father;
	TreeItem* LSon;
	TreeItem* RSon;
	TreeItem() { LSon = RSon = NULL; }
};

TreeItem* Root = NULL;
int Half_H = 0;
vector<TreeItem*> mas;

bool find(TreeItem* R, int a, TreeItem* &t)
{
	{
		if (R == NULL)
		{
			t = NULL;
			return false;
		}
		t = R;
		for (;;)
		{
			if (t->Info == a)
				return true;
			if (t->Info > a)
			{
				if (t->LSon == NULL)
					return false;
				t = t->LSon;
			}
			else
			{
				if (t->RSon == NULL)
					return false;
				t = t->RSon;
			}
		}
	}
}



enum BinSide
{
	Left,
	Right,
	Nul
};



TreeItem* Find(int data, TreeItem* node)
{
	if (node->Info == data) return node;
	if (node->Info > data)
	{
		return Find(data, node->LSon);
	}
	return Find(data, node->RSon);
}

 BinSide MeForParent(TreeItem* node)
 {
	if(node->Father==NULL) return Nul;
	if (node->Father->LSon == node) return BinSide(Left);
	if (node->Father->RSon == node) return BinSide(Right);
}

int HeightOfTree(TreeItem * node)
{
	if (node == NULL)
		return -1;
	int left, right;
	if (node->LSon != NULL) {
		left = HeightOfTree(node->LSon);
	}
	else
		left = -1;
	if (node->RSon != NULL) {

		right = HeightOfTree(node->RSon);
	}
	else
		right = -1;
	/*if(node->LSon==NULL && node->RSon==NULL)
		return -1;*/
	int max = left > right ? left : right;
	
	return max + 1;
}

bool Insert(TreeItem* &R, int info)
{
	TreeItem *s, *q;
	if (find(R, info, s))
		return false;
	q = new TreeItem;
	q->Info = info;
	if (s == NULL)
	{
		R = q;
		q->Father = NULL;
	}
	else
	{
		q->Father = s;
		if (s->Info < info)
			s->RSon = q;
		else
			s->LSon = q;
	}
}
void Print(TreeItem* &s)
{
	fout << s->Info << endl;
}

void Print_R(TreeItem* &s)
{
	cout << s->Info << endl;
}

void post_order_walk_p(TreeItem* s, void vizit(TreeItem* &s))
{
	if (s != NULL)
	{
		vizit(s);
		post_order_walk_p(s->LSon, vizit);
		post_order_walk_p(s->RSon, vizit);

	}
}

void post_order_walk(TreeItem* s, void vizit(TreeItem* &s))
{
	if (s != NULL)
	{

		post_order_walk(s->LSon, vizit);
		post_order_walk(s->RSon, vizit);
		vizit(s);

	}
}

void EraseItem(TreeItem *&s)
{
	if (s->Father != NULL)
	{

		if ((s->Father)->LSon == s)
			(s->Father)->LSon = NULL;
		else
			(s->Father)->RSon = NULL;
	}
	else
		Root = NULL;
	delete s;
	s = NULL;
}



void post_order(TreeItem* R, void vizit(TreeItem* &s))
{
	if (R == NULL)
	{
		cout << "Empty tree" << endl;
		return;
	}
	post_order_walk(R, vizit);
}
void Destroy(TreeItem * &R)
{

	post_order_walk(R, EraseItem);
}

void Find_H(TreeItem * R, int He)
{
	if (R != NULL)
	{
		if (Half_H == He / 2)
			mas.push_back(R);
		if (R->LSon != NULL)
		{
			Half_H++;
			Find_H(R->LSon, He);
		}
		if (R->RSon != NULL)
		{
			Half_H++;
			Find_H(R->RSon, He);
		}
	}
	Half_H--;
}




// TreeItem* FindMin(TreeItem* node)
// {
//	 TreeItem* OurMin = node->RSon;
//	 if (OurMin->LSon == NULL)
//		 return OurMin;
//	 while (OurMin->LSon != NULL)
//		 OurMin = OurMin->LSon;
//	 return OurMin;
// }
//
//
//
//
//void Delete(TreeItem* OurKey)
//{
//	if (OurKey == NULL)
//		return;
//	int  me = MeForParent(OurKey);
//	if(me==BinSide(Nul))
//		return;
//	//Если у узла нет дочерних элементов, его можно смело удалять
//	if (OurKey->LSon == NULL && OurKey->RSon == NULL)
//	{
//		if (me == BinSide(Left))
//		{
//			OurKey->Father->LSon = NULL;
//		}
//		else
//		{
//			OurKey->Father->RSon = NULL;
//		}
//		return;
//	}
//
//
//	if (OurKey->LSon == NULL)
//	{
//		if (me == BinSide(Left))
//		{
//			OurKey->Father->LSon = OurKey->RSon;
//		}
//		else
//		{
//			OurKey->Father->RSon = OurKey->RSon;
//		}
//
//		OurKey->RSon->Father = OurKey->Father;
//		return;
//	}
//
//
//	if (OurKey->RSon == NULL)
//	{
//		if (me == BinSide(Left))
//		{
//			OurKey->Father->LSon = OurKey->LSon;
//		}
//		else
//		{
//			OurKey->Father->RSon = OurKey->LSon;
//		}
//
//		OurKey->LSon->Father = OurKey->Father;
//		return;
//	}
//	TreeItem* ToChanged = FindMin(OurKey);
//	OurKey->Info = ToChanged->Info;
//	Delete(ToChanged);
//}


void ReplaceChild(TreeItem *&father, TreeItem *&old, TreeItem *&n, TreeItem *&Tree) { 
	if (father == NULL) { 
		Tree = n; 
		return; 
	} 
	if (father->LSon == old) { 
		father->LSon = n; 
		return; 
	} 
	if (father->RSon == old) { 
		father->RSon = n; 
	} 
} 

void DeleteIteratively(int x, TreeItem *&Tree) { 
	TreeItem *father = NULL; 
	TreeItem *v = Tree; 
	TreeItem *h = NULL; 
	while (true) { 
		if (v == NULL) 
			return; 
		if (x < v->Info) { 
			father = v; 
			v = v->LSon; 
			continue; 
		} 
		if (x > v->Info) { 
			father = v; 
			v = v->RSon; 
			continue; 
		} 
		if (x == v->Info) 
			break; 
	} 
	TreeItem *min_node; 
	TreeItem *result = NULL; 
	if (v->LSon == NULL) 
		result = v->RSon; 
	else { 
		if (v->RSon == NULL) 
			result = v->LSon; 
		else { 
			TreeItem *min_node_parent = v; 
			min_node = v->RSon; 
			while (min_node->LSon != NULL) { 
				min_node_parent = min_node; 
				min_node = min_node->LSon; 
			} 
			result = v; 
			v->Info = min_node->Info; 
			ReplaceChild(min_node_parent, min_node, min_node->RSon, Tree); 
		} 
	} 
	ReplaceChild(father, v, result, Tree); 
}

int main() {
	TreeItem *T = NULL;

	int y;
	int podel;
	fin>>podel;
	while (!fin.eof()) {
		fin >> y;
		Insert(T, y);
	}
	fin.close();
	//vector<bool> B_mas;
	//vector<int> Tr_Fin;
	//int h = HeightOfTree(T);
	//cout << "Height: " << h << endl;
	///*if(h%2!=0)
	//{
	//	post_order_walk_p(T,Print);
	//	return 0;
	//}*/
	//Find_H(T, h);
	//for (int i = 0; i < mas.size(); i++)
	//{
	//	B_mas.push_back(HeightOfTree(mas[i]->LSon)==HeightOfTree(mas[i]->RSon));
	//}
	//for (int i = 0; i < mas.size(); i++)
	//{
	//	if (B_mas[i] == true)
	//		Tr_Fin.push_back(mas[i]->Info);
	//}
	//sort(Tr_Fin.begin(), Tr_Fin.end());
	//for (int i = 0; i < Tr_Fin.size(); i++)
	//{
	//	cout<<Tr_Fin[i]<<endl;
	//}
	//if (Tr_Fin.size() % 2 != 0)
	//{
	//	Delete(Find( Tr_Fin[Tr_Fin.size()/ 2],T));
	//}
	DeleteIteratively(podel,T);
	post_order_walk_p(T,Print);
	fout.close();
	Destroy(T);
	return 0;
}