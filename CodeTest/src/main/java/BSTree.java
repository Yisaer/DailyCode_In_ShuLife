public class BSTree <T extends Comparable<T>> {

    public BSTNode<T> root;
    public class BSTNode<T extends Comparable<T>> {
        T key;
        BSTNode<T> left;
        BSTNode<T> right ;
        BSTNode<T> parent;

        public BSTNode(T key, BSTNode<T> left, BSTNode<T> right, BSTNode<T> parent) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        public T getKey() {
            return key;
        }
    }

    private   BSTNode<T> search(BSTNode<T> x , T key){
        if ( x == null ){
            return x;
        }
        int cmp = key.compareTo(x.key);
        if(cmp < 0){
            return search(x.left,key);
        }
        else if( cmp > 0 ){
            return search(x.right , key);
        }
        else return x;
    }
    
    public BSTNode<T> search( T key ){
        return search(root,key);
    }

}
