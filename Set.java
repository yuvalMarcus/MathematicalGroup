
/**
 * This class represents a mathematical set which is a set of integers
 * 
 * @author yuval m
 * @version 1.1a
 */

public class Set
{
    private IntNode _head;
    private int _count;

    /**
     * Constructs a Set class, creates a new Set object.
     * And initializes the _count 0.
     * And initializes the _head empty (null).
     */    

    public Set() {
        _count = 0;
        _head = null;
    }

    /**
     * This method checking if the group is empty.
     * 
     * Time complexity is O(1)
     * Complexity place is O(1)
     * 
     * @return true if the group is empty, false if not.
     */    

    public boolean isEmpty() {
        return _head == null;
    }

    /**
     *  This method checks if number exists in a group.
     *  
     * Time complexity is O(n)
     * Complexity place is O(n)
     *  
     *  @param int - number to check.
     *  @returns true if exists a group number and false if not.
     */ 

    public boolean isMember(int num) {

        if(this.isEmpty())
            return false;

        IntNode temp = _head;

        while(temp != null) {

            if(temp.getValue() == num)
                return true;
            else if(temp.getValue() > num)
                return false;            
            else
                temp = temp.getNext();

        }

        return false;

    }

    /**
     *  This method checks if the group accepts a parameter equal to the group.
     *  
     * Time complexity is O(n)
     * Complexity place is O(n)
     *  
     *  @param Set - Group to comparison.
     *  @returns true if the group accepts a parameter equal to the group.
     */ 

    public boolean equals(Set obj) {

        if(this.isEmpty() && obj._head == null)
            return true;

        if(this.isEmpty() || obj._head == null)
            return false;            

        IntNode temp = _head;
        IntNode tempObj = obj._head;

        boolean next = true;

        while(temp != null && tempObj != null && next) {

            if((temp.getNext() == null && tempObj.getNext() != null) || (temp.getNext() != null && tempObj.getNext() == null))
                next = false;

            if(temp.getValue() == tempObj.getValue()) {
                temp = temp.getNext();
                tempObj = tempObj.getNext();
            } else
                next = false;

        }

        return next;

    }

    /**
     *  This method gives the number of elements in the Group.
     *  
     * Time complexity is O(1)
     * Complexity place is O(1)
     *  
     *  @returns the number of elements in the Group.
     */ 

    public int numOfElements () {
        return _count;
    }

    /**
     *  This method checks if the group accepts a parameter is a subset of the group in object.
     *  
     * Time complexity is O(n)
     * Complexity place is O(n)
     *  
     *  @param Set - other group.
     *  @returns true if other is a sub-group of the group set an object and false otherwise.
     */ 

    public boolean subSet(Set other) {

        IntNode tempObj = _head;
        IntNode tempOth = other._head;

        while(tempOth != null) {

            if(tempObj == null && tempOth != null)
                return false;

            if(tempObj != null && tempOth != null && tempObj.getValue() == tempOth.getValue()) {
                tempObj = tempObj.getNext();
                tempOth = tempOth.getNext();
            } else if(tempObj == null || tempObj.getValue() < tempOth.getValue()) {
                tempObj = tempObj.getNext();
            } else if(tempOth == null || tempObj.getValue() > tempOth.getValue()) {
                return false;
            }

        }        

        return true;
    }

    /**
     *  Add element (number) to group.
     *  On condition that this number does not exist in the group.
     *  
     * Time complexity is O(n)
     * Complexity place is O(n)  
     *  
     *  @param int - number to add.
     */ 

    public void addToSet (int x) {

        if(this.isMember(x) || x <= 0 || x % 2 == 0)
            return;

        this._count++;

        if(this.isEmpty()) {
            _head = new IntNode(x,null);
            return;
        }

        IntNode temp = _head;

        if(temp.getValue() > x) {
            IntNode newSet = new IntNode(x,null);
            newSet.setNext(temp);
            _head = newSet;
            return;                
        }     

        while(temp != null) {

            if(temp.getNext() != null) {
                if(temp.getNext().getValue() > x) {
                    IntNode set = new IntNode(x,null);
                    set.setNext(temp.getNext());
                    temp.setNext(set);
                    return;
                } else
                    temp = temp.getNext();

            } else {
                IntNode set = new IntNode(x,null);
                temp.setNext(set);
                temp = temp.getNext();
                return;
            }
        }
    }

    /**
     *  remove element (number) in group.
     *  On condition that this number exist in the group.
     *  
     * Time complexity is O(n)
     * Complexity place is O(n)
     *  
     *  @param int - number to remove.
     */ 

    public void removeFromSet(int x) {

        if(this.isEmpty())
            return;

        IntNode temp = _head;

        if(_head.getValue() == x) {
            _head = _head.getNext();
            this._count--;
            return;
        }

        while(temp != null) {
            if(temp.getNext() != null && temp.getNext().getValue() == x) {
                temp.setNext(temp.getNext().getNext());
                this._count--;
                return;
            } else
                temp = temp.getNext();

        }

        return;

    }

    /**
     *  This method presents the group in a string that contains all the numbers in the group
     *  
     * Time complexity is O(n)
     * Complexity place is O(n)
     *  
     *  @returns string of numbers in the Group.
     */ 

    public String toString() {

        String s = "{";

        IntNode temp = _head;
        int count = 0;

        while(temp != null) {

            if(count == 0)
                s += "" + temp.getValue() + "";
            else
                s += "," + temp.getValue() + "";

            count++;
            temp = temp.getNext();

        }

        s += "}";

        return s;
    }

    /**
     *  This method returns the set intersection of other Group object's group.
     *  
     * Time complexity is O(n)
     * Complexity place is O(n)
     *  
     *  @param other - other group.
     *  @returns the set intersection of other Group object's group.
     */ 

    public Set intersection (Set other) {

        IntNode tempObj = _head;
        IntNode tempOth = other._head;

        IntNode newHead = null;
        IntNode newTemp = null;

        Set set = new Set();

        if(tempObj == null && tempOth == null)
            return set;

        while(tempObj != null || tempOth != null) {

            if(tempObj != null && tempOth != null && tempObj.getValue() == tempOth.getValue()) {

                if(newHead == null) {
                    newHead = new IntNode(tempObj.getValue(),null);
                    newTemp = newHead;
                } else {
                    newTemp.setNext(new IntNode(tempObj.getValue(),null));
                    newTemp = newTemp.getNext();
                }
                tempObj = tempObj.getNext();
                tempOth = tempOth.getNext();
            } else if(tempObj == null || ( tempOth != null && tempObj.getValue() > tempOth.getValue())) {
                tempOth = tempOth.getNext();
            } else if(tempOth == null || tempObj.getValue() < tempOth.getValue()) {
                tempObj = tempObj.getNext();
            }

        }
        set._head = newHead;
        return set;
    }

    /**
     *  This method returns the group's union group with the group an object other.
     *  
     * Time complexity is O(n)
     * Complexity place is O(n)
     *  
     *  @param other - other group.
     *  @returns the group's union group with the group an object other.
     */ 

    public Set union (Set other) {

        IntNode tempObj = _head;
        IntNode tempOth = other._head;

        IntNode newHead = null;
        IntNode newTemp = null;

        Set set = new Set();

        if(tempObj == null && tempOth == null)
            return set;

        while(tempObj != null || tempOth != null) {

            if(tempObj != null && tempOth != null && tempObj.getValue() == tempOth.getValue()) {

                if(newHead == null) {
                    newHead = new IntNode(tempObj.getValue(),null);
                    newTemp = newHead;
                } else {
                    newTemp.setNext(new IntNode(tempObj.getValue(),null));
                    newTemp = newTemp.getNext();
                }
                tempObj = tempObj.getNext();
                tempOth = tempOth.getNext();
            } else if(tempObj == null || ( tempOth != null && tempObj.getValue() > tempOth.getValue())) {

                if(newHead == null) {
                    newHead = new IntNode(tempOth.getValue(),null);
                    newTemp = newHead;
                } else {
                    newTemp.setNext(new IntNode(tempOth.getValue(),null));
                    newTemp = newTemp.getNext();
                }
                tempOth = tempOth.getNext();
            } else if(tempOth == null || tempObj.getValue() < tempOth.getValue()) {

                if(newHead == null) {
                    newHead = new IntNode(tempObj.getValue(),null);
                    newTemp = newHead;
                } else {
                    newTemp.setNext(new IntNode(tempObj.getValue(),null));
                    newTemp = newTemp.getNext();
                }
                tempObj = tempObj.getNext();
            }

        }
        set._head = newHead;
        return set;
    }

    /**
     *  This method returns the group set the difference of the group as an object other.
     *  
     * Time complexity is O(n)
     * Complexity place is O(n)
     *  
     *  @param other - other group.
     *  @returns the group set the difference of the group as an object other.
     */ 

    public Set difference (Set other) {

        IntNode tempObj = _head;
        IntNode tempOth = other._head;

        IntNode newHead = null;
        IntNode newTemp = null;

        Set set = new Set();
 
        if(tempObj == null && tempOth == null)
            return set;

        while(tempObj != null || tempOth != null) {

            if(tempObj != null && tempOth != null && tempObj.getValue() == tempOth.getValue()) {
                tempObj = tempObj.getNext();
                tempOth = tempOth.getNext();
            } else if(tempObj == null || ( tempOth != null && tempObj.getValue() > tempOth.getValue())) {
                tempOth = tempOth.getNext();
            } else if(tempOth == null || tempObj.getValue() < tempOth.getValue()) {

                if(newHead == null) {
                    newHead = new IntNode(tempObj.getValue(),null);
                    newTemp = newHead;
                } else {
                    newTemp.setNext(new IntNode(tempObj.getValue(),null));
                    newTemp = newTemp.getNext();
                }
                tempObj = tempObj.getNext();
            }

        }
        set._head = newHead;
        return set;
    }
}
