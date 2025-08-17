<h1>Task Tracker CLI</h1>
A simple Command-Line Interface (CLI) Task Manager built in Java.  
This app helps users to create, view, and manage tasks directly from the terminal.

## Features
1. Add a new task with description.
2. Update the existing tasks.
3. Delete a task by ID.
4. Change the status to `in-progress`, `done`.
5. List all the tasks or by status.

## Usage
```text
# Adding a new task
java TaskCLI add "Buy bread and butter"
# Output: Task added successfully.

# Updating tasks
java TaskCLI update 1 "Buy bread and butter and stationery"
# Output: Task updated successfully.

# Deleting tasks
java TaskCLI delete 1
# Output: Task deleted successfully.

# Marking a task as in progress or done
java TaskCLI mark 1 in-progress
java TaskCLI mark 1 done
# Output: Status updated successfully.

# Listing all tasks
java TaskCLI list

# Listing tasks by status
java TaskCLI list done
java TaskCLI list todo
java TaskCLI list in-progress
```

## What I Learned?
### 1. File Handling & Persistence
- Learned how to read from and write to JSON files without using external libraries.
- Managed differences between overwriting vs appending to the JSON file.
- Ensured data persistence between runs, avoiding accidental resets.
### 2. Data Validation & Edge Cases
- Validated CLI input: commands, arguments, and option values.
- Skipped corrupted or incomplete entries with clear warnings.
### 3. Date & Time Handling
- Used LocalDateTime to represent task creation and update times.
- Formatted date-time values into readable strings using DateTimeFormatter when saving to file.
- Handled DateTimeParseException when encountering badly formatted or corrupted date strings in the saved data.
