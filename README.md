# Linear Algebra Engine

Performs a series of operations on n-dimensional square matrices, including, addition, multiplication, and scaling. The
program uses a Swing GUI and enables text editing within the constituent cells. Matrices can also be randomly generated
and incremented/decremented in size. The program also computes identity matrices and determinants, the latter of which
can be tedious to calculate by hand.

## Some Notes On the Implementation

Addition and multiplication are both implemented in O(n^2) time. This is fairly standard for matrix operations. The
determinant method however has a very slow complexity of O(n!). This is because I chose the naive technique of using
Laplace Expansion - otherwise known as cofactor expansion. This implementation is easier and works well for small
matrices - i.e 2x2 & 3x3 matrices. However, it scales very poorly for any n > 9 matrices. A computationally superior
algorithm would be Bareiss' algorithm which implements Gaussian Elimination and guarantees O(n^3) performance for
floating rate numbers. This has been earmarked as a potential optimisation for the project.

## Features To Be Completed

1. Inverse matrices.
2. Transpose matrices.

## Installation Instructions

1. git clone [https://github.com/jackbradyeng/binary_search_trees.git]
2. cd linear_algebra_engine
3. mvn install