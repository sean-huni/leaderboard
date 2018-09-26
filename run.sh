#!/usr/bin/env bash

# Script to deploy the app
deploy(){
# From the remote host-machine, run the following cmd
    docker pull s34n/leaderboard-img
    docker run --name='leaderboard' -d -it -p 8004:8004 s34n/leaderboard-img && docker logs leaderboard -f
}

# Tag-And-Push Script to tag & push the app
tagAndPush(){
    docker tag leaderboard-img:latest s34n/leaderboard-img:latest
    docker push s34n/leaderboard-img:latest
}

# Rebuild-Script to clean & build the app using the Dockerfile script
rebuild(){
    gradle clean
    gradle build
    docker build -f Dockerfile -t leaderboard-img . --no-cache
    tagAndPush
}

# Let's get rid of the pre-existing docker images on the machine.
if [[ ! -z "$(docker container ps | grep leaderboard)" ]]; then
    echo "Leaderboard-Service Docker Container Found"
    docker stop leaderboard && docker rm leaderboard && docker rmi leaderboard-img
    rebuild
else
    echo "Leaderboard-Service Docker Container NOT Found"
    rebuild
fi

