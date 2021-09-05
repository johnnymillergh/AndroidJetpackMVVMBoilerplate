#!/bin/bash

########################## Functions Declaration ##########################
# Bash tips: Colors and formatting (ANSI/VT100 Control sequences)
# https://misc.flogisoft.com/bash/tip_colors_and_formatting
# Pass arguments into a function
# https://bash.cyberciti.biz/guide/Pass_arguments_into_a_function
function now() {
  nowVariable=$(date +%Y-%m-%d' '%H:%M:%S.%N | cut -b 1-23)
  echo "$nowVariable"
}

function printTrace() {
  echo -e "$(now) TRACE --- $1"
  return 0
}

function printInfo() {
  echo -e "$(now)  \e[32mINFO --- $1\e[0m"
  return 0
}

function printWarn() {
  echo -e "$(now)  \e[33mWARN --- $1\e[0m"
  return 0
}

function printError() {
  echo -e "$(now) \e[31mERROR --- $1\e[0m"
  return 0
}
