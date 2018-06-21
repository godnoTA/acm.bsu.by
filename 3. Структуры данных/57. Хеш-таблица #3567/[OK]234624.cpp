#include <iostream>
#include <fstream>
#include <vector>

void hash(int key, std::vector<int> &hash_table, int c);

int main() {
    std::ifstream fin("input.txt");
    std::ofstream fout("output.txt");

    int table_size;
    int const_c;
    int amount_of_keys;

    fin >> table_size;
    fin >> const_c;
    fin >> amount_of_keys;

    std::vector<int> hash_table((unsigned long) table_size, -1);

    for (int i = 0; i < amount_of_keys; ++i) {
        int key;
        fin >> key;
        hash(key, hash_table, const_c);
    }
    fin.close();

    for (auto key : hash_table) {
        fout << key << ' ';
    }
    fout.close();

    return 0;
}

void hash(int key, std::vector<int> &hash_table, int c) {
    int m = (int) hash_table.size();
    for (int i = 0; i < m; ++i) {
        int index = ((key % m) + c * i) % m;
        if (hash_table[index] == key) {
            break;
        }
        if (hash_table[index] == -1) {
            hash_table[index] = key;
            break;
        }
    }
}
