/**
 * Official solution of Rejection Sampling
 *
 * Detailed explanation can be seen in document at:
 *     https://github.com/wilwfy/LeetCode/blob/master/0470.%20Implement%20Rand10()%20Using%20Rand7()/470%20-%20Implement%20Rand10()%20Using%20Rand7()%20-Solutions.pdf
 */
class Solution extends SolBase {
    public int rand10() {
        int res, row, col;
        do {
            row = 7 * (rand7() - 1);
            col = rand7();
            res = row + col;
        } while (res > 40);
        return (res - 1) % 10 + 1; // transform 40 -> 10, 30 -> 10, 20 -> 10, 10 -> 10
    }
}


/**
 * Official solution of Utilizing out-of-range samples
 *
 * Detailed explanation can be seen in document at:
 *     https://github.com/wilwfy/LeetCode/blob/master/0470.%20Implement%20Rand10()%20Using%20Rand7()/470%20-%20Implement%20Rand10()%20Using%20Rand7()%20-Solutions.pdf
 */
class Solution extends SolBase {
    public int rand10() {
        int a, b, idx;
        while (true) {
            a = rand7();
            b = rand7();
            idx = b + (a - 1) * 7;
            if (idx <= 40)
                return 1 + (idx - 1) % 10;
            a = idx - 40;
            b = rand7();
            // get uniform dist from 1 - 63
            idx = b + (a - 1) * 7;
            if (idx <= 60)
                return 1 + (idx - 1) % 10;
            a = idx - 60;
            b = rand7();
            // get uniform dist from 1 - 21
            idx = b + (a - 1) * 7;
            if (idx <= 20)
                return 1 + (idx - 1) % 10;
        }
    }
}


/**
 * Other's solution with Random 49
 *
 * rand7() will get random 1 ~ 7
 * (rand7() - 1) * 7 + rand7() - 1 will get random 0 ~ 48
 * We discard 40 ~ 48, now we have rand40 equals to random 0 ~ 39.
 * We just need to return rand40 % 10 + 1 and we get random 1 ~ 10.
 * 
 * In 9/49 cases, we need to start over again.
 * In 40/49 cases, we call rand7() two times.
 * 
 * Overall, we need 49/40*2 = 2.45 calls of rand7() per rand10().
 */
class Solution extends SolBase {
    public int rand10() {
        int rand40 = 40;
        while (rand40 >= 40) {
            rand40 = (rand7() - 1) * 7 + rand7() - 1;
        }
        return rand40 % 10 + 1;
    }
}
