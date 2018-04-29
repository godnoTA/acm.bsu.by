#include <iostream>
#include <fstream>
#include <set> 

using namespace std;

int main()
{
	ifstream fin("input.txt");
	ofstream fos("output.txt");
	set<int> mySet;
	int size, consta, count, key;
	fin >> size;
	fin >> consta;
	fin >> count;
	int* hashTable = new int[size];
	for (int i = 0; i < size; i++) {
		hashTable[i] = -1;
	}
	for (int i = 0; i < count; i++) {
		fin >> key;
		if (mySet.end() == mySet.find(key)) {
			mySet.insert(key);
			for (int j = 0; j < count; j++) {
				int hash = (int)(((key % size) + consta * j) % size);
				if (hashTable[hash] == (-1)) {
					hashTable[hash] = key;
					break;
				}
			}
		}
	}
	for (int i = 0; i < size; i++) {
		fos << hashTable[i];
		fos << " ";
	}
	fos.close();
	fin.close();
    return 0;
}