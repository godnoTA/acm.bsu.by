#include <bits/stdc++.h>

using namespace std;

unsigned long long modularPow(unsigned long long base, unsigned long long exponent, unsigned long long mod) {
    unsigned long long rem = 1;
    while (exponent > 0) {
        if (exponent % 2 == 1) {
            rem = (rem * base) % mod;
        }
        base = (base * base) % mod;
        exponent >>= 1;
    }
    return rem;
}


int main() {
    const int rem = 1000000007;
    int n, k;
    cin >> n >> k;
    if (k > n) {
        cout << 0;
        return 0;
    }

    unsigned long long numerator = 1;
    for (int i = n; i > n - k; --i)
        numerator = (numerator * i) % rem;
    unsigned long long denominator = 1;
    for (int i = 1; i <= k; ++i) {
        denominator = (denominator * i) % rem;
    }

    // малая теорема Ферма
    // (1/denominator) = denominator ^ (rem - 2) % rem
    cout << (numerator * modularPow(denominator, rem - 2, rem)) % rem;
    return 0;
}