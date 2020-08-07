/**
 * Other's solution with Stream()
 *
 * Time complexity: O(n log n) due to the Stream.sorted(). Note that it will behave relatively slower than for-loop based solutions due to the overhead of boxing and unboxing
 *                  when accessing collection elements.
 * Space complexity: O(n)
 */
class Solution {
    public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        return Arrays.stream(restaurants)
            .filter(s -> s[2] >= veganFriendly && s[3] <= maxPrice && s[4] <= maxDistance)
            .sorted((a,b) -> a[1] == b[1] ? b[0] - a[0] : b[1] - a[1])
            .map(i -> i[0])
            .collect(Collectors.toList());
    }
}
