mvn clean install -DskipTests
cp target/pccguser-0.0.1-SNAPSHOT.jar .
docker build -t pccguser-demo .
docker run -d -p 8080:8080 --name my-pccguser pccguser-demo