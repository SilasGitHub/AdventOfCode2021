public class Main {
    public static void main(String[] args) {
        int test = Integer.MAX_VALUE;
        System.out.println(test);
        test++;
        System.out.println(test);
        int number1 = 1000000000;
        int number2 = 2000000000;
        byte num = Byte.MAX_VALUE;
        System.out.println("number1: " + number1 + " number2: " + number2 + " result: " + (number1 + number2));
        System.out.println("number1: " + number1 + " number2: " + number2 + " result: " + ((long) number1 + (long) number2));
        long numb = Long.MAX_VALUE;
        System.out.println(numb);
    }
}
