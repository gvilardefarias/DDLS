#include <bits/stdc++.h>

using namespace std;

extern "C" unsigned int ELFHash(string str) {
    unsigned int hash = 0;
    unsigned int x = 0;
    unsigned int i = 0;
    unsigned int len = str.length();

    for (i = 0; i < len; i++)
    {
        hash = (hash << 4) + (str[i]);
        if ((x = hash & 0xF0000000) != 0)
        {
            hash ^= (x >> 24);
        }
        hash &= ~x;
    }

    return hash;
}
