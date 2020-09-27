#!/bin/bash

DIR="$( cd "$( dirname "$0" )" && pwd )"
cd $DIR

# Enable /etc/rc.d/init.d/functions style color
COLOR=true

####################################################################
# Do not edit anything below this, unless you want to of course :) #
####################################################################

if [ "$COLOR" = "true" ]; then
    RES_COL=60
    MOVE_TO_COL="echo -en \\033[${RES_COL}G"
    SETCOLOR_SUCCESS="echo -en \\033[1;32m"
    SETCOLOR_FAILURE="echo -en \\033[1;31m"
    SETCOLOR_WARNING="echo -en \\033[1;33m"
    SETCOLOR_NORMAL="echo -en \\033[0;39m"
else
    MOVE_TO_COL=""
    SETCOLOR_SUCCESS=""
    SETCOLOR_FAILURE=""
    SETCOLOR_WARNING=""
    SETCOLOR_NORMAL=""
fi

## Echo PID of pid (or 0 if not running)
pid() {
    if [ -f .pid ]; then
        if [ -n "`cat .pid | xargs ps --no-headers`" ]; then
            cat .pid
            return
        fi
    fi
    echo -n 0
}

## Start module
startModule() {
    echo -n "Starting Module:"
    if [ "`pid`" != "0" ]; then
        echoresult FAILED "An instance of Module is already running"
        return 1
    fi
    ## All clear
    umask 007

    LIB_PATH=$DIR/lib
    BIN_PATH=$DIR/bin

    CLASSPATH=$CLASSPATH:$DIR/cfg/:
    for filename in `ls $DIR/lib|grep jar`
    do
            CLASSPATH=$CLASSPATH$LIB_PATH/$filename:
    done
    for filename in `ls $DIR/bin|grep jar`
    do
            CLASSPATH=$CLASSPATH$BIN_PATH/$filename:
    done

    CLASSPATH=$CLASSPATH.:
    export CLASSPATH

    #java -jar $DIR/bin/$MODULE_JAR &
    java -Dinstance=mobcoll-service-db com.catcha.mobcoll.servicedb.rest.ServiceDbRest &
    echo -n $! > .pid
    echoresult OK
    return 0
}

## kill module
killModule() {
    echo -n "Stopping Module"
    OP=`pid`
    if [ $OP != "0" ]; then
        kill -9 $OP
        echoresult OK
        return 0
    else
        echoresult FAILED "Cannot determine Module pid"
        return 1
    fi
}

## Echo result message
echoresult() {
    echo -n " "
    $MOVE_TO_COL
    echo -n "[  "
    case "$1" in
        'OK')
            $SETCOLOR_SUCCESS
        ;;
        'FAILED')
            $SETCOLOR_FAILURE
        ;;
        *)
            $SETCOLOR_WARNING
        ;;
    esac
    echo -n $1
    $SETCOLOR_NORMAL
    echo "  ]"
    shift
    if [ "$#" != "0" ] ; then echo "$1" ; fi
}

## Show help message
showhelp() {

    cat <<EOF
Usage: $0 {start|stop|status|help}

    start   : Start module
    stop    : Terminate module
    status  : Check status of module
    help    : Help

EOF

}

# If root is running this script, su to $APP_USER first
if [ "$UID" = "0" ]; then
    exec su - $APP_USER -c "$0 $1"
fi

case "$1" in

    'stop')
        killModule
    ;;

    'status')
        OP=`pid`
        if [ $OP == "0" ]; then
            echo "Module is stopped"
            exit 1
        else
            echo "Module is running (pid $OP)"
        fi
    ;;

    *)
        startModule
    ;;

esac

exit $?

