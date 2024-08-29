#!/usr/bin/env python3



def sum(A):
    total = 0
    for num in A:
        total = total + num

    return total

def wsum(G, W):
    total = 0
    for grade, weight in zip(G, W):
        total = total + grade*weight

    return total

def nwsum(G, W):
    total = 0
    normal = 0
    for grade, weight in zip(G, W):
        total = total + grade*weight
        normal = normal + weight

    total = total / normal
    return total

def grademap(num):
    if num >= 90:
        return 'A'
    elif num >= 78:
        return 'B'
    elif num >= 66:
        return 'C'
    elif num >= 50:
        return 'D'
    else:
        return 'F'

def grade(G, W):
    num = nwsum(G, W)
    return grademap(num)