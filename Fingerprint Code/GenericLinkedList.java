import java.util.NoSuchElementException;
// by Roshni and Rachel
public class GenericLinkedList<T>
{
	T data;
	Node<T> head;
	public static class Node<T>
	{
		private T data;
		private Node<T> next;
		public Node()
		{
			data = null;
			next = null;
		}
		public Node(T n)
		{
			data = n;
			next = null;
		}
		public Node(T n, Node<T> nextNode)
		{
			data = n;
			next = nextNode;
		}
	}
	public GenericLinkedList()
	{
		head = null;
	}
	public GenericLinkedList(T n)
	{
		head = new Node<T>(n);
	}
	public static String author()
	{
		return "Roshni Srikanth and Rachel Alwan";
	}
	public boolean isEmpty()
	{
		if(head == null)
		{
			return true;
		}
		return false;
	}
	public int size()
	{
		int i = 1;
		if(isEmpty())
		{
			return 0;
		}
		else
		{
			Node<T> currentNode = head;
			while(currentNode.next != null)
			{
				i++;
				currentNode = currentNode.next;
			}
		}
		return i; 
	}
	
	public String toString()
	{
		String i = "";
		if(isEmpty())
		{
			return "null";
		}
		else
		{
			Node<T> currentNode = head;
			T val = currentNode.data;
			i+= val.toString();
			i+=" --> ";
			currentNode = currentNode.next;
			while(currentNode != null)
			{
				val = currentNode.data;
				i+= val;
				i+=" --> ";
				currentNode = currentNode.next;
			}
			i+="null";
			return i;
		}
	}
	
	public T get(int index) 
	{
		int size = size();

		if(index< 0 || index>= size || isEmpty())
		{
			throw new IndexOutOfBoundsException();
		}
		else
		{
			Node<T> currentNode = head;
			int i = 0;
			while(i<index)
			{
				currentNode = currentNode.next;
				i++;
			}
			return currentNode.data;
		}
		
	}
	
	public Node<T> getNode(int index)
	{
		int size = size(); 
		Node<T> currentNode = head;
		if(index< 0 || index>= size|| isEmpty())
		{
			throw new IndexOutOfBoundsException();
		}
		else
		{
			int i = 0;
			while(i<index)
			{
				currentNode = currentNode.next;
				i++;
			}
		}
		return currentNode;
	}
	
	public boolean contains(T n)
	{
		if(isEmpty())
		{
			return false;
		}
		int size = size();
		for(int i  = 0; i< size; i++)
		{
			if(n == get(i))
			{
				return true;
			}
		}
		return false;
	}
	public void add(T n)
	{
		int size = size();
		if(isEmpty())
		{
			head = new Node<T>(n);
		}
		else 
		{
			Node<T> newNode = new Node<T>(n);
			Node<T> lastNode = getNode(size-1);
			lastNode.next = newNode;
		}
		
		
	}
	// adds before the node
	public void add(int index, T n) 
	{
		int size = size();
		if((index<0 || index>=size) && index !=0)
		{
			throw new IndexOutOfBoundsException();
		}
		if(isEmpty() && index == 0)
		{
			add(n);
		}
		else if( index>0 && index<size-1)
		{
			Node<T> newNode = new Node<T>(n);
			Node<T> previousNode = getNode(index);
			Node<T> nextNode = getNode(index+1);
			previousNode.next = newNode;
			newNode.next = nextNode;
		}
		else if (index == 0)
		{
			Node<T> newNode = new Node<T>(n);
			Node<T> currentNode = head;
			newNode.next = currentNode;
			head = newNode;
		}
	}
	public Node<T> getHead() {
		return head;
	}
	public void setHead(Node<T> head2) {
		this.head = head2;
	}
	public int indexOf(T num)
	{
		if(!contains(num))
		{
			return -1;
		}
		else
		{
			Node<T> currentNode = head;
			int i = 0;
			while(currentNode.data != num)
			{
				currentNode = currentNode.next;
				i++;
			}
			return i;
		}
		
	}
	public T remove()
	{
		if(isEmpty())
		{
			throw new NoSuchElementException();
		}
		T toReturn = head.data;
		head = head.next;
		return toReturn;
	}
	public T remove(int index)
	{
		int size = size();
		if(isEmpty() || index<0 || index>=size)
		{
			throw new IndexOutOfBoundsException();
		}
		if(index>0&& index< size-1)
		{
			Node<T> previousNode = getNode(index-1);
			Node<T> nextNode = getNode(index+1);
			previousNode.next = nextNode;
			return get(index);
		}
		else if(index == 0)
		{
			remove();
		}
		else if(index == size-1)
		{
			Node<T> previousNode = getNode(index-1);
			previousNode.next = null;
		}
		return get(index);
	}
	
	public T getData(Node<T> currentNode)
	{
		return currentNode.data;
	}
	public Node<T> getNext(Node<T> currentNode)
	{
		return currentNode.next;
	}
	public void setData(Node<T> currentNode, T val)
	{
		currentNode.data = val;
	}
	public void setNext(Node<T> currentNode, Node<T> nextNode)
	{
		currentNode.next = nextNode;
	}
}
