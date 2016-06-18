package com.allyopen.wloss.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import com.allyopen.wloss.model.Product;
import com.allyopen.wloss.model.ProductBean;

//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.RequestScoped;

@Named("productController") //@ManagedBean(name = "productController")
@SessionScoped
public class ProductController implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6377321328312334975L;
	private String msg;
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    @PostConstruct
    private void init() {
        msg = "Hello World!! JFS example.. ";
    }

    private Product product = new Product();
    public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	private ProductBean productBean = new ProductBean();
    private List<Product> productList =new ArrayList<>();
			
	
	public List<Product> getProductList() {
		productList = (List<Product>) productBean.getProducts();
		return productList;
	}

	public String viewProduct() {
		return "productList.xhtml";
	}

	public String addNewProduct() {
		product = productBean.createNewProduct(product);
		productList = productBean.getProducts();
		return "productList.xhtml";
	}
}