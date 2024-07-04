package daelim.spring_ch06;

public class CalculatorExample {

    public static void main(String[] args) {
//        long start1 = System.currentTimeMillis();
//        ImpleCalculator impleCalculator = new ImpleCalculator();
//        impleCalculator.factorial(4);
//        long end1 = System.currentTimeMillis();
//        System.out.printf("ImpleCalculator.factorial(%d) 실행시간 = %d\n", 4, (end1-start1));
//
//        long start2 = System.currentTimeMillis();
//        RecCalculator recCalculator = new RecCalculator();
//        recCalculator.factorial(4);
//        long end2 = System.currentTimeMillis();
//        System.out.printf("RecCalculator.factorial(%d) 실행시간 = %d\n", 4, (end2-start2));

        ExeTimeCalculator calculator1 = new ExeTimeCalculator(new ImpleCalculator());
        long result1 = calculator1.factorial(4);
        ExeTimeCalculator calculator2 = new ExeTimeCalculator(new RecCalculator());
        long result2 = calculator2.factorial(4);

        System.out.println("calculator1 : " + result1);
        System.out.println("calculator2 : " + result2);
    }

}
