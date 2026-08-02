package Tracker;
public class UserInterface { 
	private String name;
	private int age;
	private double weight;
	private String fitnessGoal;
	//constructor
	public UserInterface(String name,int age,double weight,String fitnessGoal) {
		this.name=name;
		this.age=age;
		this.weight=weight;
		this.fitnessGoal=fitnessGoal;
	}
	//setters
	public void setName(String name) {
		this.name=name;
	}
	public void setAge(int age) {
		this.age=age;
	}
	public void setWeight(double weight) {
		this.weight=weight;
	}
	public void setFitnessGoal(String fitnessGoal) {
		this.fitnessGoal=fitnessGoal;
	}
	//getters
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public double getWeight() {
		return weight;
	}
	public String getFitnessGoal() {
		return fitnessGoal;
	}
}
