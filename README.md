# Player Communication System

## Overview
This is a Java-based player communication system where two players exchange messages in a loop. The system can run in either:
1. **Same Process Mode**: Both players (server and client) run as separate threads.
2. **Separate Process Mode**: Each player runs in a different Java process.

The project focuses on **pure Java implementation**, with no external frameworks, and adheres to clean code principles.

## Features
- Two players communicate by exchanging messages.
- Message exchange continues until the initiator sends and receives 10 messages.
- Execution in the same or separate process modes.
- Logging of **Process ID (PID)** and **Thread Name** for debugging.
- Simple shell script (`run.sh`) to start the program.

## Project Structure
```
📂 src/main/java/com/task/playercommunication
├── App.java          # Main class to start server and client
├── Constants.java    # Stores configuration constants
├── Message.java      # Represents a message structure
├── Player.java       # Represents a player entity
│
📂 src/main/java/com/task/playercommunication/network
├── Node.java         # Abstract class handling communication logic
├── Server.java       # Server (Player 1) - starts first and listens for Client
├── Client.java       # Client (Player 2) - connects to Server
└── run.sh            # Shell script to start the program interactively
```

## How to Run
### **1. Build the Project**
To compile and package the project, run:
```sh
mvn package
```

Ensure you have **Maven** installed. If needed, clean and install using:
Ensure you have **Maven** installed. Navigate to the project root and run:
```sh
mvn clean install
```

### **2. Run the Application**
Use the provided shell script to start the application interactively:
```sh
chmod +x run.sh
./run.sh
```
This will prompt you to choose:
1. **Same Process Mode**
2. **Separate Process Mode**

Alternatively, run manually using:
```sh
java -cp target/your-jar-file.jar com.task.playercommunication.App same
```

## Design Considerations
- **Same Process Mode**: Uses **threads** for parallel execution.
- **Separate Process Mode**: Each player runs as an independent process.
- **Logging**: The **Server** logs its PID and thread before waiting for a client.
- **Graceful Termination**: The program stops automatically after 10 message exchanges.

## Requirements
- Java 11+ (for ProcessHandle API support)
- Maven for building the project

## Author
Hamza Farooq 🚀

