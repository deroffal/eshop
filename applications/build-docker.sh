#!/bin/bash

version="0.8"

script_dir="$(dirname "$0")"

for project in marketplace price product stock
do
  docker build -t deroffal/eshop-$project:$version $script_dir/eshop-$project
done




