package com.example.fistmock;

import java.util.List;

public class UserService {
    
    private final UserRepository repository;
    private final EmailClient emailClient;
    
    // Constructor for dependency injection
    public UserService(UserRepository repository, EmailClient emailClient) {
        this.repository = repository;
        this.emailClient = emailClient;
    }
    
    /**
     * Creates a new user and sends a welcome email
     * @throws IllegalArgumentException if email already exists
     */
    public User createUser(User user) {
        if(!user.getEmail().contains("@")) throw new IllegalArgumentException("Invalid Email: " + user.getEmail());
        if(user.getName() == null) throw new IllegalArgumentException("Name Cannot be NULL");

        if (repository.existsByEmail(user.getEmail())) throw new IllegalArgumentException("Email already exists: " + user.getEmail());
        
        
        User savedUser = repository.save(user);
        
        // Send welcome email
        emailClient.send(
            savedUser.getEmail(),
            "Welcome!",
            "Welcome to our service, " + savedUser.getName() + "!"
        );
        
        return savedUser;
    }
    
    /**
     * Finds a user by ID
     * @throws IllegalArgumentException if user not found
     */
    public User getUser(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
    }
    
    /**
     * Updates an existing user
     * @throws IllegalArgumentException if user not found
     */
    public User updateUser(Long id, User updatedUser) {
        User existingUser = getUser(id);
        
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setActive(updatedUser.isActive());
        
        return repository.save(existingUser);
    }
    
    /**
     * Deactivates a user instead of deleting
     * @throws IllegalArgumentException if user not found
     */
    public void deactivateUser(Long id) {
        User user = getUser(id);
        user.setActive(false);
        repository.save(user);
        
        // Notify user of deactivation
        emailClient.send(
            user.getEmail(),
            "Account Deactivated",
            "Your account has been deactivated."
        );
    }
    
    /**
     * Permanently deletes a user
     */
    public void deleteUser(Long id) {
        if (!repository.findById(id).isPresent()) {
            throw new IllegalArgumentException("User not found with id: " + id);
        }
        repository.deleteById(id);
    }
    
    /**
     * Gets all active users
     */
    public List<User> getActiveUsers() {
        return repository.findAllActive();
    }
    
    /**
     * Gets total user count
     */
    public long getUserCount() {
        return repository.count();
    }
    
    /**
     * Checks if an email is available
     */
    public boolean isEmailAvailable(String email) {
        return !repository.existsByEmail(email);
    }
}
