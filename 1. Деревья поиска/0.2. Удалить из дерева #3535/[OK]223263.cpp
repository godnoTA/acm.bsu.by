#include <fstream>

struct TreeItem {
    int value;
    TreeItem *left;
    TreeItem *right;

    TreeItem(int _value) {
        this->value = _value;
        left = nullptr;
        right = nullptr;
    }
};

void left_round(TreeItem *&root, std::ofstream &fout);

void add_element(TreeItem *root, int number);

void delete_element(TreeItem *&root, int deleted);


int main(int argc, char const *argv[]) {
    std::ifstream fin("input.txt");
    std::ofstream fout("output.txt");

    int deleted;
    fin >> deleted;

    int number;
    fin >> number;
    TreeItem *root = new TreeItem(number);

    while (!fin.eof()) {
        fin >> number;
        add_element(root, number);
    }
    fin.close();

    delete_element(root, deleted);

    left_round(root, fout);
    fout.close();

    return 0;
}

void delete_TreeItem(TreeItem *&root, TreeItem *deleted, TreeItem *deleted_father) {
    bool is_right_son = false;
    if (deleted_father) {
        is_right_son = (deleted == deleted_father->right);
    }

    if (deleted->right == nullptr && deleted->left == nullptr) {
        if (deleted == root) {
            root = nullptr;
        } else if (is_right_son) {
            deleted_father->right = nullptr;
        } else {
            deleted_father->left = nullptr;
        }
        delete deleted;
    } else if (deleted->right == nullptr || deleted->left == nullptr) {

        if (deleted == root) {
            root = (deleted->right == nullptr) ? deleted->left : deleted->right;
        } else if (deleted->right != nullptr) {
            if (is_right_son) {
                deleted_father->right = deleted->right;
            } else {
                deleted_father->left = deleted->right;
            }
        } else {
            if (is_right_son) {
                deleted_father->right = deleted->left;
            } else {
                deleted_father->left = deleted->left;
            }
        }
        delete deleted;
    } else {
        TreeItem *min_in_right_father = deleted;
        TreeItem *min_in_right = deleted->right;
        while (min_in_right->left != nullptr) {
            min_in_right_father = min_in_right;
            min_in_right = min_in_right->left;
        }

        deleted->value = min_in_right->value;
        delete_TreeItem(root, min_in_right, min_in_right_father);
    }

}

void delete_element(TreeItem *&root, int deleted) {
    TreeItem *del = root;
    TreeItem *father = nullptr;

    while (del != nullptr && del->value != deleted) {
        father = del;
        del = (deleted < del->value) ? del->left : del->right;
    }
    if (del != nullptr) {
        delete_TreeItem(root, del, father);
    }
}


void left_round(TreeItem *&root, std::ofstream &fout) {
    TreeItem *now = root;
    if (now != nullptr) {
        fout << now->value << std::endl;
        left_round(now->left, fout);
        left_round(now->right, fout);
    }
}


void add_element(TreeItem *root, int number) {
    TreeItem *now = root;
    TreeItem *added_item = new TreeItem(number);

    while (now != nullptr) {
        if (number == now->value) return;
        else if (number < now->value) {
            if (now->left == nullptr) {
                now->left = added_item;
                break;
            } else {
                now = now->left;
            }
        } else if (now->right == nullptr) {
            now->right = added_item;
            break;
        } else {
            now = now->right;
        }
    }

}