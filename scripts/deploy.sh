#!/usr/bin/env bash

echo 'Copy files...'

scp -i ~/.ssh/id_rsa \
    target/EventService-1.0.jar \
    $REMOTEUSER@$REMOTEHOST:$REMOTEAPPDIR
echo 'Restart server...'
ssh -i ~/.ssh/id_rsa $REMOTEUSER@$REMOTEHOST << EOF

pgrep java | xargs kill -9
nohup java -jar EventService-1.0.jar > log.txt &

EOF

echo 'Bye'