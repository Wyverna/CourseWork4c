package com.example.CourseWork.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.CourseWork.Models.*;

import java.util.Date;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface OrderRepository extends CrudRepository<Order, Integer> {
    Iterable<Order> findAllByUsername(User username);
    Iterable<Order> findAllByDateOrderBetween(Date date1, Date date2);
    Iterable<Order> findAllByNameProduct(Product product);
    Iterable<Order> findAllByDateOrderGreaterThanEqual(Date date1);
    Iterable<Order> findAllByDateOrderIsLessThanEqual(Date date2);
    Iterable<Order> findAllByDateOrderGreaterThanEqualAndDateOrderLessThanEqual(Date date1,Date date2);
    Iterable<Order> findAllByDateOrderGreaterThanEqualAndNameProduct(Date date1, Product product);
    Iterable<Order> findAllByDateOrderIsLessThanEqualAndNameProduct(Date date2,Product product);
    Iterable<Order> findAllByDateOrderGreaterThanEqualAndDateOrderLessThanEqualAndNameProduct(Date date1, Date date2,
                                                                                                 Product product);
    Iterable<Order> findAllByNameProductAndUsername(Product product,User username);
    Iterable<Order> findAllByDateOrderGreaterThanEqualAndUsername(Date date1,User user);
    Iterable<Order> findAllByDateOrderIsLessThanEqualAndUsername(Date date2,User user);
    Iterable<Order> findAllByDateOrderGreaterThanEqualAndDateOrderLessThanEqualAndUsername(Date date1,Date date2,User user);
    Iterable<Order> findAllByDateOrderGreaterThanEqualAndNameProductAndUsername(Date date1, Product product,User user);
    Iterable<Order> findAllByDateOrderIsLessThanEqualAndNameProductAndUsername(Date date2,Product product,User user);
    Iterable<Order> findAllByDateOrderGreaterThanEqualAndDateOrderLessThanEqualAndNameProductAndUsername(Date date1, Date date2,
                                                                                              Product product,User user);
}