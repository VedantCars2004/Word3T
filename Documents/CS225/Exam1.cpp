// THIS CONTAINS THE FUNCTIONS FROM THE LIST CLASS THAT CAN BE ASKED ON EXAM 1

// LINKED LIST FUNCTIONS

// insertAtFront(const T& t) {} -> will insert a new node at front with the data provided
void List<T>::insertAtFront(const T& t) {
    ListNode* tmp = new ListNode(t); // create a new node tmp with the data passed
    tmp->next = head_; // set the next of the head at the current head
    head_ = tmp; // set head to point at tmp since that is our new head
}

// _index(unsigned index) returns the value at the index provided
template <typename T> 
typename List<T>::ListNode*& List<T>::_index(unsigned index) {
    return _index(index, head);
}

// _index(unsigned index, ListNode*& root) is a recursive function that takes in the root we are starting
// with and the index that we need to find.
template <typename T>
typename List<T>::ListNode*& List<T>::_index(unsigned index, ListNode*& root) {
    if (root == nullptr || index == 0) { // if we have found the index (when its 0), then we return the root
     // which is also a reference to a pointer to a node in a linked list
        return root;
    }
    return _index(index - 1, root->next); // recursive call if we have not found yet with index - 1
}

// _index(unsigned index, ListNode*& root) is a iterative function that takes in the root we are starting
// with and the index that we need to find.
template <typename T>
typename List<T>::ListNode*& List<T>::_index(unsigned index, ListNode*& root) {
    if (index == 0) {
        return root; // if index is 0, then we already at the root and we should return it
    } else {
        ListNode* curr = root; // else we start the traversal from the root node
        for (unsigned int = 0; i < index - 1; i++) {
            curr = curr->next; // we move it forward index times and you will be at the node you need
        }   
        return curr->next;
    } 
} // which solution is better? large lists-> iterative, small lists-> any one works

// insert(data, index) function inserts the data at the given idnex
LinkedList::insert(data, index) {
    ListNode*& curr = _index(index); // first we can get a reference to a pointer at the given index
    // this way we can access and change what is at the value at the index
    ListNode* tmp = new ListNode(data); // we can create a new node with the data given, keep it as ptr
    tmp->next = curr; // set the tmp next pointer to the curr value at the index
    curr = tmp; // move curr ptr to point at the new tmp node
}

// operator[] returns the value at the index
template <typename T>
T& List<T>::operator[] (unsigned index) {
    ListNode*& value = _index(index);
    return value->data;
} // O(n) because it needs to find the value at the index, the _index() function is O(n)

// remove(ListNode*& node) function removes the node at the passed value, which is a ref to a pointer
// this will return the data of the deleted node
template <typename T>
T List<T>::remove(ListNode*& node) {
    ListNode* tmp = node; // have a pointer that points to the node that we have to remove
    node = node->next; // move the node to point to the next value after, which will replace it
    T data = tmp->data; // we need to access the data before deleting it and store it, so we don't lose it
    delete tmp; // we need to delete this tmp node, that is the whole purpose of the function
    return data; // this is our return value
} // this whole function can be ran in O(1)

// remove(T& data) {} runs in O(n) time because if we are given the data instead of a ref pointer
// then we have to find the place where the data actually exists instead of having the actual node pointer

// QUESTIONS
// if we can access any node with a pointer such as line 64, why do we need to use the index function?
    // is it because what's passed to us is a ref to a pointer node instead of an unsigned index?