package com.luis.teresa.tetris.logic;

public class Block {
	private Shape parent;
	private String color;
	private int x_global,y_global;
	private int x_shape,y_shape;

	
	public Block(Shape shape, int x_in_shape, int y_in_shape , String color){
		this.parent = shape;
		this.setX_shape(x_in_shape); 
		this.setY_shape(y_in_shape);
		this.setY_global(y_in_shape + parent.getY_world()); //verificar
		this.setX_global(x_in_shape + parent.getX_world());
		this.setColor(color);
	}
	public Block(int x_world, int   y_world){
		this.parent = null;
		this.setX_shape(-1); 
		this.setY_shape(-1);
		this.setY_global(y_world); 
		this.setX_global(x_world);
		this.setColor("black");
	}

	public void reachedFloor(){
		this.parent=null;
		this.setX_shape(-1); 
		this.setY_shape(-1);
	}
	public int getX_global() {
		return x_global;
	}
	public void setX_global(int x_global) {
		this.x_global = x_global;
	}
	public int getY_global() {
		return y_global;
	}
	public void setY_global(int y_global) {
		this.y_global = y_global;
	}
	public int getX_shape() {
		return x_shape;
	}
	public void setX_shape(int x_shape) {
		this.x_shape = x_shape;
	}
	public int getY_shape() {
		return y_shape;
	}
	public void setY_shape(int y_shape) {
		this.y_shape = y_shape;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	public Shape getParent(){
		return parent;
	}
	public void setParent(Shape p){
		if(p==null){
			this.x_shape=-1;
			this.y_global=-1;
		}
		this.parent= p;
	}
}
