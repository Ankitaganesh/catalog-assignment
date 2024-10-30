Polynomial Secret Finder

This Java application solves for the constant term (secret) of an unknown polynomial given a set of points in a specific encoded format.
Problem Description
The application finds the constant term of an unknown polynomial where:

The polynomial degree m is determined by k = m + 1

Points are provided in a special encoded format with different number bases

A minimum of k points are required to solve for the coefficients

Polynomial Form
f(x) = a_m x^m + a_{m-1} x^{m-1} + ... + a_1 x + c
where:

f(x) is the polynomial function
m is the degree of the polynomial
a_m, a_{m-1}, ..., a_1, c are coefficients (positive integers)
a_m ≠ 0
c is the secret we need to find

Setup Instructions

Ensure you have Java Development Kit (JDK) installed (version 8 or higher)
Clone the repository to your local machine
Navigate to the project directory

How to Run

1) Download the zip file, extract and open in any IDE
2) cd projectname
3) Compile the Java files: javac Main.java
4) Run the program: java Main
