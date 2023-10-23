#
#
#      web config 
#     
#      war - file

FROM jgsoftwares/graalce21

#hostname
#ENV HOSTNAME demogitjava

# locale to german
ENV LANG=de_DE.ISO-8859-1
ENV LANGUAGE de_DE:de
ENV LC_ALL de_DE.ISO-8859-1

ENV TZ Europe/Berlin

# web
EXPOSE 80

# h2 console
EXPOSE 8082

# cluster h2 db
EXPOSE 9101
EXPOSE 9102

# derbydb 
EXPOSE 1527

# webshop port
EXPOSE 443


ADD http://demogitjava.ddns.net:8000/webshop-0.0.1-SNAPSHOT.war /root/webshop.jar
ENTRYPOINT ["java", "-jar", "/root/webshop.jar"]
