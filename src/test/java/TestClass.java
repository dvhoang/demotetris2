import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestClass {
    @Test
    public void test(){
        int[][] arr = {{1, 2}, {3, 4}};
        int x = arr[1][0];
        System.out.println("Giá trị là: "+x);
        assertEquals(3, x);
    }
}
