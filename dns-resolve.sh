while true; do
    #clear  # Optional: Clears the terminal for a cleaner output
    #echo "TCP Connections for Port $destination_port:"
    dig +nocmd +noquestion +nocomments +nostats +answer redis-test-2-ro.8grenv.ng.0001.use1.cache.amazonaws.com

    sleep 0.25  # Adjust the sleep duration as needed
done