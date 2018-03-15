#include <iostream>
#include <iomanip>
#include <fstream>
#include <vector>
#include <algorithm>

using namespace std;
bool flac = false;
bool signal = false;
class Tree;


class TreeNode
{
	friend class  Tree;
public:
	TreeNode(const int &d)
		:leftPtr(0),
		data(d),
		rightPtr(0)
	{

	}
	TreeNode()
	{
		leftPtr = 0;
		rightPtr = 0;
	
	}
	int getData() const
	{
		return data;
	}
	int data;
	int h;
	TreeNode *fatherPtr;
	TreeNode *leftPtr;

	TreeNode  *rightPtr;



};

class Tree
{
public:
	Tree();
	void insertNode(const int &);
	void preOrderTraversal(ofstream&) ;
	void postOrderTraversal(ofstream&) ;

	void deleteItem(int&);
	int rootHalfPathHeight = 0;
	void Tree::preOrderTrav(ofstream &fon);
	TreeNode *halfPathPtr;
	void middleValueFinding(TreeNode*, vector<int>&);
	void inOrderTraversal(ofstream&);
private:

	int middleValue = 0;
	void inOrderHelper(TreeNode*, ofstream&);
	void preOrderjourneying(TreeNode *, ofstream &);
	TreeNode* del;
	TreeNode  *rootPtr;
	TreeNode* FinalJourney(TreeNode*,vector<int>&);
	void GetH(TreeNode*);
	void postOrderTraversalHelper(TreeNode*, ofstream&);
	void insertNodeHelper(TreeNode**, const int&,TreeNode*);
	void deleteItemHelper(TreeNode*, int&);
	void preOrderHelper(TreeNode*, ofstream&) ;
	TreeNode* searchNode(TreeNode *, int&);
};

Tree::Tree() : rootPtr(nullptr) {};

void Tree ::insertNode(const int &value)
{
	insertNodeHelper(&rootPtr, value,NULL);
}

void Tree ::insertNodeHelper(TreeNode **ptr, const int &value, TreeNode* fatherPtr)
{
	if (*ptr == 0)
	{
		*ptr = new TreeNode(value);
		(*ptr)->fatherPtr=fatherPtr;
	}
		

	else
	{
		if (value < (*ptr)->data)
			insertNodeHelper(&((*ptr)->leftPtr), value, *ptr);
		else
		{
			if (value >(*ptr)->data)
				insertNodeHelper(&((*ptr)->rightPtr), value,*ptr);
			else
				cout << value << " dup" << endl;
		}
	}
}

TreeNode* Tree  ::searchNode(TreeNode *ptr,int &value)
{
	
	if (ptr!=NULL)
	{
		if (value == ((ptr)->data))
		{
			flac = true;
			return ptr;
		}
		else
		if (value > ((ptr)->data))
		{
			ptr = (ptr)->rightPtr;
			return searchNode(ptr, value);
		}
		else
		if (value < ((ptr)->data))
		{
			ptr = (ptr)->leftPtr;
			return searchNode(ptr, value);
		}

		if (flac)
		{
			return ptr;
		}
		else
		{
			return NULL;
		}
		
	}
	
	else
	{
		return NULL;
	}
	
}

void Tree::GetH(TreeNode *ptr)
{
	if (ptr->leftPtr == 0 && ptr->rightPtr== 0)
	{
		ptr->h = 0;
	}
	else
	{
		if (ptr->leftPtr != 0 && ptr->rightPtr != 0)
		{
			if (ptr->leftPtr->h > ptr->rightPtr->h)
			{
				ptr->h = ptr->leftPtr->h + 1;
			}
			else
			{
				ptr->h = ptr->rightPtr->h + 1;
			}
		}
		else if (ptr->leftPtr == 0)
		{
			
			ptr->h = ptr->rightPtr->h + 1;
		}
		else
		{
			ptr->h = ptr->leftPtr->h + 1;
		}
	}
}

void Tree::postOrderTraversal(ofstream& fon)
{
	postOrderTraversalHelper(rootPtr, fon);
}

void Tree::postOrderTraversalHelper(TreeNode *ptr, ofstream& fon)
{
	if (ptr != 0)
	{
		postOrderTraversalHelper(ptr->leftPtr, fon);
		postOrderTraversalHelper(ptr->rightPtr, fon);
	//	cout << ptr->data << endl;
		GetH(ptr);
	}
}

void Tree ::preOrderTraversal(ofstream &fon)
{
	preOrderHelper(rootPtr, fon);
}

TreeNode* Tree::FinalJourney(TreeNode *ptr,vector<int>& nodes)
{
	int count = 0;
	if(ptr!=NULL)
	{
		nodes.push_back(ptr->data);
		middleValue += ptr->data;
		if (ptr->leftPtr != 0 && ptr->rightPtr != 0)
		{
			if (ptr->leftPtr->h >= ptr->rightPtr->h)
			{

				return FinalJourney(ptr->leftPtr,nodes);
			}
			else
			{

				return FinalJourney(ptr->rightPtr,nodes);
			}
		}
		else
		
			if (ptr->leftPtr == 0 && ptr->rightPtr != 0)
			{
				return FinalJourney(ptr->rightPtr,nodes);
			}
			else
			if (ptr->rightPtr == 0 && ptr->leftPtr != 0)
			{
				
				return FinalJourney(ptr->leftPtr,nodes);
			}
			else if(ptr->leftPtr==0 && ptr->rightPtr == 0)
			{
			
					return ptr;
				
			}
		
		
		
	}
	 return NULL;

}

void Tree::middleValueFinding(TreeNode* ptr,vector<int>& nodes)
{
	bool psk = false;
	
	if (rootHalfPathHeight % 2 != 1)
	{
		nodes.push_back(ptr->data);
		int tempuroh1 = 0, tempuroh2 = 0;
		if (ptr->leftPtr == 0 && ptr->rightPtr == 0)
		{
			deleteItemHelper(ptr, ptr->data);
			psk = true;
		}
		if(!psk)
		{ 
		int lcount = 0, rcount = 0;
		TreeNode* leftRadical = FinalJourney(ptr->leftPtr,nodes);
		if (leftRadical == NULL)
		{
			leftRadical = ptr;
		}
		TreeNode* rightRadical = FinalJourney(ptr->rightPtr,nodes);
		if (rightRadical == NULL)
		{
			rightRadical = ptr;
		}
		sort(nodes.begin(),nodes.end());
		deleteItemHelper(rootPtr, nodes[rootHalfPathHeight/2]);
		/*middleValue += ptr->data;
		middleValue /= rootHalfPathHeight + 1;
		int min = abs(middleValue - (ptr->data));
		TreeNode* middle = halfPathPtr;
		int temp = 0;
		if (middle->leftPtr != 0 && middle->rightPtr != 0 && middle->leftPtr->h == middle->rightPtr->h &&!psk)
		{
			deleteItemHelper(middle, middle->data);
			psk = true;
		}
		if (halfPathPtr->leftPtr != 0)
		{
			tempuroh1 = halfPathPtr->leftPtr->h;
		}
		if (halfPathPtr->rightPtr != 0)
		{
			tempuroh2 = halfPathPtr->rightPtr->h;
		}
		if (!psk && ((middle == leftRadical && middle != rightRadical) || (middle->leftPtr->h) < (middle->rightPtr->h)))
		{
			if (middle->leftPtr != 0)
			{
				lcount += middle->leftPtr->h + 1;

			}
			middle = middle->rightPtr;
			rcount++;
			if (abs(middleValue - (middle->data)) < min)
			{
				min = middle->data;
			}
			/*if (!psk && (rcount == (((tempuroh1+tempuroh2) / 2)+1) || lcount == (((tempuroh1 + tempuroh2) / 2)+1)))
			{
				deleteItemHelper(middle, middle->data);
				psk = true;
			}
			while (middle != rightRadical && !psk)
			{
				/*if (!psk && (rcount == (((tempuroh1 + tempuroh2) / 2)+1) || lcount == (((tempuroh1 + tempuroh2) / 2)+1)))
				{
					deleteItemHelper(middle, middle->data);
					psk = true;
					break;
				}
				if (middle->leftPtr != 0 && middle->rightPtr != 0)
				{
					if (middle->leftPtr->h >= middle->rightPtr->h)
					{
						lcount++;
						middle = middle->leftPtr;
						if (abs(middleValue - (middle->data)) < min)
						{
							min = middle->data;
						}

					}
					else
					{
						rcount++;
						middle = middle->rightPtr;
						if (abs(middleValue - (middle->data)) < min)
						{
							min = middle->data;
						}
					}
				}
				else if (middle->leftPtr != 0 && middle->rightPtr == 0)
				{
					lcount++;
					middle = middle->leftPtr;
					if (abs(middleValue - (middle->data)) < min)
					{
						min = middle->data;
					}
				}
				else if (middle->rightPtr != 0 && middle->leftPtr == 0)
				{
					rcount++;
					middle = middle->rightPtr;
					if (abs(middleValue - (middle->data)) < min)
					{
						min = middle->data;
					}
				}
				else if (middle->leftPtr == 0 && middle->rightPtr == 0)
				{
					middle = rightRadical;
					if (abs(middleValue - (middle->data)) < min)
					{
						min = middle->data;
					}
				}
				psk = true;
			}
			/*if (!psk && (rcount == (((tempuroh1 + tempuroh2) / 2)+1) || lcount == (((tempuroh1 + tempuroh2) / 2)+1)))
			{
				deleteItemHelper(middle, middle->data);
				psk = true;
			}
		}
			if (!psk && ((middle == rightRadical && middle != leftRadical) || (middle->rightPtr->h) < (middle->leftPtr->h)) )
			{
				if (middle->rightPtr != 0)
				{
					rcount += middle->rightPtr->h + 1;
				}
				middle = middle->leftPtr;
				lcount++;
				if (abs(middleValue - (middle->data)) < min)
				{
					min = middle->data;
				}
				/*if ( !psk && (rcount == (((tempuroh1 + tempuroh2) / 2)+1 ) || lcount == (((tempuroh1 + tempuroh2) / 2)+1 )) )
				{
					deleteItemHelper(middle, middle->data);
					psk = true;
				}
				while (middle != leftRadical && !psk)
				{
					/*if (!psk && (rcount == (((tempuroh1 + tempuroh2) / 2)+1) || lcount == (((tempuroh1 + tempuroh2) / 2) +1)))
					{
						deleteItemHelper(middle, middle->data);
						psk = true;
						break;
					}
					if (middle->leftPtr != 0 && middle->rightPtr != 0)
					{
						if (middle->leftPtr->h >= middle->rightPtr->h)
						{
							lcount++;
							middle = middle->leftPtr;
							if (abs(middleValue - (middle->data)) < min)
							{
								min = middle->data;
							}

						}
						else
						{
							rcount++;
							middle = middle->rightPtr;
							if (abs(middleValue - (middle->data)) < min)
							{
								min = middle->data;
							}
						}
					}
					else if (middle->leftPtr != 0 && middle->rightPtr == 0)
					{
						lcount++;
						middle = middle->leftPtr;
						if (abs(middleValue - (middle->data)) < min)
						{
							min = middle->data;
						}
					}
					else if (middle->rightPtr != 0 && middle->leftPtr==0)
					{
						rcount++;
						middle = middle->rightPtr;
						if (abs(middleValue - (middle->data)) < min)
						{
							min = middle->data;
						}
					}
					else if (middle->leftPtr == 0 && middle->rightPtr == 0)
					{
						middle = leftRadical;
						if (abs(middleValue - (middle->data)) < min)
						{
							min = middle->data;
						}
					}
				}
				deleteItemHelper(rootPtr, min);
			/*	if (!psk && (rcount == (((tempuroh1 + tempuroh2) / 2)+1 ) || lcount == (((tempuroh1 + tempuroh2) / 2)+1 )) )
				{
					deleteItemHelper(middle, middle->data);
					psk = true;
				}*/

			}
			//	cout << leftRadical->data;
			/*int count = rootHalfPathHeight;

			while (leftRadical != halfPathPtr)
			{
				if (lcount == rootHalfPathHeight / 2 || rcount == rootHalfPathHeight / 2)
				{
					middle = leftRadical;
					deleteItemHelper(middle, middle->data);
					psk = true;
					break;
				}
				if ((leftRadical->fatherPtr)->leftPtr != 0 && leftRadical == (leftRadical->fatherPtr)->leftPtr)
				{
					rcount++;//may be
					leftRadical = leftRadical->fatherPtr;
					continue;
				}
				if ((leftRadical->fatherPtr)->rightPtr != 0 && leftRadical == (leftRadical->fatherPtr)->rightPtr)
				{
					lcount++;
					leftRadical = leftRadical->fatherPtr;
					continue;
				}

			}
			if ((lcount == rootHalfPathHeight / 2 || rcount == rootHalfPathHeight / 2) && !psk)
			{
				middle = leftRadical;
				deleteItemHelper(middle, middle->data);
				psk = true;
			}

			if (lcount != rootHalfPathHeight / 2 && rcount != rootHalfPathHeight / 2)
			{
				middle = leftRadical;
				while (middle != rightRadical)
				{
					if ((lcount == rootHalfPathHeight/2 || rcount == rootHalfPathHeight/2)&& !psk)
					{
						deleteItemHelper(middle, middle->data);
						psk = true;
						break;
					}
					if (middle->leftPtr != 0 && middle->rightPtr != 0)
					{
						if (middle->leftPtr->h >= middle->rightPtr->h)
						{
							lcount++;
							middle = (middle->leftPtr);
							continue;
						}
						else
						{
							rcount++;
							middle = (middle->rightPtr);
							continue;
						}
					}

					if (middle->leftPtr == 0 && middle->rightPtr != 0)
					{
						rcount++;
						middle = (middle->rightPtr);
						continue;
					}

					if (middle->rightPtr == 0 && middle->leftPtr != 0)
					{
						lcount++;
						middle = (middle->leftPtr);
						continue;
					}
				}
			}
			if ((lcount == rootHalfPathHeight/2 || rcount == rootHalfPathHeight/2)&&!psk)
			{
				deleteItemHelper(middle, middle->data);
				psk = true;
			}
			*/
		//}
		
	}
}

void Tree ::preOrderHelper(TreeNode *ptr, ofstream &fon) 
{

	if (ptr != 0)
	{
//		fon << ptr->data << endl;

		preOrderHelper(ptr->leftPtr, fon);

		preOrderHelper(ptr->rightPtr, fon);
		
	}
}
void Tree::inOrderTraversal(ofstream&fon)
{
	inOrderHelper(rootPtr, fon);
}

void Tree::inOrderHelper(TreeNode* ptr, ofstream&fon)
{
	if (ptr != 0 && ptr->leftPtr == 0 && ptr->rightPtr == 0 && ptr->fatherPtr==0)
	{
		halfPathPtr = ptr;
	}
	if(ptr!=0)
	{ 
	int tmpHeight = 0;
	inOrderHelper(ptr->leftPtr,fon);
	if (ptr->leftPtr != 0 && ptr->rightPtr != 0)
	{
		tmpHeight = ptr->leftPtr->h + ptr->rightPtr->h + 2;
	}
	else
		if (ptr->leftPtr == 0 && ptr->rightPtr != 0)
		{
			tmpHeight = ptr->rightPtr->h + 1;
		}
		else
			if (ptr->rightPtr == 0 && ptr->leftPtr != 0)
			{
				tmpHeight = ptr->leftPtr->h + 1;
			}
	if (tmpHeight > rootHalfPathHeight)
	{
		rootHalfPathHeight = tmpHeight;
		halfPathPtr = ptr;
	}
	inOrderHelper(ptr->rightPtr, fon);
	}
}

void Tree::preOrderTrav(ofstream &fon)
{
	preOrderjourneying(rootPtr, fon);
}
void Tree::preOrderjourneying(TreeNode *ptr, ofstream &fon)
{
	if(ptr!=0)
	{ 
	fon << ptr->data << endl;
	preOrderjourneying(ptr->leftPtr, fon);
	preOrderjourneying(ptr->rightPtr, fon);
	}
}

void Tree::deleteItem( int& value) 
{
	deleteItemHelper(rootPtr, value);
}

void Tree::deleteItemHelper(TreeNode *ptr, int &value)
{
	if (ptr == 0)
	{
		cout << "Your binary tree is empty!\n";
	}
	else
	{
		if (ptr == rootPtr)
		{
			TreeNode* tempNode;
			tempNode = searchNode((rootPtr), value);
			if (tempNode != 0)
			{
				if ((tempNode)->leftPtr == 0 && (tempNode)->rightPtr == 0 && (tempNode)->fatherPtr == 0)
				{
					rootPtr = 0;
				}
				else if ((tempNode)->leftPtr == 0 && (tempNode)->rightPtr == 0)
				{
					if ((tempNode->fatherPtr)->leftPtr == tempNode)
					{
						(tempNode->fatherPtr)->leftPtr = 0;
					}
					if ((tempNode->fatherPtr)->rightPtr == tempNode)
					{
						(tempNode->fatherPtr)->rightPtr = 0;
					}

					tempNode = 0;
				}
				else if ((tempNode)->leftPtr == 0)
				{
					if(tempNode->fatherPtr!=NULL)
					{
						if ((tempNode->fatherPtr)->leftPtr == tempNode)
						{
							tempNode->data = (tempNode->rightPtr)->data;
							(tempNode->fatherPtr)->leftPtr = tempNode->rightPtr;

						}
						if ((tempNode->fatherPtr)->rightPtr == tempNode)
						{
							tempNode->data = (tempNode->rightPtr)->data;
							(tempNode->fatherPtr)->rightPtr = tempNode->rightPtr;

						}
					}
					else
					{	
						
						tempNode = tempNode->rightPtr;
						rootPtr = tempNode;

						
					}
					
				}
				else if ((tempNode)->rightPtr == 0)
				{
					if (tempNode->fatherPtr != NULL)
					{ 
						if ((tempNode->fatherPtr)->leftPtr == tempNode)
						{
							tempNode->data = (tempNode->leftPtr)->data;
							(tempNode->fatherPtr)->leftPtr = tempNode->leftPtr;

						}
						if ((tempNode->fatherPtr)->rightPtr == tempNode)
						{
							tempNode->data = (tempNode->leftPtr)->data;
							(tempNode->fatherPtr)->rightPtr = tempNode->leftPtr;

						}
					}
					else
					{
						tempNode = tempNode->leftPtr;
						rootPtr = tempNode;
					}
				}
				else if (((tempNode)->rightPtr != 0) && ((tempNode)->leftPtr != 0))
				{
					TreeNode* tempuroNode;
					tempNode->data = (tempNode->rightPtr)->data;
					tempuroNode = tempNode->rightPtr;
					while ((tempuroNode)->leftPtr != 0)
					{
						(tempNode)->data = ((tempuroNode)->leftPtr)->data;
						tempuroNode = tempuroNode->leftPtr;
					}
					deleteItemHelper(tempuroNode, ((tempuroNode)->data));
				}
			}
		}
		else
		{
			TreeNode* tempNode = ptr;
			tempNode = searchNode((tempNode), tempNode->data);
				if (tempNode != 0)
				{
					if ((tempNode)->leftPtr == 0 && (tempNode)->rightPtr == 0 && (tempNode)->fatherPtr == 0)
					{
						rootPtr = 0;
					}
					else if ((tempNode)->leftPtr == 0 && (tempNode)->rightPtr == 0)
					{
						if ((tempNode->fatherPtr)->leftPtr == tempNode)
						{
							(tempNode->fatherPtr)->leftPtr = 0;
						}
						if ((tempNode->fatherPtr)->rightPtr == tempNode)
						{
							(tempNode->fatherPtr)->rightPtr = 0;
						}

						tempNode = 0;
					}
					else if ((tempNode)->leftPtr == 0)
					{
						if ((tempNode->fatherPtr)->leftPtr == tempNode)
						{
							tempNode->data = (tempNode->rightPtr)->data;
							(tempNode->fatherPtr)->leftPtr = tempNode->rightPtr;

						}
						if ((tempNode->fatherPtr)->rightPtr == tempNode)
						{
							tempNode->data = (tempNode->rightPtr)->data;
							(tempNode->fatherPtr)->rightPtr = tempNode->rightPtr;

						}
					}
					else if ((tempNode)->rightPtr == 0)
					{
						if ((tempNode->fatherPtr)->leftPtr == tempNode)
						{
							tempNode->data = (tempNode->leftPtr)->data;
							(tempNode->fatherPtr)->leftPtr = tempNode->leftPtr;

						}
						if ((tempNode->fatherPtr)->rightPtr == tempNode)
						{
							tempNode->data = (tempNode->leftPtr)->data;
							(tempNode->fatherPtr)->rightPtr = tempNode->leftPtr;

						}
					}
					else if (((tempNode)->rightPtr != 0) && ((tempNode)->leftPtr != 0))
					{
						TreeNode* tempuroNode;
						tempNode->data = (tempNode->rightPtr)->data;
						tempuroNode = tempNode->rightPtr;
						while ((tempuroNode)->leftPtr != 0)
						{
							(tempNode)->data = ((tempuroNode)->leftPtr)->data;
							tempuroNode = tempuroNode->leftPtr;
						}
						deleteItemHelper(tempuroNode, ((tempuroNode)->data));
					}
				}
			}


		}
	}


int main()
{
	Tree intTree;
	int intValue;

	ifstream fin("input.txt");
	ofstream fout("output.txt");
	fin >> intValue;
	intTree.insertNode(intValue);
	while (fin >> intValue)
	{

		intTree.insertNode(intValue);
	}

	intTree.postOrderTraversal(fout);

	intTree.inOrderTraversal(fout);
	vector<int> nodes;
	intTree.middleValueFinding(intTree.halfPathPtr,nodes);
	intTree.preOrderTrav(fout);
	return 0;
}