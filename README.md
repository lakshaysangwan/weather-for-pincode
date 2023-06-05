# Weather Information API (Spring Boot)

This project provides a REST API to retrieve weather information for a specific day and pincode. The API allows you to save the pincode's latitude and longitude separately and store the weather information in a relational database (RDBMS) for optimized API calls in the future. It is implemented using Spring Boot framework.

## Installation

1. Clone the repository:

   ```
   git clone https://github.com/lakshaysangwan/weather-for-pincode.git
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
POST /weather
Content-Type: application/json

{
  "pincode": 122002,
  "for_date": "2020-10-15"
}

```

Parameters:
- `pincode` (required): The pincode for which you want to retrieve weather information.
- `for_date` (required): The specific date for which you want weather information.

#### Response

```json
{
    "message": "Success",
    "weather_data": {
        "lat": 28.4628,
        "lon": 77.0895,
        "data": [
            {
                "dt": 1602720000,
                "temp": 300.28,
                "clouds": 40,
                "sunset": 1602764534,
                "sunrise": 1602723132,
                "weather": [
                    {
                        "id": 721,
                        "icon": "50n",
                        "main": "Haze",
                        "description": "haze"
                    }
                ],
                "humidity": 36,
                "pressure": 994,
                "wind_deg": 0,
                "dew_point": 283.93,
                "feels_like": 299.91,
                "visibility": 2200,
                "wind_speed": 0
            }
        ],
        "timezone": "Asia/Kolkata",
        "timezone_offset": 19800
    }
}
```

## Database Schema

The database schema consists of three tables:

### geocoded_data

| Column     | Type        | Description                            |
| ---------- | ----------- | -------------------------------------- |
| latitude   | float       | The latitude of the pincode's location  |
| longitude  | float       | The longitude of the pincode's location |
| pin_code   | int         | The pincode associated with the location|
| id         | bigint      | Primary key                            |
| name       | varchar(255)| Optional name for the location          |

### requests_logging

| Column     | Type                  | Description                            |
| ---------- | --------------------- | -------------------------------------- |
| pin_code   | int                   | The pincode associated with the request |
| created_at | datetime(6)           | The timestamp of the request            |
| id         | bigint                | Primary key                            |
| date       | varchar(255)          | The specific date for the request       |

### unique_weather_record

| Column        | Type         | Description                                |
| ------------- | ------------ | ------------------------------------------ |
| pincode       | int          | The pincode associated with the weather record |
| id            | bigint       | Primary key                                |
| api_response  | json         | The weather API response in JSON format    |
| date          | varchar(255) | The specific date of the weather record     |

## Contributing

If you want to contribute to this project, please follow
