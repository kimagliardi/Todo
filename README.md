# Simple Todo REST API
![Kotlin](https://img.shields.io/badge/Kotlin-0095D5?&logo=kotlin&logoColor=white)
![Micronaut](https://img.shields.io/badge/Micronaut-1A171B?&logo=Micronaut&logoColor=white)
[![Build](https://github.com/kimagliardi/Todo/actions/workflows/main.yml/badge.svg)](https://github.com/kimagliardi/Todo/actions/workflows/main.yml)


This project is a simple REST API built using the Kotlin programming language and the Micronaut framework. Designed to provide a straightforward yet powerful interface for managing Todo items, this API offers a comprehensive set of CRUD operations (Create, Read, Update, Delete) that interact seamlessly with a PostgreSQL database.

## Features

- **CRUD Operations**: Supports full CRUD capabilities allowing users to create, read, update, and delete Todo items effortlessly.
- **Kotlin and Micronaut**: Leveraging the expressive power of Kotlin and the lightweight, reactive nature of the Micronaut framework for efficient API development.
- **PostgreSQL Integration**: Utilizes PostgreSQL, a robust and reliable database, ensuring data persistence and secure transactions for Todo items.
- **Architecture**: Organized into layers including API, Service, and Data Access Layers, promoting code modularity and ease of maintenance.

## Getting Started

This section will guide you through setting up the project locally, ensuring you have all necessary tools and dependencies installed to run the API.

### Prerequisites

- JDK 21
- Kotlin
- Micronaut Framework
- PostgreSQL

### Installation

1. Clone the repository:
````
git clone https://github.com/kimagliardi/Todo.git
````
2. Configure your PostgreSQL database settings by running the command `docker compose up`
3. Build the project: `./gradlew build`
4. Run the project: `./gradlew run`



## API Endpoints

The API provides the following endpoints for managing Todo items:

- `GET /todos`: Fetch all Todo items.
- `POST /todos`: Create a new Todo item.
- `GET /todos/{id}`: Fetch a single Todo item by ID.
- `PUT /todos/{id}`: Update an existing Todo item by ID.
- `DELETE /todos/{id}`: Delete a Todo item by ID.

Example of body for creating a Todo:

```
{
  "name": "Grocery Shopping",
  "description": "List of items to buy at the grocery store",
  "tasks": [
    {
      "name": "Fruits",
      "description": "Apples, Bananas, Oranges"
    },
    {
      "name": "Vegetables",
      "description": "Carrots, Broccoli, Lettuce"
    }
  ]
}
```