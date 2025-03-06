#!/bin/bash

# Ask the user to choose execution mode
echo "Choose mode: (1) startInSameProcess, (2) startSeparateProcess"
read -r choice

case "$choice" in
    1) mode="same" ;;
    2) mode="separate" ;;
    *) echo "[ERROR] Invalid input! Exiting."; exit 1 ;;
esac

# Run the Java application with the selected mode
echo "[INFO] Running application in '$mode' mode..."
java -cp target/hamza-farooq-coding-task-1.0-SNAPSHOT.jar com.task.playercommunication.App "$mode"

exit 0
