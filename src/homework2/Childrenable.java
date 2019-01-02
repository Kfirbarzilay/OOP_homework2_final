package homework2;
/**
 * This is an interface to make the generic graph nodes of ADT Graph implement getChildren method.
 * */
import java.util.ArrayList;

public interface Childrenable<N> {
	public ArrayList<N> getChildren();
}
