package Tracker;
import java.time.LocalDate;

public class Workout {
	private String exerciseName;
	private int sets;
	private int reps;
	private double weight;
	private LocalDate date;
	//constructor
	public Workout(String exerciseName,int sets,int reps,double weight,LocalDate date) {
		this.exerciseName=exerciseName;
		this.sets=sets;
		this.reps=reps;
		this.weight=weight;
		this.date=date;
	}
	
	public Workout(String exerciseName) {
		this.exerciseName=exerciseName;
	}
	//setters
	public void setExerciseName(String exerciseName) {
		this.exerciseName=exerciseName;
	}
	public void setSets(int sets) {
		this.sets=sets;
	}
	public void setReps(int reps) {
		this.reps=reps;
	}
	public void setWeight(double weight) {
		this.weight=weight;
	}
	public void setDate(LocalDate date) {
		this.date=date;
	}
	//getters
	public String getExerciseName() {
		return exerciseName;
	}
	public int getSets() {
		return sets;
	}
	public int getReps() {
		return reps;
	}
	public double getWeight() {
		return weight;
	}
	public LocalDate getDate() {
		return date;
	}
}
