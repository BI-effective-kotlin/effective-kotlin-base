import org.jetbrains.annotations.Nullable;

public class Platform {
    String name;

    public Platform(String name) {
        this.name = name;
        int a = 1;
        Object obj = a; // 어 이거 왜됨

        var auto = foo();
    }

    void foo(){
        System.out.println("foo");
    }
}
