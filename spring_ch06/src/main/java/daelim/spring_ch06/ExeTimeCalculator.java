package daelim.spring_ch06;

public class ExeTimeCalculator implements Calculator{

    private Calculator delegate;

    public ExeTimeCalculator(Calculator calculator) {
        this.delegate = calculator;
    }

    @Override
    public long factorial(long num) {
        long start = System.currentTimeMillis();
        long result = delegate.factorial(num);
        long end = System.currentTimeMillis();
        System.out.printf("%s.factorial(%d) 실행시간 = %d\n", delegate.getClass().getSimpleName(), num, (end-start));
        return result;
    }
}
