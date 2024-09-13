#!/usr/bin/env python3


def inchtofoot(x):
    y = int(x)
    z = y % 12
    a = y // 12

    return f"{x}\" = {y % 12}\' {y // 12}\""

def vowelCount(x):
    L = list(x)
    
    count = 0
    for c in x:
        if (c == "a" or c == "e" or c == "i" or c == "o" or c == "u"):
            count += 1
    return count

def vowelSwap(x):
    D = { "a":"u", "u":"i", "i":"a", "o":"e", "e":"o" }
    List = list(x)
    for c in List:
        if (c == "a" or c == "e" or c == "i" or c == "o" or c == "u"):
            for key in D.keys:
                if (key == c):
                    c = D.values
    return "".join(List)
    