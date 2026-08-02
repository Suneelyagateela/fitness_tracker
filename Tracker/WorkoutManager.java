package Tracker;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class WorkoutManager {
	private UserInterface user;
    private final String URL = "jdbc:mysql://localhost:3306/fitness_tracker";
    private final String USER = "root";       
    private final String PASSWORD = "YOUR_PASSWORD";
    public WorkoutManager(UserInterface user) {
    	this.user=user;
    }
    public void setUser(UserInterface user) {
    	this.user=user;
    }
    public UserInterface getUser() {
    	return user;
    }
    public void addWorkout(Workout workout) {
        String sql = "INSERT INTO workouts (exercise_name, sets, reps, weight, workout_date) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, workout.getExerciseName());
            pstmt.setInt(2, workout.getSets());
            pstmt.setInt(3, workout.getReps());
            pstmt.setDouble(4, workout.getWeight());
            pstmt.setDate(5, java.sql.Date.valueOf(workout.getDate()));
            pstmt.executeUpdate();
            System.out.println("Workout saved to database!");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void displayWorkouts() {
           System.out.println("--- User Profile ---");
        System.out.println("Name: " + user.getName() +" | Age: "+user.getAge()+" | Weight: "+user.getWeight()+ " | Goal: " + user.getFitnessGoal());
        System.out.println("--------------------");
        String sql = "SELECT * FROM workouts";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            System.out.println("---"+user.getName()+"'s workouts ---");
            
            boolean hasWorkouts = false;
            while (rs.next()) {
                hasWorkouts = true;
                String name = rs.getString("exercise_name");
                int sets = rs.getInt("sets");
                int reps = rs.getInt("reps");
                double weight = rs.getDouble("weight");
                Date date = rs.getDate("workout_date");
                
                System.out.println("ID: "+rs.getInt("id")+" Workout: " + name + " | sets: " + sets + 
                                   " | reps: " + reps + " | weight: " + weight + 
                                   " | date: " + date);
            }
            
            if (!hasWorkouts) {
                System.out.println("No workouts found in database.");
            }
            
        } catch (SQLException e) {
            System.out.println("Error fetching data: " + e.getMessage());
        }
    }
    public void deleteWorkout(int id) {
        String sql = "DELETE FROM workouts WHERE id = ?";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Workout deleted successfully!");
            } else {
                System.out.println("No workout found with ID: " + id);
            }
            
        } catch (SQLException e) {
            System.out.println("Error deleting workout: " + e.getMessage());
        }
    }
    public void updateWorkout(int id, String newExercise, int newSets, int newReps, double newWeight) {
        String sql = "UPDATE workouts SET exercise_name = ?, sets = ?, reps = ?, weight = ? WHERE id = ?";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, newExercise);
            pstmt.setInt(2, newSets);
            pstmt.setInt(3, newReps);
            pstmt.setDouble(4, newWeight);
            pstmt.setInt(5, id);
            
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Workout updated successfully!");
            } else {
                System.out.println("No workout found with ID: " + id);
            }
            
        } catch (SQLException e) {
            System.out.println("Error updating workout: " + e.getMessage());
        }
    }
    public void displayWorkoutStats() {
        String sql = "SELECT SUM(sets * reps * weight) as total_volume, MAX(weight) as max_weight FROM workouts";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            if (rs.next()) {
                double totalVolume = rs.getDouble("total_volume");
                double maxWeight = rs.getDouble("max_weight");
                
                System.out.println("\n------------------------------------");
                System.out.println("       --- PERFORMANCE STATS ---");
                System.out.println("TOTAL VOLUME LIFTED: " + totalVolume);
                System.out.println("HEAVIEST LIFT:       " + maxWeight);
                System.out.println("------------------------------------\n");
            }
            
        } catch (SQLException e) {
            System.out.println("Error calculating stats: " + e.getMessage());
        }
    }
}
