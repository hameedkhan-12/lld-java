public interface SearchEngine {
    SearchResult search(SearchQuery query);
}

public class ElasticSearchEngine implements SearchEngine {
    @Override
    public SearchResult search(SearchQuery query) {
        return new SearchResult();
    }
}

public abstract class Product {
    protected int id;
    protected String name;
    protected String description;
    protected String brand;
    protected double basePrice;
    protected Map<String,String> attributes = new HashMap<>();

    public Product(int id, String name, String description, String brand, double basePrice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.basePrice = basePrice;
    }

    public int getId(){
        return id;
    }

    public abstract double getDisplayPrice(PricingContext ctx);
}

public class SimpleProduct extends Product {
    private String sku;
    private int qty;
    private double weight;

    public SimpleProduct(int id, String name, String description, String brand, double basePrice, String sku, int qty, double weight) {
        super(id, name, description, brand, basePrice);
        this.sku = sku;
        this.qty = qty;
        this.weight = weight;
    }

    @Override
    public double getDisplayPrice(PricingContext ctx) {
        return basePrice;
    }
}

import java.util.*;

public class Variant {
    private int id;
    private String sku;
    private Map<String, String> attributes = new HashMap<>();
    private double price;
    private int quantity;

    public Variant(
            int id,
            String sku,
            double price) {

        this.id = id;
        this.sku = sku;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}

public class VariantProduct extends Product {
    private List<Variant> variants = new ArrayList<>();

    public VariantProduct(int id, String name, String description, String brand, double price) {
        super(id, name, description, brand, basePrice);
    }

    public void addVariant(Variant variant) {
        variants.add(variant);
    }

    @Override 
    public double getDisplayPrice(PricingContext ctx) {
        return variants.get(0).getPrice();
    }
}

public class Category {
    private int id;
    private String name;
    private Category parent;
    private List<Category> children = new ArrayList<>();

    public Category(int id, String name){
        this.id = id;
        this.name = name;
    }

    public void addChild(Category child) {
        child.parent = this;
        children.add(child);
    }
}

enum MediaType {
    IMAGE,
    VIDEO,
    DOCUMENT
}

public class ProductMedia {
    private int id;
    private String url;
    private MediaType type;
    private int sortOrder;
}

public class Review {
    private int id;
    private User user;
    private Product product;
    private int rating;
    private String title;
    private String comment;
    private LocalDate createdAt;
}

public class ProductCatalog {
    private Map<Integer, Category> categories;
    private Map<Integer, Product> products;

    public ProductCatalog() {
        this.categories = new HashMap<>();
        this.products = new HashMap<>();
    }

    public void addProduct(Product p) {
        products.put(p.getId(), p);
    }

    public Product getProduct(int id){
        return products.get(id);
    }

    public void addCategory(Category c) {
        categories.put(c.getId(), c);
    }

    public Category getCategory(int id) {
        return categories.get(id);
    }

    public Collection<Product> getAllProducts(){
        return products.values();
    }
}

public class InventoryService {
    public boolean isInStock(String sku){
        return true;
    }

    public int getQuantity(String sku){
        return 10;
    }
}
public class ProductFilter {
    private Integer categoryId;
    private String brand;
    private Double minPrice;
    private Double maxPrice;
    private Map<String, List<String>> attributes;
}
public class ProductSummary {
    private int productId;
    private String name;
    private String brand;
    private String thumbnailUrl;
    private double price;
    private double rating;
    private boolean inStock;
}
public class ProductPage {
    private List<ProductSummary> content;
    private int page;
    private int size;
    private long totalElement;
    private int totalPages;
}
public class PricingContext {
    private int userId;
    private String currency;
    private String location;
    private String channel;
}
public class ProductService {
    private ProductCatalog catalog;
    private InventoryService inventoryService;
    private SearchEngine searchEngine;

    public ProductService(ProductCatalog catalog, InventoryService inventoryService, SearchEngine searchEngine) {
        this.catalog = catalog;
        this.inventoryService = inventoryService;
        this.searchEngine = searchEngine;
    }

    public Product getProduct(int id) {
        return catalog.getProduct(id);
    }

    public void createProduct(Product product){
        return catalog.addProduct(product);
    }

    public ProductPage listProducts(ProductFilter filter, int page, int size){
        ProductPage result = new ProductPage();
        return result;
    }
}

public class User {
    private long id;
    private String name;
    private String email;
    private Set<Role> roles = new HashSet<>();
}

public class Role {
    private long id;
    private String name;
    private Set<Permission> permissions = new HashSet<>();
}