#!/bin/bash

# Replace <destination_port> with the actual port number you're interested in
destination_port=6379

while true; do
    #clear  # Optional: Clears the terminal for a cleaner output
    #echo "TCP Connections for Port $destination_port:"
    netstat -an | grep ":$destination_port"
    sleep 0.1  # Adjust the sleep duration as needed
done
