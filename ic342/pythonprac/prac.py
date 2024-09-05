#!/usr/bin/env python3

def writish(a):
    b = []
    for val in a:
        if (isinstance(next(a), int)):
            for i in range(next(a)):
                b.append(val)
        else:
            b.append(val)
    return b