#!/bin/bash

set -e -o pipefail

version=${1:-"0.9"}

projects=("marketplace" "price" "product" "stock")

build_docker_image() {
  local project=$1
  local version=$2
  local image="deroffal/eshop-${project}:${version}"

  echo "Building Docker image $image"

  if docker build -t "$image" "./applications/eshop-$project"; then
    echo "Successfully built $image"
  else
    echo "Error: Failed to build $image" >&2
    exit 1
  fi
}

echo "Building Docker images for version $version"

for project in "${projects[@]}"; do
  build_docker_image "$project" "$version"
done

echo "All Docker images built successfully"
