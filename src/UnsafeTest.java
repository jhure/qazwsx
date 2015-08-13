import java.lang.reflect.Field;
import sun.*;

public class UnsafeTest {

	
	
	
	public static void main(String[] args) {
		
		
		Field f = Unsafe.class.getDeclaredField("theUnsafe"); //Internal reference
		f.setAccessible(true);
		Unsafe unsafe = (Unsafe) f.get(null);


		
		
	}
}
