# Build
mvn clean package && docker build -t de.schoeffm/streamoutput .

# RUN

docker rm -f streamoutput || true && docker run -d -p 8080:8080 -p 4848:4848 --name streamoutput de.schoeffm/streamoutput 

# Test the result

First, start the server with the commands above - then request the (slow) resource using i.e. `curl` (and measure the time spend):

    $ time curl http://localhost:8080/streamoutput/api/resource/stream

in another terminal have a look at the traffic using `tcpdump`

    $ sudo tcpdump -A -nn -i lo0 port 8080
    
you should see several chunks transmitted once each buffer is filled.

Next, we can compare the results with the same data but when we don't stream the output. The total time spend won't differ much:

    $ time curl http://localhost:8080/streamoutput/api/resource/normal

But when looking at the transmissions you'll notice that there's only one once the complete result is available. Hence the user has to wait until the last bit is ready before he/she receives anything at all (=> perceived perfromance is worse)
 
    $ sudo tcpdump -A -nn -i lo0 port 8080
