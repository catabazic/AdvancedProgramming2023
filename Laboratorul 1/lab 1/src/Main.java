public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        String[] languages ={"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        int n = (int) (Math.random() * 1_000_000);
        n *=3;
        n += Integer.parseInt("10101", 2);
        n +=  Integer.parseInt("FF",16);
        n *= 4;
        int result;
        while(true){
            result= String.valueOf(n).length();
            if(result<10){
                break;
            }
        }
        System.out.println("Willy-nilly, this semester I will learn " + languages[result]);
    }
}