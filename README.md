# phonebooking

PhoneBooking is a Spring Boot API for managing phone bookings. It allows users to book and return phones and sends notifications on these actions.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

What you need to install the software:

- [Docker](https://www.docker.com/products/docker-desktop)
- [Docker Compose](https://docs.docker.com/compose/install/)

### Installing and Running

Follow these steps to get your development environment running:

1. **Clone the Repository**

    ```bash
    git clone https://github.com/jstardxb/phonebooking.git
    cd phonebooking
    ```

2. **Start Services with Docker Compose**

    ```bash
    docker-compose up --build
    ```

3. **Accessing the Application**

   The application will be accessible at http://localhost:8080. The Swagger UI for testing the API endpoints can be found at http://localhost:8080/swagger-ui/index.html.

## API Endpoints

The following endpoints are available:

- **Book a Phone**: `POST /api/phone/book`
- **Return a Phone**: `POST /api/phone/return`

To use these endpoints, authenticate with the following credentials:

- **Username**: admin
- **Password**: admin

### RabbitMQ Management Interface

To monitor and manage message queues, access the RabbitMQ Management Interface at http://localhost:15672.

- **Username**: guest
- **Password**: guest

## Continuous Integration

This project uses GitHub Actions for Continuous Integration (CI). Every push triggers automated builds and tests.