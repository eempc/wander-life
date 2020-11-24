import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        int[] myInts = new int[] { 7, 9, 2, 3, 4, 5, 8, 10, 15};
        IntStream meow = Arrays.stream(myInts);
        meow.filter(x -> x % 2 == 0).forEach(System.out::println);
        int[] onlyOddIntsIndices = IntStream.range(0, 5).filter(x -> myInts[x] % 2 != 0).toArray();
        for (int x : onlyOddIntsIndices) {
            System.out.println("index: " + x + " value of: " + myInts[x] + " with range 0 to 5");
        }
        System.out.println("------------");


        int[] o = new int[] { 11, 111, 1111 };
        int[] a = o;
        int[] c = new int[3];
        int[] clone = o.clone();
        int[] arrayCopied = new int[3];
        System.arraycopy(o, 0, arrayCopied, 0, o.length);

        for (int i = 0; i < 3; i++) {
            System.out.println("Original: " + o[i]);
            c[i] = o[i]; // assignment via iteration
            o[i]++; // modify original
            System.out.println("Original++: " + o[i]);
            System.out.println("Assignment copy: " + a[i]);
            System.out.println("ArrayCopy: " + arrayCopied[i]);
            System.out.println("Copied by iteration: " + c[i]);
            System.out.println("Cloned array: " + clone[i]);
        }

        int[] cat = new int[10];
        Arrays.fill(cat, 5);
        for (int x : cat) {
            System.out.println(x);
        }

        String[] pets = { "Cat", "Kitten", "Dog", "Puppy"};
        List<String> purr = Arrays.asList(pets);
        System.out.println(purr);

        int[] catLoaf = { 77, 33, 22, 100, 11, 0 };

        Arrays.sort(catLoaf);
        for (int kitty : catLoaf) {
            System.out.println(kitty);
        }
        int index = Arrays.binarySearch(catLoaf, 22);
        System.out.println("index of 22: " + index);

        int[][] original = { {0,0}, {1, 1}, {2, 2}, {3, 3}  };
        int[][] copy = new int[4][2];
        for (int i = 0; i < original.length; i++) {
            for (int j = 0; j < original[i].length; j++) {
                copy[i][j] = original[i][j];
            }
        }

        // Cna't copy by iteration for jagged array
        int[][] copy2 = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            copy2[i] = new int[original[i].length];

            for (int j = 0; j < original[i].length; j++) {
                copy2[i][j] = original[i][j];
            }
        }

        System.out.println("------------------");
        for (int[] meowwww : copy) {
            for (int meoww : meowwww) {
                System.out.println(meoww);
            }
        }

        System.out.println("------------------");
        for (int[] meowwww : copy2) {
            for (int meoww : meowwww) {
                System.out.println(meoww);
            }
        }

        Environment e = new Environment();


    }
}
