import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws IOException {

        // 코드 축약
        System.out.println("Hello World!");

        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ir);

        String a1 = br.readLine();

        int a = Integer.parseInt(a1);

        // Optional을 대체할 nullable
        var o1 =  Optional.of(0);
        var o2 =  Optional.ofNullable(null);

        // 함수형 프로그래밍 지원
        // SAM
        foo(() -> {
            System.out.println("Hello World!");
        });

        // Switch case를 대체하는 when문
        switch (a) {
            case 1 -> System.out.println("1");
            case 2 -> System.out.println("2");
            default -> System.out.println("default");
        }

        Integer in = 1;

    }

    static void foo(ClickListener listener) {
        listener.onClick();
    }


    void foo (){
        int i,j;
        for (i = 0; i < 10; i++) {
            for (j = 0; j < 10; j++) {
                if (i == 5) {
                    break;
                }
            }
        }
    }
}


interface ClickListener {
    void onClick();
}
