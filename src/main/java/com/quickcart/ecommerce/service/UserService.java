package com.quickcart.ecommerce.service;

import com.quickcart.ecommerce.entity.Cart;
import com.quickcart.ecommerce.entity.Order;
import com.quickcart.ecommerce.entity.UserEntry;
import com.quickcart.ecommerce.repository.UserRepository;
import com.quickcart.ecommerce.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    public void saveEntry(UserEntry userEntry) {
        userEntry.setRoles(List.of("USER"));
        userRepository.save(userEntry);
    }

    public List<UserEntry> getAllUser() {
        return userRepository.findAll();
    }

    public Optional<UserEntry> getById(String myId) {
        return userRepository.findById(myId);
    }

    public void deleteById(String myId) {
        userRepository.deleteById(myId);
    }


    public void updateUserCart(String userId, Cart cart) {
        UserEntry user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.getCarts().add(cart);
            userRepository.save(user);
        }
    }

    public void addOrderToUser(String userId, Order order) {
        UserEntry user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.getOrders().add(order);
            userRepository.save(user);
        }
    }
}
