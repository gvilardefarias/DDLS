#include <bits/stdc++.h>

using namespace std;

extern "C" unsigned long ELFHash ( const unsigned char *s )
{
    unsigned long   h = 0, high;
    for ( int i = 0; i < 4 ; i++ )
    {   
        cout << *s;
        h = ( h << 4 ) + *s++;
        if ( high = h & 0xF0000000 )
            h ^= high >> 24;
        h &= ~high;
    }

    cout << "\n";

    return h;
}