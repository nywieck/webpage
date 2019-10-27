/** test specific funtionality of sorted int list */

public class SortedIntListTest {

	public static void main(String[] args) {
		SortedIntList list1 = new SortedIntList();
		System.out.println("[ LIST 1 ]: constructor default capacity and allows duplicates");
		System.out.println("toString: " + list1.toString());
		System.out.println("size method test [0]: " + list1.size());
		System.out.println("isEmpty method test [true]: " + list1.isEmpty());
		System.out.println("getUnique method test [false]: " + list1.getUnique());
		list1.add(0);
		System.out.println(list1.toString() + " add 0");
		list1.add(-1);
		System.out.println(list1.toString() + " add -1");
		list1.add(2);
		System.out.println(list1.toString() + " add 2");
		list1.add(5);
		System.out.println(list1.toString() + " add 5");
		list1.add(5);
		System.out.println(list1.toString() + " add 5 again, duplicates allowed");
		list1.add(4);
		System.out.println(list1.toString() + " add 4");
		System.out.println("isEmpty method test [false]: " + list1.isEmpty());
		System.out.println("size method test [6]: " + list1.size());
		
		SortedIntList list2 = new SortedIntList(true);
		System.out.println("\n[ LIST 2 ]: constructor default capacity and does not allow duplicates");
		System.out.println("toString: " + list2.toString());
		System.out.println("size method test [0]: " + list2.size());
		System.out.println("isEmpty method test [true]: " + list2.isEmpty());
		System.out.println("getUnique method test [true]: " + list2.getUnique());
		list2.add(0);
		System.out.println(list2.toString() + " add 0");
		list2.add(-1);
		System.out.println(list2.toString() + " add -1");
		list2.add(2);
		System.out.println(list2.toString() + " add 2");
		list2.add(2);
		System.out.println(list2.toString() + " try to add 2, duplicates not allowed");
		list2.add(-10);
		System.out.println(list2.toString() + " add -10");
		list2.remove(3);
		System.out.println(list2.toString() + " remove index 3, value 2 removed");
		list2.add(2);
		System.out.println(list2.toString() + " add 2 again, because was removed is not a duplicate");
		list2.add(2);
		System.out.println(list2.toString() + " try to add 2 again, duplicates not allowed");
		System.out.println("min method test [-10]: " + list2.min());
		System.out.println("max method test [2]: " + list2.max());
		System.out.println("size method test [4]: " + list2.size());
		System.out.println("isEmpty method test [false]: " + list2.isEmpty());
		
		SortedIntList list3 = new SortedIntList(5);
		System.out.println("\n[ LIST 3 ]: constructor capacity set to 5 and allows duplicates");
		System.out.println("toString: " + list3.toString());
		System.out.println("size method test [0]: " + list3.size());
		System.out.println("isEmpty method test [true]: " + list3.isEmpty());
		System.out.println("getUnique method test [false]: " + list3.getUnique());
		list3.add(12);
		System.out.println(list3.toString() + " add 12");
		list3.add(-178);
		System.out.println(list3.toString() + " add -178");
		list3.add(230487);
		System.out.println(list3.toString() + " add 230487");
		list3.add(178);
		System.out.println(list3.toString() + " add 178");
		list3.add(12);
		System.out.println(list3.toString() + " add 12, duplicates allowed");
		System.out.println("size method test [5]: " + list3.size());
		System.out.println("isEmpty method test [false]: " + list3.isEmpty());
		System.out.println("indexOf method test for 178 [3]: " + list3.indexOf(178));
		System.out.println("get method test for index 3 [178]: " + list3.get(3));
		System.out.println("count method test for 12 [2]: " + list3.count(12));
		System.out.println("count method test for 100 [0]: " + list3.count(100));
		
		SortedIntList list4 = new SortedIntList(true, 20);
		System.out.println("\n[ LIST 4 ]: constructor capacity set to 20 and does not allow duplicates");
		System.out.println("toString: " + list4.toString());
		System.out.println("size method test [0]: " + list4.size());
		System.out.println("isEmpty method test [true]: " + list4.isEmpty());
		System.out.println("getUnique method test [true]: " + list4.getUnique());
		list4.add(0);
		System.out.println(list4.toString() + " add 0");
		list4.add(0);
		System.out.println(list4.toString() + " try to add 0, duplicates not allowed");
		list4.add(2);
		System.out.println(list4.toString() + " add 2");
		list4.add(-1);
		System.out.println(list4.toString() + " add -1");
		list4.setUnique(false);
		System.out.println("setUnique method test [false], allow duplicates: " + list4.getUnique());
		list4.add(0);
		System.out.println(list4.toString() + " add 0, now duplicates allowed");
		list4.add(0);
		System.out.println(list4.toString() + " add 0 again, now duplicates allowed");
		System.out.println("count method test for 0 [3], three 0's because duplicates allowed: " + list4.count(0));
		list4.setUnique(true);
		System.out.println("setUnique method test [true], duplicates not allowed anymore: " + list4.getUnique());
		System.out.println(list4.toString());
		System.out.println("count method test for 0 [1], duplicate 0's removed when unique set to true: " + list4.count(0));
		list4.add(0);
		System.out.println(list4.toString() + " try to add 0 again, duplicates not allowed anymore");
		System.out.println("size method test [3]: " + list4.size());
		System.out.println("isEmpty method test [false]: " + list4.isEmpty());
	}
}