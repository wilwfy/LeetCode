/**
 * Official solution of Recursive Parsing
 *
 * Intuition and Algorithm
 * 
 * This question is relatively straightforward in terms of the idea of the solution, but presents substantial
 * difficulties in the implementation.
 * 
 * Expressions may involve the evaluation of other expressions, which motivates a recursive approach.
 * 
 * One difficulty is managing the correct scope of the variables. We can use a stack of hashmaps. As we enter
 * an inner scope defined by parentheses, we need to add that scope to our stack, and when we exit, we need to
 * pop that scope off.
 * 
 * Our main evaluate function will go through each case of what form the expression could take.
 * 
 * If the expression starts with a digit or '-', it's an integer: return it.
 * 
 * If the expression starts with a letter, it's a variable. Recall it by checking the current scope in reverse order.
 * 
 * Otherwise, group the tokens (variables or expressions) within this expression by counting the "balance" bal of the
 * occurrences of '(' minus the number of occurrences of ')'. When the balance is zero, we have ended a token. For
 * example, (add 1 (add 2 3)) should have tokens '1' and '(add 2 3)'.
 * 
 * For add and mult expressions, evaluate each token and return the addition or multiplication of them.
 * 
 * For let expressions, evaluate each expression sequentially and assign it to the variable in the current scope, then
 * return the evaluation of the final expression.
 *
 * Time Complexity: O(N^2), where N is the length of expression. Each expression is evaluated once, but within that evaluation
 *                  we may search the entire scope.
 * Space Complexity: O(N^2). We may pass O(N) new strings to our evaluate function when making intermediate evaluations, each of
 *                   length O(N). With effort, we could reduce the total space complexity to O(N) with interning or passing pointers.
 */
class Solution {
    ArrayList<Map<String, Integer>> scope;
    public Solution() {
        scope = new ArrayList();
        scope.add(new HashMap());
    }

    public int evaluate(String expression) {
        scope.add(new HashMap());
        int ans = evaluate_inner(expression);
        scope.remove(scope.size() - 1);
        return ans;
    }

    public int evaluate_inner(String expression) {
        if (expression.charAt(0) != '(') {
            if (Character.isDigit(expression.charAt(0)) || expression.charAt(0) == '-')
                return Integer.parseInt(expression);
            for (int i = scope.size() - 1; i >= 0; --i) {
                if (scope.get(i).containsKey(expression))
                    return scope.get(i).get(expression);
            }
        }

        List<String> tokens = parse(expression.substring(
                expression.charAt(1) == 'm' ? 6 : 5, expression.length() - 1));
        if (expression.startsWith("add", 1)) {
            return evaluate(tokens.get(0)) + evaluate(tokens.get(1));
        } else if (expression.startsWith("mult", 1)) {
            return evaluate(tokens.get(0)) * evaluate(tokens.get(1));
        } else {
            for (int j = 1; j < tokens.size(); j += 2) {
                scope.get(scope.size() - 1).put(tokens.get(j-1), evaluate(tokens.get(j)));
            }
            return evaluate(tokens.get(tokens.size() - 1));
        }
    }

    public List<String> parse(String expression) {
        List<String> ans = new ArrayList();
        int bal = 0;
        StringBuilder buf = new StringBuilder();
        for (String token: expression.split(" ")) {
            for (char c: token.toCharArray()) {
                if (c == '(') bal++;
                if (c == ')') bal--;
            }
            if (buf.length() > 0) buf.append(" ");
            buf.append(token);
            if (bal == 0) {
                ans.add(new String(buf));
                buf = new StringBuilder();
            }
        }
        if (buf.length() > 0)
            ans.add(new String(buf));

        return ans;
    }
}


/**
 * Other's clean solution of Recursive Parsing
 *
 * Explanation:
 * 
 * To get the idea of this solution - first try to understand the function "parse(String str)"
 * This is probably better named as "split", which tokenizes an expression.
 * Recall the counter trick from typical leetcode parentheses question, it is not hard to figure out the idea there.
 * 
 * Now let's see how this "split" (or parse) function is applied - we need to trim and this line does the job:
 * 
 * List<String> tokens = parse(exp.substring(exp.charAt(1) == 'm' ? 6 : 5, exp.length() - 1));
 * 
 * This is to skip the most outter parentheses and the protected keyword like add,let,multi. We can do it alternatively:
 * 
 * List<String> tokens = split(exp.substring(exp.indexOf(" ")+1, exp.length()-1));
 * 
 * Now the most important idea of eval function is to use a scoping map with recursion.
 * The first part is simply handle the case of simple integers.
 * Then we inheritate the map from parent and do the tokenization.
 * After tokenization, it is just three scenarios to handle:
 * add or multi operation - just do the calculation on tokens, with recursive eval.
 * let operation - need to put new pair into map until the last exp
 */
class Solution {
    public int evaluate(String expression) {
        return eval(expression, new HashMap<>());
    }
    
    private int eval(String exp, Map<String, Integer> parent) {
        char c = exp.charAt(0);
        if (c != '(') {
            // just a number or a symbol
            if (Character.isDigit(c) || c == '-')
                return Integer.parseInt(exp);
            return parent.get(exp);
        }
        
        // create a new scope, add add all the previous values to it
        Map<String, Integer> map = new HashMap<>();
        map.putAll(parent);
        // skip the outermost two parentheses and the leading keyword like 'add', 'let' or 'mult'
        List<String> tokens = parse(exp.substring(exp.charAt(1) == 'm' ? 6 : 5, exp.length() - 1));
        
        if ( exp.startsWith("(a") ) { // add operation
            return eval(tokens.get(0), map) + eval(tokens.get(1), map);
        } else if ( exp.startsWith("(m") ) { // mult operation
            return eval(tokens.get(0), map) * eval(tokens.get(1), map);
        } else { // let operation
            for (int i = 0; i < tokens.size() - 2; i += 2)
                map.put(tokens.get(i), eval(tokens.get(i + 1), map));
            return eval(tokens.get(tokens.size() - 1), map);
        }
    }
    
    private List<String> parse(String str) {
        // seperate the values between two parentheses
        List<String> res = new ArrayList<>();
        int par = 0;
        StringBuilder sb = new StringBuilder();
        for (char c: str.toCharArray()) {
            if (c == '(') par++;
            if (c == ')') par--;
            if (par == 0 && c == ' ') {
                res.add(new String(sb));
                sb = new StringBuilder();
            } else {
                sb.append(c);
            }
        }
        if (sb.length() > 0) res.add(new String(sb));
        return res;
    }
}
