import React, { useState, useEffect } from "react";

function App() {
  const [tasks, setTasks] = useState([]);
  const [task, setTask] = useState("");

  const BACKEND_URL = "https://cicd-backend-mamz.onrender.com";

  // Fetch tasks from backend
  const fetchTasks = () => {
    fetch(`${BACKEND_URL}/tasks`)
      .then(res => res.json())
      .then(data => setTasks(data))
      .catch(err => console.error("Failed to fetch:", err));
  };

  useEffect(() => {
    fetchTasks();
  }, []);

  // Add a new task
  const addTask = () => {
    if (!task.trim()) return; // Prevent empty tasks
    fetch(`${BACKEND_URL}/tasks`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ task })
    })
    .then(res => res.json())
    .then(() => {
      setTasks([...tasks, task]);
      setTask("");
    })
    .catch(err => console.error("Failed to add task:", err));
  };

  // Delete a task
  const deleteTask = (taskToDelete) => {
    fetch(`${BACKEND_URL}/tasks`, {
      method: "DELETE",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ task: taskToDelete })
    })
    .then(res => res.json())
    .then(result => {
      if (result.success) {
        setTasks(tasks.filter(t => t !== taskToDelete));
      } else {
        alert(result.message);
      }
    })
    .catch(err => console.error("Failed to delete task:", err));
  };

  return (
    <div style={{ padding: "21px", fontFamily: "Arial" }}>
      <h1>To-Do App</h1>
      <input
        value={task}
        onChange={e => setTask(e.target.value)}
        placeholder="Enter task"
        style={{ padding: "5px", marginRight: "10px" }}
      />
      <button onClick={addTask} style={{ padding: "5px 10px" }}>Add</button>

      <ul style={{ marginTop: "20px" }}>
        {tasks.map((t, i) => (
          <li key={i} style={{ marginBottom: "10px" }}>
            {t} 
            <button
              onClick={() => deleteTask(t)}
              style={{ marginLeft: "10px", padding: "2px 5px" }}
            >
              Delete
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;
