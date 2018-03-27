#-------------------------------------------------------------------------------
# Name:        test2
# Purpose:		test
#
# Author:		Maria Cerna
#
# Created:     20-03-2018
#
#
#   Create a program that reads a list of arithmetic operations from a text file (res/arithmetic.txt) and prints the result of each operation to screen.
#
#-------------------------------------------------------------------------------


def main():
    #DEFINE FILE WHICH IS TO BE USED - for this implementation the file is required to be in the same folder as the main.py script
    file = 'arithmetic.txt'

    #READ ALL LINES FROM FILE AND CALCULATE REUSLT
    with open(file) as fp:
        for cnt, line in enumerate(fp):

            #python can calculate basic arithmetic operations from string using eval() function
            print line.strip("\n") + " = " + str(eval(line))             #line.strip("\n") is needed for a desired output - it strips away linebreak



if __name__ == '__main__':
    main()
