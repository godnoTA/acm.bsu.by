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
	Node(Node* toCopy){
		m_key   = toCopy->m_key;
		m_left  = toCopy->m_left;
		m_right = toCopy->m_right;
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
 in.open("input.txt");
 
 long long key;
 long long keyToRemove;
 BinarySearchTree* tree = new BinarySearchTree();

 in>>keyToRemove;
 while(in>>key){
	 Node* peak = new Node(key, NULL, NULL);
	 tree->InsertNode(peak);
 }

 in.close();

 ofstream out("output.txt");
 tree->RemoveNode(keyToRemove);
 tree->Search(out);
 out.close();

 tree->DeleteTree();
 delete tree;

 system("pause");
 return 0;
}