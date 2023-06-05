# weather-for-pincode
# Weather Information API (Spring Boot)

This project provides a REST API to retrieve weather information for a specific day and pincode. The API allows you to save the pincode's latitude and longitude separately and store the weather information in a relational database (RDBMS) for optimized API calls in the future. It is implemented using Spring Boot framework.

## Installation

1. Clone the repository:

   ```
   git clone https://github.com/your-username/your-repo.git
   ```

2. Import the project into your preferred IDE (e.g., IntelliJ, Eclipse).

3. Build the project using Maven or your IDE's build feature.

4. Set up the database:

   - Create a new RDBMS (e.g., MySQL, PostgreSQL) database.
   - Update the database connection configuration in the `application.properties` file with your database credentials.

5. Run the application:

   - If using an IDE, run the main application class.
   - If using Maven, run `mvn spring-boot:run` from the project's root directory.

6. The application should now be running on `http://localhost:8080`.

## API Usage

### Get Weather Information

#### Request

```
GET /weather?pincode=411014&for_date=2020-10-15
```

Parameters:
- `pincode` (required): The pincode for which you want to retrieve weather information.
- `for_date` (required): The specific date for which you want weather information.

#### Response

```json
{
  "status": "success",
  "data": {
    "pincode": "411014",
    "latitude": 18.5204,
    "longitude": 73.8567,
    "weather": {
      "date": "2020-10-15",
      "temperature": 28,
      "humidity": 75,
      "description": "Partly cloudy"
    }
  }
}
```

### Save Weather Information

#### Request

```
POST /weather
Content-Type: application/json

{
  "pincode": "411014",
  "latitude": 18.5204,
  "longitude": 73.8567,
  "date": "2020-10-15",
  "temperature": 28,
  "humidity": 75,
  "description": "Partly cloudy"
}
```

#### Response

```json
{
  "status": "success",
  "message": "Weather information saved successfully"
}
```

## Database Schema

The database schema consists of two tables:

### pincode_location

| Column    | Type       | Description                            |
| --------- | ---------- | -------------------------------------- |
| id        | integer    | Primary key                            |
| pincode   | varchar    | The pincode associated with the location|
| latitude  | decimal    | The latitude of the pincode's location  |
| longitude | decimal    | The longitude of the pincode's location |

### weather_information

| Column       | Type       | Description                                |
| ------------ | ---------- | ------------------------------------------ |
| id           | integer    | Primary key                                |
| pincode_id   | integer    | Foreign key referencing pincode_location   |
| date         | date       | The date for which the weather is recorded  |
| temperature  | decimal    | The temperature on the recorded date        |
| humidity     | decimal    | The humidity on the recorded date           |
| description  | varchar    | The weather description on the recorded date|

## Contributing

If you want to contribute to this project, please follow the guidelines specified in the CONTRIBUTING.md file. Contributions are welcome!

## License

This project is licensed under the [MIT License
