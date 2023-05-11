import java.util.Random;

public class Main {
    public static int[] generator(){
        int [] a = new int[10000];
        Random r = new Random();
        for (int i = 0; i<10000; i++){
            a[i] = r.nextInt();
        }
        return a;
    }
    public static int[] picker(int[] arr, int x){
        Random r = new Random();
        int[] a = new int[x];
        for (int i = 0; i<x; i++){
            a[i] = arr[r.nextInt(arr.length)];
        }
        return a;
    }

    public static void main(String[] args) {
        int [] arr = generator();
        AATree tree = new AATree();
        int [] insertRecCount = new int[arr.length];
        int [] insertTime = new int[arr.length];
        int [] findRecCount = new int[100];
        long [] findTime = new long[100];
        int [] delRecCount = new int[1000];
        long [] delTime = new long[1000];
        for (int i = 0; i<arr.length; i++){
            int [] temp = tree.insert(arr[i]);
            insertRecCount[i] = temp[0];
            insertTime[i] = temp[1];
        }
        int[] fArr = picker(arr, 100);
        for (int i = 0; i<fArr.length; i++) {
            tree.findRecCount = 0;
            long s = System.nanoTime();
            tree.contains(tree.root, fArr[i]);
            long f = System.nanoTime();
            findRecCount[i] = tree.findRecCount;
            findTime[i] = f-s;
        }
        int[] dArr = picker(arr, 1000);
        for (int i = 0; i<dArr.length; i++) {
            long s = System.nanoTime();
            delRecCount[i] = tree.delete(dArr[i]);
            long f = System.nanoTime();
            delTime[i] = f-s;
        }
        for (int i = 0; i<insertTime.length; i++) {
            System.out.print(insertTime[i]);
            System.out.print(" ");
        }
        System.out.println();
        for (int i = 0; i<insertRecCount.length; i++) {
            System.out.print(insertRecCount[i]*15);
            System.out.print(" ");
        }
        System.out.println();
        for (int i = 0; i<findTime.length; i++) {
            System.out.print(findTime[i]);
            System.out.print(" ");
        }
        System.out.println();
        for (int i = 0; i<findRecCount.length; i++) {
            System.out.print(findRecCount[i]*3);
            System.out.print(" ");
        }
        System.out.println();
        for (int i = 0; i<delTime.length; i++) {
            System.out.print(delTime[i]);
            System.out.print(" ");
        }
        System.out.println();
        for (int i = 0; i<delRecCount.length; i++) {
            System.out.print(delRecCount[i]*50);
            System.out.print(" ");
        }
    }
}
