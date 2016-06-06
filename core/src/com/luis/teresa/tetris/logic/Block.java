package com.luis.teresa.tetris.logic;

/**
 * Each Instance of this class is responsible to store the informations of that Block .
 * Each shape is composed of several blocks
 * @author Luis
 * @author Teresa
 */
public class Block {
	private Shape parent;
	private String color;
	private int x_global,y_global;
	private int x_shape,y_shape;

	/**
	 * Constructor for Blocks that Constitute the Shape in the Game
	 * @param shape			Parent Shape 
	 * @param x_in_shape	X coordinate in parent Shape
	 * @param y_in_shape	Y coordinate in parent Shape
	 * @param c			Block Color
	 */
	public Block(Shape shape, int x_in_shape, int y_in_shape , String c){
		this.parent=shape;
		x_shape=x_in_shape; 
		y_shape=y_in_shape;
		y_global=y_in_shape + parent.getY_world(); 
		x_global=x_in_shape + parent.getX_world();
		color=c;
	}
	
	/**
	 * Constructor for Blocks that constitute the Stack
	 * @param x_world		X coordinate in the Board
	 * @param y_world		y coordinate in the Board
	 */
	public Block(int x_world, int   y_world){
		this.parent = null;
		x_shape=-1; 
		y_shape=-1;
		y_global=y_world; 
		x_global=x_world;
		color="black";
	}

	/**
	 * Get X coordinate in the Board
	 * @return		X coordinate
	 */
	public int getX_global() {
		return x_global;
	}

	/**
	 * Set X coordinate in the Board
	 * param x_global	X coordinate
	 */
	public void setX_global(int x_global) {
		this.x_global = x_global;
	}

	/**
	 * Get Y coordinate in the Board
	 * @return		Y coordinate
	 */
	public int getY_global() {
		return y_global;
	}

	/**
	 * Set Y coordinate in the Board
	 * param y_global	Y coordinate
	 */
	public void setY_global(int y_global) {
		this.y_global = y_global;
	}

	/**
	 * Get X coordinate in the Shape
	 * @return		X coordinate
	 */
	public int getX_shape() {
		return x_shape;
	}

	/**
	 * Set X coordinate in the Shape
	 * param x_global	X coordinate
	 */
	public void setX_shape(int x_shape) {
		this.x_shape = x_shape;
	}

	/**
	 * Get Y coordinate in the Shape
	 * @return		Y coordinate
	 */
	public int getY_shape() {
		return y_shape;
	}

	/**
	 * Set Y coordinate in the Shape
	 * param y_global	Y coordinate
	 */
	public void setY_shape(int y_shape) {
		this.y_shape = y_shape;
	}
	
	/**
	 * Get the Color of the Block
	 * @return			String with color name
	 */
	public String getColor() {
		return color;
	}
	
	/**
	 * Set the Color of the Block
	 * @param color		String with color name
	 */
	public void setColor(String color) {
		this.color = color;
	}
	
	/**
	 * Get the Parent Shape for this Block
	 * @return			Shape
	 */
	public Shape getParent(){
		return parent;
	}
	
	/**
	 * Set new Parent Shape for this block
	 * @param p			Shape
	 */
	public void setParent(Shape p){
		if(p==null){
			this.x_shape=-1;
			this.y_global=-1;
		}
		this.parent= p;
	}
}
