package lambda;

import java.util.function.Consumer;

/**
 * lambda使用
 */
public class LambdaTest {
    public static void main(String[] args) {
        /**
         * 將 () -> System.out.println("fly"); 作为方法赋值给了 action。
         */
        Action action = () -> System.out.println("fly");

        Consumer<Integer> consumer = (obj) -> {
            System.out.println("doing 1" + ++obj);
        };

        consumer = consumer.andThen((obj) -> {
            System.out.println("doing 2" + ++obj);
        }).andThen((obj) -> {
            System.out.println("doing 3" + ++obj);
        });

        consumer.accept(128);
    }

}
