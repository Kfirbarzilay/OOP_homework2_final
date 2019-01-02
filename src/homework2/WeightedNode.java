package homework2;

import org.omg.CORBA.INTERNAL;

/**
 * A WeightedNode class is a simple record type which contains a name
 * and a cost. 
 **/
public class WeightedNode implements Comparable<WeightedNode> {
	
	/**
	 * Name of this node.
	 */
  	private final String name;
  	
  	
  	/**
  	 * Cost of this node.
  	 */
  	private final int cost;


	/**
	 * Color of this node.
	 */
	private String color;


	/**
	 * Number of the back edges of this node.
	 */
	private Integer backEdgesNum;

  	/**
     * Creates a WeightedNode.
     * @effects creates new WeightedNode with the name
     * <tt>name</tt> and the cost <tt>cost</tt> and a default color White..
     *
     */

  	public WeightedNode(String name, int cost) {
    	this.name = name;
      	this.cost = cost;
		this.color = "White";
		this.backEdgesNum = 0;
  	}

  
	/**
	 * Returns this.name.
     * @return this.name
     */
  	public String getName() {
    	return name;
  	}


	/**
	 * Returns this.backEdgesNum.
     * @return this.backEdgesNum
     */
  	public Integer getBackEdgesNum() {
    	return this.backEdgesNum;
  	}


	/**
	 * @modifies this
	 * @effects the number of the back edges
	 */
	public void increaseBackEdges(){
		this.backEdgesNum++;
	}


	/**
	 * Returns this.cost.
     * @return this.cost
     */
  	public int getCost() {
    	return cost;
  	}

	/**
	 * Returns this.color.
	 * @return this.color
	 */
	public String getColor(){
		return color;
	}

	/**
	 * @requires valid string color
	 * @modifies this
	 * @effects the color of the object
	 */
	public void setColor(String color){
		this.color = color;
	}

	/**
	 * @requires valid string color
	 * @modifies this
	 * @effects the color of the object
	 */
	public void initNumofBackEdges(){
		this.backEdgesNum = 0;
	}

	/**
	 * Standard equality operation.
	 * @return true iff o.instaceOf(WeightedNode) &&
	 *         (this.name.eqauls(o.name) && (this.cost == o.cost)
	 */
	public boolean equals(Object o) {
    	if (o instanceof WeightedNode) {
      		WeightedNode other = (WeightedNode)o;
      		return this.name.equals(other.name) &&
				   (this.cost == other.cost);
    	}
    	return false;
  	}
  
  
	/**
	 * Returns a hash code value for this.
	 * @return a hash code value for this.
	 */
  	public int hashCode() {
    	return name.hashCode();
  	}


	/**
	 * Standard object to string conversion.
	 * @return a string representation of this in the form [name: cost].
	 */
  	public String toString() {
    	return "[" + name + ": " + cost + "]";
  	}



	/**
	 * Compares this with the specified object for order.
	 * @return a negative integer, zero, or a positive integer as this
	 * 		   object is respectively less than, equal to, or greater than
	 *         the specified object .
	 * 		   <p>
	 * 		   WeightedNodes are ordered by their numeric ordering of their costs.
	 * 		   When two nodes share a cost, their ordering is determined
	 *         lexicographically by their nameic ordering.
	 */
	public int compareTo(WeightedNode o) {
		int diff_cost = cost - o.cost;
		if (diff_cost == 0)
			return -name.compareTo(o.name);
		else
			return -diff_cost;
	}


}
