import re

def main():
    s = userInput()
    result = isPalindrome(s)
    conclusion(result)

def conclusion(result):
    if result==True:
        print("Yes, the string is a palindrome.")
    if result==False:
        print("No, the string is not a palindrome.")

def userInput():
    s = str(input("Enter a string, and after removing caps, special characters and spaces, \
        this program will determine if it is a palindrome, or not:\n"))
    s = s.lower()
    s = re.sub("[^a-zA-Z0-9]","", s)
    print(s)
    return(s)

def isPalindrome(s):
    i=0
    result=True
    if len(s)==0:
        result=False
        return(result)
    while i<=len(s)/2:
        if s[i]!=s[-1-i]:
            result=False
            return(result)
        i+=1
    return(result)

if __name__ == "__main__":
    main()