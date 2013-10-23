import java.util.*;

public class FibonacciHeap<T> {
    
    public class TreeNode<T> {
       
        private TreeNode<T> rightsibling;   // sibling elements in the list
        private TreeNode<T> leftsibling;
        private TreeNode<T> parent;
        private TreeNode<T> child;  // Child node
		private int     degree = 0;       // node degree
        private boolean childcut = false; 
        private T      data;     // edge = T
        private double weight; 
        
        public T getData() {
            return data;
        }
        
        public void setData(T value) {
            data = value;
        }
        public double getWeight() {
            return weight;
        }
		//constructor for treenode
        private TreeNode(T elem, double edgeweight) {
            rightsibling = leftsibling = this;
            data = elem;
            weight = edgeweight;
        }
    }

    //Pointer to the minimum element in the heap. 
    private TreeNode<T> min = null;

    //size of the heap
    private int size = 0;

    public void insert(T value, double priority) {
        //create new node here
        TreeNode<T> result = new TreeNode<T>(value, priority);
        //merge it with top level list
        min = merge(min, result);
       //increase heapsize
        ++size;
    }

   
    public TreeNode<T> min() {
        return min;
    }

   
    public boolean isEmpty() {
        return min == null;
    }

  
    public int size() {
        return size;
    }

    
    public TreeNode<T> deleteMin() {
       
        --size;

        //store the current min element
        TreeNode<T> minElem = min;

      
        if (min.rightsibling == min) { //if only one element
            min = null;
        }
        else { // delete min from top level list
            min.leftsibling.rightsibling = min.rightsibling;
            min.rightsibling.leftsibling = min.leftsibling;
            min = min.rightsibling; // set min to some value and decide real min later
        }

      //erase the parent field of min's children
        if (minElem.child != null) {
           
            TreeNode<?> curr = minElem.child;
            do {
                curr.parent = null;
                curr = curr.rightsibling;
            } while (curr != minElem.child);
        }

       
        min = merge(min, minElem.child);

        // If there are no entries left, we're done
        if (min == null) return minElem;

        //store top level info
        List<TreeNode<T>> treeTable = new ArrayList<TreeNode<T>>();

        //node we need to visit
        List<TreeNode<T>> toVisit = new ArrayList<TreeNode<T>>();

        //add node to list
        for (TreeNode<T> curr = min; toVisit.isEmpty() 
		|| toVisit.get(0) != curr; curr = curr.rightsibling)
            toVisit.add(curr);

        //do pairewise combination
        for (TreeNode<T> curr: toVisit) {
            
            while (true) {
               //keep treetable a proper size
                while (curr.degree >= treeTable.size())
                    treeTable.add(null);

                if (treeTable.get(curr.degree) == null) {
                    treeTable.set(curr.degree, curr);
                    break;
                }

               //if conflict, merge them
                TreeNode<T> other = treeTable.get(curr.degree);
                treeTable.set(curr.degree, null);
                
                TreeNode<T> min = (other.weight < curr.weight)? other : curr;
                TreeNode<T> max = (other.weight < curr.weight)? curr  : other;

                max.rightsibling.leftsibling = max.leftsibling;
                max.leftsibling.rightsibling = max.rightsibling;  
                max.rightsibling = max.leftsibling = max;
                min.child = merge(min.child, max);              
                max.parent = min;
                max.childcut = false;

                //increase its degree
                ++min.degree;
				
                curr = min;
            }

          //decide which is smaller
            if (curr.weight <= min.weight) min = curr;
        }
        return minElem;
    }
    


   //merge two double linkedlist into one
    private <T> TreeNode<T> merge(TreeNode<T> min, TreeNode<T> inserted) {
        
        if (min == null && inserted == null) { 
            return null;
        }
        else if (min != null && inserted == null) {
            return min;
        }
        else if (min == null && inserted != null) { 
            return inserted;
        }
        else { 
            TreeNode<T> minNext = min.rightsibling;
            min.rightsibling = inserted.rightsibling;
            min.rightsibling.leftsibling = min;
            inserted.rightsibling = minNext;
            inserted.rightsibling.leftsibling = inserted;

           //decide which is smaller
            return min.weight < inserted.weight? min : inserted;
        }
    }

    

    
    private void ChildCut(TreeNode<T> TreeNode) {
        //reset it to false since it is cut
        TreeNode.childcut = false;

       //if no parent, it is done here
        if (TreeNode.parent == null) return;

       //if it has siblings
        if (TreeNode.rightsibling != TreeNode) { 
            TreeNode.rightsibling.leftsibling = TreeNode.leftsibling;
            TreeNode.leftsibling.rightsibling = TreeNode.rightsibling;
        }

        //if has children
        if (TreeNode.parent.child == TreeNode) {
            
            if (TreeNode.rightsibling != TreeNode) {
                TreeNode.parent.child = TreeNode.rightsibling;
            }
           
            else {
                TreeNode.parent.child = null;
            }
        }

       //decrease degree, lost child
        --TreeNode.parent.degree;

       
        TreeNode.leftsibling = TreeNode.rightsibling = TreeNode;
        min = merge(min, TreeNode);

      
        if (TreeNode.parent.childcut)
            ChildCut(TreeNode.parent);
        else
            TreeNode.parent.childcut = true;

        //clear its parent field
        TreeNode.parent = null;
    }
}