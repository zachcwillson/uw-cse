
import java.util.*;

public class MyClient {

    public static void main(String[] args) {
        PriorityQueue testHeap;
        testHeap = new ThreeHeap();
        test1(testHeap);
        test2(testHeap);
        test3(testHeap);
    }
    
    public static void test1(PriorityQueue test) {
        test.insert(1.0);
        test.insert(2.0);
        boolean testOrder1 = test.deleteMin() == 1.0;
        System.out.println(testOrder1 + "- Passed deleteMin check 1");
        test.makeEmpty();
        test.insert(2.0);
        test.insert(1.0);
        boolean testOrder2 = test.deleteMin() == 1.0;
        System.out.println(testOrder2 + "- Passed deleteMin check 2");
        test.makeEmpty();
        try {
            test.deleteMin();
        } catch (Exception e) {
            System.out.println("Passed Exception check 1");
            try {
                test.findMin();
            } catch (Exception f) {
                System.out.println("Passed Exception check 2");
            }
        }
        List<Double> lis = new ArrayList<Double>();
        lis.add(3.0);
        lis.add(4.0);
        lis.add(5.5);
        lis.add(8.3);
        test.buildQueue(lis);
        if(test.deleteMin() == 3.0) {
            System.out.println("Passed buildQueue check 1");
        }
        test.buildQueue(lis);
        if(test.deleteMin() == 3.0) {
            System.out.println("Passed buildQueue check 2");
            if(test.size() == 3) {
                System.out.println("Passed size() check 1");
            }
            test.insert(3.0);
            if(test.size() == 4) {
                System.out.println("Passed size() check 2");
            }
            test.makeEmpty();
            if(test.size() == 0) {
                System.out.println("Passed makeEmpty() check");
            }
            if(test.isEmpty()) {
                System.out.println("Passed isEmpty() check");
            }
        }
    }
    
    public static void test2(PriorityQueue test) {
        double[] dubs = new double[100];
        for(int i = 40; i > 0; i--) {
            test.insert(i);
            dubs[i - 1] = i;
        }
        System.out.println(test.toString());
        boolean[] tests = new boolean[100];
        //System.out.println(Arrays.toString(tests));
        for(int i = 0; i < 40; i++) {
            System.out.println(test.deleteMin());
            //test.insert(i);
        }
        //System.out.println(Arrays.toString(tests));
    }

    public static void test3(PriorityQueue test) {
        List<Double> lis = new ArrayList<Double>();
        lis.add(0.77);
        lis.add(0.61);
        lis.add(2.22);
        lis.add(1.84);
        lis.add(5.0);
        lis.add(2.32);
        lis.add(1.92);
        lis.add(0.22);
        lis.add(0.01);
        lis.add(1.15);
        lis.add(1.55);
        lis.add(0.97);
        lis.add(1.15);
        System.out.println("List: " + lis);
        test.buildQueue(lis);
        System.out.println("Heap: " + test.toString());
        double test1 = test.deleteMin();
        boolean t1 = test1 == 0.01;
        System.out.println(test1 + " - should be 0.01 -------------------------------------------------------------------------------------------------------Test 1: " + t1);
        System.out.println("Heap: " + test.toString());
        double test2 = test.deleteMin();
        boolean t2 = test2 == 0.22;
        System.out.println(test2 + " - should be 0.22 -------------------------------------------------------------------------------------------------------Test 2: " + t2);
        System.out.println("Heap: " + test.toString());
        double test3 = test.deleteMin();
        boolean t3 = test3 == 0.61;
        System.out.println(test3 + " - should be 0.61 -------------------------------------------------------------------------------------------------------Test 3: " + t3);
        System.out.println("Heap: " + test.toString());
        double test4 = test.deleteMin();
        boolean t4 = test4 == 0.77;
        System.out.println(test4 + " - should be 0.77 -------------------------------------------------------------------------------------------------------Test 4: " + t4);
        System.out.println("Heap: " + test.toString());
        double test5 = test.deleteMin();
        boolean t5 = test5 == 0.97;
        System.out.println(test5 + " - should be 0.97 -------------------------------------------------------------------------------------------------------Test 5: " + t5);
        System.out.println("Heap: " + test.toString());
        double test6 = test.deleteMin();
        boolean t6 = test6 == 1.15;
        System.out.println(test6 + " - should be 1.15 -------------------------------------------------------------------------------------------------------Test 6: " + t6);
        System.out.println("Heap: " + test.toString());
        double test7 = test.deleteMin();
        boolean t7 = test7 == 1.15;
        System.out.println(test7 + " - should be 1.15 -------------------------------------------------------------------------------------------------------Test 7: " + t7);
        System.out.println("Heap: " + test.toString());
        double test8 = test.deleteMin();
        boolean t8 = test8 == 1.55;
        System.out.println(test8 + " - should be 1.55 -------------------------------------------------------------------------------------------------------Test 8: " + t8);
        System.out.println("Heap: " + test.toString());
        double test9 = test.deleteMin();
        boolean t9 = test9 == 1.84;
        System.out.println(test9 + " - should be 1.84 -------------------------------------------------------------------------------------------------------Test 9: " + t9);
        System.out.println("Heap: " + test.toString());
        double test10 = test.deleteMin();
        boolean t10 = test10 == 1.92;
        System.out.println(test10 + " - should be 1.92 ------------------------------------------------------------------------------------------------------Test 10: " + t10);
        System.out.println("Heap: " + test.toString());
        double test11 = test.deleteMin();
        boolean t11 = test11 == 2.22;
        System.out.println(test11 + " - should be 2.22 ------------------------------------------------------------------------------------------------------Test 11: " + t11);
        System.out.println("Heap: " + test.toString());
        double test12 = test.deleteMin();
        boolean t12 = test12 == 2.32;
        System.out.println(test12 + " - should be 2.32 ----------------------------------------------------------------------------------------------------- Test 12: " + t12);
        System.out.println("Heap: " + test.toString());
        double test13 = test.deleteMin();
        boolean t13 = test13 == 5.0;
        System.out.println(t13);
    }

}
