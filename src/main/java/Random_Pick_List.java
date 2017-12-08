
import java.util.List;
import java.util.Random;

public class Random_Pick_List {

	private final Random random = new Random();

	public int getRandomList(final List<Integer> list) {
	    final int index = random.nextInt(list.size());
	    return list.get(index);
	}
}
