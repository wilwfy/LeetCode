/*
 * My solution with HashSet
 *
 * Performance: 2 ms
 */
class Solution {
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        set.add(n);
        int tmp = n;
        while (true) {
            tmp = happySum(tmp);
            //System.out.println(tmp);
            if (tmp == 1) return true;
            if (set.contains(tmp)) return false;
            set.add(tmp);
        }
    }
    
    private int happySum(int n) {
        String numString = String.valueOf(n);
        int sum = 0;
        for (int i = 0; i < numString.length(); i++) {
            sum += Math.pow(Character.getNumericValue(numString.charAt(i)), 2);
        }
        return sum;
    }
}


/*
 * Other's solution with HashSet
 *
 * Performance: 1 ms
 */
class Solution {
    public boolean isHappy(int n) {
        n = (int) Math.abs(n);
        Set<Integer> exist = new HashSet<>();
        while (n != 1) {
            if (!exist.add(n)) {
                return false;
            }
            
            int tmp = 0;
            while (n > 0) {
                tmp += Math.pow((n % 10), 2);
                n /= 10;
            }
            n = tmp;
        }
        return true;
    }
}


/*
 * Other's solution with HashSet
 *
 * Performance: 0 ms
 */
class Solution {
    public boolean isHappy(int n) {
        int slow = n;
        int fast = n;
        
        do {
            slow = HappySq(slow);
            fast = HappySq(HappySq(fast));
        } while (slow!=fast);
        return slow == 1? true: false;
    }
    
    private int HappySq(int n) {
        int num = 0;
        while(n != 0) {
            num = num + (n%10) * (n%10);
            n= n/10;
        }
        return num;
    }
}
