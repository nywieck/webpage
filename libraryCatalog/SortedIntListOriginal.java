import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * @author Nathaniel Wieck
 * @version 28 MAY 2019
 * 
 * program implements a collection that maintains a list of integers in sorted order and
 * has an option to specify no duplicates allowed.
 */
public class SortedIntList {

	private boolean unique;
	private int[] list;
	private int capacity;
	private int size;
	
	/**
	 * default constructor sets capacity to 10 and unique to false
	 */
	public SortedIntList() {
		this(10);
	}
	
	/**
	 * constructor sets capacity to parameter and unique to false
	 */
	public SortedIntList(int capacity) {
		this(false, capacity);
	}
	
	/**
	 * constructor sets capacity to default 10 and unique to parameter
	 */
	public SortedIntList(boolean unique) {
		this(unique, 10);
	}
	
	/**
	 * constructor sets capacity and unique to parameter
	 * to reduce redundancy, every other constructor eventually calls this constructor to set private fields initially
	 */
	public SortedIntList(boolean unique, int capacity) {
		if(capacity <= 0) {
			throw new IllegalArgumentException("Capacity must be greater than 0.");
		}
		this.list = new int[capacity];
		this.capacity = capacity;
		this.size = 0;
		setUnique(unique);
	}
	
	/**
	 * adds the given value from parameter to the list in sorted order by using binarySearch
	 * takes care of several hedge cases like no elements or a full list
	 * if value already exists and unique is true, then not added to list
	 * if value doesn't exist, convert to correct index and add to list
	 */
	public void add(int value) {
		if(this.size == 0) {
			this.list[0] = value;
			this.size++;
		} else if(this.size == this.capacity) {
			throw new IllegalArgumentException("The list has " + this.size + " elements and is full.");
		} else {
			int iFlag = Arrays.binarySearch(this.list,  0, this.size, value);
			if (iFlag < 0 || this.unique == false) {
				if(iFlag < 0) {
					iFlag = Math.abs(iFlag) - 1;
				}
				for(int i = this.size; i > iFlag; i--) {
					this.list[i] = this.list[i - 1];
				}
				this.list[iFlag + 1] = this.list[iFlag];
				this.list[iFlag] = value;
				this.size++;
			}
		}
	}
	
	/**
	 * removes the value at the given index from parameter
	 * pre-condition checks, and resets size to 0 and list to null if all elements removed
	 */
	public void remove(int index) {
		if(this.size - 1 < index) {
			throw new IndexOutOfBoundsException("The index cannot be greater than the size of the list.");
		} else if(index < 0) {
			throw new IndexOutOfBoundsException("The index cannot be negative.");
		} else if(this.size == 1) {
			this.size = 0;
			this.list = null;
		} else {
			for(int i = index; i < this.size; i++) {
				this.list[i] = this.list[i + 1];
			}
			this.size--;
		}
	}
	
	/**
	 * gets the value at the given index from parameter, pre-condition checks
	 */
	public int get(int index) {
		if(this.size - 1 < index) {
			throw new IndexOutOfBoundsException("The index cannot be greater than the size of the list.");
		} else if(index < 0) {
			throw new IndexOutOfBoundsException("The index cannot be negative.");
		} 
		
		return list[index];
	}
	
	/**
	 * gets the size of the list
	 */
	public int size() {
		
		return this.size;
	}

	/**
	 * gets if list is empty or not
	 */
	public boolean isEmpty() {
		if(this.size == 0) {
			
			return true;
		} else {
			
			return false;
		}
	}

	/**
	 * gets the index of given value from parameter, returns -1 if doesn't exist
	 */
	public int indexOf(int value) {
		int index = Arrays.binarySearch(this.list, 0, this.size, value);
		if(index >= 0) {
			
			return index;
		} else {
			
			return -1;
		}
	}
	
	public int max() {
		if(this.size == 0) {
			throw new NoSuchElementException("The list has 0 elements.");
		} else {
			
			return this.list[this.size - 1];
		}
	}
	
	/**
	 * gets the minimum value in the list, pre-condition check
	 */
	public int min() {
		if(this.size == 0) {
			throw new NoSuchElementException("The list has 0 elements.");
		} else {
			
			return this.list[0];
		}
	}
	
	/**
	 * gets number of elements that exist in list of given value from parameter
	 * uses indexOf method to start at most efficient location
	 * moves up and down list from starting index position to count total number of elements in list
	 */
	public int count(int value) {
		int result = 0;
		int index = indexOf(value);
		if (index > -1) {
			for(int i = index; i >= 0; i--) {
				if (this.list[i] == value) {
					result++;
				} else {
					break;
				}
			}
			for(int i = index+1; i < size; i++) {
				if (this.list[i] == value) {
					result++;
				} else {
					break;
				}
			}
		}
		
		return result;
	}
	
	/**
	 * gets unique setting of list
	 */
	public boolean getUnique() {
		
		return this.unique;
	}
	
	/**
	 * sets unique setting of list and if set to true, removes any duplicates that already exist
	 */
	public void setUnique(boolean unique) {
		if(this.size > 0) {
			if(this.unique == false && unique) {
				int curr = 0;
				for(int next = 1; next < this.size; next++) {
					if(this.list[next] != this.list[curr]) {
						this.list[curr + 1] = this.list[next];
						curr++;
					}
				}
				this.size = curr + 1;
			}
		}
		this.unique = unique;
	}
	
	/**
	 * String version of class
	 */
	public String toString() {
		String result = "";
		if(this.size == 0) {
			result += "The list has 0 elements.";
		} else {
			result = "[" + this.list[0];
			for(int i = 1; i < this.size; i++) {
				result += ", " + this.list[i];
			}
			result += "]";
		}
		
		return result;
	}
}