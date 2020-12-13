package com.example.CourseWork.Services;

import com.example.CourseWork.Models.Order;
import com.example.CourseWork.Repositories.OrderRepository;
import com.example.CourseWork.Repositories.ProductRepository;
import com.example.CourseWork.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    public Iterable<Order> getAll()
    {
        return orderRepository.findAll();
    }

    public Iterable<Order> getByUser(String username)
    {
        return  orderRepository.findAllByUsername(userRepository.findByLoginuser(username));
    }

    public Iterable<Order> getBetweenDate(Date date1,Date date2)
    {
        return  orderRepository.findAllByDateOrderBetween(date1,date2);
    }

    public void AddItem(Order order)
    {
        orderRepository.save(order);
    }

    public Iterable<Order> getByDate(Date date1, Date date2, String nameProduct, String role,String username)
    {
        System.out.println(date1);
        System.out.println(date2);
        System.out.println(nameProduct);
        if(role.equals("admin")) {
            if (date1 == null && date2 == null && nameProduct == null)
                return orderRepository.findAll();
            else if (date1 != null && date2 == null && nameProduct == null)
                return orderRepository.findAllByDateOrderGreaterThanEqual(date1);
            else if (date1 == null && date2 != null && nameProduct == null)
                return orderRepository.findAllByDateOrderIsLessThanEqual(date2);
            else if (date1 == null && date2 == null && nameProduct != null)
                return orderRepository.findAllByNameProduct(productRepository.findByNameProduct(nameProduct));
            else if (date1 != null && date2 != null && nameProduct == null)
                return orderRepository.findAllByDateOrderGreaterThanEqualAndDateOrderLessThanEqual(date1, date2);
            else if (date1 != null && date2 == null && nameProduct != null)
                return orderRepository.findAllByDateOrderGreaterThanEqualAndNameProduct(date1,
                        productRepository.findByNameProduct(nameProduct));
            else if (date1 == null && date2 != null && nameProduct != null)
                return orderRepository.findAllByDateOrderIsLessThanEqualAndNameProduct(date2,
                        productRepository.findByNameProduct(nameProduct));
            else
                return orderRepository.findAllByDateOrderGreaterThanEqualAndDateOrderLessThanEqualAndNameProduct(date1, date2,
                        productRepository.findByNameProduct(nameProduct));
        }
        else
        {
            if (date1 == null && date2 == null && nameProduct == null)
                return  orderRepository.findAllByUsername(userRepository.findByLoginuser(username));
            else if (date1 != null && date2 == null && nameProduct == null)
                return orderRepository.findAllByDateOrderGreaterThanEqualAndUsername(date1,userRepository.findByLoginuser(username));
            else if (date1 == null && date2 != null && nameProduct == null)
                return orderRepository.findAllByDateOrderIsLessThanEqualAndUsername(date2,userRepository.findByLoginuser(username));
            else if (date1 == null && date2 == null && nameProduct != null)
                return orderRepository.findAllByNameProductAndUsername(productRepository.findByNameProduct(nameProduct),
                        userRepository.findByLoginuser(username));
            else if (date1 != null && date2 != null && nameProduct == null)
                return orderRepository.findAllByDateOrderGreaterThanEqualAndDateOrderLessThanEqualAndUsername(date1, date2,
                        userRepository.findByLoginuser(username));
            else if (date1 != null && date2 == null && nameProduct != null)
                return orderRepository.findAllByDateOrderGreaterThanEqualAndNameProductAndUsername(date1,
                        productRepository.findByNameProduct(nameProduct),userRepository.findByLoginuser(username));
            else if (date1 == null && date2 != null && nameProduct != null)
                return orderRepository.findAllByDateOrderIsLessThanEqualAndNameProductAndUsername(date2,
                        productRepository.findByNameProduct(nameProduct),userRepository.findByLoginuser(username));
            else
                return orderRepository.findAllByDateOrderGreaterThanEqualAndDateOrderLessThanEqualAndNameProductAndUsername
                        (date1, date2,productRepository.findByNameProduct(nameProduct),userRepository.findByLoginuser(username));
        }
    }
}
