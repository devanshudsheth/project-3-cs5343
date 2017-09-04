
//
// CONSTRUCTION: with no initializer

import java.util.Stack;

//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @param <AnyType>
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public BinarySearchTree( )
    {
        root = null;
       
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        root = insert( x, root );
    }
    public void insertmirror( AnyType x )
    {
        root = insertmirror( x, root );
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( AnyType x )
    {
        root = remove( x, root );
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     * @throws java.lang.Exception
     */
    public AnyType findMin( ) throws Exception
    {
        if( isEmpty( ) )
            throw new Exception("Underflow Exception" );
        return findMin( root ).element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     * @throws java.lang.Exception
     */
    public AnyType findMax( ) throws Exception
    {
        if( isEmpty( ) )
            throw new Exception( "Underflow Exception" );
        return findMax( root ).element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains( AnyType x )
    {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }
    
    
    public int nodeCount()
    {
        return nodeCount(root);
    }
    
    private int nodeCount(BinaryNode<AnyType> t)
    {
        if(t == null)
            return 0;
        else 
            return 1 + nodeCount(t.left) + nodeCount(t.right);     // the root + left half + right half
    }

    public boolean isFull()
    {
        return isFull(root);
    }
    private boolean isFull(BinaryNode<AnyType> t)
    {
        
        if(t == null)
            {
            return true;
            }
          
        if(t.left == null && t.right == null )      //leaf node
            {
            return true;
            }
        
        if((t.left!=null) && (t.right!=null))                   // check if it has two children
            {
            return (isFull(t.left) && isFull(t.right));        //left and right subtree must also be full
            }
          
        else
        return false;
    }
    
    public boolean compareStructure(BinarySearchTree<AnyType> t1)
    {
        BinaryNode<AnyType> root1 = t1.root;
        return compareStructure(root, root1);
    }
    
  
    private boolean compareStructure(BinaryNode<AnyType> t, BinaryNode<AnyType> t1) 
    {
        
        if ((t == null && t1 != null) || (t != null && t1 == null))   // one of them is null
            {
            return false;
            }
        if (t == null && t1 == null)  // both are null
            {
            return true;
            }
        return compareStructure(t.left, t1.left) && compareStructure(t.right, t1.right);
    }
    
    public boolean equals(BinarySearchTree<AnyType> t1)
    {
        BinaryNode<AnyType> root1 = t1.root;
        return equals(root, root1);
    }
    
    
    private boolean equals(BinaryNode<AnyType> t, BinaryNode<AnyType> t1) 
    {
        
        if ((t == null && t1 != null) || (t != null && t1 == null)) 
            {
            return false;
            }

        if (t == null && t1 == null) 
            {
            return true;
            }
        if (t.element != t1.element)      //check data at t and t1
        {
        return false;
        }
        return equals(t.left, t1.left) && equals(t.right, t1.right);
    }
    
     public BinarySearchTree<AnyType> copy(BinarySearchTree<AnyType> oldtree)
        {
            BinarySearchTree<AnyType> newTree = new BinarySearchTree<>();
            return copy(root, newTree);
        }
        private BinarySearchTree<AnyType> copy(BinaryNode<AnyType> t, BinarySearchTree<AnyType> newTree)
        {
            
        if (t == null)           //if null   
	    return null;
 
        newTree.insert(t.element);              //preorder logic
        if(t.left!= null)
                {
                  copy(t.left, newTree);  
                }
        
        if(t.right!= null)
                {
                copy(t.right, newTree);  
                }
       
       return newTree;
        }
        
        BinaryNode<AnyType> t1 = null;
 private BinaryNode<AnyType> mirror(BinaryNode<AnyType> t, BinarySearchTree<AnyType> tree)
    {
        BinaryNode<AnyType> left =  null, right = null;
        t1 = new BinaryNode(t.element, right, left);
        tree.insertmirror(t1.element);
        
        if (t.left != null)
            {
            left = mirror(t.left, tree);
            }
        if (t.right != null) 
            {
            right = mirror(t.right, tree);
            }
        t1 = new BinaryNode(t.element, right, left);
        tree.insertmirror(t1.element);
        return t1;    
    }

    
    private boolean isMirror(BinaryNode<AnyType> t, BinarySearchTree<AnyType> t1)
    {
        BinarySearchTree<AnyType> tmp_tree = new BinarySearchTree<>();        //create temp mirror using our mirror() function
        this.mirror(t, tmp_tree);
        return tmp_tree.equals(t1);                   //compare if temp is equal to t1 using our equals() function
    }
    
    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return new BinaryNode<>( x, null, null );
        
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, t.right );
        else
            ;  // Duplicate; do nothing
        return t;
    }
    
    private BinaryNode<AnyType> insertmirror( AnyType x, BinaryNode<AnyType> t )      // insert as a mirror
    {
        if( t == null )
            return new BinaryNode<>( x, null, null );
        
        int compareResult = x.compareTo( t.element );
            
        if( compareResult > 0 )                             // elements greater fall on left of node
            t.left = insertmirror( x, t.left );
        else if( compareResult < 0 )
            t.right = insertmirror( x, t.right );     // elements greater fall on right of node
        else
            ;  // Duplicate; do nothing
        return t;
    }
    
    private void printbyLevel(BinarySearchTree<AnyType> tree)              // print by level iterative step
    {
        int h = height(root)+1;                                   // levels = height(root) + 1
        for (int i=1; i<=h; i++) 
        {
            printbyLevel(tree.root, i);
            System.out.println("<--- Level " + i );
        }
    }
    
    private void printbyLevel(BinaryNode<AnyType> t, int level)
    {
        if(t == null)
        {
        }
        
       else if(level == 1)                                     //if level one print as is
        {
        System.out.print(t.element+" ");
        }
       
        else 
        {
        printbyLevel(t.left, level-1);                         //recursive call to lower level print left subtree
        printbyLevel(t.right, level-1);                        //recursive call to lower level print right subtree
        }
    }
    Stack<BinaryNode<AnyType>> parentStack = new Stack<>();          // stack where we will store the parents

    private BinaryNode<AnyType> getParent(BinaryNode<AnyType> givenNode)
    {
        AnyType x = givenNode.element;
        BinaryNode<AnyType> k = find(x);
        return getParent( root, k);
    }
  
  private BinaryNode<AnyType> getParent( BinaryNode<AnyType> t, BinaryNode<AnyType> givenNode) 
    {
        if ( t == null)
            return null;
        
        else if (givenNode == root)                   //if node is root, parent is root 
        return root;
  
        else if (root.left == givenNode || root.right == givenNode)       // if node is immediate left or right of root, parent is root
        {
            return root;
        } 
        
        else{
                if(parentStack.size()==0)          // if stack size is zero push the first node 
                {
                    parentStack.push(t);
                   
                }
                if (t.left == givenNode )                   // if the next left node is given node, return current
                {
                return t;
                }
                else if(t.left != givenNode)          //keep pushing parents
                {
                   parentStack.push(t.left); 
                   getParent(t.left);  
                }
               
                BinaryNode<AnyType> parent = parentStack.pop();            //pop last parent
                
                if (t.right == givenNode )
                {
                return t;
                }
                else if(t.right != givenNode)
                {
                    parentStack.push(t.right);
                    getParent(t.right);
                }
                
                return parent;
                
        }
      
    } 

    
    private void leftRotate(AnyType x)
    {
        BinaryNode<AnyType> t = find(x);
        leftRotate(this, t);
        
    }    
   private BinaryNode<AnyType> leftRotate(BinarySearchTree<AnyType> tree, BinaryNode<AnyType> k1)
   {
       if ( k1 == null)                //null is passed
           return k1;
       
       else if ( k1.left == null && k1.right == null)          //single node tree is passed or leaf node 
           return k1;
       
       
       else if ( k1 == root)                                   //root case
       {
        BinaryNode<AnyType> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max( height( k1.left ), height( k1.right ) ) + 1;
        k2.height = Math.max( height( k2.right ), k1.height ) + 1;
        root = k2;
        return k2;
        
                   
       }
       
       else if( k1 == root.left || k1 == root.right)        //child of root case
       {
        BinaryNode<AnyType> parent = getParent(k1);
        System.out.println("Parent " +parent.element);
        BinaryNode<AnyType> k2 = k1.right;
        if(k1 == root.left)                                 //if left child of root
        {
            parent.left = k2;
        }
        else                                               //if right child of root
        {
            parent.right = k2;
        }
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max( height( k1.left ), height( k1.right ) ) + 1;
        k2.height = Math.max( height( k2.right ), k1.height ) + 1;
       }
       
       
  return root;
   }
   
   
     private void rightRotate(AnyType x)
    {
        BinaryNode<AnyType> t = find(x);
        rightRotate(this, t);
        
    }    
   private BinaryNode<AnyType> rightRotate(BinarySearchTree<AnyType> tree, BinaryNode<AnyType> k2)
   {
       if ( k2 == null)
           return k2;
      
       else if ( k2.left == null && k2.right == null)
           return k2;
       
       
       else if ( k2 == root)
       {
        BinaryNode<AnyType> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max( height( k2.left ), height( k2.right ) ) + 1;
        k1.height = Math.max( height( k1.left ), k2.height ) + 1;
        root = k1;
        return k1;
        
                   
       }
       
       else if( k2 == root.left || k2 == root.right)
       {
        BinaryNode<AnyType> parent = getParent(k2);
        System.out.println("Parent " +parent.element);
        BinaryNode<AnyType> k1 = k2.left;
        if(k2 == root.left)
        {
            parent.left = k1;
        }
        else
        {
            parent.right = k1;
        }
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max( height( k1.left ), height( k1.right ) ) + 1;
        k1.height = Math.max( height( k2.right ), k1.height ) + 1;
       }
       
       
  return root;
   }
   
       
    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return t;   // Item not found; do nothing
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
    {
        if( t == null )
            return null;
        else if( t.left == null )
            return t;
        return findMin( t.left );
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
    {
        if( t != null )
            while( t.right != null )
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return false;
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            return contains( x, t.left );
        else if( compareResult > 0 )
            return contains( x, t.right );
        else
            return true;    // Match
    }
    
  private BinaryNode<AnyType> find(AnyType x)                        // find node containing x
  {
      return find(x, root);
  }
    private BinaryNode<AnyType> find(AnyType x, BinaryNode<AnyType> node)
    {
    if(node != null)
        {
        if(node.element == x)
            {
            return node;
            } 
        else 
            {
            BinaryNode<AnyType> foundNode = find(x, node.left);
            if(foundNode == null)
                {
                foundNode = find(x, node.right);
                }
      
        return foundNode;
            }
        } 
        else 
            {
            return null;
            }

    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the subtree.
     */
    private void printTree( BinaryNode<AnyType> t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
    }
    
    

    /**
     * Internal method to compute height of a subtree.
     * @param t the node that roots the subtree.
     */
    private int height( BinaryNode<AnyType> t )
    {
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height( t.left ), height( t.right ) );    
    }
    
    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType>
    {
            // Constructors
        BinaryNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
        }
        
        
        int height;
        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
    }


      /** The tree root. */
    private BinaryNode<AnyType> root;


        // Test program
    public static void main( String [ ] args ) throws Exception
    {
       BinarySearchTree<Integer> t = new BinarySearchTree<>();             //create a tree with root = 4, immediate children = 2,6, and so on.
       t.insert(4);
       t.insert(2);
       t.insert(6);
       t.insert(1);
       t.insert(3);
       t.insert(5);
       t.insert(7);
       
       System.out.println("Inorder Printing: ");
       t.printTree();                                    //print inorder   
     
       
       System.out.println("\nnodeCount() method");
       int a =  t.nodeCount();
       System.out.println("Number of nodes : "+a);             //count nodes

       
       System.out.println("\nisFull() method");                   //check if full
       t.printbyLevel(t);
       boolean FullCheck = t.isFull();
       if(FullCheck == true)
           System.out.println("Tree is Full");
       else
           System.out.println("Tree is not Full");
       
       BinarySearchTree<Integer> t1 = new BinarySearchTree<>();         //make new tree t1
       t1.insert(7);
       t1.insert(5);
       t1.insert(9);
       t1.insert(4);
       t1.insert(6);
       t1.insert(8);
       t1.insert(10);
       
       boolean StructCheck = t.compareStructure(t1);                       // compare structure of t1 with t
       System.out.println("\nTree 1:");
       t.printbyLevel(t);
       System.out.println("\nTree 2:");
       t1.printbyLevel(t1);
       System.out.println("\ncompareStructure() method");
       if(StructCheck == true)
           System.out.println("Tree has Same Structure");
       else
           System.out.println("Tree does not have Same Structure");
       
     
       boolean TreeCheck = t.equals(t1);
       System.out.println("\nTree 1:");                           //check if t and t1 are equal
       t.printbyLevel(t);
       System.out.println("\nTree 2:");
       t1.printbyLevel(t1);
       System.out.println("\nequals() method");
       if(TreeCheck == true)
           System.out.println("Trees are equal");
       else
           System.out.println("Trees are not equal");
       
       System.out.println("\nEmptying t1: ");
       t1.makeEmpty();                                            //empty t1 so we can copy to t to it
       System.out.println("\nOriginal Tree: ");
       t.printbyLevel(t);
       t1 = t.copy(t);
       System.out.println("\nCopied Tree: ");
       t1.printbyLevel(t1);
 
       System.out.println("\nEmptying t1: ");
       t1.makeEmpty();                                            //empty t1 so we can make mirror of t
       t1.printbyLevel(t1);
       System.out.println("Original tree: ");
       t.printbyLevel(t);
       System.out.println("\nAfter mirroring");
       t.mirror(t.root,t1);
       t1.printbyLevel(t1);
       
       boolean MirrorCheck = t.isMirror(t.root,t1);
       System.out.println("\nisMirror() method");                    //check if mirror
       
       if(MirrorCheck == true)
           System.out.println("It is a mirror");
       else
           System.out.println("It is not a mirror");
       
       System.out.println("\nPrinting by Level: ");                     // demonstrate printbylevel
       t.printbyLevel(t);
       
       
       System.out.println("\nTree:");
       t.printbyLevel(t);
       BinaryNode<Integer> k = t.find(2);
       
       System.out.println("\nTest getParent() method for 2 in Tree: ");
       BinaryNode<Integer> parent = t.getParent(k);
       System.out.println("Parent of 2 is : "+parent.element);
       
      
       System.out.println("\nRotate the tree left around node 4 i.e. root :"); // rotate about root on left on t
       t.leftRotate(4);
       t.printbyLevel(t);
 
       System.out.println("\nRotate the Tree Right around node 4 i.e. root:");
       t1.makeEmpty();                    // make t1 empty
       t1.insert(4);                      //remake sample tree t on t1
       t1.insert(2);
       t1.insert(6);
       t1.insert(1);
       t1.insert(3);
       t1.insert(5);
       t1.insert(7);
       t1.rightRotate(4);                    //perform rotateright
       t1.printbyLevel(t1);
      
       
       
        final int NUMS = 4000;
        final int GAP  =   37;

        System.out.println( "Checking... (no more output means success)" );            // author's testing begins here

        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
            t.insert( i );

        for( int i = 1; i < NUMS; i+= 2 )
            t.remove( i );

        if( NUMS < 40 )
            t.printTree( );
        if( t.findMin( ) != 2 || t.findMax( ) != NUMS - 2 )
            System.out.println( "FindMin or FindMax error!" );

        for( int i = 2; i < NUMS; i+=2 )
             if( !t.contains( i ) )
                 System.out.println( "Find error1!" );

        for( int i = 1; i < NUMS; i+=2 )
        {
            if( t.contains( i ) )
                System.out.println( "Find error2!" );
        } 
    }
}