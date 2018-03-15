#include <iostream>
#include <fstream>
#include <vector>
#include <math.h>
#include <stdio.h>
#include <algorithm>    
using namespace std;

class Node{
private:
	long long m_key;
	Node*     m_left;
	Node*     m_right;

	long long m_depth;

public:
	Node(){
	}

	Node(long long key, Node* left, Node* right){
	    m_key = key;
		m_left = left;
		m_right = right;
		m_depth = 0;
	}
	Node(Node* toCopy){
		m_key   = toCopy->m_key;
		m_left  = toCopy->m_left;
		m_right = toCopy->m_right;
		m_depth = toCopy->m_depth;
	}

	
	long long GetKey(){
	    return m_key; 
	}

	long long GetDepth(){
	    return m_depth;
	}

	Node* GetNode(bool isLeft){
		if(isLeft){
			return m_left;
		}
		else {
			return m_right;
		}
	}
	void SetNode(bool isLeft, Node* toSet){
		if(isLeft)
			m_left = toSet;
		else
			m_right=toSet;
	}

	void SetDepth(long long depth){
	    m_depth = depth;
	}
	
};

class BinarySearchTree{
private:

    Node* m_root;
	
    vector<long long> keysForOneDepth;

	
	long long m_MAX_depth;

public:
	BinarySearchTree(){
		m_root = NULL;
		m_MAX_depth=0;
	}

	BinarySearchTree(Node* root){
		m_root = root;
		m_MAX_depth=0;
	}

	void SetMaxDepth(long long depth){
	    m_MAX_depth = depth;
	}

	vector<long long> GetVector(){
	    return keysForOneDepth;
	}
	void InsertNode(Node* toInsert){

		if(m_root==NULL){
			m_root=toInsert;
		    return;
		}

	    Node* actuallNode;
		actuallNode=m_root;
		while(true){
			if(actuallNode->GetKey()<toInsert->GetKey()){
				if(actuallNode->GetNode(false)==NULL){
				    actuallNode->SetNode(false, toInsert);
					break;
				}
				else 
				actuallNode = actuallNode->GetNode(false);
			}
			else if(actuallNode->GetKey()>toInsert->GetKey()){
				if(actuallNode->GetNode(true)==NULL){
				    actuallNode->SetNode(true, toInsert);
					break;
				}
				else 
				actuallNode = actuallNode->GetNode(true);
		    }
			else return;
		}

		toInsert->SetDepth(actuallNode->GetDepth()+1);

		if(toInsert->GetDepth()>m_MAX_depth) {
			m_MAX_depth = toInsert->GetDepth();
		}
		return;

	}

	void Search( ofstream& out,Node* node = NULL){
		
		if(node == NULL) { node = this->m_root;}
		out<<node->GetKey()<<endl;
		
		if(node->GetNode(true)!=NULL){
			Search(out, node->GetNode(true));
		}

		if(node->GetNode(false)!=NULL){
			Search(out, node->GetNode(false));
		}
	
	}

	void SearchForTask1(int* sumL, int* sumR, Node* node = NULL, bool isLeft = false){
	    if(node == NULL) {node = m_root;}
		int sumLeftForActuall  =0;
		int sumRightForActuall =0;
		if(node->GetNode(true)!=NULL){
			SearchForTask1( sumL, sumR, node->GetNode(true),  true);
			sumLeftForActuall = *sumL;
		}
		else 
			sumLeftForActuall  =0;
		if(node->GetNode(false)!=NULL){
			SearchForTask1( sumL, sumR, node->GetNode(false),  false);
			sumRightForActuall = *sumR;
		}
		else 
			sumRightForActuall =0;
		

		

		if(node->GetNode(true)==NULL&&node->GetNode(false)==NULL)
		{
			if(isLeft){
				*sumL=1;
				*sumR=0;
			}
			else{
				*sumR=1;
				*sumL=0;
			}
		}

		else 
		{
			if(sumLeftForActuall>sumRightForActuall)
			if ((floor(m_MAX_depth/2.0)+node->GetDepth()==m_MAX_depth))
			    keysForOneDepth.push_back(node->GetKey());	

			if(isLeft){ 
			    *sumL=sumLeftForActuall+sumRightForActuall+1;
				*sumR=0;
			}

		    else {
			    *sumR=sumLeftForActuall+sumRightForActuall+1;
				*sumL=0;
			}
		}
	}
	
	Node* GetRoot(){
		if(m_root!=NULL){
			return m_root; 
		}
	}

	void RemoveNode(long long key){
		Node* actuallNode       = m_root;
		Node* fatherForActuall = NULL;
		bool  isActuallLeftChild;

		while(true){
			if(key>actuallNode->GetKey()){
				if(actuallNode->GetNode(false)==NULL){
				    cout<<"Peak not founded"<<endl;
				    return;
				}
				else {
					fatherForActuall =actuallNode;
					actuallNode = actuallNode->GetNode(false);
					isActuallLeftChild = false;
				}
			}
			else if(key<actuallNode->GetKey()){
				if(actuallNode->GetNode(true)==NULL){
				    cout<<"Peak not founded"<<endl;
					return;
				}
				else{
					fatherForActuall = actuallNode;
					actuallNode = actuallNode->GetNode(true);
					isActuallLeftChild = true;
				}
			}
			else {

				if(actuallNode->GetNode(true)==NULL&&actuallNode->GetNode(false)==NULL){
				    delete actuallNode;
					if(fatherForActuall!=NULL) fatherForActuall->SetNode(isActuallLeftChild, NULL);
					break;
				}
				else if(actuallNode->GetNode(true)!=NULL&&actuallNode->GetNode(false)==NULL){
		            
					if(fatherForActuall!=NULL)
						fatherForActuall->SetNode(isActuallLeftChild, actuallNode->GetNode(true));
					else 
						m_root=actuallNode->GetNode(true);
					delete actuallNode;
					break;
				}
				else if(actuallNode->GetNode(true)==NULL&&actuallNode->GetNode(false)!=NULL){
		            
					if(fatherForActuall!=NULL)
						fatherForActuall->SetNode(isActuallLeftChild, actuallNode->GetNode(false));
					else
						m_root=actuallNode->GetNode(false);
					delete actuallNode;
					break;
				}
				else {
					
					Node* leftChild  = actuallNode->GetNode(true);
					Node* rightChild = actuallNode->GetNode(false);
					Node* toDelete   = actuallNode;

					Node* fatherToNodeToReplace = actuallNode;
					
					
					actuallNode = actuallNode->GetNode(false);
					if(actuallNode->GetNode(true)!=NULL){
			     		while(actuallNode->GetNode(true)!=NULL){
				     		fatherToNodeToReplace = actuallNode;
					    	actuallNode = actuallNode->GetNode(true);
					    }

						if(actuallNode->GetNode(false)==NULL)
							fatherToNodeToReplace->SetNode(true, NULL);
						else 
							fatherToNodeToReplace->SetNode(true, actuallNode->GetNode(false));

					    actuallNode->SetNode(true, leftChild);
					    actuallNode->SetNode(false, rightChild);
					    if(fatherForActuall!=NULL)
							fatherForActuall->SetNode(isActuallLeftChild, actuallNode);
						else 
							m_root = actuallNode;
					}
					else {
					    if(fatherForActuall!=NULL)
							fatherForActuall->SetNode(isActuallLeftChild, actuallNode);
						else 
							m_root = actuallNode;
					    actuallNode->SetNode(true, leftChild);
					}
					 delete toDelete;
				     break;
				}

			}
		}
	};

	void DeleteTree(Node* node = NULL){
		if(node==NULL) node = this->m_root;
		
		if(node->GetNode(true)!=NULL){
			DeleteTree(node->GetNode(true));
		}

		if(node->GetNode(false)!=NULL){
			DeleteTree(node->GetNode(false));
		}
	
		delete node;
	}
};

int main(){
 ifstream in;
 in.open("in.txt");
 
 long long key;
 long long keyToRemove;
 BinarySearchTree* tree = new BinarySearchTree();

 while(in>>key){
	 Node* peak = new Node(key, NULL, NULL);
	 tree->InsertNode(peak);
 }

 in.close();

 int sumL=0;
 int sumR=0;
 tree->SearchForTask1(&sumL, &sumR);
 vector <long long> keysOnOneLevel = tree->GetVector();
 if(keysOnOneLevel.size()%2==1){
	sort (keysOnOneLevel.begin(), keysOnOneLevel.end()); 
	tree->RemoveNode(keysOnOneLevel.at(keysOnOneLevel.size()/2));
 }

 ofstream out("out.txt");


 tree->Search(out);
 out.close();

 tree->DeleteTree();
 delete tree;

 system("pause");
 return 0;
}