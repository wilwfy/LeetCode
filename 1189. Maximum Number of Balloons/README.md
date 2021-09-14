**Easy**

Given a string text, you want to use the characters of text to form as many instances of the word **"balloon"** as possible.

You can use each character in text **at most once**. Return the maximum number of instances that can be formed.

 

**Example 1:**

![1189_ex1](https://github.com/wilwfy/LeetCode/blob/master/1189.%20Maximum%20Number%20of%20Balloons/1189_ex1.jfif)
```
Input: text = "nlaebolko"
Output: 1
```
**Example 2:**

![1189_ex2](https://github.com/wilwfy/LeetCode/blob/master/1189.%20Maximum%20Number%20of%20Balloons/1189_ex2.jfif)
```
Input: text = "loonbalxballpoon"
Output: 2
```
**Example 3:**
```
Input: text = "leetcode"
Output: 0
```

****Constraints:**

- `1 <= text.length <= 104`
- `text` consists of lower case English letters only.

**Hint 1**  
Count the frequency of letters in the given string.

**Hint 2**  
Find the letter than can make the minimum number of instances of the word "balloon".
