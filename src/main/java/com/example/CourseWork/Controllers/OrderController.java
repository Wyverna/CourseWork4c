package com.example.CourseWork.Controllers;

import com.example.CourseWork.Models.Order;
import com.example.CourseWork.Services.OrderService;
import com.example.CourseWork.Services.OrganizationService;
import com.example.CourseWork.Services.ProductService;
import com.example.CourseWork.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping(path="/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @GetMapping( "/")
    public Iterable<Order> ListOrder()
    {
        return orderService.getAll();
    }

    @GetMapping("/getByUser")
    public Iterable<Order> getByUser(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        return orderService.getByUser((String)session.getAttribute("username"));
    }

    @GetMapping("/getSumByDate")
    public Iterable<Order> getByDate(@RequestParam("dateOrder") String dateOrder,
                                     @RequestParam("dateOrderEnd") String dateOrderEnd)
    {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = formatter.parse(dateOrder);
            Date date2 = formatter.parse(dateOrderEnd);
            return orderService.getBetweenDate(date1, date2);
        }
        catch(Exception e) {
            return null;
        }
    }

    @PostMapping("/postOrder")
    public String AddOrder(@RequestParam("dateOrder") String dateOrder,@RequestParam("dateOrderEnd") String dateOrderEnd,
                         @RequestParam("unitCost") Integer unitcost,@RequestParam("addressClient") String addressClient,
                         @RequestParam("timeToOrder") String timeToOrder,@RequestParam("nameExtraProduct") String nameExtraProduct,
                         @RequestParam("organizationName") String organizationName, @RequestParam("nameProduct") String nameProduct,
                         HttpServletRequest request)
    {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = formatter.parse(dateOrder);
            Date date2 = formatter.parse(dateOrderEnd);
            HttpSession session = request.getSession();
            Order order = new Order();
            order.setDateOrder(date1);
            order.setDateOrderEnd(date2);
            order.setUnitCost(unitcost);
            order.setTimeToOrder(timeToOrder);
            order.setAddressClient(addressClient);
            if(nameExtraProduct==null || nameExtraProduct.equals(""))
                nameExtraProduct=" ";
            order.setNameExtraProduct(nameExtraProduct);
            order.setOrganizationName(organizationService.getItem(organizationName));
            order.setNameProduct(productService.getCostProduct(nameProduct, organizationName));
            order.setUsername(userService.getItemByLoginUser((String) session.getAttribute("username")));
            orderService.AddItem(order);
            return "Succesfully adding item";
        }
        catch(Exception e)
        {
            return "Bad parameters";
        }
    }

    @GetMapping("/getByDate")
    public Iterable<Order> getByDate(@RequestParam("dateOrder") String dateOrder,@RequestParam("dateOrderEnd") String dateOrderEnd,
                                     @RequestParam("nameProduct") String nameProduct, HttpServletRequest request)
    {
        System.out.println(dateOrder);
        HttpSession session=request.getSession();
        String username= (String)session.getAttribute("username");
        String role= (String)session.getAttribute("role");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        Date date2 = null;
        if(nameProduct.equals(""))
        {
            nameProduct=null;
        }
        try {
            date1 = formatter.parse(dateOrder);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        try {
            date2 =formatter.parse(dateOrderEnd);
        }catch (Exception e){}
        return orderService.getByDate(date1,date2,nameProduct,role,username);
    }
}
