cd ./kernels/jvm
mvn clean install
cd ../../antlr
sh quiz.sh &  PIDIOS=$!
cd ./output
npm run start &  PIDMIX=$!
wait $PIDIOS
wait $PIDMIX
cd ../../