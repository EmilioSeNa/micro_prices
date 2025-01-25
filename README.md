Here is the improved `README.md` file, incorporating the **technologies, dependencies, and plugins** from your `pom.xml`, ensuring that it aligns with your project's configuration.

---

```md
# 🛒 Price Management Microservice

This microservice provides an API to query product prices based on a given date, product ID, and brand ID.  
It uses **Spring Boot 3**, **Spring Data JPA**, **H2 Database**, and follows a **test-driven development (TDD)** approach with **JUnit 5**, **Mockito**, and **Jacoco** for test coverage.

---

## 🚀 Features

- Retrieves applicable product prices based on a given timestamp.
- Prioritizes prices based on business rules.
- Uses an **in-memory H2 database** for local development and testing.
- Includes **JUnit 5** tests for repository, service, and controller.
- Integrated **Swagger UI** for API documentation.
- **Test coverage reports** generated using **JaCoCo**.
- Uses **MapStruct** for entity-DTO conversion.
- **DevTools** enabled for live reload during development.

---

## 🛠️ Technologies Used

- **Java 17**
- **Spring Boot 3.4.0**
- **Spring Web (REST API)**
- **Spring Data JPA**
- **H2 Database (In-memory)**
- **SpringDoc OpenAPI (Swagger UI)**
- **JUnit 5 (Testing)**
- **Mockito (Unit Testing)**
- **MapStruct (Object Mapping)**
- **JaCoCo (Code Coverage)**
- **Lombok (Simplify Java code)**

---

## 📦 Database Initialization

The application **auto-populates** the database on startup using `DatabaseInitializer`:

```java
@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {

    private final PricesRepository pricesRepository;

    @Override
    public void run(String... args) {
        pricesRepository.save(new Prices(null, 1, LocalDateTime.of(2020, 6, 14, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59), 1, 35455, 0, 35.50, "EUR"));

        pricesRepository.save(new Prices(null, 1, LocalDateTime.of(2020, 6, 14, 15, 0),
                LocalDateTime.of(2020, 6, 14, 18, 30), 2, 35455, 1, 25.45, "EUR"));

        pricesRepository.save(new Prices(null, 1, LocalDateTime.of(2020, 6, 15, 0, 0),
                LocalDateTime.of(2020, 6, 15, 11, 0), 3, 35455, 1, 30.50, "EUR"));

        pricesRepository.save(new Prices(null, 1, LocalDateTime.of(2020, 6, 15, 16, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59), 4, 35455, 1, 38.95, "EUR"));
    }
}
```

---

## 📡 API Endpoints

### **1️ Get applicable price**
Retrieves the applicable price based on the given date, product ID, and brand ID.

- **URL:** `GET /api/prices`
- **Query Parameters:**
  - `date` (String, format: `yyyy-MM-dd HH:mm:ss`)
  - `productId` (Integer)
  - `brandId` (Integer)
- **Response:**
  - `200 OK` - Returns the price details.
  - `404 Not Found` - If no applicable price exists.

🔹 **Example Request:**
```bash
curl -X GET "http://localhost:8080/api/prices?date=2020-06-14 10:00:00&productId=35455&brandId=1" -H "Accept: application/json"
```

🔹 **Example Response:**
```json
{
  "id": 1,
  "brandId": 1,
  "startDate": "2020-06-14 00:00:00",
  "endDate": "2020-12-31 23:59:59",
  "priceList": 1,
  "productId": 35455,
  "priority": 0,
  "price": 35.50,
  "currency": "EUR"
}
```

---

## 📖 Swagger API Documentation

This microservice includes **Swagger UI** for easy API testing.

### **Access Swagger UI**
🔗 [Swagger UI](http://localhost:8080/swagger-ui.html)

### **Access OpenAPI JSON**
🔗 [OpenAPI JSON](http://localhost:8080/v3/api-docs)

### **Swagger Integration in Spring Boot**
This project uses **SpringDoc OpenAPI** for auto-generated API documentation.

#### **Dependencies (in `pom.xml`)**
```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.0.2</version>
</dependency>
```

#### **Controller with OpenAPI Annotations**
```java
@RestController
@RequestMapping("/api/prices")
@RequiredArgsConstructor
@Tag(name = "Prices API", description = "Endpoints for querying product prices")
public class PricesController {

	private final PricesService pricesService;

	/**
	 * Retrieves the applicable price for a given product, brand, and date.
	 *
	 * @param date      The application date (formatted as yyyy-MM-dd HH:mm:ss)
	 * @param productId The product identifier
	 * @param brandId   The brand identifier
	 * @return The applicable price or 404 if none found
	 */
	@Operation(summary = "Get applicable price", description = "Retrieves the applicable price based on the date, product ID, and brand ID.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful response", content = @Content(schema = @Schema(implementation = PricesDTO.class))),
			@ApiResponse(responseCode = "404", description = "Price not found") })
	@GetMapping
	public ResponseEntity<?> getPrice(@RequestParam(name = "date") String date,
			@RequestParam(name = "productId") int productId, @RequestParam(name = "brandId") int brandId) {

		LocalDateTime dateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		Optional<PricesDTO> prices = pricesService.getApplicablePrice(productId, brandId, dateTime);
		return prices.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
}
```

---

## 🧪 Running Tests

This project includes **unit and integration tests** with **JUnit 5**, **Mockito**, and **Spring Boot Test**.

- Run all tests:
```bash
mvn test
```

### **Test Coverage Reports**
- The project uses **JaCoCo** to generate test coverage reports.
- To generate the report, run:
```bash
mvn verify
```
- The coverage report will be available in:
```
target/site/jacoco/index.html
```

---

## 🏗️ How to Run

### **1️ Clone the repository**
```bash
git clone https://github.com/EmilioSeNa/micro_prices.git
cd micro_prices
```

### **2️ Build the project**
```bash
mvn clean install
```

### **3️ Run the application**
```bash
mvn spring-boot:run
```

### **4️ Application is available at**
```
http://localhost:8080
```

