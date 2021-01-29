#include <bits/stdc++.h>

using namespace std;

int main() {
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);

    string str;
    cin >> str;

    if (str.length() == 1) {
        std::cout << 1 << "\n" << str;
        return 0;
    }
    if (str.length() == 2) {
        if (str[0] == str[1])
            std::cout << 2 << "\n" << str;
        else
            std::cout << 1 << "\n" << str[0];
        return 0;
    }

    vector<vector<int>> table(str.length());
    for (int i = 0; i <= str.length() - 1; ++i) {
        table[i].resize(str.length());
        table[i][i] = 1;
    }

    for (int j = 1; j <= str.length() - 1; ++j) {
        for (int i = j - 1; i >= 0; --i) {
            if (str[i] == str[j])
                table[i][j] = table[i + 1][j - 1] + 2;
            else
                table[i][j] = max(table[i + 1][j], table[i][j - 1]);
        }
    }

    ostringstream half;

    int i = 0;
    int j = str.length() - 1;

    while (j >= i) {
        int n = table[i][j];
        while (i < str.length() - 1 && table[i + 1][j] == n) {
            ++i;
        }
        while (j > 0 && table[i][j - 1] == n) {
            --j;
        }
        half << str[j];
        ++i;
        --j;
    }

    string s = half.str();
    int parity = (table[0][str.length() - 1] % 2);
    cout << s.length() * 2 - parity << "\n";
    ostream_iterator<char> oit(cout);
    copy(begin(s), end(s), oit);
    copy(rbegin(s) + parity, rend(s), oit);

    return 0;
}
