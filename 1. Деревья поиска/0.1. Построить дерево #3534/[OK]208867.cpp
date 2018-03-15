#include <iostream>
#include <fstream>
#include <set>
using namespace std;

class Node{
private:
	long long m_key;
	Node*     m_left;
	Node*     m_right;
public:
	Node(){
	}
	Node(long long key, Node* left, Node* right){
	    m_key = key;
		m_left = left;
		m_right = right;
	}

	
	int GetKey(){
	    return m_key; 
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
};

class BinarySearchTree{
private:
    Node* m_root;
public:
	BinarySearchTree(){
		m_root = NULL;
	}

	BinarySearchTree(Node* root){
		m_root = root;
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
					return;
				}
				else 
				actuallNode = actuallNode->GetNode(false);
			}
			else if(actuallNode->GetKey()>toInsert->GetKey()){
				if(actuallNode->GetNode(true)==NULL){
				    actuallNode->SetNode(true, toInsert);
					return;
				}
				else 
				actuallNode = actuallNode->GetNode(true);
		    }
			else return;
		}

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

	Node* getRoot(){
		if(m_root!=NULL){
			return m_root; 
		}
	}

	void DeleteTree(Node* node){
		
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
 in.open("input.txt");
 
 long long key;
 BinarySearchTree* tree = new BinarySearchTree(); 
 while(in>>key){
	 Node* peak = new Node(key, NULL, NULL);
	 tree->InsertNode(peak);
 }

 in.close();

 ofstream out("output.txt");
 tree->Search(out);
 out.close();

 tree->DeleteTree(tree->getRoot());
 delete tree;

 system("pause");
 return 0;
}