
![Build workflow](https://github.com//Todo/actions/workflows/main.yml/badge.svg?event=push)

## High-Level Components

- **API Layer**: Handles HTTP requests and responses. It will define endpoints for CRUD operations on Todos.
- **Service Layer**: Contains business logic. It orchestrates the operations between the API layer and the Data Access layer.
- **Data Access Layer (DAL)**: Responsible for data persistence. It interacts with a database or an in-memory data structure to store and retrieve Todos.
- **Model**: Defines the data structure of a Todo and its Subtasks.

## Model

### Todo

- `id`: Unique identifier (mandatory)
- `name`: Name of the todo (mandatory)
- `description`: Description of the todo (optional)
- `tasks`: List of Subtasks (optional)

### Subtask

- `name`: Name of the subtask (mandatory)
- `description`: Description of the subtask (optional)

## API Endpoints and Operations

- `GET /todos`: Fetch all Todos.
- `POST /todos`: Create a new Todo.
- `GET /todos/{id}`: Fetch a single Todo by ID.
- `PUT /todos/{id}`: Update an existing Todo by ID.
- `DELETE /todos/{id}`: Delete a Todo by ID.
