import java.util.HashMap;
import java.util.Scanner;

public class LagerSaldo {
    private long saldo = 0; //use double in case of huge numbers
    private static HashMap <String,comandoInstruccion> instructions = new HashMap<>();
    public static void main(String[] args) {
        LagerSaldo lager = new LagerSaldo();
        System.out.println("LagerSaldo:\n----------");
        Scanner s = new Scanner(System.in);
        String nextInstruction = s.next();
        instructions.put("S",new Sell());

        while (nextInstruction.equals("quit") == false) { //checks if input is valid and feeds it to the logic in such case.
            if (lager.testValidity(nextInstruction)) {
                System.out.println(lager.logic(nextInstruction));
            } else {
                System.out.println("Input not valid");
            }
            nextInstruction = s.next();
        }

    }
    public void setSaldo(long newSaldo){
        this.saldo = newSaldo;
    }
    public long getSaldo(){
        return this.saldo;
    }

    public String logic(String input) { //computes logic and returns Strings (which can be ignored if running in a webapp)
        char letter = input.charAt(0);
        long value = 0;
        if (letter != 'L')
            value = Long.parseLong(input.substring(1));

        comandoInstruccion comando = instructions.get(String.valueOf(letter));
        return comando.instruction(value,this);
    }

    public boolean testValidity(String input) {
        char first = input.charAt(0);
        String rest = input.substring(1);
        if (first != 'L' && first != 'I' && first != 'S') //checks if first char corresponds to an instruction
            return false;
        if ((first == 'S' || first == 'I') && input.length() == 1) //checks if S or I are provided alone
            return false;
        if (first != 'L' && !rest.matches("^[0-9]*$")) //checks if the following chars are a positive number if instruction is not L
            return false;
        if (first == 'L' && input.length() > 1) //checks that input is only 1 char if instruction equals L
            return false;
        return true; //passes the input test otherwhise.
    }

}
class Sell implements comandoInstruccion{
    public String instruction(long input, LagerSaldo saldo){
        saldo.setSaldo(saldo.getSaldo() + input);
        return "Sale completed succesfully";
    }
}

interface comandoInstruccion {

    String instruction( long input, LagerSaldo saldo);
}
