//Bodie Malik
//section 502
//Ziaullah Khan
//9-30-15

/**
 * To be honest, the size, front, and back properties are not needed; you can get those values from methods rather than saving them into ints.
 * I guess it does help the queue run faster though.
 */
public class ArrayQueue {
	private String[] queueArray;
	private int size;
	private int front;
	private int back;
	
	public ArrayQueue(){
		queueArray = new String[100];
		size = 0;
		front = 0;
		back = 0;
	}
	
	public ArrayQueue(int startSize){
		queueArray = new String[startSize];
		size = 0;
		front = 0;
		back = 0;
	}
	
	/**
	 * @function returns the number of elements in the queue
	 * @return size
	 */
	public int getSize(){
		return size;
	}
	
	/**
	 * @function adds a string to the end of the queue
	 * @param toEnqueue: the input to be inserted
	 */
	public void enqueue(String toEnqueue){
		//if queue is full, do nothing.
		if(isFull() ){
			System.out.println("ERROR: Tried to enqueue in a full queue.");
			return;
		}
		
		//Enqueues. Then moves the back farther back.
		//the back is the slot AFTER the last used slot in the array.
		
		queueArray[back] = toEnqueue;
		
		back++;
		if( back >= queueArray.length)
			back = 0;
		
		size++;
		
	}
	
	/**
	 * @function removes the string from the front of the queue
	 * @return the string from the front of the queue
	 */
	public String dequeue(){
		//Get the string at the front.
		String toDequeue = queueArray[front];
		
		//move front up, and decrease size. If more is enqueued, the past array slot will be overridden with new data.
		front++;
		if(front >= queueArray.length)
			front = 0;
		size--;
		
		return toDequeue;
	}
	
	/**
	 * 
	 * @return returns true if the queue is empty, false otherwise
	 */
	public boolean isEmpty(){//Code Altered by Bodie
		if(size == 0)
			return true;
		else
			return false;
	}

	/**
	 * 
	 * @return returns true if the queue is full, false otherwise
	 */
	public boolean isFull(){
		
		if(size == queueArray.length)
			return true;
		else
			return false;
	}
	
}
