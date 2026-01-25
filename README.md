# Linear Algebra Engine

## Summary

Performs a series of operations on n-dimensional square matrices, including, addition, multiplication, and scaling. The
program uses a Swing GUI and enables text editing within the constituent cells. Matrices can also be randomly generated
and incremented/decremented in size. The program also computes identity matrices and determinants, the latter of which
can be tedious to calculate by hand.

## Some Notes On the Implementation

Originally, I wrote this program in Java. But after realising how long and tedious this was, I decided to switch
to Kotlin. Kotlin provides incredible support for functional programming - which turns out to be a very good fit for
modelling linear algebra operations - as well as null-safety and dramatically reduced boilerplate. All future
improvements to this program will therefore be in Kotlin.

Regarding the actual engine itself, addition and multiplication are both implemented in O(n^2) time. This is fairly
standard for matrix operations. The determinant method was originally implemented in O(n!) using the Laplace Method. But
this has since been re-implemented via Gaussian Elimination which runs in O(n^3), a major improvement.

## Features To Be Completed

1. Inverse matrices.
2. Transpose matrices.
3. Transformation matrices in 3D (using JavaFX).

## Installation Instructions

1. git clone [https://github.com/jackbradyeng/binary_search_trees.git]
2. cd linear_algebra_engine
3. mvn install