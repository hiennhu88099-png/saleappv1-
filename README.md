# SaleAppV1 - Ứng dụng Bán Hàng Trực Tuyến

## Mô tả
Ứng dụng bán hàng trực tuyến xây dựng bằng Java Spring Boot, Thymeleaf, Maven.

## Công nghệ sử dụng
- **Java 17**
- **Spring Boot 3.2.5**
- **Spring Web MVC**
- **Thymeleaf**
- **Jackson** (xử lý dữ liệu JSON)
- **Maven**

## Cấu trúc project
```
saleappv1
├── src/main/java/com/example/saleappv1
│   ├── Saleappv1Application.java
│   ├── controller/
│   │   ├── HomeController.java
│   │   └── ProductController.java
│   ├── model/
│   │   ├── Product.java
│   │   └── Category.java
│   └── service/
│       └── ProductService.java
├── src/main/resources
│   ├── data/
│   │   ├── categories.json
│   │   └── products.json
│   ├── static/
│   │   ├── css/style.css
│   │   ├── js/
│   │   └── images/
│   └── templates/
│       ├── index.html
│       ├── products.html
│       └── product-detail.html
├── pom.xml
├── README.md
└── .gitignore
```

## Hướng dẫn chạy

### Yêu cầu
- JDK 17+
- Maven 3.6+

### Các bước chạy
```bash
# Clone project
git clone <repository-url>
cd saleappv1

# Build project
mvn clean install

# Chạy ứng dụng
mvn spring-boot:run
```

### Truy cập ứng dụng
- Trang chủ: http://localhost:8080/
- Danh sách sản phẩm: http://localhost:8080/products
- Chi tiết sản phẩm: http://localhost:8080/products/{id}

## Tính năng
- Xem danh sách sản phẩm
- Xem chi tiết sản phẩm
- Lọc theo danh mục (Điện thoại di động, Máy tính bảng)
- Tìm kiếm theo tên sản phẩm
- Lọc theo khoảng giá
