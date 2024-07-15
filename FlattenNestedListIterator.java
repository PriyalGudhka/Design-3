// Time Complexity : next() : O(1), hasNext() : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : Yes. Forgot to add the iterator in last else condition

// Approach: Used Stack with Iterator<NestedInteger>. Used NestedInteger nextEl as a variable. In hasNext(), started by checking is stack is not empty, if not the started by checking if we have hasNext(), if not then pop, then assign nextEl and check if it isInteger(), if yes then return true, else it indicates we have list of integers then push the list of integers with native iterator. In the next(), simply return nextEl.getInteger()

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {

    Stack<Iterator<NestedInteger>> s;
    NestedInteger nextEl;

    public NestedIterator(List<NestedInteger> nestedList) {
        s = new Stack<>();
        s.push(nestedList.iterator());

    }

    @Override
    public Integer next() {
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() {

        while(!s.isEmpty()){

            if(!s.peek().hasNext()){
                s.pop();
            }
            else if((nextEl = s.peek().next()).isInteger()){
                return true;
            }
            else {
                s.push(nextEl.getList().iterator());
            }
        }

        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */