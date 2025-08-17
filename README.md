<h1>Task Tracker CLI</h1><hr/>
A simple Command-Line Interface (CLI) Task Manager built in Java.  
This app helps users to create, view, and manage tasks directly from the terminal.

<h2>Features<h2><hr/>
<ol>
<li>Add a new task with description.</li>
<li>Update the existing tasks.</li>
<li>Delete a task.</li>
<li>Delete a task by ID.</li>
<li>Change the status to ```in-progress```, ```done```.</li>
<li>List all the tasks or by status.</li>
</ol>

<h2>Usage<h2>
```declarative
#Adding a new task
java TaskCLI add "Buy bread and butter"
#Output: Task added successfully.

#Updating tasks
java TaskCLI update 1 "Buy bread and butter and stationery"
#Output: Task updated successfully.

#Deleting tasks
java TaskCLI delete 1
#Output: Task deleted successfully.

# Marking a task as in progress or done
java TaskCLI mark 1 in-progress
java TaskCLI mark 1 done
#Output: Status updated successfullyy

#Listing all tasks
java TaskCLI list

# Listing tasks by status
task-cli list done
task-cli list todo
task-cli list in-progress
```

<h2>What I Learned?</h2>
<ol>
<li>File Handling & Persistence</li>
<ul>
<li>Learned how to read from and write to JSON files without using external libraries.</li>
<li>Managed differences between overwriting vs appending to the JSON file.</li>
<li>Ensured data persistence between runs, avoiding accidental resets.</li>
</ul>
<li>Data Validation & Edge Cases</li>
<ul>
<li>Validated CLI input: commands, arguments, and option values.</li>
<li>Skipped corrupted or incomplete entries with clear warnings.</li>
</ul>
<li>Date & Time Handling</li>
<ul>
<li>Used LocalDateTime to represent task creation and update times.</li>
<li>Formatted date-time values into readable strings using DateTimeFormatter when saving to file.</li>
<li>Handled DateTimeParseException when encountering badly formatted or corrupted date strings in the saved data.</li>
</ul>
</ol>