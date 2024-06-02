#!/bin/bash

# Function to install a Helm chart
install_upgrade_chart() {
  local name=$1
  local path=$2
  echo "Installing $name from $path..."
  helm upgrade "$name" "$path"
  if [ $? -ne 0 ]; then
    helm install "$name" "$path"
    if [ $? -ne 0 ]; then
      echo "Failed to install $name"
      exit 1
    fi
  fi
  echo "$name installed successfully!"
}

# List of charts to install
charts=(
  "api-gateway"
  "delivery-service"
  "member-service"
  "order-service"
  "product-service"
  "review-service"
)

# Loop through the charts and install each one
for chart in "${charts[@]}"; do
  install_upgrade_chart "$chart" "./$chart"
done

echo "All charts installed successfully!"
exit 0