function palindromeChecker(str) {
    str = str.toLowerCase().replace(/[^a-z0-9]/g, '');
    var maxIdx = str.length - 1;
    for (var i = 0; i < maxIdx / 2; i++) {
        if (str[i] !== str[maxIdx - i]) {
            return console.log('"' + str + '" is not a palindrome =(');
        }
    }
    return console.log('"' + str + '" is a palindrome =)');
}

// three test cases
palindromeChecker("hannah");
palindromeChecker("Eva, Can I Stab Bats In A Cave?");
palindromeChecker("Nope not a palindrome");