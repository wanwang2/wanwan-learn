#!/usr/bin/python3
# -*- coding: UTF-8 -*-

for char in 'wo le ge chacha...':  # 第一个实例
    print('current:', char)

fruits = ['banana', 'apple', 'mango']
for fruit in fruits:  # 第二个实例
    print('current char:', fruit)

print("Good bye!")

sumz = 0
for i in range(1, 11):
    sumz += 1 / i
    print("{:2d} {:6.6f}".format(i , sumz))
