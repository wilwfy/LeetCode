class Solution {
    public int[] sortByBits(int[] arr) {
        Integer[] a = new Integer[arr.length];
        for (int i = 0; i < a.length; ++i)
            a[i] = arr[i];
        Arrays.sort(a, Comparator.comparing(i -> Integer.bitCount(i) * 10000 + i));
        for (int i = 0; i < a.length; ++i)
            arr[i] = a[i];
        return arr;
    }
}


class Solution {
    public int[] sortByBits(int[] arr) {
        // Multiply bitCount(i) with 10000 in comparison because 0 <= arr[i] <= 10^4
        // to let value of bitCount(i) has priority
        return Arrays.stream(arr).boxed()
            .sorted(Comparator.comparingInt(i -> Integer.bitCount(i)*(int)Math.pow(10,4) + i))
            .mapToInt(i -> i).toArray();
    }
}


class Solution {
    public int[] sortByBits(int[] arr) {
        return Arrays.stream(arr).boxed().sorted(Comparator.comparingInt(i -> Integer.bitCount(i) * 10000 + i)).mapToInt(i -> i).toArray();
    }
}


class Solution {
    public int[] sortByBits(int[] arr) {
        Integer[] input = Arrays.stream(arr).boxed().toArray(Integer[]::new);
        Arrays.sort(input, (a, b) -> Integer.bitCount(a) == Integer.bitCount(b) ? a - b : Integer.bitCount(a) - Integer.bitCount(b));
        return Arrays.stream(input).mapToInt(Integer::intValue).toArray();
    }
}
