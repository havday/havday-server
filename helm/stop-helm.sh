#!/bin/bash

delete_chart() {
  local name=$1
  echo "delete $name "
  helm delete "$name"
#  if [ $? -ne 0 ]; then
#      echo "Failed to delete $name"
#      exit 1
#  fi
  echo "$name delete successfully!"
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
  delete_chart "$chart"
done

echo "All charts delete successfully!"
exit 0