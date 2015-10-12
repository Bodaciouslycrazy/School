//Bodie Malik
//section 502
//Ziaullah Khan
//9-30-15

public class Executor {

	public static void main(String[] args) {
		Utility.init(); // initializes file readers
		String[] questions = Utility.readQuestions(); //reads question.txt file into questions array
		String[] answers = Utility.readAnswers(); // reads answers.txt file into answers array
		
		int numOracles = answers.length; //finds the number of oracles
		
		//TODO Assign questions to oracles using Utility.random and print the question paired with the oracle response

		// 1. Initialize one ArrayQueue per oracle (arrays will work best).
		ArrayQueue[] queues = new ArrayQueue[numOracles];
		for( int i = 0; i < queues.length; i++){
			
			queues[i] = new ArrayQueue();
			
		}
		// 2. Put the questions into the queues, assigning each one to the queue of the oracle whose number is returned by the random number generator.
		for(int i = 0; i < questions.length; i++){
			//question we need to add
			String q = questions[i];
			
			//which array queue. If queue is full, pick a new random queue.
			int queueNum = Utility.random(queues.length);
			
			while( queues[queueNum].isFull() ){
				queueNum = Utility.random(queues.length);
			}
			
			//enqueue question
			queues[queueNum].enqueue( q );
		}
		
		
		// 3. Loop through the oracles, having each one remove a question from its queue (if empty do nothing) and answer it with its unique answer (oracle[k] uses answers[k]). Do this repeatedly till all queues become empty.
		//does a for loop. Continues to do the for loop until all are empty.
		boolean repeat = false;
		
		do{
			
			repeat = false;//if no array queues have anything to print, then this stays false, and the loop ends.
			
			for( int i = 0; i < queues.length; i++){
				
				if(!queues[i].isEmpty() ){
					String q = queues[i].dequeue();
					System.out.println(q + ": " + answers[i]);
					repeat = true;
				}
				
			}
			
		}while( repeat );
		
		
		//end of main()
	}
}

