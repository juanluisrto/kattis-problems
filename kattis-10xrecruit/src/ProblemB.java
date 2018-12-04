import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ProblemB {
    public static void main(String[] args) throws FileNotFoundException {
        //File f = new File("src/input2.txt");
        //Scanner sc = new Scanner(f);
        Scanner sc = new Scanner(System.in);
        int problems = Integer.parseInt(sc.nextLine());
        String[] answers = new String[problems];
        //Import Data
        for (int k = 0; k < problems; k++) {
            Node mother = new Node();
            int n = Integer.parseInt(sc.nextLine());
            ArrayList<String> numbers = new ArrayList<>(n);
            for (int i = 0; i < n; i++)
                numbers.add(sc.nextLine());
            for (String number : numbers) {
                int[] phoneDigits = new int[number.length()];
                for (int i = 0; i < number.length(); i++) {
                    phoneDigits[i] = number.charAt(i) - '0';
                }
                boolean result = mother.recursiveTree(phoneDigits);
                answers[k] = result == true ? "NO" : "YES";
                if (result)
                    break;
            }
        }
        for (String a : answers) {
            System.out.println(a);
        }

    }
}


class Node {
    Node[] children;
    boolean lastnumber;

    public Node() {
        this.children = new Node[10];
    }

    boolean recursiveTree(int[] digits) {
        //checks if a phone number already finished here (but returns false if the number is duplicated)
        if (this.lastnumber)
            return (digits.length > 0)? true: false;
        //sets if current phone finishes here
        this.lastnumber = digits.length == 0;
        //checks if there is a conflict with a larger phone that already passed through this node
        if (this.lastnumber && this.childrenNotEmpty())
            return true;
        if (!this.lastnumber) {
            int[] restOfDigits = Arrays.copyOfRange(digits, 1, digits.length);
            Node node;
            if (children[digits[0]] == null) {
                node = new Node();
                children[digits[0]] = node;
            } else {
                node = children[digits[0]];
            }
            return node.recursiveTree(restOfDigits);
        } else {
            return false; //returns false if we indexed all digits without problems.
        }
    }
    boolean childrenNotEmpty(){
        boolean result = false;
        for (int i = 0; i < 10; i++){
            result = result || (this.children[i]!=null);
        }
        return result;
    }
}
