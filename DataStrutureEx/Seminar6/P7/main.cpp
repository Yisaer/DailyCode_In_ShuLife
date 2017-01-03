#include <iostream>
using namespace std;
#include <cmath>


int main() {
    int i, j;
    cin >> i >> j;
    while (i != j) {
        if (i > j) {
            i = i/2;
        } else {
            j = j/2;
        }
    }
    cout << i << endl;
    return 0;
}