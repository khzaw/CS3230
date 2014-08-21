# T1-S1
import math


def repeated_half(n):
    step = 0
    while(n != 0):
        n = n/2
        step += 1
    return step


def repeated_half_formula(n):
    return int(math.floor(math.log(n, 2))) + 1


def repeated_double(n):
    start = 1
    step = 0
    while(start < n):
        start *= 2
        step += 1
    return step


def repeated_double_formula(n):
    return int(math.ceil(math.log(n, 2)))


numbers = range(1, 26) + [31, 32, 33, 63, 64, 65, 100, 127, 128, 129, 100, 1023, 1024, 1025, 10**6, 10**9]
print "Original"
print numbers
print "Repeated-halving h(n)"
print map(repeated_half, numbers)
print map(repeated_half_formula, numbers)
print "Repeated-doubling d(n)"
print map(repeated_double, numbers)
print map(repeated_double_formula, numbers)
