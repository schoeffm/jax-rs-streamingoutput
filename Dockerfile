FROM airhacks/payara
COPY ./target/streamoutput.war ${DEPLOYMENT_DIR}
