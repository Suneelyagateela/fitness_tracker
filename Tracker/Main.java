package Tracker;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Welcome! lets set up your profile.");
    	System.out.print("Enter your name: ");
    	String name=scanner.nextLine();
    	System.out.print("Enter your age: ");
    	int age=scanner.nextInt();
    	System.out.print("Enter your weight: ");
    	double weight=scanner.nextDouble();
    	scanner.nextLine();
    	System.out.print("Enter your FitnessGoal: ");
    	String fitnessGoal=scanner.nextLine();
    	UserInterface user=new UserInterface(name,age,weight,fitnessGoal);
    	
    	
        WorkoutManager manager = new WorkoutManager(user);
        
        System.out.println("Welcome to the Workout Tracker!");
        boolean running = true;
        while (running) {
            System.out.println("\n1. Add a Workout");
            System.out.println("2. View All Workouts");
            System.out.println("3. Delete a Workout");
            System.out.println("4. Update a Workout");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter workout exerciseName: ");
                    String Name = scanner.nextLine();
                    System.out.print("Enter sets: ");
                    int Sets=scanner.nextInt();
                    System.out.print("Enter reps: ");
                    int Reps=scanner.nextInt();
                    System.out.print("Enter weight: ");
                    double Weight=scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter date: ");
                    LocalDate Date=LocalDate.parse(scanner.next());
                    Workout newWorkout = new Workout(Name,Sets,Reps,Weight,Date); 
                    manager.addWorkout(newWorkout);
                    System.out.println("Workout added!");
                    break;
                case 2:
                    manager.displayWorkouts();
                    manager.displayWorkoutStats();
                    break;
                case 3:
                	System.out.print("Enter the ID of the workout to delete: ");
                	int idToDelete = scanner.nextInt();
                	manager.deleteWorkout(idToDelete);
                	break;
                case 4: 
                    System.out.print("Enter the ID of the workout to update: ");
                    int idToUpdate = scanner.nextInt();
                    scanner.nextLine();
                    
                    System.out.print("Enter new exercise name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new sets: ");
                    int newSets = scanner.nextInt();
                    System.out.print("Enter new reps: ");
                    int newReps = scanner.nextInt();
                    System.out.print("Enter new weight: ");
                    double newWeight = scanner.nextDouble();
                    
                    manager.updateWorkout(idToUpdate, newName, newSets, newReps, newWeight);
                    break;
                case 5:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option, try again.");
            }
        }
        scanner.close();
    }
}
