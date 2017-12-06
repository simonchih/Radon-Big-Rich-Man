import java.util.List;
import java.util.Random;
 
public class Random_Pick_List {
 
	private Random random = new Random();

	public int getRandomList(List<Integer> list) {
	    int index = random.nextInt(list.size());
	    return list.get(index);
	}
 
}