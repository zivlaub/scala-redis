echo "Setup started"
echo "Installing SDKMAN..."
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
echo "Installing scala..."
sdk install scala
echo "Installing sbt..."
sdk install sbt
echo "Setup completed successfully!"
