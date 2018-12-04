import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProblemC {
    static float topSpeed = 0;
    static int n;
    static ArrayList<Stage> stages;

    public static void main(String[] args) throws FileNotFoundException {
        //File f = new File("src/input3.txt");
        //Scanner sc = new Scanner(f);
        Scanner sc = new Scanner(System.in);
        n = Integer.parseInt(sc.nextLine());
        stages = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            Stage s = new Stage(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
            if (s.M0 <= 10000) //if the mass of the stage is already over 10000 ignore it
                stages.add(s);
        }
        n = stages.size(); //recalculate total number of stages
        ProblemC.topSpeed = stages.get(0).velocityIncrement(0); //sets topvelocity case there is only one stage
        for (int i = 0; i < n; i++) {
            Stage firstStage = stages.get(i);
            firstStage.recursiveRocket(i + 1, 0);
        }
        System.out.println(Math.round(ProblemC.topSpeed));
    }
}

class Stage {
    public float S; //mass of Stage
    public float L; //mass of fuel
    public float T; //thrust
    public float C; //fuel consumption
    public float M0; //total initial mass of this stage
    static float MAX_UNSIGNED_INT = (float) 4294967295.0;

    public Stage(int s, int l, int t, int c) {
        S = checkUnsigned((float) s);
        L = checkUnsigned((float) l);
        T = checkUnsigned((float) t);
        C = checkUnsigned((float) c);
        M0 = s + l;
    }

    public float checkUnsigned(float number) {
        //transforms input to unsigned using 2's complement rule
        while (number < 0) {
            number += MAX_UNSIGNED_INT + 1;
        }
        return number;
    }

    public float velocityIncrement(float extraMass) {
        return velocityEquation(L / C, extraMass) - velocityEquation(0, extraMass);
    }

    private float velocityEquation(float t, float extraMass) {
        return (float) (-T / C * Math.log((double) Math.abs(M0 + extraMass - C * t)) - 9.8 * t);
    }

    public float recursiveRocket(int index, float actualMass) {
        if (this.M0 + actualMass <= 10000) {
            for (int j = index; j < ProblemC.n; j++) {
                Stage nextStage = ProblemC.stages.get(j);
                float accumulatedSpeed = this.velocityIncrement(actualMass) +
                        nextStage.recursiveRocket(j + 1, actualMass + this.M0);
                if (accumulatedSpeed > ProblemC.topSpeed)
                    ProblemC.topSpeed = accumulatedSpeed;
                return accumulatedSpeed;
            }

        } else {
            return 0;
        }
        return 0;
    }
}