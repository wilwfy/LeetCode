/**
 * My solution of Stack
 */
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        List<Integer> remained = new ArrayList<>();
        List<Integer> toCollideStack = new ArrayList<>();
        for (int asteroid: asteroids) {
            if (asteroid > 0)
                toCollideStack.add(asteroid);
            else {
                while ( !toCollideStack.isEmpty() && (toCollideStack.get(toCollideStack.size() - 1) < Math.abs(asteroid)) ) {
                    toCollideStack.remove(toCollideStack.size() - 1);
                }
                if (toCollideStack.isEmpty())
                    remained.add(asteroid);
                else {
                    if (toCollideStack.get(toCollideStack.size() - 1) == Math.abs(asteroid))
                        toCollideStack.remove(toCollideStack.size() - 1);
                }
            }
        }
        if (!toCollideStack.isEmpty())
            remained.addAll(toCollideStack);
        return remained.stream().mapToInt(i -> i).toArray();
    }
}


/**
 * Other's solution of Stack
 *
 * Algorithm
 * -- at the end, all the negative star has to be on the left, and all the positive star has to be on the right.
 * -- from the left, a negative star will pass through if no positive star on the left;
 * -- keep track of all the positive stars moving to the right, the right most one will be the 1st confront the
 *    challenge of any future negative star.
 * -- if it survives, keep going, otherwise, any past positive star will be exposed to the challenge, by being
 *    popped out of the stack.
 */
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        LinkedList<Integer> s = new LinkedList<>(); // use LinkedList to simulate stack so that we don't need to reverse at end.
        for (int asteroid : asteroids) {
            if (asteroid > 0)
                s.add(asteroid);
            else {
                while (!s.isEmpty() && s.getLast() > 0 && s.getLast() < -asteroid)
                    s.pollLast();
                if (!s.isEmpty() && s.getLast() == -asteroid)
                    s.pollLast();
                else if (s.isEmpty() || s.getLast() < 0)
                    s.add(asteroid);
            }
        }
        return s.stream().mapToInt(i->i).toArray();
    }
}


/**
 * Another's solution of Stack
 */
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        LinkedList<Integer> s = new LinkedList<>(); // use LinkedList to simulate stack so that we don't need to reverse at end.
        for (int i : asteroids) {
            while (!s.isEmpty() && s.getLast() > 0 && s.getLast() < -i)
                s.pollLast();
            if (s.isEmpty() || i > 0 || s.getLast() < 0)
                s.add(i);
            else if (i < 0 && s.getLast() == -i)
                s.pollLast();
        }
        return s.stream().mapToInt(i->i).toArray();
    }
}


/**
 * Official solution of Stack
 *
 * Intuition
 * 
 * A row of asteroids is stable if no further collisions will occur. After adding a new asteroid to the right,
 * some more collisions may happen before it becomes stable again, and all of those collisions (if they happen)
 * must occur right to left. This is the perfect situation for using a stack.
 * 
 * Algorithm
 * 
 * Say we have our answer as a stack with rightmost asteroid top, and a new asteroid comes in. If new is moving
 * right (new > 0), or if top is moving left (top < 0), no collision occurs.
 * 
 * Otherwise, if abs(new) < abs(top), then the new asteroid will blow up; if abs(new) == abs(top) then both asteroids
 * will blow up; and if abs(new) > abs(top), then the top asteroid will blow up (and possibly more asteroids will, so
 * we should continue checking.)
 *
 * Time Complexity: O(N), where N is the number of asteroids. Our stack pushes and pops each asteroid at most once.
 * Space Complexity: O(N), the size of ans.
 */
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack();
        for (int ast: asteroids) {
            collision: {
                while (!stack.isEmpty() && ast < 0 && 0 < stack.peek()) {
                    if (stack.peek() < -ast) {
                        stack.pop();
                        continue;
                    } else if (stack.peek() == -ast) {
                        stack.pop();
                    }
                    break collision;
                }
                stack.push(ast);
            }
        }

        int[] ans = new int[stack.size()];
        for (int t = ans.length - 1; t >= 0; --t) {
            ans[t] = stack.pop();
        }
        return ans;
    }
}
