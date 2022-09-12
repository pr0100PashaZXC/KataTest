import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String [] args){

        String input = "";

        Scanner inputStr = new Scanner(System.in);
        System.out.println("Введите выражение:");
        input = inputStr.nextLine();
        input.toUpperCase(Locale.ROOT);

        System.out.println("Результат:" + calc(input));

    }
    public static int romanToInt(String input) {
        int result=0;
        char roman[]=input.toCharArray();
        for(int i=0;i<roman.length;i++)
        {
            if(roman[i]=='I') result+=1;

            if(roman[i]=='V')
            {
                if (i != 0 && roman [i-1] == 'I') result += 4-1;
                else result+=5;
            }

            if(roman[i]=='X')
            {
                if(i!=0 && roman[i-1]=='I') result+=9-1;
                else result+=10;
            }

            if(roman[i]=='L')
            {
                if(i!=0 &&roman[i-1]=='X') result+=40-10;
                else result+=50;
            }

            if(roman[i]=='C')
            {
                if(i!=0 && roman[i-1]=='X') result+=90-10;
                else result+=100;
            }
        }
        return result;
    }

    public static String IntToRom(int arabicNum){
        StringBuffer result = new StringBuffer("");

        int c1 = arabicNum / 100;
        result.append(C(c1));
        int c2 = arabicNum % 100;

        int l1 = c2 / 50;
        result.append(L(l1));
        int l2 = c2 % 50;


        int x1 = l2 / 10;
        result.append(X(x1));
        int x2 = l2 % 10;

        result.append(I(x2));
        return result.toString();

    }

    private static String C(int input) {

        if ((input != 0) && (input < 2)) {
            StringBuffer result = new StringBuffer("");
            int i = 0;
            while (i < input) {
                result.append("C");
                i++;
            }
            return result.toString();
        }
        else return "";
    }
    private static String L(int input) {
        if (input == 4) return "XC";
        else if(input == 0) return "";
        else return "L";
    }
    private static String X(int input) {
        if (input == 4) return "XL";
        else if ((input != 0) && (input < 4)) {
            StringBuffer result = new StringBuffer("");
            int i = 0;
            while (i < input) {
                result.append("X");
                i++;
            }
            return result.toString();
        }
        else return "";
    }

    private static String I(int input) {
        String[] romanNum = {
                "",
                "I",
                "II",
                "III",
                "IV",
                "V",
                "VI",
                "VII",
                "VIII",
                "IX"
        };
        return romanNum[input];
    }
    public static String calc(String input){
        int resultNumA = 0;
        int resultNumB = 0;
        int result = 0;
        String answer = "0";
        boolean isRomanNum = false;

        if (input.isEmpty()) {
            throw new RuntimeException("Вы ничего не ввели. Попробуйте снова.");
        };
        String[] inpData = input.split(" ");

        if(inpData.length == 0){
            throw new RuntimeException("Выражение введено слитно. Вводите элементы выражения через пробел(Например: 2 + 3).");

        }

        if(inpData.length > 3){
            throw new RuntimeException("Введено более двух операндов.");
        }


        try {
            resultNumA = Integer.parseInt(inpData[0]);
            resultNumB = Integer.parseInt(inpData[2]);
        }

        catch (NumberFormatException r){
            if((inpData[0].contains("I")||
                    inpData[0].contains("V")||
                    inpData[0].contains("X")||
                    inpData[0].contains("L")||
                    inpData[0].contains("C"))&&
                    (inpData[2].contains("I")||
                            inpData[2].contains("V")||
                            inpData[2].contains("X")||
                            inpData[2].contains("L")||
                            inpData[2].contains("C"))){

                isRomanNum = true;
                resultNumA = romanToInt(inpData[0]);
                resultNumB = romanToInt(inpData[2]);
            }
            else{
                throw new RuntimeException("неправильно введены операнды. одновеременно могут использоваться целые, только арабские или только римские цифры через пробел");
            }
        }

        //System.out.println(resultNumA);
        //System.out.println(resultNumB);


        if(resultNumA > 10 || resultNumA < 1 || resultNumB > 10 || resultNumB < 1){
            throw new RuntimeException("Введеные вами числа больше 10 или меньше 1. Попробуйте снова");
        }

        String operation = inpData[1];
        switch (operation){
            case "+":
                result = resultNumA + resultNumB;
                break;
            case "-":
                result = resultNumA - resultNumB;
                break;
            case "*":
                result = resultNumA * resultNumB;
                break;
            case "/":
                result = resultNumA / resultNumB;
                break;
            default: throw new RuntimeException("Оперция не распознана. Повторите снова(Вы можете использовать только +, -, *, /).");
        }
        if (result <= 0 && isRomanNum){
            throw new RuntimeException(" Результат вычисления не может быть меньше нуля, если вы используете римские цифры");
        }
        if(isRomanNum) answer = IntToRom(result);
        else answer = Integer.toString(result);

        return answer;
    }
}
