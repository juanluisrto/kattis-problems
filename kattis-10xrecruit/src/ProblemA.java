import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProblemA {

    public static void main(String[] args) throws FileNotFoundException {
        //File f = new File("src/input.txt");
        //Scanner sc = new Scanner(f);
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        ArrayList<Integer> modifiedPositions = new ArrayList<>();
        while (n != 0) {
            modifiedPositions.clear();
            int register = 0x0;
            for (int k = 0; k < n; k++) {
                String[] line = sc.nextLine().split(" ");
                String instruction = line[0];
                int i = Integer.parseInt(line[1]);
                int j = (line.length == 3) ? Integer.parseInt(line[2]) : 0;
                if (instruction.equals("SET")) {
                    register = register | 1 << i;
                    if(modifiedPositions.contains(i)==false)
                        modifiedPositions.add(i);
                } else if (instruction.equals("CLEAR")) {
                    register = register & ~(1 << i); //invert value from 0x0001 to 0x1110 and do AND
                    if(modifiedPositions.contains(i)==false)
                        modifiedPositions.add(i);
                } else {
                    if (instruction.equals("AND")) {
                        if (modifiedPositions.contains(i) && !modifiedPositions.contains(j) && (0x1 & (register >> i)) == 1)
                            modifiedPositions.remove(new Integer(i));
                        else if (modifiedPositions.contains(j) && !modifiedPositions.contains(i) && (0x1 & (register >> j)) == 0)
                            modifiedPositions.add(i);
                        int and = 0x1 & (register >> i & register >> j);                  // shifts and masks i and j, computes AND
                        register = and == 0x1 ? register | 1 << i : register & ~(1 << i); // does SET or CLEAR
                    } else if (instruction.equals("OR")) {
                        if (modifiedPositions.contains(i) && !modifiedPositions.contains(j) && (0x1 & (register >> i)) == 0)
                            modifiedPositions.remove(new Integer(i));
                        else if (modifiedPositions.contains(j) && !modifiedPositions.contains(i) && (0x1 & (register >> j)) == 1)
                            modifiedPositions.add(i);
                        int or = 0x1 & (register >> i | register >> j);                  // shifts and masks i and j, computes OR
                        register = or == 0x1 ? register | 1 << i : register & ~(1 << i); // does SET or CLEAR
                    }
                }
            }
            String output = Integer.toBinaryString(register);
            char[] answer = "????????????????????????????????".toCharArray();
            while (output.length() < 32)
                output = "0" + output;
            for (int bit : modifiedPositions) {
                answer[31 - bit] = output.charAt(31 - bit);
            }
            System.out.println(answer);
            n = Integer.parseInt(sc.nextLine());
        }
    }
}

