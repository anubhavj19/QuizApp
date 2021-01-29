#!/bin/bash

####################################################
# DO NOT CHANGE THE GRADLE OPTIONS IN THE BLOCK    #
# BELOW, IT WILL HAVE IMPACT ON THE PERFORMANCE    #
# OF YOUR APPLICATION                              #
####################################################
GRADLE_OPTS="-Dgradle.user.home=~/gradle_cache"    #
####################################################


./gradlew clean bootrun &

while ! netstat -tna | grep 'LISTEN\>' | grep -q ':8081\>'; do
  echo "waiting for spring application to start"
  sleep 2 # time in seconds, tune it as needed
done

mongoimport --uri mongodb+srv://admin:adminP%40ssw0rd@buildout-qa.esf6i.mongodb.net/test --collection questions --type json --file initial_data_load.json
#mongoimport --uri mongodb+srv://mongodb+srv://admin:Anand123@buildout-qa.94q9m.mongodb.net/test --collection questions --type json --file initial_data_load.json
#mongoimport --db test --drop --collection questions --file initial_data_load.json

# If you have any script to load the data make sure that its part of this bash script.

