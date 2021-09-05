#!/bin/bash

# Exit immediately if a command exits with a non-zero status.
# https://stackoverflow.com/questions/19622198/what-does-set-e-mean-in-a-bash-script
set -e

########################## Functions Import ##########################
source ./.travis/common.sh

# Directory check
CURRENT_DIR=$(pwd)
printInfo "[install] CURRENT_DIR: $CURRENT_DIR"
printInfo "[install] List of CURRENT_DIR:"
command "ls"

printInfo "[install] Start to run Gradle command [clean,build]…"
# Run the Gradle command
./gradlew clean build
INSTALL_COMMAND_RESULT=$?
if [ "$INSTALL_COMMAND_RESULT" -eq 0 ]; then
  printInfo "[install] Installation succeed. INSTALL_COMMAND_RESULT: $INSTALL_COMMAND_RESULT"
else
  printError "[install] Installation failed. INSTALL_COMMAND_RESULT: $INSTALL_COMMAND_RESULT" >&2
  exit 1
fi
