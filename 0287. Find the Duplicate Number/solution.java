/**
 * Official solution of Sorting
 *
 * Time complexity : O(nlgn)
 *                   The sort invocation costs O(nlgn) time in Python and Java, so it dominates the subsequent linear scan.
 * Space complexity : O(1) (or O(n))
 *                    Here, we sort nums in place, so the memory footprint is constant. If we cannot modify the input array, then we must allocate linear space for a copy
 *                    of nums and sort that instead.
 */
class Solution {
    public int findDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i-1]) {
                return nums[i];
            }
        }

        return -1;
    }
}


/**
 * Official solution of HashSet
 *
 * Time complexity : O(n)
 * Space complexity : O(n)
 */
class Solution {
    public int findDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<Integer>();
        for (int num : nums) {
            if (seen.contains(num)) {
                return num;
            }
            seen.add(num);
        }

        return -1;
    }
}


/**
 * Official solution of Floyd's Tortoise and Hare (Cycle Detection)
 *
 * Intuition:
 * The idea is to reduce the problem to Linked List Cycle II:
 *   Given a linked list, return the node where the cycle begins.
 * 
 * First of all, where does the cycle come from? Let's use the function f(x) = nums[x] to construct the sequence:
 * x, nums[x], nums[nums[x]], nums[nums[nums[x]]], ....
 * 
 * Each new element in the sequence is an element in nums at the index of the previous element.
 * 
 * If one starts from x = nums[0], such a sequence will produce a linked list with a cycle.
 *   The cycle appears because nums contains duplicates. The duplicate node is a cycle entrance.
 * 
 * 
 * The example above is simple because the loop is small. Here is a more interesting example
 * 
 * Now the problem is to find the entrance of the cycle.
 * 
 * Algorithm:
 * Floyd's algorithm consists of two phases and uses two pointers, usually called tortoise and hare.
 * 
 * In phase 1, hare = nums[nums[hare]] is twice as fast as tortoise = nums[tortoise]. Since the hare goes fast,
 * it would be the first one who enters the cycle and starts to run around the cycle. At some point, the tortoise
 * enters the cycle as well, and since it's moving slower the hare catches the tortoise up at some intersection
 * point. Now phase 1 is over, and the tortoise has lost.
 *   Note that the intersection point is not the cycle entrance in the general case.
 * 
 * 
 * To compute the intersection point, let's note that the hare has traversed twice as many nodes as the tortoise,
 * i.e. 2d(tortoise)=d(hare), that means
 * 2(F + a) = F + nC + a, where n is some integer.
 *   Hence the coordinate of the intersection point is F + a = nCF+a=nC.
 * 
 * In phase 2, we give the tortoise a second chance by slowing down the hare, so that it now moves with the speed
 * of tortoise: tortoise = nums[tortoise], hare = nums[hare]. The tortoise is back at the starting position, and
 * the hare starts from the intersection point.
 * 
 * 
 * Let's show that this time they meet at the cycle entrance after F steps.
 *   - The tortoise started from zero, so its position after F steps is F.
 *   - The hare started at the intersection point F + a = nC, so its position after F steps is nC + F, that is the
 *     same point as F.
 *   - So the tortoise and the (slowed down) hare will meet at the entrance of the cycle.
 *
 * Time complexity : O(n)
 *                   For detailed analysis, refer to Linked List Cycle II.
 * Space complexity : O(1)
 *                    For detailed analysis, refer to Linked List Cycle II.
 */
class Solution {
  public int findDuplicate(int[] nums) {
    // Find the intersection point of the two runners.
    int tortoise = nums[0];
    int hare = nums[0];
    do {
      tortoise = nums[tortoise];
      hare = nums[nums[hare]];
    } while (tortoise != hare);

    // Find the "entrance" to the cycle.
    tortoise = nums[0];
    while (tortoise != hare) {
      tortoise = nums[tortoise];
      hare = nums[hare];
    }

    return hare;
  }
}


/**
 * Other's solution of Floyd's Tortoise and Hare (Cycle Detection)
 *
 * Time complexity : O(n)
 * Space complexity : O(1)
 */
 class Solution {
    public int findDuplicate(int[] nums) {
        if (nums.length > 1) {
            int slow = nums[0];
            int fast = nums[nums[0]];
            while (slow != fast) {
                slow = nums[slow];
                fast = nums[nums[fast]];
            }
            
            fast = 0;
            while (fast != slow) {
                fast = nums[fast];
                slow = nums[slow];
            }
            return slow;
        }
        return -1;
    }
}
