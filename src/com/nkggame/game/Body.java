package com.nkggame.game;

/**
 * 
 * @author Nathan Geater
 *
 */
public abstract class Body {
	protected Body[] satellites;
	protected double size;
	protected int temperature;
	protected int color;
	protected int ringSize;
	protected boolean isRinged;
	public Body[] getSatellites() {
		return satellites;
	}
	public void setSatellites(Body[] satellites) {
		this.satellites = satellites;
	}
	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
	}
	public int getTemperature() {
		return temperature;
	}
	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public int getRingSize() {
		return ringSize;
	}
	public void setRingSize(int ringSize) {
		this.ringSize = ringSize;
	}
	public boolean isRinged() {
		return isRinged;
	}
	public void setRinged(boolean isRinged) {
		this.isRinged = isRinged;
	}
	
}
