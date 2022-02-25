d1=$(find ./src/main/resources -type f -printf '%T@\n'| sort -n | tail -1)
d2=$(find ./src/main/resources -type f -printf '%T@\n'| sort -n | tail -1)
echo "starting development watcher"
echo "mvn clean package"
mvn clean package > log_build.txt
grep -F '[ERROR]' ./build.txt
echo "waiting for updating file..."
while true
do
    if [ "$d1" != "$d2" ]; then
    
        d1=$(find ./src/main/resources -type f -printf '%T@\n'| sort -n | tail -1)
        f=$(find ./src/main/resources -type f -printf '%T@ %p\n' | sort -n | tail -1 | cut -f2- -d" " | awk '{ print substr( $0, 3 ) }')
        echo "Regenerate code for $f"
        mvn exec:java -D"exec.args"="$f" 1> log_execution.txt
        err=$(grep -F '[ERROR]' ./run.txt)
        err_size=$(expr length "$err")
        if [ "$err_size" = "0" ] ; then
            echo "generation done for file $f"
        else
            echo "$err"
        fi
    fi
    sleep 1
    d2=$(find ./src/main/resources -type f -printf '%T@\n'| sort -n | tail -1)

done