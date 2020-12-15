package com.example.CourseWork.Controllers;

import com.example.CourseWork.Services.OrderService;
import com.example.CourseWork.Services.OrganizationService;
import com.example.CourseWork.Services.ProductService;
import com.example.CourseWork.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.CourseWork.Models.Order;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.w3c.dom.Document;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
@RestController
@RequestMapping(path="/api/xml")
public class XmlController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private UserService userService;

    @PostMapping("/ImportXML")
    public String UploadFile(@RequestParam("file") MultipartFile file)
    {
        try {
            String xml = new String(file.getBytes());
            Document doc = convertStringToXMLDocument(xml);
            NodeList nodes =doc.getFirstChild().getChildNodes();
            for(int i=0;i<nodes.getLength();i++)
            {
                System.out.println(nodes.item(i).getNodeName());
                String nameProduct = nodes.item(i).getAttributes().getNamedItem("nameProduct").getNodeValue();
                String username = nodes.item(i).getAttributes().getNamedItem("username").getNodeValue();
                String addressClient = nodes.item(i).getAttributes().getNamedItem("address").getNodeValue();
                String dateOrder =  nodes.item(i).getAttributes().getNamedItem("dateOrder").getNodeValue();
                String dateOrderEnd =  nodes.item(i).getAttributes().getNamedItem("dateOrderEnd").getNodeValue();
                Integer unitcost = Integer.parseInt(nodes.item(i).getAttributes().getNamedItem("unitcost").getNodeValue());
                String organizationName = nodes.item(i).getAttributes().getNamedItem("organizationName").getNodeValue();
                String timeToOrder = nodes.item(i).getAttributes().getNamedItem("timeToOrder").getNodeValue();
                String nameExtraProduct = nodes.item(i).getAttributes().getNamedItem("extraProductName").getNodeValue();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date1 = formatter.parse(dateOrder);
                Date date2 = formatter.parse(dateOrderEnd);
                Order order=new Order();
                order.setDateOrder(date1);
                order.setDateOrderEnd(date2);
                order.setUnitCost(unitcost);
                order.setTimeToOrder(timeToOrder);
                order.setAddressClient(addressClient);
                order.setNameExtraProduct(nameExtraProduct);
                order.setOrganizationName(organizationService.getItem(organizationName));
                order.setNameProduct(productService.getCostProduct(nameProduct,organizationName));
                order.setUsername(userService.getItemByLoginUser(username));
                orderService.AddItem(order);
            }
            return "Succesfuly adding orders";
        }
        catch (Exception e)
        {
            return "Bad parameters";
        }
    }
    @GetMapping("/ExportXML")
    public void DownloadFile(HttpServletResponse response) throws Exception
    {
        Iterable<Order> orders = orderService.getAll();
        String xml="<Orders>";
        for (Order order:orders
             ) {
            xml+="<Order nameProduct='"+order.getNameProduct().getNameProduct()+"' username='"+
                    order.getUsername().getLoginuser()+"' dateOrder='"+
                    order.getDateOrder().toString()+"' dateOrderEnd='"+order.getDateOrderEnd().toString()+
                    "' unitcost='"+order.getUnitCost()+"' "+
                    "address='"+order.getAddressClient()+"' organizationName='"+
                    order.getOrganizationName().getOrganizationName()+"' "+"timeToOrder='"+
                    order.getTimeToOrder()+"' extraProductName='"+order.getNameExtraProduct()+"'/>";
        }
        xml+="</Orders>";
        response.addHeader("Content-Disposition", "attachment; filename=Export.xml");
        ServletOutputStream out = response.getOutputStream();
        out.write(xml.getBytes());
    }

    private static Document convertStringToXMLDocument(String xmlString)
    {
        //Parser that produces DOM object trees from XML content
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        //API to obtain DOM Document instance
        DocumentBuilder builder = null;
        try
        {
            //Create DocumentBuilder with default configuration
            builder = factory.newDocumentBuilder();

            //Parse the content to Document object
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            return doc;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
