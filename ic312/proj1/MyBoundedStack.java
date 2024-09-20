import java.util.NoSuchElementException;
import java.util.*;


/** A stack with limited (but changeable) capacity.
*
* When new items pushed, the go on the "top" of the stack.
* Calling pop() also removes from the "top" of the stack,
* so that push/pop are LIFO.
*
* But stacks also have a fixed capacity. WHen calling push(),
* if the stack is already at its capacity, the oldest item
* ("bottom" of the stack) is silently removed.
*/
public class MyBoundedStack<T> implements BoundedStack<T> {
 @SuppressWarnings("unchecked")
 private T[] elements;
 private int size = 0;
 private int capacity = 0;
 //head keeps track of where the start and end of the array is
 private int head = 0;
 private int tail = 0;


 public MyBoundedStack(int capacity) {
   @SuppressWarnings("unchecked")
   T[] elements = (T[]) new Object[capacity];
   this.elements = elements;
   this.capacity = capacity;
 }


 /** Adds a new element to the top of the stack.
  *
  * If the stack is already at its capacity, a single item is
  * removed from the BOTTOM of the stack.
  */
 public void push(T item) {
   elements[tail] = item;// tail = 0
   tail = (tail + 1) % capacity; // tail = 1


   //Array = full; move head
   //Tail will be off by one
   //head will be equal to tail once array is full
   if (tail + 1 == head) {
     head = (head + 1) % capacity;
   }
   else {
     size++;
   }
   //Idea is that the head is always the actual start place
   //Tail = head once it circles around to the front
   //Tail will always be 1 more than its actual spot
     //This means when pushing, just set the value right there
     //When popping/setCapacity, use tail - 1






   // if (capacity == 1) {
   //   elements[0] = item;
   //   if (size == 0)
   //     size++;
   // }
   // else {
   //   elements[tail] = item;
   //   tail = (tail + 1) % capacity;
   // if (size == capacity) {
   //   head = (head + 1) % capacity;
   // }
   // else {
   //   size++;
   // }
   // }




 }


 /** Removes and returns the element at the top of the stack.
  * @throws NoSuchElementException if the stack is empty.
  */
 @Override
 public T pop() throws NoSuchElementException {
   if (isEmpty()) {
       throw new NoSuchElementException("The Stack is empty");
   }
   else {
     //tail is off by 1, minus 1 puts it to the correct spot
     tail = (tail - 1 + capacity) % capacity;
     T item = elements[tail];//get item
     elements[tail] = null;//set value to null
     //tail is now one off of next value
     if (size <= 1) {
       size = 0;
     }
     else {
       size--;//array is not empty yet
     }
     return item;
   }
 }


 /** Changes the capacity to the given value.
  *
  * If the current capacity is greater than the given capacity,
  * then elements may need to be removed from the BOTTOM of the
  * stack so that it reaches the desired capacity.
  *
  * This affects all future push/pop operations, but not past
  * ones. That is, increasing the capacity does not make
  * the stack magically "remember" things which have already been
  * removed.
  */
 public void setCapacity(int capacity) {
   @SuppressWarnings("unchecked")
   T[] temp = (T[]) new Object[capacity];
   int count = 0;


   //move array over backwards and then make it forward
   //i = tail -1 because tail is always off by 1
   for (int i = (tail - 1 + this.capacity) % this.capacity; count < size && count < capacity; i = (i - 1 + this.capacity) % this.capacity) {
     temp[count] = elements[i];//0 = tail
     System.out.println(elements[i]);
     count++;
   }
   count--;//Count is 1 too large
   //Array has been moved over, but is backwards
   //Make it forwards
   int n = count;
   @SuppressWarnings("unchecked")
   T[] t2 = (T[]) new Object[capacity];
   //i <= n because count is the number of times to copy
   for (int i = 0; i <= n; i++) {
     t2[i] = temp[count];
     count--;
   }


   if (size >= capacity) {
     size = capacity;
     head = 0;
     tail = 0;
     //new array so head = 0
     //array is full so tail is up by 1 which is 0
   }
   else {
     head = 0;
     tail = size;
     //size stays the same
     //head always = 0
     //tail = size because size is 1 larger than the position
   }


   this.capacity = capacity;


   elements = t2;


   // if (size >= capacity) {
   //   count = capacity - 1;
   //   }
   // else {
   //   count = size - 1;
   // }
   // int i = tail;
   // do {
   //   i = (i - 1 + this.capacity) % this.capacity;
   //   temp[count] = elements[i];
   //   count--;
   //   if (count == -1)
   //     break;
   // }while(i != head);


   // if (size >= capacity){
   //   size = capacity;
   // }
   // head = 0;
   // tail = size;
   // this.capacity = capacity;


   // elements = temp;
 }


 /** Returns whether the stack is currently empty. */
 public boolean isEmpty() {


   return size == 0;
 }


 /** Removes all elements from the stack.
  *
  * The capacity should remain unchanged.
  */
 public void clear() {
   @SuppressWarnings("unchecked")
   T[] temp = (T[]) new Object[capacity];
   elements = temp;
   head = 0;
   tail = 0;
   size = 0;
 }




   public T get(int num) {
       return elements[(num + this.head) % this.capacity];
   }


   public String getAll(){
     return size + " " + head + " " + tail;
   }






     @Override
 // this produces a string like "[ 1 2 3 4 ]"
 public String toString() {
   StringBuilder sb = new StringBuilder();
   sb.append("[ ");
   for (int i = 0; i < size; i++) {
     sb.append(get(i).toString());
     sb.append(' ');
   }
   sb.append(']');
   return sb.toString();
 }


 public static void main(String args[]) {
   BoundedStack<Integer> stk = new MyBoundedStack<>(3);
   stk.push(10);
   stk.push(20);
       //System.out.println(stk.get(0));
   stk.setCapacity(5);
   stk.push(30);
   stk.push(40);
   // System.out.println(stk.get(0));
   // System.out.println(stk.get(1));
   // System.out.println(stk.get(2));
   // System.out.println(stk.get(3));


   //System.out.println(stk.toString());


   if (40 == (int)stk.pop()) {
     System.out.println("success");
   }
       if (30 == (int)stk.pop()) {
     System.out.println("success");
   }
  
   System.out.println(stk.getAll());


       if (20 == (int)stk.pop()) {
     System.out.println("success");
   }
       if (10 == (int)stk.pop()) {
     System.out.println("success");
   }
   stk.push(100);
   stk.push(200);
   stk.push(300);
  
   stk.setCapacity(1);
   if (!stk.isEmpty()) {
     System.out.println("not empty");
   }
   if (300 == (int)stk.pop()) {
     System.out.println("success");
   }   
   if (stk.isEmpty()) {
     System.out.println("empty");
   }
 }
}

