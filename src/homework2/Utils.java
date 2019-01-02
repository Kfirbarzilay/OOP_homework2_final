package homework2;
/** <p>Utilty helper tools for ADT response handling
 * <p>Success: the action has succeded in doing the modifies and effect in its spec.
 * <p>InvalidArguments: failure. the input does not meet the requires in the methods spec.
 * <p>itemDoesntExist:failure. the input does not exist and the adt method needs that the item should exist.
 * <p>itemAlreadyExists:failure. the input does exist already and the adt method needs that the item shouldnt exist.
 * <p>SelfLoop:failure. no self loop edges allowed. See header file of graph.
 */
public class Utils {
	public enum Result {
		Success,invalidArguments,itemDoesntExist,itemAlreadyExists,SelfLoop
	}
}
