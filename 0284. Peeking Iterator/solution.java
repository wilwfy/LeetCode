/**
 * Other's solution
 *
 * using null to check if we have reached the end of iterator is not 100% right.
 * In theory, null could be a valid element.
 * An slightly alternative approach to use boolean indicating end of iterator
 */

// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html

import java.util.NoSuchElementException;
class PeekingIterator implements Iterator<Integer> {
    private Integer next; // a cache for the next element
    private Iterator<Integer> iter;
    boolean noSuchElement;
    
	public PeekingIterator(Iterator<Integer> iterator) {
	    // initialize any member here.
        iter = iterator;
        advanceIter();
	}
	
    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
        // you should confirm with interviewer what to return/throw
        // if there are no more values
        return next;
	}
	
	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
        if (noSuchElement)
            throw new NoSuchElementException();
	    Integer res = next;
        advanceIter();
        return res;
	}
	
	@Override
	public boolean hasNext() {
	    return !noSuchElement;
	}
    
    private void advanceIter() {
        if (iter.hasNext())
            next = iter.next();
        else
            noSuchElement = true;
    }
}
