/** Brendan Raimann
*	11/19/15
*	Interface for Stack
*/

public interface Stack<E>
{
	/**	
	*	Adds the given item to the top of the stack
	*	@param item The item to be added to the end of the stack
	*/
	void push(E item);
	
	/**
	*	Removes the top item from the stack and returns it
	*	@return Returns the top item from the stack
	*/
	E pop();
	
	/**	
	*	Returns the top item from the stack
	*	@return Returns the front item of the stack
	*/ 
	E peek();
	
	/**	
	*	Returns whether the stack is empty or not
	*	@return Returns whether or not the stack is empty
	*/
	boolean isEmpty();
}