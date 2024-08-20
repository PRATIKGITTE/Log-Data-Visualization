import json
import time
import random
from datetime import datetime

# List of sample log messages
log_messages = [
    {"level": "INFO", "message": "Received GET request", "endpoint": "/api/v1/resource", "status_code": 200},
    {"level": "INFO", "message": "Received POST request", "endpoint": "/api/v1/resource", "status_code": 201},
    {"level": "WARNING", "message": "Rate limit exceeded", "endpoint": "/api/v1/resource", "status_code": 429},
    {"level": "ERROR", "message": "Internal server error", "endpoint": "/api/v1/resource", "status_code": 500},
    {"level": "INFO", "message": "Authentication successful", "endpoint": "/api/v1/auth", "status_code": 200},
    {"level": "ERROR", "message": "Authentication failed", "endpoint": "/api/v1/auth", "status_code": 401},
    {"level": "INFO", "message": "Resource updated", "endpoint": "/api/v1/resource/123", "status_code": 200},
    {"level": "INFO", "message": "Resource deleted", "endpoint": "/api/v1/resource/123", "status_code": 204},
    {"level": "INFO", "message": "Database query executed", "query": "SELECT * FROM resources WHERE id=123", "status_code": 200},
    {"level": "ERROR", "message": "Database connection failed", "query": "SELECT * FROM resources WHERE id=123", "status_code": 500},
]

def generate_log_entry():
    log_entry = random.choice(log_messages)
    log_entry["timestamp"] = datetime.now().isoformat()
    return log_entry

def write_logs_to_file(filename, num_entries):
    with open(filename, 'w') as file:
        log_entries = [generate_log_entry() for _ in range(num_entries)]
        json.dump(log_entries, file, indent=4)
    print(f"{num_entries} log entries have been written to {filename}")

if __name__ == "__main__":
    try:
        num_entries = 100  # Number of log entries to generate
        filename = 'logs.json'
        write_logs_to_file(filename, num_entries)
    except KeyboardInterrupt:
        print("Log generation stopped.")
