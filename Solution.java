import java.util.*;

public class Solution<Key extends Comparable<Key>, Value> {
    private Node root; 
    int size = 0;            // root of BST

    private class Node {
        private Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees
     
        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
            
        }
    }

    /**
     * Initializes an empty symbol table.
     */
    public Solution() {
        root = null;

    }

    /**
     * Returns true if this symbol table is empty.
     * @return {@code true} if this symbol table is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        if(size ==0){
            return true;
        }
        return false;
        
       
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
      return size;
       
    }

   
    /**
     * Does this symbol table contain the given key?
     *
     * @param  key the key
     * @return {@code true} if this symbol table contains {@code key} and
     *         {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    // public boolean contains(Key key) {
       
    // }



    private Value get(Key key) {
      
        if (key == null){ 
            throw new IllegalArgumentException("calls get() with a null key");
        }
        Node curNode = root;
        while(curNode.key != key){
        	int comp = key.compareTo(curNode.key);
        	if(comp < 0){
        		curNode = curNode.left;
        	}
	        else if(comp > 0){
	        	curNode = curNode.right;
	        }
        }
        if(curNode.key == key){
        	// System.out.println(curNode.val);
        }
        return curNode.val;
    }




    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the old 
     * value with the new value if the symbol table already contains the specified key.
     * Deletes the specified key (and its associated value) from this symbol table
     * if the specified value is {@code null}.
     *
     * @param  key the key
     * @param  val the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void put(Key key, Value val) {
        Node newNode = new Node(key,val);
        if(root == null){
        	root = newNode;
        }
        else{
        	Node curNode = root;
        	Node parent;
        	while(true){
        		parent = curNode;
        		int comp = key.compareTo(curNode.key);
        		if(comp < 0){
        			curNode = curNode.left;
        			if(curNode == null){
        				parent.left = newNode;
        				size = size + 1;
        				return;
        			}
        			else if(curNode.key == key){
        				curNode.val = val;
        				return;
        			}
        		}
        		else if(comp > 0){
        			curNode = curNode.right;
        			if(curNode == null){
        				parent.right = newNode;
        				size = size + 1;
        				return;
        			}
        			else if(curNode.key == key){
        				curNode.val = val;
        				return;
        			}
        		}
        	}
        }
        size = size + 1;
    }

   

    /**
     * Returns the largest key in the symbol table less than or equal to {@code key}.
     *
     * @param  key the key
     * @return the largest key in the symbol table less than or equal to {@code key}
     * @throws NoSuchElementException if there is no such key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */

    

    /**
     * Return the key in the symbol table whose rank is {@code k}.
     * This is the (k+1)st smallest key in the symbol table.
     *
     * @param  k the order statistic
     * @return the key in the symbol table of rank {@code k}
     * @throws IllegalArgumentException unless {@code k} is between 0 and
     *        <em>n</em>–1
     */
    public Key min() {
        if(isEmpty()){
            throw new NoSuchElementException("There is no element found");
        }
        else{
                Node curNode = root;
                while(curNode.left != null){
                    curNode = curNode.left;
                }
                return curNode.key;
        }
     } 
 
     public Key max() { 
         if(isEmpty()){
                throw new NoSuchElementException("There is no element found");
        }
        else{
                Node curNode = root;
                while(curNode.right != null){
                    curNode = curNode.right;
                }
                return curNode.key;
        }
     } 
    

    public Iterable<Key> keys(Key lo, Key hi) {
        if(lo==null || hi==null) throw new IllegalArgumentException("argument to keys() is null");

        Queue<Key> queue = new LinkedList<Key>();
        keys(root, queue, lo, hi);  
        return queue;
    } 

    private void keys(Node x,Queue<Key> queue, Key lo, Key hi) { 
        if (x == null) return;  
        
        Node temp1 = x;  
        while (temp1 != null){  
      
            int cmp = temp1.key.compareTo(hi);
            int cam = temp1.key.compareTo(lo);
            if (temp1.left == null){   
                if (cmp <= 0 && cam >= 0)  queue.offer(temp1.key);
                temp1 = temp1.right;

            }else{  
                Node temp2 = temp1.left;
            
                while (temp2.right != null && temp2.right != temp1)  
                    temp2 = temp2.right;  
        
                if (temp2.right == null){  
                    temp2.right = temp1;  
                    temp1 = temp1.left; 

                }else{  
                    temp2.right = null;    
                    if (cmp <= 0 && cam >= 0)  queue.offer(temp1.key);  
                    temp1 = temp1.right;  
                }  
            }  
        }   
    } 

    public Key floor(Key key){
		if(isEmpty()){
			throw new NoSuchElementException("calls floor() with empty symbol table");
		}
		Node curNode = root;
		Node parent = null;
		while(curNode != null){
			parent = curNode;
			int cmp = key.compareTo(parent.key);
			if(key == null){
				throw new IllegalArgumentException("calls floor() with a null key");
			}
			if(cmp == 0){
				return parent.key;
			}
			if(cmp < 0){
				curNode = parent.left;
			}
			//This condition is for checking floor of given key in right side
			else if(cmp > 0){
				curNode = parent.right;
				//This condition returns previous parent as floor of given key as it is less the right parent
				int cm = key.compareTo(curNode.key);
				if(cm < 0 ){
					return parent.key;
				}
				//This is for if the key is still greater then right parent
				else{
					curNode = parent.right;
				} 
			}
		}
		return parent.key;
    }
    public Key select(Key key){
		if(isEmpty()){
			throw new NoSuchElementException("calls floor() with empty symbol table");
		}
		Node curNode = root;
		Node parent = null;
		while(curNode != null){
			parent = curNode;
			int cmp = key.compareTo(parent.key);
			if(key == null){
				throw new IllegalArgumentException("calls floor() with a null key");
			}
			if(cmp > 0){
				curNode = parent.right;
			}
			//This condition is for checking floor of given key in left side
			else if(cmp < 0){
				curNode = parent.left;
				//This condition checks if key is greater than left parent then it returns the curNode 
				int cm = key.compareTo(parent.key);
				if(cm > 0 ){
					return curNode.key;
				}
				//This is for if the key is still less then left parent
				else{
					curNode = parent.left;
				} 
			}
		}
		return parent.key;
	}
  
    

 
  
    public static void main(String[] args) { 

        Solution<String, Integer> BST = new Solution<String,Integer>();
        assert(BST.isEmpty()==true);
        BST.put("ABDUL",1);
        System.out.println(BST.get("ABDUL"));
        BST.put("HRITHIK",2);
        BST.put("SAI",3);
        BST.put("SAMAL",6);
        System.out.println(BST.get("SAI"));
        BST.put("TASHI",4);
        System.out.println(BST.size());
        System.out.println(BST.min());
        System.out.println(BST.floor("HRITHIK"));
        System.out.println(BST.floor("HAHA"));
        System.out.println(BST.select("HRITHIKH"));
        System.out.println(BST.keys("ABDUL","TASHI"));
        BST.put("CHIMMI",5);
        BST.put("SAMAL",4);
        System.out.println(BST.get("SAMAL"));
        BST.put("NIMA",7);
        System.out.println(BST.size());
        System.out.println(BST.get("CHIMMI"));
        System.out.println(BST.floor("CHIMA"));
        BST.put("SONAM",8);
        System.out.println(BST.keys("ABDUL","TASHI"));

    
        
       
    }
}