/**
 * Official solution based on Sort()
 *
 * Time complexity : O(nlogn). Collections.sort() uses binary sort so it has a O(nlogn) complexity.
 * Space complexity : O(k). The in-place sorting does not consume any extra space. However, generating a k length sublist will take some space.
 */
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        Integer[] arrInt = Arrays.stream(arr)
                                 .boxed()
                                 .toArray(Integer[]::new);
        Arrays.sort(arrInt, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                int aVal = a.intValue(), bVal = b.intValue();
                return aVal == bVal ? aVal - bVal : Math.abs(aVal - x) - Math.abs(bVal - x);
            }
        });
        //System.out.println(Arrays.asList(arrInt));
        List<Integer> res = Arrays.asList(arrInt).subList(0, k);
        Collections.sort(res);
        return res;
    }
}
