
https://leetcode.com/problems/remove-k-digits/discuss/88660/A-greedy-method-using-stack-O(n)-time-and-O(n)-space

Just a few of my intuition to make myself more comfortable about why using greedy. **In fact, any time you see "smallest", "as... as possible",
you should try greedy first and that might be a really nice solution.**
By meaning greedy, we mean to start from most significant digit (left most digit). If you can replace that digit (k>0) with a smaller number
(by removing that digit), you remove it. The following digit takes the place and the number becomes smaller. And as you are operating from
most significant digit, the resulting number is guaranteed to be the smallest.

When we try to minimize or maximize something, it usually is a Dynamic Programming question. Since Greedy Algorithm (To my knowledge) is a "lucky"
version of Dynamic Programming, we can try greedy first, and if it doesn't work, switch back to DP.

Greedy and DP are related in that they solve these optimisation problems.
