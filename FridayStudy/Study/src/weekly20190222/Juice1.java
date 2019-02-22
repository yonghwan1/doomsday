package weekly20190222;


import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

class juice1 {
    public static class Person{
        int banana;
        int strawberry;
        int kiwi;
        Person(int ba, int st, int ki) {
            this.banana = ba;
            this.strawberry =st;
            this.kiwi = ki;
        }
    }
    static int N;
    static int MAX_LITER = 10000;
      
    static ArrayList<Person> people = new ArrayList<Person>();
      
    public static int solution() {
        int ret = 0;
        int maxCnt = 0;
        int banana, strawberry, kiwi;
        for (int i = 0; i < N; i++) {
            banana = people.get(i).banana;
            for (int j = 0; j < N; j++) {
                strawberry = people.get(j).strawberry;
                kiwi = MAX_LITER - (banana + strawberry);             
                if (kiwi >= 0) {       
                    maxCnt = 0;
                    for (int k = 0; k < N; k++) {
                        if (banana >= people.get(k).banana && strawberry >= people.get(k).strawberry && kiwi >= people.get(k).kiwi) {
                            maxCnt++;
                        }
                    }
                    ret = Math.max(ret,  maxCnt);
                }
            }
        }
        return ret;
    }
    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("res/sample_input_juice.txt"));
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        while (cases > 0) {
            cases--;
            N = sc.nextInt();
            people.clear();
            for (int i = 1; i <= N; i++) {
                people.add(new Person(sc.nextInt(), sc.nextInt(), sc.nextInt()));
            }         
            System.out.println(solution());       
        }
        sc.close();
    }
}