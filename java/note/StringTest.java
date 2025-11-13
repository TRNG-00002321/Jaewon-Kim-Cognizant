package note;

public class StringTest{
    public static void main(String[] args) {
        String hello = " Hello Word! ";
        System.out.println(hello.toUpperCase());
        System.out.println(hello.toLowerCase());
        System.out.println(hello.charAt(1));
        System.out.println(hello.concat(hello));
        System.out.println(hello.equals("False"));
        System.out.println(hello.equalsIgnoreCase(" HELLO WORLD! "));
        System.out.println(hello.length());
        System.out.println(hello.replace('l', 'p'));
        System.out.println(hello.trim());

        StringBuilder builder = new StringBuilder("Hello World! BUILDER");
        System.out.println(builder.append("String").toString());
        System.out.println(builder.insert(3, " N E W ").toString());
        System.out.println(builder.deleteCharAt(4).toString());
        builder.setCharAt(4, 'w');
        System.out.println(builder.toString());
        System.out.println(builder.substring(3));
    }
}